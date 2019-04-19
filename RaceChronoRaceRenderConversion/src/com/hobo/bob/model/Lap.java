package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.hobo.bob.ConversionConstants;

public class Lap {
	private List<DataRow> lapData;
	private List<Sector> sectors;
	private double lapTime;
	private double lapDisplay;
	private double lapStart;
	private double lapFinish;
	private int lapNum;
	private double startDistance;
	private double finishDistance;
	private double lapStartBuffer = ConversionConstants.LAP_BUFFER;
	private List<Double> coneTimes;

	public Lap(int lapNum) {
		this.lapNum = lapNum;
	}

	public List<DataRow> getLapData() {
		return lapData;
	}

	public void setLapData(List<DataRow> lapData) {
		this.lapData = lapData;
	}

	public void addLapData(Deque<DataRow> lapData) {
		if (this.lapData == null) {
			this.lapData = new ArrayList<>();
		}
		this.lapData.addAll(lapData);
	}

	public List<Sector> getSectors() {
		return sectors;
	}

	public void setSectors(List<Sector> sectors) {
		this.sectors = sectors;
	}

	public void addSector(Sector sector) {
		if (this.sectors == null) {
			this.sectors = new ArrayList<>();
		}
		this.sectors.add(sector);
	}

	public double getLapTime() {
		return lapTime;
	}

	public void setLapTime(double lapTime) {
		this.lapTime = lapTime;
	}

	public double getLapDisplay() {
		return lapDisplay;
	}

	public void setLapDisplay(double time, int cones, boolean offCourse, boolean dnf) {
		if (dnf) {
			lapDisplay = ConversionConstants.DNF_DISPLAY_TIME;
			lapTime = lapDisplay;
		} else if (offCourse) {
			lapDisplay = ConversionConstants.OFF_DISPLAY_TIME;
			lapTime = lapDisplay;
		} else if (cones > 9) {
			throw new IllegalArgumentException("Unable to process runs with more than 9 hit cones.");
		} else if (cones > 0) {
			lapTime = time;
			lapDisplay = time + ConversionConstants.CONE_DISPLAY_PENALTY * cones;
		} else {
			lapDisplay = time;
			lapTime = lapDisplay;
		}
	}

	public double getLapStart() {
		return lapStart;
	}

	public void setLapStart(double lapStart) {
		this.lapStart = lapStart;
	}

	public double getLapFinish() {
		return lapFinish;
	}

	public void setLapFinish(double lapFinish) {
		this.lapFinish = lapFinish;
	}

	public int getLapNum() {
		return lapNum;
	}

	public void setLapNum(int lapNum) {
		this.lapNum = lapNum;
	}

	public double getStartDistance() {
		return startDistance;
	}

	public void setStartDistance(double startDistance) {
		this.startDistance = startDistance;
	}

	public double getFinishDistance() {
		return finishDistance;
	}

	public void setFinishDistance(double finishDistance) {
		this.finishDistance = finishDistance;
	}

	public double getLapStartBuffer() {
		return lapStartBuffer;
	}

	public void setLapStartBuffer(double lapStartBuffer) {
		this.lapStartBuffer = lapStartBuffer;
	}

	public List<Double> getConeTimes() {
		if (coneTimes == null) {
			coneTimes = new ArrayList<>();
		}
		return coneTimes;
	}

	public void setConeTimes(List<Double> coneTimes) {
		this.coneTimes = coneTimes;
	}
}
