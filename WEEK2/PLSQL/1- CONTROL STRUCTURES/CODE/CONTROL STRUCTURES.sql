CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);
ALTER TABLE Customers ADD IsVIP VARCHAR2(5);
CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
INSERT INTO Customers 
VALUES (1, 'John Doe', TO_DATE('1950-01-01', 'YYYY-MM-DD'), 12000, SYSDATE, NULL);
INSERT INTO Customers 
VALUES (2, 'Jane Smith', TO_DATE('1980-01-01', 'YYYY-MM-DD'), 9000, SYSDATE, NULL);
INSERT INTO Loans 
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans 
VALUES (2, 2, 3000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 60));
--SCENE1
SET SERVEROUTPUT ON;
DECLARE
    v_age NUMBER;
BEGIN
    FOR cust IN (SELECT * FROM Customers) LOOP
        SELECT FLOOR(MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12) INTO v_age FROM dual;
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;

            DBMS_OUTPUT.PUT_LINE('Discount applied for: ' || cust.Name);
        END IF;
    END LOOP;
END;
/
--scene2
BEGIN
    FOR cust IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers SET IsVIP = 'TRUE' WHERE CustomerID = cust.CustomerID;
        ELSE
            UPDATE Customers SET IsVIP = 'FALSE' WHERE CustomerID = cust.CustomerID;
        END IF;
    END LOOP;
END;
/
SELECT Name, Balance, IsVIP FROM Customers;
/
--SCENE 3
SET SERVEROUTPUT ON;
BEGIN
    FOR loan_rec IN (
        SELECT L.LoanID, C.Name, L.EndDate
        FROM Loans L JOIN Customers C ON L.CustomerID = C.CustomerID
        WHERE L.EndDate <= SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || loan_rec.LoanID || 
                             ' for ' || loan_rec.Name || 
                             ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
UPDATE Loans SET EndDate = SYSDATE + 15 WHERE LoanID = 1;
