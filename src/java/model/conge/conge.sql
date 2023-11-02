/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  To Mamiarilaza
 * Created: 20 oct. 2023
 */

-- Modification dans la table utilisateur
ALTER TABLE utilisateur ADD COLUMN admin BOOLEAN;
UPDATE utilisateur SET admin = false;

ALTER TABLE utilisateur ADD COLUMN id_personnel INTEGER;
ALTER TABLE utilisateur ADD CONSTRAINT fkey_personnel FOREIGN KEY (id_personnel) REFERENCES personnel(id_personnel);

CREATE TABLE Personnel (
    id_personnel SERIAL PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    date_naissance DATE,
    telephone VARCHAR(10),
    id_superieur INT,
    date_embauche DATE,
    poste VARCHAR(30),
    salaire DECIMAL(10, 2),
    id_classe_employe SERIAL,
    FOREIGN KEY (id_superieur) REFERENCES Personnel(id_personnel)
    FOREIGN KEY (id_classe_employe) REFERENCES classe_employe(id_categorie_employe)
);

CREATE TABLE type_conge (
    id_type_conge SERIAL PRIMARY KEY,
    type_conge_name VARCHAR(50),
    is_cumulable BOOLEAN,
    sexe_valability INTEGER,        -- Homme 1 et Femme 0 et 2 pour tous    ?? Mety ve izao hoe 0, 1, 2 sa misy fika tsara ??
    duree INTEGER,                  -- O si non déterminée en jour
    etat INTEGER
);

CREATE TABLE conge_parameter (
    accumulation DECIMAL(3,1),       -- 2.5 par mois par exemple
    minimum_duree INTEGER,      -- 1 ans apres vao mahazo maka conge
    max_solde INTEGER           -- 90 jour
);

CREATE TABLE conge (
    id_conge SERIAL PRIMARY KEY,
    id_personnel SERIAL,
    id_type_conge SERIAL,
    deposit_date DATE DEFAULT NOW(),
    explication VARCHAR(200),
    date_debut_demande DATE,
    date_fin_demande DATE,
    date_debut_reel DATE,
    date_fin_reel DATE,
    id_chef_hierarchique INTEGER,
    remarque_chef_hierarchique VARCHAR(200),
    id_personnel_rh INTEGER,
    remarque_personnel_rh VARCHAR(200),
    etat INTEGER,        -- Etat 0 annulé, 1 refusé, 2 en attente, 3 validé
    FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel),
    FOREIGN KEY(id_type_conge) REFERENCES type_conge(id_type_conge),
    FOREIGN KEY(id_chef_hierarchique) REFERENCES personnel(id_personnel),
    FOREIGN KEY(id_personnel_rh) REFERENCES personnel(id_personnel)
);

CREATE TABLE conge_report (
    id_personnel SERIAL,
    annee INTEGER,
    reste_conge INTEGER,
    FOREIGN KEY(id_personnel) REFERENCES personnel(id_personnel)
);

INSERT INTO conge_report (id_personnel, annee, reste_conge) 
VALUES 
(12, 2022, 0),
(12, 2023, 30),
(13, 2021, 0),
(13, 2022, 30),
(13, 2023, 25),
(16, 2023, 0),
(17, 2022, 0),
(17, 2022, 30);


-- Donnes pour le personnel
INSERT INTO Personnel (nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste)
VALUES
('Smith', 'John', '1990-01-15', '1234567890', NULL, '2022-02-15', 'Developpeur'),
('Doe', 'Jane', '1985-03-30', '9876543210', NULL, '2021-11-30', 'Concepteur')
('Johnson', 'Michael', '1995-05-20', '5555555555', 12, '2023-04-10', 'Designer'),
('Williams', 'Sarah', '1998-07-10', '6666666666', 13, '2022-08-05', 'Designer');
--('Davis', 'Robert', '1992-12-25', '7777777777', 1, '2013-07-22', 'Chaffeur'),
--('Taylor', 'Emily', '1994-09-30', '8888888888', 2, '2016-03-17', 'Femme de menage'),
--('Brown', 'Christopher', '1997-04-03', '9999999999', NULL, '2018-11-25', 'Gardien');

INSERT INTO type_conge (type_conge_name, is_cumulable, sexe_valability, duree) VALUES
('Congé de maternité', 'f', 0, 90),
('Congé de paternité', 'f', 1, 3),
('Repos médicale', 'f', 2, 0),
('Vacance', 't', 2, 0),
('Evenement familiale', 't', 2, 0);

INSERT INTO type_conge (type_conge_name, is_cumulable, sexe_valability, duree) VALUES
('Vente de conge', 't', 2, 0);

INSERT INTO conge_parameter VALUES
(2.5, 365, 90);

-- Employé 1
INSERT INTO conge (id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, date_debut_reel, date_fin_reel, id_chef_hierarchique, remarque_chef_hierarchique, id_personnel_rh, remarque_personnel_rh, etat)
VALUES
(1, 3, 'Repos médical', '2023-08-15', '2023-08-17', NULL, NULL, NULL, NULL, NULL, NULL, 2), -- Non validé
(1, 4, 'Vacance', '2023-07-10', '2023-07-20', NULL, NULL, NULL, NULL, 5, 'On a besoin de vous en ce moment', 1), -- Refusé
(1, 5, 'Evenement familiale', '2023-06-01', '2023-06-05', '2023-06-01', '2023-06-05', NULL, NULL, 5, 'OK bonne vacance', 3); -- Validé
-- Employé 2
INSERT INTO conge (id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, date_debut_reel, date_fin_reel, id_chef_hierarchique, remarque_chef_hierarchique, id_personnel_rh, remarque_personnel_rh, etat)
VALUES
(3, 2, 'Congé de paternité', '2023-05-15', '2023-05-18', '2023-05-15', '2023-05-19', 1, 'Bonne travail pere', 5, NULL, 3), -- Non validé
(3, 3, 'Repos médical', '2023-09-10', '2023-09-12', NULL, NULL, 1, 'Vous faites semblant d''etre malade', NULL, NULL, 1), -- Refusé
(3, 4, 'Vacance', '2023-07-05', '2023-07-15', '2023-07-08', '2023-07-15', 1, NULL, 5, NULL, 3); -- Validé
-- Employé 3
INSERT INTO conge (id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, date_debut_reel, date_fin_reel, id_chef_hierarchique, remarque_chef_hierarchique, id_personnel_rh, remarque_personnel_rh, etat)
VALUES
(4, 4, 'Vacance', '2023-10-05', '2023-10-10', NULL, NULL, NULL, NULL, NULL, NULL, 2), -- Non validé
(4, 5, 'Evenement familiale', '2023-12-01', '2023-12-05', NULL, NULL, 1, 'Tu dois encore finir ton travail', NULL, NULL, 1), -- Refusé
(4, 1, 'Congé de maternité', '2023-11-15', '2023-11-25', '2023-11-16', '2023-11-23', 1, 'Bonne accouchement', 5, 'Bonne accouchement', 3); -- Validé
-- Employé 4
INSERT INTO conge (id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, date_debut_reel, date_fin_reel, id_chef_hierarchique, remarque_chef_hierarchique, id_personnel_rh, remarque_personnel_rh, etat)
VALUES
(5, 2, 'Congé de paternité', '2023-08-10', '2023-08-12', NULL, NULL, NULL, NULL, NULL, NULL, 2), -- Demande
(5, 3, 'Repos médical', '2023-11-05', '2023-11-10', NULL, NULL, 2, 'Ok tu attends la validation du RH', NULL, NULL, 2), -- Demande
(5, 5, 'Evenement familiale', '2023-06-15', '2023-06-20', NULL, NULL, 2, 'Tu ne peux pas', NULL, NULL, 1); -- Validé

-- les conges avec leurs types
CREATE VIEW v_conge_with_type AS
SELECT c.*, tc.type_conge_name, tc.is_cumulable, tc.sexe_valability, tc.duree, p.id_superieur 
FROM conge c JOIN type_conge tc ON c.id_type_conge = tc.id_type_conge
JOIN personnel p ON c.id_personnel = p.id_personnel;

-- les demandes de conge
CREATE VIEW v_demande_conge AS
SELECT * FROM v_conge_with_type WHERE etat = 2 AND date_debut_demande > NOW();

-- les conges validé par le chef
CREATE VIEW v_conge_valide_chef AS
SELECT * FROM v_conge_with_type WHERE etat = 3;

-- les conges validé
CREATE VIEW v_conge_valide AS
SELECT * FROM v_conge_with_type WHERE etat = 4;

-- les conges refusé
CREATE VIEW v_conge_refuse AS
SELECT * FROM v_conge_with_type WHERE etat = 1;

-- les conges en cours
CREATE VIEW v_current_conge AS 
SELECT * FROM v_conge_valide WHERE date_debut_reel <= NOW() AND date_fin_reel is null;
