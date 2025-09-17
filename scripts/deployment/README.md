# 🚀 Scripts de Despliegue - Ciudadanía360

Esta carpeta contiene scripts para el despliegue de la aplicación Ciudadanía360 en entornos de producción.

## 📁 Scripts Disponibles

### 1. `deploy-to-tomcat.bat`
Script de Windows para desplegar la aplicación en un servidor Tomcat.

**Características:**
- Compilación del proyecto
- Generación del archivo WAR
- Despliegue en Tomcat
- Verificación del despliegue
- Logs de ejecución

**Uso:**
```cmd
.\scripts\deployment\deploy-to-tomcat.bat
```

## 🔧 Prerrequisitos de Despliegue

### Software Requerido
- **Java 21+** - JDK instalado y configurado
- **Maven 3.9+** - Herramienta de construcción
- **Apache Tomcat 10+** - Servidor de aplicaciones
- **PostgreSQL 13+** - Base de datos de producción

### Configuración del Entorno
- Variable `JAVA_HOME` configurada
- Variable `PATH` con Java y Maven
- Tomcat ejecutándose y accesible
- Base de datos PostgreSQL configurada
- Usuario con permisos de despliegue

## 🏗️ Proceso de Despliegue

### 1. Preparación del Entorno

```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar Tomcat
curl http://localhost:8080/manager/text/list
```

### 2. Compilación del Proyecto

```bash
# Compilar proyecto completo
mvn clean compile

# Ejecutar tests
mvn test

# Generar WAR
mvn clean package -pl ciudadania360-backend
```

### 3. Despliegue en Tomcat

```cmd
# Despliegue automático
.\scripts\deployment\deploy-to-tomcat.bat
```

### 4. Verificación del Despliegue

```bash
# Verificar aplicación
curl http://localhost:8080/ciudadania360/api/health

# Verificar servicios SOAP
curl http://localhost:8080/ciudadania360/services/CiudadanoWebService?wsdl
```

## 🌐 Configuración de Producción

### Variables de Entorno

```bash
# Base de datos
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=ciudadania360
export DB_USER=ciudadania360
export DB_PASSWORD=ciudadania360

# Aplicación
export SERVER_PORT=8080
export CONTEXT_PATH=/ciudadania360
export LOG_LEVEL=INFO
```

### Configuración de Tomcat

#### server.xml
```xml
<Context path="/ciudadania360" docBase="ciudadania360-backend.war" />
```

#### web.xml
```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>es.valencia.ciudadania360.backend.Application</param-value>
</context-param>
```

## 🔒 Seguridad en Producción

### Configuración de Seguridad

```properties
# application-prod.properties
spring.security.user.name=admin
spring.security.user.password=${ADMIN_PASSWORD}
spring.security.user.roles=ADMIN

# Base de datos
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

# Logging
logging.level.es.valencia.ciudadania360=INFO
logging.level.org.springframework.security=WARN
```

### Certificados SSL

```bash
# Generar certificado SSL
keytool -genkey -alias ciudadania360 -keyalg RSA -keystore ciudadania360.keystore

# Configurar HTTPS en Tomcat
# server.xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
           maxThreads="150" SSLEnabled="true">
    <SSLHostConfig>
        <Certificate certificateKeystoreFile="conf/ciudadania360.keystore"
                     certificateKeystorePassword="changeit"
                     type="RSA" />
    </SSLHostConfig>
</Connector>
```

## 📊 Monitoreo y Logs

### Configuración de Logs

```properties
# Logback configuration
logging.file.name=logs/ciudadania360.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
logging.level.root=INFO
logging.level.es.valencia.ciudadania360=DEBUG
```

### Monitoreo de Aplicación

```bash
# Health check
curl http://localhost:8080/ciudadania360/api/health

# Métricas de aplicación
curl http://localhost:8080/ciudadania360/actuator/health
curl http://localhost:8080/ciudadania360/actuator/metrics
```

## 🔄 Actualizaciones y Rollback

### Proceso de Actualización

```bash
# 1. Backup de la versión actual
cp ciudadania360-backend.war ciudadania360-backend.war.backup

# 2. Despliegue de nueva versión
.\scripts\deployment\deploy-to-tomcat.bat

# 3. Verificación
curl http://localhost:8080/ciudadania360/api/health
```

### Rollback

```bash
# 1. Detener aplicación
curl -X POST http://localhost:8080/manager/text/stop?path=/ciudadania360

# 2. Restaurar versión anterior
cp ciudadania360-backend.war.backup ciudadania360-backend.war

# 3. Reiniciar aplicación
curl -X POST http://localhost:8080/manager/text/start?path=/ciudadania360
```

## 🧪 Testing de Producción

### Scripts de Prueba

```powershell
# Prueba completa de servicios
.\scripts\testing\test-soap-services.ps1 -BaseUrl http://localhost:8080/ciudadania360

# Prueba rápida
.\scripts\testing\quick-test.ps1 -BaseUrl http://localhost:8080/ciudadania360
```

### Verificación de Funcionalidad

```bash
# Verificar base de datos
psql -h localhost -p 5432 -U ciudadania360 -d ciudadania360 -c "SELECT COUNT(*) FROM ciudadano.ciudadanos;"

# Verificar servicios SOAP
curl -X POST -H "Content-Type: text/xml" \
  -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.ciudadania360.valencia.es/"><soapenv:Header/><soapenv:Body><ws:listarCiudadanos/></soapenv:Body></soapenv:Envelope>' \
  http://localhost:8080/ciudadania360/services/CiudadanoWebService
```

## 🔍 Troubleshooting

### Problemas Comunes

#### Error: "Aplicación no se inicia"
- Verificar logs de Tomcat
- Verificar configuración de base de datos
- Verificar permisos de archivos

#### Error: "Servicios SOAP no disponibles"
- Verificar configuración de CXF
- Verificar logs de la aplicación
- Verificar conectividad de base de datos

#### Error: "Base de datos no accesible"
- Verificar conexión a PostgreSQL
- Verificar credenciales
- Verificar configuración de red

### Logs de Diagnóstico

```bash
# Logs de Tomcat
tail -f $CATALINA_HOME/logs/catalina.out

# Logs de la aplicación
tail -f logs/ciudadania360.log

# Logs de PostgreSQL
tail -f /var/log/postgresql/postgresql.log
```

## 📞 Soporte

Para problemas de despliegue:

1. Verificar prerrequisitos
2. Revisar logs de aplicación y servidor
3. Ejecutar scripts de verificación
4. Contactar al equipo de desarrollo

---

**Versión:** 2.0.0  
**Entorno:** Producción  
**Sistema:** Ciudadanía360
