package com.hobo.bob.gauge.custom.autocross;

import com.hobo.bob.gauge.AbstractCustomGauge;
import com.hobo.bob.gauge.model.Frame;

public class EstimatedGapTimer extends AbstractCustomGauge {

private float Header;
private float Footer;
private float Border;
private float Delta;
private String Display;
private String Color;
private float X;

public EstimatedGapTimer(Frame frame, float sizeX, float sizeY) {
super(frame, sizeX, sizeY);
}

@Override
public void backgroundScript() {
Header = SizeY / 4;
Footer = SizeY / 3;
Border = 3;

//Draw Filled Background
DrawRRect(0, Footer, SizeX, SizeY - Header, ColorF, Filled);
DrawRRect(SizeX / 3, 0, 2 * SizeX / 3 + Border, Footer - Border, ColorF, Filled);
DrawLine(SizeX / 2, Footer + Border, SizeX / 2, SizeY - Border - Header, ColorD, Border);
}

@Override
public void foregroundScript() {
Delta = DataValue;
if(GetCurLapNum() < 3) {
	Delta = 0;
} else if((GetCurLapNum() > 3)) {
	Delta = GetLapTime(3) - GetLapTime(1);
}

Display = FormatNumber(Delta, 2);
DataRatio = 1 - (Delta - DataMin) / (DataMax - DataMin);

Color = ColorE;
if(Delta < -0.01) {
	X = DataRatio * SizeX;
	if(X > SizeX - 2 * Border) {
		X = SizeX - 2 * Border;
	}
	Color = ColorA;
	DrawRect(SizeX / 2 + Border, Border + Footer, X, SizeY - Header - Border - 1, Color, Filled);
} else if(Delta > 0.01) {
	X = DataRatio * SizeX;
	if(X < 2 * Border) {
		X = 2 * Border;
	}
	Color = ColorB;
	DrawRect(SizeX / 2 - Border, Border + Footer, X, SizeY - Header - Border - 1, Color, Filled);
	Display = "+" + Display;
}

SetTextOutline(Transparent);
DrawText(Display, 2 * SizeX / 3, Footer - 7, Color, 52, AlignH_Right);
}

}
