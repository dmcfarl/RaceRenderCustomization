package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
	private Lap best;
	private List<Lap> laps;
	private List<String> headers;

	public Lap getBest() {
		return best;
	}

	public void setBest(Lap best) {
		this.best = best;
	}

	public List<Lap> getLaps() {
		if (this.laps == null) {
			this.laps = new ArrayList<>();
		}
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
		lap.setPrevBest(getBest());

		if (best == null || lap.getLapDisplay() < best.getLapDisplay()) {
			setBest(lap);
		}
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
}
