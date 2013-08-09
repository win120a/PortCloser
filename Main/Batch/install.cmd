@echo off & title Installing PortCloser... & color fc & echo Installing... & echo. & echo. & echo DO NOT CLOSE!
echo COPYING FILES...
Rem This Folder I will not create here,because this is JRE Files.
xcopy RT "C:\PC\run_engine" /y /e /q
copy uninstall.cmd C:\PC\uninstall.cmd
xcopy Java "C:\PC\run_engine\engine" /y /e /q
xcopy Setter "C:\PC\Setter" /y /e /q
echo Registering AutoRun...
reg import Registry\install_autorun.reg
echo Are you need run the setter?
set /p c=.........[ ] (Yes/No)
if "%c%" == "Yes" C:\PC\run_engine\java.exe -jar C:\PC\Lib\Setter.jar
if "%c%" == "yes" C:\PC\run_engine\java.exe -jar C:\PC\Lib\Setter.jar
if "%c%" == "Y" C:\PC\run_engine\java.exe -jar C:\PC\Lib\Setter.jar
if "%c%" == "y" C:\PC\run_engine\java.exe -jar C:\PC\Lib\Setter.jar

if "%c%" == "No" exit
if "%c%" == "no" exit
if "%c%" == "N" exit
if "%c%" == "n" exit
