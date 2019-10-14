    --RESET DB
    ALTER TABLE REIMBURSEMENT DROP COLUMN EMPLOYEE_ID;
    DROP TABLE REIMBURSEMENT;
    ALTER TABLE EMPLOYEE DROP COLUMN EMPLOYEE_ID;
    DROP TABLE EMPLOYEE;
    
    --CREATE TABLES
    CREATE TABLE EMPLOYEE(
        EMPLOYEE_ID INTEGER PRIMARY KEY,
        USERNAME VARCHAR2(100), 
        PASSWORD VARCHAR2(100),
        MANAGER_ID INTEGER,
        EMAIL VARCHAR2(100)
    );
    /
    CREATE TABLE REIMBURSEMENT(
        REIMBURSEMENT_ID INTEGER PRIMARY KEY,
        EMPLOYEE_ID INTEGER,
        AMOUNT NUMBER,
        PAD INTEGER --PENDING(0)/APPROVED(1)/DENIED(2)
    );
    /
    --Use sequences to generate USER_ID and BANK_ACCOUNT_ID.
    DROP SEQUENCE E_INC;
    DROP SEQUENCE R_INC;
    
    CREATE SEQUENCE E_INC
    START WITH 1
    INCREMENT BY 1;
    /
    CREATE SEQUENCE R_INC
    START WITH 1
    INCREMENT BY 1;
    /
    
    --Triggers
    CREATE OR REPLACE TRIGGER E_TRIG
    BEFORE INSERT ON EMPLOYEE
    FOR EACH ROW
    BEGIN
       SELECT E_INC.NEXTVAL INTO :NEW.EMPLOYEE_ID FROM DUAL;
    END;
    /
    CREATE OR REPLACE TRIGGER R_TRIG
    BEFORE INSERT ON REIMBURSEMENT
    FOR EACH ROW
    BEGIN
       SELECT R_INC.NEXTVAL INTO :NEW.REIMBURSEMENT_ID FROM DUAL;
    END;
    /
    
    --FK
    ALTER TABLE REIMBURSEMENT
    ADD CONSTRAINT FK_EMPLOYEE_REIMBURSEMENT
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID);
    /
    
    --ALTER
    ALTER TABLE REIMBURSEMENT ADD CONSTRAINT NOT_NEG CHECK (AMOUNT > 0.00);
    ALTER TABLE REIMBURSEMENT ADD CONSTRAINT PAD_CHECK CHECK (PAD >= 0);
    ALTER TABLE REIMBURSEMENT ADD CONSTRAINT PAD_CHECK2 CHECK (PAD <= 2);
    
    INSERT ALL
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('HI', 'BYE', 0, 'raymondjhua@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('AAllison', 'AAllisonP', 1, 'aallisonP@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('BBenson', 'BBensonP', 1, 'bbenson@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('CCodor', 'CCodorP', 1, 'ccodor@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('DDaniels', 'DDanielsP', 2, 'ddaniels@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('EEricson', 'EEricsonP', 2, 'eericson@gmail.com')
    INTO EMPLOYEE(USERNAME, PASSWORD, MANAGER_ID, EMAIL)
    VALUES ('FFrederick', 'FFrederickP', 2, 'ffrederick@gmail.com')
    SELECT * FROM DUAL;
    /
    
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 20.81, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 35.14, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 95.64, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 80.25, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (5, 75.17, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 67.69, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 85.85, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (5, 56.02, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 33.08, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (5, 46.86, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 36.81, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 69.31, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 13.25, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 32.1, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 99.3, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 85.79, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 28.95, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 66.69, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 78.92, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 87.21, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 48.18, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 37.93, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 60.54, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 22.9, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 78.93, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 82.82, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 93.83, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 26.98, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 30.56, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 81.32, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 85.29, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 30.99, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 72.32, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 46.1, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 87.81, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 38.14, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 79.61, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (4, 82.63, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 11.12, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (2, 45.48, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 49.41, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 36.33, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 77.89, 2);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 25.48, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 51.84, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (7, 56.28, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (5, 64.47, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (6, 64.87, 0);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (3, 85.51, 1);
    insert into REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) values (1, 60.87, 1);
    /
        
    COMMIT;
    
    DROP TABLE WIN;
    DROP TABLE SCORE;
    DROP TABLE USER_;
    DROP TABLE GAME;
  
    DROP TABLE FLASHCARD;
    DROP TABLE FLASHCARD_STUDY_SET;
    DROP TABLE STUDY_SET;
    DROP TABLE TOPIC;  
    COMMIT;
    
    
    CREATE SEQUENCE SCORE_INC
    START WITH 1
    INCREMENT BY 1;
    /
    
    --Triggers
    CREATE OR REPLACE TRIGGER SCORE_TRIG
    BEFORE INSERT ON SCORE
    FOR EACH ROW
    BEGIN
       SELECT SCORE_INC.NEXTVAL INTO :NEW.SCORE_ID FROM DUAL;
    END;
    /
    
insert into SCORE (SCORES, GAME_ID, USER_ID) values (74, 4, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (26, 3, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (32, 3, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (57, 1, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (7, 1, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (31, 4, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (8, 4, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (90, 3, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (47, 1, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (15, 3, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (53, 1, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (23, 3, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (24, 2, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (85, 2, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (54, 4, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (94, 1, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (76, 3, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (36, 4, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (59, 2, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (42, 1, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (5, 4, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (34, 3, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (66, 4, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (26, 1, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (74, 2, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (19, 3, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (74, 2, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (55, 3, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (77, 3, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (22, 4, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (43, 4, 4);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (7, 2, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (61, 3, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (76, 2, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (80, 1, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (36, 3, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (51, 4, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (35, 3, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (33, 1, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (24, 2, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (58, 2, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (65, 2, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (17, 2, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (99, 2, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (10, 1, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (60, 3, 5);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (63, 2, 3);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (59, 4, 1);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (46, 1, 2);
insert into SCORE (SCORES, GAME_ID, USER_ID) values (12, 4, 4);
commit;