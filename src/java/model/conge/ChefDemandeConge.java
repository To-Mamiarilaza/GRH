/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import model.conge.service.CongeFilter;
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
public class ChefDemandeConge {
/// field
    Personnel personnel;
    List<Conge> demandes;
    List<Conge> valides;
    List<Conge> refuses;
    List<Conge> currents;
    
/// getter and setter

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Conge> getDemandes() {
        return demandes;
    }

    public void setDemandes(List<Conge> demandes) {
        this.demandes = demandes;
    }

    public List<Conge> getValides() {
        return valides;
    }

    public void setValides(List<Conge> valides) {
        this.valides = valides;
    }

    public List<Conge> getRefuses() {
        return refuses;
    }

    public void setRefuses(List<Conge> refuses) {
        this.refuses = refuses;
    }

    public List<Conge> getCurrents() {
        return currents;
    }

    public void setCurrents(List<Conge> currents) {
        this.currents = currents;
    }
    
/// constructor
    public ChefDemandeConge(Personnel personnel) {
        setPersonnel(personnel);
    }
    
/// methodes pour prendre tous les conges des subordonnées du chef 
    public void setAllCongesInformation(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Conge> congeList = new ArrayList<>();
        String query = "SELECT * FROM v_conge_with_type WHERE id_superieur = %d ORDER BY id_conge DESC";
        query = String.format(query, getPersonnel().getIdPersonnel());

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idConge = resultset.getInt("id_conge");
                
                int idPersonnel = resultset.getInt("id_personnel");

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

                congeList.add(new Conge(idConge, Personnel.getPersonnelById(idPersonnel, connection), explication, typeConge, dateDebutDemande, dateFinDemande, dateDebutReel, dateFinReel, chefHierarchique, responsableRH, etat, depositDate, remarqueChefHierarchique, remarquePersonnelRH));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }
            
            // Dispatch all conges to their types
            setDemandes(CongeFilter.filterDemandeConge(congeList));
            setCurrents(CongeFilter.filterCurrentConge(congeList));
            setValides(CongeFilter.filterValidateCongeByRH(congeList));
            setRefuses(CongeFilter.filterRefusedConge(congeList));

            
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
    
}
