/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import framework.database.annotation.Champs;
import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model;

/**
 *
 * @author Chalman
 */
public class ExperienceNote extends Model {

    @Champs(mapcol = "id", name = "idExperience")
    private Experience experience;
    @Champs
    private Double note;

///Getters and setters
    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
///Constructors

    public ExperienceNote() {
    }

    public ExperienceNote(Experience experience, Double note) {
        this.experience = experience;
        this.note = note;
    }

///Fonctions
    //avoir la note correspondant aux experiences selectionnés
    public double getExperienceNote(Connection con, int idWantedProfile, String experiences) throws Exception {
        boolean b = true;
        double note = 0.0;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String request = "select note from experience_note where id_wanted_profile=" + idWantedProfile + " and id_experience=" + new Experience().getIdByName(experiences, null);
            System.out.println(request);

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(request);
            if (rs.next()) {
                note = rs.getDouble(1);
            } else {
                note = 0.0;
            }
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
        return note;
    }

    //inserer experienceNote
    public void createExperienceNote(int id_wanted_profile, int id_experience, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into experience_note values (" + id_wanted_profile + ", " + id_experience + ", " + this.getNote() + ")";
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

    public List<ExperienceNote> findBestExperience(List<Integer> lsindice, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<ExperienceNote> bestExperience = new ArrayList<>();
            Statement s = con.createStatement();
            for (int i = 0; i < lsindice.size(); i++) {
                ResultSet rs = s.executeQuery("SELECT id_wanted_profile, id_experience, note, Experience, status FROM v_experience_note where status = 1 and id_wanted_profile = " + lsindice.get(i) + " order by note desc\n"
                        + "limit 1; ");
                while (rs.next()) {
                    Experience a = new Experience(rs.getString(4), rs.getInt(5));
                    ExperienceNote dn = new ExperienceNote(a, rs.getDouble(3));
                    bestExperience.add(dn);
                }
            }
            return bestExperience;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }
}
