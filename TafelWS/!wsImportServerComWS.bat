@echo off
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

set WS_NAME=serverCom
set BUILD_PATH=%WORKSPACE_PATH%\build\classes\%WS_NAME%\gen
set BUILD_BACKUP_PATH=%WORKSPACE_PATH%\.backup\build\%WS_NAME%\gen
set SRC_PATH=%WORKSPACE_PATH%\src\%WS_NAME%\gen
set SRC_BACKUP_PATH=%WORKSPACE_PATH%\.backup\src\%WS_NAME%\gen

::BACKUP machen
rd /s /q %BUILD_BACKUP_PATH%
rd /s /q %SRC_BACKUP_PATH%
xcopy /s /i /q %BUILD_PATH% %BUILD_BACKUP_PATH%
xcopy /s /i /q %SRC_PATH% %SRC_BACKUP_PATH%
echo --- Old backed ---
echo.

rd /s /q %BUILD_PATH%
rd /s /q %SRC_PATH%
echo --- Old deleted ---
echo.

wsimport -d %WORKSPACE_PATH%\build\classes -s %WORKSPACE_PATH%\src -keep -p %WS_NAME%.gen http://localhost:8080/TafelWS/serverws?wsdl

echo --- Done ---
echo.

pause
