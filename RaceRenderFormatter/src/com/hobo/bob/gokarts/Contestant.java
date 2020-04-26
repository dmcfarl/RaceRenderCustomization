package com.hobo.bob.gokarts;

import java.util.ArrayList;
import java.util.List;

public class Contestant {
	private List<LapTime> lapTimes;
	private int contestantEnum;
	private String name;
	private double lap0Time;
	
	public Contestant(int contestantEnum) {
		this.contestantEnum = contestantEnum;
		lapTimes = new ArrayList<>();
	}
	
	public Contestant(String name) {
		this.name = name;
		lapTimes = new ArrayList<>();
	}
	
	public Contestant(int contestantEnum, List<Double> lapTimes) {
		this.contestantEnum = contestantEnum;
		setLapTimes(lapTimes);
	}

	public List<LapTime> getLapTimes() {
		return lapTimes;
	}

	public void setLapTimes(List<Double> lapTimes) {
		this.lapTimes = new ArrayList<>();
		double best = Double.MAX_VALUE;
		for (int i = 0; i < lapTimes.size(); i++) {
			if (lapTimes.get(i) < best) {
				best = lapTimes.get(i);
			}
			if (name != null) {
				this.lapTimes.add(new LapTime(name, i + 1, best, lapTimes.get(i)));
			} else {
				this.lapTimes.add(new LapTime(contestantEnum, i + 1, best, lapTimes.get(i)));
			}
		}
	}
	
	public void addLapTime(double newLap) {
		double best = newLap;
		if (!lapTimes.isEmpty()) {
			LapTime last = lapTimes.get(lapTimes.size() - 1);
			if (last.getBest() < best) {
				best = last.getBest();
			}
		}
		if (name != null) {
			lapTimes.add(new LapTime(name, lapTimes.size() + 1, best, newLap));
		} else {
			lapTimes.add(new LapTime(contestantEnum, lapTimes.size() + 1, best, newLap));
		}
	}

	public int getContestantEnum() {
		return contestantEnum;
	}

	public void setContestantEnum(int contestantEnum) {
		this.contestantEnum = contestantEnum;
	}
	
	public LapTime getLapTimeAtTime(double seconds) {
		LapTime result = new LapTime();
		double runTime = lap0Time;
		for (int i = 0; i < lapTimes.size() && lapTimes.get(i).getLast() + runTime <= seconds; i++) {
			result = lapTimes.get(i);
			runTime += lapTimes.get(i).getLast();
		}
		
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLap0Time() {
		return lap0Time;
	}

	public void setLap0Time(double lap0Time) {
		this.lap0Time = lap0Time;
	}
}
