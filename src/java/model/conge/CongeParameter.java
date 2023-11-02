/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeParameter {
/// field
    double accumulation;
    int minimumDuree;
    int maxSolde;
    
/// getter and setter

    public double getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(double accumulation) {
        this.accumulation = accumulation;
    }

    public int getMinimumDuree() {
        return minimumDuree;
    }

    public void setMinimumDuree(int minimumDuree) {
        this.minimumDuree = minimumDuree;
    }

    public int getMaxSolde() {
        return maxSolde;
    }

    public void setMaxSolde(int maxSolde) {
        this.maxSolde = maxSolde;
    }
    
/// constructor

    public CongeParameter(double accumulation, int minimumDuree, int maxSolde) {
        this.accumulation = accumulation;
        this.minimumDuree = minimumDuree;
        this.maxSolde = maxSolde;
    }
    
/// methods
    
    public static CongeParameter getCongeParameter(Connection connection) throws SQLException, Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        CongeParameter congeParameter = null;
        String query = "SELECT * FROM conge_parameter";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                double accumulation = resultset.getDouble("accumulation");
                int minimumDuree = resultset.getInt("minimum_duree");
                int maxSolde = resultset.getInt("max_solde");
                
                congeParameter = new CongeParameter(accumulation, minimumDuree, maxSolde);
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return congeParameter;
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
