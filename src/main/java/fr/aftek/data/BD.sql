-- Suppression des tables existantes pour éviter les conflits
DROP TABLE IF EXISTS ParticipeCollectif;
DROP TABLE IF EXISTS Participe;
DROP TABLE IF EXISTS Athlete;
DROP TABLE IF EXISTS Epreuve;
DROP TABLE IF EXISTS Equipe;
DROP TABLE IF EXISTS Sport;
DROP TABLE IF EXISTS Pays;

-- Création de la table Pays
CREATE TABLE Pays (
    nomPays VARCHAR(25) PRIMARY KEY NOT NULL
);

-- Création de la table Sport
CREATE TABLE Sport (
    nomSport VARCHAR(25) PRIMARY KEY NOT NULL,
    forcesRequis INT,
    agiliteRequis INT,
    enduranceRequis INT,
    individuel BOOLEAN NOT NULL,
    nbEquipes INT,
    nbJoueursParEquipe INT
);

-- Création de la table Equipe
CREATE TABLE Equipe (
    idEquipe INT PRIMARY KEY NOT NULL,
    nomEquipe VARCHAR(25),
    nomPays VARCHAR(25),
    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)
);

-- Création de la table Epreuve
CREATE TABLE Epreuve (
    idEpreuve INT PRIMARY KEY,
    nomEpreuve VARCHAR(25),
    sexeEpreuve CHAR(1),
    idSport INT,
    collective BOOLEAN,
    FOREIGN KEY (idSport) REFERENCES Sport(idSport)
);

-- Création de la table Athlete
CREATE TABLE Athlete (
    idAthlete INT PRIMARY KEY NOT NULL,
    nomAthlete VARCHAR(25),
    prenomAthlete VARCHAR(25),
    sexe CHAR(1),
    forces INT,
    agilite INT,
    endurance INT,
    nomPays VARCHAR(25),
    nomSport VARCHAR(25),
    idEquipe INT,

    Foreign Key (nomSport) REFERENCES Sport(nomSport),
    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),
    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)
);

-- Création de la table Participe
CREATE TABLE Participe (
    idEpreuve INT,
    idAthlete INT,
    PRIMARY KEY (idAthlete, idEpreuve),
    FOREIGN KEY (idAthlete) REFERENCES Athlete(idAthlete),
    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)
);

-- Création de la table ParticipeCollectif
CREATE TABLE ParticipeCollectif (
    idEpreuve INT,
    idEquipe INT,
    PRIMARY KEY (idEquipe, idEpreuve),
    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),
    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)
);
