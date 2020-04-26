package com.hobo.bob;

import java.io.File;

import com.hobo.bob.model.Event;
import com.hobo.bob.reader.RaceChronoReader;
import com.hobo.bob.reader.LapFileReader;
import com.hobo.bob.writer.DataWriter;
import com.hobo.bob.writer.IntroDisplayWriter;

public class Driver {

	public static void main(String[] args) {
		if(args.length < 1 || args.length > 3){
			System.out.println("Requires 2 arguments with one optional argument: sessionFile [lapsFile] [allLaps]");
			System.exit(1);
		}

		File dataFile = new File(args[0]);
		
		boolean extractAllLaps = (args.length == 3 && Boolean.parseBoolean(args[2])) || args.length == 1;
		try {
			Event event;
			if (args.length >= 2) {
				event = new LapFileReader().extractLaps(args[1]);
			} else {
				event = new Event();
			}
			System.out.println("Reading data...");
			RaceChronoReader extractor = new RaceChronoReader(args[0], event);
			extractor.extract();
			System.out.println("Data extracted...");

			DataWriter writer = new DataWriter(dataFile.getParent() + File.separator, event);
//			if(extractAllLaps) {
//				writer.writeAll();
//			} else {
//				writer.writeBestAndGhost();
//			}
			writer.compareLaps(6, 31);
//			new IntroDisplayWriter(dataFile.getParent() + File.separator).writeDisplayFile();
			System.out.println("Conversion complete.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
