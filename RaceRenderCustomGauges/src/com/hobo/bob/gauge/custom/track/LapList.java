package com.hobo.bob.gauge.custom.track;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapList extends AbstractCustomGauge {

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
private float PrevBestLapNumIndex;
private float TotalLapsIndex;
private float SessionLapsIndex;
private float PrevBestLapNum;
private float TotalLaps;
private float SessionLapStart;
private float SessionLapStartIndex;
private float SessionLaps;
private float CurrentLapNum;
private float Ratio;
private float FutureLapDisplay;
private float PreviousLapDisplay;
private float PreviousLapTransition;
private boolean DrawFutureTime;
private boolean DrawCurrentTime;
private int DrawMinutes;
private float PenaltyIndex;
private Float Penalties;
private float PenaltyX;
private float CurrentPenaltyIndex;
private int TotalPenalties;

public LapList(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

Header = 103;
RowY = 50;
Buffer = 7;

TotalLapsIndex = GetDataIndex("Total Laps");
SessionLapsIndex = GetDataIndex("Session Laps");
SessionLapStartIndex = GetDataIndex("Session Lap Start");
PrevBestLapNumIndex = GetDataIndex("Previous Lap Number");
CurrentPenaltyIndex = GetDataIndex("Current Penalty");

X = 13;
FontSize = 42;
PenaltyX = SizeX - 120;
TimeX = PenaltyX - 15;

FutureLapDisplay = 2; // seconds
PreviousLapDisplay = 4; // seconds
PreviousLapTransition = 1 / 2; // seconds
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

TotalLaps = GetDataValue(TotalLapsIndex);
SessionLapStart = GetDataValue(SessionLapStartIndex);
SessionLaps = GetDataValue(SessionLapsIndex);
PrevBestLapNum = GetDataValue(PrevBestLapNumIndex);

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
//BottomY = Y - NumDisp * RowY;
//DrawRect(0, BottomY, SizeX / 10, BottomY + SizeX / 10, ColorF, Filled);
//DrawRRect(0, BottomY, SizeX, SizeY, ColorF, Filled);

if(DrawFutureTime) {
	DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
	DrawNumber(CurrentLapNum + 1, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawTime(0, 3, TimeX - 3, Y, ColorC, FontSize, AlignH_Right, 1);
	Y -= RowY;
}

// Draw Banner
//DrawRRect(0, SizeY - Header, PenaltyX, SizeY, ColorE, Filled);
DrawCircle(PenaltyX * 9 / 10, SizeY - (PenaltyX / 10), PenaltyX / 10, ColorE, Filled);
DrawRect(0, SizeY - Header, PenaltyX * 9 / 10, SizeY, ColorE, Filled);
DrawRect(0, SizeY - Header, PenaltyX, SizeY - (PenaltyX / 10), ColorE, Filled);
DrawLine(PenaltyX / 4, SizeY - Header + FontSize + Buffer, PenaltyX * 3 / 4, SizeY - Header + FontSize + Buffer, ColorG, 2);
DrawNumber(CurrentLapNum, 0, (PenaltyX / 2) - 18, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Right);
DrawText("/", PenaltyX / 2, SizeY - Header + FontSize + 2, ColorG, FontSize, AlignH_Center);
DrawNumber(TotalLaps, 0, (PenaltyX / 2) + 18, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Left);

TotalPenalties = 0;
PenaltyIndex = CurrentPenaltyIndex;
if(PenaltyIndex >= 0 && PenaltyIndex <= 100) {
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
	PenaltyIndex = CurrentPenaltyIndex;
	Penalties = GetDataValue(PenaltyIndex);
	if(Penalties == 0) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else if(Penalties < 10) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
	} else {
		DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
		if(Penalties >= 12) {
			DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else if(Penalties >= 11) {
			DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	}
	DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}

//NumRuns - 1
if(floor(Y) > 0 && CurrentLapNum > 0) {
	if(LapIdx <= 0) {
		LapIdx += SessionLapStart + SessionLaps;
	}

	// Highlight Run Number Box
	if(GetCurLapNum() > 1 && GetCurLapTime() < PreviousLapDisplay) {
		if(PrevBestLapNum == CurrentLapNum) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}

//NumRuns - 2
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 3
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 4
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 5
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 6
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 7
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 8
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 9
if(floor(Y) > 0 && CurrentLapNum > 0) {
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
//NumRuns - 10: Only used when a Session has finished
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
	PenaltyIndex = GetDataIndex("Penalty Lap " + FormatNumber(CurrentLapNum, 0));
	if(PenaltyIndex < 0 || PenaltyIndex > 100) {
		DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
		if(TotalPenalties > 0) {
			DrawText("---", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		}
	} else {
		Penalties = GetDataValue(PenaltyIndex);
		if(Penalties < 10) {
			DrawTime(LapTime, 3, TimeX - 3, Y, RunColor, FontSize, AlignH_Right, 2 - DrawMinutes);
			DrawText("+" + FormatNumber(Penalties, 0), PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
		} else {
			DrawText("-----", TimeX - 50, Y, RunColor, FontSize, AlignH_Right);
			if(Penalties >= 12) {
				DrawText("DNF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else if(Penalties >= 11) {
				DrawText("OFF", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			} else {
				DrawText("RE", PenaltyX + 60, Y, RunColor, FontSize, AlignH_Center);
			}
		}
	}

	Y -= RowY;
	LapIdx -= 1;
	CurrentLapNum -= 1;
}
}

}
