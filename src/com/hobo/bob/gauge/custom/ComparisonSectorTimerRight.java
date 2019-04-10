package com.hobo.bob.gauge.custom;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class ComparisonSectorTimerRight extends AbstractCustomGauge {

private int ConePenalty;
private int SplitStart;
private int NumSplits;
private int SplitNum;
private float SplitTime;
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
private String BackgroundColor;
private int FontSize;
private int FontWidth;
private int HeaderY;
private int SectorY;
private int ConesIndex;
private double X;
private int Y;
private float RunTime;
private String DiffText;
private float RunDisplayTime;
private String TimeString;
private int TimeIdx;
private float LapTime;
private int FooterY;
private int SeparatorX;
private int GapX;
private String SplitColor;
private float DiffSector;
private float DiffSplit;
private String DiffColor;
private float SectorTime;

public ComparisonSectorTimerRight(Frame frame, int sizeX, int sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
ConePenalty = 2;

//Gather Split Information
SplitStart = 0;
NumSplits = 0;

//Find Lap with Last Split Time
SplitNum = 2;
SplitTime = GetLapTime(SplitNum);
LastPrevSplit = 999999;
if(SplitTime == 0) {
	SplitNum = SplitNum + 1;
	SplitTime = GetLapTime(SplitNum);
}

//Possible Lap 1
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 2
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 3
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 4
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 5
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 6
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 7
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 8
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 9
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 10
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 11
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 12
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 13
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 14
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 15
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 16
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 17
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 18
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 19
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 20
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 21
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 22
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 23
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 24
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Lap 25
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 1
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 2
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 3
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 4
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 5
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 6
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split End
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0) {
	if(SplitStart == 0) {
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0) {
		NumSplits = SplitNum - SplitStart;
	}
}

//Colors
RunColor = ColorA;
PositiveSplitColor = ColorC;
NegativeSplitColor = ColorD;
HeaderColor = ColorE;
BackgroundColor = ColorF;

//Parameters
FontSize = 100;
FontWidth = 72;

//Spacing
HeaderY = SizeY - 100;
FooterY = 30;
SeparatorX = 3 * SizeX / 10;
GapX = SizeX / 8; // Gap between sector time and label

ConesIndex = GetDataIndex("Cones");

DrawCircle(SizeX - FooterY, FooterY, FooterY, BackgroundColor, Filled);
DrawRect(0, 0, SizeX - FooterY, FooterY, BackgroundColor, Filled);

//Draw Background
DrawRect(0, FooterY, SizeX, HeaderY, BackgroundColor, Filled);

//Draw Separator
DrawLine(SeparatorX, FooterY / 2, SeparatorX, HeaderY - FooterY / 2, White, 2);
DrawLine(SizeX - SeparatorX, FooterY / 2, SizeX - SeparatorX, HeaderY - FooterY / 2, White, 2);

//Save Split Information and Draw Sector List
SectorY = (HeaderY - FooterY) / (NumSplits + 1);
Y = HeaderY - FooterY + 5;
CurrSplit1 = 0;
CurrSplit2 = 0;
CurrSplit3 = 0;
CurrSplit4 = 0;
CurrSplit5 = 0;
CurrSplit6 = 0;
PrevSplit1 = 0;
PrevSplit2 = 0;
PrevSplit3 = 0;
PrevSplit4 = 0;
PrevSplit5 = 0;
PrevSplit6 = 0;
if(NumSplits >= 1) {
	CurrSplit1 = GetLapTime(SplitStart);
	CurrSplit2 = GetLapTime(1);
	PrevSplit1 = GetLapTime(SplitStart + NumSplits + 1);
	PrevSplit2 = GetLapTime(SplitStart + NumSplits + 2);
	DrawText("SECTOR 1", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 2) {
	CurrSplit2 = GetLapTime(SplitStart + 1);
	CurrSplit3 = GetLapTime(1);
	PrevSplit2 = GetLapTime(SplitStart + NumSplits + 2);
	PrevSplit3 = GetLapTime(SplitStart + NumSplits + 3);
	DrawText("SECTOR 2", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 3) {
	CurrSplit3 = GetLapTime(SplitStart + 2);
	CurrSplit4 = GetLapTime(1);
	PrevSplit3 = GetLapTime(SplitStart + NumSplits + 3);
	PrevSplit4 = GetLapTime(SplitStart + NumSplits + 4);
	DrawText("SECTOR 3", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 4) {
	CurrSplit4 = GetLapTime(SplitStart + 3);
	CurrSplit5 = GetLapTime(1);
	PrevSplit4 = GetLapTime(SplitStart + NumSplits + 4);
	PrevSplit5 = GetLapTime(SplitStart + NumSplits + 5);
	DrawText("SECTOR 4", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 5) {
	CurrSplit5 = GetLapTime(SplitStart + 4);
	CurrSplit6 = GetLapTime(1);
	PrevSplit5 = GetLapTime(SplitStart + NumSplits + 5);
	PrevSplit6 = GetLapTime(SplitStart + NumSplits + 6);
	DrawText("SECTOR 5", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 6) {
	CurrSplit6 = GetLapTime(SplitStart + 5);
	PrevSplit6 = GetLapTime(SplitStart + NumSplits + 6);
	DrawText("SECTOR 6", SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
}
DrawText("SECTOR " + FormatNumber(NumSplits + 1, 0), SizeX / 2, Y, RunColor, 3 * FontSize / 8, AlignH_Center);
LastPrevSplit = GetLapTime(SplitStart + NumSplits * 2 + 1);

//Draw Header
DrawRect(0, HeaderY, SizeX, SizeY, HeaderColor, Filled);

//Draw Car Line
DrawLineFlat(140, HeaderY + FooterY / 2, 140, SizeY - 20, ColorB, 6);

DrawRect(17, SizeY - 69, 115, SizeY - 22, White, Filled);
DrawRect(17, HeaderY + 15, 100, SizeY - 69, White, Filled);
DrawCircle(100, SizeY - 69, 15, White, Filled);

DrawRect(SizeX / 2 + 17, SizeY - 69, SizeX / 2 + 115, SizeY - 22, White, Filled);
DrawRect(SizeX / 2 + 17, HeaderY + 15, SizeX / 2 + 100, SizeY - 69, White, Filled);
DrawCircle(SizeX / 2 + 100, SizeY - 69, 15, White, Filled);
//Draw Car Line
DrawLineFlat(SizeX / 2 + 140, HeaderY + FooterY / 2, SizeX / 2 + 140, SizeY - 20, ColorG, 6);

SetTextOutline(Transparent);
DrawNumber(SplitStart - 3, 0, SizeX / 2 + 67, SizeY - 15, Black, 62, AlignH_Center);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

if(GetCurLapNum() < 1) {
	LapTime = 0;
} else {
	LapTime = GetCurLapTime();
	if(GetCurLapNum() > 1) {
		LapTime += GetLapTime(1);
	}
	if(GetCurLapNum() > 2) {
		LapTime += GetLapTime(2);
	}
	if(GetCurLapNum() > 3) {
		LapTime += GetLapTime(3);
	}
	if(GetCurLapNum() > 4) {
		LapTime += GetLapTime(4);
	}
	if(GetCurLapNum() > 5) {
		LapTime += GetLapTime(5);
	}
	if(GetCurLapNum() > 6) {
		LapTime += GetLapTime(6);
	}
}

X = 75 + SizeX - SeparatorX + 10;
Y = HeaderY - 45;

//Current Run or Difference between Splits
if((LapTime > LastPrevSplit) && (GetCurLapNum() > 1)) {
	DiffSplit = GetLapTime(1) - LastPrevSplit;
	if(ConesIndex > 0) {
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffText = FormatNumber(DiffSplit, 3);
	DiffColor = RunColor;
	if(DiffSplit < 0) {
		DiffColor = NegativeSplitColor;
	} else if(DiffSplit > 0) {
		DiffColor = PositiveSplitColor;
		DiffText = "+" + DiffText;
	}
	DrawText(DiffText, X - 30, Y, DiffColor, FontSize, AlignH_Left);
} else {
	RunTime = GetCurLapTime();
	if(GetCurLapNum() > 1) {
		RunDisplayTime = GetLapTime(1);
	} else {
		RunDisplayTime = RunTime;
	}
	if(ConesIndex > 0) {
		RunDisplayTime += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	TimeString = FormatNumber(RunDisplayTime, 3);
	TimeIdx = 0;
	if(RunDisplayTime >= 10) {
		DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
	}
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth + 50, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2 + 25, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3 + 25, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4 + 25, Y, RunColor, FontSize, AlignH_Center);
}

//Previous Split
X -= (SizeX - SeparatorX + 10);
if(LapTime > LastPrevSplit) {
	RunDisplayTime = LastPrevSplit;
} else {
	RunDisplayTime = LapTime;
}

if(ConesIndex > 0) {
	RunDisplayTime += ceil(GetDataValue(ConesIndex)) * ConePenalty;
}
TimeString = FormatNumber(RunDisplayTime, 3);
TimeIdx = 0;
if(RunDisplayTime >= 10) {
	DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
}
DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
TimeIdx += 1;
DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth + 50, Y, RunColor, FontSize, AlignH_Center);
TimeIdx += 1;
DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2 + 25, Y, RunColor, FontSize, AlignH_Center);
TimeIdx += 1;
DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3 + 25, Y, RunColor, FontSize, AlignH_Center);
TimeIdx += 1;
DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4 + 25, Y, RunColor, FontSize, AlignH_Center);

//Draw Sectors
Y = HeaderY - FooterY + 5;
FontWidth *= 3 / 8;
FontSize *= 3 / 8;
if(NumSplits >= 0) {
	SplitColor = RunColor;
	if(LapTime > CurrSplit1) {
		SectorTime = CurrSplit1;
	} else {
		SectorTime = LapTime;
	}
	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit1 && LapTime > CurrSplit1) {
		DiffSector = CurrSplit1 - PrevSplit1;
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.5 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else {
		TimeIdx = 0;
		TimeString = FormatNumber(SectorTime, 3);
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}

	X = SizeX / 2 - 1.335 * GapX;
	SectorTime = LapTime;
	if(LapTime > PrevSplit1) {
		SectorTime = PrevSplit1;
	}
	TimeString = FormatNumber(SectorTime, 3);
	TimeIdx = 0;
	if(SectorTime >= 10) {
		DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
	}
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);

	Y -= SectorY;
}
if(NumSplits >= 1) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > CurrSplit2) {
		SectorTime = CurrSplit2 - CurrSplit1;
	} else if(LapTime > CurrSplit1) {
		SectorTime = LapTime - CurrSplit1;
	}

	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit2 && LapTime > CurrSplit2) {
		DiffSector = (CurrSplit2 - CurrSplit1) - (PrevSplit2 - PrevSplit1);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.75 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	X = SizeX / 2 - 1.335 * GapX;

	SectorTime = 0;
	if(LapTime > PrevSplit2) {
		SectorTime = PrevSplit2 - PrevSplit1;
	} else if(LapTime > PrevSplit1) {
		SectorTime = LapTime - PrevSplit1;
	}
	if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}

	Y -= SectorY;
}
if(NumSplits >= 2) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > CurrSplit3) {
		SectorTime = CurrSplit3 - CurrSplit2;
	} else if(LapTime > CurrSplit2) {
		SectorTime = LapTime - CurrSplit2;
	}
	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit3 && LapTime > CurrSplit3) {
		DiffSector = (CurrSplit3 - CurrSplit2) - (PrevSplit3 - PrevSplit2);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.75 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	X = SizeX / 2 - 1.335 * GapX;
	SectorTime = 0;
	if(LapTime > PrevSplit3) {
		SectorTime = PrevSplit3 - PrevSplit2;
	} else if(LapTime > PrevSplit2) {
		SectorTime = LapTime - PrevSplit2;
	}
	if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	Y -= SectorY;
}
if(NumSplits >= 3) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > CurrSplit4) {
		SectorTime = CurrSplit4 - CurrSplit3;
	} else if(LapTime > CurrSplit3) {
		SectorTime = LapTime - CurrSplit3;
	}
	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit4 && LapTime > CurrSplit4) {
		DiffSector = (CurrSplit4 - CurrSplit3) - (PrevSplit4 - PrevSplit3);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.75 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	X = SizeX / 2 - 1.335 * GapX;
	SectorTime = 0;
	if(LapTime > PrevSplit4) {
		SectorTime = PrevSplit4 - PrevSplit3;
	} else if(LapTime > PrevSplit3) {
		SectorTime = LapTime - PrevSplit3;
	}
	if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	Y -= SectorY;
}
if(NumSplits >= 4) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > CurrSplit5) {
		SectorTime = CurrSplit5 - CurrSplit4;
	} else if(LapTime > CurrSplit4) {
		SectorTime = LapTime - CurrSplit4;
	}

	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit5 && LapTime > CurrSplit5) {
		DiffSector = (CurrSplit5 - CurrSplit4) - (PrevSplit5 - PrevSplit4);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.75 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	X = SizeX / 2 - 1.335 * GapX;
	SectorTime = 0;
	if(LapTime > PrevSplit5) {
		SectorTime = PrevSplit5 - PrevSplit4;
	} else if(LapTime > PrevSplit4) {
		SectorTime = LapTime - PrevSplit4;
	}
	if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	Y -= SectorY;
}
if(NumSplits >= 5) {
	SplitColor = RunColor;
	SectorTime = 0;
	if(LapTime > CurrSplit6) {
		SectorTime = CurrSplit6 - CurrSplit5;
	} else if(LapTime > CurrSplit5) {
		SectorTime = LapTime - CurrSplit5;
	}

	X = SizeX / 2 + 0.715 * GapX;
	if(LapTime > PrevSplit6 && LapTime > CurrSplit6) {
		DiffSector = (CurrSplit6 - CurrSplit5) - (PrevSplit6 - PrevSplit5);
		DiffText = FormatNumber(DiffSector, 3);
		if(DiffSector > 0) {
			DiffText = "+" + DiffText;
			SplitColor = PositiveSplitColor;
		} else if(DiffSector < 0) {
			SplitColor = NegativeSplitColor;
		}
		TimeIdx = 0;
		if(abs(DiffSector) >= 10) {
			DrawText(substr(DiffText, TimeIdx, 1), X - 0.75 * FontWidth, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		} else if(SectorTime != 0) {
			DrawText(substr(DiffText, TimeIdx, 1), X, Y, SplitColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 1.7, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 2.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 3.4, Y, SplitColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(DiffText, TimeIdx, 1), X + FontWidth * 4.4, Y, SplitColor, FontSize, AlignH_Center);
	} else if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
	X = SizeX / 2 - 1.335 * GapX;
	SectorTime = 0;
	if(LapTime > PrevSplit6) {
		SectorTime = PrevSplit6 - PrevSplit5;
	} else if(LapTime > PrevSplit5) {
		SectorTime = LapTime - PrevSplit5;
	}
	if(SectorTime > 0) {
		TimeString = FormatNumber(SectorTime, 3);
		TimeIdx = 0;
		if(SectorTime >= 10) {
			DrawText(substr(TimeString, TimeIdx, 1), X, Y, RunColor, FontSize, AlignH_Center);
			TimeIdx += 1;
		}
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 1.7, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 2.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 3.4, Y, RunColor, FontSize, AlignH_Center);
		TimeIdx += 1;
		DrawText(substr(TimeString, TimeIdx, 1), X + FontWidth * 4.4, Y, RunColor, FontSize, AlignH_Center);
	}
}
}

}
