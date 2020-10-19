package com.hobo.bob.writer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.DataRow;
import com.hobo.bob.model.Event;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;
import com.hobo.bob.model.Session;
import com.hobo.bob.util.DataConverter;

public class DataWriter {

	private String filepath;
	private Event event;

	public DataWriter(String filepath, Event event) {
		this.filepath = filepath;
		this.event = event;
	}

	public void writeBestAndGhost() throws IOException {
		try (OutputStream best = new FileOutputStream(filepath + "Best.csv");
				OutputStream bestTiming = new FileOutputStream(filepath + "Best-timing.csv");
				OutputStream ghost = new FileOutputStream(filepath + "Ghost.csv");
				OutputStream ghostTiming = new FileOutputStream(filepath + "Ghost-timing.csv")) {
			int bufferRows = getBufferRows(event.getBest());
			String prologue = getPrologue(event.getBest(), bufferRows);
			best.write(prologue.getBytes());
			bestTiming.write(prologue.getBytes());
			writeHeaders(best, event.getBest());
			writeTimingHeaders(bestTiming);
			DataRow last = writeLap(best, event.getBest(), 0);
			writeLapTiming(bestTiming, event.getBest(), 0, 1, 1);
			String footer = getFooter(event.getBest(), event.getBest(), 1, 1, last);
			best.write("# Session End\n\n".getBytes());
			bestTiming.write(footer.getBytes());
			System.out.println("Best lap file written...");
			if (event.getBest().getPrevBest() != null) {
				ghost.write(prologue.getBytes());
				ghostTiming.write(prologue.getBytes());
				writeHeaders(ghost, event.getBest().getPrevBest());
				writeTimingHeaders(ghostTiming);
				last = writeLap(ghost, event.getBest().getPrevBest(), 0);
				writeLapTiming(ghostTiming, event.getBest().getPrevBest(), 0, 1, 1);
				footer = getFooter(event.getBest().getPrevBest(), event.getBest().getPrevBest(), 1, 1, last);
				ghost.write("# Session End\n\n".getBytes());
				ghostTiming.write(footer.getBytes());
				System.out.println("Ghost lap file written...");
			}
		}
	}

	public void writeAll() {
//		for (Session session : event.getSessions()) {
		event.getSessions().parallelStream().forEach(session -> {
			if (!session.getLaps().isEmpty()) {
				String filename = session.getLaps().size() == 1 ? "Lap " + session.getLaps().get(0).getLapNum()
						: "Session " + session.getSessionNum();
				try (OutputStream out = new FileOutputStream(filepath + filename + ".csv");
						OutputStream outTiming = new FileOutputStream(filepath + filename + "-timing.csv")) {
					writeSession(out, outTiming, session);
					System.out.println(filename + " file written...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
//		}
	}
	
	public void compareLaps(int lapNum1, int lapNum2) {
		if (lapNum1 <= 0 || lapNum1 > event.getLaps().size() || lapNum2 <= 0 || lapNum2 > event.getLaps().size()) {
			throw new IllegalArgumentException("Can't compare laps " + lapNum1 + " and " + lapNum2
					+ " because there are only " + event.getLaps().size() + " laps.");
		}
		
		Lap lap1 = event.getLaps().get(lapNum1 < lapNum2 ? lapNum1 - 1 : lapNum2 - 1);
		Lap lap2 = event.getLaps().get(lapNum1 < lapNum2 ? lapNum2 - 1 : lapNum1 - 1);
		
		String filename = "Lap" + lap1.getLapNum() + "and" + lap2.getLapNum() + "Comparison";
		try (OutputStream out = new FileOutputStream(
				filepath + filename + ".csv");
				OutputStream outTiming = new FileOutputStream(filepath + filename + "-timing.csv")) {
			int bufferRows = getBufferRows(event.getBest());
			String prologue = getPrologue(event.getBest(), bufferRows);
			out.write(prologue.getBytes());
			outTiming.write(prologue.getBytes());
			writeHeaders(out, event.getBest());
			writeTimingHeaders(outTiming);

			
			// Write first lap
			lap1.setPreciseStartTime(ConversionConstants.LAP_BUFFER);
			lap1.setDataStartTime(lap1.getLapData().get(0).getTime() - ConversionConstants.LAP_BUFFER);
			if (lap1.getStartBufferData().isEmpty() || getRowSessionTime(lap1, lap1.getLapData().get(0).getTime()) != 0) {
				DataRow start = lap1.getLapData().get(0).clone();
				start.addTime(-1 * ConversionConstants.LAP_BUFFER);
				writeRow(out, lap1, start, start);
			}
			DataRow last = writeLap(out, lap1, 0);
			last.addTime(lap1.getLapTime() - (last.getTime() - lap1.getLapStart().getTime()));
			writeRow(out, lap1, last, last);
			
			// Separate laps by 5 minutes to ensure separator isn't the best lap
			double lapTimeSeparator = 300;
			last.addTime(lapTimeSeparator - 0.001);
			writeRow(out, lap1, last, last);
			printLapHeader(out, 2, lapTimeSeparator);
			
			// Write second lap
			// TODO Carry over Lap 1
			lap2.setPreciseStartTime(ConversionConstants.LAP_BUFFER);
			lap2.setDataStartTime(lap1.getDataStartTime());
			lap2.getStartBufferData().clear();
			double timeToAdd = last.getTime() - lap2.getLapData().get(0).getTime() + 0.001;
			last = writeLap(out, lap2, 2, timeToAdd);
			last.addTime(lap2.getLapTime() - (last.getTime() - lap2.getLapStart().getTime())
					+ ConversionConstants.LAP_BUFFER);
			writeRow(out, lap2, last, last);
			out.write("# Session End\n\n".getBytes());

			// Write Timing file
			lap1.setPrevBest(lap2);
			writeLapTiming(outTiming, lap1, 0, 1, 3);
			
			StringBuffer sectors = new StringBuffer();
			sectors.append(",");
			sectors.append(Integer.toString(1));
			sectors.append(",");
			sectors.append(Integer.toString(3));
			sectors.append(",");
			sectors.append(Integer.toString(3));
			sectors.append(",");
			sectors.append(Integer.toString(2));
			for (int i = 0; i < lap1.getSectors().size() + 1; i++) {
				sectors.append(",0,0");
			}
			sectors.append(",0,0");
			double lap2Start = lap1.getLapStart().getTime() + lap1.getLapTime();
			outTiming.write(String
					.format("%.3f,", getRowSessionTime(lap1, lap2Start))
					.getBytes());
			outTiming.write(String.format("%.3f", lap2Start).getBytes());
			outTiming.write(sectors.toString().getBytes());
			outTiming.write("\n".getBytes());
			
			double lap2Finish = lap2Start + lapTimeSeparator;
			outTiming.write(String
					.format("%.3f,", getRowSessionTime(lap1, lap2Finish - 0.001))
					.getBytes());
			outTiming.write(String.format("%.3f", lap2Finish - 0.001).getBytes());
			outTiming.write(sectors.toString().getBytes());
			outTiming.write("\n".getBytes());

			printLapHeader(outTiming, 2, lapTimeSeparator);
			lap2.setPrevBest(lap1);
			writeLapTiming(outTiming, lap2, 2, 1, 3);
			// TODO Carry over Lap 3

			outTiming.write("# Session End\n\n".getBytes());

			System.out.println(filename + " file written...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeSession(OutputStream out, OutputStream outTiming, Session session) throws IOException {
		int bufferRows = getBufferRows(event.getBest());
		String prologue = getPrologue(event.getBest(), bufferRows);
		out.write(prologue.getBytes());
		outTiming.write(prologue.getBytes());
		writeHeaders(out, event.getBest());
		writeTimingHeaders(outTiming);

		double startSessionTime = getRowSessionTime(session.getLaps().get(0), session.getLaps().get(0).getLapData().get(0).getTime());
		if (startSessionTime > 0) {
			DataRow start = session.getLaps().get(0).getLapData().get(0).clone();
			start.addTime(-1 * startSessionTime);
			writeRow(out, session.getLaps().get(0), start, start);
		}

		DataRow last = null;
		for (int i = 0; i < session.getLaps().size(); i++) {
			last = writeLap(out, session.getLaps().get(i), i);
			writeLapTiming(outTiming, session.getLaps().get(i), i, session.getLaps().get(0).getLapNum(),
					session.getLaps().size());
		}

		out.write("# Session End\n\n".getBytes());
		Lap sessionLast = session.getLaps().get(session.getLaps().size() - 1);
		Lap sessionBest = sessionLast.getPrevBest() != null
				&& sessionLast.getLapTime() >= sessionLast.getPrevBest().getLapTime() ? sessionLast.getPrevBest() : null;
		writeTimingRow(outTiming, session.getLaps().get(0).getLapNum(), session.getLaps().size(),
				sessionLast, sessionBest, sessionLast.getLapStart().getTime() + sessionLast.getLapTime());
		outTiming.write(getFooter(session, last).getBytes());
	}

	private DataRow writeLap(OutputStream out, Lap lap, int sessionLapNumber) throws IOException {
		return writeLap(out, lap, sessionLapNumber, 0);
	}
	
	private DataRow writeLap(OutputStream out, Lap lap, int sessionLapNumber, double timeToAdd) throws IOException {
		int bufferRows = getBufferRows(lap);

		AtomicInteger currentLap = new AtomicInteger(sessionLapNumber);
		AtomicInteger currentSector = new AtomicInteger(0);
		boolean wroteStart = sessionLapNumber > 0;
		if (wroteStart) {
			currentLap.getAndIncrement();
		}
		boolean wroteFinish = false;
		
		for (int i = 0; i < lap.getLapData().size(); i++) {
			DataRow row = lap.getLapData().get(i);
			if (!wroteStart && row.getTime() >= lap.getLapStart().getTime()) {
				printLapHeader(out, currentLap.getAndIncrement(), lap.getPreciseStartTime());
				wroteStart = true;
			}

			if (timeToAdd != 0) {
				row.addTime(timeToAdd);
			}

			writeRow(out, lap, row, i + bufferRows < lap.getLapData().size() ? lap.getLapData().get(i + bufferRows)
					: lap.getLapData().get(i));

			if (!wroteFinish && row.getTime() == lap.getLapFinish().getTime()) {
				// Cones for the current lap are handled with the Cones column
				double lapDisplay = lap.getLapTime();
				if (!lap.getSectors().isEmpty()) {
					printSectorHeader(out, currentSector.incrementAndGet(),
							lap.getLapTime() - lap.getSectors().get(lap.getSectors().size() - 1).getSplit());
				}
				printLapHeader(out, currentLap.get(), lapDisplay);
				wroteFinish = true;
			} else if (currentSector.get() < lap.getSectors().size()
					&& row.getTime() == lap.getSectors().get(currentSector.get()).getDataRow().getTime()) {
				Sector sector = lap.getSectors().get(currentSector.getAndIncrement());
				printSectorHeader(out, currentSector.get(), sector.getSector());
			}
		}
		return lap.getLapData().get(lap.getLapData().size() - 1).clone();
	}

	private void writeLapTiming(OutputStream outTiming, Lap lap, int sessionLapNumber, int sessionLapStart,
			int sessionLaps) throws IOException {
		if (sessionLapNumber == 0) {
			double startSessionTime = getRowSessionTime(lap, lap.getLapData().get(0).getTime());
			outTiming.write("0.000,".getBytes());
			outTiming.write(String.format("%.3f", lap.getLapData().get(0).getTime() - startSessionTime).getBytes());
			outTiming.write(",".getBytes());
			outTiming.write(Integer.toString(sessionLapStart).getBytes());
			outTiming.write(",".getBytes());
			outTiming.write(Integer.toString(sessionLaps).getBytes());
			outTiming.write(",".getBytes());
			outTiming.write(Integer.toString(event.getLaps().size()).getBytes());
			outTiming.write(",".getBytes());
			outTiming.write(Integer.toString(lap.getLapNum()).getBytes());
			for (int i = 0; i < lap.getSectors().size() + 1; i++) {
				outTiming.write(",0".getBytes());
			}
			// TODO: Handle current cones
			outTiming.write(",0".getBytes());
			if (lap.getPrevBest() != null) {
				outTiming.write(",".getBytes());
				outTiming.write(Integer.toString(lap.getPrevBest().getLapNum()).getBytes());
				for (Sector sector : lap.getPrevBest().getSectors()) {
					outTiming.write(String.format(",%.3f", sector.getSplit()).getBytes());
				}
				outTiming.write(String.format(",%.3f", lap.getPrevBest().getLapTime()).getBytes());
			} else {
				outTiming.write(",0".getBytes());
				for (int i = 0; i < lap.getSectors().size(); i++) {
					outTiming.write(",0".getBytes());
				}
				outTiming.write(",0".getBytes());

			}
			// TODO: Handle previous cones
			outTiming.write(",0".getBytes());
			for (Lap lapPenalty : event.getLaps()) {
				if (lapPenalty.getPenalties() > 0) {
					outTiming.write(("," + lapPenalty.getPenalties()).getBytes());
				}
			}
			outTiming.write("\n".getBytes());
			printLapHeader(outTiming, sessionLapNumber, lap.getPreciseStartTime());
		}
		sessionLapNumber++;

		writeTimingRow(outTiming, sessionLapStart, sessionLaps, lap, lap.getPrevBest(), lap.getLapStart().getTime());

		int currentSector = 0;
		for (Sector sector : lap.getSectors()) {
			printSectorHeader(outTiming, ++currentSector, sector.getSector());
		}

		writeTimingRow(outTiming, sessionLapStart, sessionLaps, lap, lap.getPrevBest(),
				lap.getLapStart().getTime() + lap.getLapTime() - 0.001);

		if (!lap.getSectors().isEmpty()) {
			printSectorHeader(outTiming, ++currentSector,
					lap.getLapTime() - lap.getSectors().get(lap.getSectors().size() - 1).getSplit());
		}
		printLapHeader(outTiming, sessionLapNumber, lap.getLapTime());
	}
	
	private void writeTimingRow(OutputStream outTiming, int sessionLapStart, int sessionLaps, Lap current, Lap prevBest, double utcTime) throws IOException {
		double sessionTime = getRowSessionTime(current, utcTime);
		StringBuffer sectors = new StringBuffer();
		sectors.append(",");
		sectors.append(Integer.toString(sessionLapStart));
		sectors.append(",");
		sectors.append(Integer.toString(sessionLaps));
		sectors.append(",");
		sectors.append(Integer.toString(event.getLaps().size()));
		sectors.append(",");
		sectors.append(current.getLapNum());
		for (Sector sector : current.getSectors()) {
			sectors.append(String.format(",%.3f", sector.getSplit()));
		}
		sectors.append(String.format(",%.3f", current.getLapTime()));
		// TODO Handle current penalties
		sectors.append(",0");
		if (prevBest != null) {
			sectors.append(",");
			sectors.append(prevBest.getLapNum());
			for (Sector sector : prevBest.getSectors()) {
				sectors.append(String.format(",%.3f", sector.getSplit()));
			}
			sectors.append(String.format(",%.3f", prevBest.getLapTime()));
		} else {
			sectors.append(",");
			sectors.append(current.getLapNum());
			// Want to maintain the sector timing even though the previous best lap number remains the same
			List<Sector> sectorList = current.getPrevBest() != null ? current.getPrevBest().getSectors()
					: current.getSectors();
			for (Sector sector : sectorList) {
				sectors.append(String.format(",%.3f", sector.getSplit()));
			}
			sectors.append(String.format(",%.3f",
					current.getPrevBest() != null ? current.getPrevBest().getLapTime() : current.getLapTime()));

		}
		// TODO Handle previous penalties
		sectors.append(",0");
		
		outTiming.write(String.format("%.3f,", sessionTime).getBytes());
		outTiming.write(String.format("%.3f", utcTime).getBytes());
		outTiming.write(sectors.toString().getBytes());
		for (Lap lap : event.getLaps()) {
			if (lap.getPenalties() > 0) {
				outTiming.write(("," + lap.getPenalties()).getBytes());
			}
		}
		outTiming.write("\n".getBytes());
	}

	private int getBufferRows(Lap lap) {
		return 0;
//		int bufferRows = -1;
//		DataRow positionRow;
//		do {
//			bufferRows++;
//			positionRow = lap.getLapData().get(bufferRows);
//		} while (positionRow.getTime() - lap.getDataStartTime() < ConversionConstants.POSITION_BUFFER);
//		
//		return bufferRows;
	}

	private String getPrologue(Lap lap, int positionBuffer) {
		StringBuffer prologue = new StringBuffer();
		prologue.append("# RaceRender Data\n");
		prologue.append("# RaceRenderFormatter: https://github.com/dmcfarl/RaceRenderCustomization\n");
		DataRow positionStart = lap.getLapData().get(lap.getLapData().indexOf(lap.getLapStart()) + positionBuffer);
		prologue.append(String.format("# Start Point: %f,%f @ %.2f deg\n", positionStart.getLongitude(),
				positionStart.getLatitude(), positionStart.getBearing()));
		for (int i = 0; i < lap.getSectors().size(); i++) {
			DataRow positionSector = lap.getLapData()
					.get(lap.getLapData().indexOf(lap.getSectors().get(i).getDataRow()) + positionBuffer);
			prologue.append(String.format("# Split Point %d: %f,%f @ %.2f deg\n", i + 1, positionSector.getLongitude(),
					positionSector.getLatitude(), positionSector.getBearing()));
		}
		int positionFinishIndex = lap.getLapData().indexOf(lap.getLapFinish()) + positionBuffer;
		if (positionFinishIndex >= lap.getLapData().size()) {
			positionFinishIndex = lap.getLapData().size() - 1;
		}
		DataRow positionFinish = lap.getLapData().get(positionFinishIndex);
		prologue.append(String.format("# End Point: %f,%f @ %.2f deg\n", positionFinish.getLongitude(),
				positionFinish.getLatitude(), positionFinish.getBearing()));

		return prologue.toString();
	}

	private void writeHeaders(OutputStream out, Lap lap) throws IOException {
		out.write("Time".getBytes());
		for (int i = 0; i < event.getHeaders().size(); i++) {
			String header = event.getHeaders().get(i);
			if (header.equals("Lap #") || header.equals("Session fragment #")) {
				continue;
			}
			out.write(",".getBytes());
			if (header.equals(ConversionConstants.TIME_HEADER)) {
				header = "UTC Time";
			} else if (header.contains(ConversionConstants.CELCIUS_HEADER)) {
				header = header.replace(ConversionConstants.CELCIUS_HEADER, ConversionConstants.FAHRENHEIT_HEADER);
			} else if (header.contains(ConversionConstants.METERS_PER_SECOND_HEADER)) {
				header = header.replace(ConversionConstants.METERS_PER_SECOND_HEADER,
						ConversionConstants.MILES_PER_HOUR_HEADER);
				if (!header.contains("OBD")) {
					header = "GPS " + header;
				}
			} else if (header.contains(ConversionConstants.KILOPASCALS_HEADER)) {
				header = header.replace(ConversionConstants.KILOPASCALS_HEADER,
						ConversionConstants.POUNDS_PER_SQUARE_INCH_HEADER);
			} else if (header.contains(ConversionConstants.METERS_HEADER) && !header.contains("position")) {
				header = header.replace(ConversionConstants.METERS_HEADER, ConversionConstants.MILES_HEADER);
			}
			out.write(header.getBytes());
		}
		out.write((",Boost " + ConversionConstants.POUNDS_PER_SQUARE_INCH_HEADER).getBytes());
		if (!lap.getConeTimes().isEmpty()) {
			out.write(", Cones".getBytes());
		}
		out.write("\n".getBytes());
	}

	private void writeTimingHeaders(OutputStream out) throws IOException {
		out.write("Time,UTC Time,Session Lap Start,Session Laps,Total Laps,Current Lap Number".getBytes());
		for (int i = 0; i < event.getBest().getSectors().size() + 1; i++) {
			out.write((",Current Split " + (i + 1)).getBytes());
		}
		out.write(",Current Penalty".getBytes());
		out.write(",Previous Lap Number".getBytes());
		for (int i = 0; i < event.getBest().getSectors().size() + 1; i++) {
			out.write((",Previous Split " + (i + 1)).getBytes());
		}
		out.write(",Previous Penalty".getBytes());
		String penalties = "";
		for (Lap lap : event.getLaps()) {
			if (lap.getPenalties() > 0) {
				penalties += ",Penalty Lap " + lap.getLapNum();
			}
		}
		out.write(penalties.getBytes());
		out.write("\n".getBytes());
	}

	private String getFooter(Session session, DataRow last) throws IOException {
		return getFooter(session.getLaps().get(0), session.getLaps().get(session.getLaps().size() - 1),
				session.getLaps().get(0).getLapNum(), session.getLaps().size(), last);
	}

	private String getFooter(Lap startSession, Lap endSession, int sessionLapStart, int sessionLaps, DataRow last) throws IOException {
		StringBuffer footer = new StringBuffer();
		AtomicInteger currentLap = new AtomicInteger(sessionLaps + 1);
		if (startSession.getLapNum() > 1) {
			footer.append(getHeader("Lap", currentLap.getAndIncrement(), 0));
			double lapTimes = 0;
			for (int i = 1; i < event.getLaps().size() && i < startSession.getLapNum(); i++) {
				Lap curr = event.getLaps().get(i - 1);
				lapTimes += curr.getLapTime();
				for (int j = 0; j < curr.getSectors().size(); j++) {
					footer.append(getHeader("Sector", j + 1, curr.getSectors().get(j).getSector()));
				}
				if (!curr.getSectors().isEmpty()) {
					footer.append(getHeader("Sector", curr.getSectors().size() + 1,
							curr.getLapTime() - curr.getSectors().get(curr.getSectors().size() - 1).getSplit()));
				}
				footer.append(getHeader("Lap", currentLap.getAndIncrement(), curr.getLapTime()));
			}
			
			if (last != null) {
				last.addTime(lapTimes + 1);
				Lap sessionBest = endSession.getPrevBest() != null
						&& endSession.getLapTime() >= endSession.getPrevBest().getLapTime() ? endSession.getPrevBest()
								: null;
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				writeTimingRow(out, sessionLapStart, sessionLaps, endSession, sessionBest, last.getTime());
				footer.append(out.toString());
			}
		}

		footer.append("# Session End\n\n");

		return footer.toString();
	}

	private void writeRow(OutputStream out, Lap lap, DataRow row, DataRow positionRow) throws IOException {
		StringBuffer data = new StringBuffer();
		double rowLapTime = getRowSessionTime(lap, row.getTime());
		data.append(String.format("%.3f", rowLapTime));

		for (int i = 0; i < event.getHeaders().size(); i++) {
			String header = event.getHeaders().get(i);
			if (header.equals("Lap #") || header.equals("Session fragment #")) {
				continue;
			}
			data.append(",");
			String value = row.getLine()[i];
			if (header.equals(ConversionConstants.TIME_HEADER)) {
				value = String.format("%.3f", Double.parseDouble(value));
			} else {
				if (header.contains(ConversionConstants.CELCIUS_HEADER)) {
					value = DataConverter.celciusToFahrenheit(value);
				} else if (header.contains(ConversionConstants.METERS_PER_SECOND_HEADER)) {
					value = DataConverter.metersPerSecondToMilesPerHour(value);
				} else if (header.contains(ConversionConstants.KILOPASCALS_HEADER)) {
					value = DataConverter.kpaToPsi(value);
				} else if (header.contains(ConversionConstants.METERS_HEADER)) {
					if (header.equals(ConversionConstants.DISTANCE_HEADER)) {
						value = DataConverter.distanceInMetersToMiles(value, lap.getLapStart().getDistance());
					} else if (!header.contains("position")) {
						value = DataConverter.metersToMiles(value);
					}
				} else if (header.equals(ConversionConstants.LAT_HEADER)) {
					value = Double.toString(positionRow.getLatitude());
				} else if (header.equals(ConversionConstants.LON_HEADER)) {
					value = Double.toString(positionRow.getLongitude());
				}
				if (!value.isEmpty() && !header.contains("(deg)")) {
					// Format most fields to only contain 5 decimal places for
					// easier reading.
					// Ignore Latitude and Longitude fields since we need
					// greater precision.
					try {
						DecimalFormat formatter = new DecimalFormat("#.#####");
						value = formatter.format(Double.parseDouble(value));
					} catch (NumberFormatException e) {
						// Not a number
					}
				}
			}

			data.append(value);
		}

		// Add Boost
		String boost = "";
		try {
			int manifoldIndex = event.getHeaders().indexOf(ConversionConstants.MANIFOLD_HEADER);
			int barometricIndex = event.getHeaders().indexOf(ConversionConstants.BAROMETRIC_HEADER);
			if (manifoldIndex >= 0 && barometricIndex >= 0) {
				String manifoldStr = row.getLine()[manifoldIndex];
				String barometricStr = row.getLine()[barometricIndex];
				boost = DataConverter.kpaToPsi(manifoldStr, barometricStr);
				DecimalFormat formatter = new DecimalFormat("#.#####");
				boost = formatter.format(Double.parseDouble(boost));
			}
		} catch (NumberFormatException e) {
			// Could not write boost
		}
		data.append(",");
		data.append(boost);

		// Add Cones
		if (!lap.getConeTimes().isEmpty()) {
			data.append(",");
			int cones;
			for (cones = 0; cones < lap.getConeTimes().size(); cones++) {
				if (rowLapTime < lap.getConeTimes().get(cones) + lap.getPreciseStartTime()) {
					break;
				}
			}
			data.append(Integer.toString(cones));
		}

		data.append("\n");
		out.write(data.toString().getBytes());
	}
	
	private double getRowSessionTime(Lap lap, double utcTime) {
		return utcTime - lap.getDataStartTime();
	}

	private void printLapHeader(OutputStream out, int lap, double time) throws IOException {
		out.write(getHeader("Lap", lap, time).getBytes());
	}

	private void printSectorHeader(OutputStream out, int sector, double time) throws IOException {
		out.write(getHeader("Sector", sector, time).getBytes());
	}

	private String getHeader(String type, int sector, double time) {
		LocalTime sectorTime = LocalTime.MIDNIGHT.plus(Duration.ofMillis((long) Math.round(time * 1000)));
		return String.format("# %s %d: %s\n", type, sector,
				DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(sectorTime));
	}
}
