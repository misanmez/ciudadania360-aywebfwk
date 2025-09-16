-- ========================================================
-- DATOS DE PRUEBA - CIUDADANÍA360
-- ========================================================
-- Esta migración inserta datos de prueba en las tablas

-- Insertar ciudadanos de prueba (solo si no existen)
INSERT INTO ciudadano.ciudadanos (nombre, apellidos, dni, fecha_nacimiento, email, telefono, direccion, codigo_postal, municipio, provincia, pais, activo) VALUES
('Juan', 'Pérez García', '12345678A', '1985-03-15', 'juan.perez@email.com', '666123456', 'Calle Mayor 123', '46001', 'Valencia', 'Valencia', 'España', true),
('María', 'González López', '87654321B', '1990-07-22', 'maria.gonzalez@email.com', '666654321', 'Avenida del Puerto 45', '46002', 'Valencia', 'Valencia', 'España', true),
('Carlos', 'Martín Ruiz', '11223344C', '1978-11-08', 'carlos.martin@email.com', '666987654', 'Plaza del Ayuntamiento 1', '46003', 'Valencia', 'Valencia', 'España', true),
('Ana', 'Sánchez Moreno', '55667788D', '1992-05-14', 'ana.sanchez@email.com', '666111222', 'Calle Xàtiva 78', '46004', 'Valencia', 'Valencia', 'España', true),
('Pedro', 'Jiménez Torres', '99887766E', '1987-09-30', 'pedro.jimenez@email.com', '666333444', 'Gran Vía Marqués del Turia 12', '46005', 'Valencia', 'Valencia', 'España', true)
ON CONFLICT (dni) DO NOTHING;

-- Insertar solicitudes de prueba (solo si no existen)
INSERT INTO solicitudes.solicitudes (numero_expediente, tipo_solicitud, categoria, subcategoria, descripcion, estado, prioridad, ciudadano_id, ciudadano_nombre, ciudadano_email, departamento_asignado, empleado_asignado) VALUES
('EXP-2024-001', 'INCIDENCIA', 'Limpieza', 'Basura', 'Contenedor de basura desbordado en la calle Mayor', 'PENDIENTE', 'NORMAL', 1, 'Juan Pérez García', 'juan.perez@email.com', 'Servicios Urbanos', 'Empleado001'),
('EXP-2024-002', 'SOLICITUD', 'Urbanismo', 'Licencias', 'Solicitud de licencia de obras para reforma de vivienda', 'EN_TRAMITE', 'ALTA', 2, 'María González López', 'maria.gonzalez@email.com', 'Urbanismo', 'Empleado002'),
('EXP-2024-003', 'QUEJA', 'Transporte', 'Autobús', 'Retraso excesivo en la línea 5 del autobús', 'RESUELTA', 'NORMAL', 3, 'Carlos Martín Ruiz', 'carlos.martin@email.com', 'Transporte', 'Empleado003'),
('EXP-2024-004', 'SUGERENCIA', 'Parques', 'Mantenimiento', 'Propuesta de instalación de más bancos en el parque', 'EVALUANDO', 'BAJA', 4, 'Ana Sánchez Moreno', 'ana.sanchez@email.com', 'Parques y Jardines', 'Empleado004'),
('EXP-2024-005', 'INCIDENCIA', 'Alumbrado', 'Farolas', 'Farola fundida en la plaza del Ayuntamiento', 'PENDIENTE', 'ALTA', 5, 'Pedro Jiménez Torres', 'pedro.jimenez@email.com', 'Servicios Urbanos', 'Empleado001')
ON CONFLICT (numero_expediente) DO NOTHING;

-- Insertar comunicaciones de prueba
INSERT INTO comunicaciones.comunicaciones (tipo_comunicacion, asunto, contenido, destinatario, estado, fecha_envio, canal, solicitud_id) VALUES
('EMAIL', 'Confirmación de solicitud EXP-2024-001', 'Su solicitud ha sido recibida y está siendo procesada.', 'juan.perez@email.com', 'ENVIADA', CURRENT_TIMESTAMP, 'EMAIL', 1),
('SMS', 'Actualización de estado EXP-2024-002', 'Su solicitud de licencia está en trámite.', '666654321', 'ENVIADA', CURRENT_TIMESTAMP, 'SMS', 2),
('EMAIL', 'Resolución de queja EXP-2024-003', 'Su queja ha sido resuelta. Se han tomado medidas correctivas.', 'carlos.martin@email.com', 'ENVIADA', CURRENT_TIMESTAMP, 'EMAIL', 3),
('PUSH', 'Nueva sugerencia EXP-2024-004', 'Gracias por su sugerencia. Está siendo evaluada.', 'ana.sanchez@email.com', 'PENDIENTE', NULL, 'PUSH', 4),
('EMAIL', 'Asignación de incidencia EXP-2024-005', 'Su incidencia ha sido asignada al departamento correspondiente.', 'pedro.jimenez@email.com', 'ENVIADA', CURRENT_TIMESTAMP, 'EMAIL', 5);

-- Insertar procesos de IA de prueba
INSERT INTO ia.ia_procesos (tipo_servicio, entrada, salida, modelo, confianza, categoria, subcategoria, sentimiento, solicitud_id) VALUES
('CLASIFICACION', 'Contenedor de basura desbordado en la calle Mayor', 'Limpieza - Basura', 'clasificador-v1', 0.95, 'Limpieza', 'Basura', 'NEUTRO', 1),
('ANALISIS_SENTIMIENTOS', 'Retraso excesivo en la línea 5 del autobús', 'NEGATIVO', 'sentimientos-v1', 0.87, 'Transporte', 'Autobús', 'NEGATIVO', 3),
('CHATBOT', '¿Cómo puedo solicitar una licencia de obras?', 'Para solicitar una licencia de obras debe...', 'chatbot-v1', 0.92, 'Urbanismo', 'Licencias', 'POSITIVO', 2),
('CLASIFICACION', 'Propuesta de instalación de más bancos en el parque', 'Parques - Mantenimiento', 'clasificador-v1', 0.88, 'Parques', 'Mantenimiento', 'POSITIVO', 4),
('ANALISIS_SENTIMIENTOS', 'Farola fundida en la plaza del Ayuntamiento', 'NEUTRO', 'sentimientos-v1', 0.76, 'Alumbrado', 'Farolas', 'NEUTRO', 5);

-- Insertar información de prueba
INSERT INTO informacion.informacion (titulo, descripcion, contenido, categoria, subcategoria, tipo_contenido, estado, autor, departamento, tags, destacada) VALUES
('Cómo solicitar una licencia de obras', 'Guía completa para solicitar licencias de obras en Valencia', 'Para solicitar una licencia de obras debe presentar...', 'Urbanismo', 'Licencias', 'GUIA', 'PUBLICADA', 'Departamento de Urbanismo', 'Urbanismo', 'licencias, obras, construcción', true),
('Horarios de recogida de basura', 'Información sobre los horarios de recogida de basura por zonas', 'Los horarios de recogida de basura varían según la zona...', 'Limpieza', 'Recogida', 'INFORMACION', 'PUBLICADA', 'Servicios Urbanos', 'Servicios Urbanos', 'basura, recogida, horarios', true),
('Líneas de autobús en Valencia', 'Información sobre las líneas de autobús y sus horarios', 'Valencia cuenta con una amplia red de autobuses...', 'Transporte', 'Autobús', 'INFORMACION', 'PUBLICADA', 'Departamento de Transporte', 'Transporte', 'autobús, transporte, líneas', false),
('Parques y jardines de Valencia', 'Lista de parques y jardines públicos en la ciudad', 'Valencia cuenta con numerosos parques y jardines...', 'Parques', 'Información', 'INFORMACION', 'PUBLICADA', 'Parques y Jardines', 'Parques y Jardines', 'parques, jardines, espacios verdes', false),
('Preguntas frecuentes sobre servicios municipales', 'FAQ sobre los servicios más consultados', '¿Cómo puedo solicitar un certificado? ¿Dónde pago mis impuestos?...', 'General', 'FAQ', 'FAQ', 'PUBLICADA', 'Atención al Ciudadano', 'Atención al Ciudadano', 'faq, preguntas, servicios', true);
