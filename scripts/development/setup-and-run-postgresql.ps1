# ========================================
#   CIUDADANIA360 - CONFIGURACION COMPLETA
#   PostgreSQL Database Setup
# ========================================

Write-Host "========================================" -ForegroundColor Green
Write-Host "  CIUDADANIA360 - CONFIGURACION COMPLETA" -ForegroundColor Green
Write-Host "  PostgreSQL Database Setup" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Verificar que PostgreSQL esté instalado
$psqlPath = Get-Command psql -ErrorAction SilentlyContinue
if (-not $psqlPath) {
    Write-Host "ERROR: PostgreSQL no encontrado en el PATH" -ForegroundColor Red
    Write-Host "Por favor, instala PostgreSQL y asegúrate de que psql esté disponible" -ForegroundColor Red
    Write-Host "Descarga desde: https://www.postgresql.org/download/" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
    exit 1
}
Write-Host "PostgreSQL encontrado en: $($psqlPath.Source)" -ForegroundColor Green

Write-Host ""
Write-Host "[1/4] Configurando base de datos PostgreSQL..." -ForegroundColor Yellow
Write-Host ""

# Configurar la base de datos
Write-Host "Ejecutando configuración completa de base de datos PostgreSQL..." -ForegroundColor Cyan
& $psqlPath.Source -h localhost -p 5432 -U postgres -f "scripts\database\setup-postgresql-complete.sql"
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: No se pudo configurar la base de datos PostgreSQL" -ForegroundColor Red
    Write-Host "Verifica que PostgreSQL esté ejecutándose y que las credenciales sean correctas" -ForegroundColor Red
    Write-Host "Credenciales por defecto: postgres/Aqmdla04@localhost:5432" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
    exit 1
}

Write-Host ""
Write-Host "[2/4] Compilando proyecto..." -ForegroundColor Yellow
Write-Host ""

# Compilar proyecto
& mvn clean compile
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Fallo en la compilación" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
    exit 1
}

Write-Host ""
Write-Host "[3/4] Ejecutando tests..." -ForegroundColor Yellow
Write-Host ""

# Ejecutar tests
& mvn test
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Fallo en los tests" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
    exit 1
}

Write-Host ""
Write-Host "[4/4] Iniciando aplicación..." -ForegroundColor Yellow
Write-Host ""

# Iniciar aplicación
& mvn spring-boot:run -pl ciudadania360-backend
if ($LASTEXITCODE -ne 0) {
    Write-Host "ERROR: Fallo al iniciar la aplicación" -ForegroundColor Red
    Read-Host "Presiona Enter para salir"
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Green
Write-Host "  CONFIGURACIÓN COMPLETA FINALIZADA" -ForegroundColor Green
Write-Host "  Aplicación disponible en:" -ForegroundColor Green
Write-Host "  http://localhost:8080/ciudadania360" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Read-Host "Presiona Enter para salir"
