@echo off
setlocal ENABLEDELAYEDEXPANSION
title %~n0

:: Right now we're in the eclipse working directory
::echo %cd%
set ECLIPSE_PATH=%cd%
cd /D %~dp0
:: We changed the directory to the script location
::echo %cd%
set WORKSPACE_PATH=%cd%
cd /D %ECLIPSE_PATH%
::echo %cd%
:: Again back to the eclipse working dir, so wsimport works

set WS_NAME=client
set GEN_FOLDER=gen
set WS_GEN_PATH=%WS_NAME%\%GEN_FOLDER%
set BUILD_PATH=%WORKSPACE_PATH%\bin
set SRC_PATH=%WORKSPACE_PATH%\src

set BUILD_GEN_PATH=%BUILD_PATH%\%WS_GEN_PATH%
set BUILD_GEN_BACKUP_PATH=%WORKSPACE_PATH%\.backup\!BUILD_PATH:%WORKSPACE_PATH%\=!\%WS_GEN_PATH%
set SRC_GEN_PATH=%SRC_PATH%\%WS_GEN_PATH%
set SRC_GEN_BACKUP_PATH=%WORKSPACE_PATH%\.backup\!SRC_PATH:%WORKSPACE_PATH%\=!\%WS_GEN_PATH%

::BACKUP machen
rd /s /q %BUILD_GEN_BACKUP_PATH%
rd /s /q %SRC_GEN_BACKUP_PATH%
xcopy /s /i /q %BUILD_GEN_PATH% %BUILD_GEN_BACKUP_PATH%
xcopy /s /i /q %SRC_GEN_PATH% %SRC_GEN_BACKUP_PATH%
echo --- Old backed ---
echo.

rd /s /q %BUILD_GEN_PATH%
rd /s /q %SRC_GEN_PATH%
echo --- Old deleted ---
echo.

wsimport -d %BUILD_PATH% -s %SRC_PATH% -keep -p %WS_NAME%.%GEN_FOLDER% http://localhost:8080/TafelWS/tafelws?wsdl

echo --- Done ---
echo.

pause
endlocal
