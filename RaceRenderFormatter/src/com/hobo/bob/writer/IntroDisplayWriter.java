package com.hobo.bob.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class IntroDisplayWriter {
	
	private static final String CONTENTS = "Time,DisplayTitle,DisplayBody,Transparency\n0,0,0,100\n1,0,0,100\n1.5,1,0,100\n2,1,1,100\n3,1,1,0\n8,1,1,0\n9,1,1,100\n9.5,1,0,100\n10,0,0,100\n120,0,0,100";
	
	private String filepath;

	public IntroDisplayWriter(String filepath) {
		this.filepath = filepath;
	}
	
	public void writeDisplayFile() throws FileNotFoundException, IOException {
		try (OutputStream displayFile = new FileOutputStream(filepath + "Display.csv")) {
			displayFile.write(CONTENTS.getBytes());
			System.out.println("Display file written...");
		}
	}
}
