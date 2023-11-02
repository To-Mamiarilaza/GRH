/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  To Mamiarilaza
 * Created: 26 oct. 2023
 */

-- On doit donc crée un view pour afficher les employees en cours pour enlever les licencié

CREATE TABLE type_licenciement (
    id_type_licenciement SERIAL PRIMARY KEY,
    type_licenciement VARCHAR(50)
);

INSERT INTO type_licenciement VALUES 
(1, 'Demande employe'),
(2, 'Ordre employeur');

CREATE TABLE licenciement (
    id_licenciement SERIAL PRIMARY KEY,
    id_employe SERIAL,
    date_preavis DATE,
    date_licenciement DATE,
    id_type_licenciement SERIAL,
    droit_preavis DECIMAL(10, 2),
    etat INTEGER,
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel),
    FOREIGN KEY(id_type_licenciement) REFERENCES type_licenciement(id_type_licenciement)
);

CREATE TABLE classe_employe (
    id_classe_employe SERIAL PRIMARY KEY,
    classe_employe VARCHAR(50),
    classe_description  VARCHAR(50),
    indamnite_licenciement DECIMAL(10, 2),
    duree_preavis INTEGER -- Nombre de jour
);

INSERT INTO classe_employe (id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis) VALUES 
(DEFAULT, 'D', 'Emplois salaries d''execution peu qualifies', 100000, 30),
(DEFAULT, 'C', 'Emplois salaries d''execution qualifies', 200000, 45),
(DEFAULT, 'B', 'Emplois salaries de niveau intermediaire', 300000, 60),
(DEFAULT, 'A', 'Emplois salaries de niveau supérieur', 500000, 90),
(DEFAULT, 'HC', 'Emplois Hors Classe', 500000, 120);

-- Table requis pour les primes
CREATE TABLE prime (
    id_prime SERIAL PRIMARY KEY,
    prime_name VARCHAR(50),
    etat INTEGER DEFAULT 1
);

INSERT INTO prime (prime_name) VALUES
('Prime d''anciennete'),
('Prime de rendement'),
('Prime divers');

CREATE TABLE prime_employe (
    id_prime_employe SERIAL PRIMARY KEY,
    id_prime SERIAL,
    id_employe SERIAL,
    date_prime DATE,
    montant DECIMAL(10, 2),
    etat INTEGER DEFAULT 1,
    FOREIGN KEY(id_prime) REFERENCES prime(id_prime),
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel)
);
    
-- Table requis pour les heures supplementaires
CREATE TABLE heure_supplementaire (
    id_heure_supplementaire SERIAL PRIMARY KEY,
    id_employe SERIAL,
    debut TIMESTAMP,
    fin TIMESTAMP,
    etat INTEGER,
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel)
);

CREATE TABLE heure_sup_duration_parameter (
    heure_debut INTEGER,    -- Duration
    heure_fin INTEGER,
    pourcentage DECIMAL(4,1)
);

INSERT INTO heure_sup_duration_parameter (heure_debut, heure_fin, pourcentage) VALUES
(0, 8, 30),
(9, 16, 50);    -- On va les prendres ensembles

CREATE TABLE night_work_parameter (
    debut_heure TIME,
    fin_heure TIME,
    pourcentage DECIMAL(4,1)
);

INSERT INTO night_work_parameter VALUES 
('22:00', '23:59', 30),
('00:00', '05:00', 30);

CREATE TABLE week_end_parameter (
    day INTEGER,
    pourcentage DECIMAL(4, 1)
);

INSERT INTO week_end_parameter (day, pourcentage) VALUES 
(7, 40);

CREATE TABLE out_work_parameter (
    day INTEGER,
    month INTEGER,
    pourcentage DECIMAL(4, 1)
);

INSERT INTO out_work_parameter (day, month, pourcentage) VALUES
(1, 1, 100),
(26, 6, 100);

-- table pour le vente conge
CREATE TABLE vente_conge (
    id_vente_conge SERIAL PRIMARY KEY,
    id_employe SERIAL,
    debut DATE,
    fin DATE,
    montant DECIMAL(10, 2),
    etat INTEGER,
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel)
);

INSERT INTO type_conge (type_conge_name, is_cumulable, sexe_valability, duree) VALUES
('Vente de conge', 't', 2, 0);

-- table pour les organisme et taxe
CREATE TABLE organisme (
    id_organisme SERIAL PRIMARY KEY,
    nom_organisme VARCHAR(30),
    description VARCHAR(50),
    etat INTEGER DEFAULT 1
);

INSERT INTO organisme (nom_organisme, description) VALUES 
('CNAPS', 'Caisse NAtional de Prevoyance Sociale'),
('OSTIE', 'Organisation Sante Entreprise');

CREATE TABLE organisme_parameter (
    id_organisme SERIAL,
    pourcentage DECIMAL(4, 2),
    plafond INTEGER,    -- 8 fois SMIG par  exemple
    FOREIGN KEY (id_organisme) REFERENCES organisme(id_organisme)
);

INSERT INTO organisme_parameter (id_organisme, pourcentage, plafond) VALUES
(1, 1, 8),
(1, 1, 4);

CREATE TABLE smig_parameter (
    smig_value DECIMAL(10, 2)
);

INSERT INTO smig_parameter (smig_value) VALUES (250000);

CREATE TABLE irsa_parameter (
    debut DECIMAL(10, 2),
    fin DECIMAL(10, 2),
    pourcentage DECIMAL(4, 2)
);

INSERT INTO irsa_parameter (debut, fin, pourcentage) VALUES
(0, 350000, 0),
(350001, 400000, 5),
(400001, 500000, 10),
(500001, 600000, 15),
(600001, null, 20);

-- Table pour marquer les abscences
CREATE TABLE abscence (
    id_abscence SERIAL PRIMARY KEY, 
    id_employe SERIAL,
    date DATE,
    heure_debut TIME,
    heure_fin TIME,
    etat INTEGER,
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel)
);

-- Table pour le demande d'avance
CREATE TABLE avance (
    id_avance SERIAL PRIMARY KEY,
    id_employe SERIAL,
    date Date,
    montant DECIMAL(10, 2),
    etat INTEGER,
    FOREIGN KEY(id_employe) REFERENCES personnel(id_personnel)
);

-- Table pour le rappel de période antérieur
CREATE TABLE rappel_periode (
    id_rappel_periode SERIAL PRIMARY KEY,
    date DATE,
    modification_salaire DECIMAL(4, 1),
    nombre_mois INTEGER
);

CREATE TABLE departement_rappel_periode (
    id_service INTEGER,
    id_rappel_periode INTEGER,
    FOREIGN KEY(id_service) REFERENCES service(id_service),
    FOREIGN KEY(id_rappel_periode) REFERENCES rappel_periode(id_rappel_periode),
    PRIMARY KEY(id_service, id_rappel_periode)
);