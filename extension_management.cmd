@echo off

SET CONFIG_TYPE=dev
SET WORK_DIR=F:
SET HYBRIS_VERSION=_5.5
SET HYBRIS_DIR=%WORK_DIR%\hybris
SET HYBRIS_CONFIG=%HYBRIS_DIR%\hybris\config
SET HYBRIS_EXTENSIONS=%HYBRIS_DIR%\hybris\bin\custom
SET CUSTOM_CONFIG=%HYBRIS_CONFIG%\%CONFIG_TYPE%
SET EXTENSIONS=%HYBRIS_DIR%\extensions

SET localextensionsfile=localextensions.xml
SET localpropertiesfile=local.properties
REM SET localextensions="%HYBRIS_CONFIG%\%localextensionsfile%"
REM SET localproperties="%HYBRIS_CONFIG%\%localpropertiesfile%"
SET localextensions="%CUSTOM_CONFIG%\%localextensionsfile%"
SET localproperties="%CUSTOM_CONFIG%\%localpropertiesfile%"

if exist %localextensions% (
copy %localextensions% %localextensions%_backup_%DATE%
::del /F %localextensions%
echo %localextensions% backed-up
) else (
echo ERROR: %localextensions% DOES NOT EXIST
)

if exist %localproperties% (
copy %localproperties% %localproperties%_backup_%DATE%
::del /F %localproperties%
echo %localproperties% backed-up
) else (
echo ERROR: %localproperties% DOES NOT EXIST
)

REM ------/H      Creates a hard link instead of a symbolic link.
REM Syntax
      REM MKLINK [[/D] | [/H] | [/J]] Link Target

REM Key:
   REM /D     Create a Directory symbolic link. (default is file)
   REM /H     Create a hard link instead of a symbolic link.
   REM /J     Create a Directory Junction.
   REM Link   The new symbolic link name.
   REM Target The path (relative or absolute) that the new link refers to.
REM =======================================
REM fsutil hardlink create Link Target
REM for xp
REM fsutil hardlink create "C:\Distr\Installer-2.exe" "C:\Distr\Installer.exe"
REM for all win after xpREM %WORK_DIR%\MKLINK /H "%HYBRIS_CONFIG%\%localextensionsfile%" "%localextensions%"
fsutil hardlink create "%HYBRIS_CONFIG%\%localextensionsfile%" "%localextensions%"
echo hard link created to point to %localextensions%
REM %WORK_DIR%\MKLINK /H "%HYBRIS_CONFIG%\%localpropertiesfile%" "%localproperties%"
fsutil hardlink create  "%HYBRIS_CONFIG%\%localpropertiesfile%" "%localproperties%"
echo hard link created to point to %localproperties%

if exist "%HYBRIS_EXTENSIONS%" (
RMDIR /Q /S "%HYBRIS_EXTENSIONS%"
)
REM for all win after xp
REM %WORK_DIR%\MKLINK /J "%HYBRIS_EXTENSIONS%" "%EXTENSIONS%"
REM win xp - download linkd.exe - linkd Link Target
%HYBRIS_DIR%\linkd "%HYBRIS_EXTENSIONS%" "%EXTENSIONS%"

REM pause