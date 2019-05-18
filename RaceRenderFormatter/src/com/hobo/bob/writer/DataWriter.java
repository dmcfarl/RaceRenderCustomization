package com.hobo.bob.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.DataRow;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;
import com.hobo.bob.model.Session;
import com.hobo.bob.util.DataConverter;

public class DataWriter {

	private String filepath;
	private Session session;

	public DataWriter(String filepath, Session session) {
		this.filepath = filepath;
		this.session = session;
	}

	public void writeBestAndGhost() throws IOException {
		try (OutputStream best = new FileOutputStream(filepath + "Best.csv");
				OutputStream ghost = new FileOutputStream(filepath + "Ghost.csv")) {
			writeLap(best, session.getBest());
			System.out.println("Best lap file written...");
			if (session.getBest().getPrevBest() != null) {
				writeLap(ghost, session.getBest().getPrevBest());
				System.out.println("Ghost lap file written...");
			}
		}
	}

	public void writeAll() {
		session.getLaps().parallelStream().forEach(lap -> {
			try (OutputStream out = new FileOutputStream(filepath + "Lap " + lap.getLapNum() + ".csv")) {
				writeLap(out, lap);
				System.out.println("Lap " + lap.getLapNum() + " file written...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void writeLap(OutputStream out, Lap lap) throws IOException {
		String footer = getFooter(lap);
		
		int bufferRows = -1;
		DataRow positionRow;
		do {
			bufferRows++;
			positionRow = lap.getLapData().get(bufferRows);
		} while (positionRow.getTime() - lap.getDataStartTime() < ConversionConstants.POSITION_BUFFER);

		writeHeader(out, lap, bufferRows);

		AtomicInteger currentLap = new AtomicInteger(0);
		AtomicInteger currentSector = new AtomicInteger(0);
		boolean wroteStart = false;
		boolean wroteFinish = false;
		for (int i = 0; i < lap.getLapData().size(); i++) {
			DataRow row = lap.getLapData().get(i);
			if (!wroteStart && row.getTime() >= lap.getLapStart().getTime()) {
				printLapHeader(out, currentLap.getAndIncrement(), lap.getPreciseStartTime());
				wroteStart = true;
			} else if (!wroteFinish && row.getTime() == lap.getLapFinish().getTime()) {
				double lapDisplay = lap.getLapDisplay();
				if (lapDisplay > lap.getLapTime() && lapDisplay < ConversionConstants.OFF_DISPLAY_TIME) {
					// Cones for the current lap are handled with the Cones column
					lapDisplay = lap.getLapTime();
				}
				printSectorHeader(out, currentSector.incrementAndGet(),
						lap.getLapTime() - lap.getSectors().get(lap.getSectors().size() - 1).getSplit());
				printLapHeader(out, currentLap.getAndIncrement(), lapDisplay);
				wroteFinish = true;
			} else if (currentSector.get() < lap.getSectors().size()
					&& row.getTime() == lap.getSectors().get(currentSector.get()).getDataRow().getTime()) {
				Sector sector = lap.getSectors().get(currentSector.getAndIncrement());
				printSectorHeader(out, currentSector.get(), sector.getSector());
			}
			writeRow(out, lap, row, i + bufferRows < lap.getLapData().size() ? lap.getLapData().get(i + bufferRows)
					: lap.getLapData().get(i));
		}
		DataRow last = lap.getLapData().get(lap.getLapData().size() - 1).clone();

		double lapTimes = 0;
		for (int i = 1; i < lap.getLapNum(); i++) {
			lapTimes += session.getLaps().get(i - 1).getLapDisplay();
		}
		for (Sector sector : lap.getSectors()) {
			lapTimes += sector.getSplit();
		}
		if (session.getBest().getPrevBest() != null) {
			for (Sector sector : session.getBest().getPrevBest().getSectors()) {
				lapTimes += sector.getSplit();
			}
		}

		last.addTime(lapTimes + 1);
		writeRow(out, lap, last, last);

		out.write(footer.getBytes());
	}
	
	private void writeHeader(OutputStream out, Lap lap, int positionBuffer) throws IOException {
		out.write("# RaceRender Data\n".getBytes());
		out.write("# RaceRenderFormatter: https://github.com/dmcfarl/RaceRenderCustomization\n".getBytes());
		DataRow positionStart = lap.getLapData().get(lap.getLapData().indexOf(lap.getLapStart()) + positionBuffer);
		out.write(String.format("# Start Point: %f,%f @ %.2f deg\n", positionStart.getLongitude(),
				positionStart.getLatitude(), positionStart.getBearing()).getBytes());
		for (int i = 0; i < lap.getSectors().size(); i++) {
			DataRow positionSector = lap.getLapData()
					.get(lap.getLapData().indexOf(lap.getSectors().get(i).getDataRow()) + positionBuffer);
			out.write(String.format("# Split Point %d: %f,%f @ %.2f deg\n", i + 1, positionSector.getLongitude(),
					positionSector.getLatitude(), positionSector.getBearing()).getBytes());
		}
		DataRow positionFinish = lap.getLapData().get(lap.getLapData().indexOf(lap.getLapFinish()) + positionBuffer);
		out.write(String.format("# End Point: %f,%f @ %.2f deg\n", positionFinish.getLongitude(),
				positionFinish.getLatitude(), positionFinish.getBearing()).getBytes());

		out.write("Time".getBytes());
		for (int i = 0; i < session.getHeaders().size(); i++) {
			String header = session.getHeaders().get(i);
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
	
	private String getFooter(Lap lap) {
		StringBuffer footer = new StringBuffer();
		AtomicInteger currentLap = new AtomicInteger(2);

		for (int i = 1; i < lap.getLapNum(); i++) {
			Lap curr = session.getLaps().get(i - 1);
			for (int j = 0; j < curr.getSectors().size(); j++) {
				footer.append(getHeader("Sector", j + 1, curr.getSectors().get(j).getSector()));
			}
			footer.append(getHeader("Sector", curr.getSectors().size() + 1,
					curr.getLapTime() - curr.getSectors().get(curr.getSectors().size() - 1).getSplit()));
			footer.append(getHeader("Lap", currentLap.getAndIncrement(),
					curr.getLapDisplay()));
		}
		footer.append(getHeader("Lap", currentLap.getAndIncrement(), 0));
		for (Sector sector : lap.getSectors()) {
			footer.append(getHeader("Lap", currentLap.getAndIncrement(), sector.getSplit()));
		}
		footer.append(getHeader("Lap", currentLap.getAndIncrement(), 0));
		if (lap.getLapNum() > 1) {
			Lap prevBest = lap.getPrevBest();

			for (Sector sector : prevBest.getSectors()) {
				footer.append(getHeader("Lap", currentLap.getAndIncrement(), sector.getSplit()));
			}
			footer.append(getHeader("Lap", currentLap.getAndIncrement(), prevBest.getLapDisplay()));
		}

		footer.append("# Session End\n\n");
		
		return footer.toString();
	}

	private void writeRow(OutputStream out, Lap lap, DataRow row, DataRow positionRow) throws IOException {
		double rowLapTime = row.getTime() - lap.getDataStartTime(); 
		out.write(String.format("%.3f", rowLapTime).getBytes());

		for (int i = 0; i < session.getHeaders().size(); i++) {
			String header = session.getHeaders().get(i);
			if (header.equals("Lap #") || header.equals("Session fragment #")) {
				continue;
			}
			out.write(",".getBytes());
			String value = row.getLine()[i];
			if (header.equals(ConversionConstants.TIME_HEADER)) {
				value = String.format("%.0f", Double.parseDouble(value));
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

			out.write(value.getBytes());
		}

		// Add Boost
		String boost = "";
		try {
			int manifoldIndex = session.getHeaders().indexOf(ConversionConstants.MANIFOLD_HEADER);
			int barometricIndex = session.getHeaders().indexOf(ConversionConstants.BAROMETRIC_HEADER);
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
		out.write(",".getBytes());
		out.write(boost.getBytes());

		// Add Cones
		if (!lap.getConeTimes().isEmpty()) {
			out.write(",".getBytes());
			int cones;
			for (cones = 0; cones < lap.getConeTimes().size(); cones++) {
				if (rowLapTime < lap.getConeTimes().get(cones) + lap.getPreciseStartTime()) {
					break;
				}
			}
			out.write(Integer.toString(cones).getBytes());
		}

		out.write("\n".getBytes());
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
