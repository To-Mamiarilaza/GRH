/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.avance;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.paie.venteconge.VenteConge;

/**
 *
 * @author To Mamiarilaza
 */
public class AvanceService {
    /// classe de service pour la gestion des ventes de conges
    public static void demanderAvance(int idEmploye, double montant) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO avance (id_employe, date, montant, etat) VALUES (?, NOW(), ?, 1)";

            connection = GConnection.getSimpleConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idEmploye);
            statement.setDouble(2, montant);
            

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
    public static List<Avance> getAllAvance(int idEmploye, int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Avance> avanceList = new ArrayList<>();
        String query = "SELECT * FROM avance WHERE etat = 1 AND EXTRACT(MONTH FROM date) = %d AND EXTRACT(YEAR FROM date) = %d AND id_employe = %d";
        query = String.format(query, mois, annee, idEmploye);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idAvance = resultset.getInt("id_avance");
                LocalDate date = resultset.getDate("date").toLocalDate();
                double montant = resultset.getDouble("montant");
                
                avanceList.add(new Avance(idAvance, idEmploye, date, montant, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return avanceList;
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
        List<Avance> avanceList = getAllAvance(12, 10, 2023, null);
        for (Avance avance : avanceList) {
            System.out.println("Montant : " + avance.getMontant());
        }
    }
}