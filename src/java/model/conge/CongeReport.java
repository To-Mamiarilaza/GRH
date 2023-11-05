/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import static model.conge.service.CongeManager.checkCongeAvailability;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeReport {

    /// field
    int idPersonnel;
    int annee;
    int resteConge;

    /// getter and setter
    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getResteConge() {
        return resteConge;
    }

    public void setResteConge(int resteConge) {
        this.resteConge = resteConge;
    }

    /// constructor
    public CongeReport(int idPersonnel, int annee, int resteConge) {
        this.idPersonnel = idPersonnel;
        this.annee = annee;
        this.resteConge = resteConge;
    }

    /// methods
    
    // Fonction pour inserer un report de conge chaque annee
    public static void addCongeReport(int idEmploye, int annee, int resteConge, Connection connection) throws Exception {
        String query = "INSERT INTO conge_report (id_personnel, annee, reste_conge) VALUES (?, ?, ?)";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idEmploye);
            statement.setInt(2, annee);
            statement.setInt(3, resteConge);

            statement.executeUpdate();

            statement.close();

            if (closeable) {
                //connection.commit();
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
                //connection.rollback();
                connection.close();
            }
            throw e;
        }
    }
    
    public static void main(String[] args) throws Exception {
        addCongeReport(3, 2023, 0, null);
    }

}
