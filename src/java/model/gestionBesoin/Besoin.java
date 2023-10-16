/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionBesoin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.requis.Service;

/**
 *
 * @author Chalman
 */
public class Besoin extends Model {
    private Integer idBesoin;
    private Service service;
    private LocalDate creationDate;
    private String description;
    private List<Task> tasks = new ArrayList<>();
    private List<WorkLoad> workLoad = new ArrayList<>();
    private Integer status;
    
///Getters and setters
    public Integer getIdBesoin() {
        return idBesoin;
    }
    public void setIdBesoin(Integer idBesoin) {
        this.idBesoin = idBesoin;
    }
    
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setCreationDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateCreation = LocalDate.parse(date, formatter);
        this.setCreationDate(dateCreation);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<WorkLoad> getWorkLoad() {
        return workLoad;
    }
    public void setWorkLoad(List<WorkLoad> workLoad) {
        this.workLoad = workLoad;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
   
///Constructors
    public Besoin (){
    }

    public Besoin(Integer idBesoin, Service service, LocalDate creationDate, String description, Integer status) {
        this.idBesoin = idBesoin;
        this.service = service;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
    }
    
    public Besoin(Service service, LocalDate creationDate, String description, Integer status) {
        this.service = service;
        this.creationDate = creationDate;
        this.description = description;
        this.status = status;
    }

///Fonctions de la classe
    //Creer un besoin
    public void create(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "INSERT INTO besoin (id_besoin, id_service, creation_date, description, status) VALUES (DEFAULT,"+this.getService().getIdService()+",'"+this.getCreationDate()+"','"+this.getDescription()+"',"+this.getStatus()+")";
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Avoir tous les besoins
    public static ArrayList<Besoin> getBesoinsService(Connection conn, Service service)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM besoin WHERE id_service="+service.getIdService()+" AND status!=0";
        System.out.println("Req : "+req);
        ResultSet result = work.executeQuery(req);
        ArrayList<Besoin> besoins = new ArrayList<>();
        int i = 1;
        while(result.next()) {
            Besoin besoin = new Besoin(result.getInt(1), Service.getById(conn,result.getInt(2)), Besoin.getLocalDate(result.getString(3)), result.getString(4), result.getInt(5));
            besoins.add(besoin);
        }
        
        return besoins;
    }
    
    //Avoir tous les besoins selon le status 
    public static ArrayList<Besoin> getBesoinByStatus(Connection conn, String status, Service service)  throws Exception { 
        if(status == null) {
            status = "1";
        }
        Statement work = conn.createStatement();
        String req = "SELECT * FROM besoin WHERE status = "+status+ " AND id_service="+service.getIdService();
        ResultSet result = work.executeQuery(req);
        ArrayList<Besoin> besoins = new ArrayList<>();
        while(result.next()) {
            Besoin besoin = new Besoin(result.getInt(1), Service.getById(conn,result.getInt(2)), Besoin.getLocalDate(result.getString(3)), result.getString(4), result.getInt(5));
            besoins.add(besoin);
        }
        
        return besoins;
    }
    
    //Recuperer une service par son id
    public static Besoin getById(Connection conn, Integer idBesoin) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from besoin where id_besoin = "+idBesoin;
        ResultSet result = work.executeQuery(req);
        Besoin besoin = new Besoin();
        int i = 1;
        while(result.next()) {
            besoin.setIdBesoin(result.getInt(1));
            besoin.setService(Service.getById(conn, result.getInt(2)));
            besoin.setCreationDate(result.getString(3));
            besoin.setDescription(result.getString(4));
            besoin.setStatus(result.getInt(5));
        }
        
        return besoin;
    }
    
    //Modifier un besoin
    public void update(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE besoin SET id_service="+this.getService().getIdService()+", creation_date="+this.getCreationDate()+", description='"+this.getDescription()+"', status="+this.getStatus()+" WHERE id_besoin="+this.getIdBesoin();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //refuser un besoin
    public void refused(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE besoin SET status=5 WHERE id_besoin="+this.getIdBesoin();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //refuser un besoin
    public void validate(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE besoin SET status=10 WHERE id_besoin="+this.getIdBesoin();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Recuperer le dernier besoin insere
    public static Besoin getLastBesoin(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "SELECT * FROM besoin ORDER BY id_besoin DESC LIMIT 1";
        ResultSet result = work.executeQuery(req);
        Besoin besoin = new Besoin();
        int i = 1;
        while(result.next()) {
            besoin.setIdBesoin(result.getInt(1));
            besoin.setService(Service.getById(conn, result.getInt(2)));
            besoin.setCreationDate(result.getString(3));
            besoin.setDescription(result.getString(4));
            besoin.setStatus(result.getInt(5));
        }
        
        return besoin;
    }
    
    //Supprimer un besoin
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from besoin WHERE id_besoin="+this.getIdBesoin();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Ajouter une tache dans la liste des taches
    public void addTask(Task task) {
        this.getTasks().add(task);
    }
    
    //Supprimer une tache
    public void delTask(Task task) {
        ArrayList<Task> tasks = new ArrayList<>();
        for(int i = 0; i < this.getTasks().size(); i++) {
            if(!this.getTasks().get(i).getTask().equalsIgnoreCase(task.getTask())) {
                tasks.add(this.getTasks().get(i));
            }
        }
        this.setTasks(tasks);
    }
    
    //Ajouter une charge de travaiil dans la liste des charges de travail
    public void addWorkLoad(WorkLoad workLoad) {
        this.getWorkLoad().add(workLoad);
    }
    
    //Supprimer une charge de travail
    public void delWorkLoad(WorkLoad workLoad) {
        ArrayList<WorkLoad> workLoads = new ArrayList<>();
    
        for(int i = 0; i < this.getWorkLoad().size(); i++) {
            if(!this.getWorkLoad().get(i).getWantedProfile().getPoste().equalsIgnoreCase(workLoad.getWantedProfile().getPoste())) {
                workLoads.add(this.getWorkLoad().get(i));
            }
        }
        this.setWorkLoad(workLoads);
    }
    
    //Transformer un string en localDate
    public static LocalDate getLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateCreation = LocalDate.parse(date, formatter);
        
        return dateCreation;
    }
}