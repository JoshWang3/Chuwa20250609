CREATE DATABASE db;
USE db;

CREATE TABLE Contacts (
    ID INT PRIMARY KEY,
    Name VARCHAR(100),
    PhoneNumber VARCHAR(20)
);

INSERT INTO Contacts (ID, Name, PhoneNumber)
VALUES 
    (1, 'Aril Schreider', '408-555-2211'),
    (2, 'Bob Kang', '665-225-4332');

SELECT * FROM Contacts;
