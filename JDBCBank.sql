/*
-USER TABLE (USER_ID , USERNAME, PASSWORD, FIRST_NAME, LAST_NAME)
-ACCOUNT TABLE(BANK_ACCOUNT_ID, USER_ID, BALANCE)
Bonus:
A user's transactions are recorded - Another table (TRANSACTION_ID, BANK_ACCOUNT_ID, TYPE_OF_TRANSACTION, AMOUNT)
A user may view transaction history - RETURN TABLE WHERE BANK_ACCOUNT_ID = X
*/
ALTER TABLE ACCOUNT
DROP COLUMN USER_ID;
ALTER TABLE TRANSACTION
DROP COLUMN BANK_ACCOUNT_ID;
DROP TABLE USER_;
DROP TABLE ACCOUNT;
DROP TABLE TRANSACTION;

CREATE TABLE USER_ (
    USER_ID INTEGER PRIMARY KEY,
    USERNAME VARCHAR2(100), 
    PASSWORD VARCHAR2(100)
);
/
CREATE TABLE ACCOUNT (
    BANK_ACCOUNT_ID INTEGER PRIMARY KEY,
    USER_ID INTEGER,
    BALANCE NUMBER
);
/
CREATE TABLE TRANSACTION (
    TRANSACTION_ID INTEGER PRIMARY KEY,
    BANK_ACCOUNT_ID INTEGER,
    AMOUNT NUMBER
);
/
--Use sequences to generate USER_ID and BANK_ACCOUNT_ID.
DROP SEQUENCE U_INC;
DROP SEQUENCE BA_INC;
DROP SEQUENCE T_INC;

CREATE SEQUENCE U_INC
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE BA_INC
START WITH 1
INCREMENT BY 1;
/
CREATE SEQUENCE T_INC
START WITH 1
INCREMENT BY 1;
/
--Triggers
CREATE OR REPLACE TRIGGER U_TRIG
BEFORE INSERT ON USER_
FOR EACH ROW
BEGIN
   SELECT U_INC.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER BA_TRIG
BEFORE INSERT ON ACCOUNT
FOR EACH ROW
BEGIN
   SELECT BA_INC.NEXTVAL INTO :NEW.BANK_ACCOUNT_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER T_TRIG
BEFORE INSERT ON TRANSACTION
FOR EACH ROW
BEGIN
   SELECT T_INC.NEXTVAL INTO :NEW.TRANSACTION_ID FROM DUAL;
END;
/
--FK
ALTER TABLE ACCOUNT
ADD CONSTRAINT FK_USER_ACCOUNT
FOREIGN KEY (USER_ID) REFERENCES USER_(USER_ID);
/
ALTER TABLE TRANSACTION
ADD CONSTRAINT FK_ACCOUNT_TRANSACTION
FOREIGN KEY (BANK_ACCOUNT_ID) REFERENCES ACCOUNT(BANK_ACCOUNT_ID);
/
--ALTER
ALTER TABLE USER_ MODIFY USER_ID NOT NULL;
ALTER TABLE ACCOUNT MODIFY BANK_ACCOUNT_ID NOT NULL;
ALTER TABLE TRANSACTION MODIFY TRANSACTION_ID NOT NULL;
ALTER TABLE USER_ ADD UNIQUE (USERNAME);
ALTER TABLE USER_ MODIFY USERNAME NOT NULL;
ALTER TABLE USER_ MODIFY PASSWORD NOT NULL;
ALTER TABLE ACCOUNT ADD CONSTRAINT NOT_NEG_BALANCE CHECK (BALANCE >= 0.00);
--Insert random USERs, ACCOUNTs, and TRANSACTIONS
INSERT ALL
INTO USER_(USERNAME, PASSWORD)
VALUES ('AAllison', 'AAllisonP')
INTO USER_(USERNAME, PASSWORD)
VALUES ('BBenson', 'BBensonP')
INTO USER_(USERNAME, PASSWORD)
VALUES ('CCodor', 'CCodorP')
INTO USER_(USERNAME, PASSWORD)
VALUES ('DDaniels', 'DDanielsP')
INTO USER_(USERNAME, PASSWORD)
VALUES ('EEricson', 'EEricsonP')
INTO USER_(USERNAME, PASSWORD)
VALUES ('FFrederick', 'FFrederickP')
SELECT * FROM DUAL;
/
INSERT ALL
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (1, 1000)
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (2, 2000)
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (3, 3000)
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (4, 4000)
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (5, 5000)
INTO ACCOUNT(USER_ID, BALANCE)
VALUES (6, 6000)
SELECT * FROM DUAL;
/
INSERT ALL
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (1, 20)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (1, 200)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (2, 40)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (2, 400)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (3, 60)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (3, 600)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (4, 80)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (4, 800)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (5, 100)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (5, 1000)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (6, 120)
INTO TRANSACTION(BANK_ACCOUNT_ID, AMOUNT)
VALUES (6, 1200)
SELECT * FROM DUAL;
/
--Stored Procedure  
--PL/SQL with at least one stored procedure
--A user may view transaction history - RETURN TABLE WHERE BANK_ACCOUNT_ID = KEY_ID
CREATE OR REPLACE PROCEDURE TransactionHistory(KEY_ID IN INTEGER)
AS
S SYS_REFCURSOR;
T SYS_REFCURSOR;
BEGIN
    OPEN S FOR
    SELECT TYPE_OF_TRANSACTION FROM TRANSACTION WHERE BANK_ACCOUNT_ID = KEY_ID;
    OPEN T FOR
    SELECT AMOUNT FROM TRANSACTION WHERE BANK_ACCOUNT_ID = KEY_ID;
    DBMS_SQL.RETURN_RESULT(S);
    DBMS_SQL.RETURN_RESULT(T);    
END;

/*
UPDATE USER_ SET USERNAME = 'A', PASSWORD = 'A' WHERE USERNAME = 'AAllison' AND PASSWORD = 'AAllisonP'; 
SELECT * FROM USER_ WHERE USERNAME = 'A';-- AND PASSWORD = 'AAllisonP';
INSERT INTO USER_ VALUES (B,B);
SELECT BANK_ACCOUNT_ID FROM ACCOUNT WHERE USER_ID = 1;
UPDATE ACCOUNT SET BALANCE = 0 WHERE BANK_ACCOUNT_ID = 7;
*/