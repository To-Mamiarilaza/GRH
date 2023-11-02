/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge.service;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeReel {
    // Classe de service pour marquer les congees réel
    // Marquer la date debut d'un conge
    public static void setRealBeginDate(int idConge, LocalDate beginDate, Connection connection) throws Exception {
        String query = "UPDATE conge SET date_debut_reel = ? WHERE id_conge = ?";

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
            statement.setDate(1, Date.valueOf(beginDate));
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

    // Marquer la date debut d'un conge
    public static void setRealEndDate(int idConge, LocalDate endDate, Connection connection) throws Exception {
        String query = "UPDATE conge SET date_fin_reel = ? WHERE id_conge = ?";

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
            statement.setDate(1, Date.valueOf(endDate));
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
