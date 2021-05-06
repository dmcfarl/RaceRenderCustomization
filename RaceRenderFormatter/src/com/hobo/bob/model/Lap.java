package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hobo.bob.ConversionConstants;

public class Lap {
	private List<DataRow> allData = null;
	private final List<DataRow> startBufferData = new ArrayList<>();
	private final List<DataRow> lapData = new ArrayList<>();
	private final List<DataRow> finishBufferData = new ArrayList<>();
	private List<Sector> sectors;
	private Double lapTime;
	private int penalties = 0;
	private DataRow lapStart;
	private DataRow lapFinish;
	private double dataStartTime = 0;
	private final int lapNum;
	private double preciseStartTime = ConversionConstants.LAP_BUFFER;
	private List<Double> coneTimes;
	private Lap prevBest;

	public Lap(int lapNum) {
		this.lapNum = lapNum;
		this.prevBest = null;
		this.lapTime = null;
	}

	public List<DataRow> getLapOnlyData() {
		return lapData;
	}

	public List<DataRow> getLapData() {
		if (allData == null || allData.size() < startBufferData.size() + lapData.size() + finishBufferData.size()) {
			Stream<DataRow> stream = Stream.of();
			stream = Stream.concat(stream, startBufferData.stream());
			stream = Stream.concat(stream, lapData.stream());
			stream = Stream.concat(stream, finishBufferData.stream());

			allData = stream.collect(Collectors.toList());
		}
		return allData;
	}

	public List<DataRow> getStartBufferData() {
		return startBufferData;
	}

	public void addStartBufferData(Deque<DataRow> startBufferData) {
		this.startBufferData.addAll(startBufferData);
	}

	public List<DataRow> getFinishBufferData() {
		return finishBufferData;
	}

	public void setFinishBufferData(Deque<DataRow> finishBufferData) {
		this.finishBufferData.addAll(finishBufferData);
	}

	public void addLapData(Deque<DataRow> lapData) {
		this.lapData.addAll(lapData);
	}

	public List<Sector> getSectors() {
		if (this.sectors == null) {
			this.sectors = new ArrayList<>();
		}
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

	public Double getLapTime() {
		return lapTime;
	}
	
	public double getTotalLapTime() {
		return lapTime == null || lapTime < 0 ? 99999 : lapTime + penalties * ConversionConstants.CONE_TIME_PENALTY;
	}

	public void setLapTime(double lapTime) {
		setLapTime(lapTime, 0);
	}

	public void setLapTime(double time, int cones) {
		this.penalties = cones;
		this.lapTime = time;
	}

	public DataRow getLapStart() {
		return lapStart;
	}

	public void setLapStart(DataRow lapStart) {
		this.lapStart = lapStart;
	}

	public DataRow getLapFinish() {
		return lapFinish;
	}

	public void setLapFinish(DataRow lapFinish) {
		this.lapFinish = lapFinish;
		if (lapTime == null) {
			lapTime = lapFinish.getTime() - lapStart.getTime();
		}
	}

	public double getDataStartTime() {
		return dataStartTime;
	}

	public void setDataStartTime(double dataStartTime) {
		this.dataStartTime = dataStartTime;
	}

	public int getLapNum() {
		return lapNum;
	}

	public double getPreciseStartTime() {
		return preciseStartTime;
	}

	public void setPreciseStartTime(double preciseStartTime) {
		this.preciseStartTime = preciseStartTime;
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

	public Lap getPrevBest() {
		return prevBest;
	}

	public void setPrevBest(Lap prevBest) {
		this.prevBest = prevBest;
	}

	public int getPenalties() {
		return penalties;
	}
}
