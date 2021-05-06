package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapListBackground extends AbstractCustomGauge {

private float LapIdx;
private float Header;
private float RowY;
private float NumDisp;
private float Y;
private float LapTime;
private float Buffer;
private float BottomY;
private float SessionLapsIndex;
private float SessionLapStart;
private float SessionLapStartIndex;
private float SessionLaps;
private float CurrentLapNum;
private float Ratio;
private float FutureLapDisplay;
private boolean DrawFutureTime;

public LapListBackground(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

Header = 103;
RowY = 50;
Buffer = 7;

SessionLapsIndex = GetDataIndex("Session Laps");
SessionLapStartIndex = GetDataIndex("Session Lap Start");

FutureLapDisplay = 2; // seconds
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

SessionLapStart = GetDataValue(SessionLapStartIndex);
SessionLaps = GetDataValue(SessionLapsIndex);

if(GetCurLapNum() > SessionLaps) {
	LapIdx = SessionLaps;
	LapTime = GetCurLapTime();
	CurrentLapNum = SessionLaps + SessionLapStart - 1;
} else if(GetCurLapNum() >= 1) {
	LapIdx = GetCurLapNum();
	LapTime = GetCurLapTime();
	CurrentLapNum = GetCurLapNum() + SessionLapStart - 1;
} else {
	LapIdx = 1;
	LapTime = 0;
	CurrentLapNum = SessionLapStart;
}

Y = SizeY - Header - Buffer; // Space between top of chart and the topmost Run time

DrawFutureTime = GetCurLapNum() > 0 && GetCurLapNum() + 1 <= SessionLaps && GetLapTime(LapIdx) - FutureLapDisplay <= LapTime;

NumDisp = trunc(Y / RowY);
if(NumDisp > CurrentLapNum) {
	NumDisp = CurrentLapNum;
	if(DrawFutureTime) {
		NumDisp += 1;
	}
}

// Draw Future Lap
if(DrawFutureTime) {
	Ratio = 1 - (GetLapTime(LapIdx) - LapTime) / FutureLapDisplay;
	Y += RowY - Ratio * RowY;
}

// Draw background
BottomY = Y - NumDisp * RowY;
DrawRect(0, BottomY, SizeX / 10, BottomY + SizeX / 10, ColorF, Filled);
DrawRRect(0, BottomY, SizeX, SizeY, ColorF, Filled);
}

}
