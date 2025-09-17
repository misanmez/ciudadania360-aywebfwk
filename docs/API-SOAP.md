# 🔌 API SOAP - Ciudadanía360

## Visión General

El sistema Ciudadanía360 expone servicios web SOAP para la gestión de ciudadanos, siguiendo estándares JAX-WS y utilizando Apache CXF como framework de implementación.

## Información del Servicio

- **Namespace**: `http://ws.ciudadania360.valencia.es/`
- **Endpoint**: `http://localhost:8080/ciudadania360/services/CiudadanoWebService`
- **WSDL**: `http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl`
- **Implementación**: Apache CXF 4.0.3
- **Estándar**: JAX-WS 2.3

## Operaciones Disponibles

### 1. listarCiudadanos()

**Descripción**: Obtiene todos los ciudadanos activos del sistema.

**Parámetros**: Ninguno

**Respuesta**: Lista de ciudadanos activos

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:listarCiudadanos/>
   </soap:Body>
</soap:Envelope>
```

**Ejemplo de Respuesta**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:listarCiudadanosResponse xmlns:ns2="http://ws.ciudadania360.valencia.es/">
         <return>
            <id>1</id>
            <nombre>Juan</nombre>
            <apellidos>Pérez García</apellidos>
            <dni>12345678A</dni>
            <email>juan.perez@email.com</email>
            <telefono>600123456</telefono>
            <activo>true</activo>
         </return>
         <!-- ... más ciudadanos -->
      </ns2:listarCiudadanosResponse>
   </soap:Body>
</soap:Envelope>
```

### 2. obtenerCiudadano(Long id)

**Descripción**: Obtiene un ciudadano específico por su ID.

**Parámetros**:
- `id` (Long): Identificador único del ciudadano

**Respuesta**: Datos del ciudadano o null si no existe

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:obtenerCiudadano>
         <id>1</id>
      </ws:obtenerCiudadano>
   </soap:Body>
</soap:Envelope>
```

### 3. crearCiudadano(CiudadanoRequestDTO)

**Descripción**: Crea un nuevo ciudadano en el sistema.

**Parámetros**:
- `ciudadanoRequest` (CiudadanoRequestDTO): Datos del nuevo ciudadano

**Respuesta**: Ciudadano creado con ID asignado

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:crearCiudadano>
         <ciudadanoRequest>
            <nombre>María</nombre>
            <apellidos>García López</apellidos>
            <dni>87654321B</dni>
            <email>maria.garcia@email.com</email>
            <telefono>600654321</telefono>
            <fechaNacimiento>1990-05-15</fechaNacimiento>
            <direccion>Calle Mayor 123</direccion>
            <municipio>Valencia</municipio>
            <provincia>Valencia</provincia>
            <codigoPostal>46001</codigoPostal>
            <pais>España</pais>
         </ciudadanoRequest>
      </ws:crearCiudadano>
   </soap:Body>
</soap:Envelope>
```

### 4. actualizarCiudadano(Long id, CiudadanoRequestDTO)

**Descripción**: Actualiza los datos de un ciudadano existente.

**Parámetros**:
- `id` (Long): Identificador único del ciudadano
- `ciudadanoRequest` (CiudadanoRequestDTO): Nuevos datos del ciudadano

**Respuesta**: Ciudadano actualizado

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:actualizarCiudadano>
         <id>1</id>
         <ciudadanoRequest>
            <nombre>Juan Carlos</nombre>
            <apellidos>Pérez García</apellidos>
            <dni>12345678A</dni>
            <email>juan.carlos.perez@email.com</email>
            <telefono>600123456</telefono>
            <!-- ... otros campos -->
         </ciudadanoRequest>
      </ws:actualizarCiudadano>
   </soap:Body>
</soap:Envelope>
```

### 5. eliminarCiudadano(Long id)

**Descripción**: Elimina un ciudadano del sistema (soft delete).

**Parámetros**:
- `id` (Long): Identificador único del ciudadano

**Respuesta**: Confirmación de eliminación

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:eliminarCiudadano>
         <id>1</id>
      </ws:eliminarCiudadano>
   </soap:Body>
</soap:Envelope>
```

### 6. buscarCiudadanoPorDni(String dni)

**Descripción**: Busca un ciudadano por su DNI.

**Parámetros**:
- `dni` (String): DNI del ciudadano

**Respuesta**: Datos del ciudadano o null si no existe

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:buscarCiudadanoPorDni>
         <dni>12345678A</dni>
      </ws:buscarCiudadanoPorDni>
   </soap:Body>
</soap:Envelope>
```

### 7. buscarCiudadanoPorEmail(String email)

**Descripción**: Busca un ciudadano por su dirección de email.

**Parámetros**:
- `email` (String): Email del ciudadano

**Respuesta**: Datos del ciudadano o null si no existe

**Ejemplo de Petición**:
```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
   <soap:Header/>
   <soap:Body>
      <ws:buscarCiudadanoPorEmail>
         <email>juan.perez@email.com</email>
      </ws:buscarCiudadanoPorEmail>
   </soap:Body>
</soap:Envelope>
```

## Tipos de Datos

### CiudadanoRequestDTO

```xml
<xs:complexType name="ciudadanoRequestDTO">
   <xs:sequence>
      <xs:element name="nombre" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="apellidos" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="dni" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="email" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="telefono" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="fechaNacimiento" type="xs:date" minOccurs="0" maxOccurs="1"/>
      <xs:element name="direccion" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="municipio" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="provincia" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="codigoPostal" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="pais" type="xs:string" minOccurs="0" maxOccurs="1"/>
   </xs:sequence>
</xs:complexType>
```

### CiudadanoResponseDTO

```xml
<xs:complexType name="ciudadanoResponseDTO">
   <xs:sequence>
      <xs:element name="id" type="xs:long" minOccurs="1" maxOccurs="1"/>
      <xs:element name="nombre" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="apellidos" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="dni" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="email" type="xs:string" minOccurs="1" maxOccurs="1"/>
      <xs:element name="telefono" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="fechaNacimiento" type="xs:date" minOccurs="0" maxOccurs="1"/>
      <xs:element name="direccion" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="municipio" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="provincia" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="codigoPostal" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="pais" type="xs:string" minOccurs="0" maxOccurs="1"/>
      <xs:element name="activo" type="xs:boolean" minOccurs="1" maxOccurs="1"/>
      <xs:element name="fechaCreacion" type="xs:dateTime" minOccurs="1" maxOccurs="1"/>
      <xs:element name="fechaModificacion" type="xs:dateTime" minOccurs="0" maxOccurs="1"/>
   </xs:sequence>
</xs:complexType>
```

## Validaciones

### CiudadanoRequestDTO
- **nombre**: Requerido, máximo 100 caracteres
- **apellidos**: Requerido, máximo 200 caracteres
- **dni**: Requerido, formato válido de DNI español
- **email**: Requerido, formato válido de email
- **telefono**: Opcional, formato válido de teléfono español
- **fechaNacimiento**: Opcional, fecha válida
- **direccion**: Opcional, máximo 255 caracteres
- **municipio**: Opcional, máximo 100 caracteres
- **provincia**: Opcional, máximo 100 caracteres
- **codigoPostal**: Opcional, formato válido de código postal español
- **pais**: Opcional, máximo 100 caracteres

## Códigos de Error

### Errores de Validación (400)
```xml
<soap:Fault>
   <faultcode>soap:Client</faultcode>
   <faultstring>Validation Error</faultstring>
   <detail>
      <validationError>
         <field>dni</field>
         <message>DNI format is invalid</message>
      </validationError>
   </detail>
</soap:Fault>
```

### Errores de Servidor (500)
```xml
<soap:Fault>
   <faultcode>soap:Server</faultcode>
   <faultstring>Internal Server Error</faultstring>
   <detail>
      <errorMessage>Database connection failed</errorMessage>
   </detail>
</soap:Fault>
```

### Recurso No Encontrado (404)
```xml
<soap:Fault>
   <faultcode>soap:Client</faultcode>
   <faultstring>Resource Not Found</faultstring>
   <detail>
      <errorMessage>Ciudadano with id 999 not found</errorMessage>
   </detail>
</soap:Fault>
```

## Herramientas de Prueba

### 1. SoapUI
1. Crear nuevo proyecto SOAP
2. Importar WSDL: `http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl`
3. Generar requests automáticamente
4. Ejecutar pruebas

### 2. Postman
1. Crear nueva request
2. Método: POST
3. URL: `http://localhost:8080/ciudadania360/services/CiudadanoWebService`
4. Headers: `Content-Type: text/xml`
5. Body: XML con envelope SOAP

### 3. cURL
```bash
curl -X POST \
  -H "Content-Type: text/xml" \
  -d '<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/">
       <soap:Header/>
       <soap:Body>
          <ws:listarCiudadanos/>
       </soap:Body>
     </soap:Envelope>' \
  http://localhost:8080/ciudadania360/services/CiudadanoWebService
```

### 4. Scripts de Prueba
```powershell
# Ejecutar script de prueba completo
.\scripts\testing\test-soap-services.ps1

# Ejecutar con información detallada
.\scripts\testing\test-soap-services.ps1 -Verbose
```

## Seguridad

### Autenticación
- **Método**: HTTP Basic Authentication (configurable)
- **Usuario**: Configurado en Spring Security
- **Contraseña**: Generada automáticamente en desarrollo

### Autorización
- **Roles**: Configurables por operación
- **Permisos**: Basados en Spring Security
- **Auditoría**: Registro de todas las operaciones

### CORS
- **Configuración**: Habilitado para desarrollo
- **Orígenes**: Configurables por entorno
- **Métodos**: GET, POST, OPTIONS

## Monitoreo

### Health Check
- **Endpoint**: `http://localhost:8080/ciudadania360/api/health`
- **Respuesta**: Estado de la aplicación y servicios

### Métricas
- **Endpoint**: `http://localhost:8080/ciudadania360/actuator/metrics`
- **Información**: Métricas de rendimiento y uso

### Logs
- **Nivel**: Configurable por entorno
- **Formato**: JSON estructurado
- **Rotación**: Automática por tamaño y tiempo

## Mejores Prácticas

### 1. Manejo de Errores
- Siempre incluir manejo de excepciones SOAP
- Usar códigos de error apropiados
- Proporcionar mensajes descriptivos

### 2. Validación
- Validar datos de entrada en el servicio
- Usar anotaciones de validación Jakarta
- Retornar errores de validación claros

### 3. Performance
- Implementar paginación para listas grandes
- Usar índices de base de datos apropiados
- Cachear datos frecuentemente accedidos

### 4. Seguridad
- Validar todos los inputs
- Implementar rate limiting
- Registrar todas las operaciones

---
**Versión**: 2.0.0  
**Fecha**: 2025-09-17  
**Framework**: Apache CXF 4.0.3  
**Estándar**: JAX-WS 2.3
