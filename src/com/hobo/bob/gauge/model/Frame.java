package com.hobo.bob.gauge.model;

import java.util.List;
import java.util.Map;

public class Frame {
	private int lapNum;
	private float lapTime;
	private float[] laps;
	private Map<String, Integer> dataIndices;
	private List<Object> dataValues;
	
	public Frame(int lapNum, float lapTime, float[] laps, Map<String, Integer> dataIndices, List<Object> dataValues) {
		this.lapNum = lapNum;
		this.lapTime = lapTime;
		this.laps = laps;
		this.dataIndices = dataIndices;
		this.dataValues = dataValues;
	}
	
	public int getCurrentLapNum() {
		return lapNum;
	}
	
	public float getCurrentLapTime() {
		return lapTime;
	}
	
	public float getLapTime(int lap) {
		return laps[lap];
	}
	
	public int getDataIndex(String field) {
		return dataIndices.get(field);
	}
	
	public Object getDataValue(int index) {
		return dataValues.get(index);
	}
}
