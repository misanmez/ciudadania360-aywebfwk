# Scripts de Testing - Ciudadanía360

Esta carpeta contiene scripts de prueba para verificar el funcionamiento de los servicios SOAP de Ciudadanía360.

## Scripts Disponibles

### 1. `test-soap-services.ps1`
Script principal y completo para probar todos los servicios SOAP.

**Características:**
- Prueba endpoint de salud
- Verifica WSDL del servicio SOAP
- Prueba operaciones SOAP (listarCiudadanos)
- Verifica estado del puerto
- Modo verbose disponible
- Resumen de resultados

**Uso:**
```powershell
# Prueba básica
.\test-soap-services.ps1

# Con información detallada
.\test-soap-services.ps1 -Verbose

# Con URL personalizada
.\test-soap-services.ps1 -BaseUrl http://localhost:8081/ciudadania360

# Mostrar ayuda
.\test-soap-services.ps1 -Help
```

### 2. `quick-test.ps1`
Script de prueba rápida para verificar que la aplicación está funcionando.

**Características:**
- Solo prueba el endpoint de salud
- Ejecución rápida
- Ideal para CI/CD

**Uso:**
```powershell
# Prueba rápida
.\quick-test.ps1

# Con URL personalizada
.\quick-test.ps1 -BaseUrl http://localhost:8081/ciudadania360
```

## Requisitos

- PowerShell 5.1 o superior
- Aplicación Ciudadanía360 ejecutándose
- Acceso a `curl` (incluido en Windows 10/11)

## Endpoints Probados

- **Salud**: `GET /api/health`
- **WSDL**: `GET /services/CiudadanoWebService?wsdl`
- **SOAP**: `POST /services/CiudadanoWebService`

## Códigos de Salida

- `0`: Todas las pruebas pasaron
- `1`: Una o más pruebas fallaron

## Ejemplos de Uso

### Desarrollo Local
```powershell
# Desde la raíz del proyecto
.\scripts\testing\test-soap-services.ps1
```

### CI/CD Pipeline
```powershell
# Prueba rápida para verificar que la aplicación está lista
.\scripts\testing\quick-test.ps1
```

### Debugging
```powershell
# Información detallada para debugging
.\scripts\testing\test-soap-services.ps1 -Verbose
```

## Troubleshooting

### Error: "No se puede establecer una conexión"
- Verificar que la aplicación esté ejecutándose
- Comprobar que el puerto 8080 esté libre
- Verificar la URL base

### Error: "WSDL no contiene definiciones válidas"
- Verificar que el servicio SOAP esté configurado correctamente
- Comprobar logs de la aplicación

### Error: "Respuesta SOAP no válida"
- Verificar que la base de datos esté configurada
- Comprobar que las tablas existan
- Verificar logs de la aplicación
