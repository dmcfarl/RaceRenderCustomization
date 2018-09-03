package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
	private Lap best;
	private Lap ghost;
	private List<Lap> laps;
	private List<String> headers;

	public Lap getBest() {
		return best;
	}

	public void setBest(Lap best) {
		this.best = best;
	}

	public Lap getGhost() {
		return ghost;
	}

	public void setGhost(Lap ghost) {
		this.ghost = ghost;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	public void setLaps(List<Lap> laps) {
		this.laps = laps;
	}

	public void addLap(Lap lap) {
		if (this.laps == null) {
			this.laps = new ArrayList<>();
		}
		this.laps.add(lap);

		if (best == null || lap.getLapTime() < best.getLapTime()) {
			if (best != null) {
				ghost = best;
			}
			best = lap;
		}
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
}
