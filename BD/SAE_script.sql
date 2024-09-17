DROP TABLE Athlete;
DROP TABLE Equipe;
DROP TABLE Pays;
DROP TABLE Sport;

CREATE TABLE Pays (
    nomPays VARCHAR(25) PRIMARY KEY NOT NULL
);

CREATE TABLE Equipe (
    idEquipe INT PRIMARY KEY NOT NULL,
    nomEquipe VARCHAR(25),
    idPays INT,
    FOREIGN KEY (idPays) REFERENCES Pays(nomPays)
);

CREATE TABLE Sport (
    idSport INT PRIMARY KEY NOT NULL,
    nomSport VARCHAR(25),
    forcesRequis INT,
    agiliteRequis INT,
    enduranceRequis INT,
    collectif BOOLEAN
);

CREATE TABLE Athlete (
    idAthlete INT PRIMARY KEY NOT NULL,
    nomAthlete VARCHAR(25),
    sexe CHAR,
    forces INT,
    agilite INT,
    endurance INT,
    idPays INT,
    idEquipe INT,
    nomEpreuve VARCHAR(25),
    sexeEpreuve CHAR,
    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),
    FOREIGN KEY (idPays) REFERENCES Pays(nomPays)
);

CREATE TABLE Participe (
    idAthlete INT,
    idEpreuve INT,
    PRIMARY KEY (idAthlete, idEpreuve)
);

CREATE TABLE ParticipeCollectif (
    idEquipe INT,
    idEpreuve INT,
    PRIMARY KEY (idEquipe, idEpreuve)
);

CREATE TABLE Epreuve (
    idEpreuve INT PRIMARY KEY,
    nomEpreuve VARCHAR(25),
    sexeEpreuve CHAR(1),
    idSport INT,
    collective BOOLEAN,
    FOREIGN KEY (idSport) REFERENCES Sport(idSport)
);