@echo off
echo ========================================
echo   CIUDADANIA360 - AYWEBFWK
echo   Configuracion Completa con PostgreSQL
echo ========================================
echo.

echo Verificando si PostgreSQL esta en el PATH...
where psql >nul 2>nul
if %errorlevel% neq 0 (
    echo PostgreSQL no encontrado en el PATH. Intentando añadir ubicaciones comunes...
    set PATH=%PATH%;C:\Program Files\PostgreSQL\15\bin
    set PATH=%PATH%;C:\Program Files\PostgreSQL\14\bin
    set PATH=%PATH%;C:\Program Files\PostgreSQL\13\bin
    where psql >nul 2>nul
    if %errorlevel% neq 0 (
        echo ERROR: PostgreSQL no se encuentra. Por favor, instalelo o asegurese de que este en el PATH.
        pause
        exit /b 1
    )
)
echo PostgreSQL encontrado.

echo.
echo PASO 1: Configurando base de datos PostgreSQL...
echo Conectando como postgres para configurar el sistema completo...
psql -h localhost -p 5432 -U postgres -f scripts\database\setup-postgresql-complete.sql
if %errorlevel% neq 0 (
    echo ERROR: Fallo al ejecutar setup-postgresql-complete.sql
    echo Asegurese de que PostgreSQL este ejecutandose y las credenciales sean correctas.
    echo Credenciales por defecto: postgres/Aqmdla04@localhost:5432
    pause
    exit /b 1
)
echo Base de datos PostgreSQL configurada.

echo.
echo PASO 2: Compilando e instalando el proyecto Maven...
mvn clean install -DskipTests
if %errorlevel% neq 0 (
    echo ERROR: Fallo al compilar e instalar el proyecto Maven.
    pause
    exit /b 1
)
echo Proyecto Maven compilado e instalado.

echo.
echo PASO 3: Iniciando aplicacion Spring Boot...
echo La aplicacion ejecutara automaticamente todas las migraciones Flyway.
echo URL: http://localhost:8080/ciudadania360
echo.
echo Presione Ctrl+C para detener la aplicacion.
echo.

cd ciudadania360-backend
mvn spring-boot:run

pause