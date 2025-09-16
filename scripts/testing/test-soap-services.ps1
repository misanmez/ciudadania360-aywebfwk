# test-soap-services.ps1
# Script unificado para probar los servicios SOAP de Ciudadanía360
# Versión: 2.0 - Script mejorado y unificado

param(
    [string]$BaseUrl = "http://localhost:8080/ciudadania360",
    [switch]$Verbose,
    [switch]$Help
)

# Función para mostrar ayuda
function Show-Help {
    Write-Host "=== SCRIPT DE PRUEBA DE SERVICIOS SOAP CIUDADANÍA360 ===" -ForegroundColor Green
    Write-Host ""
    Write-Host "Uso: .\test-soap-services.ps1 [opciones]"
    Write-Host ""
    Write-Host "Opciones:"
    Write-Host "  -BaseUrl <url>    URL base de la aplicación (default: http://localhost:8080/ciudadania360)"
    Write-Host "  -Verbose          Mostrar información detallada"
    Write-Host "  -Help             Mostrar esta ayuda"
    Write-Host ""
    Write-Host "Ejemplos:"
    Write-Host "  .\test-soap-services.ps1"
    Write-Host "  .\test-soap-services.ps1 -BaseUrl http://localhost:8081/ciudadania360"
    Write-Host "  .\test-soap-services.ps1 -Verbose"
    Write-Host ""
}

# Mostrar ayuda si se solicita
if ($Help) {
    Show-Help
    exit 0
}

# Configuración de endpoints
$healthEndpoint = "$BaseUrl/api/health"
$wsdlEndpoint = "$BaseUrl/services/CiudadanoWebService?wsdl"
$soapEndpoint = "$BaseUrl/services/CiudadanoWebService"

Write-Host "=== PROBANDO SERVICIOS SOAP CIUDADANÍA360 ===" -ForegroundColor Green
Write-Host "URL Base: $BaseUrl" -ForegroundColor Cyan
Write-Host "Timestamp: $(Get-Date -Format 'yyyy-MM-dd HH:mm:ss')" -ForegroundColor Cyan
Write-Host ""

# Función para probar endpoint de salud
function Test-HealthEndpoint {
    Write-Host "1. Probando endpoint de salud..." -ForegroundColor Yellow
    try {
        $healthResponse = curl -s $healthEndpoint
        if ($healthResponse -and $healthResponse -match '"status":"UP"') {
            Write-Host "✅ Endpoint de salud funcionando:" -ForegroundColor Green
            if ($Verbose) {
                Write-Host "   Respuesta completa: $healthResponse" -ForegroundColor Gray
            } else {
                # Extraer información clave
                if ($healthResponse -match '"application":"([^"]*)"') {
                    Write-Host "   - Aplicación: $($matches[1])" -ForegroundColor White
                }
                if ($healthResponse -match '"version":"([^"]*)"') {
                    Write-Host "   - Versión: $($matches[1])" -ForegroundColor White
                }
                if ($healthResponse -match '"status":"([^"]*)"') {
                    Write-Host "   - Estado: $($matches[1])" -ForegroundColor White
                }
            }
            return $true
        } else {
            Write-Host "❌ Error: Respuesta de salud no válida" -ForegroundColor Red
            return $false
        }
    } catch {
        Write-Host "❌ Error accediendo al endpoint de salud: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Función para probar WSDL
function Test-WSDL {
    Write-Host "`n2. Probando WSDL del servicio SOAP..." -ForegroundColor Yellow
    try {
        $wsdlResponse = curl -s $wsdlEndpoint
        if ($wsdlResponse -match "wsdl:definitions") {
            Write-Host "✅ WSDL del servicio SOAP accesible y válido." -ForegroundColor Green
            Write-Host "   - Contiene definiciones WSDL válidas" -ForegroundColor White
            Write-Host "   - Longitud de respuesta: $($wsdlResponse.Length) caracteres" -ForegroundColor White
            
            if ($Verbose) {
                Write-Host "   - Primeros 300 caracteres del WSDL:" -ForegroundColor Gray
                Write-Host "     $($wsdlResponse.Substring(0, [Math]::Min(300, $wsdlResponse.Length)))..." -ForegroundColor Gray
            }
            return $true
        } else {
            Write-Host "❌ Error: WSDL no contiene definiciones válidas" -ForegroundColor Red
            return $false
        }
    } catch {
        Write-Host "❌ Error accediendo al WSDL: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Función para probar servicio SOAP
function Test-SOAPService {
    Write-Host "`n3. Probando servicio SOAP (listarCiudadanos)..." -ForegroundColor Yellow
    $soapBody = @"
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:listarCiudadanos/>
   </soapenv:Body>
</soapenv:Envelope>
"@

    try {
        $soapResponse = curl -s -X POST -H "Content-Type: text/xml; charset=utf-8" -d $soapBody $soapEndpoint
        if ($soapResponse -match "soap:Envelope") {
            Write-Host "✅ Servicio SOAP funcionando. Respuesta recibida:" -ForegroundColor Green
            Write-Host "   - Longitud de respuesta: $($soapResponse.Length) caracteres" -ForegroundColor White
            Write-Host "   - Contiene envoltorio SOAP válido" -ForegroundColor White
            
            if ($Verbose) {
                Write-Host "   - Respuesta completa (primeros 500 caracteres):" -ForegroundColor Gray
                Write-Host "     $($soapResponse.Substring(0, [Math]::Min(500, $soapResponse.Length)))..." -ForegroundColor Gray
            } else {
                Write-Host "   - Primeros 200 caracteres:" -ForegroundColor White
                Write-Host "     $($soapResponse.Substring(0, [Math]::Min(200, $soapResponse.Length)))..." -ForegroundColor Gray
            }
            return $true
        } else {
            Write-Host "❌ Error: Respuesta SOAP no válida" -ForegroundColor Red
            return $false
        }
    } catch {
        Write-Host "❌ Error en servicio SOAP: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Función para verificar puerto
function Test-Port {
    Write-Host "`n4. Verificando estado del puerto..." -ForegroundColor Yellow
    try {
        $port = if ($BaseUrl -match ":(\d+)") { $matches[1] } else { "8080" }
        $portCheck = netstat -ano | findstr ":$port"
        if ($portCheck) {
            Write-Host "✅ Puerto $port está en uso:" -ForegroundColor Green
            if ($Verbose) {
                Write-Host "   $portCheck" -ForegroundColor Gray
            } else {
                $firstLine = ($portCheck -split "`n")[0]
                Write-Host "   $firstLine" -ForegroundColor White
            }
            return $true
        } else {
            Write-Host "❌ Puerto $port no está en uso" -ForegroundColor Red
            return $false
        }
    } catch {
        Write-Host "❌ Error verificando puerto: $($_.Exception.Message)" -ForegroundColor Red
        return $false
    }
}

# Función para probar operaciones SOAP adicionales
function Test-AdditionalSOAPOperations {
    Write-Host "`n5. Probando operaciones SOAP adicionales..." -ForegroundColor Yellow
    
    # Probar obtenerCiudadano
    $soapBodyGet = @"
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soapenv:Header/>
   <soapenv:Body>
      <ws:obtenerCiudadano>
         <id>1</id>
      </ws:obtenerCiudadano>
   </soapenv:Body>
</soapenv:Envelope>
"@

    try {
        $soapResponse = curl -s -X POST -H "Content-Type: text/xml; charset=utf-8" -d $soapBodyGet $soapEndpoint
        if ($soapResponse -match "soap:Envelope") {
            Write-Host "✅ Operación obtenerCiudadano funcionando" -ForegroundColor Green
        } else {
            Write-Host "⚠️ Operación obtenerCiudadano: respuesta no válida" -ForegroundColor Yellow
        }
    } catch {
        Write-Host "⚠️ Operación obtenerCiudadano: $($_.Exception.Message)" -ForegroundColor Yellow
    }
}

# Ejecutar todas las pruebas
$results = @{
    Health = Test-HealthEndpoint
    WSDL = Test-WSDL
    SOAP = Test-SOAPService
    Port = Test-Port
}

# Probar operaciones adicionales si está en modo verbose
if ($Verbose) {
    Test-AdditionalSOAPOperations
}

# Resumen final
Write-Host "`n=== RESUMEN DE PRUEBAS ===" -ForegroundColor Green
$totalTests = $results.Count
$passedTests = ($results.Values | Where-Object { $_ -eq $true }).Count

Write-Host "Total de pruebas: $totalTests" -ForegroundColor White
Write-Host "Pruebas exitosas: $passedTests" -ForegroundColor Green
Write-Host "Pruebas fallidas: $($totalTests - $passedTests)" -ForegroundColor Red

if ($passedTests -eq $totalTests) {
    Write-Host "`n🎉 ¡TODAS LAS PRUEBAS PASARON! Los servicios SOAP están funcionando correctamente." -ForegroundColor Green
    exit 0
} else {
    Write-Host "`n⚠️ Algunas pruebas fallaron. Revisa los errores anteriores." -ForegroundColor Yellow
    exit 1
}

Write-Host "`n=== FIN DE PRUEBAS ===" -ForegroundColor Green
