/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge.service;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static model.conge.service.CongeManager.CHEF_VALIDATION_ETAT;
import static model.conge.service.CongeManager.RH_VALIDATION_ETAT;

/**
 *
 * @author To Mamiarilaza
 */
public class ValidationDemande {
    // Classe de service pour la validation des demandes
    // Fonction pour valider le demande par un chef hierarchique
    public static void validateDemandeCongeByChef(int idConge, int idResponsable, String remarque, int resultat, Connection connection) throws Exception {
        String query = "UPDATE conge SET id_chef_hierarchique = ?, remarque_chef_hierarchique = ?, etat = ? WHERE id_conge = ?";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;
        int etat = resultat == 0 ? 1 : CHEF_VALIDATION_ETAT;   // 3 etat validé par chef hierarchique

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idResponsable);
            statement.setString(2, remarque);
            statement.setInt(3, etat);
            statement.setInt(4, idConge);

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

    // Fonction pour valider le demande par le responsable RH
    public static void validateDemandeCongeByRH(int idConge, int idResponsable, String remarque, int resultat, Connection connection) throws Exception {
        String query = "UPDATE conge SET id_personnel_rh = ?, remarque_personnel_rh = ?, etat = ? WHERE id_conge = ?";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;
        int etat = resultat == 0 ? 1 : RH_VALIDATION_ETAT;   // 3 etat validé par chef hierarchique

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, idResponsable);
            statement.setString(2, remarque);
            statement.setInt(3, etat);
            statement.setInt(4, idConge);

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
    
    // Pour annuler un demande de conges
    public static void annulerDemandeConge(int idConge, Connection connection) throws Exception {
        String query = "UPDATE conge SET etat = ? WHERE id_conge = ?";

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
            statement.setInt(1, 0);
            statement.setInt(2, idConge);

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

}
