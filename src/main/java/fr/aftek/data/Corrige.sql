CREATE TABLE ATHLETE (
  PRIMARY KEY (id_Athlete),
  id_Athlete int,
  nom_Athlete varchar(25),
  sexe varchar(1),     
  forces int,
  agiliter int,
  endurance int,
  id_Equipe int
);

CREATE TABLE EPREUVE (
  PRIMARY KEY (id_Epreuve),
  id_Epreuve int,
  nom_Epreuve varchar(25),
  sexe_Epreuve varchar(1),
  id_Sport int
);

CREATE TABLE EQUIPE (
  PRIMARY KEY (id_Equipe),
  id_Equipe  VARCHAR(42) NOT NULL,
  nom_Equipe VARCHAR(42)
);

CREATE TABLE PARTICIPE (
  PRIMARY KEY (id_Athlete, id_Epreuve),
  id_Athlete VARCHAR(42) NOT NULL,
  id_Epreuve VARCHAR(42) NOT NULL
);

CREATE TABLE PARTICIPE_EQUIPE (
  PRIMARY KEY (id_Epreuve, id_Equipe),
  id_Epreuve int,
  id_Equipe int
);

CREATE TABLE PAYS (
  PRIMARY KEY (id_Pays),
  id_Pays int,
  nom_Pays varchar(25),
  id_Equipe int
);

CREATE TABLE SPORT (
  PRIMARY KEY (id_Sport),
  id_Sport         VARCHAR(42) NOT NULL,
  nom_Sport        VARCHAR(42),
  forces_requis    VARCHAR(42),
  agiliter_requis  VARCHAR(42),
  endurance_requis VARCHAR(42),
  Collectif        VARCHAR(42)
);

ALTER TABLE ATHLETE ADD FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe);

ALTER TABLE EPREUVE ADD FOREIGN KEY (id_Sport) REFERENCES SPORT (id_Sport);

ALTER TABLE PARTICIPE ADD FOREIGN KEY (id_Epreuve) REFERENCES EPREUVE (id_Epreuve);
ALTER TABLE PARTICIPE ADD FOREIGN KEY (id_Athlete) REFERENCES ATHLETE (id_Athlete);
ALTER TABLE PARTICIPE_EQUIPE ADD FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe);
ALTER TABLE PARTICIPE_EQUIPE ADD FOREIGN KEY (id_Epreuve) REFERENCES EPREUVE (id_Epreuve);

ALTER TABLE PAYS ADD FOREIGN KEY (id_Equipe) REFERENCES EQUIPE (id_Equipe);