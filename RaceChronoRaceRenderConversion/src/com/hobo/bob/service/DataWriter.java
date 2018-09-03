package com.hobo.bob.service;

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
			if(session.getGhost() != null){
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
			footer.append(getLapHeader(currentLap.getAndIncrement(), session.getLaps().get(i - 1).getLapTime()));
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
		out.write("# RaceRender Data\n".getBytes());
		out.write("Time".getBytes());
		for (int i = 0; i < session.getHeaders().size(); i++) {
			if (session.getHeaders().get(i).equals("Lap #")
					|| session.getHeaders().get(i).equals("Session fragment #")) {
				continue;
			}
			out.write(",".getBytes());
			String header = session.getHeaders().get(i);
			if (header.equals(ConversionConstants.TIME_HEADER)) {
				header = "UTC Time";
			} else if (header.contains("(.C)")) {
				header = header.replace("(.C)", "(.F)");
			} else if (header.contains("(m/s)")) {
				header = header.replace("(m/s)", "(mph)");
				if (!header.contains("OBD")) {
					header = "Satellite " + header;
				}
			} else if (header.contains("(kPa)")) {
				header = header.replace("(kPa)", "(psi)");
			} else if (header.contains("(m)")) {
				header = header.replace("(m)", "(mi)");
			}
			out.write(header.getBytes());
		}
		out.write(",Boost (psi)".getBytes());
		out.write("\n".getBytes());

		AtomicInteger currentLap = new AtomicInteger(0);

		boolean wroteStart = false;
		boolean wroteFinish = false;
		for (DataRow row : lap.getLapData()) {
			if (!wroteStart && row.getTime() == lap.getLapStart()) {
				printLapHeader(out, currentLap.getAndIncrement(), ConversionConstants.LAP_BUFFER);
				wroteStart = true;
			} else if (!wroteFinish && row.getTime() == lap.getLapFinish()) {
				printLapHeader(out, currentLap.getAndIncrement(), lap.getLapTime());
				wroteFinish = true;
			}
			writeRow(out, lap, row);
		}
		DataRow last = lap.getLapData().get(lap.getLapData().size() - 1).clone();

		double lapTimes = 0;
		for (int i = 1; i < lap.getLapNum(); i++) {
			lapTimes += session.getLaps().get(i - 1).getLapTime();
		}
		for (Sector sector : lap.getSectors()) {
			lapTimes += sector.getTime() - lap.getLapStart();
		}
		if(session.getGhost() != null){
			for (Sector sector : session.getGhost().getSectors()) {
				lapTimes += sector.getTime() - session.getGhost().getLapStart();
			}
		}

		last.addTime(lapTimes + 1);
		writeRow(out, lap, last);

		out.write(footer.getBytes());
	}

	private void writeRow(OutputStream out, Lap lap, DataRow row) throws IOException {
		out.write(String.format("%.3f", row.getTime() - lap.getLapStart() + ConversionConstants.LAP_BUFFER).getBytes());

		for (int i = 0; i < session.getHeaders().size(); i++) {
			if (session.getHeaders().get(i).equals("Lap #")
					|| session.getHeaders().get(i).equals("Session fragment #")) {
				continue;
			}
			out.write(",".getBytes());
			String value = row.getLine()[i];
			if (session.getHeaders().get(i).equals(ConversionConstants.TIME_HEADER)) {
				value = String.format("%.0f", Double.parseDouble(value));
			} else {
				if (session.getHeaders().get(i).contains("(.C)")) {
					value = DataConverter.celciusToFahrenheit(value);
				} else if (session.getHeaders().get(i).contains("(m/s)")) {
					value = DataConverter.metersPerSecondToMilesPerHour(value);
				} else if (session.getHeaders().get(i).contains("(kpa)")) {
					value = DataConverter.kpaToPsi(value);
				} else if (session.getHeaders().get(i).contains("(m)")) {
					value = DataConverter.metersToMiles(value);
				}
				if (!value.isEmpty() && !session.getHeaders().get(i).contains("(deg)")) {
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
