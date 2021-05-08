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
private float PenaltyX;
private int TotalPenalties;
private float PenaltyIndex;

public LapListBackground(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

Header = 103;
RowY = 50;
Buffer = 7;

PenaltyX = SizeX - 120;

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

TotalPenalties = 0;
PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
if(TotalPenalties == 0 && PenaltyIndex >= 0 && PenaltyIndex <= 100) {
	TotalPenalties += GetDataValue(PenaltyIndex);
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 1, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 2, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 3, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 4, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 5, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 6, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 7, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 8, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}
if(TotalPenalties == 0) {
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum - 9, 0));
	if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
		TotalPenalties += GetDataValue(PenaltyIndex);
	}
}

if(TotalPenalties > 0) {
	DrawRRect(0, BottomY, SizeX, SizeY - Header, ColorE, Filled);
	DrawRect(0, BottomY, PenaltyX, BottomY + PenaltyX / 10, ColorF, Filled);
} else {
	DrawRect(0, BottomY, PenaltyX / 10, BottomY + PenaltyX / 10, ColorF, Filled);
}
DrawRRect(0, BottomY, PenaltyX, SizeY, ColorF, Filled);
}

}
