@echo off & title Installing PortCloser... & color fc & echo Installing... & echo. & echo. & echo DO NOT CLOSE!
echo COPYING FILES...
Rem This Folder I will not create here,because this is JRE Files.
xcopy RT "C:\PC\run_engine" /y /e /q
copy uninstall.cmd C:\PC\uninstall.cmd
xcopy Java "C:\\PC\\run_engine\\run_engine" /y /e /q

Rem Later...
