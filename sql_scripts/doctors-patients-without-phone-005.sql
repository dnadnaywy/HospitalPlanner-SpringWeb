delete from patients;
/
delete from doctors;
/

SET SERVEROUTPUT ON;
DECLARE
  TYPE varr IS VARRAY(1000) OF varchar2(255);
  name_list varr := varr('Ababei','Acasandrei','Adascalitei','Afanasie','Agafitei','Agape','Aioanei','Alexandrescu','Alexandru','Alexe','Alexii','Amarghioalei','Ambroci','Andonesei','Andrei','Andrian','Andrici','Andronic','Andros','Anghelina','Anita','Antochi','Antonie','Apetrei','Apostol','Arhip','Arhire','Arteni','Arvinte','Asaftei','Asofiei','Aungurenci','Avadanei','Avram','Babei','Baciu','Baetu','Balan','Balica','Banu','Barbieru','Barzu','Bazgan','Bejan','Bejenaru','Belcescu','Belciuganu','Benchea','Bilan','Birsanu','Bivol','Bizu','Boca','Bodnar','Boistean','Borcan','Bordeianu','Botezatu','Bradea','Braescu','Budaca','Bulai','Bulbuc-aioanei','Burlacu','Burloiu','Bursuc','Butacu','Bute','Buza','Calancea','Calinescu','Capusneanu','Caraiman','Carbune','Carp','Catana','Catiru','Catonoiu','Cazacu','Cazamir','Cebere','Cehan','Cernescu','Chelaru','Chelmu','Chelmus','Chibici','Chicos','Chilaboc','Chile','Chiriac','Chirila','Chistol','Chitic','Chmilevski','Cimpoesu','Ciobanu','Ciobotaru','Ciocoiu','Ciofu','Ciornei','Citea','Ciucanu','Clatinici','Clim','Cobuz','Coca','Cojocariu','Cojocaru','Condurache','Corciu','Corduneanu','Corfu','Corneanu','Corodescu','Coseru','Cosnita','Costan','Covatariu','Cozma','Cozmiuc','Craciunas','Crainiceanu','Creanga','Cretu','Cristea','Crucerescu','Cumpata','Curca','Cusmuliuc','Damian','Damoc','Daneliuc','Daniel','Danila','Darie','Dascalescu','Dascalu','Diaconu','Dima','Dimache','Dinu','Dobos','Dochitei','Dochitoiu','Dodan','Dogaru','Domnaru','Dorneanu','Dragan','Dragoman','Dragomir','Dragomirescu','Duceac','Dudau','Durnea','Edu','Eduard','Eusebiu','Fedeles','Ferestraoaru','Filibiu','Filimon','Filip','Florescu','Folvaiter','Frumosu','Frunza','Galatanu','Gavrilita','Gavriliuc','Gavrilovici','Gherase','Gherca','Ghergu','Gherman','Ghibirdic','Giosanu','Gitlan','Giurgila','Glodeanu','Goldan','Gorgan','Grama','Grigore','Grigoriu','Grosu','Grozavu','Gurau','Haba','Harabula','Hardon','Harpa','Herdes','Herscovici','Hociung','Hodoreanu','Hostiuc','Huma','Hutanu','Huzum','Iacob','Iacobuta','Iancu','Ichim','Iftimesei','Ilie','Insuratelu','Ionesei','Ionesi','Ionita','Iordache','Iordache-tiroiu','Iordan','Iosub','Iovu','Irimia','Ivascu','Jecu','Jitariuc','Jitca','Joldescu','Juravle','Larion','Lates','Latu','Lazar','Leleu','Leon','Leonte','Leuciuc','Leustean','Luca','Lucaci','Lucasi','Luncasu','Lungeanu','Lungu','Lupascu','Lupu','Macariu','Macoveschi','Maftei','Maganu','Mangalagiu','Manolache','Manole','Marcu','Marinov','Martinas','Marton','Mataca','Matcovici','Matei','Maties','Matrana','Maxim','Mazareanu','Mazilu','Mazur','Melniciuc-puica','Micu','Mihaela','Mihai','Mihaila','Mihailescu','Mihalachi','Mihalcea','Mihociu','Milut','Minea','Minghel','Minuti','Miron','Mitan','Moisa','Moniry-abyaneh','Morarescu','Morosanu','Moscu','Motrescu','Motroi','Munteanu','Murarasu','Musca','Mutescu','Nastaca','Nechita','Neghina','Negrus','Negruser','Negrutu','Nemtoc','Netedu','Nica','Nicu','Oana','Olanuta','Olarasu','Olariu','Olaru','Onu','Opariuc','Oprea','Ostafe','Otrocol','Palihovici','Pantiru','Pantiruc','Paparuz','Pascaru','Patachi','Patras','Patriche','Perciun','Perju','Petcu','Pila','Pintilie','Piriu','Platon','Plugariu','Podaru','Poenariu','Pojar','Popa','Popescu','Popovici','Poputoaia','Postolache','Predoaia','Prisecaru','Procop','Prodan','Puiu','Purice','Rachieru','Razvan','Reut','Riscanu','Riza','Robu','Roman','Romanescu','Romaniuc','Rosca','Rusu','Samson','Sandu','Sandulache','Sava','Savescu','Schifirnet','Scortanu','Scurtu','Sfarghiu','Silitra','Simiganoschi','Simion','Simionescu','Simionesei','Simon','Sitaru','Sleghel','Sofian','Soficu','Sparhat','Spiridon','Stan','Stavarache','Stefan','Stefanita','Stingaciu','Stiufliuc','Stoian','Stoica','Stoleru','Stolniceanu','Stolnicu','Strainu','Strimtu','Suhani','Tabusca','Talif','Tanasa','Teclici','Teodorescu','Tesu','Tifrea','Timofte','Tincu','Tirpescu','Toader','Tofan','Toma','Toncu','Trifan','Tudosa','Tudose','Tuduri','Tuiu','Turcu','Ulinici','Unghianu','Ungureanu','Ursache','Ursachi','Urse','Ursu','Varlan','Varteniuc','Varvaroi','Vasilache','Vasiliu','Ventaniuc','Vicol','Vidru','Vinatoru','Vlad','Voaides','Vrabie','Vulpescu','Zamosteanu','Zazuleac');
  girl_names varr := varr('Adina','Alexandra','Alina','Ana','Anca','Anda','Andra','Andreea','Andreia','Antonia','Bianca','Camelia','Claudia','Codrina','Cristina','Daniela','Daria','Delia','Denisa','Diana','Ecaterina','Elena','Eleonora','Elisa','Ema','Emanuela','Emma','Gabriela','Georgiana','Ileana','Ilona','Ioana','Iolanda','Irina','Iulia','Iuliana','Larisa','Laura','Loredana','Madalina','Malina','Manuela','Maria','Mihaela','Mirela','Monica','Oana','Paula','Petruta','Raluca','Sabina','Sanziana','Simina','Simona','Stefana','Stefania','Tamara','Teodora','Theodora','Vasilica','Xena');
  boy_names varr := varr('Adrian','Alex','Alexandru','Alin','Andreas','Andrei','Aurelian','Beniamin','Bogdan','Camil','Catalin','Cezar','Ciprian','Claudiu','Codrin','Constantin','Corneliu','Cosmin','Costel','Cristian','Damian','Dan','Daniel','Danut','Darius','Denise','Dimitrie','Dorian','Dorin','Dragos','Dumitru','Eduard','Elvis','Emil','Ervin','Eugen','Eusebiu','Fabian','Filip','Florian','Florin','Gabriel','George','Gheorghe','Giani','Giulio','Iaroslav','Ilie','Ioan','Ion','Ionel','Ionut','Iosif','Irinel','Iulian','Iustin','Laurentiu','Liviu','Lucian','Marian','Marius','Matei','Mihai','Mihail','Nicolae','Nicu','Nicusor','Octavian','Ovidiu','Paul','Petru','Petrut','Radu','Rares','Razvan','Richard','Robert','Roland','Rolland','Romanescu','Sabin','Samuel','Sebastian','Sergiu','Silviu','Stefan','Teodor','Teofil','Theodor','Tudor','Vadim','Valentin','Valeriu','Vasile','Victor','Vlad','Vladimir','Vladut');
  medical_fields varr := varr('Urology', 'Neurology', 'Cardiology', 'ORL', 'Gastroenterology', 'Pneumology');
  medical_grades varr := varr('Primar', 'Specialist', 'Rezident', 'Generalist');
       
  v_name VARCHAR2(255);
  v_prename VARCHAR2(255);
  v_prename1 VARCHAR2(255);
  v_prename2 VARCHAR2(255);
  v_age INT;  
  v_email varchar2(40);
  v_specialization VARCHAR2(255);
  v_medical_grade VARCHAR2(255);
  v_insurance INT;
  v_illness_category VARCHAR2(255);
BEGIN
  
   DBMS_OUTPUT.PUT_LINE('Inserarea a 1025 pacienti...');
   FOR v_i IN 1..1025 LOOP
      --SELECT SUBSTR(NAME, INSTR(NAME,' ')) INTO v_name FROM (SELECT * FROM USERS WHERE INSTR(NAME,' ')>1 ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM=1;
      --IF length(v_name)>20 then v_name:=substr(v_name,1,20); end if;
      
      --------------------alegere nume de familie------------------------------
      v_name := name_list(TRUNC(DBMS_RANDOM.VALUE(0,name_list.count))+1);
      
      ---------alegere prenume----------
      IF (DBMS_RANDOM.VALUE(0,100)<50) THEN      
         v_prename1 := girl_names(TRUNC(DBMS_RANDOM.VALUE(0,girl_names.count))+1);
         LOOP
            v_prename2 := girl_names(TRUNC(DBMS_RANDOM.VALUE(0,girl_names.count))+1);
            exit when v_prename1<>v_prename2;
         END LOOP;
       ELSE
         v_prename1 := boy_names(TRUNC(DBMS_RANDOM.VALUE(0,boy_names.count))+1);
         LOOP
            v_prename2 := boy_names(TRUNC(DBMS_RANDOM.VALUE(0,boy_names.count))+1);
            exit when v_prename1<>v_prename2;
         END LOOP;       
       END IF;
     
     IF (DBMS_RANDOM.VALUE(0,100)<60) THEN  
        IF LENGTH(v_prename1 || ' ' || v_prename2) <= 20 THEN
          v_prename := v_prename1 || ' ' || v_prename2;
        END IF;
        else 
           v_prename:=v_prename1;
      END IF;    
          
      -----alegere varsta
      v_age := TRUNC(DBMS_RANDOM.VALUE(10,90));
      
      ----------------------alegere email--------------
      v_email := lower(v_name ||'.'|| v_prename1);
      
      v_email := v_email ||'@gmail.com';
      
      ---------medical insurance------
      
      v_insurance := TRUNC(DBMS_RANDOM.VALUE(0,2));
      
      -------illness_category------
      
      v_illness_category := medical_fields(TRUNC(DBMS_RANDOM.VALUE(0,medical_fields.count))+1);
                      
      --DBMS_OUTPUT.PUT_LINE (v_name||' '||v_prename ||' '|| v_age||' '|| v_email);      
      insert into patients (name, age, email, medical_insurance, illness_category) values(v_name || ' ' || v_prename, v_age, v_email, v_insurance, v_illness_category);
   END LOOP;
   DBMS_OUTPUT.PUT_LINE('Inserarea a 1025 pacienti... GATA !');


   DBMS_OUTPUT.PUT_LINE('Inserarea a 20 de doctori...');
   FOR v_i IN 1..20 LOOP
      --SELECT SUBSTR(NAME, INSTR(NAME,' ')) INTO v_name FROM (SELECT * FROM USERS WHERE INSTR(NAME,' ')>1 ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM=1;
      --IF length(v_name)>20 then v_name:=substr(v_name,1,20); end if;
      
      --------------------alegere nume de familie------------------------------
      v_name := name_list(TRUNC(DBMS_RANDOM.VALUE(0,name_list.count))+1);
      
      ---------alegere prenume----------
      IF (DBMS_RANDOM.VALUE(0,100)<50) THEN      
         v_prename1 := girl_names(TRUNC(DBMS_RANDOM.VALUE(0,girl_names.count))+1);
         LOOP
            v_prename2 := girl_names(TRUNC(DBMS_RANDOM.VALUE(0,girl_names.count))+1);
            exit when v_prename1<>v_prename2;
         END LOOP;  
       END IF;
     
     IF (DBMS_RANDOM.VALUE(0,100)<60) THEN  
        IF LENGTH(v_prename1 || ' ' || v_prename2) <= 20 THEN
          v_prename := v_prename1 || ' ' || v_prename2;
        END IF;
        else 
           v_prename:=v_prename1;
      END IF;    
          
      -----alegere varsta
      v_age := TRUNC(DBMS_RANDOM.VALUE(10,90));
      
      ----------------------alegere email--------------
      v_email := lower(v_name ||'.'|| v_prename1);
      
      v_email := v_email ||'@gmail.com';
      
      ---------specialisation------
      
      v_specialization := medical_fields(TRUNC(DBMS_RANDOM.VALUE(0,medical_fields.count))+1);
      
      -------illness_category------
      
      v_medical_grade := medical_grades(TRUNC(DBMS_RANDOM.VALUE(0,medical_grades.count))+1);
                      
      --DBMS_OUTPUT.PUT_LINE (v_name||' '||v_prename ||' '|| v_age||' '|| v_email);      
      insert into doctors (name, age, email, specialization, medical_grade) values(v_name || ' ' || v_prename, v_age, v_email, v_specialization, v_medical_grade);
   END LOOP;
   DBMS_OUTPUT.PUT_LINE('Inserarea a 20 doctori... GATA !');
END;
/

select * from patients;
/
select * from doctors;
/