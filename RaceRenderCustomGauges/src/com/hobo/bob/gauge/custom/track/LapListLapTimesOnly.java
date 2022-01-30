package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapListLapTimesOnly extends AbstractCustomGauge {

private float LapIdx;
private float Header;
private float RowY;
private float NumDisp;
private float X;
private float Y;
private float FontSize;
private float TimeX;
private float LapTime;
private String RunColor;
private float Buffer;
private String BackColor;
private float PrevBestLapNum;
private float TotalLaps;
private float CurrentLapNum;
private float Ratio;
private float FutureLapDisplay;
private float PreviousLapDisplay;
private float PreviousLapTransition;
private boolean DrawFutureTime;
private boolean DrawCurrentTime;
private int DrawMinutes;
private float PenaltyX;
private int SessionLapStart;
private float SessionLaps;
private float BottomY;
private boolean DrawBackground;

public LapListLapTimesOnly(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

// Number of seconds before the next lap to
//  start shifting laps down to make room to
//  display the next lap
FutureLapDisplay = 2;

// Number of seconds to show the colored
//  background indicating a faster or slower lap
//  including the transition time
PreviousLapDisplay = 4;

// Number of seconds to change from blank number 
//  background to colored background indicating 
//  faster or slower lap
PreviousLapTransition = 1 / 2; 
DrawBackground = false;

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

X = 13;
FontSize = 42;
PenaltyX = SizeX - 120;
TimeX = PenaltyX - 15;

DrawCircle(PenaltyX * 9 / 10, SizeY - (PenaltyX / 10) - 1, PenaltyX / 10, ColorE, Filled);
DrawRect(0, SizeY - Header, PenaltyX * 9 / 10, SizeY, ColorE, Filled);
DrawRect(0, SizeY - Header, PenaltyX, SizeY - (PenaltyX / 10), ColorE, Filled);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

PrevBestLapNum = GetCurBestLapNum();
if(GetCurLapNum() > SessionLaps) {
	LapIdx = SessionLaps;
	LapTime = GetCurLapTime();
	CurrentLapNum = SessionLaps + SessionLapStart - 1;
	PrevBestLapNum = GetCurBestLapNum();
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

DrawCurrentTime = GetCurLapNum() <= SessionLaps;
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

// Background drawn by other Display Object
if(DrawBackground) {
	if(NumDisp > CurrentLapNum) {
		BottomY = Y - NumDisp * RowY;
	} else {
		BottomY = SizeY - Header - Buffer - NumDisp * RowY;
	}
	DrawRect(0, BottomY, PenaltyX / 10, BottomY + PenaltyX / 10, ColorF, Filled);
	DrawRRect(0, BottomY, PenaltyX, SizeY - Header + FontSize + Buffer, ColorF, Filled);
}

if(DrawFutureTime) {
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum + 1, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(GetCurLapTime() >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	DrawTime(0, 3, TimeX - 3, Y, ColorC, FontSize, AlignH_Right, 2 - DrawMinutes);
	Y -= RowY;
}

// Draw Banner
DrawRect(0, SizeY - Header, PenaltyX, SizeY - Header + FontSize + Buffer, ColorE, Filled);
DrawLine(PenaltyX / 4, SizeY - Header + FontSize + Buffer, PenaltyX * 3 / 4, SizeY - Header + FontSize + Buffer, ColorG, 2);
DrawNumber(CurrentLapNum, 0, (PenaltyX / 2) - 18, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Right);
DrawText("/", PenaltyX / 2, SizeY - Header + FontSize + 2, ColorG, FontSize, AlignH_Center);
if(TotalLaps >= 100) {
	DrawText(FormatNumber(TotalLaps, 0) + "+", (PenaltyX / 2) + 18, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Left);
} else {
	DrawNumber(TotalLaps, 0, (PenaltyX / 2) + 18, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Left);
}

if(DrawCurrentTime) {
	// Draw Current Lap
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	RunColor = ColorH;

	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}

//NumRuns - 1
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}

	// Highlight Run Number Box
	if(GetCurLapNum() > 1 && GetCurLapTime() < PreviousLapDisplay) {
		if(PrevBestLapNum == LapIdx) {
			BackColor = ColorD;
		} else {
			BackColor = ColorB;
		}
		if(LapTime < PreviousLapTransition) {
			BackColor = BlendColorsRGB(ColorG, BackColor, LapTime / PreviousLapTransition);
		} else if(LapTime > PreviousLapDisplay - PreviousLapTransition) {
			BackColor = BlendColors(BackColor, ColorG, (LapTime - PreviousLapDisplay + PreviousLapTransition) / PreviousLapTransition);
		}
	} else {
		BackColor = ColorG;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, BackColor, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, BackColor, Filled);
	DrawCircle(X + 58, Y - 33, 7, BackColor, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}

//NumRuns - 2
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 3
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 4
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 5
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 6
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 7
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 8
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 9
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	// If no NumRuns-10, Draw Best or Last?
	if(floor(Y - RowY) <= 0 && PrevBestLapNum < CurrentLapNum) {
		LapIdx -= (CurrentLapNum - PrevBestLapNum);
		CurrentLapNum = PrevBestLapNum;
	}
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);

	// Only draw if above 0
	if(Y >= 32) {
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	} else {
		DrawRect(X, 0, X + 65, Y + 1, ColorG, Filled);
	}
	if(Y >= 40) {
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	} else if(Y - 32 > 0) {
		DrawRect(X, 0, X + 57, Y - 32, ColorG, Filled);
	}
	if(Y > 26) {
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	}
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 10
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 11
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 12
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 13
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 14
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 15
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 16
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 17
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 18
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 19
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	// If no NumRuns-10, Draw Best or Last?
	if(floor(Y - RowY) <= 0 && PrevBestLapNum < CurrentLapNum) {
		LapIdx -= (CurrentLapNum - PrevBestLapNum);
		CurrentLapNum = PrevBestLapNum;
	}
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);

	// Only draw if above 0
	if(Y >= 32) {
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	} else {
		DrawRect(X, 0, X + 65, Y + 1, ColorG, Filled);
	}
	if(Y >= 40) {
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	} else if(Y - 32 > 0) {
		DrawRect(X, 0, X + 57, Y - 32, ColorG, Filled);
	}
	if(Y > 26) {
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	}
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 20: Only used after last lap
if(floor(Y) - RowY > 0 && CurrentLapNum > 0) {
	// If no NumRuns-10, Draw Best or Last?
	if(floor(Y - RowY) <= 0 && PrevBestLapNum < CurrentLapNum) {
		LapIdx -= (CurrentLapNum - PrevBestLapNum);
		CurrentLapNum = PrevBestLapNum;
	}
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);

	// Only draw if above 0
	if(Y >= 32) {
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	} else {
		DrawRect(X, 0, X + 65, Y + 1, ColorG, Filled);
	}
	if(Y >= 40) {
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	} else if(Y - 32 > 0) {
		DrawRect(X, 0, X + 57, Y - 32, ColorG, Filled);
	}
	if(Y > 26) {
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	}
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//Last Lap
if(floor(Y) > 0 && CurrentLapNum > 0) {
	// Draw Best or Last?
	if(PrevBestLapNum < CurrentLapNum) {
		LapIdx -= (CurrentLapNum - PrevBestLapNum);
		CurrentLapNum = PrevBestLapNum;
	}
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}
	LapTime = GetLapTime(LapIdx);

	// Only draw if above 0
	if(Y >= 32) {
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	} else {
		DrawRect(X, 0, X + 65, Y + 1, ColorG, Filled);
	}
	if(Y >= 40) {
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	} else if(Y - 32 > 0) {
		DrawRect(X, 0, X + 57, Y - 32, ColorG, Filled);
	}
	if(Y > 26) {
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	}
	DrawNumber(CurrentLapNum, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	if(LapTime >= 60) {
		DrawMinutes = 1;
	} else {
		DrawMinutes = 0;
	}
	if(CurrentLapNum == PrevBestLapNum) {
		RunColor = ColorD;
	} else {
		RunColor = ColorC;
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
}

}
