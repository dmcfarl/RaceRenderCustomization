package com.hobo.bob.gauge;

import com.hobo.bob.gauge.model.Frame;

public abstract class AbstractCustomGauge extends GaugeConstants {
private Frame frame;

protected int SizeX;
protected int SizeY;

public AbstractCustomGauge(Frame frame, int sizeX, int sizeY) {
this.frame = frame;
this.SizeX = sizeX;
this.SizeY = sizeY;
}

public abstract void backgroundScript();

public abstract void foregroundScript();

protected float GetLapTime(int LapNum) {
return frame.getCurrentLapNum();
}

protected float GetCurLapTime() {
return frame.getCurrentLapTime();
}

protected int GetCurLapNum() {
return frame.getCurrentLapNum();
}

protected float abs(float val) {
return Math.abs(val);
}

protected float ceil(Object val) {
return (float) Math.ceil((Float) val);
}

protected float floor(float val) {
return (float) Math.floor(val);
}

protected String substr(String value, int start, int length) {
return value.substring(start, start + length);
}

protected String substr(float value, int start, int length) {
return Float.toString(value).substring(start, start + length);
}

protected int GetDataIndex(String dataFieldName) {
return frame.getDataIndex(dataFieldName);
}

protected Object GetDataValue(int dataFieldIndex) {
return frame.getDataValue(dataFieldIndex);
}

protected void DrawRect(int x1, int y1, int x2, int y2, String color, String thickness) {

}

protected void DrawCircle(int x, int y, int radius, String color, String thickness) {

}

protected void DrawLine(int x1, int y1, int x2, int y2, String color, int thickness) {

}

protected void DrawLineFlat(int x1, int y1, int x2, int y2, String color, int thickness) {

}

protected void DrawLineGradientRGB(int x1, int y1, int x2, int y2, String colorA, String colorB, int thickness) {

}

protected void DrawText(String text, int x, int y, String color, int size, String alignment) {

}

protected void DrawText(String text, double x, int y, String color, int size, String alignment) {

}

protected void DrawText(String text, int x, int y, String color, int size, int alignment) {

}

protected void DrawNumber(Object val, int decimals, int x, int y, String color, int size, String alignment) {

}

protected void DrawTime(Object val, int decimals, int x, int y, String color, int size, String alignment, int compact) {

}

protected String BlendColorsRGB(String colorA, String colorB, double amount) {
return "colorBlend";
}

protected void SetTextOutline(String color) {

}

protected String FormatNumber(float value, int decimals) {
return String.format("%." + decimals + "f", value);
}
}
