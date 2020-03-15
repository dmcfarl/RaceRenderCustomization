package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class ComparisonSectorTimerLeft extends AbstractCustomGauge {

private float ConePenalty;
private float NumSplits;
private String RunColor;
private String PositiveSplitColor;
private String NegativeSplitColor;
private String HeaderColor;
private String BackgroundColor;
private float FontSize;
private float HeaderY;
private float SectorY;
private float ConesIndex;
private float Y;
private float RunTime;
private String DiffText;
private float RunDisplayTime;
private float LapTime;
private float FooterY;
private float SeparatorX;
private float SectorX1;
private float SectorX2;
private String SplitColor;
private float DiffSector;
private float DiffSplit;
private String DiffColor;
private float SectorTime;
private float Compact;
private float TimeDisp;
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
private boolean CurrentSplitOnLeft;
private float CurrentLapNumIdx;
private float PreviousLapNumIdx;
private Float LeftCarNum;
private Float RightCarNum;
private Float LeftSplit1;
private Float LeftSplit2;
private Float LeftSplit3;
private Float LeftSplit4;
private Float LeftSplit5;
private Float LeftSplit6;
private Float RightSplit1;
private Float RightSplit2;
private Float RightSplit3;
private Float RightSplit4;
private Float RightSplit5;
private Float RightSplit6;
private float CurrentSplitLastIdx;
private float PreviousSplitLastIdx;
private Float LeftSplitLast;
private Float RightSplitLast;

public ComparisonSectorTimerLeft(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
CurrentSplitOnLeft = true;
ConePenalty = 2;

//Gather Split Information
NumSplits = 0;

CurrentSplit1Idx = GetDataIndex("Current Split 1");
CurrentSplit2Idx = GetDataIndex("Current Split 2");
CurrentSplit3Idx = GetDataIndex("Current Split 3");
CurrentSplit4Idx = GetDataIndex("Current Split 4");
CurrentSplit5Idx = GetDataIndex("Current Split 5");
CurrentSplit6Idx = GetDataIndex("Current Split 6");
CurrentSplitLastIdx = GetDataIndex("Current Last Split");
PreviousSplit1Idx = GetDataIndex("Previous Split 1");
PreviousSplit2Idx = GetDataIndex("Previous Split 2");
PreviousSplit3Idx = GetDataIndex("Previous Split 3");
PreviousSplit4Idx = GetDataIndex("Previous Split 4");
PreviousSplit5Idx = GetDataIndex("Previous Split 5");
PreviousSplit6Idx = GetDataIndex("Previous Split 6");
PreviousSplitLastIdx = GetDataIndex("Previous Last Split");

CurrentLapNumIdx = GetDataIndex("Current Lap Number");
PreviousLapNumIdx = GetDataIndex("Previous Lap Number");

if(CurrentSplit6Idx >= 0 && CurrentSplit6Idx < 50) {
	NumSplits += 1;
}
if(CurrentSplit5Idx >= 0 && CurrentSplit5Idx < 50) {
	NumSplits += 1;
}
if(CurrentSplit4Idx >= 0 && CurrentSplit4Idx < 50) {
	NumSplits += 1;
}
if(CurrentSplit3Idx >= 0 && CurrentSplit3Idx < 50) {
	NumSplits += 1;
}
if(CurrentSplit2Idx >= 0 && CurrentSplit2Idx < 50) {
	NumSplits += 1;
}
if(CurrentSplit1Idx >= 0 && CurrentSplit1Idx < 50) {
	NumSplits += 1;
}

//Colors
RunColor = ColorA;
PositiveSplitColor = ColorC;
NegativeSplitColor = ColorD;
HeaderColor = ColorE;
BackgroundColor = ColorF;

//Parameters
FontSize = 100;

//Spacing
HeaderY = SizeY - 100;
FooterY = 30;
SeparatorX = 3 * SizeX / 10;
SectorX1 = SizeX / 2 - 131;
SectorX2 = SizeX / 2 + 277;

ConesIndex = GetDataIndex("Cones");

DrawCircle(SizeX - FooterY, FooterY, FooterY, BackgroundColor, Filled);
DrawRect(0, 0, SizeX - FooterY, FooterY, BackgroundColor, Filled);

//Draw Background
DrawRect(0, FooterY, SizeX, HeaderY, BackgroundColor, Filled);

//Draw Separator
DrawLine(SeparatorX, FooterY / 2, SeparatorX, HeaderY - FooterY / 2, White, 2);
DrawLine(SizeX - SeparatorX, FooterY / 2, SizeX - SeparatorX, HeaderY - FooterY / 2, White, 2);

//Save Split Information and Draw Sector List
SectorY = (HeaderY - FooterY) / NumSplits;
Y = HeaderY - FooterY + 5;
if(NumSplits >= 1) {
	DrawText("SECTOR 1", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
	CurrentSplitLastIdx = CurrentSplit1Idx;
	PreviousSplitLastIdx = PreviousSplit1Idx;
}
if(NumSplits >= 2) {
	DrawText("SECTOR 2", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
	CurrentSplitLastIdx = CurrentSplit2Idx;
	PreviousSplitLastIdx = PreviousSplit2Idx;
}
if(NumSplits >= 3) {
	DrawText("SECTOR 3", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
	CurrentSplitLastIdx = CurrentSplit3Idx;
	PreviousSplitLastIdx = PreviousSplit3Idx;
}
if(NumSplits >= 4) {
	DrawText("SECTOR 4", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
	CurrentSplitLastIdx = CurrentSplit4Idx;
	PreviousSplitLastIdx = PreviousSplit4Idx;
}
if(NumSplits >= 5) {
	DrawText("SECTOR 5", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
	CurrentSplitLastIdx = CurrentSplit5Idx;
	PreviousSplitLastIdx = PreviousSplit5Idx;
}
if(NumSplits >= 6) {
	DrawText("SECTOR 6", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	CurrentSplitLastIdx = CurrentSplit6Idx;
	PreviousSplitLastIdx = PreviousSplit6Idx;
}

//Draw Header
DrawRect(0, HeaderY, SizeX, SizeY, HeaderColor, Filled);

//Draw Car Line
DrawLineFlat(140, HeaderY + FooterY / 2, 140, SizeY - 20, ColorB, 6);

DrawRect(17, SizeY - 69, 115, SizeY - 22, White, Filled);
DrawRect(17, HeaderY + 15, 100, SizeY - 69, White, Filled);
DrawCircle(100, SizeY - 69, 15, White, Filled);

SetTextOutline(Transparent);

DrawRect(SizeX / 2 + 17, SizeY - 69, SizeX / 2 + 115, SizeY - 22, White, Filled);
DrawRect(SizeX / 2 + 17, HeaderY + 15, SizeX / 2 + 100, SizeY - 69, White, Filled);
DrawCircle(SizeX / 2 + 100, SizeY - 69, 15, White, Filled);
//Draw Car Line
DrawLineFlat(SizeX / 2 + 140, HeaderY + FooterY / 2, SizeX / 2 + 140, SizeY - 20, ColorG, 6);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

if(CurrentSplitOnLeft) {
	LeftCarNum = GetDataValue(CurrentLapNumIdx);
	LeftSplit1 = GetDataValue(CurrentSplit1Idx);
	LeftSplit2 = GetDataValue(CurrentSplit2Idx);
	LeftSplit3 = GetDataValue(CurrentSplit3Idx);
	LeftSplit4 = GetDataValue(CurrentSplit4Idx);
	LeftSplit5 = GetDataValue(CurrentSplit5Idx);
	LeftSplit6 = GetDataValue(CurrentSplit6Idx);
	LeftSplitLast = GetDataValue(CurrentSplitLastIdx);
	RightCarNum = GetDataValue(PreviousLapNumIdx);
	RightSplit1 = GetDataValue(PreviousSplit1Idx);
	RightSplit2 = GetDataValue(PreviousSplit2Idx);
	RightSplit3 = GetDataValue(PreviousSplit3Idx);
	RightSplit4 = GetDataValue(PreviousSplit4Idx);
	RightSplit5 = GetDataValue(PreviousSplit5Idx);
	RightSplit6 = GetDataValue(PreviousSplit6Idx);
	RightSplitLast = GetDataValue(PreviousSplitLastIdx);
} else {
	LeftCarNum = GetDataValue(PreviousLapNumIdx);
	LeftSplit1 = GetDataValue(PreviousSplit1Idx);
	LeftSplit2 = GetDataValue(PreviousSplit2Idx);
	LeftSplit3 = GetDataValue(PreviousSplit3Idx);
	LeftSplit4 = GetDataValue(PreviousSplit4Idx);
	LeftSplit5 = GetDataValue(PreviousSplit5Idx);
	LeftSplit6 = GetDataValue(PreviousSplit6Idx);
	LeftSplitLast = GetDataValue(PreviousSplitLastIdx);
	RightCarNum = GetDataValue(CurrentLapNumIdx);
	RightSplit1 = GetDataValue(CurrentSplit1Idx);
	RightSplit2 = GetDataValue(CurrentSplit2Idx);
	RightSplit3 = GetDataValue(CurrentSplit3Idx);
	RightSplit4 = GetDataValue(CurrentSplit4Idx);
	RightSplit5 = GetDataValue(CurrentSplit5Idx);
	RightSplit6 = GetDataValue(CurrentSplit6Idx);
	RightSplitLast = GetDataValue(CurrentSplitLastIdx);
}

if(RightSplitLast > 60 || LeftSplitLast > 60) {
	Compact = 1;
} else {
	Compact = 2;
}

DrawNumber(LeftCarNum, 0, 67, SizeY - 25, Black, 62, AlignH_Center);
DrawNumber(RightCarNum, 0, SizeX / 2 + 67, SizeY - 25, Black, 62, AlignH_Center);

if(GetCurLapNum() == 0 || (GetCurLapNum() == 2 && GetCurLapTime() > 30)) {
	LapTime = 0;
} else {
	LapTime = GetCurLapTime();
	if(GetCurLapNum() == 2) {
		LapTime += GetLapTime(1);
	}
	if(GetCurLapNum() > 3) {
		LapTime += GetLapTime(3);
	}
}

Y = HeaderY - 65;

//Current Run or Difference between Splits
RunTime = GetCurLapTime();
if(GetCurLapNum() > 1) {
	RunDisplayTime = LeftSplitLast;
} else {
	RunDisplayTime = RunTime;
}
if(ConesIndex > 0) {
	RunDisplayTime += ceil(GetDataValue(ConesIndex)) * ConePenalty;
}
if(Compact == 2) {
	DrawTime(RunDisplayTime, 3, 5 * SizeX / 18, Y, RunColor, FontSize, AlignH_Right, Compact);
} else {
	TimeDisp = Compact;
	if(RunDisplayTime < 60) {
		TimeDisp = 2;
	}
	DrawTime(RunDisplayTime, 3, 5 * SizeX / 18 + 32, Y, RunColor, FontSize - 6, AlignH_Right, TimeDisp);
}

//Previous Split
if(LapTime > RightSplitLast) {
	RunDisplayTime = RightSplitLast;
} else {
	RunDisplayTime = LapTime;
}

if((LapTime > RightSplitLast) && (GetCurLapNum() == 2 || GetCurLapNum() > 3)) {
	DiffSplit = RightSplitLast - LeftSplitLast;
	if(ConesIndex > 0) {
		DiffSplit -= ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffText = FormatNumber(DiffSplit, 3);
	DiffColor = RunColor;
	if(DiffSplit < 0) {
		DiffColor = NegativeSplitColor;
	} else if(DiffSplit > 0) {
		DiffColor = PositiveSplitColor;
		DiffText = "+" + DiffText;
	}
	if(Compact == 2) {
		DrawText(DiffText, SizeX - 40, Y, DiffColor, FontSize, AlignH_Right);
	} else {
		DrawText(DiffText, SizeX - 8, Y, DiffColor, FontSize - 6, AlignH_Right);
	}
} else {
	if(Compact == 2) {
		DrawTime(RunDisplayTime, 3, SizeX - 40, Y, RunColor, FontSize, AlignH_Right, Compact);
	} else {
		TimeDisp = Compact;
		if(RunDisplayTime < 60) {
			TimeDisp = 2;
		}
		DrawTime(RunDisplayTime, 3, SizeX - 8, Y, RunColor, FontSize - 6, AlignH_Right, TimeDisp);
	}
}

//Draw Sectors
Y = HeaderY - FooterY + 5;
FontSize *= 3 / 8;
if(NumSplits >= 1) {
	SplitColor = RunColor;
	if(LapTime > LeftSplit1) {
		SectorTime = LeftSplit1;
	} else {
		SectorTime = LapTime;
	}
	DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);

	if(LapTime > RightSplit1 && LapTime > LeftSplit1) {
		DiffSector = RightSplit1 - LeftSplit1;
		DiffText = FormatNumber(DiffSector, 3);
		if(RightSplit1 > LeftSplit1) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(RightSplit1 < LeftSplit1) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = LapTime;
		if(LapTime > RightSplit1) {
			SectorTime = RightSplit1;
		}
		DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
	}
	Y -= SectorY;
}
if(NumSplits >= 2) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > LeftSplit2) {
		SectorTime = LeftSplit2 - LeftSplit1;
	} else if(LapTime > LeftSplit1) {
		SectorTime = LapTime - LeftSplit1;
	}
	if(SectorTime > 0) {
		DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);
	}
	if(LapTime > RightSplit2 && LapTime > LeftSplit2) {
		DiffSector = (RightSplit2 - RightSplit1) - (LeftSplit2 - LeftSplit1);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = 0;
		if(LapTime > RightSplit2) {
			SectorTime = RightSplit2 - RightSplit1;
		} else if(LapTime > RightSplit1) {
			SectorTime = LapTime - RightSplit1;
		}
		if(SectorTime > 0) {
			DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 3) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > LeftSplit3) {
		SectorTime = LeftSplit3 - LeftSplit2;
	} else if(LapTime > LeftSplit2) {
		SectorTime = LapTime - LeftSplit2;
	}
	if(SectorTime > 0) {
		DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);
	}
	if(LapTime > RightSplit3 && LapTime > LeftSplit3) {
		DiffSector = (RightSplit3 - RightSplit2) - (LeftSplit3 - LeftSplit2);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = 0;
		if(LapTime > RightSplit3) {
			SectorTime = RightSplit3 - RightSplit2;
		} else if(LapTime > RightSplit2) {
			SectorTime = LapTime - RightSplit2;
		}
		if(SectorTime > 0) {
			DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 4) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > LeftSplit4) {
		SectorTime = LeftSplit4 - LeftSplit3;
	} else if(LapTime > LeftSplit3) {
		SectorTime = LapTime - LeftSplit3;
	}
	if(SectorTime > 0) {
		DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);
	}
	if(LapTime > RightSplit4 && LapTime > LeftSplit4) {
		DiffSector = (RightSplit4 - RightSplit3) - (LeftSplit4 - LeftSplit3);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = 0;
		if(LapTime > RightSplit4) {
			SectorTime = RightSplit4 - RightSplit3;
		} else if(LapTime > RightSplit3) {
			SectorTime = LapTime - RightSplit3;
		}
		if(SectorTime > 0) {
			DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 5) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > LeftSplit5) {
		SectorTime = LeftSplit5 - LeftSplit4;
	} else if(LapTime > LeftSplit4) {
		SectorTime = LapTime - LeftSplit4;
	}
	if(SectorTime > 0) {
		DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);
	}
	if(LapTime > RightSplit5 && LapTime > LeftSplit5) {
		DiffSector = (RightSplit5 - RightSplit4) - (LeftSplit5 - LeftSplit4);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = 0;
		if(LapTime > RightSplit5) {
			SectorTime = RightSplit5 - RightSplit4;
		} else if(LapTime > RightSplit4) {
			SectorTime = LapTime - RightSplit4;
		}
		if(SectorTime > 0) {
			DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 6) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > LeftSplit6) {
		SectorTime = LeftSplit6 - LeftSplit5;
	} else if(LapTime > LeftSplit5) {
		SectorTime = LapTime - LeftSplit5;
	}
	if(SectorTime > 0) {
		DrawTime(SectorTime, 3, SectorX1, Y, RunColor, FontSize, AlignH_Right, 2);
	}
	if(LapTime > RightSplit6 && LapTime > LeftSplit6) {
		DiffSector = (RightSplit6 - RightSplit5) - (LeftSplit6 - LeftSplit5);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		DrawText(DiffText, SectorX2, Y, SplitColor, FontSize, AlignH_Right);
	} else {
		SectorTime = 0;
		if(LapTime > RightSplit6) {
			SectorTime = RightSplit6 - RightSplit5;
		} else if(LapTime > RightSplit5) {
			SectorTime = LapTime - RightSplit5;
		}
		if(SectorTime > 0) {
			DrawTime(SectorTime, 3, SectorX2, Y, SplitColor, FontSize, AlignH_Right, 2);
		}
	}
}
}

}
