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
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Unity;
import model.gestionBesoin.WorkLoad;

/**
 *
 * @author Chalman
 */
public class Adresse {
    private Integer idAdresse;
    private String adresse;
    private Integer status;

///Getters and setters
    public Integer getIdAdresse() {
        return idAdresse;
    }
    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

///Constructors
    public Adresse() {
    }

    public Adresse(String adresse, Integer status) {
        this.adresse = adresse;
        this.status = status;
    }

    public Adresse(Integer idAdresse, String adresse, Integer status) {
        this.idAdresse = idAdresse;
        this.adresse = adresse;
        this.status = status;
    }

///Fonctions
    //avoir l'idCorrespondant au adresse choisi
    public int getIdByName(String adresse, Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            int id = 0;
            String request = " select id_adresse from adresse where status = 1 and adresse = '" + adresse +"'";
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

    public List<Adresse> getAllAdresse(Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            String request = " select * from adresse where status = 1 ";
            List<Adresse> listeAdresse = new ArrayList<>();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(request);
            while (rs.next()) {
                Adresse a = new Adresse(rs.getString(2), rs.getInt(3));
                listeAdresse.add(a);
            }
            return listeAdresse;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    //Recuperer une adresse par son id
    public static Adresse getById(Connection conn, Integer idAdresse) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from adresse where id_adresse = "+idAdresse;
        ResultSet result = work.executeQuery(req);
        Adresse adresse = new Adresse();
        int i = 1;
        while(result.next()) {
            adresse.setIdAdresse(result.getInt("id_adresse"));
            adresse.setAdresse(result.getString("adresse"));
            adresse.setStatus(result.getInt("status"));
        }
        
        return adresse;
    }
    
}
