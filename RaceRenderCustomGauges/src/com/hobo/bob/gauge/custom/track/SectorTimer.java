package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class SectorTimer extends AbstractCustomGauge {

private float SplitDisplayLength;
private float PenaltyTime;
private float NumSplits;
private float LastPrevSplit;
private float CurrSplit1;
private float CurrSplit2;
private float CurrSplit3;
private float CurrSplit4;
private float CurrSplit5;
private float CurrSplit6;
private float PrevSplit1;
private float PrevSplit2;
private float PrevSplit3;
private float PrevSplit4;
private float PrevSplit5;
private float PrevSplit6;
private String RunColor;
private String PositiveSplitColor;
private String NegativeSplitColor;
private String HeaderColor;
private float FontSize;
private float HeaderY;
private float SectorY;
private float SectorX;
private float Sector;
private float X;
private float Y;
private float CurrSplit;
private float PrevSplit;
private float NextPrevSplit;
private float RunTime;
private float DiffSplit;
private String DiffText;
private String DiffColor;
private float PrevTime;
private float Compact;
private float CurrentSplit1Idx;
private float CurrentSplit2Idx;
private float CurrentSplit3Idx;
private float CurrentSplit4Idx;
private float CurrentSplit5Idx;
private float CurrentSplit6Idx;
private float PreviousSplit1Idx;
private float PreviousSplit2Idx;
private float PreviousSplit3Idx;
private float PreviousSplit4Idx;
private float PreviousSplit5Idx;
private float PreviousSplit6Idx;
private float CurrentLapNumIdx;
private float CurrentPenaltyIdx;
private float PreviousPenaltyIdx;
private float SessionLapsIndex;
private Float SessionLaps;
private float PreviousSplitLastIdx;
private int CompletedSectors;
private float NextCurrSplit;
private float SectorProgress;
private float ProgressX;

public SectorTimer(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
//Length of time in seconds to display the Current Split Time
SplitDisplayLength = 3;
PenaltyTime = 2;

//Gather Split Information
NumSplits = 0;

SessionLapsIndex = GetDataIndex("Session Laps");

CurrentSplit1Idx = GetDataIndex("Current Split 1");
CurrentSplit2Idx = GetDataIndex("Current Split 2");
CurrentSplit3Idx = GetDataIndex("Current Split 3");
CurrentSplit4Idx = GetDataIndex("Current Split 4");
CurrentSplit5Idx = GetDataIndex("Current Split 5");
CurrentSplit6Idx = GetDataIndex("Current Split 6");
PreviousSplit1Idx = GetDataIndex("Previous Split 1");
PreviousSplit2Idx = GetDataIndex("Previous Split 2");
PreviousSplit3Idx = GetDataIndex("Previous Split 3");
PreviousSplit4Idx = GetDataIndex("Previous Split 4");
PreviousSplit5Idx = GetDataIndex("Previous Split 5");
PreviousSplit6Idx = GetDataIndex("Previous Split 6");

CurrentLapNumIdx = GetDataIndex("Current Lap Number");
CurrentPenaltyIdx = GetDataIndex("Current Penalty");
PreviousPenaltyIdx = GetDataIndex("Previous Penalty");

if(CurrentSplit1Idx >= 0 && CurrentSplit1Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit1Idx;
}
if(CurrentSplit2Idx >= 0 && CurrentSplit2Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit2Idx;
}
if(CurrentSplit3Idx >= 0 && CurrentSplit3Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit3Idx;
}
if(CurrentSplit4Idx >= 0 && CurrentSplit4Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit4Idx;
}
if(CurrentSplit5Idx >= 0 && CurrentSplit5Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit5Idx;
}
if(CurrentSplit6Idx >= 0 && CurrentSplit6Idx < 50) {
	NumSplits += 1;
	PreviousSplitLastIdx = PreviousSplit6Idx;
}

//Colors
RunColor = ColorA;
PositiveSplitColor = ColorC;
NegativeSplitColor = ColorD;
HeaderColor = ColorE;

//Parameters
FontSize = 100;

//Spacing
//OffsetX = 6;
//OffsetY = 6;
HeaderY = SizeY - 100;
SectorY = 30;

//Draw Sectors
SectorX = SizeX / (NumSplits);

//Draw Header
DrawRect(0, HeaderY, SizeX, SizeY, HeaderColor, Filled);

//Draw Car Line
DrawLineFlat(140, HeaderY + 15, 140, SizeY - 20, ColorB, 6);

DrawRect(17, SizeY - 69, 115, SizeY - 22, White, Filled);
DrawRect(17, HeaderY + 15, 100, SizeY - 69, White, Filled);
DrawCircle(100, SizeY - 69, 15, White, Filled);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);
DrawNumber(GetDataValue(CurrentLapNumIdx), 0, 67, SizeY - 25, Black, 62, AlignH_Center);

SessionLaps = GetDataValue(SessionLapsIndex);

X = SizeX / 2 - 45;
Y = HeaderY - 45;

//Current Run

CurrSplit = 0;
PrevSplit = 0;
if(NumSplits > 0) {
	CurrSplit1 = GetDataValue(CurrentSplit1Idx);
	PrevSplit1 = GetDataValue(PreviousSplit1Idx);
} else {
	CurrSplit1 = 0;
	PrevSplit2 = 0;
}
if(NumSplits > 1) {
	CurrSplit2 = GetDataValue(CurrentSplit2Idx);
	PrevSplit2 = GetDataValue(PreviousSplit2Idx);
} else {
	CurrSplit2 = 0;
	PrevSplit2 = 0;
}
if(NumSplits > 2) {
	CurrSplit3 = GetDataValue(CurrentSplit3Idx);
	PrevSplit3 = GetDataValue(PreviousSplit3Idx);
} else {
	CurrSplit3 = 0;
	PrevSplit3 = 0;
}
if(NumSplits > 3) {
	CurrSplit4 = GetDataValue(CurrentSplit4Idx);
	PrevSplit4 = GetDataValue(PreviousSplit4Idx);
} else {
	CurrSplit4 = 0;
	PrevSplit4 = 0;
}
if(NumSplits > 4) {
	CurrSplit5 = GetDataValue(CurrentSplit5Idx);
	PrevSplit5 = GetDataValue(PreviousSplit5Idx);
} else {
	CurrSplit5 = 0;
	PrevSplit5 = 0;
}
if(NumSplits > 5) {
	CurrSplit6 = GetDataValue(CurrentSplit6Idx);
	PrevSplit6 = GetDataValue(PreviousSplit6Idx);
} else {
	CurrSplit6 = 0;
	PrevSplit6 = 0;
}
LastPrevSplit = GetDataValue(PreviousSplitLastIdx);

NextCurrSplit = 0;
NextPrevSplit = 0;
RunTime = GetCurLapTime();
// Determine Current Split Times
if(GetCurLapNum() > SessionLaps) {
	CurrSplit = GetLapTime(SessionLaps);
	PrevSplit = LastPrevSplit;
	NextPrevSplit = 99999;
} else if(RunTime >= CurrSplit6 && CurrSplit6 > 0) {
	CurrSplit = CurrSplit6;
	PrevSplit = PrevSplit6;
	NextCurrSplit = GetLapTime(GetCurLapNum());
	NextPrevSplit = LastPrevSplit;
} else if(RunTime >= CurrSplit5 && CurrSplit5 > 0) {
	CurrSplit = CurrSplit5;
	PrevSplit = PrevSplit5;
	if(CurrSplit6 == 0) {
		NextCurrSplit = GetLapTime(GetCurLapNum());
		NextPrevSplit = LastPrevSplit;
	} else {
		NextCurrSplit = CurrSplit6;
		NextPrevSplit = PrevSplit6;
	}
} else if(RunTime >= CurrSplit4 && CurrSplit4 > 0) {
	CurrSplit = CurrSplit4;
	PrevSplit = PrevSplit4;
	if(CurrSplit5 == 0) {
		NextCurrSplit = GetLapTime(GetCurLapNum());
		NextPrevSplit = LastPrevSplit;
	} else {
		NextCurrSplit = CurrSplit5;
		NextPrevSplit = PrevSplit5;
	}
} else if(RunTime >= CurrSplit3 && CurrSplit3 > 0) {
	CurrSplit = CurrSplit3;
	PrevSplit = PrevSplit3;
	if(CurrSplit4 == 0) {
		NextCurrSplit = GetLapTime(GetCurLapNum());
		NextPrevSplit = LastPrevSplit;
	} else {
		NextCurrSplit = CurrSplit4;
		NextPrevSplit = PrevSplit4;
	}
} else if(RunTime >= CurrSplit2 && CurrSplit2 > 0) {
	CurrSplit = CurrSplit2;
	PrevSplit = PrevSplit2;
	if(CurrSplit3 == 0) {
		NextCurrSplit = GetLapTime(GetCurLapNum());
		NextPrevSplit = LastPrevSplit;
	} else {
		NextCurrSplit = CurrSplit3;
		NextPrevSplit = PrevSplit3;
	}
} else if(RunTime >= CurrSplit1 && CurrSplit1 > 0) {
	CurrSplit = CurrSplit1;
	PrevSplit = PrevSplit1;
	if(CurrSplit2 == 0) {
		NextCurrSplit = GetLapTime(GetCurLapNum());
		NextPrevSplit = LastPrevSplit;
	} else {
		NextCurrSplit = CurrSplit2;
		NextPrevSplit = PrevSplit2;
	}
} else {
	NextCurrSplit = CurrSplit1;
	NextPrevSplit = PrevSplit1;
}
DiffSplit = CurrSplit - PrevSplit;
if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
	DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
}
if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
	DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
}

//Current Run or Difference between Splits
if(CurrSplit != 0 && (CurrSplit + SplitDisplayLength > RunTime || GetCurLapNum() > SessionLaps)) {
	DiffColor = RunColor;
	DiffText = FormatNumber(DiffSplit, 3);
	if(PrevSplit == 99999 || PrevSplit == 0) {
		DiffSplit = CurrSplit;
		DiffText = FormatNumber(DiffSplit, 3);
	} else {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
			DiffText = "+" + DiffText;
		}
	}
	if(GetCurLapTime() >= 60) {
		DrawText(DiffText, X + 32, Y, DiffColor, FontSize - 5, AlignH_Right);
	} else {
		DrawText(DiffText, X, Y, DiffColor, FontSize, AlignH_Right);
	}
} else {
	Compact = 2;
	if(GetCurLapTime() > 60) {
		if(RunTime > 60) {
			Compact = 1;
		}
		DrawTime(RunTime, 3, X + 32, Y, RunColor, FontSize - 5, AlignH_Right, Compact);
	} else {
		DrawTime(RunTime, 3, X, Y, RunColor, FontSize, AlignH_Right, Compact);
	}
}

//Previous Split
DrawText("PREVIOUS", 3 * SizeX / 4, Y + 10, RunColor, 3 * FontSize / 8, AlignH_Center);
if(CurrSplit != 0 && RunTime < CurrSplit + SplitDisplayLength) {
	PrevTime = PrevSplit;
} else {
	PrevTime = NextPrevSplit;
}

X = 7 * SizeX / 8 + 10;

Y -= 40;
if(PrevTime > 0 && PrevTime < 999999) {
	Compact = 2;
	if(LastPrevSplit > 60) {
		if(RunTime > 60) {
			Compact = 1;
		}
		DrawTime(PrevTime, 3, X + 25, Y, RunColor, 5 * FontSize / 8, AlignH_Right, Compact);
	} else {
		DrawTime(PrevTime, 3, X, Y, RunColor, 5 * FontSize / 8, AlignH_Right, Compact);
	}
} else {
	DrawText("---", X - 175, Y, RunColor, 5 * FontSize / 8, AlignH_Right);
}

//Draw Sectors
Sector = 0;
CompletedSectors = 0;
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit1 > 0 && (RunTime > CurrSplit1 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit1 && RunTime < CurrSplit1 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit2 > 0 && (RunTime > CurrSplit2 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit2 && RunTime < CurrSplit2 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit3 > 0 && (RunTime > CurrSplit3 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit3 && RunTime < CurrSplit3 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit4 > 0 && (RunTime > CurrSplit4 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit4 && RunTime < CurrSplit4 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit5 > 0 && (RunTime > CurrSplit5 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit5 && RunTime < CurrSplit5 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}
if(Sector < NumSplits) {
	if(GetCurLapNum() > 0 && CurrSplit6 > 0 && (RunTime > CurrSplit6 || (GetCurLapNum() > SessionLaps))) {
		if((RunTime > CurrSplit6 && RunTime < CurrSplit6 + SplitDisplayLength) || (Sector + 1 == NumSplits && RunTime < SplitDisplayLength)) {
			DrawText("SECTOR " + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		} else {
			DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, Black, 30, AlignH_Center);
		}
		CompletedSectors += 1;
	} else {
		DrawText("S" + FormatNumber(Sector + 1, 0), (SectorX * Sector + SectorX * (Sector + 1)) / 2, SectorY - 2, White, 30, AlignH_Center);
	}
	Sector += 1;
}

if(NextCurrSplit > 0 && GetCurLapNum() > 0) {
	SectorProgress = (RunTime - CurrSplit) / (NextCurrSplit - CurrSplit);
	ProgressX = SectorX * (CompletedSectors + SectorProgress);
	DrawLine(ProgressX, 0, ProgressX, SectorY, White, 2);
	DrawPoly3(ProgressX - 7, 0, ProgressX, 7, ProgressX + 7, 0, White, Filled);
}
}

}
