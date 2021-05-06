package com.hobo.bob.gauge;

import com.hobo.bob.gauge.model.Frame;

public abstract class AbstractCustomGauge {
private Frame frame;

protected float DataRatio;
/**
 * Minimum data value, as set by the user or as measured (if automatic)
 */
protected float DataMin;
/**
 * Set to true if DataMin was the result of automatic measurement, or false if
 * DataMin came from the user
 */
protected float DataMinAuto;
/**
 * Maximum data value, as set by the user or as measured (if automatic)
 */
protected float DataMax;
/**
 * Set to true if DataMax was the result of automatic measurement, or false if
 * DataMax came from the user
 */
protected float DataMaxAuto;
/**
 * Equal to DataMax - DataMin
 */
protected float DataRange;
/**
 * Trigger activation level set by the user (when enabled; controls IsTriggered)
 */
protected float DataTrigger;
/**
 * Like DataFactor, but for the DataTrigger value (when enabled)
 */
protected float DataTriggerFactor;

/**
 * Data Values. Foreground Script Only.
 */
protected float DataValue;
/**
 * Data Values. Foreground Script Only.
 */
protected float DataFactor;
/**
 * Data Values. Foreground Script Only.
 */
protected float IsTriggered;
/**
 * Data Values. Foreground Script Only.
 */
protected float SampleTime;
/**
 * Data Values. Foreground Script Only.
 */
protected float FrameInterval;
/**
 * Data Values. Foreground Script Only.
 */
protected float DisplayTime;

/**
 * Rendering canvas width
 */
protected float SizeX;
/**
 * Rendering canvas height
 */
protected float SizeY;
/**
 * Horizontal center coordinate in canvas
 */
protected float CenterX;
/**
 * Vertical center coordinate in canvas
 */
protected float CenterY;

/**
 * 1st user-controlled color value (when enabled)
 */
protected String ColorA = "colorA";
/**
 * 2nd user-controlled color value (when enabled)
 */
protected String ColorB = "colorB";
/**
 * 3rd user-controlled color value (when enabled)
 */
protected String ColorC = "colorC";
/**
 * 4th user-controlled color value (when enabled)
 */
protected String ColorD = "colorD";
/**
 * 5th user-controlled color value (when enabled)
 */
protected String ColorE = "colorE";
/**
 * 6th user-controlled color value (when enabled)
 */
protected String ColorF = "colorF";
/**
 * 7th user-controlled color value (when enabled)
 */
protected String ColorG = "colorG";
/**
 * 8th user-controlled color value (when enabled)
 */
protected String ColorH = "colorH";

/**
 * Current project's preferred speed unit ("MPH", "km/h", "knots", "m/s")
 */
protected String SpeedUnit;
/**
 * Current input's name / label
 */
protected String InputName;
/**
 * Location name, based on the input's starting coordinates (if available) Note:
 * The functionality of this may change in the future
 */
protected String LocName;

/**
 * Draws a solid shape when specified as the Thickness parameter for Circles,
 * Rectangles, and Polygons
 */
protected float Filled = Integer.MAX_VALUE;
protected String White = "white";
protected String Gray = "gray";
protected String Black = "black";
protected String Red = "red";
protected String Green = "green";
protected String Blue = "blue";
protected String Yellow = "yellow";
protected String Orange = "orange";
protected String Brown = "brown";
protected String Cyan = "cyan";
protected String Magenta = "magenta";
/**
 * special case used for SetTextOutline()
 */
protected String Transparent = "transparent";
/**
 * same as Transparent
 */
protected String Invisible = "transparent";

/**
 * Text Alignment: (for use with DrawNumber(), DrawTime(), DrawChar(), and
 * DrawText()) Horizontal align left
 */
protected float AlignH_Left = 0;
/**
 * Text Alignment: (for use with DrawNumber(), DrawTime(), DrawChar(), and
 * DrawText()) Horizontal align center
 */
protected float AlignH_Center = 1;
/**
 * Text Alignment: (for use with DrawNumber(), DrawTime(), DrawChar(), and
 * DrawText()) Horizontal align right
 */
protected float AlignH_Right = 2;

/**
 * Data Field Type: (for use with GetDataValue()) X Position (Longitude if GPS
 * coordinates)
 */
protected float DFT_PosX;
/**
 * Data Field Type: (for use with GetDataValue()) Y Position (Latitude if GPS
 * coordinates)
 */
protected float DFT_PosY;
/**
 * Data Field Type: (for use with GetDataValue()) Z Position (Altitude, in
 * meters)
 */
protected float DFT_PosZ;
/**
 * Data Field Type: (for use with GetDataValue()) Speed (unit depends on user
 * setting)
 */
protected float DFT_Speed;
/**
 * Data Field Type: (for use with GetDataValue()) Heading (0.0 - 359.9 degrees)
 */
protected float DFT_Heading;
/**
 * Data Field Type: (for use with GetDataValue()) X Acceleration (G's)
 */
protected float DFT_GForceX;
/**
 * Data Field Type: (for use with GetDataValue()) Y Acceleration (G's)
 */
protected float DFT_GForceY;
/**
 * Data Field Type: (for use with GetDataValue()) Throttle Position (typically
 * 0-100%; depends on input data file)
 */
protected float DFT_Throttle;
/**
 * Data Field Type: (for use with GetDataValue()) Brake Switch or Position
 * (depends on input data file)
 */
protected float DFT_Brake;
/**
 * Data Field Type: (for use with GetDataValue()) Engine RPM
 */
protected float DFT_RPM;
/**
 * Data Field Type: (for use with GetDataValue()) Transmission Gear number
 */
protected float DFT_Gear;
/**
 * Data Field Type: (for use with GetDataValue()) Air/Fuel Ratio or Lambda
 * (depends on input data file)
 */
protected float DFT_AirFuel;
/**
 * Data Field Type: (for use with GetDataValue()) Engine Power Output (typically
 * HP or kW; depends on input data file)
 */
protected float DFT_Power;
/**
 * Data Field Type: (for use with GetDataValue()) Engine Torque Output
 * (typically ft-lbs or Nm; depends on input data file)
 */
protected float DFT_Torque;

public AbstractCustomGauge(Frame frame, float sizeX2, float sizeY2) {
this.frame = frame;
this.SizeX = sizeX2;
this.SizeY = sizeY2;
}

public abstract void backgroundScript();

public abstract void foregroundScript();

/**
 * Sets the outline color used for for DrawNumber(), DrawTime(), DrawChar(), and
 * DrawText(). Call SetTextOutline(Transparent) to disable it.
 */
protected void SetTextOutline(String color) {
}

/**
 * Draws Value as a number at X, Y
 * 
 * @param val
 * @param decimals
 * @param x
 * @param y
 * @param color
 * @param size
 * @param alignment
 */
protected void DrawNumber(Object val, float decimals, float x, float y, String color, float size, float alignment) {
}

/**
 * Draws Value (number of seconds) as a formatted time at X, Y Compact 0 =
 * Always Show Hours, 1 = Minutes, 2 = Seconds
 * 
 * @param val
 * @param decimals
 * @param x
 * @param y
 * @param color
 * @param size
 * @param alignment
 * @param compact
 */
protected void DrawTime(Object val, float decimals, float x, float y, String color, float size, float alignment,
		float compact) {
}

/**
 * Draws a character at X, Y. Note: single character only, not a string
 * 
 * @param text
 * @param x
 * @param y
 * @param color
 * @param size
 * @param alignment
 */
protected void DrawChar(char text, float x, float y, String color, float size, float alignment) {
}

/**
 * Draws a text string at X, Y
 * 
 * @param text
 * @param x
 * @param y
 * @param color
 * @param size
 * @param alignment
 */
protected void DrawText(String text, float x, float y, String color, float size, float alignment) {
}

/**
 * Draws a dot at X, Y
 * 
 * @param x
 * @param y
 * @param color
 * @param size
 */
protected void DrawDot(float x, float y, String color, float size) {
}

/**
 * Draws a dot at X, Y with gradient coloring
 * 
 * @param x
 * @param y
 * @param colorA
 * @param colorB
 * @param size
 */
protected void DrawDotGradient(float x, float y, String colorA, String colorB, float size) {
}

/**
 * Draws a dot at X, Y with gradient coloring (RGB blending for brighter colors)
 * 
 * @param x
 * @param y
 * @param colorA
 * @param colorB
 * @param size
 */
protected void DrawDotGradientRGB(float x, float y, String colorA, String colorB, float size) {
}

/**
 * Draws a filled square at X, Y
 * 
 * @param x
 * @param y
 * @param color
 * @param size
 */
protected void DrawSquare(float x, float y, String color, float size) {
}

/**
 * Draws a line from X1, Y1 to X2, Y2
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 * @param thickness
 */
protected void DrawLine(float x1, float y1, float x2, float y2, String color, float thickness) {
}

/**
 * Draws a line from X1, Y1 to X2, Y2, with gradient coloring
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param colorA
 * @param colorB
 * @param thickness
 */
protected void DrawLineGradient(float x1, float y1, float x2, float y2, String colorA, String colorB, float thickness) {
}

/**
 * Draws a line from X1, Y1 to X2, Y2, with gradient coloring (RGB blending for
 * brighter colors)
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param colorA
 * @param colorB
 * @param thickness
 */
protected void DrawLineGradientRGB(float x1, float y1, float x2, float y2, String colorA, String colorB,
		float thickness) {
}

/**
 * Draws a line from X1, Y1 to X2, Y2, with flat ends
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 * @param thickness
 */
protected void DrawLineFlat(float x1, float y1, float x2, float y2, String color, float thickness) {
}

/**
 * Draws a circle with a center at X, Y. Thickness may be set to Filled
 * 
 * @param x
 * @param y
 * @param radius
 * @param color
 * @param thickness
 */
protected void DrawCircle(float x, float y, float radius, String color, float thickness) {
}

/**
 * Draws a quadratic Bezier curve from X1,Y1 to X2,Y2 based on specified control
 * point, with optional thickness tapering and gradient-coloring. ProgressLimit
 * should be between 0.0 and 1.0; Use 1.0 to draw the entire curve.
 * 
 * @param x1
 * @param y1
 * @param controlX
 * @param controlY
 * @param x2
 * @param y2
 * @param progressLimit
 * @param colorA
 * @param colorB
 * @param thicknessA
 * @param thicknessB
 */
protected void DrawCurve(float x1, float y1, float controlX, float controlY, float x2, float y2, float progressLimit,
		String colorA, String colorB, float thicknessA, float thicknessB) {
}

/**
 * Draws a quadratic Bezier curve from X1,Y1 to X2,Y2 based on specified control
 * point, with optional thickness tapering and gradient-coloring (RGB blending
 * for brighter colors). ProgressLimit should be between 0.0 and 1.0; Use 1.0 to
 * draw the entire curve.
 * 
 * @param x1
 * @param y1
 * @param controlX
 * @param controlY
 * @param x2
 * @param y2
 * @param progressLimit
 * @param colorA
 * @param colorB
 * @param thicknessA
 * @param thicknessB
 */
protected void DrawCurveRGB(float x1, float y1, float controlX, float controlY, float x2, float y2, float progressLimit,
		String colorA, String colorB, float thicknessA, float thicknessB) {
}

/**
 * Draws a rectangle from X1, Y1 to X2, Y2. Thickness may be set to Filled
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 * @param thickness
 */
protected void DrawRect(float x1, float y1, float x2, float y2, String color, float thickness) {
}

/**
 * Draws a rounded-rectangle from X1, Y1 to X2, Y2. Thickness may be set to
 * Filled
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 * @param thickness
 */
protected void DrawRRect(float x1, float y1, float x2, float y2, String color, float thickness) {
}

/**
 * Draws an oval-shaped rectangle from X1, Y1 to X2, Y2. Thickness may be set to
 * Filled
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param color
 * @param thickness
 */
protected void DrawORect(float x1, float y1, float x2, float y2, String color, float thickness) {
}

/**
 * Draws a 3-point polygon from X1, Y1 to X2, Y2 to X3, Y3. Thickness may be set
 * to Filled
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param x3
 * @param y3
 * @param color
 * @param thickness
 */
protected void DrawPoly3(float x1, float y1, float x2, float y2, float x3, float y3, String color, float thickness) {
}

/**
 * Draws a 4-point polygon from X1, Y1 to X2, Y2 to X3, Y3 to X4, Y4. Thickness
 * may be set to Filled
 * 
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @param x3
 * @param y3
 * @param x4
 * @param y4
 * @param color
 * @param thickness
 */
protected void DrawPoly4(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, String color,
		float thickness) {
}

/**
 * Returns color blended from Color1 to Color2 by Amount (0.0 to 1.0)
 * 
 * @param colorA
 * @param colorB
 * @param amount
 * @return
 */
protected String BlendColors(String colorA, String colorB, double amount) {
return "colorBlend";
}

/**
 * Returns color RGB-blended from Color1 to Color2 by Amount (0.0 to 1.0). This
 * tends to result in brighter colors than the BlendColors() function.
 * 
 * @param colorA
 * @param colorB
 * @param amount
 * @return
 */
protected String BlendColorsRGB(String colorA, String colorB, double amount) {
return "colorBlend";
}

/**
 * Gets current data field index for the given data channel name (text string).
 * These indexes are dynamic, based on the input data file.
 * <p>
 * Important: This can reduce performance if used in the Foreground script.
 * Instead, use it to set a variable in the Background script, and then use that
 * variable with GetDataValue() in the Foreground script.
 * 
 * @param dataFieldName
 * @return
 */
protected float GetDataIndex(String dataFieldName) {
return frame.getDataIndex(dataFieldName);
}

/**
 * Gets current data value for the specified data channel. See DFT_* values for
 * available options, or obtain an index for a specific named channel using
 * GetDataIndex().
 * 
 * @param dataFieldIndex
 * @return
 */
protected Float GetDataValue(float dataFieldIndex) {
return (Float) frame.getDataValue(dataFieldIndex);
}

/**
 * Gets the completed lap time (in seconds) for the given lap number
 * 
 * @param LapNum
 * @return
 */
protected float GetLapTime(float LapNum) {
return frame.getCurrentLapNum();
}

/**
 * Gets the current lap number
 * 
 * @return
 */
protected float GetCurLapNum() {
return frame.getCurrentLapNum();
}

/**
 * Gets the current running lap time (in seconds)
 * 
 * @return
 */
protected float GetCurLapTime() {
return frame.getCurrentLapTime();
}

/**
 * Gets the overall best lap number
 * 
 * @return
 */
protected float GetBestLapNum() {
return frame.getCurrentLapNum();
}

/**
 * Gets the overall best lap time (in seconds)
 */
protected float GetBestLapTime() {
return frame.getCurrentLapTime();
}

/**
 * Gets the current best lap number
 * 
 * @return
 */
protected float GetCurBestLapNum() {
return frame.getCurrentLapNum();
}

/**
 * Gets the current best lap time (in seconds)
 * 
 * @return
 */
protected float GetCurBestLapTime() {
return frame.getCurrentLapTime();
}

/**
 * Gets the best lap number as of the previous lap
 * 
 * @return
 */
protected float GetPrevBestLapNum() {
return frame.getCurrentLapNum();
}

/**
 * Gets the best lap time (in seconds) as of the previous lap
 * 
 * @return
 */
protected float GetPrevBestLapTime() {
return frame.getCurrentLapTime();
}

/**
 * Returns a string from a numeric value, shown to the specified number of
 * decimal places.
 * 
 * @param value
 * @param decimals
 * @return
 */
protected String FormatNumber(float value, float decimals) {
return String.format("%." + decimals + "f", value);
}

/**
 * Returns a directional heading string from a numeric heading value (0-360
 * degrees).
 * 
 * @param value
 * @return
 */
protected String FormatHeading(float value) {
return "N";
}

/**
 * Returns the number of characters in a string.
 * 
 * @param value
 * @return
 */
protected float strlen(String value) {
return value.length();
}

/**
 * Returns a substring from within a string, based on StartPos (index of first
 * character; starts at zero) and Length (number of characters). Returns a blank
 * string if StartPos is invalid. Returns all characters after StartPos if
 * Length is <0 or larger than input string length.
 * 
 * @param value
 * @param start
 * @param length
 * @return
 */
protected String substr(String value, float start, float length) {
return value.substring((int) start, (int) (start + length));
}

/**
 * Converts a string to uppercase.
 * 
 * @param value
 * @return
 */
protected String ToUpper(String value) {
return value.toUpperCase();
}

/**
 * Converts a string to lowercase.
 * 
 * @param value
 * @return
 */
protected String ToLower(String value) {
return value.toLowerCase();
}

/**
 * Trims whitespace off of the start and end of a string.
 * 
 * @param value
 * @return
 */
protected String trim(String value) {
return value.trim();
}

/**
 * Converts an ASCII character code value into a text character.
 * 
 * @param value
 * @return
 */
protected String chr(float value) {
return "" + ((char) value);
}

/**
 * Converts a text character into its an ASCII character code value.
 * 
 * @param value
 * @return
 */
protected float asc(String value) {
return (float) value.charAt(0);
}

/**
 * Alternates between true and false (1 and 0 integers) based on Interval
 * (seconds); useful for making things flash.
 * 
 * @param interval
 * @return
 */
protected float Strobe(float interval) {
return 0;
}

/**
 * Writes one or more parameter values (numeric or string) to the Output
 * Messages log
 * 
 * @param value
 */
protected void Print(String... value) {
}

/**
 * Absolute: Removes negative sign, if applicable (-1.5 becomes 1.5, 1.7 remains
 * 1.7)
 * 
 * @param value
 * @return
 */
protected float abs(float value) {
return Math.abs(value);
}

/**
 * Ceiling: Rounds Value up to the next higher integer (1.3 becomes 2.0, -1.3
 * becomes -1.0)
 * 
 * @param value
 * @return
 */
protected float ceil(Object value) {
return (float) Math.ceil((Float) value);
}

/**
 * Floor: Rounds Value down to the lower integer (1.7 becomes 1.0, -1.7 becomes
 * -2.0)
 * 
 * @param value
 * @return
 */
protected float floor(float value) {
return (float) Math.floor(value);
}

/**
 * Maximum: Returns the greater of ValueA and ValueB
 * 
 * @param valueA
 * @param valueB
 * @return
 */
protected float max(float valueA, float valueB) {
return valueA;
}

/**
 * Minimum: Returns the lesser of ValueA and ValueB
 * 
 * @param valueA
 * @param valueB
 * @return
 */
protected float min(float valueA, float valueB) {
return valueA;
}

/**
 * Power: Raise Base to the specified Exponent
 * 
 * @param base
 * @param exponent
 * @return
 */
protected float pow(float base, float exponent) {
return (float) Math.pow(base, exponent);
}

/**
 * Round: Rounds Value to number of decimal places; if 0, then the nearest
 * integer (1.6 becomes 2.0, 1.4 becomes 1.0)
 * 
 * @param value
 * @param decimals
 * @return
 */
protected float round(float value, float decimals) {
return (float) (Math.round(value * Math.pow(10, decimals)) / Math.pow(10, decimals));
}

/**
 * Square Root
 * 
 * @param value
 * @return
 */
protected float sqrt(float value) {
return (float) Math.sqrt(value);
}

/**
 * Truncate: Discards the decimal component of Value to make it an integer (1.7
 * becomes 1.0, -1.7 becomes -1.0)
 * 
 * @param value
 * @return
 */
protected float trunc(float value) {
return (int) Math.floor(value);
}

/**
 * Sine
 * 
 * @param degrees
 * @return
 */
protected float sin(float degrees) {
return (float) Math.sin(degrees);
}

/**
 * Cosine
 * 
 * @param degrees
 * @return
 */
protected float cos(float degrees) {
return (float) Math.cos(degrees);
}

/**
 * Tangent
 * 
 * @param degrees
 * @return
 */
protected float tan(float degrees) {
return (float) Math.tan(degrees);
}

/**
 * Arc sine of Value, in degrees
 * 
 * @param value
 * @return
 */
protected float asin(float value) {
return (float) Math.asin(value);
}

/**
 * Arc cosine of Value, in degree
 * 
 * @param value
 * @return
 */
protected float acos(float value) {
return (float) Math.acos(value);
}

/**
 * Arc tangent of Value, in degrees
 * 
 * @param value
 * @return
 */
protected float atan(float value) {
return (float) Math.atan(value);
}

/**
 * Arc tangent of Y/X, in degrees
 * 
 * @param value
 * @return
 */
protected float atan2(float x, float y) {
return (float) Math.atan(y / x);
}

/**
 * Hypotenuse of triangle with sides of X and Y length. Square root of (X^2 +
 * Y^2)
 * 
 * @param x
 * @param y
 * @return
 */
protected float hypot(float x, float y) {
return 0;
}
}
