DROP TABLE doctors CASCADE CONSTRAINTS
/
DROP TABLE patients CASCADE CONSTRAINTS
/
DROP TABLE doctors_schedules CASCADE CONSTRAINTS
/
DROP TABLE patients_appointments CASCADE CONSTRAINTS
/
DROP TABLE patients_preferences CASCADE CONSTRAINTS
/
DROP SEQUENCE doctors_seq;
/
DROP SEQUENCE patients_seq;
/
----------------------------------doctors table--------------------------------
CREATE SEQUENCE doctors_seq START WITH 1;
/
CREATE TABLE doctors (
    id INT PRIMARY KEY,
    name VARCHAR2(255) UNIQUE NOT NULL,
    specialization VARCHAR2(255),
    phone_number VARCHAR2(20) UNIQUE,
    email VARCHAR2(100) UNIQUE,
    phone VARCHAR(20) UNIQUE,
    password VARCHAR(100),
    medical_grade VARCHAR(20)
);
/
CREATE OR REPLACE TRIGGER doctors_trigger
    BEFORE INSERT ON doctors
    FOR EACH ROW
BEGIN
  SELECT doctors_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

------------------------patients table---------------------------------

CREATE SEQUENCE patients_seq START WITH 1;
/
CREATE TABLE patients (
    id INT PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    age INT, 
    medical_insurance INT NOT NULL,
    illness_category VARCHAR2(255),
    phone_number VARCHAR2(20),
    email VARCHAR2(100),
    password VARCHAR(100)
);
/
CREATE OR REPLACE TRIGGER patients_trigger
    BEFORE INSERT ON patients
    FOR EACH ROW
BEGIN
  SELECT patients_seq.NEXTVAL INTO :NEW.id FROM DUAL;
END;
/

---------------------------------doctors_schedules-----------------------------

CREATE TABLE doctors_schedules (
    id_doctor INT,
    day_of_the_week INT, 
    hour VARCHAR2(10)
);
/
------------------------------------------patients_appointments----------------

CREATE TABLE patients_appointments (
    id_patient INT,
    id_doctor INT,
    day_of_the_week INT, 
    hour VARCHAR2(10)
);
/
----------------------------patients_preferences-----------------------------

CREATE TABLE patients_preferences (
    id_patient INT,
    id_doctor INT,
    preference_order INT  --de la 1 la nr de doctori la specializarea respectiva
);
/

-------------------------------------------------------------------------------

select * from patients;
