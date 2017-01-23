@echo off

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


wsimport -d %WORKSPACE_PATH%\build\classes -s %WORKSPACE_PATH%\src -keep -p client.gen http://localhost:8080/TafelWS/tafelws?wsdl

pause
