/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class HeureSupplementaireService {
    // Fonction de service pour gerer les heures supplementaire
    public static void addNewHeureSupplementaire(int idEmploye, LocalDateTime debut, LocalDateTime fin) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO heure_supplementaire (id_employe, debut, fin, etat) VALUES (?, ?, ?, 1)";

            connection = GConnection.getSimpleConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idEmploye);
            statement.setTimestamp(2, Timestamp.valueOf(debut));
            statement.setTimestamp(3, Timestamp.valueOf(fin));

            statement.executeUpdate();

            statement.close();

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
    
    // Pour avoir les heures supplementaires d'une employe dans un intervalle donnée
    public static List<HeureSupplementaire> getEmployeHeureSupplementaire(int idEmploye, LocalDate dateDebut, LocalDate dateFin, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<HeureSupplementaire> heureSupList = new ArrayList<>();
        String query = "SELECT * FROM heure_supplementaire WHERE etat = 1 AND debut >= ? AND debut <= ? AND id_employe = ?";
        
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(dateDebut));
            statement.setDate(2, Date.valueOf(dateFin));
            statement.setInt(3, idEmploye);
            System.out.println("Query : " + statement.toString());
            
            resultset = statement.executeQuery();

            while (resultset.next()) {
                int idHeureSup = resultset.getInt("id_heure_supplementaire");
                LocalDateTime debut = resultset.getTimestamp("debut").toLocalDateTime();
                LocalDateTime fin = resultset.getTimestamp("fin").toLocalDateTime();
                heureSupList.add(new HeureSupplementaire(idHeureSup, idEmploye, debut, fin, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return heureSupList;
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
    
    // Pour avoir tous les heure supplementaires d'une employe dans un moment donnée
    public static List<HeureSupplementaire> getEmployeHeureSupplementaire(int idEmploye, int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<HeureSupplementaire> heureSupList = new ArrayList<>();
        String query = "SELECT * FROM heure_supplementaire WHERE etat = 1 AND EXTRACT(MONTH FROM debut) = %d AND EXTRACT(YEAR FROM debut) = %d AND id_employe = %d ORDER BY debut DESC";
        query = String.format(query, mois, annee, idEmploye);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idHeureSup = resultset.getInt("id_heure_supplementaire");
                LocalDateTime debut = resultset.getTimestamp("debut").toLocalDateTime();
                LocalDateTime fin = resultset.getTimestamp("fin").toLocalDateTime();
                heureSupList.add(new HeureSupplementaire(idHeureSup, idEmploye, debut, fin, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return heureSupList;
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
    
    public static void main(String[] args) throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        //HeureSupplementaireService.addNewHeureSupplementaire(3, LocalDateTime.of(2023, 11, 3, , 0), LocalDateTime.of(2023, 11, 3, 23, 0));
        
        List<HeureSupplementaire> list = getEmployeHeureSupplementaire(3, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 6), connection);
        for (HeureSupplementaire heureSupplementaire : list) {
            System.out.println("ID : " + heureSupplementaire.getIdHeureSupplementaire());
        }
    }
}
