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
			writeBest(best);
			System.out.println("Best lap file written...");
			if (session.getBest().getPrevBest() != null) {
				writeLap(ghost, session.getBest().getPrevBest(), "# Session End\n\n");
				System.out.println("Ghost lap file written...");
			}
		}
	}
	
	public void writeAll() {
		session.getLaps().parallelStream().forEach(lap -> {
			try (OutputStream out = new FileOutputStream(filepath + "Lap " + lap.getLapNum() + ".csv")) {
				StringBuffer footer = new StringBuffer();
				AtomicInteger currentLap = new AtomicInteger(2);

				for (int i = 1; i < lap.getLapNum(); i++) {
					footer.append(getLapHeader(currentLap.getAndIncrement(), session.getLaps().get(i - 1).getLapDisplay()));
				}
				footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
				for (Sector sector : lap.getSectors()) {
					footer.append(getLapHeader(currentLap.getAndIncrement(), sector.getTime() - lap.getLapStart()
							- (lap.getLapStartBuffer() - ConversionConstants.LAP_BUFFER)));
				}
				footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
				if (lap.getLapNum() > 1) {
					Lap prevBest = lap.getPrevBest();
					
					for (Sector sector : prevBest.getSectors()) {
						footer.append(
								getLapHeader(currentLap.getAndIncrement(), sector.getTime() - prevBest.getLapStart()
										- (prevBest.getLapStartBuffer() - ConversionConstants.LAP_BUFFER)));
					}
					footer.append(getLapHeader(currentLap.getAndIncrement(), prevBest.getLapDisplay()));
				}

				footer.append("# Session End\n\n");

				writeLap(out, lap, footer.toString());
				System.out.println("Lap " + lap.getLapNum() + " file written...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void writeBest(OutputStream out) throws IOException {
		Lap lap = session.getBest();
		StringBuffer footer = new StringBuffer();
		AtomicInteger currentLap = new AtomicInteger(2);

		for (int i = 1; i < lap.getLapNum(); i++) {
			footer.append(getLapHeader(currentLap.getAndIncrement(), session.getLaps().get(i - 1).getLapDisplay()));
		}
		footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
		for (Sector sector : lap.getSectors()) {
			footer.append(getLapHeader(currentLap.getAndIncrement(),
					sector.getTime() - lap.getLapStart() - (lap.getLapStartBuffer() - ConversionConstants.LAP_BUFFER)));
		}
		footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
		if (session.getBest().getPrevBest() != null) {
			for (Sector sector : session.getBest().getPrevBest().getSectors()) {
				footer.append(
						getLapHeader(currentLap.getAndIncrement(), sector.getTime() - session.getBest().getPrevBest().getLapStart()
								- (session.getBest().getPrevBest().getLapStartBuffer() - ConversionConstants.LAP_BUFFER)));
			}
		}

		footer.append("# Session End\n\n");

		writeLap(out, lap, footer.toString());
	}

	private void writeLap(OutputStream out, Lap lap, String footer) throws IOException {
		int bufferRows = -1;
		DataRow positionRow;
		do {
			bufferRows++;
			positionRow = lap.getLapData().get(bufferRows);
		} while (positionRow.getTime() - lap.getLapStart()
				+ ConversionConstants.LAP_BUFFER < ConversionConstants.POSITION_BUFFER);

		out.write("# RaceRender Data\n".getBytes());
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
		if(!lap.getConeTimes().isEmpty()) {
			out.write(", Cones".getBytes());
		}
		out.write("\n".getBytes());

		AtomicInteger currentLap = new AtomicInteger(0);
		boolean wroteStart = false;
		boolean wroteFinish = false;
		for (int i = 0; i < lap.getLapData().size(); i++) {
			DataRow row = lap.getLapData().get(i);
			if (!wroteStart
					&& row.getTime() - lap.getLapStart() + ConversionConstants.LAP_BUFFER >= lap.getLapStartBuffer()) {
				printLapHeader(out, currentLap.getAndIncrement(), lap.getLapStartBuffer());
				wroteStart = true;
			} else if (!wroteFinish && row.getTime() == lap.getLapFinish()) {
				double lapDisplay = lap.getLapDisplay();
				if (lapDisplay > lap.getLapTime() && lapDisplay < ConversionConstants.OFF_DISPLAY_TIME) {
					// Cones for the current lap are handled with the Cones column
					lapDisplay = lap.getLapTime();
				}
				printLapHeader(out, currentLap.getAndIncrement(), lapDisplay);
				wroteFinish = true;
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
			lapTimes += sector.getTime() - lap.getLapStart();
		}
		if (session.getBest().getPrevBest() != null) {
			for (Sector sector : session.getBest().getPrevBest().getSectors()) {
				lapTimes += sector.getTime() - session.getBest().getPrevBest().getLapStart();
			}
		}

		last.addTime(lapTimes + 1);
		writeRow(out, lap, last, last);

		out.write(footer.getBytes());
	}

	private void writeRow(OutputStream out, Lap lap, DataRow row, DataRow positionRow) throws IOException {
		out.write(String.format("%.3f", row.getTime() - lap.getLapStart() + ConversionConstants.LAP_BUFFER).getBytes());

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
						value = DataConverter.distanceInMetersToMiles(value, lap.getStartDistance());
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
				double currentTime = row.getTime() - lap.getLapStart() + ConversionConstants.LAP_BUFFER;
				double coneTime = lap.getConeTimes().get(cones)
						+ lap.getLapStartBuffer();
				if (currentTime < coneTime) {
					break;
				}
			}
			out.write(Integer.toString(cones).getBytes());
		}

		out.write("\n".getBytes());
	}

	private void printLapHeader(OutputStream out, int lap, double time) throws IOException {
		out.write(getLapHeader(lap, time).getBytes());
	}

	private String getLapHeader(int lap, double time) throws IOException {
		LocalTime lapTime = LocalTime.MIDNIGHT.plus(Duration.ofMillis((long) (time * 1000)));
		return String.format("# Lap %d: %s\n", lap, DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(lapTime));
	}
}
