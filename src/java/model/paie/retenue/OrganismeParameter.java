/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.retenue;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class OrganismeParameter {
    /// Field
    Organisme organisme;
    double pourcentage;
    int plafond;
    
    /// Getter and setter

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public int getPlafond() {
        return plafond;
    }

    public void setPlafond(int plafond) {
        this.plafond = plafond;
    }
    
    /// Constructor

    public OrganismeParameter(Organisme organisme, double pourcentage, int plafond) {
        this.organisme = organisme;
        this.pourcentage = pourcentage;
        this.plafond = plafond;
    }
    
    /// methods
    public static double getSMIGValue(Connection connection) throws Exception {
        double smigValue = 0;
        
        String query = "SELECT * FROM smig_parameter";
        
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultset = statement.executeQuery();
        
        if (resultset.next()) {            
            smigValue = resultset.getDouble("smig_value");
        }
        
        return smigValue;
    }
    
    public static OrganismeParameter getOrganismeParameter(String organismeName, Connection connection) throws Exception {
        OrganismeParameter parameter = null;
        String query = "SELECT * FROM v_organisme_parameter WHERE nom_organisme LIKE ?";
        
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, organismeName);
        
        ResultSet resultset = statement.executeQuery();
        
        if (resultset.next()) {            
            int idOrganisme = resultset.getInt("id_organisme");
            String nomOrganisme = resultset.getString("nom_organisme");
            String description  = resultset.getString("description");
            int etat = resultset.getInt("etat");
            double pourcentage = resultset.getDouble("pourcentage");
            int plalfond =  resultset.getInt("plafond");
            
            Organisme organisme = new Organisme(idOrganisme, nomOrganisme, description, etat);
            
            parameter = new OrganismeParameter(organisme, pourcentage, plalfond);
        }
        
        return parameter;
    }
    
    public static List<OrganismeParameter> getAllOrganismeParameter(Connection connection) throws SQLException {
        List<OrganismeParameter> parameters = new ArrayList<>();
        
        String query = "SELECT * FROM v_organisme_parameter";
        
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        while (resultset.next()) {            
            int idOrganisme = resultset.getInt("id_organisme");
            String nomOrganisme = resultset.getString("nom_organisme");
            String description  = resultset.getString("description");
            int etat = resultset.getInt("etat");
            double pourcentage = resultset.getDouble("pourcentage");
            int plalfond =  resultset.getInt("plafond");
            
            Organisme organisme = new Organisme(idOrganisme, nomOrganisme, description, etat);
            
            parameters.add(new OrganismeParameter(organisme, pourcentage, plalfond));
        }
        
        return parameters;
    }
    
    // Main
    public static void main(String[] args) throws SQLException, Exception {
        Connection connection = GConnection.getSimpleConnection();
        List<OrganismeParameter> parameters = getAllOrganismeParameter(connection);
        for (OrganismeParameter parameter : parameters) {
            System.out.println("Plafond : " + parameter.getPlafond());
        }
        connection.close();
    }
}
