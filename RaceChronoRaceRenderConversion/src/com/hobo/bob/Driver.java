package com.hobo.bob;

import java.io.File;
import java.io.IOException;

import com.hobo.bob.model.Session;
import com.hobo.bob.service.DataExtractor;
import com.hobo.bob.service.DataWriter;

public class Driver {

	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Requires 2 arguments: sessionFile and lapsFile");
			System.exit(1);
		}

		File dataFile = new File(args[0]);
		
		DataExtractor extractor = new DataExtractor(args[0], args[1]);
		try {
			System.out.println("Reading data...");
			Session session = extractor.extract();
			System.out.println("Data extracted...");

			DataWriter writer = new DataWriter(dataFile.getParent() + File.separator, session);
			writer.write();
			System.out.println("Conversion complete.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
