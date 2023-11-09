CREATE DATABASE gestion_entreprise;

\c gestion_entreprise;

CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".abscence_id_abscence_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".abscence_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".adresse_id_adresse_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".annonce_id_annonce_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".answer_id_answer_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".answer_id_question_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".avance_id_avance_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".avance_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".besoin_id_besoin_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".candidature_id_candidature_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".candidature_test_id_candidature_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".candidature_test_id_candidature_test_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".candidature_test_id_quiz_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".classe_employe_id_classe_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".conge_id_conge_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".conge_id_personnel_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".conge_id_type_conge_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".conge_report_id_personnel_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".contrat_id_contrat_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".diplome_id_diplome_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".employe_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".experience_id_experience_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".formation_base_id_formation_base_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".heure_supplementaire_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".heure_supplementaire_id_heure_supplementaire_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".licenciement_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".licenciement_id_licenciement_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".licenciement_id_type_licenciement_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".organisme_id_organisme_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".organisme_parameter_id_organisme_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".personnel_id_classe_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".personnel_id_personnel_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".prime_employe_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".prime_employe_id_prime_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".prime_employe_id_prime_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".prime_id_prime_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".professional_career_id_professional_career_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".province_id_province_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".question_id_question_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".question_id_quiz_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".quiz_id_quiz_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".quiz_id_quiz_type_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".quiz_id_service_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".quiz_type_id_quiz_type_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".rappel_periode_id_rappel_periode_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".salaire_id_salaire_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".service_id_service_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".sexe_id_sexe_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".task_id_task_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".type_conge_id_type_conge_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".type_licenciement_id_type_licenciement_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".unity_id_unity_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".utilisateur_id_utilisateur_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".vente_conge_id_employe_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".vente_conge_id_vente_conge_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".wanted_profile_id_wanted_profile_seq START WITH 20 INCREMENT BY 1;

CREATE SEQUENCE "public".work_location_id_work_location_seq AS integer START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".workload_id_workload_seq START WITH 20 INCREMENT BY 1;

CREATE  TABLE "public".adresse ( 
	id_adresse           integer DEFAULT nextval('adresse_id_adresse_seq'::regclass) NOT NULL  ,
	adresse              varchar(50)    ,
	status               numeric    ,
	CONSTRAINT adresse_pkey PRIMARY KEY ( id_adresse )
 );

CREATE  TABLE "public".classe_employe ( 
	id_classe_employe    integer DEFAULT nextval('classe_employe_id_classe_employe_seq'::regclass) NOT NULL  ,
	classe_employe       varchar(50)    ,
	classe_description   varchar(50)    ,
	indamnite_licenciement numeric(10,2)    ,
	duree_preavis        integer    ,
	CONSTRAINT classe_employe_pkey PRIMARY KEY ( id_classe_employe )
 );

CREATE  TABLE "public".conge_parameter ( 
	accumulation         numeric(3,1)    ,
	minimum_duree        integer    ,
	max_solde            integer    
 );

CREATE  TABLE "public".diplome ( 
	id_diplome           integer DEFAULT nextval('diplome_id_diplome_seq'::regclass) NOT NULL  ,
	diplome              varchar(50)    ,
	status               numeric    ,
	CONSTRAINT diplome_pkey PRIMARY KEY ( id_diplome )
 );

CREATE  TABLE "public".experience ( 
	id_experience        integer DEFAULT nextval('experience_id_experience_seq'::regclass) NOT NULL  ,
	experience           varchar(50)    ,
	status               numeric    ,
	CONSTRAINT experience_pkey PRIMARY KEY ( id_experience )
 );

CREATE  TABLE "public".heure_sup_duration_parameter ( 
	heure_debut          integer    ,
	heure_fin            integer    ,
	pourcentage          numeric(4,1)    
 );

CREATE  TABLE "public".irsa_parameter ( 
	debut                numeric(10,2)    ,
	fin                  numeric(10,2)    ,
	pourcentage          numeric(4,2)    
 );

CREATE  TABLE "public".night_work_parameter ( 
	debut_heure          time    ,
	fin_heure            time    ,
	pourcentage          numeric(4,1)    
 );

CREATE  TABLE "public".organisme ( 
	id_organisme         integer DEFAULT nextval('organisme_id_organisme_seq'::regclass) NOT NULL  ,
	nom_organisme        varchar(30)    ,
	description          varchar(50)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT organisme_pkey PRIMARY KEY ( id_organisme )
 );

CREATE  TABLE "public".organisme_parameter ( 
	id_organisme         integer DEFAULT nextval('organisme_parameter_id_organisme_seq'::regclass) NOT NULL  ,
	pourcentage          numeric(4,2)    ,
	plafond              integer    ,
	CONSTRAINT organisme_parameter_id_organisme_fkey FOREIGN KEY ( id_organisme ) REFERENCES "public".organisme( id_organisme )   
 );

CREATE  TABLE "public".out_work_parameter ( 
	"day"                integer    ,
	"month"              integer    ,
	pourcentage          numeric(4,1)    
 );

CREATE  TABLE "public".personnel ( 
	id_personnel         integer DEFAULT nextval('personnel_id_personnel_seq'::regclass) NOT NULL  ,
	nom                  varchar(50)    ,
	prenom               varchar(50)    ,
	date_naissance       date    ,
	telephone            varchar(10)    ,
	id_superieur         integer    ,
	date_embauche        date    ,
	poste                varchar(30)    ,
	salaire              numeric(10,2)    ,
	id_classe_employe    integer DEFAULT nextval('personnel_id_classe_employe_seq'::regclass) NOT NULL  ,
	CONSTRAINT personnel_pkey PRIMARY KEY ( id_personnel ),
	CONSTRAINT personnel_id_superieur_fkey FOREIGN KEY ( id_superieur ) REFERENCES "public".personnel( id_personnel )   ,
	CONSTRAINT classe_fkey FOREIGN KEY ( id_classe_employe ) REFERENCES "public".classe_employe( id_classe_employe )   
 );

CREATE  TABLE "public".prime ( 
	id_prime             integer DEFAULT nextval('prime_id_prime_seq'::regclass) NOT NULL  ,
	prime_name           varchar(50)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT prime_pkey PRIMARY KEY ( id_prime )
 );

CREATE  TABLE "public".province ( 
	id_province          integer DEFAULT nextval('province_id_province_seq'::regclass) NOT NULL  ,
	name                 varchar(50)    ,
	CONSTRAINT province_pkey PRIMARY KEY ( id_province )
 );

CREATE  TABLE "public".quiz_type ( 
	id_quiz_type         integer DEFAULT nextval('quiz_type_id_quiz_type_seq'::regclass) NOT NULL  ,
	quiz_type            varchar(40)    ,
	CONSTRAINT quiz_type_pkey PRIMARY KEY ( id_quiz_type )
 );

CREATE TABLE states(
    id_states serial primary key not null,
    states varchar(20)
);

insert into states values (default, 'intact'),
                            (default, 'en cours'),
                            (default, 'fini');

CREATE TABLE todo (
	id_todo SERIAL primary key not null,
	id_service INTEGER,
	todo VARCHAR(100),
	start_date TIMESTAMP,
	deadline TIMESTAMP,
        id_states integer references states(id_states),
	status INTEGER,
	FOREIGN KEY (id_service) REFERENCES service(id_service)
);

CREATE TABLE under_task (
	id_under_task SERIAL primary key not null,
	id_employe INTEGER,
	id_todo INTEGER,
	under_task VARCHAR(100),
        deadline TIMESTAMP,
        id_states integer references states(id_states),
	status INTEGER,
	FOREIGN KEY(id_employe) REFERENCES employe(id_employe),
	FOREIGN KEY(id_todo) REFERENCES todo(id_todo)
);

CREATE  TABLE "public".rappel_periode ( 
	id_rappel_periode    integer DEFAULT nextval('rappel_periode_id_rappel_periode_seq'::regclass) NOT NULL  ,
	"date"               date    ,
	modification_salaire numeric(4,1)    ,
	nombre_mois          integer    ,
	etat                 integer    ,
	CONSTRAINT rappel_periode_pkey PRIMARY KEY ( id_rappel_periode )
 );

CREATE  TABLE "public".salaire ( 
	id_salaire           integer DEFAULT nextval('salaire_id_salaire_seq'::regclass) NOT NULL  ,
	salaire              double precision    ,
	status               numeric    ,
	CONSTRAINT salaire_pkey PRIMARY KEY ( id_salaire )
 );

CREATE  TABLE "public".service ( 
	id_service           integer DEFAULT nextval('service_id_service_seq'::regclass) NOT NULL  ,
	service              varchar(50)    ,
	fonction             varchar(50)    ,
	creation_date        date    ,
	status               numeric    ,
	CONSTRAINT service_pkey PRIMARY KEY ( id_service )
 );

CREATE  TABLE "public".sexe ( 
	id_sexe              integer DEFAULT nextval('sexe_id_sexe_seq'::regclass) NOT NULL  ,
	sexe                 varchar(20)    ,
	status               numeric    ,
	CONSTRAINT sexe_pkey PRIMARY KEY ( id_sexe )
 );

CREATE  TABLE "public".smig_parameter ( 
	smig_value           numeric(10,2) DEFAULT '250000'::numeric   
 );

CREATE  TABLE "public".society ( 
	name                 varchar(50)    ,
	adresse              varchar(50)    ,
	nif                  varchar(50)    ,
	contact              varchar(25)    ,
	secteur              varchar(20)    ,
	legal_statut         numeric    
 );

CREATE  TABLE "public".type_conge ( 
	id_type_conge        integer DEFAULT nextval('type_conge_id_type_conge_seq'::regclass) NOT NULL  ,
	type_conge_name      varchar(50)    ,
	is_cumulable         boolean    ,
	sexe_valability      integer    ,
	duree                integer    ,
	etat                 integer    ,
	CONSTRAINT type_conge_pkey PRIMARY KEY ( id_type_conge )
 );

CREATE  TABLE "public".type_licenciement ( 
	id_type_licenciement integer DEFAULT nextval('type_licenciement_id_type_licenciement_seq'::regclass) NOT NULL  ,
	type_licenciement    varchar(50)    ,
	CONSTRAINT type_licenciement_pkey PRIMARY KEY ( id_type_licenciement )
 );

CREATE  TABLE "public".unity ( 
	id_unity             integer DEFAULT nextval('unity_id_unity_seq'::regclass) NOT NULL  ,
	unity                varchar(20)    ,
	status               numeric    ,
	CONSTRAINT unity_pkey PRIMARY KEY ( id_unity )
 );

CREATE  TABLE "public".week_end_parameter ( 
	"day"                integer    ,
	pourcentage          numeric(4,1)    
 );

CREATE  TABLE "public".work_location ( 
	id_work_location     integer DEFAULT nextval('work_location_id_work_location_seq'::regclass) NOT NULL  ,
	name                 varchar(100)    ,
	id_adress            integer    ,
	CONSTRAINT work_location_pkey PRIMARY KEY ( id_work_location ),
	CONSTRAINT work_location_id_adress_fkey FOREIGN KEY ( id_adress ) REFERENCES "public".adresse( id_adresse )   
 );

CREATE  TABLE "public".besoin ( 
	id_besoin            integer DEFAULT nextval('besoin_id_besoin_seq'::regclass) NOT NULL  ,
	id_service           integer    ,
	creation_date        date    ,
	description          varchar(200)    ,
	status               numeric    ,
	CONSTRAINT besoin_pkey PRIMARY KEY ( id_besoin ),
	CONSTRAINT besoin_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service )   
 );

CREATE  TABLE "public".departement_rappel_periode ( 
	id_service           integer  NOT NULL  ,
	id_rappel_periode    integer  NOT NULL  ,
	CONSTRAINT departement_rappel_periode_pkey PRIMARY KEY ( id_service, id_rappel_periode ),
	CONSTRAINT departement_rappel_periode_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service )   ,
	CONSTRAINT departement_rappel_periode_id_rappel_periode_fkey FOREIGN KEY ( id_rappel_periode ) REFERENCES "public".rappel_periode( id_rappel_periode )   
 );

CREATE  TABLE "public".licenciement ( 
	id_licenciement      integer DEFAULT nextval('licenciement_id_licenciement_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('licenciement_id_employe_seq'::regclass) NOT NULL  ,
	date_preavis         date    ,
	date_licenciement    date    ,
	id_type_licenciement integer DEFAULT nextval('licenciement_id_type_licenciement_seq'::regclass) NOT NULL  ,
	droit_preavis        numeric(10,2)    ,
	etat                 integer    ,
	CONSTRAINT licenciement_pkey PRIMARY KEY ( id_licenciement ),
	CONSTRAINT licenciement_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".personnel( id_personnel )   ,
	CONSTRAINT licenciement_id_type_licenciement_fkey FOREIGN KEY ( id_type_licenciement ) REFERENCES "public".type_licenciement( id_type_licenciement )   
 );

CREATE  TABLE "public".quiz ( 
	id_quiz              integer DEFAULT nextval('quiz_id_quiz_seq'::regclass) NOT NULL  ,
	id_service           integer DEFAULT nextval('quiz_id_service_seq'::regclass) NOT NULL  ,
	quiz_name            varchar(50)    ,
	id_quiz_type         integer DEFAULT nextval('quiz_id_quiz_type_seq'::regclass) NOT NULL  ,
	status               integer    ,
	CONSTRAINT quiz_pkey PRIMARY KEY ( id_quiz ),
	CONSTRAINT quiz_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service )   ,
	CONSTRAINT quiz_id_quiz_type_fkey FOREIGN KEY ( id_quiz_type ) REFERENCES "public".quiz_type( id_quiz_type )   
 );

CREATE  TABLE "public".task ( 
	id_task              integer DEFAULT nextval('task_id_task_seq'::regclass) NOT NULL  ,
	id_besoin            integer    ,
	task                 varchar(150)    ,
	status               numeric    ,
	CONSTRAINT task_pkey PRIMARY KEY ( id_task ),
	CONSTRAINT task_id_besoin_fkey FOREIGN KEY ( id_besoin ) REFERENCES "public".besoin( id_besoin )   
 );

CREATE  TABLE "public".wanted_profile ( 
	id_wanted_profile    integer DEFAULT nextval('wanted_profile_id_wanted_profile_seq'::regclass) NOT NULL  ,
	poste                varchar(50)    ,
	id_service           integer    ,
	status               numeric    ,
	id_quiz              integer    ,
	CONSTRAINT wanted_profile_pkey PRIMARY KEY ( id_wanted_profile ),
	CONSTRAINT wanted_profile_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service )   ,
	CONSTRAINT quiz_fkey FOREIGN KEY ( id_quiz ) REFERENCES "public".quiz( id_quiz )   
 );

CREATE  TABLE "public".workload ( 
	id_workload          integer DEFAULT nextval('workload_id_workload_seq'::regclass) NOT NULL  ,
	id_besoin            integer    ,
	id_wanted_profile    integer    ,
	quantity             numeric    ,
	id_unity             integer    ,
	CONSTRAINT workload_pkey PRIMARY KEY ( id_workload ),
	CONSTRAINT workload_id_besoin_fkey FOREIGN KEY ( id_besoin ) REFERENCES "public".besoin( id_besoin )   ,
	CONSTRAINT workload_id_unity_fkey FOREIGN KEY ( id_unity ) REFERENCES "public".unity( id_unity )   ,
	CONSTRAINT workload_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".adresse_note ( 
	id_wanted_profile    integer    ,
	id_adresse           integer    ,
	note                 numeric    ,
	CONSTRAINT adresse_note_id_adresse_fkey FOREIGN KEY ( id_adresse ) REFERENCES "public".adresse( id_adresse )   ,
	CONSTRAINT adresse_note_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".annonce ( 
	id_annonce           integer DEFAULT nextval('annonce_id_annonce_seq'::regclass) NOT NULL  ,
	id_besoin            integer    ,
	id_service           integer    ,
	nom_annonce          varchar(100)    ,
	date_annonce         date    ,
	status               integer    ,
	CONSTRAINT annonce_pkey PRIMARY KEY ( id_annonce ),
	CONSTRAINT annonce_id_besoin_fkey FOREIGN KEY ( id_besoin ) REFERENCES "public".besoin( id_besoin )   ,
	CONSTRAINT annonce_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service )   
 );

CREATE  TABLE "public".candidature ( 
	id_candidature       integer DEFAULT nextval('candidature_id_candidature_seq'::regclass) NOT NULL  ,
	id_wanted_profile    integer    ,
	deposit_date         date    ,
	name                 varchar(30)    ,
	first_name           varchar(30)    ,
	birth_date           date    ,
	id_adresse           integer    ,
	email                varchar(30)    ,
	id_sexe              integer    ,
	id_experience        integer    ,
	id_diplome           integer    ,
	interest_center      varchar(30)    ,
	salary_expectation   double precision    ,
	self_profile         varchar(300)    ,
	photo                varchar(50)    ,
	dossier              varchar(50)    ,
	note                 double precision    ,
	status               integer    ,
	telephone            varchar(30)    ,
	candidature_picture  varchar(100)    ,
	CONSTRAINT candidature_pkey PRIMARY KEY ( id_candidature ),
	CONSTRAINT candidature_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   ,
	CONSTRAINT candidature_id_adresse_fkey FOREIGN KEY ( id_adresse ) REFERENCES "public".adresse( id_adresse )   ,
	CONSTRAINT candidature_id_sexe_fkey FOREIGN KEY ( id_sexe ) REFERENCES "public".sexe( id_sexe )   ,
	CONSTRAINT candidature_id_experience_fkey FOREIGN KEY ( id_experience ) REFERENCES "public".experience( id_experience )   ,
	CONSTRAINT candidature_id_diplome_fkey FOREIGN KEY ( id_diplome ) REFERENCES "public".diplome( id_diplome )   
 );

CREATE  TABLE "public".candidature_test ( 
	id_candidature_test  integer DEFAULT nextval('candidature_test_id_candidature_test_seq'::regclass) NOT NULL  ,
	id_candidature       integer DEFAULT nextval('candidature_test_id_candidature_seq'::regclass) NOT NULL  ,
	note                 integer    ,
	id_quiz              integer DEFAULT nextval('candidature_test_id_quiz_seq'::regclass) NOT NULL  ,
	quiz_date            timestamp    ,
	status               integer    ,
	CONSTRAINT candidature_test_pkey PRIMARY KEY ( id_candidature_test ),
	CONSTRAINT candidature_test_id_quiz_fkey FOREIGN KEY ( id_quiz ) REFERENCES "public".quiz( id_quiz )   ,
	CONSTRAINT candidature_test_id_candidature_fkey FOREIGN KEY ( id_candidature ) REFERENCES "public".candidature( id_candidature )   
 );

CREATE  TABLE "public".diplome_note ( 
	id_wanted_profile    integer    ,
	id_diplome           integer    ,
	note                 numeric    ,
	CONSTRAINT diplome_note_id_diplome_fkey FOREIGN KEY ( id_diplome ) REFERENCES "public".diplome( id_diplome )   ,
	CONSTRAINT diplome_note_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".experience_note ( 
	id_wanted_profile    integer    ,
	id_experience        integer    ,
	note                 numeric    ,
	CONSTRAINT experience_note_id_experience_fkey FOREIGN KEY ( id_experience ) REFERENCES "public".experience( id_experience )   ,
	CONSTRAINT experience_note_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".formation_base ( 
	id_formation_base    integer DEFAULT nextval('formation_base_id_formation_base_seq'::regclass) NOT NULL  ,
	id_candidature       integer    ,
	"year"               varchar(20)    ,
	diplome              varchar(30)    ,
	school               varchar(50)    ,
	CONSTRAINT formation_base_pkey PRIMARY KEY ( id_formation_base ),
	CONSTRAINT formation_base_id_candidature_fkey FOREIGN KEY ( id_candidature ) REFERENCES "public".candidature( id_candidature )   
 );

CREATE  TABLE "public".professional_career ( 
	id_professional_career integer DEFAULT nextval('professional_career_id_professional_career_seq'::regclass) NOT NULL  ,
	id_candidature       integer    ,
	start_date           date    ,
	end_date             date    ,
	society              varchar(30)    ,
	poste                varchar(30)    ,
	task                 varchar(100)    ,
	CONSTRAINT professional_career_pkey PRIMARY KEY ( id_professional_career ),
	CONSTRAINT professional_career_id_candidature_fkey FOREIGN KEY ( id_candidature ) REFERENCES "public".candidature( id_candidature )   
 );

CREATE  TABLE "public".question ( 
	id_question          integer DEFAULT nextval('question_id_question_seq'::regclass) NOT NULL  ,
	id_quiz              integer DEFAULT nextval('question_id_quiz_seq'::regclass) NOT NULL  ,
	question             varchar(100)    ,
	score                integer    ,
	CONSTRAINT question_pkey PRIMARY KEY ( id_question ),
	CONSTRAINT question_id_quiz_fkey FOREIGN KEY ( id_quiz ) REFERENCES "public".quiz( id_quiz )   
 );

CREATE  TABLE "public".salaire_note ( 
	id_wanted_profile    integer    ,
	id_salaire           integer    ,
	note                 numeric    ,
	CONSTRAINT salaire_note_id_salaire_fkey FOREIGN KEY ( id_salaire ) REFERENCES "public".salaire( id_salaire )   ,
	CONSTRAINT salaire_note_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".sexe_note ( 
	id_wanted_profile    integer    ,
	id_sexe              integer    ,
	note                 numeric    ,
	CONSTRAINT sexe_note_id_sexe_fkey FOREIGN KEY ( id_sexe ) REFERENCES "public".sexe( id_sexe )   ,
	CONSTRAINT sexe_note_id_wanted_profile_fkey FOREIGN KEY ( id_wanted_profile ) REFERENCES "public".wanted_profile( id_wanted_profile )   
 );

CREATE  TABLE "public".answer ( 
	id_answer            integer DEFAULT nextval('answer_id_answer_seq'::regclass) NOT NULL  ,
	id_question          integer DEFAULT nextval('answer_id_question_seq'::regclass) NOT NULL  ,
	answer               varchar(100)    ,
	"value"              integer    ,
	CONSTRAINT answer_pkey PRIMARY KEY ( id_answer ),
	CONSTRAINT answer_id_question_fkey FOREIGN KEY ( id_question ) REFERENCES "public".question( id_question )   
 );

CREATE  TABLE "public".abscence ( 
	id_abscence          integer DEFAULT nextval('abscence_id_abscence_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('abscence_id_employe_seq'::regclass) NOT NULL  ,
	"date"               date    ,
	heure_debut          time    ,
	heure_fin            time    ,
	etat                 integer    ,
	CONSTRAINT abscence_pkey PRIMARY KEY ( id_abscence )
 );

CREATE  TABLE "public".avance ( 
	id_avance            integer DEFAULT nextval('avance_id_avance_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('avance_id_employe_seq'::regclass) NOT NULL  ,
	"date"               date    ,
	montant              numeric(10,2)    ,
	etat                 integer    ,
	CONSTRAINT avance_pkey PRIMARY KEY ( id_avance )
 );

CREATE  TABLE "public".conge ( 
	id_conge             integer DEFAULT nextval('conge_id_conge_seq'::regclass) NOT NULL  ,
	id_personnel         integer DEFAULT nextval('conge_id_personnel_seq'::regclass) NOT NULL  ,
	id_type_conge        integer DEFAULT nextval('conge_id_type_conge_seq'::regclass) NOT NULL  ,
	explication          varchar(200)    ,
	date_debut_demande   date    ,
	date_fin_demande     date    ,
	date_debut_reel      date    ,
	date_fin_reel        date    ,
	id_chef_hierarchique integer    ,
	remarque_chef_hierarchique varchar(200)    ,
	id_personnel_rh      integer    ,
	remarque_personnel_rh varchar(200)    ,
	etat                 integer    ,
	deposit_date         date DEFAULT now()   ,
	CONSTRAINT conge_pkey PRIMARY KEY ( id_conge )
 );

CREATE  TABLE "public".conge_report ( 
	id_personnel         integer DEFAULT nextval('conge_report_id_personnel_seq'::regclass) NOT NULL  ,
	annee                integer    ,
	reste_conge          integer    
 );

CREATE  TABLE "public".contrat ( 
	id_contrat           integer DEFAULT nextval('contrat_id_contrat_seq'::regclass) NOT NULL  ,
	id_work_location     integer    ,
	id_candidature       integer    ,
	id_province          integer    ,
	salary               double precision    ,
	periode_essai        integer    ,
	start_date           date    ,
	end_date             date    ,
	contrat_date         date    ,
	is_cnaps             integer    ,
	is_sanitaire         integer    ,
	status               integer    ,
	poste                integer    ,
	"path"               varchar(100)    ,
	id_superieur         integer    ,
	CONSTRAINT contrat_pkey PRIMARY KEY ( id_contrat )
 );

CREATE  TABLE "public".employe ( 
	id_employe           integer DEFAULT nextval('employe_id_employe_seq'::regclass) NOT NULL  ,
	id_contrat           integer    ,
	num_matricule        varchar(10)    ,
	date_embauche        date    ,
	status               integer    ,
	id_classe_employe    integer    ,
	CONSTRAINT employe_pkey PRIMARY KEY ( id_employe )
 );

CREATE  TABLE "public".heure_supplementaire ( 
	id_heure_supplementaire integer DEFAULT nextval('heure_supplementaire_id_heure_supplementaire_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('heure_supplementaire_id_employe_seq'::regclass) NOT NULL  ,
	debut                timestamp    ,
	fin                  timestamp    ,
	etat                 integer    ,
	CONSTRAINT heure_supplementaire_pkey PRIMARY KEY ( id_heure_supplementaire )
 );

CREATE  TABLE "public".prime_employe ( 
	id_prime_employe     integer DEFAULT nextval('prime_employe_id_prime_employe_seq'::regclass) NOT NULL  ,
	id_prime             integer DEFAULT nextval('prime_employe_id_prime_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('prime_employe_id_employe_seq'::regclass) NOT NULL  ,
	date_prime           date    ,
	montant              numeric(10,2)    ,
	etat                 integer DEFAULT 1   ,
	CONSTRAINT prime_employe_pkey PRIMARY KEY ( id_prime_employe )
 );

CREATE  TABLE "public".utilisateur ( 
	id_utilisateur       integer DEFAULT nextval('utilisateur_id_utilisateur_seq'::regclass) NOT NULL  ,
	id_service           integer    ,
	username             varchar(20)    ,
	"password"           varchar(20)    ,
	mail                 varchar(30)    ,
	status               numeric    ,
	"admin"              boolean    ,
	id_employe           integer    ,
	CONSTRAINT utilisateur_pkey PRIMARY KEY ( id_utilisateur )
 );

CREATE  TABLE "public".vente_conge ( 
	id_vente_conge       integer DEFAULT nextval('vente_conge_id_vente_conge_seq'::regclass) NOT NULL  ,
	id_employe           integer DEFAULT nextval('vente_conge_id_employe_seq'::regclass) NOT NULL  ,
	debut                date    ,
	fin                  date    ,
	montant              numeric(10,2)    ,
	etat                 integer    ,
	CONSTRAINT vente_conge_pkey PRIMARY KEY ( id_vente_conge )
 );

CREATE OR REPLACE VIEW "public".contrat_employe AS SELECT c.id_contrat,     c.id_work_location,     c.id_candidature,     c.id_province,     c.salary,     c.periode_essai,     c.start_date,     c.end_date,     c.contrat_date,     c.is_cnaps,     c.is_sanitaire,     c.status,     c.poste,     c.path,     c.id_superieur,     emp.id_employe    FROM (contrat c      JOIN employe emp ON ((c.id_contrat = emp.id_contrat)));

CREATE OR REPLACE VIEW "public".v_adresse_note AS SELECT dn.id_wanted_profile,     dn.id_adresse,     dn.note,     d.adresse,     d.status    FROM (adresse_note dn      JOIN adresse d ON ((d.id_adresse = dn.id_adresse)))   WHERE (d.status = (1)::numeric);

CREATE OR REPLACE VIEW "public".v_candidature_test_detail AS SELECT ct.id_candidature_test,     ct.note,     ct.id_quiz,     ct.quiz_date,     ct.status,     c.id_candidature,     c.id_wanted_profile    FROM (candidature_test ct      JOIN candidature c ON ((ct.id_candidature = c.id_candidature)));

CREATE OR REPLACE VIEW "public".v_conge_with_type AS SELECT c.id_conge,     c.id_personnel,     c.id_type_conge,     c.explication,     c.date_debut_demande,     c.date_fin_demande,     c.date_debut_reel,     c.date_fin_reel,     c.id_chef_hierarchique,     c.remarque_chef_hierarchique,     c.id_personnel_rh,     c.remarque_personnel_rh,     c.etat,     c.deposit_date,     tc.type_conge_name,     tc.is_cumulable,     tc.sexe_valability,     tc.duree,     con.id_superieur    FROM (((conge c      JOIN type_conge tc ON ((c.id_type_conge = tc.id_type_conge)))      JOIN employe e ON ((c.id_personnel = e.id_employe)))      JOIN contrat con ON ((e.id_contrat = con.id_contrat)));

CREATE OR REPLACE VIEW "public".v_diplome_note AS SELECT dn.id_wanted_profile,     dn.id_diplome,     dn.note,     d.diplome,     d.status    FROM (diplome_note dn      JOIN diplome d ON ((d.id_diplome = dn.id_diplome)))   WHERE (d.status = (1)::numeric);

CREATE OR REPLACE VIEW "public".v_diplome_wanted_profile AS SELECT dn.id_wanted_profile,     dn.id_diplome,     wp.id_service,     d.diplome,     dn.note,     wp.poste,     d.status AS status_diplome,     wp.status AS status_wanted_profile    FROM ((diplome_note dn      JOIN diplome d ON ((dn.id_diplome = d.id_diplome)))      JOIN wanted_profile wp ON ((wp.id_wanted_profile = dn.id_wanted_profile)));

CREATE OR REPLACE VIEW "public".v_experience_note AS SELECT dn.id_wanted_profile,     dn.id_experience,     dn.note,     d.experience,     d.status    FROM (experience_note dn      JOIN experience d ON ((d.id_experience = dn.id_experience)))   WHERE (d.status = (1)::numeric);

CREATE OR REPLACE VIEW "public".v_experience_wanted_profile AS SELECT en.id_wanted_profile,     en.id_experience,     wp.id_service,     e.experience,     en.note,     wp.poste,     e.status AS status_experience,     wp.status AS status_wanted_profile    FROM ((experience_note en      JOIN experience e ON ((en.id_experience = e.id_experience)))      JOIN wanted_profile wp ON ((en.id_wanted_profile = wp.id_wanted_profile)));


CREATE OR REPLACE VIEW "public".v_organisme_parameter AS SELECT o.id_organisme,     o.nom_organisme,     o.description,     o.etat,     op.pourcentage,     op.plafond    FROM (organisme o      JOIN organisme_parameter op ON ((o.id_organisme = op.id_organisme)));

CREATE OR REPLACE VIEW "public".v_quiz_full_info AS SELECT q.id_quiz,     q.id_service,     q.quiz_name,     q.id_quiz_type,     q.status,     s.service,     s.fonction,     s.status AS service_status,     qt.quiz_type    FROM ((quiz q      JOIN service s ON ((q.id_service = s.id_service)))      JOIN quiz_type qt ON ((q.id_quiz_type = qt.id_quiz_type)));

CREATE OR REPLACE VIEW "public".v_salaire_note AS SELECT dn.id_wanted_profile,     dn.id_salaire,     dn.note,     d.salaire,     d.status    FROM (salaire_note dn      JOIN salaire d ON ((d.id_salaire = dn.id_salaire)))   WHERE (d.status = (1)::numeric);

CREATE OR REPLACE VIEW "public".v_sexe_note AS SELECT dn.id_wanted_profile,     dn.id_sexe,     dn.note,     d.sexe,     d.status    FROM (sexe_note dn      JOIN sexe d ON ((d.id_sexe = dn.id_sexe)))   WHERE (d.status = (1)::numeric);

CREATE OR REPLACE VIEW "public".v_liste_profile AS SELECT dn.id_wanted_profile,     dn.id_diplome,     dn.note AS diplome_note,     dn.diplome,     dn.status AS diplome_status,     en.id_experience,     en.note AS experience_note,     en.experience,     en.status AS experience_status,     sn.id_salaire,     sn.note AS salaire_note,     sn.salaire,     sn.status AS salaire_status,     sen.id_sexe,     sen.note AS sexe_note,     sen.sexe,     sen.status AS sexe_status,     an.id_adresse,     an.note AS adresse_note,     an.adresse,     an.status AS adresse_status,     wp.poste,     wp.id_service,     wp.status AS wanted_profile_status,     wp.id_quiz    FROM (((((v_diplome_note dn      JOIN v_experience_note en ON ((dn.id_wanted_profile = en.id_wanted_profile)))      JOIN v_salaire_note sn ON ((sn.id_wanted_profile = dn.id_wanted_profile)))      JOIN v_sexe_note sen ON ((sen.id_wanted_profile = dn.id_wanted_profile)))      JOIN v_adresse_note an ON ((an.id_wanted_profile = dn.id_wanted_profile)))      JOIN wanted_profile wp ON ((wp.id_wanted_profile = dn.id_wanted_profile)));

CREATE OR REPLACE VIEW "public".v_personnal_task AS SELECT td.id_todo,     td.id_service,     td.todo,     td.start_date,     td.deadline,     td.id_states AS todo_states,     td.status AS todo_status,     ut.id_under_task,     ut.id_employe,     ut.under_task,     ut.id_states AS under_task_states,     ut.status AS under_task_status, ut.estimed_time    FROM (todo td      JOIN under_task ut ON ((td.id_todo = ut.id_todo)));

CREATE OR REPLACE VIEW "public".v_employe_task AS SELECT pt.id_todo,     pt.id_service,     pt.todo,     pt.start_date,     pt.deadline,     pt.todo_states,     pt.todo_status,     pt.id_under_task,     pt.id_employe,     pt.under_task, pt.estimed_time ,     pt.under_task_states,     pt.under_task_status,     us.id_utilisateur,     us.username,     us.password,     us.mail,     us.status,     us.admin,     us.service,     us.fonction,     us.creation_date    FROM (v_personnal_task pt      JOIN v_user_service us ON ((pt.id_employe = us.id_employe)));

CREATE OR REPLACE VIEW "public".v_user_service AS SELECT u.id_utilisateur,     u.id_service,     u.username,     u.password,     u.mail,     u.status,     u.admin,     u.id_employe,     s.service,     s.fonction,     s.creation_date    FROM (utilisateur u      JOIN service s ON ((u.id_service = s.id_service)));

INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 1, 'Andoharanofotsy', 1);
INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 2, 'Tanjombato', 1);
INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 3, 'Anosy', 1);
INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 4, 'Itaosy', 1);
INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 5, 'Analakely', 1);
INSERT INTO "public".adresse( id_adresse, adresse, status ) VALUES ( 6, 'Alasora', 1);
INSERT INTO "public".classe_employe( id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis ) VALUES ( 1, 'D', 'Emplois salaries d''execution peu qualifies', 100000, 30);
INSERT INTO "public".classe_employe( id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis ) VALUES ( 2, 'C', 'Emplois salaries d''execution qualifies', 200000, 45);
INSERT INTO "public".classe_employe( id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis ) VALUES ( 3, 'B', 'Emplois salaries de niveau intermediaire', 300000, 60);
INSERT INTO "public".classe_employe( id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis ) VALUES ( 4, 'A', 'Emplois salaries de niveau sup‚rieur', 500000, 90);
INSERT INTO "public".classe_employe( id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis ) VALUES ( 5, 'HC', 'Emplois Hors Classe', 500000, 120);
INSERT INTO "public".conge_parameter( accumulation, minimum_duree, max_solde ) VALUES ( 2.5, 365, 90);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 1, 'CEPE', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 2, 'BEPC', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 3, 'BACC', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 4, 'License en management', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 5, 'Master en management', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 6, 'License en droit', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 7, 'Master en droit', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 8, 'License en informatique', 1);
INSERT INTO "public".diplome( id_diplome, diplome, status ) VALUES ( 9, 'Master en informatique', 1);
INSERT INTO "public".experience( id_experience, experience, status ) VALUES ( 1, '1 an d`experience', 1);
INSERT INTO "public".experience( id_experience, experience, status ) VALUES ( 2, '3 ans d`experiences', 1);
INSERT INTO "public".experience( id_experience, experience, status ) VALUES ( 3, '5 ans d`experiences', 1);
INSERT INTO "public".heure_sup_duration_parameter( heure_debut, heure_fin, pourcentage ) VALUES ( 0, 8, 30);
INSERT INTO "public".heure_sup_duration_parameter( heure_debut, heure_fin, pourcentage ) VALUES ( 9, 16, 50);
INSERT INTO "public".irsa_parameter( debut, fin, pourcentage ) VALUES ( 0, 350000, 0);
INSERT INTO "public".irsa_parameter( debut, fin, pourcentage ) VALUES ( 350001, 400000, 5);
INSERT INTO "public".irsa_parameter( debut, fin, pourcentage ) VALUES ( 400001, 500000, 10);
INSERT INTO "public".irsa_parameter( debut, fin, pourcentage ) VALUES ( 500001, 600000, 15);
INSERT INTO "public".irsa_parameter( debut, fin, pourcentage ) VALUES ( 600001, null, 20);
INSERT INTO "public".night_work_parameter( debut_heure, fin_heure, pourcentage ) VALUES ( '22:00:00', '23:59:00', 30);
INSERT INTO "public".night_work_parameter( debut_heure, fin_heure, pourcentage ) VALUES ( '00:00:00', '05:00:00', 30);
INSERT INTO "public".organisme( id_organisme, nom_organisme, description, etat ) VALUES ( 1, 'CNAPS', 'Caisse NAtional de Prevoyance Sociale', 1);
INSERT INTO "public".organisme( id_organisme, nom_organisme, description, etat ) VALUES ( 2, 'OSTIE', 'Organisation Sante Entreprise', 1);
INSERT INTO "public".organisme_parameter( id_organisme, pourcentage, plafond ) VALUES ( 1, 1, 8);
INSERT INTO "public".organisme_parameter( id_organisme, pourcentage, plafond ) VALUES ( 2, 1, 4);
INSERT INTO "public".out_work_parameter( "day", "month", pourcentage ) VALUES ( 1, 1, 100);
INSERT INTO "public".out_work_parameter( "day", "month", pourcentage ) VALUES ( 26, 6, 100);
INSERT INTO "public".personnel( id_personnel, nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste, salaire, id_classe_employe ) VALUES ( 12, 'Smith', 'John', '1990-01-15', '1234567890', null, '2022-02-15', 'Developpeur', 1000000, 1);
INSERT INTO "public".personnel( id_personnel, nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste, salaire, id_classe_employe ) VALUES ( 13, 'Doe', 'Jane', '1985-03-30', '9876543210', null, '2021-11-30', 'Concepteur', 1200000, 2);
INSERT INTO "public".personnel( id_personnel, nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste, salaire, id_classe_employe ) VALUES ( 16, 'Johnson', 'Michael', '1995-05-20', '5555555555', 12, '2023-04-10', 'Designer', 1500000, 3);
INSERT INTO "public".personnel( id_personnel, nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste, salaire, id_classe_employe ) VALUES ( 17, 'Williams', 'Sarah', '1998-07-10', '6666666666', 13, '2022-08-05', 'Designer', 1800000, 4);
INSERT INTO "public".prime( id_prime, prime_name, etat ) VALUES ( 1, 'Prime d''anciennete', 1);
INSERT INTO "public".prime( id_prime, prime_name, etat ) VALUES ( 2, 'Prime de rendement', 1);
INSERT INTO "public".prime( id_prime, prime_name, etat ) VALUES ( 3, 'Prime divers', 1);
INSERT INTO "public".province( id_province, name ) VALUES ( 1, 'ANTANANARIVO');
INSERT INTO "public".province( id_province, name ) VALUES ( 2, 'DIEGO');
INSERT INTO "public".province( id_province, name ) VALUES ( 3, 'TOAMASINA');
INSERT INTO "public".province( id_province, name ) VALUES ( 4, 'MAHAJANGA');
INSERT INTO "public".province( id_province, name ) VALUES ( 5, 'TOLIARA');
INSERT INTO "public".province( id_province, name ) VALUES ( 6, 'FIANARANTSOA');
INSERT INTO "public".quiz_type( id_quiz_type, quiz_type ) VALUES ( 1, 'Question');
INSERT INTO "public".quiz_type( id_quiz_type, quiz_type ) VALUES ( 2, 'Reponse');
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 1, 200000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 2, 300000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 3, 500000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 4, 700000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 5, 1000000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 6, 1500000.0, 1);
INSERT INTO "public".salaire( id_salaire, salaire, status ) VALUES ( 7, 2000000.0, 1);
INSERT INTO "public".service( id_service, service, fonction, creation_date, status ) VALUES ( 1, 'Administration', 'Responsable de l''administration du societe', '2022-01-01', 1);
INSERT INTO "public".service( id_service, service, fonction, creation_date, status ) VALUES ( 2, 'Ressources humaines', 'Responsable des ressources humaines', '2022-01-01', 1);
INSERT INTO "public".service( id_service, service, fonction, creation_date, status ) VALUES ( 3, 'Production', 'Responsable des productions du societe', '2022-01-01', 1);
INSERT INTO "public".sexe( id_sexe, sexe, status ) VALUES ( 1, 'Homme', 1);
INSERT INTO "public".sexe( id_sexe, sexe, status ) VALUES ( 2, 'Femme', 1);
INSERT INTO "public".smig_parameter( smig_value ) VALUES ( 250000);
INSERT INTO "public".society( name, adresse, nif, contact, secteur, legal_statut ) VALUES ( 'AUXIMAD', 'Antananarivo', '0012-8221', '+261346595861', 'Informatique', 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 1, 'Conge de maternite', false, 0, 90, 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 2, 'Conge de paternite', false, 1, 3, 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 3, 'Repos medicale', false, 2, 0, 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 4, 'Vacance', true, 2, 0, 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 5, 'Evenement familiale', true, 2, 0, 1);
INSERT INTO "public".type_conge( id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat ) VALUES ( 6, 'Vente de conge', true, 2, 0, 0);
INSERT INTO "public".type_licenciement( id_type_licenciement, type_licenciement ) VALUES ( 1, 'Demande employe');
INSERT INTO "public".type_licenciement( id_type_licenciement, type_licenciement ) VALUES ( 2, 'Ordre employeur');
INSERT INTO "public".unity( id_unity, unity, status ) VALUES ( 1, 'H/J', 1);
INSERT INTO "public".week_end_parameter( "day", pourcentage ) VALUES ( 7, 40);
INSERT INTO "public".work_location( id_work_location, name, id_adress ) VALUES ( 1, 'Siege Huile de bongolava', 1);
INSERT INTO "public".work_location( id_work_location, name, id_adress ) VALUES ( 2, 'Point de vente Analakely', 5);
INSERT INTO "public".licenciement( id_licenciement, id_employe, date_preavis, date_licenciement, id_type_licenciement, droit_preavis, etat ) VALUES ( 1, 12, '2023-10-27', '2023-10-27', 2, 1000000, null);
INSERT INTO "public".licenciement( id_licenciement, id_employe, date_preavis, date_licenciement, id_type_licenciement, droit_preavis, etat ) VALUES ( 2, 12, '2023-10-27', '2023-11-26', 2, 0, 1);
INSERT INTO "public".wanted_profile( id_wanted_profile, poste, id_service, status, id_quiz ) VALUES ( 1, 'Directeur generale', 1, 1, null);
INSERT INTO "public".wanted_profile( id_wanted_profile, poste, id_service, status, id_quiz ) VALUES ( 3, 'Directeur de production', 3, 1, null);
INSERT INTO "public".wanted_profile( id_wanted_profile, poste, id_service, status, id_quiz ) VALUES ( 4, 'Ouvrier producteur', 3, 1, null);
INSERT INTO "public".wanted_profile( id_wanted_profile, poste, id_service, status, id_quiz ) VALUES ( 5, 'Secretaire generale', 1, 1, null);
INSERT INTO "public".wanted_profile( id_wanted_profile, poste, id_service, status, id_quiz ) VALUES ( 2, 'Ressouces humaines', 2, 1, null);
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 1, 1, '2022-01-01', 'INSSA', 'Chalman', '2000-06-12', 2, 'inssa.chalman@gmail.com', 1, 2, 7, 'BasketBall - Musique - Loisir', 2000000.0, 'Je suis motiv‚e pour diriger cette entreprise. Ca fait partie de mon reve d''etre sur la tete d''une entreprise', 'chalman.png', 'chalman.zip', 17.0, 6, '034 50 986 12', 'chalman_candidature.png');
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 2, 2, '2022-01-01', 'MAMIARILAZA', 'To', '2004-07-07', 1, 'mamiarilaza.to@gmail.com', 1, 3, 5, 'FootBall - Lecture - Musique', 1000000.0, 'Depuis 5 ans je suis le responsable ressource humaine chez GROOVE Industrie, et j''aimerai porter cette experience a vous', 'to.png', 'to.zip', 18.0, 6, '034 14 517 93', 'to_candidature.png');
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 3, 3, '2022-01-01', 'MICHAEL', 'Fy', '2005-03-24', 4, 'fy.michael@gmail.com', 1, 3, 9, 'Dance - Cinema - Moto', 1300000.0, 'Je suis directeur de production dans diff‚rent domaine et je pense que je peux m''exercer aussi dans le votre', 'fy.png', 'fy.zip', 16.0, 6, '034 23 420 12', 'fy_candidature.png');
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 4, 4, '2022-01-01', 'RAKOTOARIMANANA', 'Finoana', '1998-08-13', 5, 'finoanaRAKOTO@gmail.com', 1, 3, 3, 'Musculation - Sport - Musique', 800000.0, 'Depuis ma carriŠre la gestion des machines de production me va bien', 'finoana.png', 'finoana.zip', 15.0, 6, '034 32 125 63', 'finoana_candidature.png');
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 5, 4, '2022-01-01', 'RATSIVAHINY', 'Solo', '1994-10-10', 6, 'finoanaRAKOTO@gmail.com', 1, 3, 3, 'Ecriture - Poete - Analyse', 850000.0, 'La production me passione, mon objectif n''est pas vraiment l''argent mais produire', 'solo.png', 'solo.zip', 16.0, 6, '033 25 312 28', 'solo_candidature.png');
INSERT INTO "public".candidature( id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture ) VALUES ( 6, 5, '2023-01-01', 'RIANTSOA', 'Mialy', '2000-04-08', 3, 'mialy.RIANTSOA@gmail.com', 2, 3, 5, 'Chanter - Management', 1000000.0, 'Je suis sur que si c''est mois qui prends vos notes, aucun evenement se perd', 'mialy.png', 'mialy.zip', 19.0, 6, '034 12 453 65', 'mialy_candidature.png');
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 1, 2022, 0);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 1, 2023, 30);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 2, 2022, 0);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 2, 2023, 30);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 3, 2022, 0);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 3, 2023, 30);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 4, 2022, 0);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 4, 2023, 30);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 5, 2022, 0);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 5, 2023, 30);
INSERT INTO "public".conge_report( id_personnel, annee, reste_conge ) VALUES ( 6, 2023, 0);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 1, 1, 1, 1, 2000000.0, 0, '2022-01-01', null, '2022-01-01', 1, 1, 3, 1, 'chalman_contrat', null);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 2, 1, 2, 1, 1000000.0, 0, '2022-01-01', null, '2022-01-01', 1, 1, 3, 2, 'to_contrat', 1);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 3, 1, 3, 1, 1200000.0, 0, '2022-01-01', null, '2022-01-01', 1, 1, 3, 3, 'fy_contrat', 1);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 6, 2, 6, 1, 1000000.0, 0, '2023-01-01', null, '2023-01-01', 1, 1, 3, 5, 'mialy_contrat', 1);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 4, 1, 4, 1, 800000.0, 0, '2022-01-01', null, '2022-01-01', 1, 1, 3, 4, 'finoana_contrat', 3);
INSERT INTO "public".contrat( id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, "path", id_superieur ) VALUES ( 5, 1, 5, 1, 800000.0, 0, '2022-01-01', null, '2022-01-01', 1, 1, 3, 4, 'solo_contrat', 3);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 1, 1, 'EMP00122', '2022-01-01', 1, 5);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 2, 2, 'EMP00222', '2022-01-01', 1, 4);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 3, 3, 'EMP00322', '2022-01-01', 1, 4);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 4, 4, 'EMP00422', '2022-01-01', 1, 2);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 5, 5, 'EMP00522', '2022-01-01', 1, 2);
INSERT INTO "public".employe( id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe ) VALUES ( 6, 6, 'EMP00622', '2023-01-01', 1, 3);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 1, 1, 'INSSA Chalman', 'chalman', 'inssa.chalman@gmail.com', 1, true, 1);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 2, 2, 'To MAMIARILAZA', 'to', 'mamiarilaza.to@gmail.com', 1, true, 2);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 3, 3, 'Fy Michael', 'fy', 'fy.michael@gmail.com', 1, true, 3);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 4, 3, 'Finoana RAKOTO', 'finoana', 'finoanaRAKOTO@gmail.com', 1, false, 4);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 5, 3, 'Solo RATSIVAHINY', 'solo', 'soloRATSIVAHINY@gmail.com', 1, false, 5);
INSERT INTO "public".utilisateur( id_utilisateur, id_service, username, "password", mail, status, "admin", id_employe ) VALUES ( 6, 1, 'Mialy RIANTSOA', 'mialy', 'mialy.RIANTSOA@gmail.com', 1, false, 5);

ALTER TABLE "public".abscence ADD CONSTRAINT abscence_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".avance ADD CONSTRAINT avance_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".conge ADD CONSTRAINT conge_id_type_conge_fkey FOREIGN KEY ( id_type_conge ) REFERENCES "public".type_conge( id_type_conge );

ALTER TABLE "public".conge ADD CONSTRAINT conge_id_personnel_fkey FOREIGN KEY ( id_personnel ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".conge ADD CONSTRAINT conge_id_personnel_rh_fkey FOREIGN KEY ( id_personnel_rh ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".conge ADD CONSTRAINT conge_id_chef_hierarchique_fkey FOREIGN KEY ( id_chef_hierarchique ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".conge_report ADD CONSTRAINT conge_report_id_personnel_fkey FOREIGN KEY ( id_personnel ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".contrat ADD CONSTRAINT contrat_id_work_location_fkey FOREIGN KEY ( id_work_location ) REFERENCES "public".work_location( id_work_location );

ALTER TABLE "public".contrat ADD CONSTRAINT contrat_id_candidature_fkey FOREIGN KEY ( id_candidature ) REFERENCES "public".candidature( id_candidature );

ALTER TABLE "public".contrat ADD CONSTRAINT contrat_id_province_fkey FOREIGN KEY ( id_province ) REFERENCES "public".province( id_province );

ALTER TABLE "public".contrat ADD CONSTRAINT contrat_poste_fkey FOREIGN KEY ( poste ) REFERENCES "public".wanted_profile( id_wanted_profile );

ALTER TABLE "public".contrat ADD CONSTRAINT contrat_id_superieur_fkey FOREIGN KEY ( id_superieur ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".employe ADD CONSTRAINT employe_id_contrat_fkey FOREIGN KEY ( id_contrat ) REFERENCES "public".contrat( id_contrat );

ALTER TABLE "public".employe ADD CONSTRAINT employe_id_classe_employe_fkey FOREIGN KEY ( id_classe_employe ) REFERENCES "public".classe_employe( id_classe_employe );

ALTER TABLE "public".heure_supplementaire ADD CONSTRAINT heure_supplementaire_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".prime_employe ADD CONSTRAINT prime_employe_id_prime_fkey FOREIGN KEY ( id_prime ) REFERENCES "public".prime( id_prime );

ALTER TABLE "public".prime_employe ADD CONSTRAINT prime_employe_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".utilisateur ADD CONSTRAINT utilisateur_id_service_fkey FOREIGN KEY ( id_service ) REFERENCES "public".service( id_service );

ALTER TABLE "public".utilisateur ADD CONSTRAINT utilisateur_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );

ALTER TABLE "public".vente_conge ADD CONSTRAINT vente_conge_id_employe_fkey FOREIGN KEY ( id_employe ) REFERENCES "public".employe( id_employe );
