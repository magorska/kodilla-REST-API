call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo Cannot run runcrud.bat

:browser
chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Cannot run browser.

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.