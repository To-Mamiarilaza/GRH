/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.rappelperiode;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
public class RappelPeriodeService {
    // Classe de service pour le rappel de periode
    
    // Fonction pour avoir l' id du dernier rappel insérée
    public static int getLastRappelID(Connection connection) throws Exception {
        String query = "SELECT max(id_rappel_periode) FROM rappel_periode";
        
        int lastId = 0;
        
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        if (resultset.next()) {
            lastId = resultset.getInt(1);
        }
        
        statement.close();
        resultset.close();
        
        if (lastId == 0) {
            throw new Exception("Aucun rappel n'est insérée");
        }
        
        return lastId;
    }
    
    // Fonction pour avoir les services concernée par un rappel
    public static List<Service> getAllRappelService(int idRappel, Connection connection) throws Exception {
         // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Service> serviceList = new ArrayList<>();
        String query = "SELECT * FROM departement_rappel_periode WHERE id_rappel_periode = %d";
        query = String.format(query, idRappel);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                Service service = Service.getById(connection, resultset.getInt("id_service"));
                serviceList.add(service);
            }

            resultset.close();
            statement.close();
            
            if (closeable) {
                connection.close();
            }

            return serviceList;
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
    
    // Fonction pour avoir tous les rappel periode dans un mois et annee données
    public static List<RappelPeriode> getAllRappelPeriode(int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<RappelPeriode> rappelList = new ArrayList<>();
        String query = "SELECT * FROM rappel_periode WHERE etat = 1 AND EXTRACT(MONTH FROM date) = %d AND EXTRACT(YEAR FROM date) = %d";
        
        query = String.format(query, mois, annee);
        System.out.println("Query : " + query);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idRappelPeriode = resultset.getInt("id_rappel_periode");
                LocalDate date = resultset.getDate("date").toLocalDate();
                double modification = resultset.getDouble("modification_salaire");
                int nombreMois = resultset.getInt("nombre_mois");
                int etat = resultset.getInt("etat");
                
                RappelPeriode newRappel = new RappelPeriode(idRappelPeriode, date, modification, nombreMois);
                newRappel.setServices(getAllRappelService(idRappelPeriode, connection));
                
                rappelList.add(newRappel);
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return rappelList;
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
    
    // Ajouter un rappelle sur période antérieur
    public static void addNewRappelle(int nbMois, double modification, List<Service> services) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO rappel_periode (date, modification_salaire, nombre_mois, etat) VALUES (NOW(), ?, ?, 1)";
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
            
            statement = connection.prepareStatement(query);
            statement.setDouble(1, modification);
            statement.setInt(2, nbMois);
            statement.executeUpdate();
            statement.close();
            
            int lastId = getLastRappelID(connection);
            
            // Insertion des départements concernée
            for (Service service : services) {
                query = "INSERT INTO departement_rappel_periode (id_service, id_rappel_periode) VALUES (?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, service.getIdService());
                statement.setInt(2, lastId);
                
                statement.executeUpdate();
                statement.close();
            }
            
            connection.commit();

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
    
    public static void main(String[] args) throws Exception {
        List<RappelPeriode> rappels = getAllRappelPeriode(11, 2023, null);
        
        for (RappelPeriode rappel : rappels) {
            System.out.println("ID rappel : " + rappel.getIdRappelPeriode());
            for (Service service : rappel.getServices()) {
                System.out.println("- " + service.getService());
            }
        }
    }
}
