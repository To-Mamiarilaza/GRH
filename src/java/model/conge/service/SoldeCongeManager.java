/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge.service;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import model.conge.Conge;
import model.conge.CongeParameter;
import model.conge.CongePersonnel;
import model.conge.CongeReport;
import model.conge.Personnel;

/**
 *
 * @author To Mamiarilaza
 */
public class SoldeCongeManager {

    // Service spéciale pour le calcule du solde de conge
    // Calculer la somme de conge pris par une personne
    public static int calculerSommeConge(List<Conge> historiqueConges) {
        int somme = 0;
        for (Conge conge : historiqueConges) {
            if (conge.getEtat() == 4 && conge.getTypeConge().isIsCumulable()) {
                System.out.println("ID : " + conge.getIdConge());
                LocalDate dateDebut = conge.getDateDebutReel() == null ? conge.getDateDebutDemande() : conge.getDateDebutReel();
                LocalDate dateFin = conge.getDateFinReel() == null ? conge.getDateFinDemande() : conge.getDateFinReel();

                somme += ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
            }
        }
        return somme;
    }
    
    // Fonction pour avoir le report de congé dans une année
    public static CongeReport getAnneeCongeReport(Personnel personnel, int annee, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }
        
        CongeReport report = null;

        String query = "SELECT * FROM conge_report WHERE id_personnel = %d and annee = %d";
        query = String.format(query, personnel.getIdPersonnel(), annee);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                int resteConge = resultset.getInt("reste_conge");
                report = new CongeReport(personnel.getIdPersonnel(), annee, resteConge);
            } else {
                throw new Exception("Aucun report n'a ete fait pour ce personne cette annee");
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            return report;
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
 
    // Pour calculer le nombre de conge restant pour un personnel
    public static int calculerSoldeRestant(CongePersonnel congePersonnel, int annee, Connection connection) throws Exception {
        CongeParameter parameter = CongeParameter.getCongeParameter(connection);
        
        CongeReport report = getAnneeCongeReport(congePersonnel.getPersonnel(), annee, connection);
        List<Conge> anneeConge = CongeFilter.filterYearConge(congePersonnel.getHistoriqueConge(), annee);
        
        // Reste du conge cette annee plus les accumulations recents
        int dayBetween = (int) ChronoUnit.DAYS.between(LocalDate.of(annee, 1, 1), LocalDate.now()) + 1;
        int sommeSolde = report.getResteConge() + (int) (((double)(dayBetween / 30)) * parameter.getAccumulation());
        System.out.println("Day between : " + dayBetween);
        System.out.println("Reste conge : " + report.getResteConge());
        System.out.println("Get nb new conge : " + ((int) (((double)(dayBetween / 30)) * parameter.getAccumulation())));
        System.out.println("Somme solde : " + sommeSolde);
        
        // Somme des conges cette annee
        int totalConge = calculerSommeConge(anneeConge);
        System.out.println("Somme total du conge" + totalConge);
        
        return sommeSolde - totalConge;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(ChronoUnit.DAYS.between(LocalDate.of(2023, 1, 1), LocalDate.now()));
    }

}
