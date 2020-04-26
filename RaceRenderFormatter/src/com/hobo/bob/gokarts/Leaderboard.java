package com.hobo.bob.gokarts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leaderboard {
	private List<Contestant> contestants;
	
	public Leaderboard() {
		contestants = new ArrayList<>();
	}

	public List<Contestant> getContestants() {
		return contestants;
	}

	public void setContestants(List<Contestant> contestants) {
		this.contestants = contestants;
	}
	
	public List<Double> getEvents() {
		Set<Double> eventSet = new HashSet<>();
		for (Contestant contestant : contestants) {
			double sessionTime = 0;
			for (LapTime lap : contestant.getLapTimes()) {
				sessionTime += lap.getLast();
				eventSet.add(sessionTime);
			}
		}
		
		List<Double> events = new ArrayList<>(eventSet);
		Collections.sort(events);
		
		return events;
	}
	
	public List<String> getRowAtEvent(double seconds) {
		List<LapTime> currLaps = new ArrayList<>();
		for (Contestant contestant : contestants) {
			currLaps.add(contestant.getLapTimeAtTime(seconds));
		}
		
		currLaps.sort(Comparator.comparingDouble(LapTime::getBest));
		List<String> row = new ArrayList<>();
		for (LapTime lap : currLaps) {
			row.addAll(lap.getRow(currLaps.get(0)));
		}
		
		return row;
	}
}
