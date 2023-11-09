/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.todo_list;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.employe.Employe;
import model.requis.Service;

/**
 *
 * @author chalman
 */
public class UnderTask {
    private Integer idUnderTask;
    private Employe employe;
    private String underTask;
    private Integer status;
    private Double progression;
    private Timestamp timeRest;
    private ToDo todo;
    
///Getters et setters
    public Integer getIdUnderTask() {
        return idUnderTask;
    }
    public void setIdUnderTask(Integer idUnderTask) {
        this.idUnderTask = idUnderTask;
    }

    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getUnderTask() {
        return underTask;
    }
    public void setUnderTask(String underTask) {
        this.underTask = underTask;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getProgression() {
        return progression;
    }
    public void setProgression(Double progression) {
        this.progression = progression;
    }

    public Timestamp getTimeRest() {
        return timeRest;
    }
    public void setTimeRest(Timestamp timeRest) {
        this.timeRest = timeRest;
    }

    public ToDo getTodo() {
        return todo;
    }
    public void setTodo(ToDo todo) {
        this.todo = todo;
    }
    
///Constructors
    public UnderTask() {
    }

    public UnderTask(Integer idUnderTask, Employe employe, String underTask, Integer status, Double progression, Timestamp timeRest, ToDo todo) {
        this.idUnderTask = idUnderTask;
        this.employe = employe;
        this.underTask = underTask;
        this.status = status;
        this.progression = progression;
        this.timeRest = timeRest;
        this.todo = todo;
    }

///Fonctions
     // Enregistre le sous-tache d'un todo list dans la base de données
    public void save(Connection connection) throws Exception {
        String query = "INSERT INTO under_task ( id_under_task, id_employe, id_todo, under_task, status) VALUES (?, ?, ?, ?, ?)";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, getIdUnderTask());
            statement.setInt(2, getEmploye().getIdEmploye());
            statement.setInt(3, getTodo().getIdTodo());
            statement.setString(4, getUnderTask());
            statement.setInt(5, getStatus());

            statement.executeUpdate();

            statement.close();

            if (closeable) {
                connection.commit();
                connection.close();
            }

        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }
    
    // Pour avoir un sous-tache todo list grace a son ID
    public static UnderTask getById(int id) throws Exception {
        UnderTask underTask = new UnderTask();
        String query = "SELECT * FROM under_task WHERE id_under_task= %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                
            }

            resultset.close();
            statement.close();
            connection.close();

            return underTask;
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

    //Avoir tous les taches d'une service
    public static List<UnderTask> getByToDo(int idToDo) throws Exception {
        List<UnderTask> underTasks = new ArrayList<>();
        String query = "SELECT * FROM under_task WHERE id_todo = %d";
        query = String.format(query, idToDo);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                UnderTask underTask = new UnderTask();
                underTasks.add(underTask);
            }

            resultset.close();
            statement.close();
            connection.close();

            return underTasks;
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
    
    //Modifie le todo list
    public void update(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE under_task SET id_employe="+this.getEmploye().getIdEmploye()+", id_todo='"+this.getTodo().getIdTodo()+"', under_task='"+this.getUnderTask()+"', status="+this.getStatus()+" WHERE id_under_task="+this.getUnderTask();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Supprimer un todolist
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from under_task WHERE id_under_task="+this.getIdUnderTask();
        work.execute(req);
        conn.setAutoCommit(true);
    }
}
