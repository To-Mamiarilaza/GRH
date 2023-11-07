/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.licenciement;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import model.conge.Personnel;

/**
 *
 * @author To Mamiarilaza
 */
public class LicenciementService {

    /// Classe de service pour gerer les licenciements
    // Type 0 si licenciement direct et 1 sinon
    public static void licencier(int idEmploye, int type) throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        PreparedStatement statement = null;
        ResultSet resultset = null;
        
        try {
            Personnel personnel = Personnel.getPersonnelById(idEmploye, connection);
            LocalDate datePreavis = LocalDate.now();

            LocalDate dateLicenciement = datePreavis.plusDays(personnel.getClasseEmploye().getDureePreavis());
            double droitPreavis = 0;

            // Pour le licenciement directe
            if (type == 0) {
                dateLicenciement = LocalDate.now();
                droitPreavis = (personnel.getSalaire() / 30) * personnel.getClasseEmploye().getDureePreavis();
            }

            // Insertion dans la base de données
            String query = "INSERT INTO licenciement (id_employe, date_preavis, date_licenciement, id_type_licenciement, droit_preavis, etat) VALUES (?, ?, ?, ?, ?, 1)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, personnel.getIdPersonnel());
            statement.setDate(2, Date.valueOf(datePreavis));
            statement.setDate(3, Date.valueOf(dateLicenciement));
            statement.setInt(4, 2);     // Ordre de l'employeur
            statement.setDouble(5, droitPreavis);

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
    
    public static void main(String[] args) {
        try {
            licencier(12, 1);
        } catch (Exception e) {
        }
    }
}
