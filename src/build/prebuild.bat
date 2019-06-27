@cd /d %~dp0
@cd ..
@set PROJECT_PATH=%cd%
@cd /d %~dp0

@cd /d %PROJECT_PATH%\third_party\management-pack-lib
jar xvf com.vmware.vrops.mpe.ui_1.0.0.4354673.jar resources\lib\xmltask.jar resources\lib\ant-contrib-1.0b3.jar
@move .\resources\lib\*.jar .
@rmdir /s /q .\resources
@del /f /q com.vmware.vrops.mpe.ui_1.0.0.4354673.jar

@cd /d %PROJECT_PATH%\third_party\plugin-lib
jar xvf com.vmware.vide.sdk.ops60_6.3.0.4354673.jar SDK\samples\RESTfulApiAdapter\lib\commons-logging-1.2.jar SDK\samples\RESTfulApiAdapter\lib\jackson-annotations-2.4.0.jar SDK\samples\RESTfulApiAdapter\lib\jackson-core-2.4.2.jar SDK\samples\RESTfulApiAdapter\lib\jackson-databind-2.4.2.jar SDK\samples\RESTfulApiAdapter\lib\log4j-1.2.17.jar SDK\samples\RESTfulApiAdapter\lib\spring-beans-4.3.0.RELEASE.jar SDK\samples\RESTfulApiAdapter\lib\spring-core-4.3.0.RELEASE.jar SDK\samples\RESTfulApiAdapter\lib\spring-web-4.3.0.RELEASE.jar SDK\samples\RESTfulApiAdapter\lib\vcops-common.jar
@move .\SDK\samples\RESTfulApiAdapter\lib\*.jar .
@rmdir /s /q .\SDK
@del /f /q com.vmware.vide.sdk.ops60_6.3.0.4354673.jar