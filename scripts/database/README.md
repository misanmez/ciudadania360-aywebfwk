# 🗄️ Scripts de Base de Datos - PostgreSQL

Esta carpeta contiene todos los scripts necesarios para configurar la base de datos PostgreSQL del sistema Ciudadanía360.

## 📁 Estructura de Scripts

```
scripts/database/
├── 01-setup-postgresql-database.sql     # Configuración básica del usuario
├── 02-setup-postgresql-schemas.sql      # Creación de esquemas
├── 03-setup-postgresql-tables.sql       # Creación de tablas
├── 04-setup-postgresql-sample-data.sql  # Datos de prueba
├── setup-postgresql-complete.sql        # Script unificado completo
└── README.md                            # Esta documentación
```

## 🚀 Uso de los Scripts

### Configuración Completa (Recomendado)

Para configurar todo el sistema desde cero:

```bash
# Windows CMD
.\scripts\development\setup-and-run-postgresql.bat

# Windows PowerShell
.\scripts\development\setup-and-run-postgresql.ps1
```

### Configuración Manual

Si prefieres ejecutar los scripts manualmente:

```bash
# 1. Conectar como postgres
psql -h localhost -p 5432 -U postgres

# 2. Ejecutar configuración completa
\i scripts/database/setup-postgresql-complete.sql
```

### Scripts Individuales

Si necesitas ejecutar solo una parte:

```sql
-- Solo configuración básica
\i scripts/database/01-setup-postgresql-database.sql

-- Solo esquemas
\i scripts/database/02-setup-postgresql-schemas.sql

-- Solo tablas
\i scripts/database/03-setup-postgresql-tables.sql

-- Solo datos de prueba
\i scripts/database/04-setup-postgresql-sample-data.sql
```

## 🏗️ Estructura de la Base de Datos

### Esquemas Creados

- **ciudadano** - Gestión de ciudadanos
- **solicitudes** - Gestión de solicitudes
- **comunicaciones** - Sistema de comunicaciones
- **ia** - Procesos de inteligencia artificial
- **informacion** - Gestión de información
- **audit** - Auditoría del sistema

### Tablas Principales

#### Esquema ciudadano
- `ciudadanos` - Datos de ciudadanos

#### Esquema solicitudes
- `solicitudes` - Solicitudes ciudadanas

#### Esquema comunicaciones
- `comunicaciones` - Comunicaciones multicanal

#### Esquema ia
- `ia_procesos` - Procesos de IA

#### Esquema informacion
- `informacion` - Información pública

#### Esquema audit
- `audit_log` - Log de auditoría

## 📊 Datos de Prueba

Los scripts incluyen datos de prueba:

- **5 ciudadanos** con datos completos
- **5 solicitudes** en diferentes estados
- **5 comunicaciones** por diversos canales
- **5 procesos de IA** con resultados
- **5 informaciones** públicas

## 🔧 Configuración de Conexión

### Credenciales por Defecto

- **Usuario principal**: `ciudadania360`
- **Contraseña**: `ciudadania360`
- **Host**: `localhost`
- **Puerto**: `5432`
- **Base de datos**: `ciudadania360`

### Usuario de Administración

- **Usuario**: `postgres`
- **Contraseña**: `Aqmdla04`

## 🛠️ Mantenimiento

### Reinicialización Completa

Para empezar de nuevo:

```sql
-- Eliminar base de datos
DROP DATABASE IF EXISTS ciudadania360;

-- Eliminar usuario
DROP USER IF EXISTS ciudadania360;

-- Ejecutar configuración completa
\i scripts/database/setup-postgresql-complete.sql
```

### Backup y Restore

```bash
# Backup
pg_dump -h localhost -p 5432 -U ciudadania360 -d ciudadania360 > ciudadania360_backup.sql

# Restore
psql -h localhost -p 5432 -U ciudadania360 -d ciudadania360 < ciudadania360_backup.sql
```

## 🔍 Verificación

### Verificar Conexión

```bash
psql -h localhost -p 5432 -U ciudadania360 -d ciudadania360
```

### Verificar Esquemas

```sql
\dn
```

### Verificar Tablas

```sql
\dt ciudadano.*
\dt solicitudes.*
\dt comunicaciones.*
\dt ia.*
\dt informacion.*
\dt audit.*
```

## 📞 Soporte

Para problemas con la base de datos:

1. Verificar que PostgreSQL esté ejecutándose
2. Comprobar las credenciales
3. Ejecutar el script de reinicialización completa
4. Revisar los logs de PostgreSQL

---

**Versión:** 2.0.0  
**Base de Datos:** PostgreSQL 13+  
**Sistema:** Ciudadanía360