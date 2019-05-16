package com.hobo.bob;

import java.io.File;
import java.io.IOException;

import com.hobo.bob.model.Session;
import com.hobo.bob.reader.RaceChronoReader;
import com.hobo.bob.reader.LapFileReader;
import com.hobo.bob.writer.DataWriter;

public class Driver {

	public static void main(String[] args) {
		if(args.length < 2 || args.length > 3){
			System.out.println("Requires 2 arguments with one optional argument: sessionFile lapsFile [allLaps]");
			System.exit(1);
		}

		File dataFile = new File(args[0]);
		
		boolean extractAllLaps = args.length == 3 && Boolean.parseBoolean(args[2]);
		RaceChronoReader extractor = new RaceChronoReader(args[0], extractAllLaps);
		try {
			Session session = new LapFileReader().extractLaps(args[1]);
			System.out.println("Reading data...");
			extractor.extract(session);
			System.out.println("Data extracted...");

			DataWriter writer = new DataWriter(dataFile.getParent() + File.separator, session);
			if(extractAllLaps) {
				writer.writeAll();
			} else {
				writer.writeBestAndGhost();
			}
			System.out.println("Conversion complete.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
