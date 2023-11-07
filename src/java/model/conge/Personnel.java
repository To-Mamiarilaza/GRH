/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class Personnel {
/// field

    int idPersonnel;
    String nom;
    String prenom;
    LocalDate dateNaissance;
    String telephone;
    int idSuperieur;
    LocalDate embauche;
    Personnel superieur;
    double salaire;
    ClasseEmploye classeEmploye;
    String poste;
    List<Personnel> subordonnes;

/// getter and setter
    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getIdSuperieur() {
        return idSuperieur;
    }

    public void setIdSuperieur(int idSuperieur) {
        this.idSuperieur = idSuperieur;
    }

    public LocalDate getEmbauche() {
        return embauche;
    }

    public void setEmbauche(LocalDate embauche) {
        this.embauche = embauche;
    }

    public Personnel getSuperieur() {
        return superieur;
    }

    public void setSuperieur(Personnel superieur) {
        this.superieur = superieur;
    }

    public List<Personnel> getSubordonnes() {
        return subordonnes;
    }

    public void setSubordonnes(List<Personnel> subordonnes) {
        this.subordonnes = subordonnes;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public ClasseEmploye getClasseEmploye() {
        return classeEmploye;
    }

    public void setClasseEmploye(ClasseEmploye classeEmploye) {
        this.classeEmploye = classeEmploye;
    }
    
/// constructor
    public Personnel(int idPersonnel, String nom, String prenom, LocalDate dateNaissance, String telephone, int idSuperieur, LocalDate embauche, String poste) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.idSuperieur = idSuperieur;
        this.embauche = embauche;
        this.poste = poste;
    }

    public Personnel(int idPersonnel, String nom, String prenom, LocalDate dateNaissance, String telephone, int idSuperieur, LocalDate embauche, double salaire, ClasseEmploye classeEmploye, String poste) {
        this.idPersonnel = idPersonnel;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.telephone = telephone;
        this.idSuperieur = idSuperieur;
        this.embauche = embauche;
        this.salaire = salaire;
        this.classeEmploye = classeEmploye;
        this.poste = poste;
    }
    
    

    public Personnel() {
    }

/// methods
    public List<Personnel> getAllSubordonnes(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Personnel> personnelList = new ArrayList<>();
        String query = "SELECT * FROM personnel WHERE id_superieur = %d";
        query = String.format(query, getIdPersonnel());

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                String nom = resultset.getString("nom");
                String prenom = resultset.getString("prenom");
                LocalDate dateNaissance = resultset.getDate("date_naissance").toLocalDate();
                String telephone = resultset.getString("telephone");
                int idSuperieur = resultset.getInt("id_superieur");
                LocalDate dateEmbauche = resultset.getDate("date_embauche").toLocalDate();
                String poste = resultset.getString("poste");

                personnelList.add(new Personnel(resultset.getInt("id_personnel"), nom, prenom, dateNaissance, telephone, idSuperieur, dateEmbauche, poste));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return personnelList;
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

    public static Personnel getPersonnelById(int idPersonnel, Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        Personnel personnel = null;
        String query = "SELECT * FROM personnel WHERE id_personnel = %d";
        query = String.format(query, idPersonnel);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                String nom = resultset.getString("nom");
                String prenom = resultset.getString("prenom");
                LocalDate dateNaissance = resultset.getDate("date_naissance").toLocalDate();
                String telephone = resultset.getString("telephone");
                int idSuperieur = resultset.getInt("id_superieur");
                LocalDate dateEmbauche = resultset.getDate("date_embauche").toLocalDate();
                String poste = resultset.getString("poste");
                double salaire = resultset.getDouble("salaire");
                ClasseEmploye classeEmploye = ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection);

                personnel = new Personnel(idPersonnel, nom, prenom, dateNaissance, telephone, idSuperieur, dateEmbauche, salaire, classeEmploye, poste);

                personnel.setSubordonnes(personnel.getAllSubordonnes(connection));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return personnel;
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
        Personnel personnel = Personnel.getPersonnelById(12, null);
        System.out.println("Nom : " + personnel.getNom());
        System.out.println("Subordonnées : ");
        for (Personnel subordonne : personnel.getSubordonnes()) {
            System.out.println("- " + subordonne.getIdPersonnel());
        }
        System.out.println("Classe : " + personnel.getClasseEmploye().getClasseEmploye() + " salaire : " + personnel.getSalaire());
    }
}
