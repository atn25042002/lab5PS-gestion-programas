-- Ejemplo 1
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('java_version', 'Versión de Java utilizada en el proyecto', '11', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 2
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('python_version', 'Versión de Python utilizada en el proyecto',' 3.9', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 3
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('javascript_framework', 'Framework de JavaScript utilizado en el proyecto', 'React', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 4
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('csharp_framework', 'Framework de C# utilizado en el proyecto', '.NET Core', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 5
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('ruby_version', 'Versión de Ruby utilizada en el proyecto', '2.7.2', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 6
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('php_framework', 'Framework de PHP utilizado en el proyecto', 'Laravel', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 7
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('typescript_version', 'Versión de TypeScript utilizada en el proyecto', '4.2.3', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 8
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('go_version', 'Versión de Go utilizada en el proyecto', '1.16', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 9
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('rust_version', 'Versión de Rust utilizada en el proyecto', '1.50', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);

-- Ejemplo 10
INSERT INTO "Parametros" ("nombre", "descripcion", "valor", "parent", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('swift_version', 'Versión de Swift utilizada en el proyecto', '5.4', NULL, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 1);


-- Insertar categorías
INSERT INTO "Categoria" ("nombre", "descripcion", "imagen", "created_by", "created_at", "modify_by", "modify_at", "status")
VALUES ('Backend', 'Desarrollo del lado del servidor', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDCibmy-IM2GS5mCTQyjynJqKcNCkUk7AkSPJ2mNHE2A&s', 'Admin', '2024-01-01T10:00:00Z', 'Admin', '2024-01-01T10:00:00Z', 1),
       ('Frontend', 'Desarrollo del lado del cliente', 'https://acropolium.com/img/articles/find-backend-developers/img01.jpg', 'Admin', '2024-02-01T10:00:00Z', 'Admin', '2024-02-01T10:00:00Z', 1);

-- Insertar lecciones
INSERT INTO "Leccion" ("nombre", "descripcion", "status", "created_by", "created_at", "modify_by", "modiby_at", "id_categoria")
VALUES ('Node.js', 'Introducción a Node.js', 1, 'Profesor1', '2024-01-02T10:00:00Z', 'Admin', '2024-01-02T10:00:00Z', 1),
       ('Express.js', 'Framework para Node.js', 1, 'Profesor2', '2024-01-05T10:00:00Z', 'Admin', '2024-01-05T10:00:00Z', 1),
       ('HTML', 'Introducción a HTML', 1, 'Profesor3', '2024-02-02T10:00:00Z', 'Admin', '2024-02-02T10:00:00Z', 2),
       ('CSS', 'Introducción a CSS', 1, 'Profesor4', '2024-02-05T10:00:00Z', 'Admin', '2024-02-05T10:00:00Z', 2);

-- Insertar clases
INSERT INTO "Clase" ("nombre", "anotaciones", "url", "imagen", "ejemplo", "status", "created_by", "create_at", "modify_by", "modify_at", "leccion_id")
VALUES ('Clase 1 de Node.js', 'Anotaciones de la clase 1', 'https://example.com/clase1', 'imagen_clase1.jpg', 'Ejemplo de la clase 1', 1, 'Profesor1', '2024-01-03T10:00:00Z', 'Admin', '2024-01-03T10:00:00Z', 1),
       ('Clase 2 de Node.js', 'Anotaciones de la clase 2', 'https://example.com/clase2', 'imagen_clase2.jpg', 'Ejemplo de la clase 2', 1, 'Profesor1', '2024-01-04T10:00:00Z', 'Admin', '2024-01-04T10:00:00Z', 1),
       ('Clase 1 de Express.js', 'Anotaciones de la clase 1', 'https://example.com/clase1', 'imagen_clase1.jpg', 'Ejemplo de la clase 1', 1, 'Profesor2', '2024-01-06T10:00:00Z', 'Admin', '2024-01-06T10:00:00Z', 2),
       ('Clase 1 de HTML', 'Anotaciones de la clase 1', 'https://example.com/clase1', 'imagen_clase1.jpg', 'Ejemplo de la clase 1', 1, 'Profesor3', '2024-02-03T10:00:00Z', 'Admin', '2024-02-03T10:00:00Z', 3),
       ('Clase 2 de HTML', 'Anotaciones de la clase 2', 'https://example.com/clase2', 'imagen_clase2.jpg', 'Ejemplo de la clase 2', 1, 'Profesor3', '2024-02-04T10:00:00Z', 'Admin', '2024-02-04T10:00:00Z', 3),
       ('Clase 1 de CSS', 'Anotaciones de la clase 1', 'https://example.com/clase1', 'imagen_clase1.jpg', 'Ejemplo de la clase 1', 1, 'Profesor4', '2024-02-06T10:00:00Z', 'Admin', '2024-02-06T10:00:00Z', 4);


-- Insertar registros en la tabla Genero
INSERT INTO "Genero" (descripcion) VALUES ('Masculino');
INSERT INTO "Genero" (descripcion) VALUES ('Femenino');
INSERT INTO "Genero" (descripcion) VALUES ('Otro');

-- Insertar registros en la tabla TipoDocumento
INSERT INTO "TipoDocumento" (descripcion) VALUES ('DNI');
INSERT INTO "TipoDocumento" (descripcion) VALUES ('Pasaporte');
INSERT INTO "TipoDocumento" (descripcion) VALUES ('Carnet de identidad');

-- Insertar registros en la tabla Estudiante


INSERT INTO "Estudiante" (usuario, contraseña, id_tipo_documento, nro_documento, nombres_apellidos, id_genero, fecha_nacimiento, correo, nro_celular, puntaje_pseudocodigo, puntaje_razonamiento_math, cooperativo, facilidad_palabra, lider, ingenioso, organizado, confrontacional, aislado, no_empatico, pais, provincia, carrera, universidad, ciclo, grado_academico, nivel_ingles, experiencia_laboral, descripcion_experiencia, nivel_programacion, status, created_by, created_at, modify_by, modify_at)
VALUES
    ('usuario1', 'password1', 1, '10', 'Boris', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 1, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '11', 'David', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 1, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '12', 'Cesar', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 1, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '11', 'Juan', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 0, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '11', 'Betty', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 1, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '11', 'Angel', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 0, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP),
    ('usuario1', 'password1', 1, '11', 'Luis', 1, '1990-01-01', 'juan@example.com', '987654321', 80.5, 75.0, 'Sí', 'Sí', 'Sí', 'No', 'Sí', 'No', 'No', 'Si', 'Argentina', 'Buenos Aires', 'Ingeniería Informática', 'Universidad Nacional', 'Ciclo 3', 'Licenciatura', 'Intermedio', '2 años como desarrollador web', 'Desarrollo de aplicaciones web', 'Intermedio', 1, 'admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP);


INSERT INTO "Nivel" ("descripcion") VALUES ('Basico');
INSERT INTO "Nivel" ("descripcion") VALUES ('Intermedio');
INSERT INTO "Nivel" ("descripcion") VALUES ('Avanzado');


INSERT INTO "Nivel_Estudiante" ("id_nivel", "id_estudiante", "id_parametros")
VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 1, 3),
    (1, 1, 4),
    (2, 1, 5),
    (3, 1, 6),
    (3, 1, 7),
    (1, 1, 8),
    (2, 1, 9),
    (3, 1, 10),
    (3, 2, 1),
    (2, 2, 2),
    (1, 2, 3),
    (3, 2, 4),
    (2, 2, 5),
    (1, 2, 6),
    (3, 2, 7),
    (2, 2, 8),
    (1, 2, 9),
    (3, 2, 10),
    (1, 3, 1),
    (2, 3, 2),
    (3, 3, 3),
    (3, 3, 4),
    (2, 3, 5),
    (2, 3, 6),
    (1, 3, 7),
    (1, 3, 8),
    (2, 3, 9),
    (2, 3, 10),
    (1, 4, 1),
    (2, 4, 2),
    (3, 4, 3),
    (3, 4, 4),
    (2, 4, 5),
    (2, 4, 6),
    (1, 4, 7),
    (1, 4, 8),
    (2, 4, 9),
    (2, 4, 10),
    (1, 5, 1),
    (2, 5, 2),
    (3, 5, 3),
    (3, 5, 4),
    (2, 5, 5),
    (2, 5, 6),
    (1, 5, 7),
    (1, 5, 8),
    (2, 5, 9),
    (2, 5, 10),
    (1, 6, 1),
    (2, 6, 2),
    (3, 6, 3),
    (3, 6, 4),
    (2, 6, 5),
    (2, 6, 6),
    (1, 6, 7),
    (1, 6, 8),
    (2, 6, 9),
    (2, 6, 10),
    (1, 7, 1),
    (2, 7, 2),
    (3, 7, 3),
    (3, 7, 4),
    (2, 7, 5),
    (2, 7, 6),
    (1, 7, 7),
    (1, 7, 8),
    (2, 7, 9),
    (2, 7, 10);

-- Primera inserción
INSERT INTO "Programa" ("descripcion", "nombre", "fecha_inicio", "fecha_fin", "fecha_inicio_real", "fecha_fin_real")
VALUES
    ('Descripción del Programa 1', 'Programa 1', '2024-04-10 08:00:00', '2024-04-20 17:00:00', '2024-04-10 08:00:00', '2024-04-20 17:00:00'),
    ('Descripción del Programa 2', 'Programa 2', '2024-05-05 09:00:00', '2024-05-15 18:00:00', '2024-05-05 09:00:00', '2024-05-15 18:00:00'),
    ('Descripción del Programa 3', 'Programa 3', '2024-06-01 10:00:00', '2024-06-10 19:00:00', '2024-06-01 10:00:00', '2024-05-15 19:00:00');

INSERT INTO "Programa_Estudiante" ("id_programa", "id_estudiante", "status")
VALUES
    (1, 1, 1),
    (2, 1, 1),
    (3, 2, 1),
    (3, 3, 1),
    (1, 3, 1),
    (2, 4, 1),
    (3, 5, 1),
    (3, 6, 1),
    (1, 7, 1),
    (2, 3, 1),
    (3, 4, 1),
    (3, 7, 1);

INSERT INTO "Programa_Clase" ("created_by", "created_at", "modify_by", "modify_at", "status", "id_programa", "is_finished", "id_clase") 
VALUES 
('usuario1', '2024-04-11', 'usuario1', '2024-04-11', 1, 1, 0, 1),
('usuario2', '2024-04-12', 'usuario2', '2024-04-12', 1, 1, 0, 2),
('usuario3', '2024-04-13', 'usuario3', '2024-04-13', 1, 1, 1, 3),
('usuario1', '2024-04-11', 'usuario1', '2024-04-11', 1, 1, 0, 4),
('usuario2', '2024-04-12', 'usuario2', '2024-04-12', 1, 1, 0, 5),
('usuario3', '2024-04-13', 'usuario3', '2024-04-13', 1, 1, 1, 6),
('usuario1', '2024-04-11', 'usuario1', '2024-04-11', 1, 2, 0, 1),
('usuario2', '2024-04-12', 'usuario2', '2024-04-12', 1, 2, 0, 2),
('usuario3', '2024-04-13', 'usuario3', '2024-04-13', 1, 2, 1, 3);