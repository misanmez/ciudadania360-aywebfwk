-- ========================================================
-- SCRIPT DE ELIMINACIÓN COMPLETA - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================
-- Este script elimina todos los esquemas y datos del sistema

-- Conectar a la base de datos postgres para eliminar la base de datos
\c postgres

-- Eliminar base de datos si existe
DROP DATABASE IF EXISTS ciudadania360;

-- Eliminar usuario si existe
DROP USER IF EXISTS ciudadania360;

-- Mensaje de confirmación
DO $$
BEGIN
    RAISE NOTICE '========================================';
    RAISE NOTICE '  ESQUEMAS Y BASE DE DATOS ELIMINADOS';
    RAISE NOTICE '  Sistema Ciudadanía360 limpiado';
    RAISE NOTICE '========================================';
END $$;
