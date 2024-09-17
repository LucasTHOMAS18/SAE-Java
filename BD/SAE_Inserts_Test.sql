-- == INSERTIONS DE TEST == 

INSERT INTO PAYS (id_Pays, nom_Pays)
VALUES (1, 'France'),
       (2, 'Espagne'),
       (3, 'Italie');

INSERT INTO EQUIPE (idEquipe, nom_Equipe, id_Pays)
VALUES (1, 'Équipe A', 1),
       (2, 'Équipe B', 2),
       (3, 'Équipe C', 3);

INSERT INTO SPORT (id_Sport, nom_Sport, forces_requis, agiliter_requis, endurance_requis, Collectif)
VALUES (1, 'Football', 80, 70, 60, true),
       (2, 'Basketball', 75, 65, 55, true),
       (3, 'Natation', 60, 85, 90, false);

INSERT INTO ATHLETE (id_Athlete, nom_Athlete, sexe, forces, agiliter, endurance, id_Pays, id_Equipe, id_Sport, nom_Epreuve, sexe_Epreuve)
VALUES (1, 'Jean Dupont', 'M', 85, 75, 70, 1, 1, 1, 'Course de vitesse', 'M'),
       (2, 'Marie Leclerc', 'F', 70, 60, 85, 1, 1, 3, 'Natation en piscine', 'F'),
       (3, 'Pablo Sanchez', 'M', 90, 80, 65, 2, 2, 1, 'Match de football', 'M'),
       (4, 'Sophie Martin', 'F', 80, 70, 60, 2, 2, 2, 'Match de basketball', 'F'),
       (5, 'Luigi Rossi', 'M', 75, 65, 55, 3, 3, 2, 'Match de basketball', 'M');
