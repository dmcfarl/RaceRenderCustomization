#!/bin/bash
read -p 'Enter filepath for session file: ' sessionFile
read -p 'Enter filepath for session file: ' lapsFile

java -jar RaceRenderFormatter.jar $sessionFile $lapsFile true