package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private Lap best;
	private List<Session> sessions = new ArrayList<>();
	private List<String> headers = new ArrayList<>();
	private List<Lap> laps = new ArrayList<>();

	public Lap getBest() {
		return best;
	}

	public void setBest(Lap best) {
		this.best = best;
	}
	
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public void addLap(Lap lap) {
		if (this.laps == null) {
			this.laps = new ArrayList<>();
		}
		this.laps.add(lap);
		lap.setPrevBest(getBest());

		if (best == null || lap.getTotalLapTime() < best.getTotalLapTime()) {
			setBest(lap);
		}
	}

}
