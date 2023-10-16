/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chalman
 */
public class Sexe {
    private String sexe;
    private Integer status;

///Getters and setters
    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

///Constructors
    public Sexe() {
    }

    public Sexe(String sexe, Integer status) {
        this.sexe = sexe;
        this.status = status;
    }

///Fonctions
    
    public String getSexeString(int id){
        if(id == 1) return "Homme";
        else return "Femme";
    }
    //avoir l'idCorrespondant au sexe choisi
    public int getIdByName(String sexe, Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            int id = 0;
            String request = " select id_sexe from sexe where status = 1 and sexe = '" + sexe + "'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(request);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Sexe> getAllSexe(Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            String request = " select * from sexe where status = 1";
            List<Sexe> listeSexe = new ArrayList<>();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(request);
            while (rs.next()) {
                Sexe se = new Sexe(rs.getString(2), rs.getInt(3));
                listeSexe.add(se);
            }
            return listeSexe;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    
    //Recuperer une sexe par son id
    public static Sexe getById(Connection conn, Integer idSexe) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from sexe where id_sexe = "+idSexe;
        ResultSet result = work.executeQuery(req);
        Sexe sexe = new Sexe();
        while(result.next()) {
            sexe.setSexe(result.getString("sexe"));
            sexe.setStatus(result.getInt("status"));
        }
        
        return sexe;
    }
}
