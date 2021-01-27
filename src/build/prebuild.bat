@cd /d %~dp0
@cd ..
@set PROJECT_PATH=%cd%
@cd /d %~dp0

@cd /d %PROJECT_PATH%\third_party\management-pack-lib
jar xvf com.vmware.vrops.mpe.ui_1.0.0.4354673.jar resources\lib\xmltask.jar resources\lib\ant-contrib-1.0b3.jar
@move .\resources\lib\*.jar .
@rmdir /s /q .\resources
@del /f /q com.vmware.vrops.mpe.ui_1.0.0.4354673.jar