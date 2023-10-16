/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  To Mamiarilaza
 * Created: 8 oct. 2023
 */

CREATE TABLE quiz_type (
    id_quiz_type SERIAL PRIMARY KEY,
    quiz_type VARCHAR(40)
);

INSERT INTO quiz_type (id_quiz_type, quiz_type) VALUES
(1, 'Question'),
(2, 'Reponse');

CREATE TABLE quiz (
    id_quiz SERIAL PRIMARY KEY,
    id_service SERIAL,
    quiz_name VARCHAR(50),
    id_quiz_type SERIAL,
    status INTEGER,
    FOREIGN KEY(id_service) REFERENCES service(id_service),
    FOREIGN KEY(id_quiz_type) REFERENCES quiz_type(id_quiz_type)
);

INSERT INTO quiz (id_quiz, id_service, quiz_name, id_quiz_type, status) VALUES
(1, 3, 'Quiz pour les développeurs JUNIOR', 1, 1),
(2, 3, 'Quiz pour les administrateurs reseaux', 1, 1);

CREATE TABLE question (
    id_question SERIAL PRIMARY KEY,
    id_quiz SERIAL,
    question VARCHAR(100),
    score INTEGER,
    FOREIGN KEY (id_quiz) REFERENCES quiz(id_quiz)
);

INSERT INTO question (id_question, id_quiz, question, score) VALUES 
(1, 1, 'Quelle est votre interêt de postuler notre equipe ?', 2),
(2, 1, 'Comment avez vous apris le langage c# ?', 5),
(3, 1, 'Qu''est ce qui est important pour vous ?', 3),
(4, 1, 'Quel est le moyen le plus sure de bien faire son travail ?', 5),
(5, 2, 'Pour vous le reseaux c''est quoi ?', 5),
(6, 2, 'Vous avez commencer le réseaux quand ?', 3),
(7, 2, 'Quel sont les étapes pour trouver le problème dans un réseau ?', 8);


-- Le value est 1 si réponse 0 si mauvaise réponse
CREATE TABLE answer (
    id_answer SERIAL PRIMARY KEY,
    id_question SERIAL,
    answer VARCHAR(100),
    value INTEGER,
    FOREIGN KEY (id_question) REFERENCES question(id_question)
);

INSERT INTO answer (id_answer, id_question, answer, value) VALUES
(1, 1, 'Je veux gagner beaucoup d''argent', 1), 
(2, 1, 'J''aime bien travailler dans une équipe dynamique', 1), 
(3, 1, 'Je suis au chomage et je ne sais plus quoi faire', 0), 
(4, 2, 'Dans le programme d''école', 1), 
(5, 2, 'Je suis un auto didacte', 1), 
(6, 2, 'Je ne sais pas encore mais je peux apprendre', 0), 
(7, 3, 'Bien vivre sa vie', 1), 
(8, 3, 'Bien faire son travail', 0), 
(9, 4, 'Bien se documenter et travailler dur', 0), 
(10, 4, 'Mieux dispatcher les taches et travailler en équipe', 1), 
(11, 4, 'Demander de l''aide', 0), 
(12, 5, 'Une toile d''arraignes', 1), 
(13, 5, 'Les fils du poteau du JIRAMA', 0), 
(14, 5, 'C''est la vie', 0), 
(15, 6, 'Depuis que j''étais petite', 0), 
(16, 6, 'A l''école dans un projet', 1), 
(17, 7, 'Analyser-Verifier-Trouver', 1), 
(18, 7, 'Voir n''importe ou', 0), 
(19, 7, 'Regarder le camera de surveillance', 0); 

-- View joignant le quiz et son service
CREATE VIEW v_quiz_full_info AS
SELECT 
    q.*, s.service, s.fonction, s.status as service_status, quiz_type
FROM 
    quiz q JOIN service s ON q.id_service = s.id_service
    JOIN quiz_type qt ON q.id_quiz_type = qt.id_quiz_type;

-- Base pour contenir le résultat des tests d'un candidat
CREATE TABLE candidature_test (
    id_candidature_test SERIAL PRIMARY KEY,
    id_candidature SERIAL,
    note INTEGER,
    id_quiz SERIAL,
    quiz_date TIMESTAMP,
    FOREIGN KEY(id_quiz) REFERENCES quiz(id_quiz)
    -- Don't forget to add constraint to id_candidature
);
