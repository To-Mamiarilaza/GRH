/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge.service;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import model.conge.ChefDemandeConge;
import model.conge.Conge;
import model.conge.CongePersonnel;
import model.conge.Personnel;
import model.conge.RHDemandeConge;
import model.conge.TypeConge;
import model.quiz.Question;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeManager {

    // Classe de service pour gerer les conges
    static final int CHEF_VALIDATION_ETAT = 3;
    static final int RH_VALIDATION_ETAT = 4;   // Etat du conge apres la validation du RH
    
    // Pour avoir le conge en cours d'un personnel
    public static Conge getCongeById(int idConge, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        Conge conge = null;
        String query = "SELECT * FROM v_conge_with_type WHERE id_conge = %d";
        query = String.format(query, idConge);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                Personnel personnel = Personnel.getPersonnelById(resultset.getInt("id_personnel"), connection);

                int idTypeConge = resultset.getInt("id_type_conge");
                String typeCongeName = resultset.getString("type_conge_name");
                boolean isCumulable = resultset.getBoolean("is_cumulable");
                int duree = resultset.getInt("duree");
                int sexeValability = resultset.getInt("sexe_valability");
                TypeConge typeConge = new TypeConge(idTypeConge, typeCongeName, isCumulable, duree, 0);

                String explication = resultset.getString("explication");

                LocalDate dateDebutDemande = resultset.getDate("date_debut_demande").toLocalDate();
                LocalDate dateFinDemande = resultset.getDate("date_fin_demande").toLocalDate();

                LocalDate dateDebutReel = resultset.getDate("date_debut_reel") == null ? null : resultset.getDate("date_debut_reel").toLocalDate();
                LocalDate dateFinReel = resultset.getDate("date_fin_reel") == null ? null : resultset.getDate("date_fin_reel").toLocalDate();

                LocalDate depositDate = resultset.getDate("deposit_date").toLocalDate();

                Personnel chefHierarchique = new Personnel();
                String remarqueChefHierarchique = "...";
                chefHierarchique.setNom("Réponse du chef en attente !");
                chefHierarchique.setPrenom("");
                if (resultset.getInt("id_chef_hierarchique") != 0) {
                    chefHierarchique = Personnel.getPersonnelById(resultset.getInt("id_chef_hierarchique"), connection);
                    remarqueChefHierarchique = resultset.getString("remarque_chef_hierarchique");
                }

                Personnel responsableRH = new Personnel();
                String remarquePersonnelRH = "...";
                responsableRH.setNom("Réponse du RH en attente !");
                responsableRH.setPrenom("");
                if (resultset.getInt("id_personnel_rh") != 0) {
                    responsableRH = Personnel.getPersonnelById(resultset.getInt("id_personnel_rh"), connection);
                    remarquePersonnelRH = resultset.getString("remarque_personnel_rh");
                }

                int etat = resultset.getInt("etat");

                conge = new Conge(idConge, personnel, explication, typeConge, dateDebutDemande, dateFinDemande, dateDebutReel, dateFinReel, chefHierarchique, responsableRH, etat, depositDate, remarqueChefHierarchique, remarquePersonnelRH);

            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return conge;
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

    public static void checkCongeAvailability(int idPersonnel, LocalDate dateDebutDemande, LocalDate dateFinDemande, Connection connection) throws Exception {
        CongePersonnel congePersonnel = CongeManager.getCongePersonnelInfo(idPersonnel, connection);
        Personnel personnel = congePersonnel.getPersonnel();

        if (ChronoUnit.MONTHS.between(personnel.getEmbauche(), LocalDate.now()) <= 12) {
            throw new Exception("Vous devez tout d'abord avoir une anciennete plus d'un ans !");
        }

        if (ChronoUnit.DAYS.between(LocalDate.now(), dateDebutDemande) < 15) {
            throw new Exception("Vous devez demander le conge 15 jour a l'avance");
        }

        if (ChronoUnit.DAYS.between(dateDebutDemande, dateFinDemande) > congePersonnel.getSoldeRestant()) {
            throw new Exception("Vous n'avez plus assez de jour de conge, solde restant : " + congePersonnel.getSoldeRestant());
        }
    }

    // Fonction pour inserer une demande de conge
    public static void demanderConge(int idPersonnel, String explication, int idTypeConge, LocalDate dateDebutDemande, LocalDate dateFinDemande, Connection connection) throws Exception {
        checkCongeAvailability(idPersonnel, dateDebutDemande, dateFinDemande, connection);

        final int attenteEtat = 2;

        String query = "INSERT INTO conge (id_personnel, id_type_conge, explication, date_debut_demande, date_fin_demande, etat) VALUES (?, ?, ?, ?, ?, ?)";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idPersonnel);
            statement.setInt(2, idTypeConge);
            statement.setString(3, explication);
            statement.setDate(4, Date.valueOf(dateDebutDemande));
            statement.setDate(5, Date.valueOf(dateFinDemande));
            statement.setInt(6, attenteEtat);

            statement.executeUpdate();

            statement.close();

            if (closeable) {
                connection.commit();
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
                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }

    // Pour avoir l'information a propos des congées d'une personne
    public static CongePersonnel getCongePersonnelInfo(int idPersonnel, Connection connection) throws Exception {

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        Personnel personnel;
        CongePersonnel congePersonnel = null;

        try {
            personnel = Personnel.getPersonnelById(idPersonnel, connection);
            congePersonnel = new CongePersonnel(personnel);
            congePersonnel.fetchAllConge(connection);
            congePersonnel.setSoldeRestant(SoldeCongeManager.calculerSoldeRestant(congePersonnel, LocalDate.now().getYear(), connection));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        if (closeable) {
            connection.close();
        }

        return congePersonnel;
    }
    
    // Pour avoir l'information a propos des congées des subordonnées d'un chef
    public static ChefDemandeConge getCongeChefDemandeConge(int idSuperieur, Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        Personnel personnel = null;
        ChefDemandeConge chefDemande = null;

        try {
            personnel = Personnel.getPersonnelById(idSuperieur, connection);
            chefDemande = new ChefDemandeConge(personnel);
            chefDemande.setAllCongesInformation(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        if (closeable) {
            connection.close();
        }

        return chefDemande;
    }
    
    // Pour avoir l'information a propos des congées des subordonnées d'un chef
    public static RHDemandeConge getCongeRHDemandeConge(int idResponsable, Connection connection) throws Exception {
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        Personnel personnel = null;
        RHDemandeConge rhDemande = null;

        try {
            personnel = Personnel.getPersonnelById(idResponsable, connection);
            rhDemande = new RHDemandeConge(personnel);
            rhDemande.setAllCongesInformation(connection);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        if (closeable) {
            connection.close();
        }

        return rhDemande;
    }

    public static void main(String[] args) throws Exception {
        RHDemandeConge rhDemande = getCongeRHDemandeConge(2, null);
        System.out.println("Demandes en attente :");
        for (Conge demande : rhDemande.getDemandes()) {
            System.out.println("- " + demande.getIdConge());
        }
        System.out.println("Demandes validé :");
        for (Conge demande : rhDemande.getValides()) {
            System.out.println("- " + demande.getIdConge());
        }
    }
}
