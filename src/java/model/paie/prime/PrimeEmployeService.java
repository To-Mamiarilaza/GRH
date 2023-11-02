/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.prime;

import framework.database.utilitaire.GConnection;
import static framework.database.utilitaire.GConnection.connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class PrimeEmployeService {

    // Classe de service pour les primes des employes
    public static void givePrimeToEmploye(int idEmploye, int idPrime, double montant) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            // Insertion dans la base de données
            String query = "INSERT INTO prime_employe (id_prime, id_employe, date_prime, montant, etat) VALUES (?, ?, NOW(), ?, 1)";

            connection = GConnection.getSimpleConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idPrime);
            statement.setInt(2, idEmploye);
            statement.setDouble(3, montant);

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
                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }
    
    // Pour avoir tous les prime d'une employe dans un moment donnée
    public static List<PrimeEmploye> getEmployePrime(int idEmploye, int mois, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<PrimeEmploye> primeList = new ArrayList<>();
        String query = "SELECT * FROM prime_employe WHERE etat = 1 AND EXTRACT(MONTH FROM date_prime) = %d AND EXTRACT(YEAR FROM date_prime) = %d AND id_employe = %d";
        query = String.format(query, mois, annee, idEmploye);
        
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idPrimeEmploye = resultset.getInt("id_prime_employe");
                Prime prime = Prime.getPrimeById(resultset.getInt("id_prime"), connection);
                LocalDate datePrime = resultset.getDate("date_prime").toLocalDate();
                double montant = resultset.getDouble("montant");
                
                primeList.add(new PrimeEmploye(idPrimeEmploye, prime, idEmploye, montant, datePrime, 1));
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
    
    public static void main(String[] args) throws Exception {
        List<PrimeEmploye> primeList = getEmployePrime(12, 9, 2023, null);
        for (PrimeEmploye primeEmploye : primeList) {
            System.out.println("- " + primeEmploye.getIdEmploye() + " " + primeEmploye.getPrime().getPrime() + " " + primeEmploye.getDatePrime());
        }
    }
}
