package com.hobo.bob.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Session;

public class LapFileReader {

	public Session extractLaps(String lapsFile) throws FileNotFoundException, IOException {
		Session session = new Session();
		int currentLap = 1;
		try (BufferedReader lapReader = new BufferedReader(new FileReader(lapsFile))) {
			Lap lap = new Lap(currentLap);
			String line = lapReader.readLine();
			if (!line.matches("^\\d+") || !parseLapLine(lap, line)) {
				line = lapReader.readLine();
				if (!parseLapLine(lap, line)) {
					throw new IllegalArgumentException("Unable to parse laps file.");
				}
			}
			session.addLap(lap);

			while ((line = lapReader.readLine()) != null) {
				if (!line.isEmpty()) {
					currentLap++;
					lap = new Lap(currentLap);
					if (!parseLapLine(lap, line)) {
						throw new IllegalArgumentException("Unable to parse laps file.");
					}
					session.addLap(lap);
				}
			}
		}

		return session;
	}

	private boolean parseLapLine(Lap lap, String line) {
		boolean parsed = false;
		try {
			if (line == null) {
				throw new IllegalArgumentException("Reached end of laps file before processing a lap.");
			}
			String[] tokens = line.split(",");
			
			// Parse lap time
			if (tokens[0].toLowerCase().contains("dnf")) {
				lap.setLapDisplay(-1, -1, false, true);
			} else if (tokens[0].toLowerCase().contains("oc") || tokens[0].toLowerCase().contains("off")) {
				lap.setLapDisplay(-1, -1, true, false);
			} else if (tokens[0].trim().matches("^\\d+(\\.\\d+)?\\+\\d$")) {
				String[] time = tokens[0].split("\\+");
				lap.setLapDisplay(Double.parseDouble(time[0]), Integer.parseInt(time[1]), false, false);
			} else {
				lap.setLapDisplay(Double.parseDouble(tokens[0]), 0, false, false);
			}
			
			// Parse precise lap start time
			if (tokens.length > 1) {
				lap.setPreciseStartTime(Double.parseDouble(tokens[1]));
			}
			
			// Parse current lap cone times
			if (tokens.length > 2) {
				String[] cones = tokens[2].split("\\|");
				for (String cone : cones) {
					if (!cone.trim().isEmpty()) {
						lap.getConeTimes().add(Double.parseDouble(cone));
					}
				}
			}
			
			parsed = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			parsed = false;
		}

		return parsed;
	}

}
