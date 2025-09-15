# Ciudadanía 360 - AyWebFwk

[![CI/CD Pipeline](https://github.com/misanmez/ciudadania360-aywebfwk/actions/workflows/ci.yml/badge.svg)](https://github.com/misanmez/ciudadania360-aywebfwk/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-blue.svg)](https://maven.apache.org/)
[![Spring](https://img.shields.io/badge/Spring-6.x-green.svg)](https://spring.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Proyecto de gestión ciudadana desarrollado con el framework AyWebFwk del Ayuntamiento de Valencia.

##  Arquitectura

Este proyecto sigue la arquitectura tradicional del AyWebFwk basada en:
- **Servicios web SOAP** (no REST)
- **Arquitectura multimodular** (no microlitos)
- **Spring Framework tradicional** (no Spring Boot)
- **JPA/Hibernate** para persistencia
- **MapStruct** para mapeo de DTOs

##  Estructura del Proyecto

``
ciudadania360-aywebfwk/
 ciudadania360-backend/           # Aplicación principal (WAR)
 ciudadania360-basic-ws/          # Servicios web SOAP
    ws-ciudadano/               #  Servicio de ciudadanos
    ws-tramitacion/             #  Servicio de tramitación
    ws-comunicaciones/          #  Servicio de comunicaciones
    ws-informacion/             #  Servicio de información
    ws-ia/                      #  Servicio de IA
    ws-interno/                 #  Servicio interno
 ciudadania360-resources/        # Configuraciones
 ciudadania360-common-schematypes/ # Tipos comunes
 ciudadania360-proxies-enterprise/ # Proxies externos
 ciudadania360-proxies-internal/   # Proxies internos
 database/                       # Scripts SQL
``

##  Servicios Implementados

### ws-ciudadano 
- **Entidades**: Ciudadano (JPA)
- **DTOs**: CiudadanoRequest, CiudadanoResponse
- **Servicios**: CiudadanoService, CiudadanoMapper
- **Web Service**: CiudadanoWebService (SOAP)
- **Tests**: 28 tests unitarios (100% exitosos)

#### Endpoints SOAP:
- listarCiudadanos() - Lista todos los ciudadanos
- obtenerCiudadano(id) - Obtiene un ciudadano por ID
- crearCiudadano(request) - Crea un nuevo ciudadano
- ctualizarCiudadano(id, request) - Actualiza un ciudadano
- liminarCiudadano(id) - Elimina un ciudadano

#### WSDL:
``
http://localhost:8080/services/ciudadano?wsdl
``

##  Tecnologías

- **Java 21**
- **Maven 3.9+**
- **Spring Framework 6.x**
- **JPA/Hibernate 6.x**
- **Apache CXF 4.0.3** (SOAP)
- **MapStruct 1.5.5**
- **Lombok 1.18.30**
- **JUnit 5** + **Mockito**
- **PostgreSQL** (base de datos)

##  Prerrequisitos

- Java 21+
- Maven 3.9+
- PostgreSQL 13+
- Tomcat 10+ (para despliegue)

##  Ejecución

### Compilación
``bash
mvn clean compile
``

### Tests
``bash
mvn test
``

### Despliegue
``bash
mvn clean package
# Desplegar el WAR en Tomcat
``

##  Configuración

### Base de Datos
Configurar PostgreSQL en ciudadania360-backend/src/main/resources/appContext.xml:

``xml
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="org.postgresql.Driver"/>
    <property name="url" value="jdbc:postgresql://localhost:5432/ciudadania360"/>
    <property name="username" value="ciudadania360"/>
    <property name="password" value="password"/>
</bean>
``

### Propiedades
Editar ciudadania360-resources/src/main/resources/aywebfwk-app.properties:

``properties
# Configuración de la aplicación
aywebfwk.app.name=Ciudadanía 360
aywebfwk.app.version=1.0.0

# Base de datos
aywebfwk.database.host=localhost
aywebfwk.database.port=5432
aywebfwk.database.name=ciudadania360
``

##  Testing

### Ejecutar todos los tests
``bash
mvn test
``

### Tests específicos del módulo ws-ciudadano
``bash
cd ciudadania360-basic-ws/ws-ciudadano
mvn test
``

### Cobertura de tests
``bash
mvn clean test jacoco:report
``

##  Estado del Proyecto

| Módulo | Estado | Tests | Descripción |
|--------|--------|-------|-------------|
| ws-ciudadano |  Completo | 28/28 | Gestión de ciudadanos |
| ws-tramitacion |  Pendiente | - | Gestión de trámites |
| ws-comunicaciones |  Pendiente | - | Sistema de comunicaciones |
| ws-informacion |  Pendiente | - | Información ciudadana |
| ws-ia |  Pendiente | - | Servicios de IA |
| ws-interno |  Pendiente | - | Gestión interna |

##  Verificación de Servicios

### WSDL
``bash
curl http://localhost:8080/services/ciudadano?wsdl
``

### SoapUI
1. Importar WSDL: http://localhost:8080/services/ciudadano?wsdl
2. Probar operaciones SOAP

### Postman
1. POST a http://localhost:8080/services/ciudadano
2. Content-Type: 	ext/xml
3. Body: XML SOAP request

##  Licencia

Este proyecto está desarrollado para el Ayuntamiento de Valencia siguiendo las especificaciones del pliego de condiciones.

##  Contribución

1. Fork el proyecto
2. Crear una rama para la feature (git checkout -b feature/nueva-funcionalidad)
3. Commit los cambios (git commit -am 'Agregar nueva funcionalidad')
4. Push a la rama (git push origin feature/nueva-funcionalidad)
5. Crear un Pull Request

##  Soporte

Para soporte técnico, contactar con el equipo de desarrollo del Ayuntamiento de Valencia.
