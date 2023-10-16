/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.candidature;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class Formation {
/// field

    int year;
    String diplome;
    String school;

    public void create(Connection con, int idCandidature) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into formation_base values (DEFAULT, "+ idCandidature +", "+ this.getYear() +", '"+ this.getDiplome() +"', '"+ this.getSchool() +"')";
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

/// getter and setter
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

/// constructor
    public Formation(int year, String diplome, String school) {
        this.year = year;
        this.diplome = diplome;
        this.school = school;
    }

    public Formation() {
    }

///fonctions
    //Avoir tous les formations
    public static ArrayList<Formation> getFormationCandidat(Connection conn, Integer idCandidat)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM formation_base WHERE id_candidature="+idCandidat;
        ResultSet result = work.executeQuery(req);
        ArrayList<Formation> formations = new ArrayList<>();

        while(result.next()) {
            Formation formation = new Formation(result.getInt("year"), result.getString("diplome"), result.getString("school"));
            formations.add(formation);
        }
        
        return formations;
    }
}
