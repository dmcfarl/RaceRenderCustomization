package com.hobo.bob.model;

import com.hobo.bob.ConversionConstants;

public class Sector {
	private Double time;
	private Double split;
	private Double sector;

	public Sector(Double time, Lap lap) {
		this.time = time;
		this.split = time - lap.getLapStart() - (lap.getLapStartBuffer() - ConversionConstants.LAP_BUFFER);
		this.sector = !lap.getSectors().isEmpty()
				? this.split - lap.getSectors().get(lap.getSectors().size() - 1).getSplit()
				: this.split;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Double getSplit() {
		return split;
	}

	public void setSplit(Double split) {
		this.split = split;
	}

	public Double getSector() {
		return sector;
	}

	public void setSector(Double sector) {
		this.sector = sector;
	}
}
