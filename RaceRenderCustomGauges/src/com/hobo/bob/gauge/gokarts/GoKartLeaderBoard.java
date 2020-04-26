package com.hobo.bob.gauge.gokarts;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class GoKartLeaderBoard extends AbstractCustomGauge {

private float Header;
private float RowY;
private float NumDisp;
private float X;
private float Y;
private float FontSize;
private float Buffer;
private float BottomY;
private int Contestants;
private float Pos1Name;
private float Pos1Best;
private float Pos1Last;
private float Pos1Laps;
private float Pos2Name;
private float Pos2Best;
private float Pos2Last;
private float Pos2Laps;
private float Pos2Gap;
private float Pos3Name;
private float Pos3Best;
private float Pos3Last;
private float Pos3Laps;
private float Pos3Gap;
private float Pos4Name;
private float Pos4Best;
private float Pos4Last;
private float Pos4Laps;
private float Pos4Gap;
private float Pos5Name;
private float Pos5Best;
private float Pos5Last;
private float Pos5Laps;
private float Pos5Gap;
private float Pos6Name;
private float Pos6Best;
private float Pos6Last;
private float Pos6Laps;
private float Pos6Gap;
private float Pos7Name;
private float Pos7Best;
private float Pos7Last;
private float Pos7Laps;
private float Pos7Gap;
private float Pos8Name;
private float Pos8Best;
private float Pos8Last;
private float Pos8Laps;
private float Pos8Gap;
private float Pos9Name;
private float Pos9Best;
private float Pos9Last;
private float Pos9Laps;
private float Pos9Gap;
private float Pos10Name;
private float Pos10Best;
private float Pos10Last;
private float Pos10Laps;
private float Pos10Gap;
private float Pos11Name;
private float Pos11Best;
private float Pos11Last;
private float Pos11Laps;
private float Pos11Gap;
private float Pos12Name;
private float Pos12Best;
private float Pos12Last;
private float Pos12Laps;
private float Pos12Gap;
private float Pos13Name;
private float Pos13Best;
private float Pos13Last;
private float Pos13Laps;
private float Pos13Gap;
private float Pos14Name;
private float Pos14Best;
private float Pos14Last;
private float Pos14Laps;
private float Pos14Gap;
private float Pos15Name;
private float Pos15Best;
private float Pos15Last;
private float Pos15Laps;
private float Pos15Gap;
private float Pos16Name;
private float Pos16Best;
private float Pos16Last;
private float Pos16Laps;
private float Pos16Gap;
private float Pos17Name;
private float Pos17Best;
private float Pos17Last;
private float Pos17Laps;
private float Pos17Gap;
private float Pos18Name;
private float Pos18Best;
private float Pos18Last;
private float Pos18Laps;
private float Pos18Gap;
private float Pos19Name;
private float Pos19Best;
private float Pos19Last;
private float Pos19Laps;
private float Pos19Gap;
private float Pos20Name;
private float Pos20Best;
private float Pos20Last;
private float Pos20Laps;
private float Pos20Gap;
private int Pos;
private Float NameVal;
private String Name;
private float NamePos;
private float GapPos;
private float LastPos;
private float BestPos;
private float LapsPos;

public GoKartLeaderBoard(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
SetTextOutline(Transparent);

Header = 103;
RowY = 50;
Buffer = 7;

Contestants = 1;

Pos1Name = GetDataIndex("Position 1 Name");
Pos1Best = GetDataIndex("Position 1 Best");
Pos1Last = GetDataIndex("Position 1 Last");
Pos1Laps = GetDataIndex("Position 1 Laps");

Pos2Name = GetDataIndex("Position 2 Name");
if(Pos2Name >= 0 && Pos2Name <= 200) {
	Contestants += 1;
}
Pos2Best = GetDataIndex("Position 2 Best");
Pos2Last = GetDataIndex("Position 2 Last");
Pos2Laps = GetDataIndex("Position 2 Laps");
Pos2Gap = GetDataIndex("Position 2 Gap");

Pos3Name = GetDataIndex("Position 3 Name");
if(Pos3Name >= 0 && Pos3Name <= 200) {
	Contestants += 1;
}
Pos3Best = GetDataIndex("Position 3 Best");
Pos3Last = GetDataIndex("Position 3 Last");
Pos3Laps = GetDataIndex("Position 3 Laps");
Pos3Gap = GetDataIndex("Position 3 Gap");

Pos4Name = GetDataIndex("Position 4 Name");
if(Pos4Name >= 0 && Pos4Name <= 200) {
	Contestants += 1;
}
Pos4Best = GetDataIndex("Position 4 Best");
Pos4Last = GetDataIndex("Position 4 Last");
Pos4Laps = GetDataIndex("Position 4 Laps");
Pos4Gap = GetDataIndex("Position 4 Gap");

Pos5Name = GetDataIndex("Position 5 Name");
if(Pos5Name >= 0 && Pos5Name <= 200) {
	Contestants += 1;
}
Pos5Best = GetDataIndex("Position 5 Best");
Pos5Last = GetDataIndex("Position 5 Last");
Pos5Laps = GetDataIndex("Position 5 Laps");
Pos5Gap = GetDataIndex("Position 5 Gap");

Pos6Name = GetDataIndex("Position 6 Name");
if(Pos6Name >= 0 && Pos6Name <= 200) {
	Contestants += 1;
}
Pos6Best = GetDataIndex("Position 6 Best");
Pos6Last = GetDataIndex("Position 6 Last");
Pos6Laps = GetDataIndex("Position 6 Laps");
Pos6Gap = GetDataIndex("Position 6 Gap");

Pos7Name = GetDataIndex("Position 7 Name");
if(Pos7Name >= 0 && Pos7Name <= 200) {
	Contestants += 1;
}
Pos7Best = GetDataIndex("Position 7 Best");
Pos7Last = GetDataIndex("Position 7 Last");
Pos7Laps = GetDataIndex("Position 7 Laps");
Pos7Gap = GetDataIndex("Position 7 Gap");

Pos8Name = GetDataIndex("Position 8 Name");
if(Pos8Name >= 0 && Pos8Name <= 200) {
	Contestants += 1;
}
Pos8Best = GetDataIndex("Position 8 Best");
Pos8Last = GetDataIndex("Position 8 Last");
Pos8Laps = GetDataIndex("Position 8 Laps");
Pos8Gap = GetDataIndex("Position 8 Gap");

Pos9Name = GetDataIndex("Position 9 Name");
if(Pos9Name >= 0 && Pos9Name <= 200) {
	Contestants += 1;
}
Pos9Best = GetDataIndex("Position 9 Best");
Pos9Last = GetDataIndex("Position 9 Last");
Pos9Laps = GetDataIndex("Position 9 Laps");
Pos9Gap = GetDataIndex("Position 9 Gap");

Pos10Name = GetDataIndex("Position 10 Name");
if(Pos10Name >= 0 && Pos10Name <= 200) {
	Contestants += 1;
}
Pos10Best = GetDataIndex("Position 10 Best");
Pos10Last = GetDataIndex("Position 10 Last");
Pos10Laps = GetDataIndex("Position 10 Laps");
Pos10Gap = GetDataIndex("Position 10 Gap");

Pos11Name = GetDataIndex("Position 11 Name");
if(Pos11Name >= 0 && Pos11Name <= 200) {
	Contestants += 1;
}
Pos11Best = GetDataIndex("Position 11 Best");
Pos11Last = GetDataIndex("Position 11 Last");
Pos11Laps = GetDataIndex("Position 11 Laps");
Pos11Gap = GetDataIndex("Position 11 Gap");

Pos12Name = GetDataIndex("Position 12 Name");
if(Pos12Name >= 0 && Pos12Name <= 200) {
	Contestants += 1;
}
Pos12Best = GetDataIndex("Position 12 Best");
Pos12Last = GetDataIndex("Position 12 Last");
Pos12Laps = GetDataIndex("Position 12 Laps");
Pos12Gap = GetDataIndex("Position 12 Gap");

Pos13Name = GetDataIndex("Position 13 Name");
if(Pos13Name >= 0 && Pos13Name <= 200) {
	Contestants += 1;
}
Pos13Best = GetDataIndex("Position 13 Best");
Pos13Last = GetDataIndex("Position 13 Last");
Pos13Laps = GetDataIndex("Position 13 Laps");
Pos13Gap = GetDataIndex("Position 13 Gap");

Pos14Name = GetDataIndex("Position 14 Name");
if(Pos14Name >= 0 && Pos14Name <= 200) {
	Contestants += 1;
}
Pos14Best = GetDataIndex("Position 14 Best");
Pos14Last = GetDataIndex("Position 14 Last");
Pos14Laps = GetDataIndex("Position 14 Laps");
Pos14Gap = GetDataIndex("Position 14 Gap");

Pos15Name = GetDataIndex("Position 15 Name");
if(Pos15Name >= 0 && Pos15Name <= 200) {
	Contestants += 1;
}
Pos15Best = GetDataIndex("Position 15 Best");
Pos15Last = GetDataIndex("Position 15 Last");
Pos15Laps = GetDataIndex("Position 15 Laps");
Pos15Gap = GetDataIndex("Position 15 Gap");

Pos16Name = GetDataIndex("Position 16 Name");
if(Pos16Name >= 0 && Pos16Name <= 200) {
	Contestants += 1;
}
Pos16Best = GetDataIndex("Position 16 Best");
Pos16Last = GetDataIndex("Position 16 Last");
Pos16Laps = GetDataIndex("Position 16 Laps");
Pos16Gap = GetDataIndex("Position 16 Gap");

Pos17Name = GetDataIndex("Position 17 Name");
if(Pos17Name >= 0 && Pos17Name <= 200) {
	Contestants += 1;
}
Pos17Best = GetDataIndex("Position 17 Best");
Pos17Last = GetDataIndex("Position 17 Last");
Pos17Laps = GetDataIndex("Position 17 Laps");
Pos17Gap = GetDataIndex("Position 17 Gap");

Pos18Name = GetDataIndex("Position 18 Name");
if(Pos18Name >= 0 && Pos18Name <= 200) {
	Contestants += 1;
}
Pos18Best = GetDataIndex("Position 18 Best");
Pos18Last = GetDataIndex("Position 18 Last");
Pos18Laps = GetDataIndex("Position 18 Laps");
Pos18Gap = GetDataIndex("Position 18 Gap");

Pos19Name = GetDataIndex("Position 19 Name");
if(Pos19Name >= 0 && Pos19Name <= 200) {
	Contestants += 1;
}
Pos19Best = GetDataIndex("Position 19 Best");
Pos19Last = GetDataIndex("Position 19 Last");
Pos19Laps = GetDataIndex("Position 19 Laps");
Pos19Gap = GetDataIndex("Position 19 Gap");

Pos20Name = GetDataIndex("Position 20 Name");
if(Pos20Name >= 0 && Pos20Name <= 200) {
	Contestants += 1;
}
Pos20Best = GetDataIndex("Position 20 Best");
Pos20Last = GetDataIndex("Position 20 Last");
Pos20Laps = GetDataIndex("Position 20 Laps");
Pos20Gap = GetDataIndex("Position 20 Gap");


X = 13;
FontSize = 42;
Y = SizeY - Header - Buffer; // Space between top of chart and the topmost Run time

NumDisp = trunc(Y / RowY);
if(NumDisp > Contestants) {
	NumDisp = Contestants;
}

BottomY = Y - NumDisp * RowY;
//Draw background
DrawRect(0, BottomY, SizeX / 10, BottomY + SizeX / 10, ColorF, Filled);
DrawRRect(0, BottomY, SizeX, SizeY, ColorF, Filled);


//Draw Banner
DrawRect(0, SizeY - Header, SizeX, SizeY, ColorE, Filled);
DrawLine(SizeX / 4, SizeY - Header + FontSize + Buffer, SizeX * 3 / 4, SizeY - Header + FontSize + Buffer, ColorG, 2);
//LapText = FormatNumber(CurrentLapNum, 0) + " / " + FormatNumber(TotalLaps, 0);
//if(CurrentLapNum < 10) {
//	LapText = "   " + LapText;
//}


NamePos = X + 80;
GapPos = SizeX - 25;
LastPos = GapPos - 200;
BestPos = LastPos - 200;
LapsPos = BestPos - 220;

DrawText("Pos", X + 33, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Center);
DrawText("Name", NamePos, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Left);
DrawText("Lap", LapsPos, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Center);
DrawText("Best", BestPos - 85, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Center);
DrawText("Last", LastPos - 85, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Center);
DrawText("Gap", GapPos - 75, SizeY - Header + FontSize, ColorG, FontSize, AlignH_Center);
}

@Override
public void foregroundScript() {
SetTextOutline(Transparent);

Pos = 0;

//Pos1
if(Pos < Contestants && GetDataValue(Pos1Name) > 0) {
	Pos += 1;
	NameVal = GetDataValue(Pos1Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos1Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos1Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos1Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
}

//Pos2
if(Pos < Contestants && GetDataValue(Pos2Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos2Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos2Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos2Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos2Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos2Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos3
if(Pos < Contestants && GetDataValue(Pos3Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos3Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos3Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos3Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos3Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos3Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos4
if(Pos < Contestants && GetDataValue(Pos4Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos4Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos4Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos4Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos4Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos4Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos5
if(Pos < Contestants && GetDataValue(Pos5Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos5Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos5Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos5Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos5Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos5Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos6
if(Pos < Contestants && GetDataValue(Pos6Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos6Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos6Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos6Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos6Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos6Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos7
if(Pos < Contestants && GetDataValue(Pos7Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos7Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos7Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos7Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos7Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos7Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos8
if(Pos < Contestants && GetDataValue(Pos8Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos8Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos8Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos8Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos8Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos8Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos9
if(Pos < Contestants && GetDataValue(Pos9Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos9Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos9Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos9Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos9Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos9Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos10
if(Pos < Contestants && GetDataValue(Pos10Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos10Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos10Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos10Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos10Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos10Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos11
if(Pos < Contestants && GetDataValue(Pos11Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos11Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos11Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos11Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos11Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos11Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos12
if(Pos < Contestants && GetDataValue(Pos12Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos12Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos12Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos12Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos12Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos12Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos13
if(Pos < Contestants && GetDataValue(Pos13Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos13Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos13Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos13Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos13Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos13Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos14
if(Pos < Contestants && GetDataValue(Pos14Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos14Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos14Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos14Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos14Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos14Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos15
if(Pos < Contestants && GetDataValue(Pos15Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos15Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos15Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos15Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos15Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos15Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos16
if(Pos < Contestants && GetDataValue(Pos16Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos16Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos16Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos16Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos16Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos16Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos17
if(Pos < Contestants && GetDataValue(Pos17Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos17Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos17Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos17Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos17Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos17Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos18
if(Pos < Contestants && GetDataValue(Pos18Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos18Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos18Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos18Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos18Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos18Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos19
if(Pos < Contestants && GetDataValue(Pos19Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos19Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos19Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos19Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos19Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos19Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

//Pos20
if(Pos < Contestants && GetDataValue(Pos20Name) > 0) {
	Y -= RowY;
	Pos += 1;
	NameVal = GetDataValue(Pos20Name);
	Name = chr(trunc(NameVal / 10000)) + chr(trunc(NameVal % 10000 / 100)) + chr(trunc(NameVal % 100));

	DrawRect(X, Y - 32, X + 65, Y + 1, ColorB, Filled);
	DrawRect(X, Y - 40, X + 57, Y - 32, ColorB, Filled);
	DrawCircle(X + 58, Y - 33, 7, ColorB, Filled);
	DrawNumber(Pos, 0, X + 33, Y, ColorA, FontSize, AlignH_Center);
	DrawText(Name, NamePos, Y, ColorC, FontSize, AlignH_Left);
	DrawNumber(GetDataValue(Pos20Laps), 0, LapsPos, Y, ColorC, FontSize, AlignH_Center);
	DrawTime(GetDataValue(Pos20Best), 3, BestPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawTime(GetDataValue(Pos20Last), 3, LastPos, Y, ColorC, FontSize, AlignH_Right, 2);
	DrawText("+" + FormatNumber(GetDataValue(Pos20Gap), 3), GapPos, Y, ColorC, FontSize, AlignH_Right);
}

}

}
