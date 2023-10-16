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
import java.util.List;

/**
 *
 * @author Chalman
 */
public class Task {
    private Integer idTask;
    private Besoin besoin;
    private String task;
    private Integer status;
    
///Getters and setters
    public Integer getIdTask() {
        return idTask;
    }
    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }
    
    public Besoin getBesoin() {    
        return besoin;
    }
    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {   
        this.status = status;
    }

///Constructors
    public Task() {
    }
    
    public Task(String task, Integer status) {
        this.task = task;
        this.status = status;
    }
    public Task(Besoin besoin, String task, Integer status) {
        this.besoin = besoin;
        this.task = task;
        this.status = status;
    }

    public Task(Integer idTask, Besoin besoin, String task, Integer status) {
        this.idTask = idTask;
        this.besoin = besoin;
        this.task = task;
        this.status = status;
    }
    
///Fonctions
    //Creer une tache
    public void create(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "INSERT INTO task (id_task, id_besoin, task, status) VALUES (DEFAULT,"+this.getBesoin().getIdBesoin()+",'"+this.getTask()+"', "+this.getStatus()+")";
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Avoir toutes les taches
    public static ArrayList<Task> getAll(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "select * from task";
        ResultSet result = work.executeQuery(req);
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 1;
        while(result.next()) {
            Task task = new Task(result.getInt(1), Besoin.getById(conn,result.getInt(2)), result.getString(3), result.getInt(4));
            tasks.add(task);
        }
        
        return tasks;
    }
    
    //Avoir toutes les taches d'une besoin
    public static ArrayList<Task> getAllTaskBesoin(Connection conn, Besoin besoin)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM task WHERE id_besoin = "+besoin.getIdBesoin();
        ResultSet result = work.executeQuery(req);
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 1;
        while(result.next()) {
            Task task = new Task(result.getInt(1), Besoin.getById(conn,result.getInt(2)), result.getString(3), result.getInt(4));
            tasks.add(task);
        }
        
        return tasks;
    }
    
    //Recuperer une tache par son id
    public static Task getById(Connection conn, Integer idTask) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from task where id_task = "+idTask;
        ResultSet result = work.executeQuery(req);
        Task task = new Task();
        int i = 1;
        while(result.next()) {
            task.setIdTask(result.getInt(1));
            task.setBesoin(Besoin.getById(conn, result.getInt(2)));
            task.setTask(result.getString(3));
            task.setStatus(result.getInt(4));
        }
        
        return task;
    }
    
    //Modifier un tache
    public void update(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE task SET besoin="+this.getBesoin().getIdBesoin()+", description='"+this.getTask()+"', status="+this.getStatus()+" WHERE id_task="+this.getIdTask();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Supprimer une tache
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from task WHERE id_task="+this.getIdTask();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Inserer une liste des taches
    public static void insertListTask(Connection conn, List<Task> listTask, Besoin besoin) throws Exception {
        for(int i = 0; i < listTask.size(); i++) {
            listTask.get(i).setBesoin(besoin);
            listTask.get(i).create(conn);
        }
    }
}
