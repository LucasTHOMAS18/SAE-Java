-- Insert test data into SPORT
INSERT INTO SPORT (id_Sport, nom_Sport, forces_requis, agiliter_requis, endurance_requis, Collectif)
VALUES 
(1, 'Football', 70, 80, 85, TRUE),
(2, 'Basketball', 65, 90, 80, TRUE),
(3, 'Athletics', 80, 85, 90, FALSE);

-- Insert test data into PAYS
INSERT INTO PAYS (id_Pays, nom_Pays)
VALUES 
(1, 'France'),
(2, 'USA'),
(3, 'Canada');

-- Insert test data into EQUIPE
INSERT INTO EQUIPE (id_Equipe, nom_Equipe, id_Pays)
VALUES 
(1, 'Team A', 1),
(2, 'Team B', 2),
(3, 'Team C', 3);

-- Insert test data into ATHLETE
INSERT INTO ATHLETE (id_Athlete, nom_Athlete, sexe, forces, agiliter, endurance, id_Pays_1, id_Equipe_1, id_Sport, id_Pays_2, id_Equipe_2)
VALUES 
(1, 'John Doe', 'M', 75, 85, 80, 1, 1, 1, 2, 2),
(2, 'Jane Smith', 'F', 80, 90, 85, 2, 2, 2, 3, 3),
(3, 'Bob Johnson', 'M', 85, 80, 90, 3, 3, 3, 1, 1);

-- Insert test data into EPREUVE
INSERT INTO EPREUVE (id_Epreuve, nom_Epreuve, sexe_Epreuve, id_Sport)
VALUES 
(1, '100m Sprint', 'M', 3),
(2, '200m Sprint', 'F', 3),
(3, 'Marathon', 'M', 3);

-- Insert test data into PARTICIPE
INSERT INTO PARTICIPE (id_Athlete, id_Epreuve)
VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Insert test data into PARTICIPE_EQUIPE
INSERT INTO PARTICIPE_EQUIPE (id_Epreuve, id_Equipe)
VALUES 
(1, 1),
(2, 2),
(3, 3);
