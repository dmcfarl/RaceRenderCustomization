package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Lap {
	private List<DataRow> lapData;
	private List<Sector> sectors;
	private double lapTime;
	private double lapStart;
	private double lapFinish;
	private int lapNum;

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
}
