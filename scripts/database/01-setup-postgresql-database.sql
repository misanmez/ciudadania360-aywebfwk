-- ========================================================
-- SCRIPT DE INICIALIZACIÓN COMPLETA - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================
-- Este script crea todo lo necesario para la aplicación
-- Se ejecuta automáticamente al iniciar la aplicación

-- Crear usuario si no existe
DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_catalog.pg_roles WHERE rolname = 'ciudadania360') THEN
        CREATE USER ciudadania360 WITH PASSWORD 'ciudadania360';
        RAISE NOTICE 'Usuario ciudadania360 creado exitosamente';
    ELSE
        RAISE NOTICE 'Usuario ciudadania360 ya existe';
    END IF;
END
$$;

-- Crear base de datos si no existe
SELECT 'CREATE DATABASE ciudadania360 OWNER ciudadania360'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'ciudadania360')\gexec

-- Conectar a la base de datos ciudadania360
\c ciudadania360

-- Dar permisos completos al usuario
GRANT ALL PRIVILEGES ON DATABASE ciudadania360 TO ciudadania360;
GRANT CREATE ON DATABASE ciudadania360 TO ciudadania360;
GRANT ALL ON SCHEMA public TO ciudadania360;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO ciudadania360;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO ciudadania360;

-- Configurar permisos por defecto para objetos futuros
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO ciudadania360;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO ciudadania360;

-- Crear extensiones necesarias
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pg_trgm";
CREATE EXTENSION IF NOT EXISTS "unaccent";

-- Mensaje de confirmación
DO $$
BEGIN
    RAISE NOTICE 'Base de datos y usuario configurados exitosamente';
END $$;
