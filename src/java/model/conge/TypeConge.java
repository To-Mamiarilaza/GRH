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

/**
 *
 * @author To Mamiarilaza
 */
public class TypeConge {
/// field
    int idTypeConge;
    String typeCongeName;
    boolean isCumulable;
    int sexe;
    int duree;
    
/// getter and setter

    public int getIdTypeConge() {
        return idTypeConge;
    }

    public void setIdTypeConge(int idTypeConge) {
        this.idTypeConge = idTypeConge;
    }

    public String getTypeCongeName() {
        return typeCongeName;
    }

    public void setTypeCongeName(String typeCongeName) {
        this.typeCongeName = typeCongeName;
    }

    public boolean isIsCumulable() {
        return isCumulable;
    }

    public void setIsCumulable(boolean isCumulable) {
        this.isCumulable = isCumulable;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
/// constructor

    public TypeConge(int idTypeConge, String typeCongeName, boolean isCumulable, int sexe, int duree) {
        this.idTypeConge = idTypeConge;
        this.typeCongeName = typeCongeName;
        this.isCumulable = isCumulable;
        this.sexe = sexe;
        this.duree = duree;
    }
    
/// methods
    
    public static List<TypeConge> getAllTypeConge(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<TypeConge> typeConges = new ArrayList<>();
        String query = "SELECT * FROM type_conge WHERE etat = 1";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idTypeConge = resultset.getInt("id_type_conge");
                String typeCongeName = resultset.getString("type_conge_name");
                boolean isCumulable = resultset.getBoolean("is_cumulable");
                int duree = resultset.getInt("duree");
                int etat = resultset.getInt("etat");
                
                typeConges.add(new TypeConge(idTypeConge, typeCongeName, isCumulable, etat, duree));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return typeConges;
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
        List<TypeConge> typeConges = TypeConge.getAllTypeConge(null);
        
        for (TypeConge typeConge : typeConges) {
            System.out.println("Id : " + typeConge.getIdTypeConge() + " : " + typeConge.getTypeCongeName());
        }
    }
    
}
