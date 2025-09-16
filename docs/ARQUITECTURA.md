# Arquitectura del Sistema Ciudadanía360

## Visión General

El sistema Ciudadanía360 sigue una arquitectura de capas bien definida que separa claramente las responsabilidades entre la lógica de negocio y la exposición de servicios web.

## Estructura de Capas

```
Cliente Externo
     ↓ (SOAP)
ciudadania360-basic-ws (Capa de Servicios Web)
     ↓ (Llamadas Java)
ciudadania360-backend (Lógica de Negocio)
     ↓ (JPA/Hibernate)
Base de Datos Oracle
```

## Módulos del Proyecto

### 1. ciudadania360-backend
**Responsabilidad**: Lógica de negocio y acceso a datos

**Contenido**:
- **Entidades JPA**: `es.valencia.ciudadania360.backend.entity`
- **Repositorios**: `es.valencia.ciudadania360.backend.repository`
- **Servicios de negocio**: `es.valencia.ciudadania360.backend.service`
- **Configuración**: `es.valencia.ciudadania360.backend.config`

**Tecnologías**:
- Spring Framework
- JPA/Hibernate
- Oracle Database
- Flyway (migraciones)

### 2. ciudadania360-basic-ws
**Responsabilidad**: Exposición de servicios web SOAP

**Contenido**:
- **Servicios web**: `es.valencia.ciudadania360.ws.ciudadano.ws`
- **Configuración**: `es.valencia.ciudadania360.ws.ciudadano.config`

**Tecnologías**:
- Apache CXF
- JAX-WS
- Spring Framework

### 3. ciudadania360-common-schematypes
**Responsabilidad**: DTOs y tipos compartidos

**Contenido**:
- **DTOs de request**: `es.valencia.ciudadania360.common.dto.request`
- **DTOs de response**: `es.valencia.ciudadania360.common.dto.response`
- **Mappers**: `es.valencia.ciudadania360.common.mapper`

**Tecnologías**:
- MapStruct
- Jakarta Validation
- Lombok

### 4. ciudadania360-resources
**Responsabilidad**: Configuraciones y recursos

**Contenido**:
- Propiedades de configuración
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
ciudadania360-basic-ws
    ↓ depende de
ciudadania360-backend
    ↓ depende de
ciudadania360-common-schematypes
    ↓ depende de
ciudadania360-resources
```

## Ventajas de esta Arquitectura

1. **Separación de responsabilidades**: Cada capa tiene una función específica
2. **Reutilización**: El backend puede ser usado por múltiples interfaces
3. **Mantenimiento**: Cambios en lógica no afectan la interfaz SOAP
4. **Escalabilidad**: Pueden desplegarse independientemente
5. **Testabilidad**: Cada capa puede probarse por separado
6. **Estándares**: Cumple con arquitecturas SOA empresariales

## Configuración de Base de Datos

- **Motor**: Oracle Database
- **ORM**: Hibernate/JPA
- **Migraciones**: Flyway
- **Pool de conexiones**: HikariCP

## Tecnologías Utilizadas

- **Java 21**
- **Spring Framework 6.1.5**
- **Spring Security 6.2.2**
- **Hibernate 6.4.1**
- **Apache CXF 4.0.3**
- **Oracle JDBC 21.9.0**
- **MapStruct 1.5.5**
- **Lombok 1.18.30**
- **Flyway 10.8.1**
