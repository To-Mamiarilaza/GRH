/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.venteconge;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.paie.prime.Prime;
import model.paie.prime.PrimeEmploye;

/**
 *
 * @author To Mamiarilaza
 */
public class VenteCongeService {
    /// classe de service pour la gestion des ventes de conges
    public static void sellConge(int idEmploye, LocalDate debut, LocalDate fin, double montant) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO vente_conge (id_employe, debut, fin, montant, etat) VALUES (?, ?, ?, ?, 1)";

            connection = GConnection.getSimpleConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idEmploye);
            statement.setDate(2, Date.valueOf(debut));
            statement.setDate(3, Date.valueOf(fin));
            statement.setDouble(4, montant);

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
    public static List<VenteConge> getVenteConge(int idEmploye, int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<VenteConge> venteList = new ArrayList<>();
        String query = "SELECT * FROM vente_conge WHERE etat = 1 AND EXTRACT(MONTH FROM debut) = %d AND EXTRACT(YEAR FROM debut) = %d AND id_employe = %d ORDER BY debut DESC";
        query = String.format(query, mois, annee, idEmploye);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idVenteConge = resultset.getInt("id_vente_conge");
                LocalDate debut = resultset.getDate("debut").toLocalDate();
                LocalDate fin = resultset.getDate("fin").toLocalDate();
                double montant = resultset.getDouble("montant");
                
                venteList.add(new VenteConge(idVenteConge, idEmploye, debut, fin, montant, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return venteList;
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
    
    public static List<VenteConge> getVenteConge(int idEmploye, LocalDate dateDebut, LocalDate dateFin, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<VenteConge> venteList = new ArrayList<>();
        String query = "SELECT * FROM vente_conge WHERE etat = 1 AND debut >= ? AND fin <= ? AND id_employe = ?";
        
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, Date.valueOf(dateDebut));
            statement.setDate(2, Date.valueOf(dateFin));
            statement.setInt(3, idEmploye);
            
            
            resultset = statement.executeQuery();

            while (resultset.next()) {
                int idVenteConge = resultset.getInt("id_vente_conge");
                LocalDate debut = resultset.getDate("debut").toLocalDate();
                LocalDate fin = resultset.getDate("fin").toLocalDate();
                double montant = resultset.getDouble("montant");
                
                venteList.add(new VenteConge(idVenteConge, idEmploye, debut, fin, montant, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return venteList;
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
