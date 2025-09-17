# 🚀 CIUDADANÍA360 - AYWEBFWK
## Instrucciones de Ejecución Completa

Este documento contiene las instrucciones paso a paso para configurar y ejecutar el proyecto Ciudadanía360 con el framework AyWebFwk **desde cero**.

## 📋 Prerrequisitos

### 1. PostgreSQL
- **Versión:** PostgreSQL 13 o superior
- **Puerto:** 5432 (por defecto)
- **Usuario:** postgres (con permisos de administrador)
- **Contraseña:** Aqmdla04 (configurada en scripts)

### 2. Java
- **Versión:** Java 21
- **Variables de entorno:** JAVA_HOME configurado

### 3. Maven
- **Versión:** Maven 3.9 o superior
- **Variables de entorno:** MAVEN_HOME configurado

## 🛠️ Configuración Automática Completa

### ⚡ Ejecución en Un Solo Paso

El sistema incluye **scripts de inicialización automática**. Ejecuta uno de estos comandos:

**Windows (CMD):**
```cmd
.\scripts\development\setup-and-run-postgresql.bat
```

**Windows (PowerShell):**
```powershell
.\scripts\development\setup-and-run-postgresql.ps1
```

### 🔄 ¿Qué hace el script automático?

1. **Elimina** la base de datos existente (si existe)
2. **Crea** el usuario `ciudadania360` desde cero
3. **Crea** la base de datos `ciudadania360`
4. **Configura** todos los permisos necesarios
5. **Compila** el proyecto Maven
6. **Ejecuta** la aplicación Spring Boot
7. **Inicializa automáticamente** todos los esquemas, tablas, índices y datos de prueba

### 📁 Scripts de Inicialización Automática

La aplicación ejecuta automáticamente estos scripts en orden:

1. `01-setup-postgresql-database.sql` - Crea usuario y base de datos
2. `02-setup-postgresql-schemas.sql` - Crea esquemas por módulo
3. `03-setup-postgresql-tables.sql` - Crea todas las tablas
4. `04-setup-postgresql-sample-data.sql` - Inserta datos de prueba

## 🌐 Acceso a la Aplicación

Una vez ejecutándose, la aplicación estará disponible en:
- **URL:** http://localhost:8080/ciudadania360
- **Puerto:** 8080
- **Contexto:** /ciudadania360

## 🔧 Servicios SOAP Disponibles

### Servicio de Ciudadanos
- **Endpoint:** http://localhost:8080/ciudadania360/services/CiudadanoWebService
- **WSDL:** http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl

### Operaciones Disponibles:
1. `listarCiudadanos()` - Obtener todos los ciudadanos activos
2. `obtenerCiudadano(Long id)` - Buscar ciudadano por ID
3. `crearCiudadano(CiudadanoRequestDTO)` - Crear nuevo ciudadano
4. `actualizarCiudadano(Long id, CiudadanoRequestDTO)` - Actualizar ciudadano
5. `eliminarCiudadano(Long id)` - Eliminar ciudadano (soft delete)
6. `buscarCiudadanoPorDni(String dni)` - Buscar ciudadano por DNI
7. `buscarCiudadanoPorEmail(String email)` - Buscar ciudadano por email

## 🗄️ Estructura de Base de Datos

La aplicación crea automáticamente:

### Esquemas:
- `ciudadano` - Gestión de ciudadanos
- `solicitudes` - Gestión de solicitudes
- `comunicaciones` - Gestión de comunicaciones
- `ia` - Procesos de inteligencia artificial
- `informacion` - Gestión de información
- `audit` - Auditoría

### Tablas Principales:
- `ciudadano.ciudadanos` - Datos de ciudadanos (8 registros de prueba)
- `solicitudes.solicitudes` - Solicitudes ciudadanas (8 registros de prueba)
- `comunicaciones.comunicaciones` - Comunicaciones (8 registros de prueba)
- `ia.ia_procesos` - Procesos de IA (5 registros de prueba)
- `informacion.informacion` - Información pública (8 registros de prueba)

### Datos de Prueba Incluidos:
- **8 ciudadanos** con datos completos
- **8 solicitudes** en diferentes estados
- **8 comunicaciones** por diversos canales
- **5 procesos de IA** con resultados
- **8 informaciones** públicas y privadas

## 🧪 Testing

### Scripts de Prueba Disponibles

**Prueba completa de servicios SOAP:**
```powershell
.\scripts\testing\test-soap-services.ps1
```

**Prueba rápida de salud:**
```powershell
.\scripts\testing\quick-test.ps1
```

**Con información detallada:**
```powershell
.\scripts\testing\test-soap-services.ps1 -Verbose
```

### Endpoints de Prueba
- **Salud:** http://localhost:8080/ciudadania360/api/health
- **WSDL:** http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl
- **SOAP:** http://localhost:8080/ciudadania360/services/CiudadanoWebService

## 🐛 Solución de Problemas

### Error de Conexión a Base de Datos
```
FATAL: la autentificación password falló para el usuario 'ciudadania360'
```

**Solución:**
1. Ejecutar `.\scripts\development\setup-and-run-postgresql.bat` o `.\scripts\development\setup-and-run-postgresql.ps1`
2. El script elimina y recrea todo desde cero

### Error de Puerto en Uso
```
Port 8080 was already in use
```

**Solución:**
1. Cambiar el puerto en `application.properties`
2. O detener la aplicación que está usando el puerto 8080

### Error de Compilación Maven
```
Could not find artifact es.valencia:ciudadania360-common-schematypes
```

**Solución:**
El script automático ya incluye `mvn clean install -DskipTests`

### Error de Flyway
```
Unable to obtain inputstream for resource: db/migration/V1__Initial_schema.sql
```

**Solución:**
1. Verificar que los archivos de migración existan en `ciudadania360-backend/src/main/resources/db/migration/`
2. O deshabilitar Flyway temporalmente en `application.properties`: `spring.flyway.enabled=false`

## 🔄 Reinicialización Completa

Si necesitas empezar de nuevo:

1. **Detener** la aplicación (Ctrl+C)
2. **Ejecutar** `.\scripts\development\setup-and-run-postgresql.bat` o `.\scripts\development\setup-and-run-postgresql.ps1`
3. El sistema eliminará todo y creará desde cero

## 📊 Verificación de Funcionamiento

### 1. Verificar Base de Datos
Conecta con DBeaver a:
- Host: `localhost`
- Puerto: `5432`
- Base de datos: `ciudadania360`
- Usuario: `ciudadania360`
- Contraseña: `ciudadania360`

### 2. Verificar Servicios SOAP
Accede a: http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl

### 3. Verificar Datos
Consulta las tablas para verificar que los datos de prueba se insertaron correctamente.

### 4. Verificar Logs
Los logs de la aplicación se muestran en la consola. Busca mensajes como:
- `Started Application in X.XXX seconds`
- `Tomcat started on port 8080`
- `Creating Service {http://ws.ciudadania360.valencia.es/}CiudadanoWebService`

## 🔧 Configuración Manual (Opcional)

Si prefieres configurar manualmente:

### 1. Base de Datos
```sql
-- Conectar como postgres
psql -U postgres -h localhost

-- Crear usuario y base de datos
CREATE USER ciudadania360 WITH PASSWORD 'ciudadania360';
CREATE DATABASE ciudadania360 OWNER ciudadania360;
GRANT ALL PRIVILEGES ON DATABASE ciudadania360 TO ciudadania360;
```

### 2. Compilación
```bash
mvn clean install -DskipTests
```

### 3. Ejecución
```bash
mvn spring-boot:run -pl ciudadania360-backend
```

## 📞 Soporte

Para problemas o dudas:
1. Verificar los logs de la aplicación
2. Ejecutar el script de reinicialización completa
3. Comprobar que todos los prerrequisitos estén instalados
4. Revisar la documentación de [Arquitectura](ARQUITECTURA.md)

---
**Versión:** 2.0.0  
**Fecha:** 2025-09-17  
**Framework:** AyWebFwk + Spring Boot  
**Característica:** Inicialización automática completa