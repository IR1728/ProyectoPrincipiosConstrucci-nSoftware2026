-- ============================================================================
-- Creacion de la base de datos y del usuario de la aplicacion.
-- Ejecutar como administrador (root) de MySQL:
--     mysql -u root -p < crear-usuario-bd.sql
--
-- Las credenciales aqui definidas deben coincidir con el archivo
-- configuracion-bd.properties (clase sgpp.modelo.ConexionBD).
-- Cambie la contrasena por una de su preferencia si lo desea.
-- ============================================================================

CREATE DATABASE IF NOT EXISTS practicasprofesionales
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usuario dedicado para que la aplicacion administre la base de datos.
CREATE USER IF NOT EXISTS 'practicas_app'@'localhost'
    IDENTIFIED BY 'Practicas.2026';

-- Permisos para administrar (gestionar datos) de esa base de datos.
GRANT ALL PRIVILEGES ON practicasprofesionales.* TO 'practicas_app'@'localhost';

FLUSH PRIVILEGES;
