@echo off
:: Usage: runTests.bat <feature_file> <tag> <browser> <runner_class>

set FEATURE=%1
set TAG=%2
set BROWSER=%3
set RUNNER=%4

echo Running tests for:
echo Feature File: %FEATURE%
echo Tag: %TAG%
echo Browser: %BROWSER%
echo Runner: %RUNNER%

mvn clean verify ^
  -Dcucumber.features="src/test/java/com/qa/features/%FEATURE%" ^
  -Dcucumber.filter.tags="%TAG%" ^
  -Dbrowser=%BROWSER% ^
  -Dtest=%RUNNER%