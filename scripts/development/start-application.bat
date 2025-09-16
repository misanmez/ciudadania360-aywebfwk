@echo off
echo ========================================
echo   CIUDADANIA360 - AYWEBFWK
echo   INICIO RAPIDO
echo ========================================
echo.

echo Verificando que Oracle este ejecutandose...
netstat -an | findstr :1521 >nul
if %errorlevel% neq 0 (
    echo ADVERTENCIA: Oracle no esta ejecutandose en el puerto 1521
    echo Asegurate de que Oracle este ejecutandose antes de continuar
    echo.
)

echo Iniciando aplicacion...
echo URL: http://localhost:8080/ciudadania360
echo.

cd ciudadania360-backend
mvn spring-boot:run

pause
