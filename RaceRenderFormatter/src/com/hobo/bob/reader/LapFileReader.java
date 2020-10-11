package com.hobo.bob.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.Event;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;

public class LapFileReader {

	public Event extractLaps(String lapsFile) throws FileNotFoundException, IOException {
		Event event = new Event();
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
			event.addLap(lap);

			while ((line = lapReader.readLine()) != null) {
				if (!line.isEmpty()) {
					currentLap++;
					lap = new Lap(currentLap);
					if (!parseLapLine(lap, line)) {
						throw new IllegalArgumentException("Unable to parse laps file.");
					}
					event.addLap(lap);
				}
			}
		}
		
		double maxTime = -1;
		for (Lap lap : event.getLaps()) {
			if (lap.getLapTime() > maxTime) {
				maxTime = lap.getLapTime();
			}
		}
		
		for (Lap lap : event.getLaps()) {
			if (lap.getLapTime() < 0) {
				lap.setLapTime(maxTime, lap.getPenalties());
			}
		}

		return event;
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
				lap.setLapTime(-1, ConversionConstants.DNF_PENALTY);
			} else if (tokens[0].toLowerCase().contains("oc") || tokens[0].toLowerCase().contains("off")) {
				lap.setLapTime(-1, ConversionConstants.OFF_PENALTY);
			} else if (tokens[0].toLowerCase().contains("rerun")) {
				lap.setLapTime(-1, ConversionConstants.RERUN_PENALTY);
			} else if (tokens[0].trim().matches("^\\d+(\\.\\d+)?\\+\\d$")) {
				String[] time = tokens[0].split("\\+");
				lap.setLapTime(Double.parseDouble(time[0]), Integer.parseInt(time[1]));
			} else {
				lap.setLapTime(Double.parseDouble(tokens[0]), 0);
			}
			
			// Parse precise lap start time
			if (tokens.length > 1) {
				lap.setPreciseStartTime(Double.parseDouble(tokens[1]));
			}
			
			// Parse precise sector times
			if (tokens.length > 2) {
				String[] sectors = tokens[2].split("\\|");
				for (String sector : sectors) {
					if (!sector.trim().isEmpty()) {
						lap.addSector(new Sector(Double.parseDouble(sector), lap));
					}
				}
			}
			
			// Parse current lap cone times
			if (tokens.length > 3) {
				String[] cones = tokens[3].split("\\|");
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
