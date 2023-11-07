/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.prime;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.conge.Personnel;

/**
 *
 * @author To Mamiarilaza
 */
public class Prime {
    /// Field
    int idPrime;
    String prime;
    int etat;
    
    /// getter and setter

    public int getIdPrime() {
        return idPrime;
    }

    public void setIdPrime(int idPrime) {
        this.idPrime = idPrime;
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// Constructor

    public Prime(int idPrime, String prime, int etat) {
        this.idPrime = idPrime;
        this.prime = prime;
        this.etat = etat;
    }
    
    /// methods
    
    public static List<Prime> getAllPrime(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Prime> primeList = new ArrayList<>();
        String query = "SELECT * FROM prime WHERE etat = 1";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idPrime = resultset.getInt("id_prime");
                String primeName = resultset.getString("prime_name");
                
                primeList.add(new Prime(idPrime, primeName, 1));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return primeList;
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
    
    public static Prime getPrimeById(int idPrime, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        Prime prime = null;
        String query = "SELECT * FROM prime WHERE id_prime = %d";
        query = String.format(query, idPrime);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                String primeName = resultset.getString("prime_name");
                int etat = resultset.getInt("etat");
                
                prime = new Prime(idPrime, primeName, etat);
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            if (prime == null) {
                throw new Exception("La prime que vous demander n'existe pas !");
            }

            return prime;
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
