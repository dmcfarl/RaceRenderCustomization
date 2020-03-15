package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
	private List<Lap> laps = new ArrayList<>();
	private final int sessionNum;
	
	public Session(int sessionNum) {
		this.sessionNum = sessionNum;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public void addLap(Lap lap) {
		this.laps.add(lap);
	}

	public int getSessionNum() {
		return sessionNum;
	}
}
