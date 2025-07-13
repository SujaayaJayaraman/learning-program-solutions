CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);
CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
);
INSERT INTO Customers VALUES (1, 'John Doe', TO_DATE('1985-01-01','YYYY-MM-DD'), 1000, SYSDATE);
INSERT INTO Customers VALUES (2, 'Jane Smith', TO_DATE('1990-01-01','YYYY-MM-DD'), 1500, SYSDATE);
INSERT INTO Accounts VALUES (1, 1, 'Savings', 2000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Savings', 3000, SYSDATE);
INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', SYSDATE);
INSERT INTO Employees VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', SYSDATE);
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all savings accounts.');
END;
/
SET SERVEROUTPUT ON;
EXEC ProcessMonthlyInterest;
--SCENE2
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN VARCHAR2,
    p_bonus_pct IN NUMBER
) IS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_pct / 100)
    WHERE Department = p_department;
    
    DBMS_OUTPUT.PUT_LINE('Bonus applied to department: ' || p_department);
END;
/
SET SERVEROUTPUT ON;
EXEC UpdateEmployeeBonus('IT', 10);
--SCENE3
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account IN NUMBER,
    p_amount IN NUMBER
) IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from_account;

    IF v_balance >= p_amount THEN
        UPDATE Accounts
        SET Balance = Balance - p_amount
        WHERE AccountID = p_from_account;

        UPDATE Accounts
        SET Balance = Balance + p_amount
        WHERE AccountID = p_to_account;

        DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from Account ' || p_from_account || ' to ' || p_to_account);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance in Account ' || p_from_account);
    END IF;
END;
/
SET SERVEROUTPUT ON;
EXEC TransferFunds(1, 2, 500);
