package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapListBackgroundLapTimesOnly extends AbstractCustomGauge {

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
private float TotalLaps;

public LapListBackgroundLapTimesOnly(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

//Number of seconds before the next lap to
//start shifting laps down to make room to
//display the next lap
FutureLapDisplay = 2;

Header = 103; // pixels
RowY = 50; // pixels
Buffer = 7; // pixels

// Determine Total Number of Laps
TotalLaps = 0;
CurrentLapNum = 1;
// Lap 1
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 2
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 3
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 4
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 5
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 6
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 7
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 8
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 9
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 10
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 11
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 12
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 13
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 14
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 15
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 16
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 17
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 18
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 19
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 20
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 21
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 22
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 23
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 24
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 25
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 26
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 27
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 28
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 29
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 30
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 31
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 32
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 33
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 34
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 35
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 36
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 37
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 38
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 39
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 40
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 41
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 42
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 43
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 44
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 45
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 46
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 47
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 48
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 49
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 50
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 51
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 52
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 53
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 54
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 55
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 56
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 57
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 58
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 59
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 60
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 61
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 62
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 63
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 64
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 65
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 66
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 67
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 68
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 69
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 70
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 71
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 72
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 73
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 74
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 75
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 76
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 77
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 78
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 79
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 80
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 81
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 82
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 83
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 84
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 85
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 86
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 87
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 88
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 89
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 90
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 91
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 92
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 93
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 94
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 95
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 96
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 97
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 98
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 99
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
//Lap 100
if(GetLapTime(CurrentLapNum) > 1) {
	CurrentLapNum += 1;
}
CurrentLapNum -= 1;
SessionLapStart = 1;
SessionLaps = CurrentLapNum;
TotalLaps = CurrentLapNum;

PenaltyX = SizeX - 120;
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

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

DrawRect(0, BottomY, PenaltyX / 10, BottomY + PenaltyX / 10, ColorF, Filled);
DrawRRect(0, BottomY, PenaltyX, SizeY - 14, ColorF, Filled);
}

}
