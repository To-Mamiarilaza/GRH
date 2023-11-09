/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todo_list;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fy Botas
 */
public class EmployeTask {

    int id_under_task;
    String task;
    String username;
    String under_task;
    Timestamp deadline;
    Time estimed_time;
    int todo_task_states;
    int under_task_states;

    //changer le status des sous taches si il est supprimer alors son status est 2
    public void deleteUnderTask(int idUnderTask, Connection conn) throws SQLException, Exception {
        String query = "update under_task set status=0 where id_under_task=%d";
        query = String.format(query, idUnderTask);
        System.out.println(query);

        Statement work = conn.createStatement();
        work.execute(query);
        conn.setAutoCommit(true);
    }

    //changer l'etat des sous taches
    public void updateUnderTaskState(int idUnderTask, int states, Connection conn) throws SQLException, Exception {
        String query = "update under_task set id_states=%d where id_under_task=%d";
        query = String.format(query, states, idUnderTask);
        System.out.println(query);

        Statement work = conn.createStatement();
        work.execute(query);
        conn.setAutoCommit(true);
    }

    //filter les sous_taches par son id et son etat
    public List<EmployeTask> FilterTask(int idUnderTask, int states) throws Exception {
        List<EmployeTask> listeEmployeTask = new ArrayList<>();
        String query = "SELECT * FROM v_employe_task WHERE id_under_task = %d and under_task_states = %d and todo_status = 1 and under_task_status = 1"; //l'indice 1 signifie que la tache et le sous tache n'est pas encore supprimer ou terminer
        query = String.format(query, idUnderTask, states);
        System.out.println(query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                EmployeTask EmployeTask = new EmployeTask();
                EmployeTask.setId_under_task(resultset.getInt("id_under_task"));
                EmployeTask.setTask(resultset.getString("todo"));
                EmployeTask.setUsername(resultset.getString("username"));
                EmployeTask.setUnder_task(resultset.getString("under_task"));
                EmployeTask.setDeadline(resultset.getTimestamp("deadline"));
                EmployeTask.setEstimed_time(resultset.getTime("estimed_time"));
                EmployeTask.setTodo_task_states(resultset.getInt("todo_states"));
                EmployeTask.setUnder_task_states(resultset.getInt("under_task_states"));
                listeEmployeTask.add(EmployeTask);
            }

            resultset.close();
            statement.close();
            connection.close();

            return listeEmployeTask;
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

    //avoir la liste des taches de chaque employe
    public List<EmployeTask> getTaskByIdEmploye(int idEmp) throws Exception {
        List<EmployeTask> listeEmployeTask = new ArrayList<>();
        String query = "SELECT * FROM v_employe_task WHERE id_Employe = %d and todo_status = 1 and under_task_status = 1"; //l'indice 1 signifie que la tache et le sous tache n'est pas encore supprimer ou terminer
        query = String.format(query, idEmp);
        System.out.println(query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                EmployeTask EmployeTask = new EmployeTask();
                EmployeTask.setId_under_task(resultset.getInt("id_under_task"));
                EmployeTask.setTask(resultset.getString("todo"));
                EmployeTask.setUsername(resultset.getString("username"));
                EmployeTask.setUnder_task(resultset.getString("under_task"));
                EmployeTask.setDeadline(resultset.getTimestamp("deadline"));
                EmployeTask.setEstimed_time(resultset.getTime("estimed_time"));
                EmployeTask.setTodo_task_states(resultset.getInt("todo_states"));
                EmployeTask.setUnder_task_states(resultset.getInt("under_task_states"));
                listeEmployeTask.add(EmployeTask);
            }

            resultset.close();
            statement.close();
            connection.close();

            return listeEmployeTask;
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

    // avoir toutes les sous_taches
    public List<EmployeTask> getAll() throws Exception {
        List<EmployeTask> listeEmployeTask = new ArrayList<>();
        String query = "SELECT * FROM v_employe_task WHERE todo_status = 1 and under_task_status = 1"; //l'indice 1 signifie que la tache et le sous tache n'est pas encore supprimer ou terminer
        System.out.println(query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                EmployeTask EmployeTask = new EmployeTask();
                EmployeTask.setId_under_task(resultset.getInt("id_under_task"));
                EmployeTask.setTask(resultset.getString("todo"));
                EmployeTask.setUsername(resultset.getString("username"));
                EmployeTask.setUnder_task(resultset.getString("under_task"));
                EmployeTask.setDeadline(resultset.getTimestamp("deadline"));
                EmployeTask.setEstimed_time(resultset.getTime("estimed_time"));
                EmployeTask.setTodo_task_states(resultset.getInt("todo_states"));
                EmployeTask.setUnder_task_states(resultset.getInt("under_task_states"));
                listeEmployeTask.add(EmployeTask);
            }

            resultset.close();
            statement.close();
            connection.close();

            return listeEmployeTask;
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

    //changer l'etat du sous_tache en string
    public String statesToWords(int states) {
        switch (states) {
            case 1:
                return "<p style='color:red;'> intact </p>";
            case 2:
                return "<p style='color:blue;'> en cours </p>";
            case 3:
                return "<p style='color:green;'> fini </p>";
            default:
                return "<p style='color:yellow;'> etat inconnue </p>";
        }
    }

    public EmployeTask(int id_under_task, String task, String username, String under_task, Timestamp deadline, Time estimed_time, int todo_task_states, int under_task_states) {
        this.id_under_task = id_under_task;
        this.task = task;
        this.username = username;
        this.under_task = under_task;
        this.deadline = deadline;
        this.estimed_time = estimed_time;
        this.todo_task_states = todo_task_states;
        this.under_task_states = under_task_states;
    }

    public EmployeTask() {
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUnder_task() {
        return under_task;
    }

    public void setUnder_task(String under_task) {
        this.under_task = under_task;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public Time getEstimed_time() {
        return estimed_time;
    }

    public void setEstimed_time(Time estimed_time) {
        this.estimed_time = estimed_time;
    }

    public int getTodo_task_states() {
        return todo_task_states;
    }

    public String getTodo_task_states_string() {
        return statesToWords(todo_task_states);
    }

    public void setTodo_task_states(int todo_task_states) {
        this.todo_task_states = todo_task_states;
    }

    public int getUnder_task_states() {
        return under_task_states;
    }

    public String getUnder_task_states_string() {
        return statesToWords(under_task_states);
    }

    public void setUnder_task_states(int under_task_states) {
        this.under_task_states = under_task_states;
    }

    public int getId_under_task() {
        return id_under_task;
    }

    public void setId_under_task(int id_under_task) {
        this.id_under_task = id_under_task;
    }

}
