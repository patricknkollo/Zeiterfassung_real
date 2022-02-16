create table Einsatz
(
    einsatzid     int NOT NULL AUTO_INCREMENT,
    anfangsZeit   datetime,
    endeZeit      datetime,
    mitarbeiterid int NOT NULL,
    PRIMARY KEY (einsatzid),
    FOREIGN KEY (mitarbeiterid) REFERENCES Einsatz (mitarbeiterid)
);