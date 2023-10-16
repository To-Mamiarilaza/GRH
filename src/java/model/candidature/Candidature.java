/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.candidature;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.gestionProfile.Adresse;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;
import model.gestionProfile.Sexe;
import model.gestionProfile.WantedProfile;
import model.requis.Service;

/**
 *
 * @author Fy Botas
 */
public class Candidature {

    int idCandidature;
    WantedProfile wantedProfile;
    String depositDate;
    PersonnalInformation PersonnalInformation;
    ProfessionalCareer ProfessionalCareer;
    FormationPath FormationPath;
    String interestCenter;
    double SalaryExpectation;
    String selfProfile;
    String photo;
    String dossier;
    double note;
    int status;

    public Candidature(int idCandidature, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCareer, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
        this.idCandidature = idCandidature;
        this.PersonnalInformation = PersonnalInformation;
        this.ProfessionalCareer = ProfessionalCareer;
        this.FormationPath = FormationPath;
        this.interestCenter = interestCareer;
        this.SalaryExpectation = SalaryExpectation;
        this.selfProfile = selfProfile;
        this.photo = photo;
        this.dossier = dossier;
        this.note = note;
        this.status = status;
    }

    public Candidature(int idCandidature, WantedProfile wantedProfile, String depositDate, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCenter, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
        this.idCandidature = idCandidature;
        this.wantedProfile = wantedProfile;
        this.depositDate = depositDate;
        this.PersonnalInformation = PersonnalInformation;
        this.ProfessionalCareer = ProfessionalCareer;
        this.FormationPath = FormationPath;
        this.interestCenter = interestCenter;
        this.SalaryExpectation = SalaryExpectation;
        this.selfProfile = selfProfile;
        this.photo = photo;
        this.dossier = dossier;
        this.note = note;
        this.status = status;
    }

    
    public int getLastId(Connection con) throws Exception {
        boolean b = true;
        int id = 0;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "select max(id_candidature) as id from candidature";
            System.out.println(requete);
            Statement s = con.createStatement();
            s.executeQuery(requete);
            ResultSet rs = s.executeQuery(requete);
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
        return id;
    }

    public void create(Connection con, int idWantedProfile, String adresse, int sexe, String experience, String diplome) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into candidature values (DEFAULT, " + idWantedProfile + ", CURRENT_DATE, '" + this.getPersonnalInformation().getName()
                    + "', '" + this.getPersonnalInformation().getFirstName() + "', '" + this.getPersonnalInformation().getBirthDate() + "', "
                    + this.getPersonnalInformation().getAdresse().getIdByName(adresse, null) + ", '" + this.getPersonnalInformation().getEmail() + "', "
                    + this.getPersonnalInformation().getSexe().getSexe() + ", " + this.getProfessionalCareer().getExperience().getIdByName(experience, null) + ", "
                    + this.getFormationPath().getDiplome().getIdByName(diplome, null) + ", '" + this.getInterestCareer() + "', " + this.getSalaryExpectation() + ", '" + this.getSelfProfile() + "', '"
                    + this.getPhoto() + "', '" + this.getDossier() + "', " + this.getNote() + ", 1)";
            System.out.println(requete);
            Statement s = con.createStatement();
            s.executeUpdate(requete);
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    public Candidature() {
    }

    public int getIdCandidature() {
        return idCandidature;
    }

    public void setIdCandidature(int idCandidature) {
        this.idCandidature = idCandidature;
    }

    public PersonnalInformation getPersonnalInformation() {
        return PersonnalInformation;
    }

    public void setPersonnalInformation(PersonnalInformation PersonnalInformation) {
        this.PersonnalInformation = PersonnalInformation;
    }

    public ProfessionalCareer getProfessionalCareer() {
        return ProfessionalCareer;
    }

    public void setProfessionalCareer(ProfessionalCareer ProfessionalCareer) {
        this.ProfessionalCareer = ProfessionalCareer;
    }

    public FormationPath getFormationPath() {
        return FormationPath;
    }

    public void setFormationPath(FormationPath FormationPath) {
        this.FormationPath = FormationPath;
    }

    public String getInterestCareer() {
        return interestCenter;
    }

    public void setInterestCareer(String interestCareer) {
        this.interestCenter = interestCareer;
    }

    public double getSalaryExpectation() {
        return SalaryExpectation;
    }

    public void setSalaryExpectation(double SalaryExpectation) {
        this.SalaryExpectation = SalaryExpectation;
    }

    public String getSelfProfile() {
        return selfProfile;
    }

    public void setSelfProfile(String selfProfile) {
        this.selfProfile = selfProfile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public WantedProfile getWantedProfile() {
        return wantedProfile;
    }

    public void setWantedProfile(WantedProfile wantedProfile) {
        this.wantedProfile = wantedProfile;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public String getInterestCenter() {
        return interestCenter;
    }

    public void setInterestCenter(String interestCenter) {
        this.interestCenter = interestCenter;
    }

    //Avoir toutes les candidats
    public static ArrayList<Candidature> getAll(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "SELECT * FROM candidature";
        ResultSet result = work.executeQuery(req);
        ArrayList<Candidature> candidats = new ArrayList<>();

        while(result.next()) {
            //Candidature(int idCandidature, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCareer, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
            //PersonnalInformation(String name, String firstName, Date birthDate, Adresse adresse, String email, String telephone, Sexe sexe)
            PersonnalInformation pi = new PersonnalInformation(result.getString("name"), result.getString("first_name"), result.getDate("birth_date"), Adresse.getById(conn, result.getInt("id_adresse")), result.getString("email"), "0345091434", Sexe.getById(conn, result.getInt("id_sexe")));
            ProfessionalCareer pc = new ProfessionalCareer(Experience.getById(conn, result.getInt("id_experience")), Career.getCareerCandidat(conn, result.getInt("id_candidature")));
            FormationPath fp = new FormationPath(Diplome.getById(conn, result.getInt("id_diplome")), Formation.getFormationCandidat(conn, result.getInt("id_candidature")));
            Candidature candidat = new Candidature(result.getInt("id_candidature"), WantedProfile.getById(conn, result.getInt("id_wanted_profile")), result.getString("deposit_date"), pi, pc, fp, result.getString("interest_center"), result.getDouble("salary_expectation"), result.getString("self_profile"), result.getString("photo"), result.getString("dossier"), result.getDouble("note"), result.getInt("status"));
            candidats.add(candidat);
        }
        
        return candidats;
    }
    
    //Avoir toutes les candidats dans une service et poste
    public static ArrayList<Candidature> getAllInServicePoste(Connection conn, Service service, WantedProfile wantedProfile) throws Exception {
        Statement work = conn.createStatement();
        String req = "SELECT * FROM candidature id_service="+service.getIdService()+" AND id_wanted_profile="+wantedProfile.getIdWantedProfile();
        ResultSet result = work.executeQuery(req);
        ArrayList<Candidature> candidats = new ArrayList<>();

        while(result.next()) {
            //Candidature(int idCandidature, PersonnalInformation PersonnalInformation, ProfessionalCareer ProfessionalCareer, FormationPath FormationPath, String interestCareer, double SalaryExpectation, String selfProfile, String photo, String dossier, double note, int status) {
            //PersonnalInformation(String name, String firstName, Date birthDate, Adresse adresse, String email, String telephone, Sexe sexe)
            PersonnalInformation pi = new PersonnalInformation(result.getString("name"), result.getString("first_name"), result.getDate("birth_date"), Adresse.getById(conn, result.getInt("id_adresse")), result.getString("email"), "0345091434", Sexe.getById(conn, result.getInt("id_sexe")));
            ProfessionalCareer pc = new ProfessionalCareer(Experience.getById(conn, result.getInt("id_experience")), Career.getCareerCandidat(conn, result.getInt("id_candidature")));
            FormationPath fp = new FormationPath(Diplome.getById(conn, result.getInt("id_diplome")), Formation.getFormationCandidat(conn, result.getInt("id_candidature")));
            Candidature candidat = new Candidature(result.getInt("id_candidature"), WantedProfile.getById(conn, result.getInt("id_wanted_profile")), result.getString("deposit_date"), pi, pc, fp, result.getString("interest_center"), result.getDouble("salary_expectation"), result.getString("self_profile"), result.getString("photo"), result.getString("dossier"), result.getDouble("note"), result.getInt("status"));
            candidats.add(candidat);
        }
        
        return candidats;
    }
    
    //Recuperer une candidature par son id
    public static Candidature getById(Connection conn, Integer idCandidat) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from candidature where id_candidature = "+idCandidat;
        ResultSet result = work.executeQuery(req);
        Candidature candidature = new Candidature();
        
        while(result.next()) {
            candidature.setIdCandidature(idCandidat);
            PersonnalInformation pi = new PersonnalInformation(result.getString("name"), result.getString("first_name"), result.getDate("birh_date"), Adresse.getById(conn, result.getInt("id_adresse")), result.getString("email"), result.getString("telephone"), Sexe.getById(conn, result.getInt("id_sexe")));    
            candidature.setPersonnalInformation(pi);
            ProfessionalCareer pc = new ProfessionalCareer(Experience.getById(conn, result.getInt("id_experience")), Career.getCareerCandidat(conn, result.getInt("id_candidature")));            
            candidature.setProfessionalCareer(pc);
            FormationPath fp = new FormationPath(Diplome.getById(conn, result.getInt("id_diplome")), Formation.getFormationCandidat(conn, result.getInt("id_candidat")));       
            candidature.setFormationPath(fp);
            candidature.setInterestCareer(result.getString("interest_center"));
            candidature.setSalaryExpectation(result.getDouble("salary_expectation"));
            candidature.setSelfProfile(result.getString("self_profile"));
            candidature.setPhoto(result.getString("photo"));
            candidature.setDossier(result.getString("dossier"));
            candidature.setNote(result.getDouble("note"));
            candidature.setStatus(result.getInt("status"));
        }
        
        return candidature;
    }
}
