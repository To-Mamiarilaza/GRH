/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.requis;

import framework.database.annotation.Champs;
import model.*;
import java.sql.*;
import framework.database.utilitaire.GConnection;
import java.util.ArrayList;


/**
 *
 * @author Chalman
 */
public class User extends Model {
    private int idUser;
    private String name;
    private Service service;
    private String username;
    private String password;
    private String mail;
    private Integer status;
    
    public int getIdUser() {    
        return idUser;
    }

///Getters and setters
    public void setIdUser(int idUser) {
        this.idUser = idUser;    
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {   
        this.status = status;
    }

///Constructors
    public User() {
    }

    public User(String name, Service service, String username, String password, String mail, Integer status) {
        this.name = name;
        this.service = service;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.status = status;
    } 
    
///Fonctions de la classe
    //Creer un user
    public void create(Connection con) throws Exception {
        boolean valid = true ;
        try{
            if (con == null){
                con = GConnection.getSimpleConnection();
                valid = false ;
            }
            int id = this.sequence("idUserSeq",con);
            this.setId(id);
            this.create(con);
        }catch(Exception exe){
            con.rollback();
            throw exe ;
        }finally {
            if(!valid){
                con.close();
            }
        }
    }
    
    // vérifier les champs depuis l'affichage
    public static void checkLoginForm(String username, String password) throws Exception {
         // check form value
        if (username == null || username.trim().equals("")) {
            throw new Exception("Le nom d'utilisateur ne doit pas être vide ou null");
        }
        
        if (password == null || password.trim().equals("")) {
            throw new Exception("Le mot de passe ne doit pas être vide ou null");
        }
    }
    
    // Fonction pour authentifier un utilisateur
    public static User checkLogin(String username, String password) throws Exception {
        checkLoginForm(username, password);
        
        String query = "SELECT * FROM v_user_service WHERE username = '%s' and password = '%s'";
        query = String.format(query, username, password);
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            
            if (resultset.next()) {
                User user = new User();
                user.setId(resultset.getInt("id_utilisateur"));
                user.setUsername(resultset.getString("username"));
                user.setMail(resultset.getString("mail"));
                //user.setPassword(resultset.getString("password"));
                
                Service service = new Service();
                service.setIdService(resultset.getInt("id_service"));
                service.setService(resultset.getString("service"));
                
                user.setService(service);
                
                System.out.println("User : " + user.getUsername());
                return user;
            }
            
            throw new Exception("Vérifier votre nom d'utilisateur ou mot de passe !");
        } catch (Exception e) {
            if (resultset != null) resultset.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            throw e;
        }
    }
    
    //Avoir tous les users
    public ArrayList<User> getAll(Connection conn)  throws Exception { 
        return this.findAll(conn);
    }
    
    //Recuperer un user par son id
    public User getById(Connection conn, Integer idUser) throws Exception {
        return this.findOneWhere(conn, "id ="+idUser);
    }
    
    public static void main(String[] args) {
        try {
            User.checkLogin("Andry Info", "Andry111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}