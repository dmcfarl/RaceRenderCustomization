package com.hobo.bob.gokarts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GoKartDriver {
	private Leaderboard leaderboard;
	private double raceBuffer = 30;

	public static void main(String[] args) {
		GoKartDriver driver = new GoKartDriver();

		try {
			driver.read();

			driver.write();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Conversion completed.");
	}

	public Leaderboard read() throws EncryptedDocumentException, IOException {
		File home = new File(".");
		File[] files = home.listFiles();
		Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

		for (int i = 0; i < files.length; i++) {
			if (!files[i].getName().startsWith("LeaderboardData")) {
				String[] tokens = files[i].getName().split("[.]");
				if (tokens.length > 0) {
					String extension = tokens[tokens.length - 1];
					if ("xlsx".equalsIgnoreCase(extension) || "xls".equalsIgnoreCase(extension)) {
						return readExcel(files[i]);
					} else if ("csv".equalsIgnoreCase(extension)) {
						return readCsv(files[i]);
					}
				}
			}
		}

		return null;
	}

	public Leaderboard readExcel(File file) throws EncryptedDocumentException, IOException {
		leaderboard = new Leaderboard();
		try (Workbook workbook = WorkbookFactory.create(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			int rowSize = sheet.getLastRowNum();
			int i = sheet.getFirstRowNum();
			if (i < rowSize) {
				// Read header row
				Row header = sheet.getRow(i);
				int columnSize = header.getLastCellNum();
				for (int j = 0; j <= columnSize; j++) {
					Cell cell = header.getCell(j);
					if (cell != null && cell.getStringCellValue() != null && !cell.getStringCellValue().isBlank()) {
						leaderboard.getContestants().add(new Contestant(cell.getStringCellValue()));
					}
				}
				
				// Read lap0Time
				i++;
				if (i <= rowSize) {
					Row row = sheet.getRow(i);
					for (int j = 0; j <= columnSize; j++) {
						Cell cell = row.getCell(j);
						if (cell != null) {
							double lapTime = cell.getNumericCellValue();
							if (lapTime > 0) {
								leaderboard.getContestants().get(j).setLap0Time(lapTime);
							}
						}
					}
				}

				// Read lapTimes
				for (i++; i <= rowSize; i++) {
					Row row = sheet.getRow(i);
					for (int j = 0; j <= columnSize; j++) {
						Cell cell = row.getCell(j);
						if (cell != null) {
							double lapTime = cell.getNumericCellValue();
							if (lapTime > 0) {
								leaderboard.getContestants().get(j).addLapTime(lapTime);
							}
						}
					}
				}
			}
		}

		return leaderboard;
	}

	public Leaderboard readCsv(File file) throws FileNotFoundException, IOException {
		leaderboard = new Leaderboard();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String header = in.readLine();
			if (header != null) {
				String[] headerCells = header.split(",");
				for (int i = 0; i < headerCells.length; i++) {
					if (!headerCells[i].isBlank()) {
						leaderboard.getContestants().add(new Contestant(headerCells[i]));
					}
				}

				// Read lap0Times
				String row = in.readLine();
				if (row != null) {
					String[] cells = row.split(",");
					for (int i = 0; i < cells.length; i++) {
						try {
							if (cells[i].length() > 0) {
								double lapTime = Double.parseDouble(cells[i]);
								leaderboard.getContestants().get(i).setLap0Time(lapTime);
							}
						} catch (NumberFormatException e) {
							// Ignore
						}
					}
					
					// Read lapTimes
					while ((row = in.readLine()) != null) {
						cells = row.split(",");
						for (int i = 0; i < cells.length; i++) {
							try {
								if (cells[i].length() > 0) {
									double lapTime = Double.parseDouble(cells[i]);
									leaderboard.getContestants().get(i).addLapTime(lapTime);
								}
							} catch (NumberFormatException e) {
								// Ignore
							}
						}
					}
				}
			}
		}

		return leaderboard;
	}

	public void write() {
		String filename = "LeaderboardData"
				+ DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.systemDefault()).format(Instant.now())
				+ ".csv";
		try (FileWriter out = new FileWriter(filename)) {
			out.write("Time,Position 1 Name,Position 1 Best,Position 1 Last,Position 1 Laps");
			for (int i = 1; i < leaderboard.getContestants().size(); i++) {
				String pos = "Position " + (i + 1);
				out.write("," + pos + " Name," + pos + " Best," + pos + " Last," + pos + " Laps," + pos + " Gap");
			}
			out.write("\n");
			List<Double> events = leaderboard.getEvents();

			out.write(formatRow(0, leaderboard.getRowAtEvent(0)));
			out.write(formatRow(raceBuffer, leaderboard.getRowAtEvent(0)));

			for (Double event : events) {
				out.write(formatRow(raceBuffer + event - 0.0001, leaderboard.getRowAtEvent(event - 0.0001)));
				out.write(formatRow(raceBuffer + event, leaderboard.getRowAtEvent(event)));
			}

			out.write(formatRow(2 * raceBuffer + events.get(events.size() - 1),
					leaderboard.getRowAtEvent(events.get(events.size() - 1))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String formatRow(double sessionTime, List<String> toFormat) {
		StringBuffer row = new StringBuffer();
		row.append(String.format("%.4f,", sessionTime));
		for (int i = 0; i < toFormat.size(); i++) {
			row.append(toFormat.get(i));
			if (i < toFormat.size() - 1) {
				row.append(",");
			}
		}
		row.append("\n");

		return row.toString();
	}
}
