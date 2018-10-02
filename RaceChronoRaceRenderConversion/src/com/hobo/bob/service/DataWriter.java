package com.hobo.bob.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.DataRow;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;
import com.hobo.bob.model.Session;

public class DataWriter {

	private String filepath;
	private Session session;

	public DataWriter(String filepath, Session session) {
		this.filepath = filepath;
		this.session = session;
	}

	public void write() throws IOException {
		try (OutputStream best = new FileOutputStream(filepath + "Best.csv");
				OutputStream ghost = new FileOutputStream(filepath + "Ghost.csv")) {
			writeBest(best);
			System.out.println("Best lap file written...");
			if (session.getGhost() != null) {
				writeLap(ghost, session.getGhost(), "# Session End\n\n");
				System.out.println("Ghost lap file written...");
			}
		}
	}

	private void writeBest(OutputStream out) throws IOException {
		Lap lap = session.getBest();
		StringBuffer footer = new StringBuffer();
		AtomicInteger currentLap = new AtomicInteger(2);

		footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
		for (int i = 1; i < lap.getLapNum(); i++) {
			footer.append(getLapHeader(currentLap.getAndIncrement(), session.getLaps().get(i - 1).getLapDisplay()));
		}
		footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
		for (Sector sector : lap.getSectors()) {
			footer.append(getLapHeader(currentLap.getAndIncrement(), sector.getTime() - lap.getLapStart()));
		}
		footer.append(getLapHeader(currentLap.getAndIncrement(), 0));
		if (session.getGhost() != null) {
			for (Sector sector : session.getGhost().getSectors()) {
				footer.append(getLapHeader(currentLap.getAndIncrement(),
						sector.getTime() - session.getGhost().getLapStart()));
			}
		}

		footer.append("# Session End\n\n");

		writeLap(out, lap, footer.toString());
	}

	private void writeLap(OutputStream out, Lap lap, String footer) throws IOException {
		Deque<Double> bearingBuffer = new LinkedList<>();
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
		out.write((",Boost " + ConversionConstants.POUNDS_PER_SQUARE_INCH_HEADER + "\n").getBytes());

		AtomicInteger currentLap = new AtomicInteger(0);

		boolean wroteStart = false;
		boolean wroteFinish = false;
		for (DataRow row : lap.getLapData()) {
			bearingBuffer.add(row.getBearing());
			if (!wroteStart && row.getTime() == lap.getLapStart()) {
				printLapHeader(out, currentLap.getAndIncrement(), ConversionConstants.LAP_BUFFER);
				wroteStart = true;
			} else if (!wroteFinish && row.getTime() == lap.getLapFinish()) {
				printLapHeader(out, currentLap.getAndIncrement(), lap.getLapDisplay());
				wroteFinish = true;
			}
			writeRow(out, lap, row, bearingBuffer);
		}
		DataRow last = lap.getLapData().get(lap.getLapData().size() - 1).clone();

		double lapTimes = 0;
		for (int i = 1; i < lap.getLapNum(); i++) {
			lapTimes += session.getLaps().get(i - 1).getLapDisplay();
		}
		for (Sector sector : lap.getSectors()) {
			lapTimes += sector.getTime() - lap.getLapStart();
		}
		if (session.getGhost() != null) {
			for (Sector sector : session.getGhost().getSectors()) {
				lapTimes += sector.getTime() - session.getGhost().getLapStart();
			}
		}

		last.addTime(lapTimes + 1);
		writeRow(out, lap, last, bearingBuffer);

		out.write(footer.getBytes());
	}

	private void writeRow(OutputStream out, Lap lap, DataRow row, Deque<Double> bearingBuffer) throws IOException {
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
				} else if (header.equals(ConversionConstants.BEARING_HEADER)) {
					if(row.getTime() - lap.getLapStart() + ConversionConstants.LAP_BUFFER < ConversionConstants.BEARING_BUFFER){
						value = Double.toString(bearingBuffer.peek());
					} else {
						value = Double.toString(bearingBuffer.pop());
					}
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
