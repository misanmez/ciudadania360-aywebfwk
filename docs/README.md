# 🏛️ Ciudadanía360 - AyWebFwk

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Spring](https://img.shields.io/badge/Spring-6.x-green.svg)](https://spring.io/)
[![Oracle](https://img.shields.io/badge/Oracle-Database-red.svg)](https://www.oracle.com/database/)

Sistema de gestión ciudadana desarrollado con el framework AyWebFwk del Ayuntamiento de Valencia.

## 🏗️ Arquitectura

El sistema sigue una arquitectura de capas bien definida:

```
Cliente Externo
     ↓ (SOAP)
ciudadania360-basic-ws (Capa de Servicios Web)
     ↓ (Llamadas Java)
ciudadania360-backend (Lógica de Negocio)
     ↓ (JPA/Hibernate)
Base de Datos Oracle
```

## 📁 Estructura del Proyecto

```
ciudadania360-aywebfwk/
├── ciudadania360-backend/              # Aplicación principal (WAR)
├── ciudadania360-basic-ws/             # Servicios web SOAP
│   └── ws-ciudadano/                   # Servicio de ciudadanos
├── ciudadania360-common-schematypes/   # DTOs y tipos compartidos
├── ciudadania360-resources/            # Configuraciones
├── scripts/                            # Scripts de automatización
│   ├── database/                       # Scripts de base de datos
│   ├── development/                    # Scripts de desarrollo
│   └── deployment/                     # Scripts de despliegue
├── docs/                               # Documentación
└── database/                           # Scripts SQL existentes
```

## 🚀 Inicio Rápido

### Prerrequisitos

- **Java 21+**
- **Maven 3.9+**
- **Oracle Database 19c+**
- **Tomcat 10+** (para despliegue)

### Configuración Automática

**Windows (CMD):**
```cmd
.\scripts\development\setup-and-run-oracle.bat
```

**Windows (PowerShell):**
```powershell
.\scripts\development\setup-and-run-oracle.ps1
```

### Inicio Rápido (si ya está configurado)

```cmd
.\scripts\development\start-application.bat
```

## 🌐 Acceso a la Aplicación

- **URL:** http://localhost:8080/ciudadania360
- **Servicios SOAP:** http://localhost:8080/ciudadania360/services/
- **WSDL Ciudadano:** http://localhost:8080/ciudadania360/services/ciudadano?wsdl

## 🔧 Servicios Implementados

### ws-ciudadano
- **Operaciones:** CRUD completo de ciudadanos
- **Validaciones:** DNI, email, teléfono
- **Endpoints:** 6 operaciones SOAP

## 🛠️ Tecnologías

- **Java 21**
- **Spring Framework 6.x**
- **Apache CXF 4.0.3** (SOAP)
- **Hibernate 6.x** (JPA)
- **Oracle Database**
- **MapStruct 1.5.5**
- **Lombok 1.18.30**

## 📚 Documentación

- [Arquitectura](ARQUITECTURA.md) - Documentación técnica de la arquitectura
- [Instrucciones de Ejecución](INSTRUCCIONES-EJECUCION.md) - Guía detallada
- [Resumen del Proyecto](RESUMEN_PROYECTO.md) - Estado actual

## 🔄 Desarrollo

### Compilación
```bash
mvn clean compile
```

### Tests
```bash
mvn test
```

### Despliegue
```bash
.\scripts\deployment\deploy-to-tomcat.bat
```

## 📞 Soporte

Para soporte técnico, contactar con el equipo de desarrollo del Ayuntamiento de Valencia.

---

**Versión:** 2.0.0  
**Framework:** AyWebFwk + Spring Boot  
**Base de Datos:** Oracle Database
