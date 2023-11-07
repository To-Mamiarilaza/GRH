/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.retenue;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class IRSAParameter {

    /// Field
    double debutMontant;
    double finMontant;
    double pourcentage;

    /// Getter and setter
    public double getDebutMontant() {
        return debutMontant;
    }

    public void setDebutMontant(double debutMontant) {
        this.debutMontant = debutMontant;
    }

    public double getFinMontant() {
        return finMontant;
    }

    public void setFinMontant(double finMontant) {
        this.finMontant = finMontant;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    /// Constructor
    public IRSAParameter(double debutMontant, double finMontant, double pourcentage) {
        this.debutMontant = debutMontant;
        this.finMontant = finMontant;
        this.pourcentage = pourcentage;
    }

    /// methods
    public static List<IRSAParameter> getAllIRSAParameter(Connection connection) throws SQLException {
        List<IRSAParameter> parameters = new ArrayList<>();

        String query = "SELECT * FROM irsa_parameter ORDER BY debut";

        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            double debutMontant = resultset.getDouble("debut");
            double finMontant = resultset.getDouble("fin");
            double pourcentage = resultset.getDouble("pourcentage");
            
            parameters.add(new IRSAParameter(debutMontant, finMontant, pourcentage));
            
        }

        return parameters;
    }
    
    public static void main(String[] args) throws SQLException, Exception {
        Connection connection = GConnection.getSimpleConnection();
        List<IRSAParameter> parameters = getAllIRSAParameter(connection);
        for (IRSAParameter parameter : parameters) {
            System.out.println("Debut : " + parameter.getDebutMontant());
        }
    }
}
