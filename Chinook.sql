--2 	SQL Queries
--2.1, SELECT
--2.1.1, Select all records from the Employee table.
SELECT * FROM EMPLOYEE;
--2.1.2, Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
--2.1.3, Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

--2.2, ORDER BY
--2.2.1, Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM ORDER BY TITLE DESC;
--2.2.2, Select first name from Customer and sort result set in ascending order by city.
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;
--Insert ", CITY" after FIRSTNAME to select cities to verify ascending order

--2.3 INSERT INTO
--2.3.1, Insert two new records into Genre table 
INSERT INTO GENRE (GENREID, NAME) VALUES (26, 'EDM');
INSERT INTO GENRE (GENREID, NAME) VALUES (27, 'K-POP');
--2.3.2, Insert two new records into Employee table
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(9, 'Doe', 'John', 'Member', 6, TO_DATE('1997-03-07', 'yyyy-mm-dd'), TO_DATE('2019-09-13', 'yyyy-mm-dd'), '1 White House Street', 'Washington DC', 'MD', 'United States of America', '11111', '+1 (111) 111-1111', '+1 (111) 111-1112', 'john.doe@wdc.gov');
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES
(10, 'Doe', 'Jane', 'President', 1, TO_DATE('1997-07-07', 'yyyy-mm-dd'), TO_DATE('3019-09-20', 'yyyy-mm-dd'), '10 Cross Street', 'Yellow City', 'Pineapple Island', 'United States of America', '*1*1*', '+1 (123) 456-7890', '+1 (FAX) 111-1111', 'jane.doe@president.pineappleisland');
--2.3.3, Insert two new records into Customer table
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES
(60, 'Henry','Chu', 'Stony Brook University', '100 Apple Street', 'Maple City', 'NY', 'USA', '10983', '+1 (923) 231-2303', '+1 (923) 231-2304', 'hchu@gmail.com', 5);
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, COMPANY, ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL, SUPPORTREPID) VALUES
(61, 'Anthony','Chin', 'Binghamton University', '300 Pear Street', 'Green City', 'NY', 'USA', '10923', '+1 (293) 323-3913', '+1 (293) 323-3914', 'achin@gmail.com', 4);

--2.4 UPDATE
--2.4.1, Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
--2.4.2, Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';

--2.5 LIKE
--2.5.1, Select all invoices with a billing address like “T%” 
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

--2.6 BETWEEN
--2.6.1, Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;
--2.6.2, Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE('2003-06-01', 'yyyy-mm-dd') AND TO_DATE('2004-03-01', 'yyyy-mm-dd');

--2.7 DELETE
--2.7.1, Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
--To not remove constraints, deleting all records related to the record in CUSTOMER table is done
--Go backwards, CUSTOMER -> INVOICE -> INVOICELINE
DELETE FROM INVOICELINE WHERE INVOICEID = 50 OR INVOICEID = 61 OR INVOICEID = 245 OR INVOICEID = 268 OR INVOICEID = 290 OR INVOICEID = 342 OR INVOICEID = 116;
DELETE FROM INVOICE WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';

--3 	SQL Functions
--3.1 System Defined Functions
--3.1.1, Create a function that returns the current time.
CREATE OR REPLACE FUNCTION CurrentTime
    RETURN VARCHAR2
    IS
    RESULT VARCHAR2(200);
    TEMP_TIME TIMESTAMP;
BEGIN
    SELECT TO_CHAR(CURRENT_TIMESTAMP(0), 'DD-MON-YYYY HH12:MI:SSxFF') INTO TEMP_TIME FROM DUAL;
    RESULT := TO_CHAR(TEMP_TIME);
    RETURN RESULT;
END;
/*--TESTCASE
DECLARE
RESULT VARCHAR(200);
BEGIN
    RESULT := CurrentTime;
    DBMS_OUTPUT.PUT_LINE('Current time is: '||RESULT);
END;
*/
--3.1.2, create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION LengthName(X IN NUMBER)
    RETURN NUMBER
    IS
    RESULT NUMBER;
    TEMP_NAME VARCHAR2(120);
BEGIN
    SELECT NAME INTO TEMP_NAME FROM MEDIATYPE WHERE MEDIATYPEID = X;
    RESULT := LENGTH(TEMP_NAME);
    RETURN RESULT;
END;  
/*--TESTCASE
DECLARE
ID NUMBER;
RESULT NUMBER;
BEGIN
    ID := 3;
    RESULT := LengthName(ID);
    DBMS_OUTPUT.PUT_LINE('Length is: '||RESULT);
END;
*/

--3.2 System Defined Aggregate Functions
--3.2.1, Create a function that returns the most expensive track
CREATE OR REPLACE FUNCTION MostExpensive
    RETURN SYS_REFCURSOR
    IS
    S SYS_REFCURSOR; 
BEGIN
    OPEN S FOR
    SELECT NAME FROM TRACK WHERE UNITPRICE = (SELECT MAX(TRACK.UNITPRICE) FROM TRACK);
    RETURN S;
END;
/*--TEST CASE
--COUNTER variable implemented to verify all 213 entries are outputted
DECLARE
S SYS_REFCURSOR;
COUNTER NUMBER;
SOME_NAME TRACK.NAME%TYPE;
BEGIN
    S := MostExpensive;
    COUNTER := 1;
    LOOP
    FETCH S INTO SOME_NAME; 
    EXIT WHEN S%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('NAME: '||SOME_NAME||', ENTRY NUMBER '||COUNTER);
    COUNTER := COUNTER + 1;
    END LOOP;
    CLOSE S;
END;
*/

--3.3 User Defined Scalar Functions
--3.3.1, Create a function that returns the average price of invoice line items in the invoiceline table
CREATE OR REPLACE FUNCTION AvgPriceInvoiceLine
    RETURN NUMBER
    IS
    result NUMBER;
BEGIN
    SELECT  AVG(UNITPRICE) INTO result FROM INVOICELINE;
    RETURN result;     
END;
/*--TEST CASE
DECLARE
result NUMBER;
BEGIN
    result := AvgPriceInvoiceLine;
    DBMS_OUTPUT.PUT_LINE('The average price of invoice line items in the INVOICELINE table is : '||result);

END;
*/

--3.4 User Defined Table Valued Functions
--3.4.1, Create a function that returns all employees who were born after 1968
CREATE OR REPLACE FUNCTION BornAfter1968
    RETURN SYS_REFCURSOR
    IS
    S SYS_REFCURSOR; 
BEGIN
    OPEN S FOR
    SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE WHERE (( TO_DATE('1968-12-31 00:00:00','yyyy-mm-dd hh24:mi:ss')) < BIRTHDATE);
    RETURN S;
END;
/*--TEST CASE
DECLARE
S SYS_REFCURSOR;
COUNTER NUMBER;
SOME_FIRSTNAME EMPLOYEE.FIRSTNAME%TYPE; 
SOME_LASTNAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('These employees are born after 1968');
    S := BornAfter1968;
    LOOP
    FETCH S INTO SOME_FIRSTNAME, SOME_LASTNAME;
    EXIT WHEN S%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('NAME: '||SOME_FIRSTNAME||' '||SOME_LASTNAME);
    END LOOP;
    CLOSE S;
END;
*/

--4.0 Stored Procedures
--4.1 Basic Stored Procedure
--4.1.1, Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE FLNameE
AS
S SYS_REFCURSOR;  
BEGIN
  OPEN S FOR
  SELECT FIRSTNAME, LASTNAME
  FROM EMPLOYEE;
  DBMS_SQL.RETURN_RESULT(S);
END;
--TEST CASE
--EXECUTE FLNameE;

--4.2 Stored Procedure Input Parameters
--4.2.1, Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UpdateEInfo(KEY_ID IN NUMBER, ID_IN IN NUMBER, L_IN IN VARCHAR2, F_IN IN VARCHAR2, T_IN IN VARCHAR2, R_IN IN NUMBER, DOB_IN IN DATE, HD_IN IN DATE, A_IN IN VARCHAR2, CI_IN IN VARCHAR2, S_IN IN VARCHAR2, CO_IN IN VARCHAR2, P_IN IN VARCHAR2, PHONE_IN IN VARCHAR2, FAX_IN IN VARCHAR2, E_IN IN VARCHAR2)
AS  
BEGIN
    UPDATE EMPLOYEE SET EMPLOYEEID = ID_IN, LASTNAME = L_IN, FIRSTNAME = F_IN, TITLE = T_IN, REPORTSTO = R_IN, BIRTHDATE = DOB_IN, HIREDATE = HD_IN, ADDRESS = A_IN, CITY = CI_IN, STATE = S_IN, COUNTRY = CO_IN, POSTALCODE = P_IN, PHONE = PHONE_IN, FAX = FAX_IN, EMAIL = E_IN  WHERE EMPLOYEEID = KEY_ID;
END;
--4.2.2, Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE managerWho(KEY_ID IN NUMBER)
AS 
S SYS_REFCURSOR;  
BEGIN
    OPEN S FOR
    SELECT * 
    FROM EMPLOYEE 
    WHERE EMPLOYEEID = 
    (SELECT REPORTSTO FROM EMPLOYEE WHERE EMPLOYEEID = KEY_ID);
    DBMS_SQL.RETURN_RESULT(S);
END;
--TEST CASE
--EXECUTE managerWho(2);

--4.3 Stored Procedure Output Parameters
--4.3.1, Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE CusNameCompany(CUS_IN IN NUMBER, F_OUT OUT VARCHAR2, L_OUT OUT VARCHAR2, COM_OUT OUT VARCHAR2)
AS
BEGIN
    SELECT FIRSTNAME, LASTNAME, COMPANY INTO F_OUT, L_OUT, COM_OUT FROM CUSTOMER WHERE CUSTOMERID = CUS_IN;
END;

--5.0 Transactions
--5.0.1, Create a transaction that given an invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).

CREATE OR REPLACE PROCEDURE DeleteInvoice (KEY_ID IN NUMBER)
AS
BEGIN
    DELETE FROM INVOICELINE WHERE INVOICEID = KEY_ID;
    DELETE FROM INVOICE WHERE INVOICEID = KEY_ID;
    COMMIT;
END;
--TEST CASE
--EXECUTE deleteInvoice (2);

--6.0 Triggers
--6.1 AFTER/FOR
--6.1.1, Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER  EmployeeITrigger
AFTER INSERT ON EMPLOYEE
FOR EACH ROW
DECLARE
    action VARCHAR2(100);
BEGIN
    action := CASE
        WHEN INSERTING THEN 'INSERT'
END;
END;
--6.1.2, Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER  AlbumUTrigger
AFTER INSERT ON ALBUM
FOR EACH ROW
DECLARE
    action VARCHAR2(100);
BEGIN
    action := CASE
        WHEN INSERTING THEN 'UPDATE'
END;
END;
--6.1.3, Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE OR REPLACE TRIGGER  CustomerDTrigger
AFTER DELETE ON CUSTOMER
FOR EACH ROW
DECLARE
    action VARCHAR2(100);
BEGIN
    action := CASE
        WHEN DELETING THEN 'DELETE'
END;
END;

--7.0 JOINS
--7.1 INNER
--7.1.1, Create an inner join that joins customers and orders, and specifies the name of the customer and the invoiceId.
SELECT FIRSTNAME AS FIRSTNAME, LASTNAME AS LASTNAME, INVOICEID AS INVOICEID
FROM CUSTOMER 
INNER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
ORDER BY LASTNAME;

--7.2 OUTER
--7.2.1, Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT CUSTOMER.CUSTOMERID AS CUSTOMERID, FIRSTNAME AS FIRSTNAME, LASTNAME AS LASTNAME, INVOICE.INVOICEID AS INVOICEID, TOTAL AS TOTAL
FROM CUSTOMER 
FULL OUTER JOIN INVOICE
ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID
ORDER BY CUSTOMERID, INVOICEID;

--7.3 RIGHT
--7.3.1, Create a right join that joins album and artist specifying artist name and title.
SELECT NAME AS NAME, TITLE AS TITLE
FROM ALBUM 
RIGHT OUTER JOIN ARTIST
ON ARTIST.ARTISTID = ALBUM.ARTISTID
ORDER BY NAME, TITLE;

--7.4 CROSS
--7.4.1, Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT *
FROM ALBUM 
CROSS JOIN ARTIST
ORDER BY NAME;

--7.5 SELF
--7.5.1, Perform a self-join on the employee table, joining on the reportsto column.
SELECT *
FROM EMPLOYEE A
JOIN EMPLOYEE B
ON A.REPORTSTO = B.REPORTSTO;