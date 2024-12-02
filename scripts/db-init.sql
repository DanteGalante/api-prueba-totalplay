-- Crear el usuario (esquema)
CREATE USER C##totalplay_prueba IDENTIFIED BY total_play123;
GRANT CONNECT, RESOURCE TO C##totalplay_prueba;
ALTER USER C##TOTALPLAY_PRUEBA QUOTA 100M ON USERS;

-- Conectar al esquema
ALTER SESSION SET CURRENT_SCHEMA = C##totalplay_prueba;

-- Crear la secuencia para la clave primaria
CREATE SEQUENCE personal_seq START WITH 1 INCREMENT BY 1 NOCACHE;

-- Crear la tabla personal
CREATE TABLE personal (
    id NUMBER PRIMARY KEY,
    foto_url VARCHAR2(255),
    nombre VARCHAR2(35) NOT NULL,
    apellido_paterno VARCHAR2(35) NOT NULL,
    apellido_materno VARCHAR2(35) NOT NULL,
    telefono VARCHAR2(15) NOT NULL,
    correo_electronico VARCHAR2(50) NOT NULL UNIQUE,
    fecha_ingreso DATE NOT NULL,
    sueldo NUMBER(10, 2) NOT NULL,
    descripcion_funciones CLOB,
    CONSTRAINT chk_nombre CHECK (REGEXP_LIKE(nombre, '^[A-Z]+$')),
    CONSTRAINT chk_apellido_paterno CHECK (REGEXP_LIKE(apellido_paterno, '^[A-Z]+$')),
    CONSTRAINT chk_apellido_materno CHECK (REGEXP_LIKE(apellido_materno, '^[A-Z]+$'))
);

-- Crear el trigger para auto-incrementar el campo id
CREATE OR REPLACE TRIGGER personal_bir
FOR EACH ROW
BEGIN
  IF :NEW.id IS NULL THEN
    SELECT personal_seq.NEXTVAL INTO :NEW.id FROM dual;
  END IF;
END;
/