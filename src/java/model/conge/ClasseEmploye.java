/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.conge.service.CongeFilter;

/**
 *
 * @author To Mamiarilaza
 */
public class ClasseEmploye {
    /// Field
    int idClasseEmploye;
    String classeEmploye;
    String description;
    double indamniteLicenciement;
    int dureePreavis;
    
    /// Getter and setter
    
    public int getIdClasseEmploye() {
        return idClasseEmploye;
    }

    public void setIdClasseEmploye(int idClasseEmploye) {
        this.idClasseEmploye = idClasseEmploye;
    }

    public String getClasseEmploye() {
        return classeEmploye;
    }

    public void setClasseEmploye(String classeEmploye) {
        this.classeEmploye = classeEmploye;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getIndamniteLicenciement() {
        return indamniteLicenciement;
    }

    public void setIndamniteLicenciement(double indamniteLicenciement) {
        this.indamniteLicenciement = indamniteLicenciement;
    }

    public int getDureePreavis() {
        return dureePreavis;
    }

    public void setDureePreavis(int dureePreavis) {
        this.dureePreavis = dureePreavis;
    }

    
    
    /// Constructor

    public ClasseEmploye(int idClasseEmploye, String classeEmploye, String description, double indamniteLicenciement, int dureePreavis) {
        this.idClasseEmploye = idClasseEmploye;
        this.classeEmploye = classeEmploye;
        this.description = description;
        this.indamniteLicenciement = indamniteLicenciement;
        this.dureePreavis = dureePreavis;
    }

    public ClasseEmploye() {
    }
    
    /// methods
    
    // Prendre tous les classes d'employes
    public static List<ClasseEmploye> getAllClasseEmploye(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<ClasseEmploye> classeEmployeList = new ArrayList<>();
        String query = "SELECT * FROM classe_employe";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idClasseEmploye = resultset.getInt("id_classe_employe");
                String classeEmployeName = resultset.getString("classe_employe");
                String description = resultset.getString("classe_description");
                double indamniteLicenciement = resultset.getDouble("indamnite_licenciement");
                int dureePreavis = resultset.getInt("duree_preavis");
                
                classeEmployeList.add(new ClasseEmploye(idClasseEmploye, classeEmployeName, description, indamniteLicenciement, dureePreavis));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            return classeEmployeList;

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
    
    // Trouver une employe par son ID
    public static ClasseEmploye getClasseEmployeById(int idClasseEmploye, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        ClasseEmploye classeEmploye = null;
        String query = "SELECT * FROM classe_employe WHERE id_classe_employe = %d";
        query = String.format(query, idClasseEmploye);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                String classeEmployeName = resultset.getString("classe_employe");
                String description = resultset.getString("classe_description");
                double indamniteLicenciement = resultset.getDouble("indamnite_licenciement");
                int dureePreavis = resultset.getInt("duree_preavis");
                
                classeEmploye = new ClasseEmploye(idClasseEmploye, classeEmployeName, description, indamniteLicenciement, dureePreavis);
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            if (classeEmploye == null) {
                throw new Exception("La classe employe que vous chercher n'existe pas !");
            }
            else return classeEmploye;

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
        List<ClasseEmploye> employeList = ClasseEmploye.getAllClasseEmploye(null);
    }
}
