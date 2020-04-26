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
	private Double lapDisplay;
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
		this.lapDisplay = null;
	}

	public List<DataRow> getLapOnlyData() {
		return lapData;
	}

	public List<DataRow> getLapData() {
		if (allData == null) {
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

	public void setLapTime(double lapTime) {
		this.lapTime = lapTime;
	}

	public Double getLapDisplay() {
		return lapDisplay;
	}

	public void setLapDisplay(double time, int cones) {
		setLapDisplay(time, cones, Penalty.NONE);
	}

	public void setLapDisplay(double time, int cones, Penalty penalty) {
		if (Penalty.DNF.equals(penalty)) {
			lapDisplay = ConversionConstants.DNF_DISPLAY_TIME;
			lapTime = lapDisplay;
		} else if (Penalty.OFF.equals(penalty)) {
			lapDisplay = ConversionConstants.OFF_DISPLAY_TIME;
			lapTime = lapDisplay;
		} else if (Penalty.RERUN.equals(penalty)) {
			lapDisplay = ConversionConstants.RERUN_DISPLAY_TIME;
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

	public enum Penalty {
		DNF, OFF, RERUN, NONE;
	}
}
