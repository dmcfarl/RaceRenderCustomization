package com.hobo.bob.model;

public class Sector {
	private DataRow dataRow;
	private Double split;
	private Double sector;

	public Sector(DataRow dataRow, Lap lap) {
		this.dataRow = dataRow;
		this.split = dataRow.getTime() - lap.getDataStartTime() - lap.getPreciseStartTime();
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
