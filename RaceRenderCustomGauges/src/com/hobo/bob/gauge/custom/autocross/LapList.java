package com.hobo.bob.gauge.custom.autocross;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapList extends AbstractCustomGauge {

private int ConePenalty;
private int ConeTime;
private int NumRuns;
private int RunIdx;
private int BestRun;
private float BestRunTime;
private float Run;
private float Cones;
private int Header;
private int RowY;
private int NumDisp;
private int X;
private int Y;
private int FontSize;
private int TimeX;
private int ConeIndex;
private int ConeX;
private float RunTime;
private String ConeText;
private float LastDisp;
private float LastCones;
private String RunColor;
private float BestCones;
private int Buffer;
private int BottomY;

public LapList(Frame frame, int sizeX, int sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);
//Set Cone Penalty
ConePenalty = 2;
ConeTime = 600; // Number of seconds added to recorded Lap times to designate a cone

Header = 63;
RowY = 50;
Buffer = 7;

//Find Number of Runs (i.e. where 0-second lap occurs
if(GetLapTime(2) == 0) {
	NumRuns = 1;
} else if(GetLapTime(3) == 0) {
	NumRuns = 2;
} else if(GetLapTime(4) == 0) {
	NumRuns = 3;
} else if(GetLapTime(5) == 0) {
	NumRuns = 4;
} else if(GetLapTime(6) == 0) {
	NumRuns = 5;
} else if(GetLapTime(7) == 0) {
	NumRuns = 6;
} else if(GetLapTime(8) == 0) {
	NumRuns = 7;
} else if(GetLapTime(9) == 0) {
	NumRuns = 8;
} else if(GetLapTime(10) == 0) {
	NumRuns = 9;
} else if(GetLapTime(11) == 0) {
	NumRuns = 10;
} else if(GetLapTime(12) == 0) {
	NumRuns = 11;
} else if(GetLapTime(13) == 0) {
	NumRuns = 12;
} else if(GetLapTime(14) == 0) {
	NumRuns = 13;
} else if(GetLapTime(15) == 0) {
	NumRuns = 14;
} else if(GetLapTime(16) == 0) {
	NumRuns = 15;
} else if(GetLapTime(17) == 0) {
	NumRuns = 16;
} else if(GetLapTime(18) == 0) {
	NumRuns = 17;
} else if(GetLapTime(19) == 0) {
	NumRuns = 18;
} else if(GetLapTime(20) == 0) {
	NumRuns = 19;
} else if(GetLapTime(21) == 0) {
	NumRuns = 20;
} else if(GetLapTime(22) == 0) {
	NumRuns = 21;
} else if(GetLapTime(23) == 0) {
	NumRuns = 22;
} else if(GetLapTime(24) == 0) {
	NumRuns = 23;
} else if(GetLapTime(25) == 0) {
	NumRuns = 24;
} else {
	NumRuns = 25;
}

NumDisp = trunc((SizeY - Header - Buffer) / RowY);
if(NumDisp > NumRuns) {
	NumDisp = NumRuns;
}
BottomY = SizeY - Header - Buffer - NumDisp * RowY;
DrawRect(0, BottomY, SizeX / 10, BottomY + SizeX / 10, ColorF, Filled);
DrawRRect(0, BottomY, SizeX, SizeY, ColorF, Filled);
DrawRect(0, SizeY - Header, SizeX, SizeY, ColorE, Filled);

//Find Best Run and Set Display Fields
RunIdx = NumRuns - 2;
BestRun = -1;
BestRunTime = 99999;
BestCones = 0;

X = 13;
Y = SizeY - Header - Buffer;
FontSize = 42;
TimeX = SizeX - 230; // Gap between the lap number and the lap time (may need to fit 2 or 3 digits)
ConeIndex = GetDataIndex("Cones");
ConeX = SizeX - 60;

DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
DrawNumber(NumRuns, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
Y -= RowY;

//NumRuns - 1
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 2
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 3
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 4
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 5
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 6
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 7
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 8
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 9
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 10
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 11
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 12
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 13
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 14
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 15
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 16
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 17
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 18
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 19
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 20
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 21
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 22
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 23
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 24
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
//NumRuns - 25
if(RunIdx >= 0) {
	Run = GetLapTime(RunIdx + 2);
	Cones = round(Run / ConeTime, 0);
	if(Run < BestRunTime) {
		BestRunTime = Run;
		BestCones = Cones;
		BestRun = RunIdx;
	}
	if(RunIdx >= NumRuns - NumDisp) {
		RunTime = Run - Cones * ConeTime;
		// Draw Number Box
		DrawRect(X, Y - 32, X + 65, Y + 1, ColorG, Filled);
		DrawRect(X, Y - 40, X + 57, Y - 32, ColorG, Filled);
		DrawCircle(X + 58, Y - 33, 7, ColorG, Filled);
		if(RunIdx > NumRuns - NumDisp) {
			DrawNumber(RunIdx + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
			if(Cones == 18) {
				DrawText("DNF", TimeX, Y, ColorC, FontSize, 0);
			} else if(Cones == 12) {
				DrawText("OFF", TimeX, Y, ColorC, FontSize, 0);
			} else {
				DrawTime(RunTime, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
				if(Cones > 0) {
					ConeText = "+" + FormatNumber(Cones, 0);
					DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
				}
			}
		} else {
			LastDisp = RunTime;
			LastCones = Cones;
		}
		Y -= RowY;
	}
	RunIdx -= 1;
}
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

Y = SizeY - Header - Buffer; // Space between top of chart and the topmost Run time

//Current Run
RunIdx = NumRuns;

Cones = 0;
if(ConeIndex > 0) {
	Cones = ceil(GetDataValue(ConeIndex));
}

RunTime = GetCurLapTime();
RunIdx = GetCurLapNum();
RunColor = ColorB;
if(RunIdx < 1) {
	RunTime = 0;
} else if(RunIdx > 1) {
	RunTime = GetLapTime(1);
	if(RunTime + Cones * ConePenalty < BestRunTime) {
		BestRun = NumRuns - 1;
		RunColor = ColorD;
	}
}

DrawTime(RunTime, 3, ConeX - 3, Y, RunColor, FontSize, AlignH_Right, 2);
if(Cones > 0) {
	ConeText = "+" + FormatNumber(Cones, 0);
	DrawText(ConeText, ConeX, Y, RunColor, FontSize, AlignH_Left);
}

if(NumDisp > 1) {
	Y = SizeY - Header - Buffer - (NumDisp - 1) * RowY;

	if(NumRuns - NumDisp > BestRun) {
		DrawNumber(BestRun + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
		DrawTime(BestRunTime, 3, ConeX - 3, Y, ColorD, FontSize, AlignH_Right, 2);
		if(BestCones > 0) {
			ConeText = "+" + FormatNumber(BestCones, 0);
			DrawText(ConeText, ConeX, Y, ColorD, FontSize, AlignH_Left);
		}
	} else {
		DrawNumber(NumRuns - NumDisp + 1, 0, X + 31, Y, ColorA, FontSize, AlignH_Center);
		DrawTime(LastDisp, 3, ConeX - 3, Y, ColorC, FontSize, AlignH_Right, 2);
		if(LastCones > 0) {
			ConeText = "+" + FormatNumber(LastCones, 0);
			DrawText(ConeText, ConeX, Y, ColorC, FontSize, AlignH_Left);
		}

		if(BestRun != NumRuns - 1) {
			Y = SizeY - Header - Buffer - (NumRuns - BestRun - 1) * RowY;
			DrawTime(BestRunTime, 3, ConeX - 3, Y, ColorD, FontSize, AlignH_Right, 2);
			if(BestCones > 0) {
				ConeText = "+" + FormatNumber(BestCones, 0);
				DrawText(ConeText, ConeX, Y, ColorD, FontSize, AlignH_Left);
			}
		}
	}
}
}

}
