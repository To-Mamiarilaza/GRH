/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.requis.Service;

/**
 *
 * @author Fy Botas
 */
public class BestCritere {
    List<WantedProfile> listeWantedProfile;
    List<DiplomeNote> listeDiplomeNote;
    List<AdresseNote> listeAdresseNote;
    List<SexeNote> listeSexeNote;
    List<SalaireNote> listeSalaireNote;
    List<ExperienceNote> listeExperienceNote;

    public BestCritere(List<WantedProfile> listeWantedProfile, List<DiplomeNote> listeDiplomeNote, List<AdresseNote> listeAdresseNote, List<SexeNote> listeSexeNote, List<SalaireNote> listeSalaireNote, List<ExperienceNote> listeExperienceNote) {
        this.listeWantedProfile = listeWantedProfile;
        this.listeDiplomeNote = listeDiplomeNote;
        this.listeAdresseNote = listeAdresseNote;
        this.listeSexeNote = listeSexeNote;
        this.listeSalaireNote = listeSalaireNote;
        this.listeExperienceNote = listeExperienceNote;
    }

    public BestCritere() {
    }

    

    public List<BestCritere> getListeProfile(List<Integer> lsindice, Connection con) throws SQLException, Exception{
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<BestCritere> listeProfile = new ArrayList<>();
            List<WantedProfile> listeWantedProfile = new ArrayList<>();
            List<DiplomeNote> listeDiplomeNote = new ArrayList<>();
            List<AdresseNote> listeAdresseNote = new ArrayList<>();
            List<ExperienceNote> listeExperienceNote = new ArrayList<>();
            List<SexeNote> listeSexeNote = new ArrayList<>();
            List<SalaireNote> listeSalaireNote = new ArrayList<>();
            Statement s = con.createStatement();
            for (int i = 0; i < lsindice.size(); i++) {
                String req = "SELECT * FROM v_liste_profile where wanted_profile_status = 1 and id_wanted_profile = " + lsindice.get(i) + " order by  diplome_note desc limit 1";
                ResultSet rs = s.executeQuery(req);
                System.out.println(req);
                while (rs.next()) {
                    listeWantedProfile.add(new WantedProfile(rs.getInt("id_wanted_profile"), rs.getString("poste"), new Service(rs.getInt("id_service"), 1)) );
                    listeDiplomeNote.add(new DiplomeNote(new Diplome(rs.getInt("id_diplome"), rs.getString("diplome"), rs.getInt("diplome_status")), rs.getDouble("diplome_note")));
                    listeAdresseNote.add(new AdresseNote(new Adresse(rs.getInt("id_adresse"), rs.getString("adresse"), rs.getInt("adresse_status")), rs.getDouble("adresse_note")));
                    listeExperienceNote.add(new ExperienceNote(new Experience(rs.getInt("id_experience"), rs.getString("experience"), rs.getInt("experience_status")), rs.getDouble("experience_note")));
                    listeSexeNote.add(new SexeNote(new Sexe(rs.getString("sexe"), rs.getInt("sexe_status")), rs.getDouble("sexe_note")));
                    listeSalaireNote.add(new SalaireNote(new Salaire(rs.getDouble("salaire"), rs.getInt("salaire_status")), rs.getDouble("salaire_note")));
                    BestCritere bc = new BestCritere(listeWantedProfile, listeDiplomeNote, listeAdresseNote, listeSexeNote, listeSalaireNote, listeExperienceNote);
                    listeProfile.add(bc);
                }
            }
            return listeProfile;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    public List<WantedProfile> getListeWantedProfile() {
        return listeWantedProfile;
    }

    public void setListeWantedProfile(List<WantedProfile> listeWantedProfile) {
        this.listeWantedProfile = listeWantedProfile;
    }

    public List<DiplomeNote> getListeDiplomeNote() {
        return listeDiplomeNote;
    }

    public void setListeDiplomeNote(List<DiplomeNote> listeDiplomeNote) {
        this.listeDiplomeNote = listeDiplomeNote;
    }

    public List<AdresseNote> getListeAdresseNote() {
        return listeAdresseNote;
    }

    public void setListeAdresseNote(List<AdresseNote> listeAdresseNote) {
        this.listeAdresseNote = listeAdresseNote;
    }

    public List<SexeNote> getListeSexeNote() {
        return listeSexeNote;
    }

    public void setListeSexeNote(List<SexeNote> listeSexeNote) {
        this.listeSexeNote = listeSexeNote;
    }

    public List<SalaireNote> getListeSalaireNote() {
        return listeSalaireNote;
    }

    public void setListeSalaireNote(List<SalaireNote> listeSalaireNote) {
        this.listeSalaireNote = listeSalaireNote;
    }

    public List<ExperienceNote> getListeExperienceNote() {
        return listeExperienceNote;
    }

    public void setListeExperienceNote(List<ExperienceNote> listeExperienceNote) {
        this.listeExperienceNote = listeExperienceNote;
    }
        
}
