@echo off
title %~n0

cd /D %~dp0
set WORKPATH=%cd%
set FILENAME=tafelAdressen
set FILEPATH=%WORKPATH%\%FILENAME%

echo.

if exist %FILEPATH% (
    echo Found file %FILEPATH%
    echo.
    echo ...Creating files
    echo.

    setlocal ENABLEDELAYEDEXPANSION
    for /l %%x in (1, 1, 6) do (
        set CURRENTFILE="%FILEPATH%%%x"
        echo !CURRENTFILE!
        if exist !CURRENTFILE! (
            del !CURRENTFILE!       
        )
        call :createFile !CURRENTFILE!
        
        for /f "eol=; tokens=1,2* delims=, " %%i in (%FILEPATH%) do (
            if NOT %%x == %%i (
                set changedws=%%j
                set changedws=!changedws:tafelws?=serverws?!
                echo %%i !changedws! >>!CURRENTFILE!
            )
        )
    )
    endlocal

    echo.
    echo Done!
    echo.
) else (
    echo Error: tafelAdressen existiert nicht. Bitte Erstellen!
    echo.
)

goto :end


:createFile
setlocal
set CREATEDFILE=%~1
::echo %CREATEDFILE%
echo. 2>%CREATEDFILE%
endlocal
exit /B 0



:end
exit /B 0
