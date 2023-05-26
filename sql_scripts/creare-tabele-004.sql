--drop table doctors;
--drop table patients;
--drop table patients_preferences;
--drop table patients_appointments;
--drop table doctors_schedules;
----------------------------------doctors table--------------------------------
CREATE SEQUENCE doctors_seq START WITH 1;
/
CREATE TABLE doctors (
    id INT PRIMARY KEY,
    name VARCHAR2(255)  NOT NULL,         --am scos UNIQUE de aici ca pot fi duplicate la nume
    age INT, 
    email VARCHAR2(50),                 --aici nu putem avea duplicat tho, trb sa vedem ce facem
    specialization VARCHAR2(255),
    medical_grade VARCHAR2(255)
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
    name VARCHAR2(255) NOT NULL,            --aici am scos UNIQUE, putem avea ac nume
    age INT, 
    email VARCHAR2(50),                     --aici ar trb sa fie unique tho, sa vedem ce facem
    medical_insurance INT NOT NULL,
    illness_category VARCHAR2(255)
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