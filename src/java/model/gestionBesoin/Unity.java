/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionBesoin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Chalman
 */
public class Unity {
    private Integer idUnity;
    private String unity;
    private Integer status;
    
///Getters and setters
    public Integer getIdUnity() {
        return idUnity;
    }
    public void setIdUnity(Integer idUnity) {
        this.idUnity = idUnity;
    }
    
    
    public String getUnity() {
        return unity;
    }
    public void setUnity(String unity) {
        this.unity = unity;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

///Constructors
    public Unity() {
    }

    public Unity(Integer idUnity, String unity, Integer status) {
        this.idUnity = idUnity;
        this.unity = unity;
        this.status = status;
    }
    
    public Unity(String unity, Integer status) {
        this.unity = unity;
        this.status = status;
    }

///Fonctions
     //Avoir tous les unites
    public static ArrayList<Unity> getAll(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "select * from unity";
        ResultSet result = work.executeQuery(req);
        ArrayList<Unity> unitys = new ArrayList<>();
        int i = 1;
        while(result.next()) {
            Unity unity = new Unity(result.getInt(1), result.getString(2), result.getInt(3));
            unitys.add(unity);
        }
        
        return unitys;
    }
    
    //Recuperer une unity par son id
    public static Unity getById(Connection conn, Integer idUnity) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from unity where id_unity = "+idUnity;
        ResultSet result = work.executeQuery(req);
        Unity unity = new Unity();
        int i = 1;
        while(result.next()) {
            unity.setIdUnity(result.getInt(1));
            unity.setUnity(result.getString(2));
            unity.setStatus(result.getInt(3));
        }
        
        return unity;
    }
    
     //Recuperer une unity par son nom
    public static Unity getByName(Connection conn, String unityValue) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from unity where unity = '"+unityValue+"'";
        ResultSet result = work.executeQuery(req);
        Unity unity = new Unity();
        int i = 1;
        while(result.next()) {
            unity.setIdUnity(result.getInt(1));
            unity.setUnity(result.getString(2));
            unity.setStatus(result.getInt(3));
        }
        
        return unity;
    }
    
}