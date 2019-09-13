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
