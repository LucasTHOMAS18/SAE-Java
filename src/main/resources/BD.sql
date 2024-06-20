-- Création de la base
DROP DATABASE IF EXISTS JO2024;
CREATE DATABASE JO2024;
USE JO2024;

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
    idEquipe INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nomEquipe VARCHAR(25),
    nomPays VARCHAR(25),
    FOREIGN KEY (nomPays) REFERENCES Pays(nomPays)
);

-- Création de la table Epreuve
CREATE TABLE Epreuve (
    idEpreuve INT PRIMARY KEY AUTO_INCREMENT,
    nomEpreuve VARCHAR(25),
    sexeEpreuve CHAR(1),
    nomSport VARCHAR(25),
    collective BOOLEAN,
    FOREIGN KEY (nomSport) REFERENCES Sport(nomSport)
);

-- Création de la table Athlete
CREATE TABLE Athlete (
    idAthlete INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
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
    points INT,
    PRIMARY KEY (idAthlete, idEpreuve),
    FOREIGN KEY (idAthlete) REFERENCES Athlete(idAthlete),
    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)
);

-- Création de la table ParticipeCollectif
CREATE TABLE ParticipeCollectif (
    idEpreuve INT,
    idEquipe INT,
    points INT,
    PRIMARY KEY (idEquipe, idEpreuve),
    FOREIGN KEY (idEquipe) REFERENCES Equipe(idEquipe),
    FOREIGN KEY (idEpreuve) REFERENCES Epreuve(idEpreuve)
);

DROP ROLE IF EXISTS Admin;
DROP ROLE IF EXISTS Organisateur;
DROP ROLE IF EXISTS Journaliste;

CREATE ROLE Admin;
CREATE ROLE Organisateur;
CREATE ROLE Journaliste;

GRANT ALL PRIVILEGES ON JO2024.* TO Admin;
GRANT USAGE ON JO2024.* TO Admin;

GRANT SELECT, INSERT, UPDATE, DELETE ON JO2024.* TO Organisateur;
GRANT USAGE ON JO2024.* TO Organisateur;

GRANT SELECT ON JO2024.Pays TO Journaliste;
GRANT SELECT ON JO2024.Sport TO Journaliste;
GRANT SELECT ON JO2024.Equipe TO Journaliste;
GRANT SELECT ON JO2024.Epreuve TO Journaliste;
GRANT SELECT ON JO2024.Athlete TO Journaliste;
GRANT SELECT ON JO2024.Participe TO Journaliste;
GRANT SELECT ON JO2024.ParticipeCollectif TO Journaliste;
GRANT USAGE ON JO2024.* TO Journaliste;