create table Mitarbeiter
(
    mitarbeiterID int NOT NULL AUTO_INCREMENT,
    name          varchar(255),
    vorname       varchar(255),
    geschlecht    varchar(1),
    geburt        TIMESTAMP,
    PRIMARY KEY (mitarbeiterID)
);