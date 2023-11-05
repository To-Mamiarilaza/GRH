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

/**
 *
 * @author chalman
 */
public class Province {
    private Integer idProvince;
    private String name;
    
///Getter et setters
    public Integer getIdProvince() {
        return idProvince;
    }
    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
///Contructors
    public Province() {
    }

    public Province(Integer idProvince, String name) {
        this.idProvince = idProvince;
        this.name = name;
    }
 
///Fonctions
   
    // Pour avoir un province par son ID
    public static Province getById(int id) throws Exception {
        Province province = new Province();
        String query = "SELECT * FROM province WHERE id_province = %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                province.setIdProvince(id);
                province.setName(resultset.getString("name"));
            }

            resultset.close();
            statement.close();
            connection.close();

            return province;
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
    public static Province getById(int id, Connection connection) throws Exception {
        Province province = new Province();
        String query = "SELECT * FROM province WHERE id_province = %d";
        query = String.format(query, id);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                province.setIdProvince(id);
                province.setName(resultset.getString("name"));
            }

            resultset.close();
            statement.close();

            return province;
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

    //Avoir tous les provinces
    public static List<Province> getAll() throws Exception {
        List<Province> provinces = new ArrayList<>();
        String query = "SELECT * FROM province";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Province province = new Province(resultset.getInt("id_province"), resultset.getString("name"));
                provinces.add(province);
            }

            resultset.close();
            statement.close();
            connection.close();

            return provinces;
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
