/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.candidature.Candidature;
import model.candidature.Career;
import model.candidature.Formation;
import model.candidature.FormationPath;
import model.candidature.PersonnalInformation;
import model.candidature.ProfessionalCareer;
import model.gestionProfile.Adresse;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;
import model.gestionProfile.Sexe;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author To Mamiarilaza
 */
public class CandidatureService {
    /// Classe de service pour les candidatures en quiz
    
    //Avoir toutes les candidats
    public static ArrayList<Candidature> getAll(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "SELECT * FROM candidature WHERE status = 2";
        ResultSet result = work.executeQuery(req);
        ArrayList<Candidature> candidats = new ArrayList<>();

        while (result.next()) {
            //Candidature(int idCandidature, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCareer, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
            //PersonnalInformation(String name, String firstName, Date birthDate, Adresse adresse, String email, String telephone, Sexe sexe)
            PersonnalInformation pi = new PersonnalInformation(result.getString("name"), result.getString("first_name"), result.getDate("birth_date"), Adresse.getById(conn, result.getInt("id_adresse")), result.getString("email"), "0345091434", Sexe.getById(conn, result.getInt("id_sexe")));
            ProfessionalCareer pc = new ProfessionalCareer(Experience.getById(conn, result.getInt("id_experience")), Career.getCareerCandidat(conn, result.getInt("id_candidature")));
            FormationPath fp = new FormationPath(Diplome.getById(conn, result.getInt("id_diplome")), Formation.getFormationCandidat(conn, result.getInt("id_candidature")));
            Candidature candidat = new Candidature(result.getInt("id_candidature"), WantedProfile.getById(conn, result.getInt("id_wanted_profile")), result.getDate("deposit_date"), pi, pc, fp, result.getString("interest_center"), result.getDouble("salary_expectation"), result.getString("self_profile"), result.getString("photo"), result.getString("dossier"), result.getDouble("note"), result.getInt("status"));
            candidat.setCandidaturePicture(result.getString("candidature_picture"));
            candidats.add(candidat);
        }

        return candidats;
    }
    
    //Avoir toutes les candidats a un poste donnée
    public static ArrayList<Candidature> getAllInPoste(int idWantedProfile, Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "SELECT * FROM candidature WHERE status = 2 and id_wanted_profile = %d";
        req = String.format(req, idWantedProfile);
        ResultSet result = work.executeQuery(req);
        ArrayList<Candidature> candidats = new ArrayList<>();

        while (result.next()) {
            //Candidature(int idCandidature, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCareer, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
            //PersonnalInformation(String name, String firstName, Date birthDate, Adresse adresse, String email, String telephone, Sexe sexe)
            PersonnalInformation pi = new PersonnalInformation(result.getString("name"), result.getString("first_name"), result.getDate("birth_date"), Adresse.getById(conn, result.getInt("id_adresse")), result.getString("email"), "0345091434", Sexe.getById(conn, result.getInt("id_sexe")));
            ProfessionalCareer pc = new ProfessionalCareer(Experience.getById(conn, result.getInt("id_experience")), Career.getCareerCandidat(conn, result.getInt("id_candidature")));
            FormationPath fp = new FormationPath(Diplome.getById(conn, result.getInt("id_diplome")), Formation.getFormationCandidat(conn, result.getInt("id_candidature")));
            Candidature candidat = new Candidature(result.getInt("id_candidature"), WantedProfile.getById(conn, result.getInt("id_wanted_profile")), result.getDate("deposit_date"), pi, pc, fp, result.getString("interest_center"), result.getDouble("salary_expectation"), result.getString("self_profile"), result.getString("photo"), result.getString("dossier"), result.getDouble("note"), result.getInt("status"));
            candidat.setCandidaturePicture(result.getString("candidature_picture"));
            candidats.add(candidat);
        }

        return candidats;
    }
}
