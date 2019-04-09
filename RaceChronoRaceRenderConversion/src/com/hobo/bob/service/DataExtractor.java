package com.hobo.bob.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.DataRow;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;
import com.hobo.bob.model.Session;

public class DataExtractor {
	private String sessionFile;
	private String lapsFile;
	private boolean allLaps;

	public DataExtractor(String sessionFile, String lapsFile) {
		this.sessionFile = sessionFile;
		this.lapsFile = lapsFile;
		this.allLaps = false;
	}

	public Session extract() throws IOException {
		Session session = extractLaps(lapsFile);

		try (BufferedReader sessionReader = new BufferedReader(new FileReader(sessionFile))) {
			clearUnusedHeaderData(sessionReader);

			Deque<DataRow> dataBuffer = new LinkedList<>();

			session.setHeaders(extractHeaders(sessionReader));

			DataRow.setRowConf(session.getHeaders().indexOf(ConversionConstants.TIME_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LAP_HEADER),
					session.getHeaders().indexOf(ConversionConstants.TRAP_HEADER),
					session.getHeaders().indexOf(ConversionConstants.DISTANCE_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LAT_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LON_HEADER));
			String line;
			while ((session.getBest().getLapData() == null || allLaps) && (line = sessionReader.readLine()) != null) {
				DataRow row = new DataRow(line);
				dataBuffer.add(row);
				while (dataBuffer.peek().getTime() < row.getTime() - ConversionConstants.LAP_BUFFER) {
					dataBuffer.pop();
				}

				if (session.getBest().getLapNum() == row.getLap()) {
					readLap(session.getBest(), sessionReader, dataBuffer, row);
				} else if (session.getGhost() != null && session.getGhost().getLapNum() == row.getLap()) {
					readLap(session.getGhost(), sessionReader, dataBuffer, row);

					if (session.getBest().getLapNum() == dataBuffer.peekLast().getLap()) {
						Iterator<DataRow> iter = dataBuffer.descendingIterator();
						DataRow bestStart = iter.next();
						while (iter.hasNext() && bestStart != null) {
							if (session.getBest().getLapNum() != bestStart.getLap()) {
								break;
							}
							bestStart = iter.next();
						}

						readLap(session.getBest(), sessionReader, dataBuffer, bestStart);
					}
				} else if (allLaps && row.getLap() > 0) {
					readLap(session.getLaps().get(row.getLap() - 1), sessionReader, dataBuffer, row);
				}
			}
		}

		return session;
	}

	private void clearUnusedHeaderData(BufferedReader sessionReader) throws IOException {
		String line = "a";
		while (!line.isEmpty() && !line.startsWith(",")) {
			line = sessionReader.readLine();
		}
	}

	private List<String> extractHeaders(BufferedReader sessionReader) throws IOException {
		String line;
		if ((line = sessionReader.readLine()).isEmpty()) {
			line = sessionReader.readLine();
		}

		return Arrays.asList(line.split(",", -1));
	}

	private Session extractLaps(String lapsFile) throws FileNotFoundException, IOException {
		Session session = new Session();
		int currentLap = 1;
		try (BufferedReader lapReader = new BufferedReader(new FileReader(lapsFile))) {
			Lap lap = new Lap(currentLap);
			String line = lapReader.readLine();
			if (!parseLapLine(lap, line)) {
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
			
			// Parse start buffer
			if (tokens.length > 1) {
				lap.setLapStartBuffer(Double.parseDouble(tokens[1]));
			}
			
			parsed = true;
		} catch (NumberFormatException e) {
			parsed = false;
		}

		return parsed;
	}

	private void readLap(Lap lap, BufferedReader sessionReader, Deque<DataRow> dataBuffer, DataRow lapStart)
			throws IOException {
		lap.setLapStart(lapStart.getTime());
		lap.setStartDistance(lapStart.getDistance());

		String line;
		DataRow row = null;
		while ((line = sessionReader.readLine()) != null && (row = new DataRow(line)).getLap() == lapStart.getLap()) {
			dataBuffer.add(row);
			if (row.getTrap() != null && !row.getTrap().isEmpty()) {
				lap.addSector(new Sector(row.getTime()));
			}
		}

		lap.setLapFinish(dataBuffer.peekLast().getTime());
		lap.setFinishDistance(dataBuffer.peekLast().getDistance());

		if (row != null) {
			if (!lap.getSectors().isEmpty()
					&& row.getTime() < lap.getSectors().get(lap.getSectors().size() - 1).getTime() + 2) {
				lap.getSectors().remove(lap.getSectors().size() - 1);
			}
			dataBuffer.add(row);

			Deque<DataRow> lapCooldown = new LinkedList<>();
			while ((line = sessionReader.readLine()) != null
					&& (row = new DataRow(line)).getTime() < lap.getLapFinish() + ConversionConstants.LAP_BUFFER) {
				lapCooldown.add(row);
			}

			lap.addLapData(dataBuffer);
			lap.addLapData(lapCooldown);

			dataBuffer.clear();
			dataBuffer.addAll(lapCooldown);
		}
	}

	public void setExtractAllLaps(boolean allLaps) {
		this.allLaps = allLaps;
	}

	public boolean getExtractAllLaps() {
		return allLaps;
	}
}
