package com.hobo.bob.gokarts;

import java.util.ArrayList;
import java.util.List;

public class LapTime {
	private int contestantEnum;
	private String contestantName;
	private Integer lapNum;
	private Double best;
	private Double last;

	public LapTime() {
		contestantEnum = -1;
		contestantName = null;
		best = Double.MAX_VALUE;
	}
	
	public LapTime(int contestantEnum, int lapNum, double best, double last) {
		this.contestantEnum = contestantEnum;
		this.contestantName = null;
		this.lapNum = lapNum;
		this.best = best;
		this.last = last;
	}
	
	public LapTime(String contestantName, int lapNum, double best, double last) {
		setContestantName(contestantName);
		this.contestantEnum = contestantName.hashCode();
		this.lapNum = lapNum;
		this.best = best;
		this.last = last;
	}

	public int getContestantEnum() {
		return contestantEnum;
	}

	public void setContestantEnum(int contestantEnum) {
		this.contestantEnum = contestantEnum;
	}

	public String getContestantName() {
		return contestantName;
	}

	public void setContestantName(String contestantName) {
		String toConvert = contestantName.substring(0, contestantName.length() > 3 ? 3 : contestantName.length())
				.toUpperCase();
		String converted = "";
		for (int i = 0; i < toConvert.length(); i++) {
			converted += (int)toConvert.charAt(i); 
		}
		
		this.contestantName = converted;
	}

	public Integer getLapNum() {
		return lapNum;
	}

	public void setLapNum(int lapNum) {
		this.lapNum = lapNum;
	}

	public Double getBest() {
		return best;
	}

	public void setBest(double best) {
		this.best = best;
	}

	public Double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}
	
	public List<String> getRow(LapTime leader) {
		List<String> cells = new ArrayList<>();
		if (contestantEnum < 0) {
			cells.add("");
			cells.add("");
			cells.add("");
			cells.add("");
			if (leader != this) {
				cells.add("");
			}
		} else {
			if (contestantName != null) {
				cells.add(contestantName);
			} else {
				cells.add("" + contestantEnum);
			}
			cells.add(String.format("%.3f", best));
			cells.add(String.format("%.3f", last));
			cells.add("" + lapNum);
			if (leader != this) {
				cells.add(String.format("%.3f", best - leader.getBest()));
			}
		}
		
		return cells;
	}
}
