package com.hobo.bob.gauge.custom;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class SectorTimer extends AbstractCustomGauge {

private int SplitDisplayLength;
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
private int SectorX;
private int CurrSector;
private int X;
private int Y;
private float CurrSplit;
private float PrevSplit;
private float NextPrevSplit;
private float RunTime;
private float DiffSplit;
private String DiffText;
private String DiffColor;
private float RunDisplayTime;
private String TimeString;
private int TimeIdx;
private float PrevTime;
private String PrevTimeString;

public SectorTimer(Frame frame, int sizeX, int sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
//Length of time in seconds to display the Current Split Time
SplitDisplayLength = 3;
ConePenalty = 2;

//Gather Split Information
SplitStart = 0;
NumSplits = 0;

//Find Location of First Split
//Find Lap with Last Split Time
SplitNum = 2;
SplitTime = GetLapTime(SplitNum);
LastPrevSplit = 999999;
if(SplitTime == 0){
	SplitNum = SplitNum + 1;
	SplitTime = GetLapTime(SplitNum);
}

//Possible Lap 1
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 2
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 3
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 4
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 5
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 6
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 7
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 8
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 9
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 10
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 11
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 12
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 13
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 14
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 15
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 16
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 17
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 18
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 19
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 20
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 21
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 22
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 23
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 24
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Lap 25
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
} else if(SplitStart == 0 && SplitTime < LastPrevSplit){
	LastPrevSplit = SplitTime;
}

//Possible Split 1
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 2
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 3
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 4
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 5
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split 6
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Possible Split End
SplitNum = SplitNum + 1;
SplitTime = GetLapTime(SplitNum);
if(SplitTime == 0){
	if(SplitStart == 0){
		SplitStart = SplitNum + 1;
	} else if(NumSplits == 0){
		NumSplits = SplitNum - SplitStart;
	}
}

//Save Split Information
if(NumSplits >= 1){ CurrSplit1 = GetLapTime(SplitStart); } else { CurrSplit1 = 0; }
if(NumSplits >= 2){ CurrSplit2 = GetLapTime(SplitStart + 1); } else { CurrSplit2 = 0; }
if(NumSplits >= 3){ CurrSplit3 = GetLapTime(SplitStart + 2); } else { CurrSplit3 = 0; }
if(NumSplits >= 4){ CurrSplit4 = GetLapTime(SplitStart + 3); } else { CurrSplit4 = 0; }
if(NumSplits >= 5){ CurrSplit5 = GetLapTime(SplitStart + 4); } else { CurrSplit5 = 0; }
if(NumSplits >= 6){ CurrSplit6 = GetLapTime(SplitStart + 5); } else { CurrSplit6 = 0; }

if(NumSplits >= 1){ PrevSplit1 = GetLapTime(SplitStart + NumSplits + 1); } else { PrevSplit1 = 0; }
if(NumSplits >= 2){ PrevSplit2 = GetLapTime(SplitStart + NumSplits + 2); } else { PrevSplit2 = 0; }
if(NumSplits >= 3){ PrevSplit3 = GetLapTime(SplitStart + NumSplits + 3); } else { PrevSplit3 = 0; }
if(NumSplits >= 4){ PrevSplit4 = GetLapTime(SplitStart + NumSplits + 4); } else { PrevSplit4 = 0; }
if(NumSplits >= 5){ PrevSplit5 = GetLapTime(SplitStart + NumSplits + 5); } else { PrevSplit5 = 0; }
if(NumSplits >= 6){ PrevSplit6 = GetLapTime(SplitStart + NumSplits + 6); } else { PrevSplit6 = 0; }

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
//OffsetX = 6;
//OffsetY = 6;
HeaderY = SizeY - 100;
SectorY = 30;

ConesIndex = GetDataIndex("Cones");

//Draw Sectors
SectorX = SizeX / (NumSplits + 1);
CurrSector = NumSplits;
if(CurrSector >= 0){
	DrawCircle(SectorX * (CurrSector + 1) - SectorY, SectorY, SectorY, BackgroundColor, Filled);
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY * 2, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}
if(CurrSector >= 0){
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * (CurrSector + 1) - 5, 0, SectorX * (CurrSector + 1), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}
if(CurrSector >= 0){
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * (CurrSector + 1) - 5, 0, SectorX * (CurrSector + 1), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}
if(CurrSector >= 0){
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * (CurrSector + 1) - 5, 0, SectorX * (CurrSector + 1), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}
if(CurrSector >= 0){
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * (CurrSector + 1) - 5, 0, SectorX * (CurrSector + 1), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}
if(CurrSector >= 0){
	DrawLineGradientRGB(SectorX * CurrSector + SectorY, SectorY / 2, SectorX * (CurrSector + 1) - SectorY, SectorY / 2, HeaderColor, BackgroundColor, SectorY); 
	DrawRect(SectorX * (CurrSector + 1) - 5, 0, SectorX * (CurrSector + 1), SectorY, BackgroundColor, Filled);
	DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 5, SectorY, HeaderColor, Filled);
	DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, White, 30, AlignH_Center);
	CurrSector -= 1;
}

//Draw Background
DrawRect(0, SectorY, SizeX, HeaderY, BackgroundColor, Filled);

//Draw Separator
DrawLine(SizeX / 2, SectorY + 15, SizeX / 2, HeaderY - 15, White, 2);

//Draw Header
DrawRect(0, HeaderY, SizeX, SizeY, HeaderColor, Filled);

//Draw Car Line
DrawLineFlat(140, HeaderY + 15, 140, SizeY - 20, ColorB, 6);

DrawRect(17, SizeY - 69, 115, SizeY - 22, White, Filled);
DrawRect(17, HeaderY + 15, 100, SizeY - 69, White, Filled);
DrawCircle(100, SizeY - 69, 15, White, Filled);

SetTextOutline(Transparent);
DrawNumber(SplitStart - 3, 0, 67, SizeY - 15, Black, 62, AlignH_Center);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

X = 68;//OffsetX + 32;
Y = HeaderY - 33;

//Current Run

CurrSplit = 0;
PrevSplit = 0;
NextPrevSplit = 0;
RunTime = GetCurLapTime();
// Determine Current Split Times
if(GetCurLapNum() > 1){
	CurrSplit = GetLapTime(1);
	PrevSplit = LastPrevSplit;
	NextPrevSplit = 99999;
} else if(RunTime >= CurrSplit6 && CurrSplit6 > 0){
	CurrSplit = CurrSplit6;
	PrevSplit = PrevSplit6;
	NextPrevSplit = LastPrevSplit;
} else if(RunTime >= CurrSplit5 && CurrSplit5 > 0){
	CurrSplit = CurrSplit5;
	PrevSplit = PrevSplit5;
	if(CurrSplit6 == 0){
		NextPrevSplit = LastPrevSplit;
	} else {
		NextPrevSplit = PrevSplit6;
	}
} else if(RunTime >= CurrSplit4 && CurrSplit4 > 0){
	CurrSplit = CurrSplit4;
	PrevSplit = PrevSplit4;
	if(CurrSplit5 == 0){
		NextPrevSplit = LastPrevSplit;
	} else {
		NextPrevSplit = PrevSplit5;
	}
} else if(RunTime >= CurrSplit3 && CurrSplit3 > 0){
	CurrSplit = CurrSplit3;
	PrevSplit = PrevSplit3;
	if(CurrSplit4 == 0){
		NextPrevSplit = LastPrevSplit;
	} else {
		NextPrevSplit = PrevSplit4;
	}
} else if(RunTime >= CurrSplit2 && CurrSplit2 > 0){
	CurrSplit = CurrSplit2;
	PrevSplit = PrevSplit2;
	if(CurrSplit3 == 0){
		NextPrevSplit = LastPrevSplit;
	} else {
		NextPrevSplit = PrevSplit3;
	}
} else if(RunTime >= CurrSplit1 && CurrSplit1 > 0){
	CurrSplit = CurrSplit1;
	PrevSplit = PrevSplit1;
	if(CurrSplit2 == 0){
		NextPrevSplit = LastPrevSplit;
	} else {
		NextPrevSplit = PrevSplit2;
	}
} else {
	NextPrevSplit = PrevSplit1;
}
DiffSplit = CurrSplit - PrevSplit;
if(ConesIndex > 0){
	DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
}

//Current Run or Difference between Splits
if(GetCurLapNum() > 1 || (CurrSplit != 0 && CurrSplit + SplitDisplayLength > RunTime)) {
	DiffColor = RunColor;
	DiffText = FormatNumber(DiffSplit, 3);
	if(PrevSplit == 999999 || PrevSplit == 0) {
		DiffSplit = CurrSplit;
		DiffText = FormatNumber(DiffSplit, 3);
	} else {
		if(DiffSplit < 0){
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0){
			DiffColor = PositiveSplitColor;
			DiffText = "+" + DiffText;
		}
	}
	DrawText(DiffText, SizeX / 4, Y, DiffColor, FontSize, AlignH_Center);
} else {
	RunDisplayTime = RunTime;
	TimeString = FormatNumber(RunDisplayTime, 3);
	TimeIdx = 0;
	if(RunTime >= 10){
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
DrawText("PREVIOUS", 3 * SizeX / 4, Y + 10, RunColor, 3 * FontSize / 8, AlignH_Center);
if(CurrSplit != 0 && RunTime < CurrSplit + SplitDisplayLength) {
	PrevTime = PrevSplit;
} else {
	PrevTime = NextPrevSplit;
}

FontWidth *= 5 / 8;
X += SizeX / 2 + 65;

Y -= 40;
if(PrevTime > 0 && PrevTime < 999999) {
	PrevTimeString = FormatNumber(PrevTime, 3);
	TimeIdx = 0;
	if(PrevTime >= 10){
		DrawText(substr(PrevTimeString, TimeIdx, 1), X, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
		TimeIdx += 1;
	}
	DrawText(substr(PrevTimeString, TimeIdx, 1), X + FontWidth, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(PrevTimeString, TimeIdx, 1), X + FontWidth + 30, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(PrevTimeString, TimeIdx, 1), X + FontWidth * 2 + 15, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(PrevTimeString, TimeIdx, 1), X + FontWidth * 3 + 15, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
	TimeIdx += 1;
	DrawText(substr(PrevTimeString, TimeIdx, 1), X + FontWidth * 4 + 15, Y, RunColor, 5 * FontSize / 8, AlignH_Center);
} else {
	DrawText("---", X + 55, Y, RunColor, 5 * FontSize / 8, AlignH_Left);
}

//Draw Sectors
CurrSector = 0;
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit1 > 0 && RunTime > CurrSplit1) || (CurrSplit1 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit1 > 0){
		DiffSplit = CurrSplit1 - PrevSplit1;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit1 == 0 && LastPrevSplit < 999999) || (PrevSplit1 > 0 && PrevSplit1 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector, 0, SectorX * CurrSector + 2, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit1 + SplitDisplayLength) || CurrSplit1 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit2 > 0 && RunTime > CurrSplit2) || (CurrSplit2 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit2 > 0){
		DiffSplit = CurrSplit2 - PrevSplit2;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit2 == 0 && LastPrevSplit < 999999) || (PrevSplit2 > 0 && PrevSplit2 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector - 1, 0, SectorX * CurrSector + 2, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit2 + SplitDisplayLength) || CurrSplit2 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit3 > 0 && RunTime > CurrSplit3) || (CurrSplit3 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit3 > 0){
		DiffSplit = CurrSplit3 - PrevSplit3;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit3 == 0 && LastPrevSplit < 999999) || (PrevSplit3 > 0 && PrevSplit3 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector - 1, 0, SectorX * CurrSector + 2, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit3 + SplitDisplayLength) || CurrSplit3 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit4 > 0 && RunTime > CurrSplit4) || (CurrSplit4 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit4 > 0){
		DiffSplit = CurrSplit4 - PrevSplit4;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit4 == 0 && LastPrevSplit < 999999) || (PrevSplit4 > 0 && PrevSplit4 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector, 0, SectorX * (CurrSector + 1) - SectorY, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit4 + SplitDisplayLength) || CurrSplit4 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit5 > 0 && RunTime > CurrSplit5) || (CurrSplit5 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit5 > 0){
		DiffSplit = CurrSplit5 - PrevSplit5;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit5 == 0 && LastPrevSplit < 999999) || (PrevSplit5 > 0 && PrevSplit5 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector, 0, SectorX * (CurrSector + 1) - SectorY, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit5 + SplitDisplayLength) || CurrSplit5 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
if(CurrSector <= NumSplits && (GetCurLapNum() > 1 || (CurrSplit6 > 0 && RunTime > CurrSplit6) || (CurrSplit6 == 0 && RunTime > GetLapTime(1)))){
	if(CurrSplit6 > 0){
		DiffSplit = CurrSplit6 - PrevSplit6;
	} else {
		DiffSplit = GetLapTime(1) - LastPrevSplit;
	}
	if(ConesIndex > 0){
		DiffSplit += ceil(GetDataValue(ConesIndex)) * ConePenalty;
	}
	DiffColor = RunColor;
	if((CurrSplit6 == 0 && LastPrevSplit < 999999) || (PrevSplit6 > 0 && PrevSplit6 < 999999)) {
		if(DiffSplit < 0) {
			DiffColor = NegativeSplitColor;
		} else if(DiffSplit > 0) {
			DiffColor = PositiveSplitColor;
		}
	}

	//DrawRect(SectorX * CurrSector, 0, SectorX * (CurrSector + 1) - SectorY, SectorY, DiffColor, Filled);
	if(CurrSector + 1 <= NumSplits){
		//DrawLineGradientRGB(SectorX * (CurrSector + 1) - SectorY, 2, SectorX * (CurrSector + 1), 2, HeaderColor, DiffColor, SectorY); 
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

	if((GetCurLapNum() == 1 && RunTime < CurrSplit6 + SplitDisplayLength) || CurrSplit6 == 0 && RunTime < SplitDisplayLength){
		DrawText("SECTOR " + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	} else {
		DrawText("S" + FormatNumber(CurrSector + 1, 0), (SectorX * CurrSector + SectorX * (CurrSector + 1)) / 2, 33, Black, 30, AlignH_Center);
	}
	CurrSector += 1;
}
}

}
