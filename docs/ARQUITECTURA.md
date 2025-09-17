# 🏗️ Arquitectura del Sistema Ciudadanía360

## Visión General

El sistema Ciudadanía360 sigue una arquitectura de capas bien definida que separa claramente las responsabilidades entre la lógica de negocio y la exposición de servicios web, implementando el patrón **Service-Oriented Architecture (SOA)**.

## Estructura de Capas

```
Cliente Externo
     ↓ (SOAP)
ciudadania360-basic-ws (Capa de Servicios Web)
     ↓ (Llamadas Java)
ciudadania360-core (Lógica de Negocio Centralizada)
     ↓ (JPA/Hibernate)
Base de Datos PostgreSQL
     ↑
ciudadania360-backend (Orquestador de Módulos)
```

## Módulos del Proyecto

### 1. ciudadania360-backend
**Responsabilidad**: Aplicación principal que orquesta todos los módulos

**Contenido**:
- **Configuración Spring Boot**: `es.valencia.ciudadania360.backend.config`
- **Controladores REST**: `es.valencia.ciudadania360.backend.controller`
- **Configuración de seguridad**: Spring Security
- **Configuración CXF**: Apache CXF para servicios SOAP

**Tecnologías**:
- Spring Boot 3.2.1
- Spring Security 6.2.2
- Apache CXF 4.0.3
- Tomcat embebido

### 2. ciudadania360-core
**Responsabilidad**: Lógica de negocio centralizada

**Contenido**:
- **Entidades JPA**: `es.valencia.ciudadania360.core.entity`
- **Repositorios**: `es.valencia.ciudadania360.core.repository`
- **Servicios de negocio**: `es.valencia.ciudadania360.core.service`
- **Mappers**: `es.valencia.ciudadania360.core.mapper`

**Tecnologías**:
- Spring Data JPA
- Hibernate 6.4.1
- MapStruct 1.5.5
- PostgreSQL Database

### 3. ciudadania360-basic-ws
**Responsabilidad**: Exposición de servicios web SOAP

**Contenido**:
- **Servicios web**: `es.valencia.ciudadania360.ws.ciudadano.ws`
- **Configuración**: `es.valencia.ciudadania360.ws.ciudadano.config`

**Tecnologías**:
- Apache CXF
- JAX-WS
- Spring Framework

### 4. ciudadania360-common-schematypes
**Responsabilidad**: DTOs y tipos compartidos

**Contenido**:
- **DTOs de request**: `es.valencia.ciudadania360.common.dto.request`
- **DTOs de response**: `es.valencia.ciudadania360.common.dto.response`
- **DTOs base**: `es.valencia.ciudadania360.common.dto`

**Tecnologías**:
- Jakarta Validation
- Lombok
- Jackson

### 5. ciudadania360-resources
**Responsabilidad**: Configuraciones y recursos

**Contenido**:
- Propiedades de configuración por entorno
- Recursos de la aplicación

## Flujo de Datos

### Ejemplo: Consulta de Ciudadano

1. **Cliente SOAP** envía petición:
   ```xml
   <soap:Envelope>
     <soap:Body>
       <obtenerCiudadano>
         <id>123</id>
       </obtenerCiudadano>
     </soap:Body>
   </soap:Envelope>
   ```

2. **CiudadanoWebService** recibe la petición
   - Valida parámetros
   - Llama al servicio de negocio

3. **CiudadanoService** ejecuta lógica de negocio:
   - Consulta base de datos
   - Aplica reglas de negocio
   - Retorna entidad JPA

4. **CiudadanoWebService** convierte resultado:
   - Usa CiudadanoMapper para convertir entidad a DTO
   - Construye respuesta SOAP

5. **Cliente** recibe respuesta:
   ```xml
   <soap:Envelope>
     <soap:Body>
       <obtenerCiudadanoResponse>
         <ciudadano>
           <id>123</id>
           <nombre>Juan</nombre>
           <apellidos>Pérez</apellidos>
           <!-- ... más campos -->
         </ciudadano>
       </obtenerCiudadanoResponse>
     </soap:Body>
   </soap:Envelope>
   ```

## Dependencias entre Módulos

```
ciudadania360-backend
    ↓ depende de
ciudadania360-core
    ↓ depende de
ciudadania360-common-schematypes
    ↓ depende de
ciudadania360-resources

ciudadania360-basic-ws
    ↓ depende de
ciudadania360-core
    ↓ depende de
ciudadania360-common-schematypes
```

## Ventajas de esta Arquitectura

1. **Separación de responsabilidades**: Cada capa tiene una función específica
2. **Reutilización**: El core puede ser usado por múltiples interfaces
3. **Mantenimiento**: Cambios en lógica no afectan la interfaz SOAP
4. **Escalabilidad**: Pueden desplegarse independientemente
5. **Testabilidad**: Cada capa puede probarse por separado
6. **Estándares**: Cumple con arquitecturas SOA empresariales

## Configuración de Base de Datos

- **Motor**: PostgreSQL Database
- **ORM**: Hibernate/JPA
- **Pool de conexiones**: HikariCP
- **Esquemas**: Separados por funcionalidad
  - `ciudadano` - Gestión de ciudadanos
  - `solicitudes` - Gestión de solicitudes
  - `comunicaciones` - Gestión de comunicaciones
  - `ia` - Procesos de inteligencia artificial
  - `informacion` - Gestión de información
  - `audit` - Auditoría

## Tecnologías Utilizadas

- **Java 21**
- **Spring Framework 6.1.5**
- **Spring Boot 3.2.1**
- **Spring Security 6.2.2**
- **Hibernate 6.4.1**
- **Apache CXF 4.0.3**
- **PostgreSQL Database**
- **MapStruct 1.5.5**
- **Lombok 1.18.30**
- **Jakarta Validation**

## Patrones de Diseño Implementados

1. **Service Layer Pattern**: Separación entre lógica de negocio y presentación
2. **Repository Pattern**: Abstracción del acceso a datos
3. **DTO Pattern**: Transferencia de datos entre capas
4. **Mapper Pattern**: Conversión entre entidades y DTOs
5. **Dependency Injection**: Gestión de dependencias con Spring
6. **Configuration Pattern**: Configuración centralizada por entorno

## Seguridad

- **Spring Security**: Autenticación y autorización
- **CORS**: Configuración para acceso cross-origin
- **Validación**: Jakarta Validation en DTOs
- **Auditoría**: Trazabilidad de operaciones

## Monitoreo y Logging

- **Spring Boot Actuator**: Endpoints de monitoreo
- **Logback**: Sistema de logging
- **Health Checks**: Verificación de estado de la aplicación
- **Métricas**: Recopilación de métricas de rendimiento