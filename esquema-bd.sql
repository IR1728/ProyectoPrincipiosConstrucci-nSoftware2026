-- ============================================================================
-- Esquema de la base de datos del Sistema de Gestion de Practicas Profesionales
-- (solo las tablas necesarias para los 5 casos de uso implementados) + datos
-- de ejemplo.
--
-- Ejecutar:  mysql -u practicas_app -p practicasprofesionales < esquema-bd.sql
-- ============================================================================

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS autoevaluacion;
DROP TABLE IF EXISTS reporte_mensual;
DROP TABLE IF EXISTS programacion_entregas;
DROP TABLE IF EXISTS estudiante;
DROP TABLE IF EXISTS proyecto;
DROP TABLE IF EXISTS responsable_tecnico;
DROP TABLE IF EXISTS organizacion_vinculada;
DROP TABLE IF EXISTS periodo;
DROP TABLE IF EXISTS usuario;
SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------------------------------------------------------
CREATE TABLE usuario (
    id_usuario       INT AUTO_INCREMENT PRIMARY KEY,
    numero_personal  VARCHAR(30)  NOT NULL UNIQUE,
    contrasena       VARCHAR(100) NOT NULL,
    nombre_completo  VARCHAR(150) NOT NULL,
    tipo_usuario     ENUM('COORDINADOR','ESTUDIANTE','PROFESOR') NOT NULL
);

CREATE TABLE periodo (
    id_periodo    INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(80) NOT NULL,
    fecha_inicio  DATE,
    fecha_fin     DATE,
    activo        TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE organizacion_vinculada (
    id_organizacion_vinculada INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(120) NOT NULL,
    sector     VARCHAR(80),
    correo     VARCHAR(120),
    telefono   VARCHAR(20),
    direccion  VARCHAR(150),
    ciudad     VARCHAR(80),
    estado     VARCHAR(80)
);

CREATE TABLE responsable_tecnico (
    id_responsable_tecnico INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(120) NOT NULL,
    departamento  VARCHAR(80),
    puesto        VARCHAR(80),
    correo        VARCHAR(120),
    id_organizacion_vinculada INT NOT NULL,
    FOREIGN KEY (id_organizacion_vinculada)
        REFERENCES organizacion_vinculada(id_organizacion_vinculada)
);

CREATE TABLE proyecto (
    id_proyecto INT AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(150) NOT NULL,
    objetivo_general    TEXT,
    metodologia         VARCHAR(120),
    numero_maximo_participantes INT NOT NULL DEFAULT 1,
    fecha_inicio        DATE,
    fecha_finalizacion  DATE,
    id_organizacion_vinculada INT NOT NULL,
    id_responsable_tecnico    INT NOT NULL,
    lugares_disponibles INT NOT NULL DEFAULT 0,
    FOREIGN KEY (id_organizacion_vinculada)
        REFERENCES organizacion_vinculada(id_organizacion_vinculada),
    FOREIGN KEY (id_responsable_tecnico)
        REFERENCES responsable_tecnico(id_responsable_tecnico)
);

CREATE TABLE estudiante (
    matricula  VARCHAR(15) PRIMARY KEY,
    nombre     VARCHAR(120) NOT NULL,
    correo     VARCHAR(120),
    telefono   VARCHAR(20),
    semestre   INT,
    promedio   DOUBLE,
    id_proyecto_asignado INT NOT NULL DEFAULT 0
);

CREATE TABLE reporte_mensual (
    id_reporte_mensual INT AUTO_INCREMENT PRIMARY KEY,
    numero_reporte   INT NOT NULL,
    mes              VARCHAR(20),
    fecha_entrega    DATE,
    horas_reportadas INT NOT NULL DEFAULT 0,
    estado           ENUM('PENDIENTE','ACEPTADO','RECHAZADO') NOT NULL DEFAULT 'PENDIENTE',
    observaciones    TEXT,
    nombre_estudiante VARCHAR(120),
    contenido        TEXT
);

CREATE TABLE autoevaluacion (
    id_autoevaluacion INT AUTO_INCREMENT PRIMARY KEY,
    matricula_estudiante VARCHAR(15) NOT NULL,
    calificaciones    VARCHAR(120),
    comentario        TEXT
);

CREATE TABLE programacion_entregas (
    id_programacion_entregas INT AUTO_INCREMENT PRIMARY KEY,
    apertura_documento_inicial DATE, limite_documento_inicial DATE,
    apertura_documento_parcial DATE, limite_documento_parcial DATE,
    apertura_documento_final   DATE, limite_documento_final   DATE,
    apertura_reporte_uno    DATE, limite_reporte_uno    DATE,
    apertura_reporte_dos    DATE, limite_reporte_dos    DATE,
    apertura_reporte_tres   DATE, limite_reporte_tres   DATE,
    apertura_reporte_cuatro DATE, limite_reporte_cuatro DATE
);

-- ----------------------------------------------------------------------------
-- Datos de ejemplo
-- ----------------------------------------------------------------------------
INSERT INTO usuario (numero_personal, contrasena, nombre_completo, tipo_usuario) VALUES
('jperez001','12345','Jorge Iran Perez Segovia','COORDINADOR'),
('aluno001','12345','John Smith','ESTUDIANTE'),
('prof001','12345','Maria Lopez Hernandez','PROFESOR');

INSERT INTO periodo (nombre, fecha_inicio, fecha_fin, activo) VALUES
('Febrero 2026 - Julio 2026','2026-02-01','2026-07-31',1);

INSERT INTO organizacion_vinculada (nombre, sector, correo, telefono, direccion, ciudad, estado) VALUES
('Tecnologias del Golfo','Tecnologia','contacto@tecgolfo.com','2281234567','Av. Xalapa 100','Xalapa','Veracruz'),
('Soluciones Veracruz','Software','rh@solucionesver.com','2289876543','Calle Enriquez 25','Veracruz','Veracruz');

INSERT INTO responsable_tecnico (nombre, departamento, puesto, correo, id_organizacion_vinculada) VALUES
('Ana Torres Ramirez','Desarrollo','Lider de Proyecto','ana.torres@tecgolfo.com',1),
('Carlos Mendoza Ruiz','Sistemas','Jefe de Sistemas','carlos.mendoza@tecgolfo.com',1),
('Laura Jimenez Soto','Calidad','Gerente de Calidad','laura.jimenez@solucionesver.com',2);

INSERT INTO proyecto (nombre, objetivo_general, metodologia, numero_maximo_participantes,
        fecha_inicio, fecha_finalizacion, id_organizacion_vinculada, id_responsable_tecnico,
        lugares_disponibles) VALUES
('Sistema de Inventario Web','Desarrollar un sistema web de control de inventario.','Scrum',3,
        '2026-02-15','2026-06-30',1,1,3),
('Aplicacion Movil de Citas','Construir una aplicacion movil para gestion de citas.','Kanban',2,
        '2026-02-15','2026-06-30',2,3,2);

INSERT INTO estudiante (matricula, nombre, correo, telefono, semestre, promedio, id_proyecto_asignado) VALUES
('S20011223','John Smith','john.smith@estudiantes.uv.mx','2281112233',8,9.4,0),
('S20014455','Diana Cruz Vega','diana.cruz@estudiantes.uv.mx','2282223344',8,9.1,0),
('S20017788','Roberto Diaz Luna','roberto.diaz@estudiantes.uv.mx','2283334455',7,8.7,0);

INSERT INTO reporte_mensual (numero_reporte, mes, fecha_entrega, horas_reportadas, estado,
        observaciones, nombre_estudiante, contenido) VALUES
(1,'Febrero','2026-02-28',40,'PENDIENTE','','John Smith',
 'Actividades de analisis de requerimientos y configuracion del entorno.'),
(2,'Marzo','2026-03-31',45,'PENDIENTE','','John Smith',
 'Diseno de la base de datos y primeros modulos del sistema.'),
(1,'Febrero','2026-02-28',38,'PENDIENTE','','Diana Cruz Vega',
 'Levantamiento de requerimientos con el responsable tecnico.');

INSERT INTO programacion_entregas () VALUES ();
