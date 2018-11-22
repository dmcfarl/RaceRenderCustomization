package com.hobo.bob.gauge.custom;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class ComparisonSectorTimer extends AbstractCustomGauge {

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
private int X;
private int Y;
private float RunTime;
private String DiffText;
private float RunDisplayTime;
private String TimeString;
private int TimeIdx;
private float PrevTime;
private int FooterY;
private int SeparatorX;
private int GapX;
private String SplitColor;

public ComparisonSectorTimer(Frame frame, int sizeX, int sizeY) {
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
Y = HeaderY - FooterY;
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
	DrawText("SECTOR 1", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 2) {
	CurrSplit2 = GetLapTime(SplitStart + 1);
	CurrSplit3 = GetLapTime(1);
	PrevSplit2 = GetLapTime(SplitStart + NumSplits + 2);
	PrevSplit3 = GetLapTime(SplitStart + NumSplits + 3);
	DrawText("SECTOR 2", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 3) {
	CurrSplit3 = GetLapTime(SplitStart + 2);
	CurrSplit4 = GetLapTime(1);
	PrevSplit3 = GetLapTime(SplitStart + NumSplits + 3);
	PrevSplit4 = GetLapTime(SplitStart + NumSplits + 4);
	DrawText("SECTOR 3", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 4) {
	CurrSplit4 = GetLapTime(SplitStart + 3);
	CurrSplit5 = GetLapTime(1);
	PrevSplit4 = GetLapTime(SplitStart + NumSplits + 4);
	PrevSplit5 = GetLapTime(SplitStart + NumSplits + 5);
	DrawText("SECTOR 4", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 5) {
	CurrSplit5 = GetLapTime(SplitStart + 4);
	CurrSplit6 = GetLapTime(1);
	PrevSplit5 = GetLapTime(SplitStart + NumSplits + 5);
	PrevSplit6 = GetLapTime(SplitStart + NumSplits + 6);
	DrawText("SECTOR 5", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
	Y -= SectorY;
}
if(NumSplits >= 6) {
	CurrSplit6 = GetLapTime(SplitStart + 5);
	PrevSplit6 = GetLapTime(SplitStart + NumSplits + 6);
	DrawText("SECTOR 6", SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
}
DrawText("SECTOR " + FormatNumber(NumSplits + 1, 0), SizeX / 2, Y + 5, RunColor, 3 * FontSize / 8, AlignH_Center);
LastPrevSplit = GetLapTime(SplitStart + NumSplits * 2 + 1);

//Draw Header
DrawRect(0, HeaderY, SizeX, SizeY, HeaderColor, Filled);

//Draw Car Line
DrawLineFlat(140, HeaderY + FooterY / 2, 140, SizeY - 20, ColorB, 6);

DrawRect(17, SizeY - 69, 115, SizeY - 22, White, Filled);
DrawRect(17, HeaderY + 15, 100, SizeY - 69, White, Filled);
DrawCircle(100, SizeY - 69, 15, White, Filled);

SetTextOutline(Transparent);
DrawNumber(SplitStart - 3, 0, 67, SizeY - 15, Black, 62, AlignH_Center);

DrawRect(SizeX / 2 + 17, SizeY - 69, SizeX / 2 + 115, SizeY - 22, White, Filled);
DrawRect(SizeX / 2 + 17, HeaderY + 15, SizeX / 2 + 100, SizeY - 69, White, Filled);
DrawCircle(SizeX / 2 + 100, SizeY - 69, 15, White, Filled);
//Draw Car Line
DrawLineFlat(SizeX / 2 + 140, HeaderY + FooterY / 2, SizeX / 2 + 140, SizeY - 20, ColorG, 6);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

X = 75;
Y = HeaderY - 45;

//Current Run or Difference between Splits
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

//Previous Split
X += SizeX - SeparatorX + 10;
if(GetCurLapNum() < 1) {
	PrevTime = 0;
} else {
	PrevTime = GetCurLapTime();
	if(GetCurLapNum() > 1) {
		PrevTime += GetLapTime(1);
	}
	if(GetCurLapNum() > 2) {
		PrevTime += GetLapTime(2);
	}
	if(GetCurLapNum() > 3) {
		PrevTime += GetLapTime(3);
	}
	if(GetCurLapNum() > 4) {
		PrevTime += GetLapTime(4);
	}
	if(GetCurLapNum() > 5) {
		PrevTime += GetLapTime(5);
	}
	if(GetCurLapNum() > 6) {
		PrevTime += GetLapTime(6);
	}
	if(PrevTime > LastPrevSplit) {
		RunDisplayTime = LastPrevSplit;
	} else {
		RunDisplayTime = PrevTime;
	}
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
Y = HeaderY - FooterY;
FontWidth *= 3 / 8;
FontSize *= 3 / 8;
if(NumSplits >= 0) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit1) {
		DrawText(FormatNumber(CurrSplit1, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit1) {
		if(PrevTime < CurrSplit1) {
			DrawText(FormatNumber(PrevSplit1, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit1 - CurrSplit1, 3);
			if(PrevSplit1 > CurrSplit1) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit1 < CurrSplit1) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 1) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit2) {
		DrawText(FormatNumber(CurrSplit2, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit2) {
		if(PrevTime < CurrSplit2) {
			DrawText(FormatNumber(PrevSplit2, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit2 - CurrSplit2, 3);
			if(PrevSplit2 > CurrSplit2) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit2 < CurrSplit2) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 2) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit3) {
		DrawText(FormatNumber(CurrSplit3, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit3) {
		if(PrevTime < CurrSplit3) {
			DrawText(FormatNumber(PrevSplit3, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit3 - CurrSplit3, 3);
			if(PrevSplit3 > CurrSplit3) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit3 < CurrSplit3) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 3) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit4) {
		DrawText(FormatNumber(CurrSplit4, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit4) {
		if(PrevTime < CurrSplit4) {
			DrawText(FormatNumber(PrevSplit4, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit4 - CurrSplit4, 3);
			if(PrevSplit4 > CurrSplit4) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit4 < CurrSplit4) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 4) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit5) {
		DrawText(FormatNumber(CurrSplit5, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit5) {
		if(PrevTime < CurrSplit5) {
			DrawText(FormatNumber(PrevSplit5, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit5 - CurrSplit5, 3);
			if(PrevSplit5 > CurrSplit5) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit5 < CurrSplit5) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
if(NumSplits >= 5) {
	SplitColor = RunColor;
	if(PrevTime > CurrSplit6) {
		DrawText(FormatNumber(CurrSplit6, 3), SizeX / 2 - GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
	}
	if(PrevTime > PrevSplit6) {
		if(PrevTime < CurrSplit6) {
			DrawText(FormatNumber(PrevSplit6, 3), SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		} else {
			DiffText = FormatNumber(PrevSplit6 - CurrSplit6, 3);
			if(PrevSplit6 > CurrSplit6) {
				DiffText = "+" + DiffText;
				SplitColor = PositiveSplitColor;
			} else if(PrevSplit6 < CurrSplit6) {
				SplitColor = NegativeSplitColor;
			}
			DrawText(DiffText, SizeX / 2 + GapX, Y + 5, SplitColor, FontSize, AlignH_Center);
		}
	}
	Y -= SectorY;
}
}

}
