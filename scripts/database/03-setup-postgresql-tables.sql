-- ========================================================
-- SCRIPT DE CREACIÓN DE TABLAS - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================

-- Tabla de ciudadanos
CREATE TABLE IF NOT EXISTS ciudadano.ciudadanos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(200) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    fecha_nacimiento DATE,
    email VARCHAR(255),
    telefono VARCHAR(20),
    direccion VARCHAR(500),
    codigo_postal VARCHAR(10),
    municipio VARCHAR(100),
    provincia VARCHAR(100),
    pais VARCHAR(100),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de solicitudes
CREATE TABLE IF NOT EXISTS solicitudes.solicitudes (
    id BIGSERIAL PRIMARY KEY,
    numero_expediente VARCHAR(50) UNIQUE,
    tipo_solicitud VARCHAR(100) NOT NULL,
    categoria VARCHAR(100),
    subcategoria VARCHAR(100),
    descripcion TEXT,
    estado VARCHAR(50) NOT NULL DEFAULT 'PENDIENTE',
    prioridad VARCHAR(20) DEFAULT 'NORMAL',
    ciudadano_id BIGINT NOT NULL,
    ciudadano_nombre VARCHAR(300),
    ciudadano_email VARCHAR(255),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP,
    departamento_asignado VARCHAR(100),
    empleado_asignado VARCHAR(200),
    observaciones TEXT,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de comunicaciones
CREATE TABLE IF NOT EXISTS comunicaciones.comunicaciones (
    id BIGSERIAL PRIMARY KEY,
    tipo_comunicacion VARCHAR(20) NOT NULL,
    asunto VARCHAR(500),
    contenido TEXT,
    destinatario VARCHAR(255) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    fecha_envio TIMESTAMP,
    fecha_programada TIMESTAMP,
    canal VARCHAR(50),
    solicitud_id BIGINT,
    observaciones TEXT,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de IA
CREATE TABLE IF NOT EXISTS ia.ia_procesos (
    id BIGSERIAL PRIMARY KEY,
    tipo_servicio VARCHAR(50) NOT NULL,
    entrada TEXT,
    salida TEXT,
    modelo VARCHAR(100),
    confianza DECIMAL(5,4),
    categoria VARCHAR(100),
    subcategoria VARCHAR(100),
    sentimiento VARCHAR(20),
    fecha_procesamiento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solicitud_id BIGINT,
    observaciones TEXT,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de información
CREATE TABLE IF NOT EXISTS informacion.informacion (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(500) NOT NULL,
    descripcion TEXT,
    contenido TEXT,
    categoria VARCHAR(100),
    subcategoria VARCHAR(100),
    tipo_contenido VARCHAR(50) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'BORRADOR',
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP,
    fecha_publicacion TIMESTAMP,
    autor VARCHAR(200),
    departamento VARCHAR(100),
    tags VARCHAR(500),
    destacada BOOLEAN NOT NULL DEFAULT FALSE,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de auditoría
CREATE TABLE IF NOT EXISTS audit.audit_log (
    id BIGSERIAL PRIMARY KEY,
    tabla VARCHAR(100) NOT NULL,
    operacion VARCHAR(20) NOT NULL,
    registro_id BIGINT,
    datos_anteriores TEXT,
    datos_nuevos TEXT,
    usuario VARCHAR(100),
    fecha_operacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45)
);

-- Índices para optimización
CREATE INDEX IF NOT EXISTS idx_ciudadanos_dni ON ciudadano.ciudadanos(dni);
CREATE INDEX IF NOT EXISTS idx_ciudadanos_email ON ciudadano.ciudadanos(email);
CREATE INDEX IF NOT EXISTS idx_ciudadanos_activo ON ciudadano.ciudadanos(activo);

CREATE INDEX IF NOT EXISTS idx_solicitudes_ciudadano_id ON solicitudes.solicitudes(ciudadano_id);
CREATE INDEX IF NOT EXISTS idx_solicitudes_estado ON solicitudes.solicitudes(estado);
CREATE INDEX IF NOT EXISTS idx_solicitudes_fecha_creacion ON solicitudes.solicitudes(fecha_creacion);

CREATE INDEX IF NOT EXISTS idx_comunicaciones_destinatario ON comunicaciones.comunicaciones(destinatario);
CREATE INDEX IF NOT EXISTS idx_comunicaciones_estado ON comunicaciones.comunicaciones(estado);
CREATE INDEX IF NOT EXISTS idx_comunicaciones_fecha_envio ON comunicaciones.comunicaciones(fecha_envio);

CREATE INDEX IF NOT EXISTS idx_ia_tipo_servicio ON ia.ia_procesos(tipo_servicio);
CREATE INDEX IF NOT EXISTS idx_ia_fecha_procesamiento ON ia.ia_procesos(fecha_procesamiento);

CREATE INDEX IF NOT EXISTS idx_informacion_categoria ON informacion.informacion(categoria);
CREATE INDEX IF NOT EXISTS idx_informacion_estado ON informacion.informacion(estado);
CREATE INDEX IF NOT EXISTS idx_informacion_destacada ON informacion.informacion(destacada);

CREATE INDEX IF NOT EXISTS idx_audit_tabla ON audit.audit_log(tabla);
CREATE INDEX IF NOT EXISTS idx_audit_fecha ON audit.audit_log(fecha_operacion);

-- Mensaje de confirmación
DO $$
BEGIN
    RAISE NOTICE 'Tablas creadas exitosamente para Ciudadanía360';
END $$;
