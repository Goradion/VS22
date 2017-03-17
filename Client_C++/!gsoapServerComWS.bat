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

set WS_NAME=serverCom
::set BUILD_PATH=%WORKSPACE_PATH%\bin
set SRC_PATH=%WORKSPACE_PATH%\src
set NEEDED_PATH=%WORKSPACE_PATH%\needed

::set BUILD_GEN_PATH=%BUILD_PATH%\%WS_NAME%
::set BUILD_GEN_BACKUP_PATH=%WORKSPACE_PATH%\.backup\!BUILD_PATH:%WORKSPACE_PATH%\=!\%WS_NAME%
set SRC_GEN_PATH=%SRC_PATH%\%WS_NAME%
set SRC_GEN_BACKUP_PATH=%WORKSPACE_PATH%\.backup\!SRC_PATH:%WORKSPACE_PATH%\=!\%WS_NAME%

set HEADER_PATH=%SRC_GEN_PATH%\%WS_NAME%.h
set HEADER_BACKUP_PATH=%WORKSPACE_PATH%\.backup\!SRC_PATH:%WORKSPACE_PATH%\=!\%WS_NAME%\%WS_NAME%.h

::BACKUP machen
::rd /s /q %BUILD_GEN_BACKUP_PATH%
rd /s /q %SRC_GEN_BACKUP_PATH%
::xcopy /s /i /q %BUILD_GEN_PATH% %BUILD_GEN_BACKUP_PATH%
xcopy /s /i /q %SRC_GEN_PATH% %SRC_GEN_BACKUP_PATH%
echo --- Old backed ---
echo.

::rd /s /q %BUILD_GEN_PATH%
rd /s /q %SRC_GEN_PATH%
echo --- Old deleted ---
echo.

mkdir %SRC_GEN_PATH%

xcopy /s /i /q %NEEDED_PATH% %SRC_GEN_PATH%


wsdl2h -s -o %HEADER_PATH% http://localhost:8080/TafelWS/serverws?wsdl

soapcpp2 -d %SRC_GEN_PATH% -j -C %HEADER_PATH%

::wsimport -d %BUILD_PATH% -s %SRC_PATH% -keep -p %WS_NAME%.%GEN_FOLDER% http://localhost:8080/TafelWS/serverws?wsdl

echo --- Done ---
echo.

pause
endlocal
