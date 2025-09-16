# quick-test.ps1
# Script de prueba rápida para verificar que la aplicación está funcionando

param(
    [string]$BaseUrl = "http://localhost:8080/ciudadania360"
)

Write-Host "=== PRUEBA RÁPIDA CIUDADANÍA360 ===" -ForegroundColor Green
Write-Host "URL: $BaseUrl" -ForegroundColor Cyan
Write-Host ""

# Probar solo el endpoint de salud
try {
    $response = curl -s "$BaseUrl/api/health"
    if ($response -match '"status":"UP"') {
        Write-Host "✅ Aplicación funcionando correctamente" -ForegroundColor Green
        Write-Host "   Estado: UP" -ForegroundColor White
        exit 0
    } else {
        Write-Host "❌ Aplicación no responde correctamente" -ForegroundColor Red
        exit 1
    }
} catch {
    Write-Host "❌ Error conectando con la aplicación: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}
