create table Einsatz
(
    einsatzid     int NOT NULL AUTO_INCREMENT,
    anfangsZeit   TIMESTAMP,
    endeZeit      TIMESTAMP,
    mitarbeiterID int NOT NULL,
    PRIMARY KEY (einsatzid),
    FOREIGN KEY (mitarbeiterID) REFERENCES Einsatz (mitarbeiterID)
);