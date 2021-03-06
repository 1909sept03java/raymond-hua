--DB CREATION SCRIPTS OFTEN BEGIN WITH DROP STATEMENTS
--AVOID CLASHES WITH EXISTING TABLES
DROP TABLE BEAR;
/
DROP TABLE BEAR_TYPE;
/
DROP TABLE CAVE;
/
DROP TABLE BEEHIVE;
/
DROP TABLE BEAR_BEEHIVE;
/

--TABLE CREATION WITH PRIMARY KEYS

CREATE TABLE BEAR (
    BEAR_ID INTEGER PRIMARY KEY,
    BEAR_NAME VARCHAR2(100), --100 CHARACTERS OF SPACE
    --VARCHAR2 INTERPRETS NULL AND EMPTY STRING AS THE SAME VALUE, UNLIKE VARCHAR
    BIRTHDATE DATE,
    WEIGHT NUMBER(6, 2) DEFAULT 200.00,
    BEAR_TYPE_ID INTEGER NOT NULL, --APPLYING A NOT NULL CONSTRAINT TO THIS COLUMN, WILL BE FK
    CAVE_ID INTEGER --WILL BE A NULLABLE FK
);
/

CREATE TABLE BEAR_TYPE (
    BEAR_TYPE_ID INTEGER PRIMARY KEY,
    BEAR_TYPE_NAME VARCHAR2(100)
);
/

CREATE TABLE CAVE (
    CAVE_ID INTEGER PRIMARY KEY,
    CAVE_NAME VARCHAR2(100),
    MAX_BEARS INTEGER DEFAULT 4
);
/

CREATE TABLE BEEHIVE (
    BEEHIVE_ID INTEGER PRIMARY KEY,
    HONEY_AMT NUMBER(5, 2) DEFAULT 75.00
);
/

CREATE TABLE BEAR_BEEHIVE (
    BEAR_ID INTEGER,
    BEEHIVE_ID INTEGER,
    PRIMARY KEY (BEAR_ID, BEEHIVE_ID) --COMPOSITE PRIMARY KEY
);
/

--FOREIGN KEY CONSTRAINTS 

--CONSTRAINT: RULE PLACED ON THE CONTENTS OF A TABLE, LIMITING WHAT MAY BE INSERTED
--TYPES OF CONSTRAINTS: CHECK (INCLUDES NOT NULL), UNIQUE, PRIMARY KEY, FOREIGN KEY

ALTER TABLE BEAR
ADD CONSTRAINT FK_BEAR_BEAR_TYPE
FOREIGN KEY (BEAR_TYPE_ID) REFERENCES BEAR_TYPE(BEAR_TYPE_ID);
/

ALTER TABLE BEAR
ADD CONSTRAINT FK_BEAR_CAVE
FOREIGN KEY (CAVE_ID) REFERENCES CAVE(CAVE_ID);
/

ALTER TABLE BEAR_BEEHIVE
ADD CONSTRAINT FK_BEAR_BEEHIVE_BEAR
FOREIGN KEY (BEAR_ID) REFERENCES BEAR(BEAR_ID);
/

ALTER TABLE BEAR_BEEHIVE
ADD CONSTRAINT FK_BEAR_BEEHIVE_BEEHIVE
FOREIGN KEY (BEEHIVE_ID) REFERENCES BEEHIVE(BEEHIVE_ID);
/

--ADD SOME DATA - BE CAREFUL ABOUT INSERTION ORDER
--TWO WAYS TO ADD: BY FILLING ALL COLUMNS OR SPECIFY WHICH COLUMNS TO FILL

INSERT INTO BEAR_TYPE VALUES (1, 'Grizzly');
INSERT INTO BEAR_TYPE (BEAR_TYPE_ID, BEAR_TYPE_NAME) VALUES (2, 'Sun');


INSERT ALL
INTO CAVE
VALUES(1, 'Queens College', 15)
INTO CAVE (CAVE_ID, CAVE_NAME)
VALUES(37, 'Awesome Cave')
SELECT * FROM DUAL; --DUAL IS A DUMMY TABLE, PLSQL USES IT TO MAKE STATEMENTS FIT QUERY FORMAT

SELECT * FROM DUAL;

INSERT ALL
INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID, CAVE_ID)
VALUES (72, 'Barry', TO_DATE('2000-10-08', 'yyyy-mm-dd'), 1, 37)
INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID, CAVE_ID)
VALUES (2, 'Bob', TO_DATE('2000-07-31', 'yyyy-mm-dd'), 2, 37)
INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID, CAVE_ID)
VALUES (8, 'Berneice', TO_DATE('1980-9-08', 'yyyy-mm-dd'), 1, 1)
INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, WEIGHT, BEAR_TYPE_ID, CAVE_ID)
VALUES (45, 'Beatrice', TO_DATE('2005-03-09', 'yyyy-mm-dd'), 600.00, 1, 37)
INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID, CAVE_ID)
VALUES (89, 'Yogi', TO_DATE('1958-08-18', 'yyyy-mm-dd'), 2, 1)
SELECT * FROM DUAL;

INSERT INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID)
VALUES (56, 'Bill', TO_DATE('2000-10-08', 'yyyy-mm-dd'), 1);

INSERT INTO BEAR(BEAR_ID, BEAR_NAME, BIRTHDATE, BEAR_TYPE_ID)
VALUES (56, 'Bill', TO_DATE('2000-10-08', 'yyyy-mm-dd'), 1);

--SET UP SEQUENCES TO PRODUCE PRIMARY KEYS 
CREATE SEQUENCE SQ_BEAR_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_BEAR_TYPE_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_CAVE_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_BEEHIVE_PK
START WITH 1000
INCREMENT BY 1;
/

--TRIGGERS: BLOCKS OF CODE THAT WILL EXECUTE IN REPONSE TO A DML STATEMENT 
--(INSERT, UPDATE, DELETE)
--CAN CREATE "BEFORE" OR "AFTER" TRIGGERS 
CREATE OR REPLACE TRIGGER TR_INSERT_BEAR
BEFORE INSERT ON BEAR --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEAR_PK.NEXTVAL INTO :NEW.BEAR_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_BEAR_TYPE
BEFORE INSERT ON BEAR_TYPE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEAR_TYPE_PK.NEXTVAL INTO :NEW.BEAR_TYPE_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_CAVE
BEFORE INSERT ON CAVE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_CAVE_PK.NEXTVAL INTO :NEW.CAVE_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_BEEHIVE
BEFORE INSERT ON BEEHIVE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEEHIVE_PK.NEXTVAL INTO :NEW.BEEHIVE_ID FROM DUAL;
END;
/

--TRY IT OUT!
INSERT INTO BEEHIVE (HONEY_AMT) VALUES (58.05);
INSERT INTO BEEHIVE (HONEY_AMT) VALUES (580.05);

--GIVE OUR HOMELESS BEAR SOMEWHERE TO LIVE
UPDATE BEAR SET CAVE_ID = 1 WHERE BEAR_NAME ='Bill';

--SELECT STAEMENTS

--SELECT ALL NON-GRIZZLY BEARS
SELECT BEAR_NAME, BIRTHDATE FROM BEAR
WHERE BEAR_TYPE_ID != 1
ORDER BY BEAR_NAME; --ASCENDING ORDER IS DEFAULT, USE 'DESC' FOR DESCENDING

--SELECT ALL BEARS, ORDERED BY WEIGHT AND BIRTHDATE
SELECT * FROM BEAR
ORDER BY WEIGHT, BIRTHDATE; --THE CONDITION ARE ORDERED FROM LEFT TO RIGHT

--HOW MANY BEARS PER CAVE?
SELECT CAVE.CAVE_NAME AS CAVE_NAME, COUNT(BEAR.CAVE_ID) AS NUMBEAR
FROM BEAR
INNER JOIN CAVE ON BEAR.CAVE_ID = CAVE.CAVE_ID
GROUP BY CAVE_NAME, BEAR.CAVE_ID;

--ALL CAVES WITH MORE THAN THREE BEARS
SELECT CAVE_ID, COUNT(BEAR_ID) AS NUMBEAR
FROM BEAR
GROUP BY CAVE_ID
HAVING COUNT(BEAR_ID) > 3;

--VIEWS

--HOW MANY BEARS PER CAVE? VIEW EDITION!
CREATE VIEW VW_BEARS_PER_CAVE(CAVE_NAME,NUMBEAR)
AS
SELECT CAVE_NAME, COUNT (BEAR.CAVE_ID)
FROM BEAR
INNER JOIN CAVE ON BEAR.CAVE_ID = CAVE.CAVE_ID
GROUP BY CAVE_NAME, BEAR.CAVE_ID;

SELECT * FROM VW_BEARS_PER_CAVE;

--DROP VIEW VW_BEARS_PER_CAVE;

--USER-DEFINED FUNCTIONS

--MAX OF TWO NUMBERS (COULD WE USE THIS SYSTEM-DEFINED FUNCTION? YES! WE COULD!)
CREATE OR REPLACE FUNCTION FIND_MAX_NUMBEAR(X IN NUMBER, Y IN NUMBER)
RETURN NUMBER
IS
Z NUMBER;
BEGIN
    IF X>Y THEN
    Z := X;
    ELSE
    Z := Y;
    END IF;
    RETURN Z;
END;

--CALL OUR FUNCTION
DECLARE
FIRST_NUM NUMBER;
SECOND_NUM NUMBER;
MAX_NUM NUMBER;
BEGIN
    FIRST_NUM := 22;
    SECOND_NUM := 42;
    MAX_NUM := FIND_MAX_NUMBEAR(FIRST_NUM, SECOND_NUM);
    DBMS_OUTPUT.PUT_LINE('MAX: '||MAX_NUM); --JUST LIKE A SYSOUT IN JAVA
END;

--CURSOR: POINTTABASE (LIKE THE RESULT SET OF A QUERY)
--USE CURSORS TO MOVE THROUGH RESULT SETS AND PROCESS EACH LINE
--IMPLICIT CURSORS ARE USED BY THE DB TO SUPPORT QUERYING, YOU CAN ALSO DEFINE EXPLICIT CURSORS
--DEFINE YOUR OWN OR USE SYS_REFCURSOR (ALREADY THERE)
CREATE OR REPLACE FUNCTION PRINT_ALL_BEARS
RETURN SYS_REFCURSOR
IS
S SYS_REFCURSOR;
BEGIN
    OPEN S FOR
    SELECT BEAR_ID, BEAR_NAME FROM BEAR;
    RETURN S;
END;

--INVOKE PRINT_ALL_BEARS
DECLARE 
S SYS_REFCURSOR;
SOME_ID BEAR.BEAR_ID%TYPE; --DECLARESS THAT SOME_ID IS OF THE SAME DATATYPE AS BEAR.BEAR_ID
SOME_NAME BEAR.BEAR_NAME%TYPE;
BEGIN
    S := PRINT_ALL_BEARS;
    LOOP
    FETCH S INTO SOME_ID, SOME_NAME; -- GRABBING VALUES IN COLUMNS OF NEXT ROW IN ACTIVE SET
    EXIT WHEN S%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID : '||SOME_ID||', NAME: '||SOME_NAME);
    END LOOP;
    CLOSE S;
END;
--~~~~~~~~~~~~~~~~~~~~LUNCH BREAK ASSIGNMENT
SELECT BEAR_NAME,WEIGHT FROM BEAR ORDER BY WEIGHT;

--RETURN 2ND HEAVIEST BEAR
CREATE OR REPLACE SECOND_LARGEST_BEAR
RETURN VARCHAR2(100)
IS
NAME VARCHAR2(100);
BEGIN    
    --FIND 2ND HEAVIEST
    NAME := SELECT BEAR_NAME
    FROM (SELECT BEAR_NAME, WEIGHT FROM BEAR WHERE WEIGHT != (SELECT MAX(WEIGHT)FROM BEAR)) 
    WHERE WEIGHT = (SELECT MAX(WEIGHT)FROM (SELECT BEAR_NAME, WEIGHT FROM BEAR WHERE WEIGHT != (SELECT MAX(WEIGHT)FROM BEAR)));
   -- DBMS_OUTPUT.PUT_LINE('2nd HEAVIEST BEAR : '||NAME);
    RETURN NAME);
END;

SELECT * FROM VW_BEARS_PER_CAVE;
--UPDATE BEAR SET WEIGHT = 300 WHERE BEAR_NAME = 'Yogi';
--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END OF LUNCH TIME ASSIGNMENT
--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NEW STUFF AFTER LUNCH
--TIME TO FEED THE BEARS

--FIRST,REQUIRETHAT BEARS AND BEEHIVES HAVE >= 0 AS WEIGHTS
ALTER TABLE BEEHIVE AND CONSTRAINT CK_BEEHIVE_WEIGHT
CHECK (HONEY_AMT >= 0);
/
ALTER TABLE BEAR ADD CONSTRAINT CK_BEAR_WEIGHT
CHECK (WEIGHT >= 0);
/
--ADD SOME BEAR/BEEHIVE PAIRINGS
INSERT ALL
INTO BEAR_BEEHIVE
VALUES (72, 1000)
INTO BEAR_BEEHIVE
VALUES (2, 1000)
INTO BEAR_BEEHIVE
VALUES (89, 1000)
INTO BEAR_BEEHIVE
VALUES (89,1001)
INTO BEAR_BEEHIVE
VALUES (56, 1001)
INTO BEAR_BEEHIVE
VALUES (45,1001)
SELECT * FROM DUAL;

--NOW, CREATE THE STORED PROCEDURE TO FEED A BEAR
CREATE OR REPLACE PROCEDURE SP_FEED_BEAR(B_ID IN NUMBER, H_ID IN NUMBER, HONEY_AMT IN NUMBER, AMT_FED OUT NUMBER)
IS
--VARIABLE DECLARATIONS
BB_EXISTS INTEGER
BEGIN
    --CHECK THAT BEAR AND BEEHIVE ARE CORRECTLY MATCHED
    SELECT COUNT(BB.BEAR_ID) INTO BB_EXISTS
    FROM BEAR_BEEHIVE BB --ALIASING BEAR_BEEHIVE
    WHERE BB.BEAR_ID = B_ID
    AND BB.BEEHIVE_ID = H_ID;
    --CHECK IS THE AMOUNT OF MONEY >0 AND LESS THAN THE HIVE'S WEIGHT
    IF BB_EXISTS > 0 AND HONEY_AMT > 0
        --REDUCE HIVE WEIGHT
        UPDATE BEEHIVE SET HONEY_AMT = HONEY_AMT - AMT_TO_FEED
        HERE BEHIVE_ID = H_ID;
        --INCREASE BEAR WEIGHT
        PDATE BEAR SET WEIGHT = HONEY_AMT + AMT_TO_FEED
        WHERE BEAR_ID = B_ID;
        --SET AMOUNT TO RETURN
        AMT_FED := AMT_TO_FEED;
    ELSE
        AMT_FED := 0;
    END IF;
    COMMIT;
    --EXCEPTION HANDLING
    EXCEPTION
    WHEN OTHERS THEN
    AMT_FED := 0;
    ROLLBACK;
END;

DECLARE
AMT_FED NUMBER;
BEGIN SP_FEED_BEAR(56, 1001, 10,AMT_FED);


