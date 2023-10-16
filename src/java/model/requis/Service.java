/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.requis;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Chalman
 */
public class Service {
    private Integer idService;
    private String service;
    private String  fonction;
    private Date creationDate;
    private Integer status;

///Getters and setters
    public Integer getIdService() {
        return idService;
    }
    public void setIdService(Integer idService) {    
        this.idService = idService;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

///Constructors
    public Service() {
    }

    public Service(Integer idService, Integer status) {
        this.idService = idService;
        this.status = status;
    }

    public Service(Integer idService, String service, String fonction, Date creationDate, Integer status) {
        this.idService = idService;
        this.service = service;
        this.fonction = fonction;
        this.creationDate = creationDate;
        this.status = status;
    }

///Fonctions de la classe
    //Avoir tous les services
    public static ArrayList<Service> getAll(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "select * from service";
        ResultSet result = work.executeQuery(req);
        ArrayList<Service> services = new ArrayList<>();
        int i = 1;
        while(result.next()) {
            Service service = new Service(result.getInt(1), result.getString(2), result.getString(3), result.getDate(4), result.getInt(5));
            services.add(service);
        }
        
        return services;
    }

    //Recuperer une service par son id
    public static Service getById(Connection conn, Integer idService) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from service where id_service = "+idService;
        ResultSet result = work.executeQuery(req);
        Service service = new Service();
        int i = 1;
        while(result.next()) {
            service.setIdService(result.getInt(1));
            service.setService(result.getString(2));
            service.setFonction(result.getString(3));
            service.setCreationDate(result.getDate(4));
            service.setStatus(result.getInt(5));
        }
        
        return service;
    }
}