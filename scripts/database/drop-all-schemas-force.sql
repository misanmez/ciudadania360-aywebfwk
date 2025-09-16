-- ========================================================
-- SCRIPT DE ELIMINACIÓN FORZADA - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================
-- Este script elimina todos los esquemas y datos del sistema

-- Terminar todas las conexiones a la base de datos ciudadania360
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'ciudadania360' AND pid <> pg_backend_pid();

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
