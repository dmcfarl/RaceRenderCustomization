package com.hobo.bob.model;

import com.hobo.bob.ConversionConstants;

public class Sector {
	private DataRow dataRow;
	private Double split;
	private Double sector;

	public Sector(DataRow dataRow, Lap lap) {
		this.dataRow = dataRow;
		this.split = dataRow.getTime() - lap.getLapStart().getTime()
				- (lap.getPreciseStartTime() > ConversionConstants.LAP_BUFFER
						? lap.getPreciseStartTime() - ConversionConstants.LAP_BUFFER
						: 0);
		this.sector = !lap.getSectors().isEmpty()
				? this.split - lap.getSectors().get(lap.getSectors().size() - 1).getSplit()
				: this.split;
	}

	public Sector(double split, Lap lap) {
		this.dataRow = null;
		this.split = split;
		this.sector = !lap.getSectors().isEmpty()
				? this.split - lap.getSectors().get(lap.getSectors().size() - 1).getSplit()
				: this.split;
	}

	public DataRow getDataRow() {
		return dataRow;
	}

	public void setDataRow(DataRow row) {
		this.dataRow = row;
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
