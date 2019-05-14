# RaceRenderCustomization
This repository contains two projects which facilitate the creation of videos using [RaceRender](http://racerender.com/RR3/Features.html).

## RaceRenderCustomGauges
This project contains code which can be used to create custom gauges within the RaceRender program.  This is done using the [Enhanced Object Script Editor](http://racerender.com/RR3/docs/DispObjEnhanced.html) which enables a high level of customization.

An AbstractCustomGauge class provides all of the built in functions and fields that are available in the C-like syntax of the program.  Custom gauges can extend this class.  This allows the use of an IDE such as Eclipse when working on any updates.  A provided Eclipse Formatter file can be used to ensure that the code is in the proper format to be used inside RaceRender.

Several gauges have been implemented including:
- Formula 1 style Lap List for Autocross
- Formula 1 style Split Timer for Autocross
- Formula 1 style Sector Comparison Timer for Autocross
  - Data file updates the left side
  - Data file updates the right side

Future improvements would include:
- [ ] Add Track style gauges for each of the above
- [x] Allow up to 25 laps to be displayed
- [x] Refactor fields to reduce the number of calculations performed when displaying text
- [x] Display separate minutes field
- [ ] Better spacing especially on background rounded corners

## RaceRenderFormatter
This project will format an [input data file](http://racerender.com/Developer/DataFormat.html) such that it can be used in RaceRender.  This is especially helpful for CSV files exported from [RaceChrono](https://racechrono.com/).

This formatter provides a number of quality of life improvements when formatting data:
- Converts a single large session file into separate lap files with a 15 second buffer at either end
- Converts metric units into imperial units (e.g. kph to mph)
- Formats a list of laps and sectors that can be used with the custom gauges above
- Adds a Boost (psi) column by subtracting Barometric Pressure from Manifold Pressure
- Appropriately handles time penalties such as cones in Autocross

The data file and a list of lap times are input into the main Driver.  This produces one file per lap in the same directory as the data file.  After properly aligning the video with the data file in RaceRender, a second column of a precise start can be added which will adjust the initial buffer as well as sector times.  Rerunning the driver will produce more precise files which can be refreshed in RaceRender.  Additionally, a third column denoting times when cones are hit will add a "Cones" column to the data which the custom gauges will recognize and display appropriately.

Future improvements would include:
- [ ] Add support for TrackAddict files (mainly for proper formatting to use with the custom gauges)
- [ ] Use an actual CSV Reader instead of manually parsing the files
- [ ] Consistent usage of cones and times throughout
- [ ] Allow drag-and-drop functionality with input data file (assuming that lap list is in the same folder)
