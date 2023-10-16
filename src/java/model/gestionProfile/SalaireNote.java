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
public class SalaireNote extends Model {

    @Champs(mapcol = "id", name = "idSalaire")
    private Salaire salaire;
    @Champs
    private Double note;

///Getters and setters
    public Salaire getSalaire() {
        return salaire;
    }

    public void setSalaire(Salaire salaire) {
        this.salaire = salaire;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

///Constructors
    public SalaireNote() {
    }

    public SalaireNote(Salaire salaire, Double note) {
        this.salaire = salaire;
        this.note = note;
    }

///Fonctions
    //avoir la note correspondant aux diplomes selectionnés
    public double getSalaireNote(Connection con, int idWantedProfile, double Salaire) throws Exception {
        boolean b = true;
        double note = 0.0;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String request = "select note from salaire_note where id_wanted_profile=" + idWantedProfile + " and id_salaire=" + new Salaire().getIdByName(Salaire, null);
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

    //inserer salaireNote
    public void createSalaireNote(int id_wanted_profile, int id_salaire, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into salaire_note values (" + id_wanted_profile + ", " + id_salaire + ", " + this.getNote() + ")";
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

    public List<SalaireNote> findBestSalaire(List<Integer> lsindice, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<SalaireNote> bestSalaire = new ArrayList<>();
            Statement s = con.createStatement();
            for (int i = 0; i < lsindice.size(); i++) {
                ResultSet rs = s.executeQuery("SELECT id_wanted_profile, id_salaire, note, Salaire, status FROM v_salaire_note where status = 1 and id_wanted_profile = " + lsindice.get(i) + " order by note desc\n"
                        + "limit 1; ");
                while (rs.next()) {
                    Salaire a = new Salaire(rs.getDouble(4), rs.getInt(5));
                    SalaireNote dn = new SalaireNote(a, rs.getDouble(3));
                    bestSalaire.add(dn);
                }
            }
            return bestSalaire;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

}
