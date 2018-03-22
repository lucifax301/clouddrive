cd ..\common
call mvn  install -Plocaldev -DskipTests --settings e:\settings.xml
cd ..\intapi
call mvn  install -Plocaldev -DskipTests --settings e:\settings.xml
cd ..\httpaccess
call mvn  package -Plocaldev -DskipTests --settings e:\settings.xml

xcopy /Y /S target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\coach\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\coach\target\classes\com target\httpaccess\WEB-INF\classes\com
xcopy /Y /S ..\student\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\trainorg\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\user\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\car\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\flow\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\business\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\report\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\market\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\system\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\finance\target\classes\cn target\httpaccess\WEB-INF\classes\cn
xcopy /Y /S ..\authcode\target\classes\cn target\httpaccess\WEB-INF\classes\cn

