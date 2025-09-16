-- ========================================================
-- SCRIPT DE CONFIGURACIÓN COMPLETA - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================
-- Este script configura todo el sistema desde cero

-- PASO 1: Configurar base de datos y usuario
\i 01-setup-postgresql-database.sql

-- PASO 2: Crear esquemas
\i 02-setup-postgresql-schemas.sql

-- PASO 3: Crear tablas
\i 03-setup-postgresql-tables.sql

-- PASO 4: Insertar datos de prueba
\i 04-setup-postgresql-sample-data.sql

-- Mensaje final
DO $$
BEGIN
    RAISE NOTICE '========================================';
    RAISE NOTICE '  CONFIGURACIÓN COMPLETA FINALIZADA';
    RAISE NOTICE '  Sistema Ciudadanía360 listo para usar';
    RAISE NOTICE '========================================';
END $$;
