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
public class AdresseNote extends Model {

    @Champs(mapcol = "id", name = "idAdresse")
    private Adresse adresse;
    @Champs
    private Double note;

///Getters and setters
    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

///Constructors
    public AdresseNote() {
    }

    public AdresseNote(Adresse adresse, Double note) {
        this.adresse = adresse;
        this.note = note;
    }

///Fonctions
    //avoir la note correspondant aux adresses selectionnés
    public double getAdresseNote(Connection con, int idWantedProfile, String Adresse) throws Exception {
        boolean b = true;
        double note = 0.0;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String request = "select note from adresse_note where id_wanted_profile=" + idWantedProfile + " and id_adresse=" + new Adresse().getIdByName(Adresse, null);
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

    //inserer adresseNote
    public void createAdresseNote(int id_wanted_profile, int id_adresse, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into adresse_note values (" + id_wanted_profile + ", " + id_adresse + ", " + this.getNote() + ")";
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

    public List<AdresseNote> findBestAdresse(List<Integer> lsindice, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<AdresseNote> bestAdresse = new ArrayList<>();
            Statement s = con.createStatement();
            for (int i = 0; i < lsindice.size(); i++) {
                ResultSet rs = s.executeQuery("SELECT id_wanted_profile, id_adresse, note, adresse, status FROM v_adresse_note where status = 1 and id_wanted_profile = " + lsindice.get(i) + " order by note desc\n"
                        + "limit 1; ");
                while (rs.next()) {
                    Adresse a = new Adresse(rs.getString(4), rs.getInt(5));
                    AdresseNote dn = new AdresseNote(a, rs.getDouble(3));
                    bestAdresse.add(dn);
                }
            }
            return bestAdresse;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }
}
