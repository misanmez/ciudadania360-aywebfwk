-- ========================================================
-- SCRIPT DE INICIALIZACIÓN DE ESQUEMAS - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================

-- Crear esquemas por módulo
CREATE SCHEMA IF NOT EXISTS ciudadano;
CREATE SCHEMA IF NOT EXISTS solicitudes;
CREATE SCHEMA IF NOT EXISTS comunicaciones;
CREATE SCHEMA IF NOT EXISTS ia;
CREATE SCHEMA IF NOT EXISTS informacion;
CREATE SCHEMA IF NOT EXISTS audit;

-- Configurar permisos
GRANT USAGE ON SCHEMA ciudadano TO ciudadania360;
GRANT USAGE ON SCHEMA solicitudes TO ciudadania360;
GRANT USAGE ON SCHEMA comunicaciones TO ciudadania360;
GRANT USAGE ON SCHEMA ia TO ciudadania360;
GRANT USAGE ON SCHEMA informacion TO ciudadania360;
GRANT USAGE ON SCHEMA audit TO ciudadania360;

-- Configurar búsqueda de esquemas
ALTER DATABASE ciudadania360 SET search_path TO public, ciudadano, solicitudes, comunicaciones, ia, informacion, audit;

-- Crear extensiones necesarias
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pg_trgm";
CREATE EXTENSION IF NOT EXISTS "unaccent";

-- Mensaje de confirmación
DO $$
BEGIN
    RAISE NOTICE 'Esquemas creados exitosamente para Ciudadanía360';
END $$;
