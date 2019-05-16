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
	private final boolean allLaps;

	public RaceChronoReader(String sessionFile, boolean allLaps) {
		this.sessionFile = sessionFile;
		this.allLaps = allLaps;
	}

	public void extract(Session session) throws IOException {
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
				} else if (session.getBest().getPrevBest() != null && session.getBest().getPrevBest().getLapNum() == row.getLap()) {
					readLap(session.getBest().getPrevBest(), sessionReader, dataBuffer, row);

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
}
