package com.hobo.bob.reader;

import java.io.BufferedReader;
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

public class RaceChronoReader {
	private final String sessionFile;
	private boolean allLaps;

	public RaceChronoReader(String sessionFile, boolean allLaps) {
		this.sessionFile = sessionFile;
		this.allLaps = allLaps;
	}

	public void extract(Session session) throws IOException {
		boolean createLaps = false;
		if (session.getLaps().isEmpty()) {
			allLaps = true;
			createLaps = true;
		}
		
		try (BufferedReader sessionReader = new BufferedReader(new FileReader(sessionFile))) {
			clearUnusedHeaderData(sessionReader);

			Deque<DataRow> dataBuffer = new LinkedList<>();

			session.setHeaders(extractHeaders(sessionReader));

			DataRow.setRowConf(session.getHeaders().indexOf(ConversionConstants.TIME_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LAP_HEADER),
					session.getHeaders().indexOf(ConversionConstants.TRAP_HEADER),
					session.getHeaders().indexOf(ConversionConstants.DISTANCE_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LAT_HEADER),
					session.getHeaders().indexOf(ConversionConstants.LON_HEADER),
					session.getHeaders().indexOf(ConversionConstants.BEARING_HEADER));
			String line;
			while ((allLaps || session.getBest().getLapData() == null) && (line = sessionReader.readLine()) != null) {
				DataRow row = new DataRow(line);
				dataBuffer.add(row);
				while (dataBuffer.peek().getTime() < row.getTime() - ConversionConstants.LAP_BUFFER) {
					dataBuffer.pop();
				}

				if (allLaps && row.getLapNum() > 0) {
					Lap lap;
					if (createLaps) {
						lap = new Lap(row.getLapNum());
					} else {
						lap = session.getLaps().get(row.getLapNum() - 1);
					}
					readLap(lap, sessionReader, dataBuffer, row);
					if (createLaps) {
						session.addLap(lap);
					}
				} else if (session.getBest() != null && session.getBest().getLapNum() == row.getLapNum()) {
					readLap(session.getBest(), sessionReader, dataBuffer, row);
				} else if (session.getBest() != null && session.getBest().getPrevBest() != null
						&& session.getBest().getPrevBest().getLapNum() == row.getLapNum()) {
					readLap(session.getBest().getPrevBest(), sessionReader, dataBuffer, row);

					if (session.getBest().getLapNum() == dataBuffer.peekLast().getLapNum()) {
						Iterator<DataRow> iter = dataBuffer.descendingIterator();
						DataRow bestStart = iter.next();
						while (iter.hasNext() && bestStart != null) {
							if (session.getBest().getLapNum() != bestStart.getLapNum()) {
								break;
							}
							bestStart = iter.next();
						}

						readLap(session.getBest(), sessionReader, dataBuffer, bestStart);
					}
				}
			}
		}
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

	private void readLap(Lap lap, BufferedReader sessionReader, Deque<DataRow> dataBuffer, DataRow lapStart)
			throws IOException {
		lap.setDataStartTime(dataBuffer.peekFirst().getTime());
		
		dataBuffer.removeLast();
		lap.setStartBufferData(dataBuffer);
		dataBuffer.clear();
		dataBuffer.add(lapStart);
		
		String line;
		DataRow row = null;
		if (lap.getPreciseStartTime() > ConversionConstants.LAP_BUFFER && (line = sessionReader.readLine()) != null) {
			do {
				row = new DataRow(line);
				dataBuffer.add(row);
			} while (row.getTime() - lap.getDataStartTime() < lap.getPreciseStartTime() && (line = sessionReader.readLine()) != null);

			if (dataBuffer.peekLast().getLapNum() == null) {
				dataBuffer.peekLast().setLapNum(lapStart.getLapNum());
			}
			lapStart = dataBuffer.peekLast();
		}
		lap.setLapStart(lapStart);

		boolean readSectors = lap.getSectors().isEmpty();
		int currentSector = 0;

		while ((line = sessionReader.readLine()) != null && (row = new DataRow(line)).getLapNum() == lapStart.getLapNum()) {
			dataBuffer.add(row);
			if (readSectors) {
				if(row.getTrap() != null && !row.getTrap().isEmpty()) {
					lap.addSector(new Sector(row, lap));
				}
			} else if (currentSector < lap.getSectors().size()){
				if (row.getTime() > lap.getSectors().get(currentSector).getSplit() + lap.getDataStartTime() + lap.getPreciseStartTime()) {
					lap.getSectors().get(currentSector).setDataRow(row);
					currentSector++;
				}
			}
		}

		lap.setLapFinish(dataBuffer.peekLast());

		if (row != null) {
			if (!lap.getSectors().isEmpty()
					&& row.getTime() < lap.getSectors().get(lap.getSectors().size() - 1).getDataRow().getTime() + 2) {
				lap.getSectors().remove(lap.getSectors().size() - 1);
			}
			dataBuffer.add(row);

			Deque<DataRow> lapCooldown = new LinkedList<>();
			while ((line = sessionReader.readLine()) != null && (row = new DataRow(line))
					.getTime() < lap.getLapFinish().getTime() + ConversionConstants.LAP_BUFFER) {
				lapCooldown.add(row);
			}

			lap.addLapData(dataBuffer);
			lap.setFinishBufferData(lapCooldown);

			dataBuffer.clear();
			dataBuffer.addAll(lapCooldown);
		}
	}
}
