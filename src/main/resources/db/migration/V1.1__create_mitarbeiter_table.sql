create table Mitarbeiter
(
    mitarbeiterid int NOT NULL AUTO_INCREMENT,
    name          varchar(255),
    vorname       varchar(255),
    geschlecht    varchar(1),
    geburt        date,
    PRIMARY KEY (mitarbeiterid)
);