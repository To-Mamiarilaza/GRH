/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.embauchement;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.gestionProfile.Adresse;
import model.requis.Service;

/**
 *
 * @author chalman
 */
public class WorkLocation {
    private Integer idWorkLocation;
    private String name;
    private Adresse adress;
    
///Getter et setter
    public Integer getIdWorkLocation() {
        return idWorkLocation;
    }

    public void setIdWorkLocation(Integer idWorkLocation) {
        this.idWorkLocation = idWorkLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adresse getAdress() {
        return adress;
    }

    public void setAdress(Adresse adress) {
        this.adress = adress;
    }

///Contructors
    public WorkLocation() {
    }

    public WorkLocation(Integer idWorkLocation, String name, Adresse adress) {
        this.idWorkLocation = idWorkLocation;
        this.name = name;
        this.adress = adress;
    }
    
///Fonctions de classe
    
    
    //Avoir tous les provinces
    public static ArrayList<WorkLocation> getAll() throws Exception {
        ArrayList<WorkLocation> workLocations = new ArrayList<>();
        String query = "SELECT * FROM work_location";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                WorkLocation workLocation = new WorkLocation(resultset.getInt("id_work_location"), resultset.getString("name"),Adresse.getById(connection, resultset.getInt("id_adress")));
                workLocations.add(workLocation);
            }

            resultset.close();
            statement.close();
            connection.close();

            return workLocations;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }
    
    // Pour avoir un province par son ID
    public static WorkLocation getById(int idWorkLocation) throws Exception {
        Province province = new Province();
        String query = "select * from work_location where id_work_location = %d";
        query = String.format(query, idWorkLocation);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        WorkLocation workLocation = new WorkLocation();;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                workLocation.setIdWorkLocation(resultset.getInt("id_work_location"));
                workLocation.setName( resultset.getString("name"));
                workLocation.setAdress(Adresse.getById(connection,resultset.getInt("id_adress")));
            }

            resultset.close();
            statement.close();
            connection.close();

            return workLocation;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }
    
    public static WorkLocation getById(int idWorkLocation, Connection connection) throws Exception {
        Province province = new Province();
        String query = "select * from work_location where id_work_location = %d";
        query = String.format(query, idWorkLocation);

        Statement statement = null;
        ResultSet resultset = null;
        WorkLocation workLocation = new WorkLocation();;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                workLocation.setIdWorkLocation(resultset.getInt("id_work_location"));
                workLocation.setName( resultset.getString("name"));
                workLocation.setAdress(Adresse.getById(connection,resultset.getInt("id_adress")));
            }

            resultset.close();
            statement.close();

            return workLocation;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }
}
