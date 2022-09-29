CREATE TABLE Category (
    Id serial NOT NULL,
    CategoryName varchar(255) UNIQUE,
    PRIMARY KEY (Id)
);

CREATE TABLE LibraryItem (
    Id serial NOT NULL,
    CategoryId int,
    Title varchar(255),
    Author varchar(255),
    Pages int NULL,
    RunTimeMinutes int NULL,
    isBorrowable bool NOT NULL,
    Borrower varchar(255),
    BorrowDate date NULL,
    Type varchar,
    PRIMARY KEY (Id),
    FOREIGN KEY (CategoryId) REFERENCES Category(ID)
);

CREATE TABLE Employees (
    Id serial NOT NULL,
    FirstName varchar(255),
    LastName varchar(255),
    Salary decimal,
    IsCEO bool,
    IsManager bool,
    ManagerID int NULL,
    PRIMARY KEY (Id)
);

INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Comedy');
INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Drama');
INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Fantasy');
INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Adventure');
INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Documentary');
INSERT INTO Category (Id, CategoryName) VALUES (DEFAULT, 'Non-fiction');

INSERT INTO LibraryItem VALUES (DEFAULT, 3, 'The Skyfire Cycle', 'D.C. Parlov', 875, null, true, null, null, 'Book');
INSERT INTO LibraryItem VALUES (DEFAULT, 6, 'Plants in North America', 'Matt Langdon', 433, null, false, null, null, 'ReferenceBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 1, 'Chris Rock: Laugh It Up!', null, null, 120, true, null, null, 'DVD');
INSERT INTO LibraryItem VALUES (DEFAULT, 3, 'Lord of the Rings', null, null, 350, true, null, null, 'AudioBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 3, 'The Skyfire Cycle 2', 'D.C. Parlov', 686, null, true, null, null, 'Book');
INSERT INTO LibraryItem VALUES (DEFAULT, 2, 'The Shawshank Redemption', null, null, 142, true, null, null, 'DVD');
INSERT INTO LibraryItem VALUES (DEFAULT, 4, 'SpongeBob SquarePants', null, null, 182, true, 'Nils Lindkvist', '2022-09-03', 'DVD');
INSERT INTO LibraryItem VALUES (DEFAULT, 5, 'Serialkillers in the 70s', 'Regina Lowell', 340, null, false, null, null, 'ReferenceBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 6, 'How to Catch Fish Without A Rod', 'Carl Michaels', 234, null, false, null, null, 'ReferenceBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 6, 'Abra Cadabra', 'Magic Mark', 612, null, true, 'Michelle Obama', '2022-08-15', 'Book');
INSERT INTO LibraryItem VALUES (DEFAULT, 4, 'Uncharted', null, null, 245, true, null, null, 'AudioBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 2, 'A Dark Blue Sky', null, null, 219, true, null, null, 'AudioBook');
INSERT INTO LibraryItem VALUES (DEFAULT, 5, 'The Mafia of New York', 'Lewis Larsson', 512, null, true, null, null, 'Book');