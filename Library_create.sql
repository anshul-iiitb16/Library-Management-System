Create Database Library;
Use Library;

Create Table Book (
    Book_ID int Not Null,
    Book_Name VARCHAR(100) Not Null,
    Book_Author VARCHAR(100) Default,
    Student_ID int,
    CONSTRAINT pk_Book_ID PRIMARY KEY (Book_ID)
);

Create Table Student (
    Student_ID int Not Null,
    Student_Name VARCHAR(30) Not Null,
    Student_email varchar(30) Not NUll,
    CONSTRAINT pk_Student_ID PRIMARY KEY (Student_ID)
    -- CHECk (Student_ID > 1);
);


Create table Library_Fine (
    Fine_ID int Not Null,
    Fine_Amount int Not Null,
    Student_ID int Not Null,
    CONSTRAINT pk_Fine_ID PRIMARY KEY (Fine_ID)
);

Create table Librarian (
    Librarian_ID int Not Null,
    Librarian_Name varchar(30) Not Null,
    CONSTRAINT pk_Lib_ID PRIMARY KEY (Librarian_ID)
);


ALTER TABLE Library_Fine
    ADD CONSTRAINT fk_Fined_to FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID);

ALTER TABLE Book
    ADD CONSTRAINT fk_Issued_to FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID);
    

INSERT INTO Student(Student_ID, Student_Name, Student_email) VALUES
(1, 'Sam', 'sammy@gmail.com'),
(2, 'Amit', 'tneja@yahoo.com'),
(3, 'John', 'j23@yahoo.com'),
(4, 'Frances', 'frhn@outlook.com');

INSERT INTO Library_Fine(Fine_ID, Fine_Amount, Student_ID) VALUES
(1,100,2),
(2, 200, 3);

INSERT INTO Book(Book_ID, Book_Name, Book_Author, Student_ID) VALUES
(1, 'DBMS', 'Uttam Kumar', NUll),
(2, 'OS', 'Thangraju', 2),
(3, 'DAA', 'Pradeesha', NUll),
(4, 'HOI', 'Bidisha', 1);

INSERT INTO Librarian(Librarian_ID, Librarian_Name) VALUES
(1,'Anshul Jindal');
