/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.abscence;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.paie.venteconge.VenteConge;

/**
 *
 * @author To Mamiarilaza
 */
public class AbscenceService {
    /// classe de service pour la gestion des abscences
    public static void addAbscence(int idEmploye, LocalDate date, LocalTime debut, LocalTime fin) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO abscence (id_employe, date, heure_debut, heure_fin, etat) VALUES (?, ?, ?, ?, 1)";

            connection = GConnection.getSimpleConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idEmploye);
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(debut));
            statement.setTime(4, Time.valueOf(fin));

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
//                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }
    
    // Pour avoir tous les prime d'une employe dans un moment donnée
    public static List<Abscence> getAllAbscence(int idEmploye, int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Abscence> abscenceList = new ArrayList<>();
        String query = "SELECT * FROM abscence WHERE etat = 1 AND EXTRACT(MONTH FROM date) = %d AND EXTRACT(YEAR FROM date) = %d AND id_employe = %d";
        query = String.format(query, mois, annee, idEmploye);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idAbscence = resultset.getInt("id_abscence");
                LocalDate date = resultset.getDate("date").toLocalDate();
                LocalTime debut = resultset.getTime("heure_debut").toLocalTime();
                LocalTime fin = resultset.getTime("heure_fin").toLocalTime();
                
                abscenceList.add(new Abscence(idAbscence, idEmploye, date, debut, fin, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return abscenceList;
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
    
    // Heure total d'abscence
    public static int getTotalHourAbscence(List<Abscence> abscences) {
        int total = 0;
        for (Abscence abscence : abscences) {
            total += ChronoUnit.HOURS.between(abscence.getDebut(), abscence.getFin());
        }
        return total;
    }
    
    // Pour avoir les abscence dans une intervalle donnée
    public static int getTotalAbscence(int idEmploye, LocalDate beginDate, LocalDate endDate, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Abscence> abscenceList = new ArrayList<>();
        String query = "SELECT * FROM abscence WHERE etat = 1 AND date >= ? AND date <= ? AND id_employe = ?";
        
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(beginDate));
            statement.setDate(2, Date.valueOf(endDate));
            statement.setInt(3, idEmploye);
            
            resultset = statement.executeQuery();

            while (resultset.next()) {
                int idAbscence = resultset.getInt("id_abscence");
                LocalDate date = resultset.getDate("date").toLocalDate();
                LocalTime debut = resultset.getTime("heure_debut").toLocalTime();
                LocalTime fin = resultset.getTime("heure_fin").toLocalTime();
                
                abscenceList.add(new Abscence(idAbscence, idEmploye, date, debut, fin, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            return getTotalHourAbscence(abscenceList);

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
        int heureTotal = getTotalAbscence(3, LocalDate.of(2023, 11, 8), LocalDate.of(2023, 11, 11), null);
        System.out.println("Heure total : " + heureTotal);
    }
}
