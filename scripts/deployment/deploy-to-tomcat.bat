@echo off
echo ========================================
echo   CIUDADANIA360 - AYWEBFWK
echo   DESPLIEGUE EN TOMCAT
echo ========================================
echo.

REM Verificar que el WAR existe
if not exist "ciudadania360-backend\target\ciudadania360.war" (
    echo ERROR: No se encontro el archivo WAR
    echo Ejecuta 'mvn clean package' primero
    pause
    exit /b 1
)

echo Compilando proyecto...
mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo ERROR: Fallo al compilar el proyecto
    pause
    exit /b 1
)

echo.
echo Copiando WAR a Tomcat...
if not exist "%TOMCAT_HOME%\webapps" (
    echo ERROR: Variable TOMCAT_HOME no esta configurada
    echo Configura TOMCAT_HOME o copia manualmente el WAR
    pause
    exit /b 1
)

copy "ciudadania360-backend\target\ciudadania360.war" "%TOMCAT_HOME%\webapps\"
if %errorlevel% neq 0 (
    echo ERROR: No se pudo copiar el WAR a Tomcat
    pause
    exit /b 1
)

echo.
echo ========================================
echo   DESPLIEGUE COMPLETADO
echo   URL: http://localhost:8080/ciudadania360
echo ========================================
echo.

pause
