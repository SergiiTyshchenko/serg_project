@echo off

SET HYBRIS_DIR=F:\hybris_5.5.0

SET HYBRIS_PLATFORM=%HYBRIS_DIR%\hybris\bin\platform\

cd %HYBRIS_PLATFORM%

call setantenv.bat

ant runcronjob -Dcronjob=sendEmailCronJob -Dtenant=master

pause