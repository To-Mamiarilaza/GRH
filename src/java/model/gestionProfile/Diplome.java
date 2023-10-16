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
import model.requis.Service;

/**
 *
 * @author Chalman
 */
public class Diplome extends Model {
    private Integer idDiplome;
    private String diplome;
    private Integer status;

///Getters and setters
    public Integer getIdDiplome() {
        return idDiplome;
    }
    public void setIdDiplome(Integer id_diplome) {
        this.idDiplome = id_diplome;
    }
    
    
    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

///Constructors
    public Diplome() {
    }

    public Diplome(String diplome, Integer status) {
        this.diplome = diplome;
        this.status = status;
    }

    public Diplome(Integer id_diplome, String diplome, Integer status) {
        this.idDiplome = id_diplome;
        this.diplome = diplome;
        this.status = status;
    }

///Fonctions
    
    //avoir l'idCorrespondant au diplome choisi
    public int getIdByName(String diplome, Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            int id = 0;
            String request = " select id_diplome from diplome where status = 1 and diplome = '" + diplome + "'";
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

    public List<Diplome> getAllDiplome(Connection con) throws Exception {
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
            }
            String request = " select * from diplome where status = 1 ";
            List<Diplome> listeDiplome = new ArrayList<>();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(request);
            while (rs.next()) {
                Diplome d = new Diplome(rs.getString(2), rs.getInt(3));
                listeDiplome.add(d);
            }
            return listeDiplome;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    //Recuperer un diplome par son id
    public static Diplome getById(Connection conn, Integer idDiplome) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from diplome where id_diplome = "+idDiplome;
        ResultSet result = work.executeQuery(req);
        Diplome diplome = new Diplome();
        while(result.next()) {
            diplome.setIdDiplome(result.getInt("id_diplome"));
            diplome.setDiplome(result.getString("diplome"));
            diplome.setStatus(result.getInt("status"));
        }
        
        return diplome;
    }
}
