/*
Created: 26/03/2024
Modified: 3/04/2024
Model: PostgreSQL 12
Database: PostgreSQL 12
*/

-- Create tables section -------------------------------------------------

-- Table Parametros

CREATE TABLE "Parametros"
(
    "id" BigSerial NOT NULL,
    "nombre" Character varying,
    "descripcion" Text,
    "valor" Character varying,
    "parent" Bigint,
    "created_by" Character varying,
    "created_at" Timestamp,
    "modify_by" Character varying,
    "modify_at" Timestamp,
    "status" Smallint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship11" ON "Parametros" ("parent")
;

ALTER TABLE "Parametros" ADD CONSTRAINT "PK_Parametros" PRIMARY KEY ("id")
;

-- Table Programa_Parametros

CREATE TABLE "Programa_Parametros"
(
    "id" BigSerial NOT NULL,
    "id_programa" Bigint NOT NULL,
    "id_parametros" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship1" ON "Programa_Parametros" ("id_programa")
;

CREATE INDEX "IX_Relationship2" ON "Programa_Parametros" ("id_parametros")
;

ALTER TABLE "Programa_Parametros" ADD CONSTRAINT "PK_Programa_Parametros" PRIMARY KEY ("id")
;

-- Table Programa

CREATE TABLE "Programa"
(
    "id" BigSerial NOT NULL,
    "descripcion" Character varying,
    "nombre" Character varying,
    "fecha_inicio" Timestamp,
    "fecha_fin" Timestamp,
    "fecha_inicio_real" Timestamp,
    "fecha_fin_real" Timestamp
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "Programa" ADD CONSTRAINT "PK_Programa" PRIMARY KEY ("id")
;

-- Table Programa_Clase

CREATE TABLE "Programa_Clase"
(
    "id" BigSerial NOT NULL,
    "created_by" Character varying,
    "created_at" Date,
    "modify_by" Character varying,
    "modify_at" Date,
    "status" Smallint,
    "id_programa" Bigint NOT NULL,
    "is_finished" Bigint,
    "id_clase" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship6" ON "Programa_Clase" ("id_programa")
;

CREATE INDEX "IX_Relationship22" ON "Programa_Clase" ("id_clase")
;

ALTER TABLE "Programa_Clase" ADD CONSTRAINT "PK_Programa_Clase" PRIMARY KEY ("id")
;

-- Table Leccion

CREATE TABLE "Leccion"
(
    "id" BigSerial NOT NULL,
    "descripcion" Character varying,
    "nombre" Character varying,
    "status" Smallint,
    "created_by" Character varying,
    "created_at" Timestamp,
    "modify_by" Character varying,
    "modiby_at" Timestamp,
    "id_categoria" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship4" ON "Leccion" ("id_categoria")
;

ALTER TABLE "Leccion" ADD CONSTRAINT "PK_Leccion" PRIMARY KEY ("id")
;

-- Table Clase

CREATE TABLE "Clase"
(
    "id" BigSerial NOT NULL,
    "nombre" Character varying,
    "anotaciones" Character varying,
    "url" Character varying,
    "imagen" Character varying,
    "ejemplo" Character varying,
    "status" Smallint,
    "created_by" Character varying,
    "create_at" Timestamp,
    "modify_by" Character varying,
    "modify_at" Timestamp,
    "leccion_id" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship3" ON "Clase" ("leccion_id")
;

ALTER TABLE "Clase" ADD CONSTRAINT "PK_Clase" PRIMARY KEY ("id")
;

-- Table Categoria

CREATE TABLE "Categoria"
(
    "id" BigSerial NOT NULL,
    "nombre" Character varying,
    "descripcion" Text,
    "imagen" Character varying,
    "created_by" Character varying,
    "created_at" Timestamp,
    "modify_by" Character varying,
    "modify_at" Timestamp,
    "status" Smallint
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "Categoria" ADD CONSTRAINT "PK_Categoria" PRIMARY KEY ("id")
;

-- Table Auditoria_Categoria

CREATE TABLE "Auditoria_Categoria"
(
    "id" BigSerial NOT NULL,
    "modify_by_email" Character varying,
    "modify_by_name" Character varying,
    "modify_at" Timestamp,
    "descripcion" Character varying,
    "categoria_id" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship7" ON "Auditoria_Categoria" ("categoria_id")
;

ALTER TABLE "Auditoria_Categoria" ADD CONSTRAINT "PK_Auditoria_Categoria" PRIMARY KEY ("id")
;

-- Table Auditoria_supervisor_mentor

CREATE TABLE "Auditoria_supervisor_mentor"
(
    "id" BigSerial NOT NULL,
    "modify_by_email" Character varying,
    "modify_by_name" Character varying,
    "modify_at" Timestamp,
    "descripcion" Character varying,
    "id_programa" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship8" ON "Auditoria_supervisor_mentor" ("id_programa")
;

ALTER TABLE "Auditoria_supervisor_mentor" ADD CONSTRAINT "PK_Auditoria_supervisor_mentor" PRIMARY KEY ("id")
;

-- Table Mentor_Programa

CREATE TABLE "Mentor_Programa"
(
    "id" BigSerial NOT NULL,
    "id_programa" Bigint NOT NULL,
    "correo" Character varying,
    "nombre" Character varying,
    "status" Smallint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship9" ON "Mentor_Programa" ("id_programa")
;

ALTER TABLE "Mentor_Programa" ADD CONSTRAINT "PK_Mentor_Programa" PRIMARY KEY ("id")
;

-- Table Supervisor_Programa

CREATE TABLE "Supervisor_Programa"
(
    "id" BigSerial NOT NULL,
    "id_programa" Bigint NOT NULL,
    "correo" Character varying,
    "nombre" Character varying,
    "status" Smallint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship10" ON "Supervisor_Programa" ("id_programa")
;

ALTER TABLE "Supervisor_Programa" ADD CONSTRAINT "PK_Supervisor_Programa" PRIMARY KEY ("id")
;

-- Table Programa_Estudiante

CREATE TABLE "Programa_Estudiante"
(
    "id" BigSerial NOT NULL,
    "id_programa" Bigint NOT NULL,
    "id_estudiante" Bigint NOT NULL,
    "status" Smallint
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship12" ON "Programa_Estudiante" ("id_programa")
;

CREATE INDEX "IX_Relationship14" ON "Programa_Estudiante" ("id_estudiante")
;

ALTER TABLE "Programa_Estudiante" ADD CONSTRAINT "PK_Programa_Estudiante" PRIMARY KEY ("id")
;

-- Table Auditoria_Programa_Estudiante

CREATE TABLE "Auditoria_Programa_Estudiante"
(
    "id" Bigint NOT NULL,
    "id_programa_estudiante" Bigint NOT NULL,
    "modify_by_email" Character varying,
    "modify_by_name" Character varying,
    "modify_at" Timestamp,
    "descripcion" Character varying
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship13" ON "Auditoria_Programa_Estudiante" ("id_programa_estudiante")
;

ALTER TABLE "Auditoria_Programa_Estudiante" ADD CONSTRAINT "PK_Auditoria_Programa_Estudiante" PRIMARY KEY ("id")
;

-- Table Estudiante

CREATE TABLE "Estudiante"
(
    "id" BigSerial NOT NULL,
    "usuario" Character varying,
    "contraseña" Character varying,
    "id_tipo_documento" Bigint NOT NULL,
    "nro_documento" Character varying,
    "nombres_apellidos" Character varying,
    "id_genero" Bigint NOT NULL,
    "fecha_nacimiento" Character varying,
    "correo" Character varying,
    "nro_celular" Character varying,
    "puntaje_pseudocodigo" Double precision,
    "puntaje_razonamiento_math" Double precision,
    "cooperativo" Character varying,
    "facilidad_palabra" Character varying,
    "lider" Character varying,
    "ingenioso" Character varying,
    "organizado" Character varying,
    "confrontacional" Character varying,
    "aislado" Character varying,
    "no_empatico" Character varying,
    "pais" Character varying,
    "provincia" Character varying,
    "carrera" Character varying,
    "universidad" Character varying,
    "ciclo" Character varying,
    "grado_academico" Character varying,
    "nivel_ingles" Character varying,
    "experiencia_laboral" Character varying,
    "descripcion_experiencia" Character varying,
    "nivel_programacion" Character varying,
    "status" Smallint,
    "created_by" Character varying,
    "created_at" Timestamp,
    "modify_by" Character varying,
    "modify_at" Timestamp
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship15" ON "Estudiante" ("id_tipo_documento")
;

CREATE INDEX "IX_Relationship16" ON "Estudiante" ("id_genero")
;

ALTER TABLE "Estudiante" ADD CONSTRAINT "PK_Estudiante" PRIMARY KEY ("id")
;

-- Table TipoDocumento

CREATE TABLE "TipoDocumento"
(
    "id" BigSerial NOT NULL,
    "descripcion" Character varying
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "TipoDocumento" ADD CONSTRAINT "PK_TipoDocumento" PRIMARY KEY ("id")
;

-- Table Genero

CREATE TABLE "Genero"
(
    "id" BigSerial NOT NULL,
    "descripcion" Character varying
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "Genero" ADD CONSTRAINT "PK_Genero" PRIMARY KEY ("id")
;

-- Table Nivel

CREATE TABLE "Nivel"
(
    "id" BigSerial NOT NULL,
    "descripcion" Character varying
)
    WITH (
        autovacuum_enabled=true)
;

ALTER TABLE "Nivel" ADD CONSTRAINT "PK_Nivel" PRIMARY KEY ("id")
;

-- Table Nivel_Estudiante

CREATE TABLE "Nivel_Estudiante"
(
    "id" BigSerial NOT NULL,
    "id_nivel" Bigint NOT NULL,
    "id_estudiante" Bigint NOT NULL,
    "id_parametros" Bigint NOT NULL
)
    WITH (
        autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship17" ON "Nivel_Estudiante" ("id_estudiante")
;

CREATE INDEX "IX_Relationship18" ON "Nivel_Estudiante" ("id_nivel")
;

CREATE INDEX "IX_Relationship19" ON "Nivel_Estudiante" ("id_parametros")
;

ALTER TABLE "Nivel_Estudiante" ADD CONSTRAINT "PK_Nivel_Estudiante" PRIMARY KEY ("id")
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE "Programa_Parametros"
    ADD CONSTRAINT "Relationship1"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Programa_Parametros"
    ADD CONSTRAINT "Relationship2"
        FOREIGN KEY ("id_parametros")
            REFERENCES "Parametros" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Clase"
    ADD CONSTRAINT "Relationship3"
        FOREIGN KEY ("leccion_id")
            REFERENCES "Leccion" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Leccion"
    ADD CONSTRAINT "Relationship4"
        FOREIGN KEY ("id_categoria")
            REFERENCES "Categoria" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Programa_Clase"
    ADD CONSTRAINT "Relationship6"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Auditoria_Categoria"
    ADD CONSTRAINT "Relationship7"
        FOREIGN KEY ("categoria_id")
            REFERENCES "Categoria" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Auditoria_supervisor_mentor"
    ADD CONSTRAINT "Relationship8"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Mentor_Programa"
    ADD CONSTRAINT "Relationship9"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Supervisor_Programa"
    ADD CONSTRAINT "Relationship10"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Parametros"
    ADD CONSTRAINT "Relationship11"
        FOREIGN KEY ("parent")
            REFERENCES "Parametros" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
    NOT VALID
;

ALTER TABLE "Programa_Estudiante"
    ADD CONSTRAINT "Relationship12"
        FOREIGN KEY ("id_programa")
            REFERENCES "Programa" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Auditoria_Programa_Estudiante"
    ADD CONSTRAINT "Relationship13"
        FOREIGN KEY ("id_programa_estudiante")
            REFERENCES "Programa_Estudiante" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Programa_Estudiante"
    ADD CONSTRAINT "Relationship14"
        FOREIGN KEY ("id_estudiante")
            REFERENCES "Estudiante" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Estudiante"
    ADD CONSTRAINT "Relationship15"
        FOREIGN KEY ("id_tipo_documento")
            REFERENCES "TipoDocumento" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Estudiante"
    ADD CONSTRAINT "Relationship16"
        FOREIGN KEY ("id_genero")
            REFERENCES "Genero" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Nivel_Estudiante"
    ADD CONSTRAINT "Relationship17"
        FOREIGN KEY ("id_estudiante")
            REFERENCES "Estudiante" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Nivel_Estudiante"
    ADD CONSTRAINT "Relationship18"
        FOREIGN KEY ("id_nivel")
            REFERENCES "Nivel" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Nivel_Estudiante"
    ADD CONSTRAINT "Relationship19"
        FOREIGN KEY ("id_parametros")
            REFERENCES "Parametros" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

ALTER TABLE "Programa_Clase"
    ADD CONSTRAINT "Relationship22"
        FOREIGN KEY ("id_clase")
            REFERENCES "Clase" ("id")
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
;

