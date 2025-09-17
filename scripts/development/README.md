# 🚀 Scripts de Desarrollo - Ciudadanía360

Esta carpeta contiene scripts para facilitar el desarrollo y ejecución de la aplicación Ciudadanía360.

## 📁 Scripts Disponibles

### 1. `setup-and-run-postgresql.bat`
Script de Windows CMD para configurar la base de datos PostgreSQL y ejecutar la aplicación.

**Características:**
- Configura la base de datos PostgreSQL automáticamente
- Ejecuta la aplicación Spring Boot
- Manejo de errores básico
- Logs de ejecución

**Uso:**
```cmd
.\scripts\development\setup-and-run-postgresql.bat
```

### 2. `setup-and-run-postgresql.ps1`
Script de PowerShell para configurar la base de datos PostgreSQL y ejecutar la aplicación.

**Características:**
- Configuración automática de PostgreSQL
- Ejecución de la aplicación Spring Boot
- Manejo avanzado de errores
- Logs detallados
- Verificación de prerrequisitos

**Uso:**
```powershell
.\scripts\development\setup-and-run-postgresql.ps1
```

### 3. `start-application.bat`
Script para iniciar la aplicación cuando la base de datos ya está configurada.

**Características:**
- Inicio rápido de la aplicación
- Verificación de puerto disponible
- Logs de ejecución

**Uso:**
```cmd
.\scripts\development\start-application.bat
```

## 🔧 Prerrequisitos

### Software Requerido
- **Java 21+** - JDK instalado y configurado
- **Maven 3.9+** - Herramienta de construcción
- **PostgreSQL 13+** - Base de datos
- **PowerShell 5.1+** (para scripts .ps1)

### Configuración del Entorno
- Variable `JAVA_HOME` configurada
- Variable `PATH` con Java y Maven
- PostgreSQL ejecutándose en puerto 5432
- Usuario `postgres` con contraseña `Aqmdla04`

## 🚀 Flujo de Desarrollo

### Primera Ejecución
```powershell
# Configurar base de datos y ejecutar aplicación
.\scripts\development\setup-and-run-postgresql.ps1
```

### Ejecuciones Posteriores
```cmd
# Solo ejecutar aplicación (base de datos ya configurada)
.\scripts\development\start-application.bat
```

### Desarrollo Continuo
```bash
# Compilar cambios
mvn clean compile

# Ejecutar tests
mvn test

# Reiniciar aplicación
.\scripts\development\start-application.bat
```

## 🌐 Acceso a la Aplicación

Una vez ejecutada, la aplicación estará disponible en:

- **URL Principal:** http://localhost:8080/ciudadania360
- **Endpoint de Salud:** http://localhost:8080/ciudadania360/api/health
- **Servicios SOAP:** http://localhost:8080/ciudadania360/services/
- **WSDL Ciudadano:** http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl

## 🧪 Testing

Para probar la aplicación:

```powershell
# Prueba completa de servicios
.\scripts\testing\test-soap-services.ps1

# Prueba rápida
.\scripts\testing\quick-test.ps1
```

## 🔍 Troubleshooting

### Error: "Java no encontrado"
- Verificar que `JAVA_HOME` esté configurado
- Verificar que Java esté en el `PATH`

### Error: "Maven no encontrado"
- Verificar que Maven esté instalado
- Verificar que Maven esté en el `PATH`

### Error: "PostgreSQL no disponible"
- Verificar que PostgreSQL esté ejecutándose
- Verificar credenciales de conexión
- Verificar que el puerto 5432 esté libre

### Error: "Puerto 8080 en uso"
- Detener procesos Java anteriores
- Cambiar puerto en `application.properties`
- Usar `netstat -ano | findstr :8080` para identificar procesos

### Error: "Base de datos no existe"
- Ejecutar script de configuración de base de datos
- Verificar permisos del usuario `postgres`
- Revisar logs de PostgreSQL

## 📊 Logs y Debugging

### Logs de la Aplicación
Los logs se muestran en la consola durante la ejecución. Para logs detallados:

```bash
# Ejecutar con debug
mvn spring-boot:run -pl ciudadania360-backend -Dspring-boot.run.arguments="--debug"
```

### Logs de Base de Datos
```bash
# Conectar a PostgreSQL
psql -h localhost -p 5432 -U postgres

# Verificar esquemas
\dn

# Verificar tablas
\dt ciudadano.*
```

## 🔄 Mantenimiento

### Reinicializar Base de Datos
```sql
-- Eliminar base de datos
DROP DATABASE IF EXISTS ciudadania360;

-- Eliminar usuario
DROP USER IF EXISTS ciudadania360;

-- Ejecutar configuración completa
\i scripts/database/setup-postgresql-complete.sql
```

### Limpiar Proyecto
```bash
# Limpiar compilación
mvn clean

# Limpiar y recompilar
mvn clean compile

# Limpiar y ejecutar tests
mvn clean test
```

## 📞 Soporte

Para problemas de desarrollo:

1. Verificar prerrequisitos
2. Revisar logs de ejecución
3. Ejecutar scripts de troubleshooting
4. Contactar al equipo de desarrollo

---

**Versión:** 2.0.0  
**Entorno:** Desarrollo Local  
**Sistema:** Ciudadanía360
