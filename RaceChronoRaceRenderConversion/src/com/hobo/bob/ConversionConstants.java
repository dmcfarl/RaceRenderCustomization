package com.hobo.bob;

public class ConversionConstants {
	public static final double LAP_BUFFER = 15.0;
	public static final String TIME_HEADER = "Time (s)";
	public static final String LAP_HEADER = "Lap #";
	public static final String TRAP_HEADER = "Trap name";
	public static final String MANIFOLD_HEADER = "Manifold pressure (kPa) *OBD";
	public static final String BAROMETRIC_HEADER = "Barometric pressure (kPa) *OBD";
	public static final String DISTANCE_HEADER = "Distance (m)";
	public static final String BEARING_HEADER = "Bearing (deg)";
	public static final double BEARING_BUFFER = 0.4;
	
	public static final double CONE_TIME_PENALTY = 2.0;
	public static final double DNF_DISPLAY_TIME = 3 * 60 * 60;
	public static final double OFF_DISPLAY_TIME = 2 * 60 * 60;
	public static final double CONE_DISPLAY_PENALTY = 10 * 60;
	
	public static final String CELCIUS_HEADER = "(.C)";
	public static final String FAHRENHEIT_HEADER = "(.F)";
	public static final String METERS_PER_SECOND_HEADER = "(m/s)";
	public static final String MILES_PER_HOUR_HEADER = "(mph)";
	public static final String KILOPASCALS_HEADER = "(kPa)";
	public static final String POUNDS_PER_SQUARE_INCH_HEADER = "(psi)";
	public static final String METERS_HEADER = "(m)";
	public static final String MILES_HEADER = "(mi)";
}
