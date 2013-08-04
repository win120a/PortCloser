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
if "%c%" == "Yes" 
