package com.hobo.bob.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.hobo.bob.ConversionConstants;
import com.hobo.bob.model.DataRow;
import com.hobo.bob.model.Event;
import com.hobo.bob.model.Lap;
import com.hobo.bob.model.Sector;
import com.hobo.bob.model.Session;

public class RaceChronoReader {
	private final String sessionFile;
	private final Event event;

	public RaceChronoReader(String sessionFile, Event event) {
		this.sessionFile = sessionFile;
		this.event = event;
	}

	public void extract() throws IOException {
		boolean createLaps = false;
		if (event.getLaps().isEmpty()) {
			createLaps = true;
		}

		try (BufferedReader sessionReader = new BufferedReader(new FileReader(sessionFile))) {
			clearUnusedHeaderData(sessionReader);

			Deque<DataRow> dataBuffer = new LinkedList<>();

			event.setHeaders(extractHeaders(sessionReader));

			DataRow.setRowConf(event.getHeaders().indexOf(ConversionConstants.TIME_HEADER),
					event.getHeaders().indexOf(ConversionConstants.LAP_HEADER),
					event.getHeaders().indexOf(ConversionConstants.TRAP_HEADER),
					event.getHeaders().indexOf(ConversionConstants.DISTANCE_HEADER),
					event.getHeaders().indexOf(ConversionConstants.LAT_HEADER),
					event.getHeaders().indexOf(ConversionConstants.LON_HEADER),
					event.getHeaders().indexOf(ConversionConstants.BEARING_HEADER));
			ListIterator<Lap> laps = event.getLaps().listIterator();
			Lap lap;
			while (findNextLap(sessionReader, dataBuffer)) {
				if (createLaps) {
					lap = new Lap(dataBuffer.peekLast().getLapNum());
				} else {
					lap = laps.next();
				}

				Session session = event.getSessions().get(event.getSessions().size() - 1);

				readLap(lap, sessionReader, dataBuffer);

				if (session.getLaps().isEmpty()) {
					lap.setDataStartTime(lap.getLapStart().getTime() - lap.getPreciseStartTime());
				} else {
					lap.setDataStartTime(session.getLaps().get(0).getDataStartTime());
					lap.setPreciseStartTime(0);
				}
				session.addLap(lap);

				if (createLaps) {
					// Add the lap to the event after reading since we need to know the lap time
					// which won't be known until we read the lap
					event.addLap(lap);
				}
			}
		}
	}

	private boolean findNextLap(BufferedReader sessionReader, Deque<DataRow> dataBuffer) throws IOException {
		if (!dataBuffer.isEmpty() && dataBuffer.peekLast().getLapNum() > 0) {
			return true;
		}

		DataRow row = null;
		while ((row = readDataRow(sessionReader)) != null) {
			dataBuffer.add(row);
			while (dataBuffer.peek().getTime() < row.getTime() - ConversionConstants.LAP_BUFFER) {
				dataBuffer.pop();
			}
			if (row.getLapNum() > 0) {
				// Between Sessions, so need to add a new session
				event.getSessions().add(new Session(event.getSessions().size() + 1));
				return true;
			}
		}

		return false;
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

	private void readLap(Lap lap, BufferedReader sessionReader, Deque<DataRow> dataBuffer) throws IOException {
		DataRow lapStart = dataBuffer.removeLast();
		lap.addStartBufferData(dataBuffer);
		dataBuffer.clear();
		dataBuffer.add(lapStart);

		DataRow row = null;
		if (lap.getPreciseStartTime() > ConversionConstants.LAP_BUFFER && (row = readDataRow(sessionReader)) != null) {
			do {
				dataBuffer.add(row);
			} while (row.getTime() - lap.getLapStart().getTime() < lap.getPreciseStartTime()
					&& (row = readDataRow(sessionReader)) != null);

			if (dataBuffer.peekLast().getLapNum() == null) {
				dataBuffer.peekLast().setLapNum(lapStart.getLapNum());
			}
			lapStart = dataBuffer.peekLast();
		}
		lap.setLapStart(lapStart);

		boolean readSectors = lap.getSectors().isEmpty();
		int currentSector = 0;

		while ((row = readDataRow(sessionReader)) != null && row.getLapNum() == lapStart.getLapNum()) {
			dataBuffer.add(row);
			if (readSectors) {
				if (row.getTrap() != null && !row.getTrap().isEmpty() && !row.getTrap().contains("Finish")) {
					lap.addSector(new Sector(row, lap));
				}
			} else if (currentSector < lap.getSectors().size()) {
				if (row.getTime() > lap.getSectors().get(currentSector).getSplit() + lap.getLapStart().getTime()
						+ lap.getPreciseStartTime()) {
					lap.getSectors().get(currentSector).setDataRow(row);
					currentSector++;
				}
			}
		}

		if (lap.getLapDisplay() == null && row != null && row.getLapNum() - 1 == lapStart.getLapNum()) {
			lap.setLapDisplay(row.getTime() - lap.getLapStart().getTime(), 0);
		}

		lap.setLapFinish(dataBuffer.peekLast());
		lap.addLapData(dataBuffer);
		dataBuffer.clear();

		if (row != null) {
			dataBuffer.add(row);
			if (row.getLapNum() == -1
					&& row.getTime() - lap.getLapFinish().getTime() < ConversionConstants.LAP_BUFFER) {
				while ((row = readDataRow(sessionReader)) != null && row.getLapNum() == -1
						&& row.getTime() < lap.getLapFinish().getTime() + ConversionConstants.LAP_BUFFER) {
					dataBuffer.add(row);
				}
				lap.setFinishBufferData(dataBuffer);
			}
		}
	}

	private DataRow readDataRow(BufferedReader sessionReader) throws IOException {
		String line = sessionReader.readLine();
		return line != null ? new DataRow(line) : null;
	}
}
