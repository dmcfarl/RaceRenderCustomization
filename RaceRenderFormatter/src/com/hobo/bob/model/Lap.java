package com.hobo.bob.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hobo.bob.ConversionConstants;

public class Lap {
	private List<DataRow> startBufferData;
	private List<DataRow> lapData;
	private List<DataRow> finishBufferData;
	private List<Sector> sectors;
	private Double lapTime;
	private Double lapDisplay;
	private DataRow lapStart;
	private DataRow lapFinish;
	private double dataStartTime;
	private int lapNum;
	private double preciseStartTime = ConversionConstants.LAP_BUFFER;
	private List<Double> coneTimes;
	private Lap prevBest;

	public Lap(int lapNum) {
		this.lapNum = lapNum;
		this.prevBest = null;
		this.lapTime = null;
		this.lapDisplay = null;
	}

	public List<DataRow> getLapOnlyData() {
		return lapData;
	}

	public List<DataRow> getLapData() {
		Stream<DataRow> stream = Stream.of();
		stream = Stream.concat(stream, startBufferData.stream());
		stream = Stream.concat(stream, lapData.stream());
		stream = Stream.concat(stream, finishBufferData.stream());

		return stream.collect(Collectors.toList());
	}

	public void setLapData(List<DataRow> lapData) {
		this.lapData = lapData;
	}
	
	public List<DataRow> getStartBufferData() {
		return startBufferData;
	}
	
	public void setStartBufferData(Deque<DataRow> startBufferData) {
		if (this.startBufferData == null) {
			this.startBufferData = new ArrayList<>();
		}
		this.startBufferData.addAll(startBufferData);
	}
	
	public List<DataRow> getFinishBufferData() {
		return finishBufferData;
	}
	
	public void setFinishBufferData(Deque<DataRow> finishBufferData) {
		if (this.finishBufferData == null) {
			this.finishBufferData = new ArrayList<>();
		}
		this.finishBufferData.addAll(finishBufferData);
	}

	public void addLapData(Deque<DataRow> lapData) {
		if (this.lapData == null) {
			this.lapData = new ArrayList<>();
		}
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
			lapDisplay = lapTime;
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

	public void setLapNum(int lapNum) {
		this.lapNum = lapNum;
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
}
