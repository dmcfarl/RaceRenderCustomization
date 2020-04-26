package com.hobo.bob.gauge;

import com.hobo.bob.gauge.model.Frame;

public abstract class AbstractCustomGauge extends GaugeConstants {
private Frame frame;

protected float SizeX;
protected float SizeY;
protected float DataRatio;
protected float DataMin;
protected float DataMax;
protected float DataValue;

public AbstractCustomGauge(Frame frame, float sizeX2, float sizeY2) {
this.frame = frame;
this.SizeX = sizeX2;
this.SizeY = sizeY2;
}

public abstract void backgroundScript();

public abstract void foregroundScript();

protected float GetLapTime(float LapNum) {
return frame.getCurrentLapNum();
}

protected float GetCurLapTime() {
return frame.getCurrentLapTime();
}

protected float GetCurLapNum() {
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

protected float trunc(float val) {
return (int) Math.floor(val);
}

protected float round(float val, float decimals) {
return (float) (Math.round(val * Math.pow(10, decimals)) / Math.pow(10, decimals));
}

protected String substr(String value, float start, float length) {
return value.substring((int)start, (int)(start + length));
}

protected String substr(float value, float start, float length) {
return Float.toString(value).substring((int)start, (int)(start + length));
}

protected float GetDataIndex(String dataFieldName) {
return frame.getDataIndex(dataFieldName);
}

protected Float GetDataValue(float dataFieldIndex) {
return (Float)frame.getDataValue(dataFieldIndex);
}

protected void DrawRect(float x1, float y1, float x2, float y2, String color, String thickness) {

}

protected void DrawRRect(float x1, float y1, float x2, float y2, String color, String thickness) {

}

protected void DrawCircle(float x, float y, float radius, String color, String thickness) {

}

protected void DrawLine(float x1, float y1, float x2, float y2, String color, float thickness) {

}

protected void DrawLineFlat(float x1, float y1, float x2, float y2, String color, float thickness) {

}

protected void DrawLineGradientRGB(float x1, float y1, float x2, float y2, String colorA, String colorB, float thickness) {

}

protected void DrawText(String text, float x, float y, String color, float size, String alignment) {

}

protected void DrawText(String text, double x, float y, String color, float size, String alignment) {

}

protected void DrawText(String text, float x, float y, String color, float size, float alignment) {

}

protected void DrawNumber(Object val, float decimals, float x, float y, String color, float size, String alignment) {

}

protected void DrawTime(Object val, float decimals, float x, float y, String color, float size, String alignment, float compact) {

}

protected String BlendColors(String colorA, String colorB, double amount) {
return "colorBlend";
}

protected String BlendColorsRGB(String colorA, String colorB, double amount) {
return "colorBlend";
}

protected void SetTextOutline(String color) {

}

protected String FormatNumber(float value, float decimals) {
return String.format("%." + decimals + "f", value);
}
protected String chr(float trunc) {
return "";
}
}
