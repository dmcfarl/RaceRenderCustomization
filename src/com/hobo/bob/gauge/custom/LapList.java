package com.hobo.bob.gauge.custom;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class LapList extends AbstractCustomGauge {

private int ConePenalty;
private int NumRuns;
private float RunDisplay1;
private float Cones1;
private float RunDisplay2;
private float Cones2;
private float RunDisplay3;
private float Cones3;
private float RunDisplay4;
private float Cones4;
private float RunDisplay5;
private float Cones5;
private int PrevRunNum;
private float BestRunTime;
private int BestRun;
private float BestCones;
private int RunOffset;
private float Run;
private float Cones;
private String RunNumColor;
private String CurRunTimeColor;
private String RunTimeColor;
private String BestRunTimeColor;
private String HeaderColor;
private String BackgroundColor;
private String RunBackgroundColor;
private int FontSize;
private int FontWidth;
private int TimeX;
private int GapY;
private int GapCone;
private int TopY;
private int BufferY;
private int ConeIndex;
private int RectWidth;
private int ConeX;
private int X;
private int Y;
private int RunNum;
private float RunTime;
private int CurrentRun;
private String RunColor;
private String RunTimeString;
private int CurrRunIndex;
private String ConeText;

public LapList(Frame frame, int sizeX, int sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
/*
 * This object will display the current in-progress lap time, and up to 10 prior
 * laps, if SizeY (in Object Parameters) is set big enough to accomodate that.
 * 
 * There's approximately 53 Y units per lap line, but that can be adjusted using
 * FontSize and GapY below.
 * 
 * For 0 prior laps, set SizeY to 135 For 5 prior laps, set SizeY to 400 For 10
 * prior laps, set SizeY to 665
 * 
 * Note that you'll need to adjust the Y position of the "Laps" Text Label on
 * the Object Properties tab.
 */
// Set Cone Penalty
ConePenalty = 2;

// Gather Run Information
NumRuns = -1;
RunDisplay1 = 0;
Cones1 = 0;
RunDisplay2 = 0;
Cones2 = 0;
RunDisplay3 = 0;
Cones3 = 0;
RunDisplay4 = 0;
Cones4 = 0;
RunDisplay5 = 0;
Cones5 = 0;

// Lap 1
PrevRunNum = 1;
BestRunTime = 999999;
BestRun = -1;
BestCones = -1;
RunOffset = 1;
// Get Next Run Time and reset Cones
Run = GetLapTime(PrevRunNum + RunOffset);
Cones = 0;
// Check if Lap 2 is 0; Skip to Lap 3 for Run 1 if necessary
if(Run == 0){
	RunOffset = 2;
	Run = GetLapTime(PrevRunNum + RunOffset);
	if(Run == 0){
		NumRuns = 0;
	}
}
if(NumRuns == -1){
	// Check for cone penalties/Off Course/DNF
	if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}
	if(Run > 0){
		BestRunTime = Run + Cones * ConePenalty;
		BestRun = PrevRunNum;
		BestCones = Cones;
	}

	// Shift displayed times down one notch
	if(RunDisplay4 > 0){
		RunDisplay5 = RunDisplay4;
		Cones5 = Cones4;
	}
	if(RunDisplay3 > 0){
		RunDisplay4 = RunDisplay3;
		Cones4 = Cones3;
	}
	if(RunDisplay2 > 0){
		RunDisplay3 = RunDisplay2;
		Cones3 = Cones2;
	}
	if(RunDisplay1 > 0){
		RunDisplay2 = RunDisplay1;
		Cones2 = Cones1;
	}
	RunDisplay1 = Run;
	Cones1 = Cones;
}

// Lap 2
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 3
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 4
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 5
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 6
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 7
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 8
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 9
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 10
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 11
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 12
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 13
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 14
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 15
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 16
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 17
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 18
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 19
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 20
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 21
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 22
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 23
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 24
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Lap 25
// Check if Next Lap needs to be parsed
if(NumRuns == -1){
	// Get Next Run Time and reset Cones
	PrevRunNum = PrevRunNum + 1;
	Run = GetLapTime(PrevRunNum + RunOffset);
	Cones = 0;
	// If Run is 0, then no additional checks need to be made
	if(Run == 0){
		NumRuns = PrevRunNum;
	}
	// Check for cone penalties/Off Course/DNF
	else if(Run > 10799){
		Cones = 100;
	} else if(Run > 7199){
		Cones = 50;
	} else if(Run > 600){
		Cones = floor(Run / 600);
		Run = Run - Cones * 600;
	}

	if(NumRuns == -1){
		// Check if best run
		if(Run + Cones * ConePenalty < BestRunTime){
			BestRunTime = Run + Cones * ConePenalty;
			BestRun = PrevRunNum;
			BestCones = Cones;
		}

		// Shift displayed times down one notch
		if(RunDisplay4 > 0){
			RunDisplay5 = RunDisplay4;
			Cones5 = Cones4;
		}
		if(RunDisplay3 > 0){
			RunDisplay4 = RunDisplay3;
			Cones4 = Cones3;
		}
		if(RunDisplay2 > 0){
			RunDisplay3 = RunDisplay2;
			Cones3 = Cones2;
		}
		if(RunDisplay1 > 0){
			RunDisplay2 = RunDisplay1;
			Cones2 = Cones1;
		}
		RunDisplay1 = Run;
		Cones1 = Cones;
	}
}

// Colors
RunNumColor = ColorA;
CurRunTimeColor = ColorB;
RunTimeColor = ColorC;
BestRunTimeColor = ColorD;
HeaderColor = ColorE;
BackgroundColor = ColorF;
RunBackgroundColor = ColorG;

FontSize = 42;
FontWidth = 33;

// Spacing
TimeX = 113; // Gap between the lap number and the lap time (may need to fit 2 or 3 digits)
GapY = 7; // Additional gap between rows
GapCone = 240;

TopY = SizeY - 63;

if(NumRuns <= 6){
	BufferY = TopY - ((FontSize + GapY) * NumRuns);
} else{
	BufferY = GapY * 2;
}

ConeIndex = GetDataIndex("Cones");
if((Cones1 > 0 && Cones1 < 50) || (Cones2 > 0 && Cones2 < 50) || (Cones3 > 0 && Cones3 < 50)
		|| (Cones4 > 0 && Cones4 < 50) || (BestRun > NumRuns - 5 && Cones5 > 0 && Cones5 < 50)
		|| (BestRun <= NumRuns - 5 && BestCones > 0 && BestCones < 50) || (ConeIndex > 0)){
	RectWidth = SizeX;
} else{
	RectWidth = SizeX - 50;
}
ConeX = SizeX - 60;

// Draw Header
DrawRect(0, TopY, SizeX, SizeY, HeaderColor, Filled);

// Draw Background
DrawRect(0, BufferY, RectWidth, TopY, BackgroundColor, Filled);
DrawRect(0, BufferY - GapY * 2, RectWidth - GapY * 2, TopY, BackgroundColor, Filled);
DrawCircle(RectWidth - GapY * 2, BufferY, GapY * 2, BackgroundColor, Filled);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

X = 16;
Y = TopY - GapY; //Space between top of chart and the topmost Run time

//Current Run
RunNum = NumRuns;
DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

Cones = 0;
if(ConeIndex > 0){
	Cones = ceil(GetDataValue(ConeIndex));
}

RunTime = GetCurLapTime();
CurrentRun = GetCurLapNum();
RunColor = CurRunTimeColor;
if(CurrentRun < 1){
	RunTime = 0;
}
else if(CurrentRun > 1){
	RunTime = GetLapTime(1);
	if(RunTime + Cones * ConePenalty < BestRunTime){
		BestRun = RunNum;
		RunColor = BestRunTimeColor;
	}
}

RunTimeString = FormatNumber(RunTime, 3);
CurrRunIndex = 0;
if(RunTime >= 10){
	DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
	CurrRunIndex += 1;
}
DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
CurrRunIndex += 1;
DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
CurrRunIndex += 1;
DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
CurrRunIndex += 1;
DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
CurrRunIndex += 1;
DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
if(Cones > 0){
	ConeText = "+" + FormatNumber(Cones, 0);
	DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
}

//Run -1
RunNum = RunNum - 1;
Y -= FontSize + GapY;
if(RunDisplay1 > 0 && Y > 0 && NumRuns > 1)
{
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = RunDisplay1;
	Cones = Cones1;
	if(RunNum == BestRun) { RunColor = BestRunTimeColor; } else { RunColor = RunTimeColor; }
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
}

//Run -2
RunNum = RunNum - 1;
Y -= FontSize + GapY;
if(RunDisplay2 > 0 && Y > 0)
{
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = RunDisplay2;
	Cones = Cones2;
	if(RunNum == BestRun) { RunColor = BestRunTimeColor; } else { RunColor = RunTimeColor; }
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
}

//Run -3
RunNum = RunNum - 1;
Y -= FontSize + GapY;
if(RunDisplay3 > 0 && Y > 0)
{
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = RunDisplay3;
	Cones = Cones3;
	if(RunNum == BestRun) { RunColor = BestRunTimeColor; } else { RunColor = RunTimeColor; }
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
}

//Run -4
RunNum = RunNum - 1;
Y -= FontSize + GapY;
if(RunDisplay4 > 0 && Y > 0)
{
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = RunDisplay4;
	Cones = Cones4;
	if(RunNum == BestRun) { RunColor = BestRunTimeColor; } else { RunColor = RunTimeColor; }
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
}

//Run -5
RunNum = RunNum - 1;
Y -= FontSize + GapY;
if(RunNum > BestRun){
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(BestRun, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = BestRunTime;
	Cones = BestCones;
	RunColor = BestRunTimeColor;
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
} else if(RunDisplay5 > 0 && Y > 0){
	DrawRect(X - 3, Y - FontSize + 5, X + 59, Y - GapY + 3, RunBackgroundColor, Filled);
	DrawRect(X - 3, Y - FontSize - 3, X + 51, Y - FontSize + 5, RunBackgroundColor, Filled);
	DrawCircle(X + 52, Y - FontSize + 4, 7, RunBackgroundColor, Filled);

	DrawNumber(RunNum, 0 , X + 28, Y, RunNumColor, FontSize, AlignH_Center);

	RunTime = RunDisplay5;
	Cones = Cones5;
	if(RunNum == BestRun) { RunColor = BestRunTimeColor; } else { RunColor = RunTimeColor; }
	if(Cones == 100){
		DrawText("DNF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else if(Cones == 50){
		DrawText("OFF", TimeX - 20, Y, RunColor, FontSize, 0);
	} else {
		RunTimeString = FormatNumber(RunTime, 3);
		CurrRunIndex = 0;
		if(RunTime >= 10){
			DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX, Y, RunColor, FontSize, AlignH_Center);
			CurrRunIndex += 1;
		}
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth + 20, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 2 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 3 + 10, Y, RunColor, FontSize, AlignH_Center);
		CurrRunIndex += 1;
		DrawText(substr(RunTimeString, CurrRunIndex, 1), TimeX + FontWidth * 4 + 10, Y, RunColor, FontSize, AlignH_Center);
		if(Cones > 0){
			ConeText = "+" + FormatNumber(Cones, 0);
			DrawText(ConeText, ConeX, Y, RunColor, FontSize, 0);
		}
	}
}
}

}
