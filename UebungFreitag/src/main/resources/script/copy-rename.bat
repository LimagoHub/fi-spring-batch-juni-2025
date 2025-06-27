@echo off
set "inbox=..\inbox"
set "input=..\input"
for %%f in (%inbox%\*.csv) do (
    copy "%%f" "%input%\%%~nf-data%%~xf"
)