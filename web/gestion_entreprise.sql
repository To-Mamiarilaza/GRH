--
-- PostgreSQL database dump
--

-- Dumped from database version 12.13
-- Dumped by pg_dump version 12.13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: abscence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.abscence (
    id_abscence integer NOT NULL,
    id_employe integer NOT NULL,
    date date,
    heure_debut time without time zone,
    heure_fin time without time zone,
    etat integer
);


ALTER TABLE public.abscence OWNER TO postgres;

--
-- Name: abscence_id_abscence_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abscence_id_abscence_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abscence_id_abscence_seq OWNER TO postgres;

--
-- Name: abscence_id_abscence_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abscence_id_abscence_seq OWNED BY public.abscence.id_abscence;


--
-- Name: abscence_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.abscence_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.abscence_id_employe_seq OWNER TO postgres;

--
-- Name: abscence_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.abscence_id_employe_seq OWNED BY public.abscence.id_employe;


--
-- Name: adresse_id_adresse_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.adresse_id_adresse_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.adresse_id_adresse_seq OWNER TO postgres;

--
-- Name: adresse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresse (
    id_adresse integer DEFAULT nextval('public.adresse_id_adresse_seq'::regclass) NOT NULL,
    adresse character varying(50),
    status numeric
);


ALTER TABLE public.adresse OWNER TO postgres;

--
-- Name: adresse_note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adresse_note (
    id_wanted_profile integer,
    id_adresse integer,
    note numeric
);


ALTER TABLE public.adresse_note OWNER TO postgres;

--
-- Name: annonce; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.annonce (
    id_annonce integer NOT NULL,
    id_besoin integer,
    id_service integer,
    nom_annonce character varying(100),
    date_annonce date,
    status integer
);


ALTER TABLE public.annonce OWNER TO postgres;

--
-- Name: annonce_id_annonce_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.annonce_id_annonce_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.annonce_id_annonce_seq OWNER TO postgres;

--
-- Name: annonce_id_annonce_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.annonce_id_annonce_seq OWNED BY public.annonce.id_annonce;


--
-- Name: answer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.answer (
    id_answer integer NOT NULL,
    id_question integer NOT NULL,
    answer character varying(100),
    value integer
);


ALTER TABLE public.answer OWNER TO postgres;

--
-- Name: answer_id_answer_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answer_id_answer_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.answer_id_answer_seq OWNER TO postgres;

--
-- Name: answer_id_answer_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answer_id_answer_seq OWNED BY public.answer.id_answer;


--
-- Name: answer_id_question_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.answer_id_question_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.answer_id_question_seq OWNER TO postgres;

--
-- Name: answer_id_question_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.answer_id_question_seq OWNED BY public.answer.id_question;


--
-- Name: avance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avance (
    id_avance integer NOT NULL,
    id_employe integer NOT NULL,
    date date,
    montant numeric(10,2),
    etat integer
);


ALTER TABLE public.avance OWNER TO postgres;

--
-- Name: avance_id_avance_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.avance_id_avance_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.avance_id_avance_seq OWNER TO postgres;

--
-- Name: avance_id_avance_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.avance_id_avance_seq OWNED BY public.avance.id_avance;


--
-- Name: avance_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.avance_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.avance_id_employe_seq OWNER TO postgres;

--
-- Name: avance_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.avance_id_employe_seq OWNED BY public.avance.id_employe;


--
-- Name: besoin_id_besoin_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.besoin_id_besoin_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.besoin_id_besoin_seq OWNER TO postgres;

--
-- Name: besoin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.besoin (
    id_besoin integer DEFAULT nextval('public.besoin_id_besoin_seq'::regclass) NOT NULL,
    id_service integer,
    creation_date date,
    description character varying(200),
    status numeric
);


ALTER TABLE public.besoin OWNER TO postgres;

--
-- Name: candidature; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.candidature (
    id_candidature integer NOT NULL,
    id_wanted_profile integer,
    deposit_date date,
    name character varying(30),
    first_name character varying(30),
    birth_date date,
    id_adresse integer,
    email character varying(30),
    id_sexe integer,
    id_experience integer,
    id_diplome integer,
    interest_center character varying(30),
    salary_expectation double precision,
    self_profile character varying(300),
    photo character varying(50),
    dossier character varying(50),
    note double precision,
    status integer,
    telephone character varying(30),
    candidature_picture character varying(100)
);


ALTER TABLE public.candidature OWNER TO postgres;

--
-- Name: candidature_id_candidature_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.candidature_id_candidature_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.candidature_id_candidature_seq OWNER TO postgres;

--
-- Name: candidature_id_candidature_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.candidature_id_candidature_seq OWNED BY public.candidature.id_candidature;


--
-- Name: candidature_test; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.candidature_test (
    id_candidature_test integer NOT NULL,
    id_candidature integer NOT NULL,
    note integer,
    id_quiz integer NOT NULL,
    quiz_date timestamp without time zone,
    status integer
);


ALTER TABLE public.candidature_test OWNER TO postgres;

--
-- Name: candidature_test_id_candidature_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.candidature_test_id_candidature_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.candidature_test_id_candidature_seq OWNER TO postgres;

--
-- Name: candidature_test_id_candidature_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.candidature_test_id_candidature_seq OWNED BY public.candidature_test.id_candidature;


--
-- Name: candidature_test_id_candidature_test_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.candidature_test_id_candidature_test_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.candidature_test_id_candidature_test_seq OWNER TO postgres;

--
-- Name: candidature_test_id_candidature_test_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.candidature_test_id_candidature_test_seq OWNED BY public.candidature_test.id_candidature_test;


--
-- Name: candidature_test_id_quiz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.candidature_test_id_quiz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.candidature_test_id_quiz_seq OWNER TO postgres;

--
-- Name: candidature_test_id_quiz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.candidature_test_id_quiz_seq OWNED BY public.candidature_test.id_quiz;


--
-- Name: classe_employe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.classe_employe (
    id_classe_employe integer NOT NULL,
    classe_employe character varying(50),
    classe_description character varying(50),
    indamnite_licenciement numeric(10,2),
    duree_preavis integer
);


ALTER TABLE public.classe_employe OWNER TO postgres;

--
-- Name: classe_employe_id_classe_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.classe_employe_id_classe_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.classe_employe_id_classe_employe_seq OWNER TO postgres;

--
-- Name: classe_employe_id_classe_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.classe_employe_id_classe_employe_seq OWNED BY public.classe_employe.id_classe_employe;


--
-- Name: conge; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conge (
    id_conge integer NOT NULL,
    id_personnel integer NOT NULL,
    id_type_conge integer NOT NULL,
    explication character varying(200),
    date_debut_demande date,
    date_fin_demande date,
    date_debut_reel date,
    date_fin_reel date,
    id_chef_hierarchique integer,
    remarque_chef_hierarchique character varying(200),
    id_personnel_rh integer,
    remarque_personnel_rh character varying(200),
    etat integer,
    deposit_date date DEFAULT now()
);


ALTER TABLE public.conge OWNER TO postgres;

--
-- Name: conge_id_conge_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conge_id_conge_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conge_id_conge_seq OWNER TO postgres;

--
-- Name: conge_id_conge_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conge_id_conge_seq OWNED BY public.conge.id_conge;


--
-- Name: conge_id_personnel_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conge_id_personnel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conge_id_personnel_seq OWNER TO postgres;

--
-- Name: conge_id_personnel_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conge_id_personnel_seq OWNED BY public.conge.id_personnel;


--
-- Name: conge_id_type_conge_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conge_id_type_conge_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conge_id_type_conge_seq OWNER TO postgres;

--
-- Name: conge_id_type_conge_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conge_id_type_conge_seq OWNED BY public.conge.id_type_conge;


--
-- Name: conge_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conge_parameter (
    accumulation numeric(3,1),
    minimum_duree integer,
    max_solde integer
);


ALTER TABLE public.conge_parameter OWNER TO postgres;

--
-- Name: conge_report; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conge_report (
    id_personnel integer NOT NULL,
    annee integer,
    reste_conge integer
);


ALTER TABLE public.conge_report OWNER TO postgres;

--
-- Name: conge_report_id_personnel_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conge_report_id_personnel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conge_report_id_personnel_seq OWNER TO postgres;

--
-- Name: conge_report_id_personnel_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conge_report_id_personnel_seq OWNED BY public.conge_report.id_personnel;


--
-- Name: contrat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contrat (
    id_contrat integer NOT NULL,
    id_work_location integer,
    id_candidature integer,
    id_province integer,
    salary double precision,
    periode_essai integer,
    start_date date,
    end_date date,
    contrat_date date,
    is_cnaps integer,
    is_sanitaire integer,
    status integer,
    poste integer,
    path character varying(100),
    id_superieur integer
);


ALTER TABLE public.contrat OWNER TO postgres;

--
-- Name: employe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employe (
    id_employe integer NOT NULL,
    id_contrat integer,
    num_matricule character varying(10),
    date_embauche date,
    status integer,
    id_classe_employe integer
);


ALTER TABLE public.employe OWNER TO postgres;

--
-- Name: contrat_employe; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.contrat_employe AS
 SELECT c.id_contrat,
    c.id_work_location,
    c.id_candidature,
    c.id_province,
    c.salary,
    c.periode_essai,
    c.start_date,
    c.end_date,
    c.contrat_date,
    c.is_cnaps,
    c.is_sanitaire,
    c.status,
    c.poste,
    c.path,
    c.id_superieur,
    emp.id_employe
   FROM (public.contrat c
     JOIN public.employe emp ON ((c.id_contrat = emp.id_contrat)));


ALTER TABLE public.contrat_employe OWNER TO postgres;

--
-- Name: contrat_id_contrat_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contrat_id_contrat_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contrat_id_contrat_seq OWNER TO postgres;

--
-- Name: contrat_id_contrat_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contrat_id_contrat_seq OWNED BY public.contrat.id_contrat;


--
-- Name: departement_rappel_periode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.departement_rappel_periode (
    id_service integer NOT NULL,
    id_rappel_periode integer NOT NULL
);


ALTER TABLE public.departement_rappel_periode OWNER TO postgres;

--
-- Name: diplome_id_diplome_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.diplome_id_diplome_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.diplome_id_diplome_seq OWNER TO postgres;

--
-- Name: diplome; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.diplome (
    id_diplome integer DEFAULT nextval('public.diplome_id_diplome_seq'::regclass) NOT NULL,
    diplome character varying(50),
    status numeric
);


ALTER TABLE public.diplome OWNER TO postgres;

--
-- Name: diplome_note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.diplome_note (
    id_wanted_profile integer,
    id_diplome integer,
    note numeric
);


ALTER TABLE public.diplome_note OWNER TO postgres;

--
-- Name: employe_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employe_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employe_id_employe_seq OWNER TO postgres;

--
-- Name: employe_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employe_id_employe_seq OWNED BY public.employe.id_employe;


--
-- Name: experience_id_experience_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.experience_id_experience_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.experience_id_experience_seq OWNER TO postgres;

--
-- Name: experience; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.experience (
    id_experience integer DEFAULT nextval('public.experience_id_experience_seq'::regclass) NOT NULL,
    experience character varying(50),
    status numeric
);


ALTER TABLE public.experience OWNER TO postgres;

--
-- Name: experience_note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.experience_note (
    id_wanted_profile integer,
    id_experience integer,
    note numeric
);


ALTER TABLE public.experience_note OWNER TO postgres;

--
-- Name: formation_base; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.formation_base (
    id_formation_base integer NOT NULL,
    id_candidature integer,
    year character varying(20),
    diplome character varying(30),
    school character varying(50)
);


ALTER TABLE public.formation_base OWNER TO postgres;

--
-- Name: formation_base_id_formation_base_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.formation_base_id_formation_base_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.formation_base_id_formation_base_seq OWNER TO postgres;

--
-- Name: formation_base_id_formation_base_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.formation_base_id_formation_base_seq OWNED BY public.formation_base.id_formation_base;


--
-- Name: heure_sup_duration_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.heure_sup_duration_parameter (
    heure_debut integer,
    heure_fin integer,
    pourcentage numeric(4,1)
);


ALTER TABLE public.heure_sup_duration_parameter OWNER TO postgres;

--
-- Name: heure_supplementaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.heure_supplementaire (
    id_heure_supplementaire integer NOT NULL,
    id_employe integer NOT NULL,
    debut timestamp without time zone,
    fin timestamp without time zone,
    etat integer
);


ALTER TABLE public.heure_supplementaire OWNER TO postgres;

--
-- Name: heure_supplementaire_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.heure_supplementaire_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.heure_supplementaire_id_employe_seq OWNER TO postgres;

--
-- Name: heure_supplementaire_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.heure_supplementaire_id_employe_seq OWNED BY public.heure_supplementaire.id_employe;


--
-- Name: heure_supplementaire_id_heure_supplementaire_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.heure_supplementaire_id_heure_supplementaire_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.heure_supplementaire_id_heure_supplementaire_seq OWNER TO postgres;

--
-- Name: heure_supplementaire_id_heure_supplementaire_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.heure_supplementaire_id_heure_supplementaire_seq OWNED BY public.heure_supplementaire.id_heure_supplementaire;


--
-- Name: irsa_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.irsa_parameter (
    debut numeric(10,2),
    fin numeric(10,2),
    pourcentage numeric(4,2)
);


ALTER TABLE public.irsa_parameter OWNER TO postgres;

--
-- Name: licenciement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.licenciement (
    id_licenciement integer NOT NULL,
    id_employe integer NOT NULL,
    date_preavis date,
    date_licenciement date,
    id_type_licenciement integer NOT NULL,
    droit_preavis numeric(10,2),
    etat integer
);


ALTER TABLE public.licenciement OWNER TO postgres;

--
-- Name: licenciement_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.licenciement_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.licenciement_id_employe_seq OWNER TO postgres;

--
-- Name: licenciement_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.licenciement_id_employe_seq OWNED BY public.licenciement.id_employe;


--
-- Name: licenciement_id_licenciement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.licenciement_id_licenciement_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.licenciement_id_licenciement_seq OWNER TO postgres;

--
-- Name: licenciement_id_licenciement_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.licenciement_id_licenciement_seq OWNED BY public.licenciement.id_licenciement;


--
-- Name: licenciement_id_type_licenciement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.licenciement_id_type_licenciement_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.licenciement_id_type_licenciement_seq OWNER TO postgres;

--
-- Name: licenciement_id_type_licenciement_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.licenciement_id_type_licenciement_seq OWNED BY public.licenciement.id_type_licenciement;


--
-- Name: night_work_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.night_work_parameter (
    debut_heure time without time zone,
    fin_heure time without time zone,
    pourcentage numeric(4,1)
);


ALTER TABLE public.night_work_parameter OWNER TO postgres;

--
-- Name: organisme; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organisme (
    id_organisme integer NOT NULL,
    nom_organisme character varying(30),
    description character varying(50),
    etat integer DEFAULT 1
);


ALTER TABLE public.organisme OWNER TO postgres;

--
-- Name: organisme_id_organisme_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organisme_id_organisme_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organisme_id_organisme_seq OWNER TO postgres;

--
-- Name: organisme_id_organisme_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organisme_id_organisme_seq OWNED BY public.organisme.id_organisme;


--
-- Name: organisme_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.organisme_parameter (
    id_organisme integer NOT NULL,
    pourcentage numeric(4,2),
    plafond integer
);


ALTER TABLE public.organisme_parameter OWNER TO postgres;

--
-- Name: organisme_parameter_id_organisme_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.organisme_parameter_id_organisme_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organisme_parameter_id_organisme_seq OWNER TO postgres;

--
-- Name: organisme_parameter_id_organisme_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.organisme_parameter_id_organisme_seq OWNED BY public.organisme_parameter.id_organisme;


--
-- Name: out_work_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.out_work_parameter (
    day integer,
    month integer,
    pourcentage numeric(4,1)
);


ALTER TABLE public.out_work_parameter OWNER TO postgres;

--
-- Name: personnel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personnel (
    id_personnel integer NOT NULL,
    nom character varying(50),
    prenom character varying(50),
    date_naissance date,
    telephone character varying(10),
    id_superieur integer,
    date_embauche date,
    poste character varying(30),
    salaire numeric(10,2),
    id_classe_employe integer NOT NULL
);


ALTER TABLE public.personnel OWNER TO postgres;

--
-- Name: personnel_id_classe_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.personnel_id_classe_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personnel_id_classe_employe_seq OWNER TO postgres;

--
-- Name: personnel_id_classe_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.personnel_id_classe_employe_seq OWNED BY public.personnel.id_classe_employe;


--
-- Name: personnel_id_personnel_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.personnel_id_personnel_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personnel_id_personnel_seq OWNER TO postgres;

--
-- Name: personnel_id_personnel_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.personnel_id_personnel_seq OWNED BY public.personnel.id_personnel;


--
-- Name: prime; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prime (
    id_prime integer NOT NULL,
    prime_name character varying(50),
    etat integer DEFAULT 1
);


ALTER TABLE public.prime OWNER TO postgres;

--
-- Name: prime_employe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prime_employe (
    id_prime_employe integer NOT NULL,
    id_prime integer NOT NULL,
    id_employe integer NOT NULL,
    date_prime date,
    montant numeric(10,2),
    etat integer DEFAULT 1
);


ALTER TABLE public.prime_employe OWNER TO postgres;

--
-- Name: prime_employe_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prime_employe_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prime_employe_id_employe_seq OWNER TO postgres;

--
-- Name: prime_employe_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prime_employe_id_employe_seq OWNED BY public.prime_employe.id_employe;


--
-- Name: prime_employe_id_prime_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prime_employe_id_prime_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prime_employe_id_prime_employe_seq OWNER TO postgres;

--
-- Name: prime_employe_id_prime_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prime_employe_id_prime_employe_seq OWNED BY public.prime_employe.id_prime_employe;


--
-- Name: prime_employe_id_prime_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prime_employe_id_prime_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prime_employe_id_prime_seq OWNER TO postgres;

--
-- Name: prime_employe_id_prime_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prime_employe_id_prime_seq OWNED BY public.prime_employe.id_prime;


--
-- Name: prime_id_prime_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.prime_id_prime_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.prime_id_prime_seq OWNER TO postgres;

--
-- Name: prime_id_prime_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.prime_id_prime_seq OWNED BY public.prime.id_prime;


--
-- Name: professional_career; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.professional_career (
    id_professional_career integer NOT NULL,
    id_candidature integer,
    start_date date,
    end_date date,
    society character varying(30),
    poste character varying(30),
    task character varying(100)
);


ALTER TABLE public.professional_career OWNER TO postgres;

--
-- Name: professional_career_id_professional_career_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.professional_career_id_professional_career_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.professional_career_id_professional_career_seq OWNER TO postgres;

--
-- Name: professional_career_id_professional_career_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.professional_career_id_professional_career_seq OWNED BY public.professional_career.id_professional_career;


--
-- Name: province; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.province (
    id_province integer NOT NULL,
    name character varying(50)
);


ALTER TABLE public.province OWNER TO postgres;

--
-- Name: province_id_province_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.province_id_province_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.province_id_province_seq OWNER TO postgres;

--
-- Name: province_id_province_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.province_id_province_seq OWNED BY public.province.id_province;


--
-- Name: question; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.question (
    id_question integer NOT NULL,
    id_quiz integer NOT NULL,
    question character varying(100),
    score integer
);


ALTER TABLE public.question OWNER TO postgres;

--
-- Name: question_id_question_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_id_question_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_id_question_seq OWNER TO postgres;

--
-- Name: question_id_question_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_id_question_seq OWNED BY public.question.id_question;


--
-- Name: question_id_quiz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.question_id_quiz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.question_id_quiz_seq OWNER TO postgres;

--
-- Name: question_id_quiz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.question_id_quiz_seq OWNED BY public.question.id_quiz;


--
-- Name: quiz; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quiz (
    id_quiz integer NOT NULL,
    id_service integer NOT NULL,
    quiz_name character varying(50),
    id_quiz_type integer NOT NULL,
    status integer
);


ALTER TABLE public.quiz OWNER TO postgres;

--
-- Name: quiz_id_quiz_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_id_quiz_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_id_quiz_seq OWNER TO postgres;

--
-- Name: quiz_id_quiz_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_id_quiz_seq OWNED BY public.quiz.id_quiz;


--
-- Name: quiz_id_quiz_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_id_quiz_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_id_quiz_type_seq OWNER TO postgres;

--
-- Name: quiz_id_quiz_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_id_quiz_type_seq OWNED BY public.quiz.id_quiz_type;


--
-- Name: quiz_id_service_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_id_service_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_id_service_seq OWNER TO postgres;

--
-- Name: quiz_id_service_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_id_service_seq OWNED BY public.quiz.id_service;


--
-- Name: quiz_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.quiz_type (
    id_quiz_type integer NOT NULL,
    quiz_type character varying(40)
);


ALTER TABLE public.quiz_type OWNER TO postgres;

--
-- Name: quiz_type_id_quiz_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.quiz_type_id_quiz_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quiz_type_id_quiz_type_seq OWNER TO postgres;

--
-- Name: quiz_type_id_quiz_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.quiz_type_id_quiz_type_seq OWNED BY public.quiz_type.id_quiz_type;


--
-- Name: rappel_periode; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rappel_periode (
    id_rappel_periode integer NOT NULL,
    date date,
    modification_salaire numeric(4,1),
    nombre_mois integer,
    etat integer
);


ALTER TABLE public.rappel_periode OWNER TO postgres;

--
-- Name: rappel_periode_id_rappel_periode_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rappel_periode_id_rappel_periode_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rappel_periode_id_rappel_periode_seq OWNER TO postgres;

--
-- Name: rappel_periode_id_rappel_periode_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rappel_periode_id_rappel_periode_seq OWNED BY public.rappel_periode.id_rappel_periode;


--
-- Name: salaire_id_salaire_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.salaire_id_salaire_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.salaire_id_salaire_seq OWNER TO postgres;

--
-- Name: salaire; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salaire (
    id_salaire integer DEFAULT nextval('public.salaire_id_salaire_seq'::regclass) NOT NULL,
    salaire double precision,
    status numeric
);


ALTER TABLE public.salaire OWNER TO postgres;

--
-- Name: salaire_note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.salaire_note (
    id_wanted_profile integer,
    id_salaire integer,
    note numeric
);


ALTER TABLE public.salaire_note OWNER TO postgres;

--
-- Name: service_id_service_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.service_id_service_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.service_id_service_seq OWNER TO postgres;

--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service (
    id_service integer DEFAULT nextval('public.service_id_service_seq'::regclass) NOT NULL,
    service character varying(50),
    fonction character varying(50),
    creation_date date,
    status numeric
);


ALTER TABLE public.service OWNER TO postgres;

--
-- Name: sexe_id_sexe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sexe_id_sexe_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sexe_id_sexe_seq OWNER TO postgres;

--
-- Name: sexe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexe (
    id_sexe integer DEFAULT nextval('public.sexe_id_sexe_seq'::regclass) NOT NULL,
    sexe character varying(20),
    status numeric
);


ALTER TABLE public.sexe OWNER TO postgres;

--
-- Name: sexe_note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sexe_note (
    id_wanted_profile integer,
    id_sexe integer,
    note numeric
);


ALTER TABLE public.sexe_note OWNER TO postgres;

--
-- Name: smig_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.smig_parameter (
    smig_value numeric(10,2) DEFAULT '250000'::numeric
);


ALTER TABLE public.smig_parameter OWNER TO postgres;

--
-- Name: society; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.society (
    name character varying(50),
    adresse character varying(50),
    nif character varying(50),
    contact character varying(25),
    secteur character varying(20),
    legal_statut numeric
);


ALTER TABLE public.society OWNER TO postgres;

--
-- Name: task_id_task_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.task_id_task_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.task_id_task_seq OWNER TO postgres;

--
-- Name: task; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.task (
    id_task integer DEFAULT nextval('public.task_id_task_seq'::regclass) NOT NULL,
    id_besoin integer,
    task character varying(150),
    status numeric
);


ALTER TABLE public.task OWNER TO postgres;

--
-- Name: type_conge; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_conge (
    id_type_conge integer NOT NULL,
    type_conge_name character varying(50),
    is_cumulable boolean,
    sexe_valability integer,
    duree integer,
    etat integer
);


ALTER TABLE public.type_conge OWNER TO postgres;

--
-- Name: type_conge_id_type_conge_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_conge_id_type_conge_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_conge_id_type_conge_seq OWNER TO postgres;

--
-- Name: type_conge_id_type_conge_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_conge_id_type_conge_seq OWNED BY public.type_conge.id_type_conge;


--
-- Name: type_licenciement; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.type_licenciement (
    id_type_licenciement integer NOT NULL,
    type_licenciement character varying(50)
);


ALTER TABLE public.type_licenciement OWNER TO postgres;

--
-- Name: type_licenciement_id_type_licenciement_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.type_licenciement_id_type_licenciement_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.type_licenciement_id_type_licenciement_seq OWNER TO postgres;

--
-- Name: type_licenciement_id_type_licenciement_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.type_licenciement_id_type_licenciement_seq OWNED BY public.type_licenciement.id_type_licenciement;


--
-- Name: unity_id_unity_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.unity_id_unity_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.unity_id_unity_seq OWNER TO postgres;

--
-- Name: unity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unity (
    id_unity integer DEFAULT nextval('public.unity_id_unity_seq'::regclass) NOT NULL,
    unity character varying(20),
    status numeric
);


ALTER TABLE public.unity OWNER TO postgres;

--
-- Name: utilisateur_id_utilisateur_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.utilisateur_id_utilisateur_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.utilisateur_id_utilisateur_seq OWNER TO postgres;

--
-- Name: utilisateur; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utilisateur (
    id_utilisateur integer DEFAULT nextval('public.utilisateur_id_utilisateur_seq'::regclass) NOT NULL,
    id_service integer,
    username character varying(20),
    password character varying(20),
    mail character varying(30),
    status numeric,
    admin boolean,
    id_employe integer
);


ALTER TABLE public.utilisateur OWNER TO postgres;

--
-- Name: v_adresse_note; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_adresse_note AS
 SELECT dn.id_wanted_profile,
    dn.id_adresse,
    dn.note,
    d.adresse,
    d.status
   FROM (public.adresse_note dn
     JOIN public.adresse d ON ((d.id_adresse = dn.id_adresse)))
  WHERE (d.status = (1)::numeric);


ALTER TABLE public.v_adresse_note OWNER TO postgres;

--
-- Name: v_candidature_test_detail; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_candidature_test_detail AS
 SELECT ct.id_candidature_test,
    ct.note,
    ct.id_quiz,
    ct.quiz_date,
    ct.status,
    c.id_candidature,
    c.id_wanted_profile
   FROM (public.candidature_test ct
     JOIN public.candidature c ON ((ct.id_candidature = c.id_candidature)));


ALTER TABLE public.v_candidature_test_detail OWNER TO postgres;

--
-- Name: v_conge_with_type; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_conge_with_type AS
 SELECT c.id_conge,
    c.id_personnel,
    c.id_type_conge,
    c.explication,
    c.date_debut_demande,
    c.date_fin_demande,
    c.date_debut_reel,
    c.date_fin_reel,
    c.id_chef_hierarchique,
    c.remarque_chef_hierarchique,
    c.id_personnel_rh,
    c.remarque_personnel_rh,
    c.etat,
    c.deposit_date,
    tc.type_conge_name,
    tc.is_cumulable,
    tc.sexe_valability,
    tc.duree,
    con.id_superieur
   FROM (((public.conge c
     JOIN public.type_conge tc ON ((c.id_type_conge = tc.id_type_conge)))
     JOIN public.employe e ON ((c.id_personnel = e.id_employe)))
     JOIN public.contrat con ON ((e.id_contrat = con.id_contrat)));


ALTER TABLE public.v_conge_with_type OWNER TO postgres;

--
-- Name: v_diplome_note; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_diplome_note AS
 SELECT dn.id_wanted_profile,
    dn.id_diplome,
    dn.note,
    d.diplome,
    d.status
   FROM (public.diplome_note dn
     JOIN public.diplome d ON ((d.id_diplome = dn.id_diplome)))
  WHERE (d.status = (1)::numeric);


ALTER TABLE public.v_diplome_note OWNER TO postgres;

--
-- Name: wanted_profile_id_wanted_profile_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wanted_profile_id_wanted_profile_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wanted_profile_id_wanted_profile_seq OWNER TO postgres;

--
-- Name: wanted_profile; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wanted_profile (
    id_wanted_profile integer DEFAULT nextval('public.wanted_profile_id_wanted_profile_seq'::regclass) NOT NULL,
    poste character varying(50),
    id_service integer,
    status numeric,
    id_quiz integer
);


ALTER TABLE public.wanted_profile OWNER TO postgres;

--
-- Name: v_diplome_wanted_profile; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_diplome_wanted_profile AS
 SELECT dn.id_wanted_profile,
    dn.id_diplome,
    wp.id_service,
    d.diplome,
    dn.note,
    wp.poste,
    d.status AS status_diplome,
    wp.status AS status_wanted_profile
   FROM ((public.diplome_note dn
     JOIN public.diplome d ON ((dn.id_diplome = d.id_diplome)))
     JOIN public.wanted_profile wp ON ((wp.id_wanted_profile = dn.id_wanted_profile)));


ALTER TABLE public.v_diplome_wanted_profile OWNER TO postgres;

--
-- Name: v_experience_note; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_experience_note AS
 SELECT dn.id_wanted_profile,
    dn.id_experience,
    dn.note,
    d.experience,
    d.status
   FROM (public.experience_note dn
     JOIN public.experience d ON ((d.id_experience = dn.id_experience)))
  WHERE (d.status = (1)::numeric);


ALTER TABLE public.v_experience_note OWNER TO postgres;

--
-- Name: v_experience_wanted_profile; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_experience_wanted_profile AS
 SELECT en.id_wanted_profile,
    en.id_experience,
    wp.id_service,
    e.experience,
    en.note,
    wp.poste,
    e.status AS status_experience,
    wp.status AS status_wanted_profile
   FROM ((public.experience_note en
     JOIN public.experience e ON ((en.id_experience = e.id_experience)))
     JOIN public.wanted_profile wp ON ((en.id_wanted_profile = wp.id_wanted_profile)));


ALTER TABLE public.v_experience_wanted_profile OWNER TO postgres;

--
-- Name: v_salaire_note; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_salaire_note AS
 SELECT dn.id_wanted_profile,
    dn.id_salaire,
    dn.note,
    d.salaire,
    d.status
   FROM (public.salaire_note dn
     JOIN public.salaire d ON ((d.id_salaire = dn.id_salaire)))
  WHERE (d.status = (1)::numeric);


ALTER TABLE public.v_salaire_note OWNER TO postgres;

--
-- Name: v_sexe_note; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_sexe_note AS
 SELECT dn.id_wanted_profile,
    dn.id_sexe,
    dn.note,
    d.sexe,
    d.status
   FROM (public.sexe_note dn
     JOIN public.sexe d ON ((d.id_sexe = dn.id_sexe)))
  WHERE (d.status = (1)::numeric);


ALTER TABLE public.v_sexe_note OWNER TO postgres;

--
-- Name: v_liste_profile; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_liste_profile AS
 SELECT dn.id_wanted_profile,
    dn.id_diplome,
    dn.note AS diplome_note,
    dn.diplome,
    dn.status AS diplome_status,
    en.id_experience,
    en.note AS experience_note,
    en.experience,
    en.status AS experience_status,
    sn.id_salaire,
    sn.note AS salaire_note,
    sn.salaire,
    sn.status AS salaire_status,
    sen.id_sexe,
    sen.note AS sexe_note,
    sen.sexe,
    sen.status AS sexe_status,
    an.id_adresse,
    an.note AS adresse_note,
    an.adresse,
    an.status AS adresse_status,
    wp.poste,
    wp.id_service,
    wp.status AS wanted_profile_status,
    wp.id_quiz
   FROM (((((public.v_diplome_note dn
     JOIN public.v_experience_note en ON ((dn.id_wanted_profile = en.id_wanted_profile)))
     JOIN public.v_salaire_note sn ON ((sn.id_wanted_profile = dn.id_wanted_profile)))
     JOIN public.v_sexe_note sen ON ((sen.id_wanted_profile = dn.id_wanted_profile)))
     JOIN public.v_adresse_note an ON ((an.id_wanted_profile = dn.id_wanted_profile)))
     JOIN public.wanted_profile wp ON ((wp.id_wanted_profile = dn.id_wanted_profile)));


ALTER TABLE public.v_liste_profile OWNER TO postgres;

--
-- Name: v_organisme_parameter; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_organisme_parameter AS
 SELECT o.id_organisme,
    o.nom_organisme,
    o.description,
    o.etat,
    op.pourcentage,
    op.plafond
   FROM (public.organisme o
     JOIN public.organisme_parameter op ON ((o.id_organisme = op.id_organisme)));


ALTER TABLE public.v_organisme_parameter OWNER TO postgres;

--
-- Name: v_quiz_full_info; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_quiz_full_info AS
 SELECT q.id_quiz,
    q.id_service,
    q.quiz_name,
    q.id_quiz_type,
    q.status,
    s.service,
    s.fonction,
    s.status AS service_status,
    qt.quiz_type
   FROM ((public.quiz q
     JOIN public.service s ON ((q.id_service = s.id_service)))
     JOIN public.quiz_type qt ON ((q.id_quiz_type = qt.id_quiz_type)));


ALTER TABLE public.v_quiz_full_info OWNER TO postgres;

--
-- Name: v_user_service; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.v_user_service AS
 SELECT u.id_utilisateur,
    u.id_service,
    u.username,
    u.password,
    u.mail,
    u.status,
    u.admin,
    u.id_employe,
    s.service,
    s.fonction,
    s.creation_date
   FROM (public.utilisateur u
     JOIN public.service s ON ((u.id_service = s.id_service)));


ALTER TABLE public.v_user_service OWNER TO postgres;

--
-- Name: vente_conge; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vente_conge (
    id_vente_conge integer NOT NULL,
    id_employe integer NOT NULL,
    debut date,
    fin date,
    montant numeric(10,2),
    etat integer
);


ALTER TABLE public.vente_conge OWNER TO postgres;

--
-- Name: vente_conge_id_employe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vente_conge_id_employe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vente_conge_id_employe_seq OWNER TO postgres;

--
-- Name: vente_conge_id_employe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vente_conge_id_employe_seq OWNED BY public.vente_conge.id_employe;


--
-- Name: vente_conge_id_vente_conge_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vente_conge_id_vente_conge_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vente_conge_id_vente_conge_seq OWNER TO postgres;

--
-- Name: vente_conge_id_vente_conge_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vente_conge_id_vente_conge_seq OWNED BY public.vente_conge.id_vente_conge;


--
-- Name: week_end_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.week_end_parameter (
    day integer,
    pourcentage numeric(4,1)
);


ALTER TABLE public.week_end_parameter OWNER TO postgres;

--
-- Name: work_location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.work_location (
    id_work_location integer NOT NULL,
    name character varying(100),
    id_adress integer
);


ALTER TABLE public.work_location OWNER TO postgres;

--
-- Name: work_location_id_work_location_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.work_location_id_work_location_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.work_location_id_work_location_seq OWNER TO postgres;

--
-- Name: work_location_id_work_location_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.work_location_id_work_location_seq OWNED BY public.work_location.id_work_location;


--
-- Name: workload_id_workload_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workload_id_workload_seq
    START WITH 20
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workload_id_workload_seq OWNER TO postgres;

--
-- Name: workload; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workload (
    id_workload integer DEFAULT nextval('public.workload_id_workload_seq'::regclass) NOT NULL,
    id_besoin integer,
    id_wanted_profile integer,
    quantity numeric,
    id_unity integer
);


ALTER TABLE public.workload OWNER TO postgres;

--
-- Name: abscence id_abscence; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abscence ALTER COLUMN id_abscence SET DEFAULT nextval('public.abscence_id_abscence_seq'::regclass);


--
-- Name: abscence id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abscence ALTER COLUMN id_employe SET DEFAULT nextval('public.abscence_id_employe_seq'::regclass);


--
-- Name: annonce id_annonce; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annonce ALTER COLUMN id_annonce SET DEFAULT nextval('public.annonce_id_annonce_seq'::regclass);


--
-- Name: answer id_answer; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer ALTER COLUMN id_answer SET DEFAULT nextval('public.answer_id_answer_seq'::regclass);


--
-- Name: answer id_question; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer ALTER COLUMN id_question SET DEFAULT nextval('public.answer_id_question_seq'::regclass);


--
-- Name: avance id_avance; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avance ALTER COLUMN id_avance SET DEFAULT nextval('public.avance_id_avance_seq'::regclass);


--
-- Name: avance id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avance ALTER COLUMN id_employe SET DEFAULT nextval('public.avance_id_employe_seq'::regclass);


--
-- Name: candidature id_candidature; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature ALTER COLUMN id_candidature SET DEFAULT nextval('public.candidature_id_candidature_seq'::regclass);


--
-- Name: candidature_test id_candidature_test; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test ALTER COLUMN id_candidature_test SET DEFAULT nextval('public.candidature_test_id_candidature_test_seq'::regclass);


--
-- Name: candidature_test id_candidature; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test ALTER COLUMN id_candidature SET DEFAULT nextval('public.candidature_test_id_candidature_seq'::regclass);


--
-- Name: candidature_test id_quiz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test ALTER COLUMN id_quiz SET DEFAULT nextval('public.candidature_test_id_quiz_seq'::regclass);


--
-- Name: classe_employe id_classe_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classe_employe ALTER COLUMN id_classe_employe SET DEFAULT nextval('public.classe_employe_id_classe_employe_seq'::regclass);


--
-- Name: conge id_conge; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge ALTER COLUMN id_conge SET DEFAULT nextval('public.conge_id_conge_seq'::regclass);


--
-- Name: conge id_personnel; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge ALTER COLUMN id_personnel SET DEFAULT nextval('public.conge_id_personnel_seq'::regclass);


--
-- Name: conge id_type_conge; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge ALTER COLUMN id_type_conge SET DEFAULT nextval('public.conge_id_type_conge_seq'::regclass);


--
-- Name: conge_report id_personnel; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge_report ALTER COLUMN id_personnel SET DEFAULT nextval('public.conge_report_id_personnel_seq'::regclass);


--
-- Name: contrat id_contrat; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat ALTER COLUMN id_contrat SET DEFAULT nextval('public.contrat_id_contrat_seq'::regclass);


--
-- Name: employe id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe ALTER COLUMN id_employe SET DEFAULT nextval('public.employe_id_employe_seq'::regclass);


--
-- Name: formation_base id_formation_base; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.formation_base ALTER COLUMN id_formation_base SET DEFAULT nextval('public.formation_base_id_formation_base_seq'::regclass);


--
-- Name: heure_supplementaire id_heure_supplementaire; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heure_supplementaire ALTER COLUMN id_heure_supplementaire SET DEFAULT nextval('public.heure_supplementaire_id_heure_supplementaire_seq'::regclass);


--
-- Name: heure_supplementaire id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heure_supplementaire ALTER COLUMN id_employe SET DEFAULT nextval('public.heure_supplementaire_id_employe_seq'::regclass);


--
-- Name: licenciement id_licenciement; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement ALTER COLUMN id_licenciement SET DEFAULT nextval('public.licenciement_id_licenciement_seq'::regclass);


--
-- Name: licenciement id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement ALTER COLUMN id_employe SET DEFAULT nextval('public.licenciement_id_employe_seq'::regclass);


--
-- Name: licenciement id_type_licenciement; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement ALTER COLUMN id_type_licenciement SET DEFAULT nextval('public.licenciement_id_type_licenciement_seq'::regclass);


--
-- Name: organisme id_organisme; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisme ALTER COLUMN id_organisme SET DEFAULT nextval('public.organisme_id_organisme_seq'::regclass);


--
-- Name: organisme_parameter id_organisme; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisme_parameter ALTER COLUMN id_organisme SET DEFAULT nextval('public.organisme_parameter_id_organisme_seq'::regclass);


--
-- Name: personnel id_personnel; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personnel ALTER COLUMN id_personnel SET DEFAULT nextval('public.personnel_id_personnel_seq'::regclass);


--
-- Name: personnel id_classe_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personnel ALTER COLUMN id_classe_employe SET DEFAULT nextval('public.personnel_id_classe_employe_seq'::regclass);


--
-- Name: prime id_prime; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime ALTER COLUMN id_prime SET DEFAULT nextval('public.prime_id_prime_seq'::regclass);


--
-- Name: prime_employe id_prime_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe ALTER COLUMN id_prime_employe SET DEFAULT nextval('public.prime_employe_id_prime_employe_seq'::regclass);


--
-- Name: prime_employe id_prime; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe ALTER COLUMN id_prime SET DEFAULT nextval('public.prime_employe_id_prime_seq'::regclass);


--
-- Name: prime_employe id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe ALTER COLUMN id_employe SET DEFAULT nextval('public.prime_employe_id_employe_seq'::regclass);


--
-- Name: professional_career id_professional_career; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professional_career ALTER COLUMN id_professional_career SET DEFAULT nextval('public.professional_career_id_professional_career_seq'::regclass);


--
-- Name: province id_province; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.province ALTER COLUMN id_province SET DEFAULT nextval('public.province_id_province_seq'::regclass);


--
-- Name: question id_question; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question ALTER COLUMN id_question SET DEFAULT nextval('public.question_id_question_seq'::regclass);


--
-- Name: question id_quiz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question ALTER COLUMN id_quiz SET DEFAULT nextval('public.question_id_quiz_seq'::regclass);


--
-- Name: quiz id_quiz; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz ALTER COLUMN id_quiz SET DEFAULT nextval('public.quiz_id_quiz_seq'::regclass);


--
-- Name: quiz id_service; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz ALTER COLUMN id_service SET DEFAULT nextval('public.quiz_id_service_seq'::regclass);


--
-- Name: quiz id_quiz_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz ALTER COLUMN id_quiz_type SET DEFAULT nextval('public.quiz_id_quiz_type_seq'::regclass);


--
-- Name: quiz_type id_quiz_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_type ALTER COLUMN id_quiz_type SET DEFAULT nextval('public.quiz_type_id_quiz_type_seq'::regclass);


--
-- Name: rappel_periode id_rappel_periode; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rappel_periode ALTER COLUMN id_rappel_periode SET DEFAULT nextval('public.rappel_periode_id_rappel_periode_seq'::regclass);


--
-- Name: type_conge id_type_conge; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_conge ALTER COLUMN id_type_conge SET DEFAULT nextval('public.type_conge_id_type_conge_seq'::regclass);


--
-- Name: type_licenciement id_type_licenciement; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_licenciement ALTER COLUMN id_type_licenciement SET DEFAULT nextval('public.type_licenciement_id_type_licenciement_seq'::regclass);


--
-- Name: vente_conge id_vente_conge; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente_conge ALTER COLUMN id_vente_conge SET DEFAULT nextval('public.vente_conge_id_vente_conge_seq'::regclass);


--
-- Name: vente_conge id_employe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente_conge ALTER COLUMN id_employe SET DEFAULT nextval('public.vente_conge_id_employe_seq'::regclass);


--
-- Name: work_location id_work_location; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_location ALTER COLUMN id_work_location SET DEFAULT nextval('public.work_location_id_work_location_seq'::regclass);


--
-- Data for Name: abscence; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.abscence (id_abscence, id_employe, date, heure_debut, heure_fin, etat) FROM stdin;
\.


--
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adresse (id_adresse, adresse, status) FROM stdin;
1	Andoharanofotsy	1
2	Tanjombato	1
3	Anosy	1
4	Itaosy	1
5	Analakely	1
6	Alasora	1
\.


--
-- Data for Name: adresse_note; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.adresse_note (id_wanted_profile, id_adresse, note) FROM stdin;
\.


--
-- Data for Name: annonce; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.annonce (id_annonce, id_besoin, id_service, nom_annonce, date_annonce, status) FROM stdin;
\.


--
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.answer (id_answer, id_question, answer, value) FROM stdin;
\.


--
-- Data for Name: avance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.avance (id_avance, id_employe, date, montant, etat) FROM stdin;
\.


--
-- Data for Name: besoin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.besoin (id_besoin, id_service, creation_date, description, status) FROM stdin;
\.


--
-- Data for Name: candidature; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.candidature (id_candidature, id_wanted_profile, deposit_date, name, first_name, birth_date, id_adresse, email, id_sexe, id_experience, id_diplome, interest_center, salary_expectation, self_profile, photo, dossier, note, status, telephone, candidature_picture) FROM stdin;
1	1	2022-01-01	INSSA	Chalman	2000-06-12	2	inssa.chalman@gmail.com	1	2	7	BasketBall - Musique - Loisir	2000000	Je suis motiv‚e pour diriger cette entreprise. Ca fait partie de mon reve d'etre sur la tete d'une entreprise	chalman.png	chalman.zip	17	6	034 50 986 12	chalman_candidature.png
2	2	2022-01-01	MAMIARILAZA	To	2004-07-07	1	mamiarilaza.to@gmail.com	1	3	5	FootBall - Lecture - Musique	1000000	Depuis 5 ans je suis le responsable ressource humaine chez GROOVE Industrie, et j'aimerai porter cette experience a vous	to.png	to.zip	18	6	034 14 517 93	to_candidature.png
3	3	2022-01-01	MICHAEL	Fy	2005-03-24	4	fy.michael@gmail.com	1	3	9	Dance - Cinema - Moto	1300000	Je suis directeur de production dans diff‚rent domaine et je pense que je peux m'exercer aussi dans le votre	fy.png	fy.zip	16	6	034 23 420 12	fy_candidature.png
4	4	2022-01-01	RAKOTOARIMANANA	Finoana	1998-08-13	5	finoanaRAKOTO@gmail.com	1	3	3	Musculation - Sport - Musique	800000	Depuis ma carriŠre la gestion des machines de production me va bien	finoana.png	finoana.zip	15	6	034 32 125 63	finoana_candidature.png
5	4	2022-01-01	RATSIVAHINY	Solo	1994-10-10	6	finoanaRAKOTO@gmail.com	1	3	3	Ecriture - Poete - Analyse	850000	La production me passione, mon objectif n'est pas vraiment l'argent mais produire	solo.png	solo.zip	16	6	033 25 312 28	solo_candidature.png
6	5	2023-01-01	RIANTSOA	Mialy	2000-04-08	3	mialy.RIANTSOA@gmail.com	2	3	5	Chanter - Management	1000000	Je suis sur que si c'est mois qui prends vos notes, aucun evenement se perd	mialy.png	mialy.zip	19	6	034 12 453 65	mialy_candidature.png
\.


--
-- Data for Name: candidature_test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.candidature_test (id_candidature_test, id_candidature, note, id_quiz, quiz_date, status) FROM stdin;
\.


--
-- Data for Name: classe_employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.classe_employe (id_classe_employe, classe_employe, classe_description, indamnite_licenciement, duree_preavis) FROM stdin;
1	D	Emplois salaries d'execution peu qualifies	100000.00	30
2	C	Emplois salaries d'execution qualifies	200000.00	45
3	B	Emplois salaries de niveau intermediaire	300000.00	60
4	A	Emplois salaries de niveau sup‚rieur	500000.00	90
5	HC	Emplois Hors Classe	500000.00	120
\.


--
-- Data for Name: conge; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conge (id_conge, id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, date_debut_reel, date_fin_reel, id_chef_hierarchique, remarque_chef_hierarchique, id_personnel_rh, remarque_personnel_rh, etat, deposit_date) FROM stdin;
\.


--
-- Data for Name: conge_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conge_parameter (accumulation, minimum_duree, max_solde) FROM stdin;
2.5	365	90
\.


--
-- Data for Name: conge_report; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.conge_report (id_personnel, annee, reste_conge) FROM stdin;
\.


--
-- Data for Name: contrat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.contrat (id_contrat, id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, poste, path, id_superieur) FROM stdin;
1	1	1	1	2000000	0	2022-01-01	\N	2022-01-01	1	1	3	1	chalman_contrat	\N
2	1	2	1	1000000	0	2022-01-01	\N	2022-01-01	1	1	3	2	to_contrat	1
3	1	3	1	1200000	0	2022-01-01	\N	2022-01-01	1	1	3	3	fy_contrat	1
6	2	6	1	1000000	0	2023-01-01	\N	2023-01-01	1	1	3	5	mialy_contrat	1
4	1	4	1	800000	0	2022-01-01	\N	2022-01-01	1	1	3	4	finoana_contrat	3
5	1	5	1	800000	0	2022-01-01	\N	2022-01-01	1	1	3	4	solo_contrat	3
\.


--
-- Data for Name: departement_rappel_periode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.departement_rappel_periode (id_service, id_rappel_periode) FROM stdin;
\.


--
-- Data for Name: diplome; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.diplome (id_diplome, diplome, status) FROM stdin;
1	CEPE	1
2	BEPC	1
3	BACC	1
4	License en management	1
5	Master en management	1
6	License en droit	1
7	Master en droit	1
8	License en informatique	1
9	Master en informatique	1
\.


--
-- Data for Name: diplome_note; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.diplome_note (id_wanted_profile, id_diplome, note) FROM stdin;
\.


--
-- Data for Name: employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employe (id_employe, id_contrat, num_matricule, date_embauche, status, id_classe_employe) FROM stdin;
1	1	EMP00122	2022-01-01	1	5
2	2	EMP00222	2022-01-01	1	4
3	3	EMP00322	2022-01-01	1	4
4	4	EMP00422	2022-01-01	1	2
5	5	EMP00522	2022-01-01	1	2
6	6	EMP00622	2023-01-01	1	3
\.


--
-- Data for Name: experience; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.experience (id_experience, experience, status) FROM stdin;
1	1 an d`experience	1
2	3 ans d`experiences	1
3	5 ans d`experiences	1
\.


--
-- Data for Name: experience_note; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.experience_note (id_wanted_profile, id_experience, note) FROM stdin;
\.


--
-- Data for Name: formation_base; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.formation_base (id_formation_base, id_candidature, year, diplome, school) FROM stdin;
\.


--
-- Data for Name: heure_sup_duration_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.heure_sup_duration_parameter (heure_debut, heure_fin, pourcentage) FROM stdin;
0	8	30.0
9	16	50.0
\.


--
-- Data for Name: heure_supplementaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.heure_supplementaire (id_heure_supplementaire, id_employe, debut, fin, etat) FROM stdin;
\.


--
-- Data for Name: irsa_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.irsa_parameter (debut, fin, pourcentage) FROM stdin;
0.00	350000.00	0.00
350001.00	400000.00	5.00
400001.00	500000.00	10.00
500001.00	600000.00	15.00
600001.00	\N	20.00
\.


--
-- Data for Name: licenciement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.licenciement (id_licenciement, id_employe, date_preavis, date_licenciement, id_type_licenciement, droit_preavis, etat) FROM stdin;
1	12	2023-10-27	2023-10-27	2	1000000.00	\N
2	12	2023-10-27	2023-11-26	2	0.00	1
\.


--
-- Data for Name: night_work_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.night_work_parameter (debut_heure, fin_heure, pourcentage) FROM stdin;
22:00:00	23:59:00	30.0
00:00:00	05:00:00	30.0
\.


--
-- Data for Name: organisme; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organisme (id_organisme, nom_organisme, description, etat) FROM stdin;
1	CNAPS	Caisse NAtional de Prevoyance Sociale	1
2	OSTIE	Organisation Sante Entreprise	1
\.


--
-- Data for Name: organisme_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.organisme_parameter (id_organisme, pourcentage, plafond) FROM stdin;
1	1.00	8
2	1.00	4
\.


--
-- Data for Name: out_work_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.out_work_parameter (day, month, pourcentage) FROM stdin;
1	1	100.0
26	6	100.0
\.


--
-- Data for Name: personnel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.personnel (id_personnel, nom, prenom, date_naissance, telephone, id_superieur, date_embauche, poste, salaire, id_classe_employe) FROM stdin;
12	Smith	John	1990-01-15	1234567890	\N	2022-02-15	Developpeur	1000000.00	1
13	Doe	Jane	1985-03-30	9876543210	\N	2021-11-30	Concepteur	1200000.00	2
16	Johnson	Michael	1995-05-20	5555555555	12	2023-04-10	Designer	1500000.00	3
17	Williams	Sarah	1998-07-10	6666666666	13	2022-08-05	Designer	1800000.00	4
\.


--
-- Data for Name: prime; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prime (id_prime, prime_name, etat) FROM stdin;
1	Prime d'anciennete	1
2	Prime de rendement	1
3	Prime divers	1
\.


--
-- Data for Name: prime_employe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prime_employe (id_prime_employe, id_prime, id_employe, date_prime, montant, etat) FROM stdin;
\.


--
-- Data for Name: professional_career; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.professional_career (id_professional_career, id_candidature, start_date, end_date, society, poste, task) FROM stdin;
\.


--
-- Data for Name: province; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.province (id_province, name) FROM stdin;
1	ANTANANARIVO
2	DIEGO
3	TOAMASINA
4	MAHAJANGA
5	TOLIARA
6	FIANARANTSOA
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.question (id_question, id_quiz, question, score) FROM stdin;
\.


--
-- Data for Name: quiz; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quiz (id_quiz, id_service, quiz_name, id_quiz_type, status) FROM stdin;
\.


--
-- Data for Name: quiz_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.quiz_type (id_quiz_type, quiz_type) FROM stdin;
1	Question
2	Reponse
\.


--
-- Data for Name: rappel_periode; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rappel_periode (id_rappel_periode, date, modification_salaire, nombre_mois, etat) FROM stdin;
\.


--
-- Data for Name: salaire; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salaire (id_salaire, salaire, status) FROM stdin;
1	200000	1
2	300000	1
3	500000	1
4	700000	1
5	1000000	1
6	1500000	1
7	2000000	1
\.


--
-- Data for Name: salaire_note; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.salaire_note (id_wanted_profile, id_salaire, note) FROM stdin;
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service (id_service, service, fonction, creation_date, status) FROM stdin;
1	Administration	Responsable de l'administration du societe	2022-01-01	1
2	Ressources humaines	Responsable des ressources humaines	2022-01-01	1
3	Production	Responsable des productions du societe	2022-01-01	1
\.


--
-- Data for Name: sexe; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sexe (id_sexe, sexe, status) FROM stdin;
1	Homme	1
2	Femme	1
\.


--
-- Data for Name: sexe_note; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sexe_note (id_wanted_profile, id_sexe, note) FROM stdin;
\.


--
-- Data for Name: smig_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.smig_parameter (smig_value) FROM stdin;
250000.00
\.


--
-- Data for Name: society; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.society (name, adresse, nif, contact, secteur, legal_statut) FROM stdin;
AUXIMAD	Antananarivo	0012-8221	+261346595861	Informatique	1
\.


--
-- Data for Name: task; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.task (id_task, id_besoin, task, status) FROM stdin;
\.


--
-- Data for Name: type_conge; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_conge (id_type_conge, type_conge_name, is_cumulable, sexe_valability, duree, etat) FROM stdin;
1	Conge de maternite	f	0	90	1
2	Conge de paternite	f	1	3	1
3	Repos medicale	f	2	0	1
4	Vacance	t	2	0	1
5	Evenement familiale	t	2	0	1
6	Vente de conge	t	2	0	0
\.


--
-- Data for Name: type_licenciement; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.type_licenciement (id_type_licenciement, type_licenciement) FROM stdin;
1	Demande employe
2	Ordre employeur
\.


--
-- Data for Name: unity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.unity (id_unity, unity, status) FROM stdin;
1	H/J	1
\.


--
-- Data for Name: utilisateur; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utilisateur (id_utilisateur, id_service, username, password, mail, status, admin, id_employe) FROM stdin;
1	1	INSSA Chalman	chalman	inssa.chalman@gmail.com	1	t	1
2	2	To MAMIARILAZA	to	mamiarilaza.to@gmail.com	1	t	2
3	3	Fy Michael	fy	fy.michael@gmail.com	1	t	3
4	3	Finoana RAKOTO	finoana	finoanaRAKOTO@gmail.com	1	f	4
5	3	Solo RATSIVAHINY	solo	soloRATSIVAHINY@gmail.com	1	f	5
6	1	Mialy RIANTSOA	mialy	mialy.RIANTSOA@gmail.com	1	f	5
\.


--
-- Data for Name: vente_conge; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.vente_conge (id_vente_conge, id_employe, debut, fin, montant, etat) FROM stdin;
\.


--
-- Data for Name: wanted_profile; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wanted_profile (id_wanted_profile, poste, id_service, status, id_quiz) FROM stdin;
1	Directeur generale	1	0	\N
3	Directeur de production	3	0	\N
4	Ouvrier producteur	3	0	\N
5	Secretaire generale	1	0	\N
2	Ressouces humaines	2	0	\N
\.


--
-- Data for Name: week_end_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.week_end_parameter (day, pourcentage) FROM stdin;
7	40.0
\.


--
-- Data for Name: work_location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.work_location (id_work_location, name, id_adress) FROM stdin;
1	Siege Huile de bongolava	1
2	Point de vente Analakely	5
\.


--
-- Data for Name: workload; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workload (id_workload, id_besoin, id_wanted_profile, quantity, id_unity) FROM stdin;
\.


--
-- Name: abscence_id_abscence_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abscence_id_abscence_seq', 7, true);


--
-- Name: abscence_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.abscence_id_employe_seq', 1, false);


--
-- Name: adresse_id_adresse_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.adresse_id_adresse_seq', 20, false);


--
-- Name: annonce_id_annonce_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.annonce_id_annonce_seq', 2, true);


--
-- Name: answer_id_answer_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id_answer_seq', 134, true);


--
-- Name: answer_id_question_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.answer_id_question_seq', 1, false);


--
-- Name: avance_id_avance_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.avance_id_avance_seq', 4, true);


--
-- Name: avance_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.avance_id_employe_seq', 1, false);


--
-- Name: besoin_id_besoin_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.besoin_id_besoin_seq', 30, true);


--
-- Name: candidature_id_candidature_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.candidature_id_candidature_seq', 23, true);


--
-- Name: candidature_test_id_candidature_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.candidature_test_id_candidature_seq', 1, false);


--
-- Name: candidature_test_id_candidature_test_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.candidature_test_id_candidature_test_seq', 6, true);


--
-- Name: candidature_test_id_quiz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.candidature_test_id_quiz_seq', 1, false);


--
-- Name: classe_employe_id_classe_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.classe_employe_id_classe_employe_seq', 5, true);


--
-- Name: conge_id_conge_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conge_id_conge_seq', 52, true);


--
-- Name: conge_id_personnel_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conge_id_personnel_seq', 1, false);


--
-- Name: conge_id_type_conge_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conge_id_type_conge_seq', 1, false);


--
-- Name: conge_report_id_personnel_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.conge_report_id_personnel_seq', 1, false);


--
-- Name: contrat_id_contrat_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.contrat_id_contrat_seq', 5, true);


--
-- Name: diplome_id_diplome_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.diplome_id_diplome_seq', 20, false);


--
-- Name: employe_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employe_id_employe_seq', 5, true);


--
-- Name: experience_id_experience_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.experience_id_experience_seq', 20, false);


--
-- Name: formation_base_id_formation_base_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.formation_base_id_formation_base_seq', 14, true);


--
-- Name: heure_supplementaire_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.heure_supplementaire_id_employe_seq', 1, false);


--
-- Name: heure_supplementaire_id_heure_supplementaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.heure_supplementaire_id_heure_supplementaire_seq', 20, true);


--
-- Name: licenciement_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.licenciement_id_employe_seq', 1, false);


--
-- Name: licenciement_id_licenciement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.licenciement_id_licenciement_seq', 2, true);


--
-- Name: licenciement_id_type_licenciement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.licenciement_id_type_licenciement_seq', 1, false);


--
-- Name: organisme_id_organisme_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organisme_id_organisme_seq', 2, true);


--
-- Name: organisme_parameter_id_organisme_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.organisme_parameter_id_organisme_seq', 1, false);


--
-- Name: personnel_id_classe_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.personnel_id_classe_employe_seq', 4, true);


--
-- Name: personnel_id_personnel_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.personnel_id_personnel_seq', 17, true);


--
-- Name: prime_employe_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prime_employe_id_employe_seq', 1, false);


--
-- Name: prime_employe_id_prime_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prime_employe_id_prime_employe_seq', 7, true);


--
-- Name: prime_employe_id_prime_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prime_employe_id_prime_seq', 1, false);


--
-- Name: prime_id_prime_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prime_id_prime_seq', 3, true);


--
-- Name: professional_career_id_professional_career_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.professional_career_id_professional_career_seq', 14, true);


--
-- Name: province_id_province_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.province_id_province_seq', 6, true);


--
-- Name: question_id_question_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id_question_seq', 69, true);


--
-- Name: question_id_quiz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.question_id_quiz_seq', 1, false);


--
-- Name: quiz_id_quiz_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_id_quiz_seq', 77, true);


--
-- Name: quiz_id_quiz_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_id_quiz_type_seq', 1, false);


--
-- Name: quiz_id_service_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_id_service_seq', 1, false);


--
-- Name: quiz_type_id_quiz_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.quiz_type_id_quiz_type_seq', 1, false);


--
-- Name: rappel_periode_id_rappel_periode_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rappel_periode_id_rappel_periode_seq', 10, true);


--
-- Name: salaire_id_salaire_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.salaire_id_salaire_seq', 20, false);


--
-- Name: service_id_service_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.service_id_service_seq', 20, false);


--
-- Name: sexe_id_sexe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sexe_id_sexe_seq', 20, false);


--
-- Name: task_id_task_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.task_id_task_seq', 42, true);


--
-- Name: type_conge_id_type_conge_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_conge_id_type_conge_seq', 6, true);


--
-- Name: type_licenciement_id_type_licenciement_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.type_licenciement_id_type_licenciement_seq', 1, false);


--
-- Name: unity_id_unity_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.unity_id_unity_seq', 20, false);


--
-- Name: utilisateur_id_utilisateur_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.utilisateur_id_utilisateur_seq', 20, false);


--
-- Name: vente_conge_id_employe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vente_conge_id_employe_seq', 1, false);


--
-- Name: vente_conge_id_vente_conge_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vente_conge_id_vente_conge_seq', 5, true);


--
-- Name: wanted_profile_id_wanted_profile_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wanted_profile_id_wanted_profile_seq', 30, true);


--
-- Name: work_location_id_work_location_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.work_location_id_work_location_seq', 8, true);


--
-- Name: workload_id_workload_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workload_id_workload_seq', 35, true);


--
-- Name: abscence abscence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abscence
    ADD CONSTRAINT abscence_pkey PRIMARY KEY (id_abscence);


--
-- Name: adresse adresse_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse
    ADD CONSTRAINT adresse_pkey PRIMARY KEY (id_adresse);


--
-- Name: annonce annonce_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_pkey PRIMARY KEY (id_annonce);


--
-- Name: answer answer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id_answer);


--
-- Name: avance avance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avance
    ADD CONSTRAINT avance_pkey PRIMARY KEY (id_avance);


--
-- Name: besoin besoin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.besoin
    ADD CONSTRAINT besoin_pkey PRIMARY KEY (id_besoin);


--
-- Name: candidature candidature_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_pkey PRIMARY KEY (id_candidature);


--
-- Name: candidature_test candidature_test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test
    ADD CONSTRAINT candidature_test_pkey PRIMARY KEY (id_candidature_test);


--
-- Name: classe_employe classe_employe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.classe_employe
    ADD CONSTRAINT classe_employe_pkey PRIMARY KEY (id_classe_employe);


--
-- Name: conge conge_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge
    ADD CONSTRAINT conge_pkey PRIMARY KEY (id_conge);


--
-- Name: contrat contrat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_pkey PRIMARY KEY (id_contrat);


--
-- Name: departement_rappel_periode departement_rappel_periode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departement_rappel_periode
    ADD CONSTRAINT departement_rappel_periode_pkey PRIMARY KEY (id_service, id_rappel_periode);


--
-- Name: diplome diplome_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diplome
    ADD CONSTRAINT diplome_pkey PRIMARY KEY (id_diplome);


--
-- Name: employe employe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT employe_pkey PRIMARY KEY (id_employe);


--
-- Name: experience experience_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.experience
    ADD CONSTRAINT experience_pkey PRIMARY KEY (id_experience);


--
-- Name: formation_base formation_base_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.formation_base
    ADD CONSTRAINT formation_base_pkey PRIMARY KEY (id_formation_base);


--
-- Name: heure_supplementaire heure_supplementaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heure_supplementaire
    ADD CONSTRAINT heure_supplementaire_pkey PRIMARY KEY (id_heure_supplementaire);


--
-- Name: licenciement licenciement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement
    ADD CONSTRAINT licenciement_pkey PRIMARY KEY (id_licenciement);


--
-- Name: organisme organisme_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisme
    ADD CONSTRAINT organisme_pkey PRIMARY KEY (id_organisme);


--
-- Name: personnel personnel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personnel
    ADD CONSTRAINT personnel_pkey PRIMARY KEY (id_personnel);


--
-- Name: prime_employe prime_employe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe
    ADD CONSTRAINT prime_employe_pkey PRIMARY KEY (id_prime_employe);


--
-- Name: prime prime_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime
    ADD CONSTRAINT prime_pkey PRIMARY KEY (id_prime);


--
-- Name: professional_career professional_career_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professional_career
    ADD CONSTRAINT professional_career_pkey PRIMARY KEY (id_professional_career);


--
-- Name: province province_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.province
    ADD CONSTRAINT province_pkey PRIMARY KEY (id_province);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id_question);


--
-- Name: quiz quiz_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT quiz_pkey PRIMARY KEY (id_quiz);


--
-- Name: quiz_type quiz_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz_type
    ADD CONSTRAINT quiz_type_pkey PRIMARY KEY (id_quiz_type);


--
-- Name: rappel_periode rappel_periode_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rappel_periode
    ADD CONSTRAINT rappel_periode_pkey PRIMARY KEY (id_rappel_periode);


--
-- Name: salaire salaire_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salaire
    ADD CONSTRAINT salaire_pkey PRIMARY KEY (id_salaire);


--
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id_service);


--
-- Name: sexe sexe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexe
    ADD CONSTRAINT sexe_pkey PRIMARY KEY (id_sexe);


--
-- Name: task task_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id_task);


--
-- Name: type_conge type_conge_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_conge
    ADD CONSTRAINT type_conge_pkey PRIMARY KEY (id_type_conge);


--
-- Name: type_licenciement type_licenciement_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.type_licenciement
    ADD CONSTRAINT type_licenciement_pkey PRIMARY KEY (id_type_licenciement);


--
-- Name: unity unity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unity
    ADD CONSTRAINT unity_pkey PRIMARY KEY (id_unity);


--
-- Name: utilisateur utilisateur_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_pkey PRIMARY KEY (id_utilisateur);


--
-- Name: vente_conge vente_conge_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente_conge
    ADD CONSTRAINT vente_conge_pkey PRIMARY KEY (id_vente_conge);


--
-- Name: wanted_profile wanted_profile_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wanted_profile
    ADD CONSTRAINT wanted_profile_pkey PRIMARY KEY (id_wanted_profile);


--
-- Name: work_location work_location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_location
    ADD CONSTRAINT work_location_pkey PRIMARY KEY (id_work_location);


--
-- Name: workload workload_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workload
    ADD CONSTRAINT workload_pkey PRIMARY KEY (id_workload);


--
-- Name: abscence abscence_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.abscence
    ADD CONSTRAINT abscence_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: adresse_note adresse_note_id_adresse_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse_note
    ADD CONSTRAINT adresse_note_id_adresse_fkey FOREIGN KEY (id_adresse) REFERENCES public.adresse(id_adresse);


--
-- Name: adresse_note adresse_note_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.adresse_note
    ADD CONSTRAINT adresse_note_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: annonce annonce_id_besoin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_id_besoin_fkey FOREIGN KEY (id_besoin) REFERENCES public.besoin(id_besoin);


--
-- Name: annonce annonce_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.annonce
    ADD CONSTRAINT annonce_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: answer answer_id_question_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_id_question_fkey FOREIGN KEY (id_question) REFERENCES public.question(id_question);


--
-- Name: avance avance_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avance
    ADD CONSTRAINT avance_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: besoin besoin_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.besoin
    ADD CONSTRAINT besoin_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: candidature candidature_id_adresse_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_id_adresse_fkey FOREIGN KEY (id_adresse) REFERENCES public.adresse(id_adresse);


--
-- Name: candidature candidature_id_diplome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_id_diplome_fkey FOREIGN KEY (id_diplome) REFERENCES public.diplome(id_diplome);


--
-- Name: candidature candidature_id_experience_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_id_experience_fkey FOREIGN KEY (id_experience) REFERENCES public.experience(id_experience);


--
-- Name: candidature candidature_id_sexe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_id_sexe_fkey FOREIGN KEY (id_sexe) REFERENCES public.sexe(id_sexe);


--
-- Name: candidature candidature_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature
    ADD CONSTRAINT candidature_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: candidature_test candidature_test_id_candidature_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test
    ADD CONSTRAINT candidature_test_id_candidature_fkey FOREIGN KEY (id_candidature) REFERENCES public.candidature(id_candidature);


--
-- Name: candidature_test candidature_test_id_quiz_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.candidature_test
    ADD CONSTRAINT candidature_test_id_quiz_fkey FOREIGN KEY (id_quiz) REFERENCES public.quiz(id_quiz);


--
-- Name: personnel classe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personnel
    ADD CONSTRAINT classe_fkey FOREIGN KEY (id_classe_employe) REFERENCES public.classe_employe(id_classe_employe);


--
-- Name: conge conge_id_chef_hierarchique_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge
    ADD CONSTRAINT conge_id_chef_hierarchique_fkey FOREIGN KEY (id_chef_hierarchique) REFERENCES public.employe(id_employe);


--
-- Name: conge conge_id_personnel_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge
    ADD CONSTRAINT conge_id_personnel_fkey FOREIGN KEY (id_personnel) REFERENCES public.employe(id_employe);


--
-- Name: conge conge_id_personnel_rh_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge
    ADD CONSTRAINT conge_id_personnel_rh_fkey FOREIGN KEY (id_personnel_rh) REFERENCES public.employe(id_employe);


--
-- Name: conge conge_id_type_conge_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge
    ADD CONSTRAINT conge_id_type_conge_fkey FOREIGN KEY (id_type_conge) REFERENCES public.type_conge(id_type_conge);


--
-- Name: conge_report conge_report_id_personnel_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conge_report
    ADD CONSTRAINT conge_report_id_personnel_fkey FOREIGN KEY (id_personnel) REFERENCES public.employe(id_employe);


--
-- Name: contrat contrat_id_candidature_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_id_candidature_fkey FOREIGN KEY (id_candidature) REFERENCES public.candidature(id_candidature);


--
-- Name: contrat contrat_id_province_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_id_province_fkey FOREIGN KEY (id_province) REFERENCES public.province(id_province);


--
-- Name: contrat contrat_id_superieur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_id_superieur_fkey FOREIGN KEY (id_superieur) REFERENCES public.employe(id_employe);


--
-- Name: contrat contrat_id_work_location_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_id_work_location_fkey FOREIGN KEY (id_work_location) REFERENCES public.work_location(id_work_location);


--
-- Name: contrat contrat_poste_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contrat
    ADD CONSTRAINT contrat_poste_fkey FOREIGN KEY (poste) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: departement_rappel_periode departement_rappel_periode_id_rappel_periode_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departement_rappel_periode
    ADD CONSTRAINT departement_rappel_periode_id_rappel_periode_fkey FOREIGN KEY (id_rappel_periode) REFERENCES public.rappel_periode(id_rappel_periode);


--
-- Name: departement_rappel_periode departement_rappel_periode_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.departement_rappel_periode
    ADD CONSTRAINT departement_rappel_periode_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: diplome_note diplome_note_id_diplome_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diplome_note
    ADD CONSTRAINT diplome_note_id_diplome_fkey FOREIGN KEY (id_diplome) REFERENCES public.diplome(id_diplome);


--
-- Name: diplome_note diplome_note_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.diplome_note
    ADD CONSTRAINT diplome_note_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: employe employe_id_classe_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT employe_id_classe_employe_fkey FOREIGN KEY (id_classe_employe) REFERENCES public.classe_employe(id_classe_employe);


--
-- Name: employe employe_id_contrat_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employe
    ADD CONSTRAINT employe_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES public.contrat(id_contrat);


--
-- Name: experience_note experience_note_id_experience_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.experience_note
    ADD CONSTRAINT experience_note_id_experience_fkey FOREIGN KEY (id_experience) REFERENCES public.experience(id_experience);


--
-- Name: experience_note experience_note_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.experience_note
    ADD CONSTRAINT experience_note_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: formation_base formation_base_id_candidature_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.formation_base
    ADD CONSTRAINT formation_base_id_candidature_fkey FOREIGN KEY (id_candidature) REFERENCES public.candidature(id_candidature);


--
-- Name: heure_supplementaire heure_supplementaire_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.heure_supplementaire
    ADD CONSTRAINT heure_supplementaire_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: licenciement licenciement_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement
    ADD CONSTRAINT licenciement_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.personnel(id_personnel);


--
-- Name: licenciement licenciement_id_type_licenciement_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.licenciement
    ADD CONSTRAINT licenciement_id_type_licenciement_fkey FOREIGN KEY (id_type_licenciement) REFERENCES public.type_licenciement(id_type_licenciement);


--
-- Name: organisme_parameter organisme_parameter_id_organisme_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.organisme_parameter
    ADD CONSTRAINT organisme_parameter_id_organisme_fkey FOREIGN KEY (id_organisme) REFERENCES public.organisme(id_organisme);


--
-- Name: personnel personnel_id_superieur_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personnel
    ADD CONSTRAINT personnel_id_superieur_fkey FOREIGN KEY (id_superieur) REFERENCES public.personnel(id_personnel);


--
-- Name: prime_employe prime_employe_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe
    ADD CONSTRAINT prime_employe_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: prime_employe prime_employe_id_prime_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prime_employe
    ADD CONSTRAINT prime_employe_id_prime_fkey FOREIGN KEY (id_prime) REFERENCES public.prime(id_prime);


--
-- Name: professional_career professional_career_id_candidature_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.professional_career
    ADD CONSTRAINT professional_career_id_candidature_fkey FOREIGN KEY (id_candidature) REFERENCES public.candidature(id_candidature);


--
-- Name: question question_id_quiz_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_id_quiz_fkey FOREIGN KEY (id_quiz) REFERENCES public.quiz(id_quiz);


--
-- Name: wanted_profile quiz_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wanted_profile
    ADD CONSTRAINT quiz_fkey FOREIGN KEY (id_quiz) REFERENCES public.quiz(id_quiz);


--
-- Name: quiz quiz_id_quiz_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT quiz_id_quiz_type_fkey FOREIGN KEY (id_quiz_type) REFERENCES public.quiz_type(id_quiz_type);


--
-- Name: quiz quiz_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.quiz
    ADD CONSTRAINT quiz_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: salaire_note salaire_note_id_salaire_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salaire_note
    ADD CONSTRAINT salaire_note_id_salaire_fkey FOREIGN KEY (id_salaire) REFERENCES public.salaire(id_salaire);


--
-- Name: salaire_note salaire_note_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.salaire_note
    ADD CONSTRAINT salaire_note_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: sexe_note sexe_note_id_sexe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexe_note
    ADD CONSTRAINT sexe_note_id_sexe_fkey FOREIGN KEY (id_sexe) REFERENCES public.sexe(id_sexe);


--
-- Name: sexe_note sexe_note_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sexe_note
    ADD CONSTRAINT sexe_note_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- Name: task task_id_besoin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_id_besoin_fkey FOREIGN KEY (id_besoin) REFERENCES public.besoin(id_besoin);


--
-- Name: utilisateur utilisateur_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: utilisateur utilisateur_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utilisateur
    ADD CONSTRAINT utilisateur_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: vente_conge vente_conge_id_employe_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vente_conge
    ADD CONSTRAINT vente_conge_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES public.employe(id_employe);


--
-- Name: wanted_profile wanted_profile_id_service_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wanted_profile
    ADD CONSTRAINT wanted_profile_id_service_fkey FOREIGN KEY (id_service) REFERENCES public.service(id_service);


--
-- Name: work_location work_location_id_adress_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.work_location
    ADD CONSTRAINT work_location_id_adress_fkey FOREIGN KEY (id_adress) REFERENCES public.adresse(id_adresse);


--
-- Name: workload workload_id_besoin_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workload
    ADD CONSTRAINT workload_id_besoin_fkey FOREIGN KEY (id_besoin) REFERENCES public.besoin(id_besoin);


--
-- Name: workload workload_id_unity_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workload
    ADD CONSTRAINT workload_id_unity_fkey FOREIGN KEY (id_unity) REFERENCES public.unity(id_unity);


--
-- Name: workload workload_id_wanted_profile_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workload
    ADD CONSTRAINT workload_id_wanted_profile_fkey FOREIGN KEY (id_wanted_profile) REFERENCES public.wanted_profile(id_wanted_profile);


--
-- PostgreSQL database dump complete
--

