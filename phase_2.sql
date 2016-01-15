/* select tablespace_name, table_name from user_tables; */
DROP TABLE Account CASCADE CONSTRAINTS;
DROP TABLE Credit_Card CASCADE CONSTRAINTS;
DROP TABLE ActorsActress CASCADE CONSTRAINTS;
DROP TABLE Movie CASCADE CONSTRAINTS;
DROP TABLE Genre CASCADE CONSTRAINTS;
DROP TABLE Profile CASCADE CONSTRAINTS;
DROP TABLE has_creditcard_num CASCADE CONSTRAINTS;
DROP TABLE preferred_actors CASCADE CONSTRAINTS;
DROP TABLE preferred_movie_genres CASCADE CONSTRAINTS;
DROP TABLE starred_in CASCADE CONSTRAINTS;
DROP TABLE movie_genre CASCADE CONSTRAINTS;
DROP TABLE rental_history CASCADE CONSTRAINTS;

CREATE TABLE Account(
Member_ID INTEGER,
first_name VARCHAR(20),
last_name VARCHAR(20),
birthday DATE,
gender CHAR(1) NOT NULL CHECK (gender IN ('M','F')),
address VARCHAR(50),
email_address VARCHAR(50),
PRIMARY KEY(Member_ID)); 

CREATE TABLE Credit_Card(
Card_Number CHAR(16),
first_name VARCHAR(20),
last_name VARCHAR(20),
expiration_date DATE,
security_code INTEGER,
brand_name VARCHAR(20),
PRIMARY KEY (Card_Number));

CREATE TABLE ActorsActress(
Actor_ID INTEGER,
first_name VARCHAR(20),
last_name VARCHAR(20),
birthday DATE,
gender CHAR(1) NOT NULL CHECK (gender IN ('M','F')),
PRIMARY KEY(Actor_ID));

CREATE TABLE Movie(
Movie_ID INTEGER,
name VARCHAR(50),
producer VARCHAR(50),
year_produced DATE,
movie_length INTEGER,
cast VARCHAR(150),
average_rating REAL CHECK (average_rating BETWEEN 1 AND 5),
PRIMARY KEY(Movie_ID));

CREATE TABLE Genre(
genre_type VARCHAR(20),
PRIMARY KEY (genre_type));

CREATE TABLE Profile(
Member_ID INTEGER,
profile_name VARCHAR(20),
PRIMARY KEY(Member_ID, profile_name),
FOREIGN KEY(Member_ID) REFERENCES Account(Member_ID)
ON DELETE CASCADE); 

CREATE TABLE has_creditcard_num(
Member_ID INTEGER,
Card_Number CHAR(16),
PRIMARY KEY(Member_ID, Card_Number),
FOREIGN KEY(Member_ID) REFERENCES Account (Member_ID), 
FOREIGN KEY(Card_Number) REFERENCES Credit_Card (Card_Number)
ON DELETE CASCADE);

CREATE TABLE preferred_actors(
Actor_ID INTEGER,
profile_name VARCHAR(20),
Member_ID INTEGER,
PRIMARY KEY(Actor_ID, profile_name, Member_ID),
FOREIGN KEY(Actor_ID) REFERENCES ActorsActress (Actor_ID), 
FOREIGN KEY(Member_ID) REFERENCES Account (Member_ID)
ON DELETE CASCADE);

CREATE TABLE preferred_movie_genres(
genre_type VARCHAR(20),
profile_name VARCHAR(20),
Member_ID INTEGER,
PRIMARY KEY(genre_type, profile_name, Member_ID),
FOREIGN KEY(genre_type) REFERENCES Genre (genre_type), 
FOREIGN KEY(Member_ID) REFERENCES Account (Member_ID)
ON DELETE CASCADE); 

CREATE TABLE starred_in(
Actor_ID INTEGER,
Movie_ID INTEGER,
PRIMARY KEY(Actor_ID,Movie_ID),
FOREIGN KEY(Actor_ID) REFERENCES ActorsActress (Actor_ID), 
FOREIGN KEY(Movie_ID) REFERENCES Movie(Movie_ID)
ON DELETE CASCADE);

CREATE TABLE movie_genre(
Movie_ID INTEGER,
genre_type VARCHAR(20),
PRIMARY KEY(genre_type,Movie_ID),
FOREIGN KEY(genre_type) REFERENCES Genre (genre_type), 
FOREIGN KEY(Movie_ID) REFERENCES Movie (Movie_ID)
ON DELETE CASCADE);

CREATE TABLE rental_history(
rating INTEGER CHECK (rating BETWEEN 1 AND 5),
review VARCHAR(200),
Member_ID INTEGER,
Movie_ID INTEGER,
profile_name VARCHAR(20),
PRIMARY KEY(Movie_ID, profile_name, Member_ID),
FOREIGN KEY(Movie_ID) REFERENCES Movie (Movie_ID),
FOREIGN KEY(Member_ID) REFERENCES Account (Member_ID)
ON DELETE CASCADE); 

INSERT INTO ACCOUNT VALUES (1,'Joan','Cray', '21-OCT-1960','F','1618 Richison Drive Eureka Rural, MT 59917','JoanDCray@teleworm.us');
INSERT INTO ACCOUNT VALUES (2,'Leroy', 'Ladouceur','14-OCT-1978','M','2185 Pearlman Avenue Sudbury, MA 01776','LeroyLLadouceur@dayrep.com');
INSERT INTO ACCOUNT VALUES (3,'Matthew', 'Padilla','16-FEB-1981','M','4100 Poplar Chase Lane Boise, ID 83702','MatthewVPadilla@jourrapide.com');
INSERT INTO ACCOUNT VALUES (4,'Donald', 'Nelson','09-FEB-1953','M','2527 Kuhl Avenue Gainesville, GA 30501','DonaldBNelson@teleworm.us');
INSERT INTO ACCOUNT VALUES (5,'Elvira', 'Riley','04-DEC-1955','F','3390 Queens Lane Danville, VA 24541','ElviraRRiley@armyspy.com');

INSERT INTO MOVIE VALUES (1, 'Mad Max: Fury Road','George Miller', '20-JAN-2015',120,'Tom Hardy, Charlize Theron, Nicholas Hoult, Hugh Keays-Byrne,Nathan Jones',5);
INSERT INTO MOVIE VALUES (2, 'Inside Out', 'Pete Docter', '13-Jun-2015',94, 'Amy Poehler, Bill Hader, Lewis Black, Mindy Kaling, Phyllis Smith',4.5);
INSERT INTO MOVIE VALUES (3, 'Mission: Impossible Rogue Nation','Christopher McQuarrie', '15-DEC-2015',132,'Tom Cruise, Jeremy Renner, Simon Pegg, Rebecca Ferguson, Ving Rhames',4);
INSERT INTO MOVIE VALUES (4, 'Harry Potter and the Philosopher''s Stone','J. K. Rowling', '04-NOV-2001',152,'Daniel Radcliffe, Rupert Grint, Emma Watson, John Cleese',4.5);
INSERT INTO MOVIE VALUES (5, 'Se7en','Andrew Kevin Walker', '22-SEP-1995',127,'	Brad Pitt, Morgan Freeman, Kevin Spacey, John C. McGinley, Gwyneth Paltrow',4.75);
INSERT INTO MOVIE VALUES (6, 'Harry Potter and the Chamber of Secrets', 'J. K. Rowling', '03-NOV-2002',135, 'Daniel Radcliffe, Rupert Grint, Emma Watson, John Cleese', 4.12);
INSERT INTO MOVIE VALUES (7, 'Harry Potter and the Prisoner of Azkaban', 'J. K. Rowling', '23-MAY-2004',137, 'Daniel Radcliffe, Rupert Grint, Emma Watson, John Cleese', 4.55);
INSERT INTO MOVIE VALUES (8, 'Gone Girl', 'David Fincher', '03-OCT-2014',149, 'Ben Affleck, Rosamund Pike, Neil Patrick Harris, Tyler Perry', 4.91);
INSERT INTO MOVIE VALUES (9, 'The Town', 'Ben Affleck', '08-SEP-2010', 124, 'Ben Affleck, Rebecca Hall, Jon Hamm, Jeremy Renner, Blake Lively, Titus Welliver, Pete Postlethwaite, Chris Cooper',4.60); 
INSERT INTO MOVIE VALUES (10, 'Ip Man','Wilson Yip','18-DEC-2008',108, 'Donnie Yen, Simon Yam, Lynn Hung, Gordon Lam, Fan Siu-wong, Xing Yu',3.75);

INSERT INTO ActorsActress VALUES (1,'Ben', 'Affleck','01-JAN-1970', 'M');
INSERT INTO ActorsActress VALUES (2, 'Tom', 'Hardy', '02-FEB-1971', 'M');
INSERT INTO ActorsActress VALUES (3,'Daniel','Radcliffe', '03-MAR-1972', 'M');
INSERT INTO ActorsActress VALUES (4,'Brad', 'Pitt', '04-APR-1973', 'M');
INSERT INTO ActorsActress VALUES (5,'Kevin','Spacey', '05-MAY-1974', 'M');
INSERT INTO ActorsActress VALUES (6, 'Tom', 'Cruise', '06-JUN-1975', 'M');
INSERT INTO ActorsActress VALUES (7, 'Donnie', 'Yen', '07-JUL-1976', 'M');
INSERT INTO ActorsActress VALUES (8, 'Morgan', 'Freeman', '08-AUG-1977','M');
INSERT INTO ActorsActress VALUES (9,'Bil', 'Hardy', '09-SEP-1978', 'M');
INSERT INTO ActorsActress VALUES (10,'Nicholas', 'Hoult', '10-OCT-1979', 'M');
INSERT INTO ActorsActress VALUES (11,'Amy', 'Poehler', '11-NOV-1980', 'F');
INSERT INTO ActorsActress VALUES (12,'Emma', 'Watson', '12-DEC-1990', 'F');
INSERT INTO ActorsActress VALUES (13,'Rebecca', 'Hall', '13-DEC-1981', 'F');
INSERT INTO ActorsActress VALUES (14,'Gwyneth', 'Paltrow', '14-DEC-1982', 'F');
INSERT INTO ActorsActress VALUES (15, 'Charlize', 'Theron', '20-JUL-1984', 'F');
INSERT INTO ActorsActress VALUES (16,'Mindy', 'Kaling', '21-JAN-1982', 'F');
INSERT INTO ActorsActress VALUES (17,'Phyllis', 'Smith', '14-MAR-1960','F');
INSERT INTO ActorsActress VALUES (18,'Blake', 'Lively', '31-OCT-1981','F');
INSERT INTO ActorsActress VALUES (19,'Jessica','Alba', '12-AUG-1985','F');
INSERT INTO ActorsActress VALUES (20,'Rebecca', 'Ferguson', '19-APR-1980', 'F');

INSERT INTO Genre VALUES ('Action');
INSERT INTO Genre VALUES ('Thriller');
INSERT INTO Genre VALUES ('Comedy');
INSERT INTO Genre VALUES ('Romance');
INSERT INTO Genre VALUES ('Drama');
INSERT INTO Genre VALUES ('Adventure');
INSERT INTO Genre VALUES ('Horror');
INSERT INTO Genre VALUES ('Science Fiction');
INSERT INTO Genre VALUES ('Crime');
INSERT INTO Genre VALUES ('Fantasy');
INSERT INTO Genre VALUES ('Historical');
INSERT INTO Genre VALUES ('Mystery');
INSERT INTO Genre VALUES ('Animation');
INSERT INTO Genre VALUES ('Live Action');
INSERT INTO Genre VALUES ('International');

INSERT INTO Profile VALUES (1,'Family');
INSERT INTO Profile VALUES (1, 'Kids');
INSERT INTO Profile VALUES (2,'Family Channels');
INSERT INTO Profile VALUES (2, 'Kids and Babies');
INSERT INTO Profile VALUES (3,'Family');
INSERT INTO Profile VALUES (3, 'Kids Show');
INSERT INTO Profile VALUES (4,'Family Time');
INSERT INTO Profile VALUES (4, 'Kids');
INSERT INTO Profile VALUES (5,'Children');
INSERT INTO Profile VALUES (5, 'Mature');
INSERT INTO Profile VALUES (1,'John');
INSERT INTO Profile VALUES (2, 'Friends');

INSERT INTO Credit_Card VALUES ('4063524279185495','Joan','Cray','12-AUG-2017',125,'VISA');
INSERT INTO Credit_Card VALUES ('4532209605765367','Leroy','Ladouceur','12-AUG-2018',545,'CHASE SAPPHIRE');
INSERT INTO Credit_Card VALUES ('4539186904688230','Joan','Cray','12-AUG-2018',135,'DISCOVERY');
INSERT INTO Credit_Card VALUES ('4929320448302705','Matthew','Padilla','31-DEC-2019',845,'CHASE FREEDOM');
INSERT INTO Credit_Card VALUES ('3036995039870421','Matthew','Padilla','11-SEP-2020',108,'MASTER CARD');
INSERT INTO Credit_Card VALUES ('6011687777020163','Donald','Nelson','22-JUN-2015',151,'WELLS FARGO');
INSERT INTO Credit_Card VALUES ('5264227140695627','Donald','Nelson','16-MAY-2023',652,'CITIBANK');
INSERT INTO Credit_Card VALUES ('6011099814444192','Leroy','Ladouceur','18-AUG-2016',329,'CAPTIAL ONE');
INSERT INTO Credit_Card VALUES ('3411860886285942','Elvira','Riley','02-APR-2019',784,'BBT');
INSERT INTO Credit_Card VALUES ('3786855644987094','Elvira','Riley','02-MAR-2021',168,'SUNTRUST');

INSERT INTO has_creditcard_num VALUES(1,'4063524279185495');
INSERT INTO has_creditcard_num VALUES(1,'4539186904688230');
INSERT INTO has_creditcard_num VALUES(2,'4532209605765367');
INSERT INTO has_creditcard_num VALUES(2,'6011099814444192');
INSERT INTO has_creditcard_num VALUES(3,'4929320448302705');
INSERT INTO has_creditcard_num VALUES(3,'3036995039870421');
INSERT INTO has_creditcard_num VALUES(4,'6011687777020163');
INSERT INTO has_creditcard_num VALUES(4,'5264227140695627');
INSERT INTO has_creditcard_num VALUES(5,'3411860886285942');
INSERT INTO has_creditcard_num VALUES(5,'3786855644987094');

INSERT INTO starred_in VALUES(1,8);
INSERT INTO starred_in VALUES(1,9);
INSERT INTO starred_in VALUES(2,2);
INSERT INTO starred_in VALUES(2,7);
INSERT INTO starred_in VALUES(3,6);
INSERT INTO starred_in VALUES(3,7);
INSERT INTO starred_in VALUES(4,5);
INSERT INTO starred_in VALUES(4,8);
INSERT INTO starred_in VALUES(5,4);
INSERT INTO starred_in VALUES(6,5);
INSERT INTO starred_in VALUES(7,6);
INSERT INTO starred_in VALUES(8,7);
INSERT INTO starred_in VALUES(9,8);
INSERT INTO starred_in VALUES(10,9);
INSERT INTO starred_in VALUES(11,10);
INSERT INTO starred_in VALUES(12,10);
INSERT INTO starred_in VALUES(13,1);
INSERT INTO starred_in VALUES(1,3);
INSERT INTO starred_in VALUES(20,4);
INSERT INTO starred_in VALUES(17,5);

INSERT INTO movie_genre VALUES(1,'Action');
INSERT INTO movie_genre VALUES(1,'Horror');
INSERT INTO movie_genre VALUES(1,'Comedy');
INSERT INTO movie_genre VALUES(2,'Science Fiction');
INSERT INTO movie_genre VALUES(2,'Drama');
INSERT INTO movie_genre VALUES(3,'Thriller');
INSERT INTO movie_genre VALUES(3,'Action');
INSERT INTO movie_genre VALUES(4,'Fantasy');
INSERT INTO movie_genre VALUES(4,'Mystery');
INSERT INTO movie_genre VALUES(5,'Drama');
INSERT INTO movie_genre VALUES(5,'Crime');
INSERT INTO movie_genre VALUES(6,'Fantasy');
INSERT INTO movie_genre VALUES(6,'Mystery');
INSERT INTO movie_genre VALUES(7,'Mystery');
INSERT INTO movie_genre VALUES(7,'Fantasy');
INSERT INTO movie_genre VALUES(8,'Romance');
INSERT INTO movie_genre VALUES(8,'Crime');
INSERT INTO movie_genre VALUES(9,'Drama');
INSERT INTO movie_genre VALUES(9,'Crime');
INSERT INTO movie_genre VALUES(10,'Action');
INSERT INTO movie_genre VALUES(10,'International');
INSERT INTO movie_genre VALUES(10,'Historical');

INSERT INTO preferred_actors VALUES(1,'Family',1);
INSERT INTO preferred_actors VALUES(2,'Family',1);
INSERT INTO preferred_actors VALUES(6,'Friends',2);
INSERT INTO preferred_actors VALUES(5,'Family Channels',2);
INSERT INTO preferred_actors VALUES(7,'Family',3);
INSERT INTO preferred_actors VALUES(8,'Kids Show',3);

INSERT INTO preferred_movie_genres VALUES('Action','Family',1);
INSERT INTO preferred_movie_genres VALUES('Thriller','Friends',2);
INSERT INTO preferred_movie_genres VALUES('Romance','John',1);
INSERT INTO preferred_movie_genres VALUES('International','Kids Show',3);
INSERT INTO preferred_movie_genres VALUES('Crime','Family Time',4);
INSERT INTO preferred_movie_genres VALUES('Drama','Mature',5);

INSERT INTO rental_history VALUES(2,'Boring and Lame', 1,1,'Family');
INSERT INTO rental_history VALUES(5,'Two Thumbs Up. Best Movie Ever', 1,3,'Family');
INSERT INTO rental_history VALUES(5,'Would re watch many times', 2,1,'Friends');
INSERT INTO rental_history VALUES(4,'Made me cry so much', 2,2,'Friends');
INSERT INTO rental_history VALUES(3,'Actors could''ve been better at acting', 3,6,'Kids Show');
INSERT INTO rental_history VALUES(5,'Best film of all time', 3,4,'Kids Show');
INSERT INTO rental_history VALUES(2,'Waste of my time', 4,7,'Family Time');
INSERT INTO rental_history VALUES(4,'Definitely in my top 20 movies', 5,10,'Mature');




CREATE TRIGGER maxProfileLimit 
BEFORE INSERT ON Profile
FOR EACH ROW
DECLARE
profile_count 		INTEGER;
too_many 		EXCEPTION ;
BEGIN
SELECT COUNT(*) INTO profile_count
FROM Profile
WHERE Member_ID = :NEW.Member_ID;
IF profile_count > 4 THEN
	RAISE too_many;
END IF;
EXCEPTION
	WHEN Too_many THEN Raise_application_error(-20001, 'Too many profiles!'); 
END;
/


CREATE OR REPLACE TRIGGER AVG_RATING BEFORE
  INSERT OR
  DELETE OR
  UPDATE ON Movie REFERENCING OLD AS OLD NEW AS NEW FOR EACH ROW DECLARE AVERAGE_RATING NUMBER (2,1);
  NULL_EXCEPTION                                                                 EXCEPTION;
  BEGIN
    DBMS_OUTPUT.ENABLE;
    SELECT AVG (AVERAGE_RATING) INTO AVERAGE_RATING FROM MOVIE WHERE MOVIE_ID = :NEW.MOVIE_ID;
    IF AVERAGE_RATING IS NULL THEN
      RAISE NULL_EXCEPTION;
    END IF;
    UPDATE MOVIE SET AVERAGE_RATING = AVERAGE_RATING WHERE MOVIE_ID = :NEW.MOVIE_ID;
  EXCEPTION
  WHEN NULL_EXCEPTION THEN
    DBMS_OUTPUT.PUT_LINE('NO USER HAS BEEN RATED THIS MOVIE YET');
  END;
  /



