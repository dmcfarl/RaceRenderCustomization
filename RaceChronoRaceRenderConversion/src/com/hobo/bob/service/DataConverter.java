package com.hobo.bob.service;

public class DataConverter {
	public static String metersToMiles(String meters) {
		String result = "";
		if (!meters.isEmpty()) {
			result = Double.toString(Double.parseDouble(meters) * 32 / 51499);
		}
		return result;
	}

	public static String distanceInMetersToMiles(String meters, double lapStartMeters) {
		String result = "";
		if (!meters.isEmpty()) {
			double conversion = (Double.parseDouble(meters) - lapStartMeters) * 32 / 51499;
			result = conversion  < 0 ? "0" : Double.toString(conversion);
		}
		return result;
	}

	public static String metersPerSecondToMilesPerHour(String mps) {
		String result = "";
		if (!mps.isEmpty()) {
			result = Double.toString(Double.parseDouble(mps) * 143 / 64);
		}
		return result;
	}

	public static String kilometersPerHourToMilesPerHour(String kph) {
		String result = "";
		if (!kph.isEmpty()) {
			result = Double.toString(Double.parseDouble(kph) * 5 / 8);
		}
		return result;
	}

	public static String celciusToFahrenheit(String celcius) {
		String result = "";
		if (!celcius.isEmpty()) {
			result = Double.toString(Double.parseDouble(celcius) * 9 / 5 + 32);
		}
		return result;
	}

	public static String kpaToPsi(String kpa) {
		String result = "";
		if (!kpa.isEmpty()) {
			result = Double.toString(Double.parseDouble(kpa) * 4000 / 27579);
		}
		return result;
	}

	public static String kpaToPsi(String kpa1, String kpa2) {
		String result = "";
		if (!kpa1.isEmpty()) {
			result = Double.toString((Double.parseDouble(kpa1) - Double.parseDouble(kpa2)) * 4000 / 27579);
		}
		return result;
	}
}
