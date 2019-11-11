@echo off
set /p sessionFile="Enter filepath for session file: "
set /p lapsFile="Enter filepath for session file: "

java -jar RaceRenderFormatter.jar %sessionFile% %lapsFile% true

pause