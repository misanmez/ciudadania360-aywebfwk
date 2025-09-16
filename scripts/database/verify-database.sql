-- ========================================================
-- SCRIPT DE VERIFICACIÓN - POSTGRESQL
-- CIUDADANÍA360
-- ========================================================

-- Verificar esquemas
\echo '=== ESQUEMAS CREADOS ==='
\dn

-- Verificar tablas en cada esquema
\echo ''
\echo '=== TABLAS EN ESQUEMA CIUDADANO ==='
\dt ciudadano.*

\echo ''
\echo '=== TABLAS EN ESQUEMA SOLICITUDES ==='
\dt solicitudes.*

\echo ''
\echo '=== TABLAS EN ESQUEMA COMUNICACIONES ==='
\dt comunicaciones.*

\echo ''
\echo '=== TABLAS EN ESQUEMA IA ==='
\dt ia.*

\echo ''
\echo '=== TABLAS EN ESQUEMA INFORMACION ==='
\dt informacion.*

\echo ''
\echo '=== TABLAS EN ESQUEMA AUDIT ==='
\dt audit.*

-- Verificar datos de prueba
\echo ''
\echo '=== DATOS DE PRUEBA - CIUDADANOS ==='
SELECT COUNT(*) as total_ciudadanos FROM ciudadano.ciudadanos;

\echo ''
\echo '=== DATOS DE PRUEBA - SOLICITUDES ==='
SELECT COUNT(*) as total_solicitudes FROM solicitudes.solicitudes;

\echo ''
\echo '=== DATOS DE PRUEBA - COMUNICACIONES ==='
SELECT COUNT(*) as total_comunicaciones FROM comunicaciones.comunicaciones;

\echo ''
\echo '=== DATOS DE PRUEBA - IA ==='
SELECT COUNT(*) as total_ia FROM ia.ia_procesos;

\echo ''
\echo '=== DATOS DE PRUEBA - INFORMACION ==='
SELECT COUNT(*) as total_informacion FROM informacion.informacion;

\echo ''
\echo '=== VERIFICACIÓN COMPLETADA ==='
