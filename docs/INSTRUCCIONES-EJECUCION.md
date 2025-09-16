# 🚀 CIUDADANÍA360 - AYWEBFWK
## Instrucciones de Ejecución Completa

Este documento contiene las instrucciones paso a paso para configurar y ejecutar el proyecto Ciudadanía360 con el framework AyWebFwk **desde cero**.

## 📋 Prerrequisitos

### 1. PostgreSQL
- **Versión:** PostgreSQL 12 o superior
- **Puerto:** 5432 (por defecto)
- **Usuario:** postgres (con permisos de administrador)

### 2. Java
- **Versión:** Java 21
- **Variables de entorno:** JAVA_HOME configurado

### 3. Maven
- **Versión:** Maven 3.8 o superior
- **Variables de entorno:** MAVEN_HOME configurado

## 🛠️ Configuración Automática Completa

### ⚡ Ejecución en Un Solo Paso

El sistema ahora incluye **inicialización automática completa**. Solo necesitas ejecutar:

**Windows (CMD):**
```cmd
.\setup-and-run.bat
```

**Windows (PowerShell):**
```powershell
.\setup-and-run.ps1
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

1. `01-create-database-and-user.sql` - Crea usuario y base de datos
2. `02-create-schemas.sql` - Crea esquemas por módulo
3. `03-create-tables.sql` - Crea todas las tablas
4. `04-create-indexes.sql` - Crea índices de optimización
5. `05-insert-sample-data.sql` - Inserta datos de prueba

## 🌐 Acceso a la Aplicación

Una vez ejecutándose, la aplicación estará disponible en:
- **URL:** http://localhost:8080/ciudadania360
- **Puerto:** 8080
- **Contexto:** /ciudadania360

## 🔧 Servicios SOAP Disponibles

### Servicio de Ciudadanos
- **Endpoint:** http://localhost:8080/ciudadania360/services/ciudadano
- **WSDL:** http://localhost:8080/ciudadania360/services/ciudadano?wsdl

### Operaciones Disponibles:
- `findAll()` - Obtener todos los ciudadanos
- `findById(Long id)` - Buscar ciudadano por ID
- `findByDni(String dni)` - Buscar ciudadano por DNI
- `save(CiudadanoRequest request)` - Crear nuevo ciudadano
- `update(Long id, CiudadanoRequest request)` - Actualizar ciudadano
- `delete(Long id)` - Eliminar ciudadano (soft delete)

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

## 🐛 Solución de Problemas

### Error de Conexión a Base de Datos
```
FATAL: la autentificación password falló para el usuario 'ciudadania360'
```

**Solución:**
1. Ejecutar `.\setup-and-run.bat` o `.\setup-and-run.ps1`
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

## 🔄 Reinicialización Completa

Si necesitas empezar de nuevo:

1. **Detener** la aplicación (Ctrl+C)
2. **Ejecutar** `.\setup-and-run.bat` o `.\setup-and-run.ps1`
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
Accede a: http://localhost:8080/ciudadania360/services/ciudadano?wsdl

### 3. Verificar Datos
Consulta las tablas para verificar que los datos de prueba se insertaron correctamente.

## 📞 Soporte

Para problemas o dudas:
1. Verificar los logs de la aplicación
2. Ejecutar el script de reinicialización completa
3. Comprobar que todos los prerrequisitos estén instalados

---
**Versión:** 2.0.0  
**Fecha:** 2024-01-16  
**Framework:** AyWebFwk + Spring Boot  
**Característica:** Inicialización automática completa