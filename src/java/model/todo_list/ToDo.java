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
import model.requis.Service;

/**
 *
 * @author chalman
 */
public class ToDo {
    private Integer idTodo;
    private Service service;
    private String toDo;
    private List<UnderTask> underTask;
    private Timestamp deadLine;
    private Timestamp startDate;
    private Integer status;
    
///Getters et setters
    public Integer getIdTodo() {
        return idTodo;
    }
    public void setIdTodo(Integer id_todo) {
        this.idTodo = id_todo;
    }

    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }

    public String getToDo() {
        return toDo;
    }
    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public List<UnderTask> getUnderTask() {
        return underTask;
    }
    public void setUnderTask(List<UnderTask> underTask) {
        this.underTask = underTask;
    }

    public Timestamp getDeadLine() {
        return deadLine;
    }
    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }

    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
 ///Constructors
    public ToDo() {
    }

    public ToDo(Integer idTodo, Service service, String toDo, List<UnderTask> underTask, Timestamp deadLine, Timestamp startDate, Integer status) {
        this.idTodo = idTodo;
        this.service = service;
        this.toDo = toDo;
        this.underTask = underTask;
        this.deadLine = deadLine;
        this.startDate = startDate;
        this.status = status;
    }
    
///Fonctions
     // Enregistre le todo list dans la base de données
    public void save(Connection connection) throws Exception {
        String query = "INSERT INTO todo ( id_service, todo, start_date, deadline, status) VALUES (?, ?, now(), ?, ?)";

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
            statement.setInt(1, getService().getIdService());
            statement.setString(2, getToDo());
            statement.setTimestamp(3, getStartDate());
            statement.setTimestamp(4, getDeadLine());
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

    // Pour avoir un todo list gracde a son ID
    public static ToDo getById(int id) throws Exception {
        ToDo todo = new ToDo();
        String query = "SELECT * FROM todo WHERE id_todo= %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                todo.setIdTodo(resultset.getInt("id_todo"));
                todo.setService(Service.getById(connection, resultset.getInt("id_service")));
                todo.setToDo(resultset.getString("todo"));
                todo.setStartDate(resultset.getTimestamp("start_date"));
                todo.setDeadLine(resultset.getTimestamp("deadline"));
                todo.setStatus(resultset.getInt("status"));
            }

            resultset.close();
            statement.close();
            connection.close();

            return todo;
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
    public static List<ToDo> getByService(int idService) throws Exception {
        List<ToDo> todos = new ArrayList<>();
        String query = "SELECT * FROM todo WHERE id_service = %d";
        query = String.format(query, idService);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                ToDo todo = new ToDo(resultset.getInt("id_todo"), Service.getById(connection, resultset.getInt("id_service")), resultset.getString("todo"), new ArrayList<UnderTask>(), resultset.getTimestamp("deadline"), resultset.getTimestamp("start_date"), resultset.getInt("status"));
                todos.add(todo);
            }

            resultset.close();
            statement.close();
            connection.close();

            return todos;
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
        String req = "UPDATE todo SET id_service="+this.getService().getIdService()+", todo='"+this.getToDo()+"', start_date='"+this.getStartDate()+"', deadline='"+this.getDeadLine()+"', status="+this.getStatus()+" WHERE id_todo="+this.getIdTodo();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Supprimer un todolist
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from todo WHERE id_todo="+this.getIdTodo();
        work.execute(req);
        conn.setAutoCommit(true);
    }
}
