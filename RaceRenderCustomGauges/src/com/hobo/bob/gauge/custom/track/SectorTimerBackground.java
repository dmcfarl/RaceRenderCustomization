package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class SectorTimerBackground extends AbstractCustomGauge {

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
private String BackgroundColor;
private float HeaderY;
private float SectorY;
private float SectorX;
private float CurrSector;
private float CurrSplit;
private float PrevSplit;
private float RunTime;
private float DiffSplit;
private String DiffColor;
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
private float CurrentPenaltyIdx;
private float PreviousPenaltyIdx;
private float SessionLapsIndex;
private Float SessionLaps;
private float PreviousSplitLastIdx;

public SectorTimerBackground(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
//Length of time in seconds to display the Current Split Time
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
BackgroundColor = ColorF;

//Spacing
//OffsetX = 6;
//OffsetY = 6;
HeaderY = SizeY - 100;
SectorY = 30;

//Draw Sectors
SectorX = SizeX / (NumSplits);
CurrSector = NumSplits;
if(CurrSector > 0) {
	DrawCircle(SectorX * (CurrSector) - SectorY, SectorY, SectorY, BackgroundColor, Filled);
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY * 2, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}
if(CurrSector > 0) {
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector) - 5, 0, SectorX * (CurrSector), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}
if(CurrSector > 0) {
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector) - 5, 0, SectorX * (CurrSector), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}
if(CurrSector > 0) {
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector) - 5, 0, SectorX * (CurrSector), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}
if(CurrSector > 0) {
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector) - 5, 0, SectorX * (CurrSector), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}
if(CurrSector > 0) {
	DrawLineGradientRGB(SectorX * (CurrSector - 1) + SectorY, SectorY / 2, SectorX * (CurrSector) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY);
	DrawRect(SectorX * (CurrSector) - 5, 0, SectorX * (CurrSector), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * (CurrSector - 1), 0, SectorX * (CurrSector - 1) + 5, SectorY, HeaderColor, Filled);
	CurrSector -= 1;
}

//Draw Background
DrawRect(0, SectorY, SizeX, SizeY, BackgroundColor, Filled);

//Draw Separator
DrawLine(SizeX / 2, SectorY + 15, SizeX / 2, HeaderY - 15, White, 2);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

SessionLaps = GetDataValue(SessionLapsIndex);

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

RunTime = GetCurLapTime();
// Determine Current Split Times
if(GetCurLapNum() > SessionLaps) {
	CurrSplit = GetLapTime(SessionLaps);
	PrevSplit = LastPrevSplit;
} else if(RunTime >= CurrSplit6 && CurrSplit6 > 0) {
	CurrSplit = CurrSplit6;
	PrevSplit = PrevSplit6;
} else if(RunTime >= CurrSplit5 && CurrSplit5 > 0) {
	CurrSplit = CurrSplit5;
	PrevSplit = PrevSplit5;
} else if(RunTime >= CurrSplit4 && CurrSplit4 > 0) {
	CurrSplit = CurrSplit4;
	PrevSplit = PrevSplit4;
} else if(RunTime >= CurrSplit3 && CurrSplit3 > 0) {
	CurrSplit = CurrSplit3;
	PrevSplit = PrevSplit3;
} else if(RunTime >= CurrSplit2 && CurrSplit2 > 0) {
	CurrSplit = CurrSplit2;
	PrevSplit = PrevSplit2;
} else if(RunTime >= CurrSplit1 && CurrSplit1 > 0) {
	CurrSplit = CurrSplit1;
	PrevSplit = PrevSplit1;
}
DiffSplit = CurrSplit - PrevSplit;
if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
	DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
}
if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
	DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
}

//Draw Sectors
CurrSector = 0;
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit1 > 0 && (RunTime > CurrSplit1 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit1 > 0) {
		DiffSplit = CurrSplit1 - PrevSplit1;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit1 == 0 && LastPrevSplit < 999999) || (PrevSplit1 > 0 && PrevSplit1 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit2 > 0 && (RunTime > CurrSplit2 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit2 > 0) {
		DiffSplit = CurrSplit2 - PrevSplit2;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit2 == 0 && LastPrevSplit < 999999) || (PrevSplit2 > 0 && PrevSplit2 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit3 > 0 && (RunTime > CurrSplit3 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit3 > 0) {
		DiffSplit = CurrSplit3 - PrevSplit3;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit3 == 0 && LastPrevSplit < 999999) || (PrevSplit3 > 0 && PrevSplit3 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit4 > 0 && (RunTime > CurrSplit4 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit4 > 0) {
		DiffSplit = CurrSplit4 - PrevSplit4;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit4 == 0 && LastPrevSplit < 999999) || (PrevSplit4 > 0 && PrevSplit4 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit5 > 0 && (RunTime > CurrSplit5 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit5 > 0) {
		DiffSplit = CurrSplit5 - PrevSplit5;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit5 == 0 && LastPrevSplit < 999999) || (PrevSplit5 > 0 && PrevSplit5 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
if(CurrSector < NumSplits && GetCurLapNum() > 0 && CurrSplit6 > 0 && (RunTime > CurrSplit6 || (GetCurLapNum() > SessionLaps))) {
	if(CurrSplit6 > 0) {
		DiffSplit = CurrSplit6 - PrevSplit6;
	} else {
		DiffSplit = GetLapTime(SessionLaps) - LastPrevSplit;
		if(CurrentPenaltyIdx > 0 && CurrentPenaltyIdx < 100) {
			DiffSplit += ceil(GetDataValue(CurrentPenaltyIdx)) * PenaltyTime;
		}
		if(PreviousPenaltyIdx > 0 && PreviousPenaltyIdx < 100) {
			DiffSplit -= ceil(GetDataValue(PreviousPenaltyIdx)) * PenaltyTime;
		}
	}
	DiffColor = RunColor;
	if((CurrSplit6 == 0 && LastPrevSplit < 999999) || (PrevSplit6 > 0 && PrevSplit6 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	if(CurrSector + 1 < NumSplits) {
		DrawRect(SectorX * (CurrSector + 1) - SectorY, 0, SectorX * (CurrSector + 1), SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
	} else {
		DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BlendColorsRGB(HeaderColor, DiffColor, 0.47), Filled);
		DrawRect(SectorX * (CurrSector + 1) - SectorY * 2, SectorY + 1, SectorX * (CurrSector + 1), SectorY * 2, BackgroundColor, Filled);
	}
	DrawLineGradientRGB(SectorX * CurrSector, 3, SectorX * (CurrSector + 1) - SectorY, 3, HeaderColor, DiffColor, 4);
	DrawLineGradientRGB(SectorX * CurrSector, 8, SectorX * (CurrSector + 1) - SectorY, 8, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 13, SectorX * (CurrSector + 1) - SectorY, 13, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 18, SectorX * (CurrSector + 1) - SectorY, 18, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 23, SectorX * (CurrSector + 1) - SectorY, 23, HeaderColor, DiffColor, 3);
	DrawLineGradientRGB(SectorX * CurrSector, 28, SectorX * (CurrSector + 1) - SectorY, 28, HeaderColor, DiffColor, 3);

	CurrSector += 1;
}
}

}
