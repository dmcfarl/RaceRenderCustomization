package com.hobo.bob.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DataRow {
	private String[] line;
	private ObjectNode data;

	private Double time;
	private Integer lap;
	private String trap;

	private static int timeIndex = -1;
	private static int lapIndex = -1;
	private static int trapIndex = -1;

	private DataRow(DataRow clone) {
		this.line = clone.getLine();
		this.time = clone.getTime();
		this.lap = clone.getLap();
		this.trap = clone.getTrap();
	}

	public DataRow(String line) {
		this.line = line.split(",", -1);
	}

	public String[] getLine() {
		return line;
	}

	public void setLine(String[] line) {
		this.line = line;
	}

	public ObjectNode getData() {
		return data;
	}

	public void setData(ObjectNode data) {
		this.data = data;
	}

	public Double getTime() {
		if (this.time == null) {
			String time = line[timeIndex];

			this.time = !time.isEmpty() ? Double.parseDouble(time) : Double.MAX_VALUE;
		}

		return this.time;
	}

	public void addTime(double time) {
		if(this.time == null){
			getTime();
		}
		this.time += time;
		line[timeIndex] = Double.toString(this.time);
	}

	public Integer getLap() {
		if (this.lap == null) {
			String lap = line[lapIndex];

			this.lap = !lap.isEmpty() ? Integer.parseInt(lap) : -1;
		}

		return this.lap;
	}

	public String getTrap() {
		if (this.trap == null) {
			this.trap = line[trapIndex];
		}

		return this.trap;
	}
	
	public DataRow clone(){
		return new DataRow(this);
	}

	public static void setRowConf(int time, int lap, int trap) {
		timeIndex = time;
		lapIndex = lap;
		trapIndex = trap;
	}
}
