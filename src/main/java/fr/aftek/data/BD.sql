Drop table ATHLETE;
Drop table EQUIPE;
Drop table PAYS;
Drop table SPORT;

create table PAYS(
    id_Pays INT PRIMARY KEY NOT NULL,
    nom_Pays varchar(25)
);

create table EQUIPE(
    idEquipe INT PRIMARY KEY NOT NULL,
    nom_Equipe varchar(25),
    id_Pays int,
    FOREIGN KEY (id_Pays) REFERENCES Pays(id_Pays)
);

create table SPORT(
  id_Sport INT PRIMARY KEY NOT NULL,
  nom_Sport varchar(25),
  forces_requis int,
  agiliter_requis int,
  endurance_requis int,
  Collectif boolean
);

create table ATHLETE(
    id_Athlete INT PRIMARY KEY NOT NULL,
    nom_Athlete varchar(25),
    sexe char,
    forces int,
    agiliter int,
    endurance int,
    id_Pays int,
    id_Equipe int,
    id_Sport int,
    nom_Epreuve varchar(25),
    sexe_Epreuve char,
    FOREIGN KEY (id_Sport) REFERENCES SPORT(id_Sport),
    FOREIGN KEY (id_Equipe) REFERENCES EQUIPE(idEquipe),
    FOREIGN KEY (id_Pays) REFERENCES PAYS(id_Pays)
);
