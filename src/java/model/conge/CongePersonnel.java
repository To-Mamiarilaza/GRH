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
import model.candidature.Candidature;
import model.candidature.PersonnalInformation;
import model.embauchement.Contrat;
import model.employe.Employe;

/**
 *
 * @author To Mamiarilaza
 */
public class CongePersonnel {
/// field
    Employe personnel;
    int soldeRestant;   // Nomgre de jour restant
    Conge currentConge;
    List<Conge> demandeConge;
    List<Conge> historiqueConge;
    
/// getter and setter

    public Employe getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Employe personnel) {
        this.personnel = personnel;
    }

    public int getSoldeRestant() {
        return soldeRestant;
    }

    public void setSoldeRestant(int soldeRestant) {
        this.soldeRestant = soldeRestant;
    }

    public Conge getCurrentConge() {
        return currentConge;
    }

    public void setCurrentConge(Conge currentConge) {
        this.currentConge = currentConge;
    }

    public List<Conge> getDemandeConge() {
        return demandeConge;
    }

    public void setDemandeConge(List<Conge> demandeConge) {
        this.demandeConge = demandeConge;
    }

    public List<Conge> getHistoriqueConge() {
        return historiqueConge;
    }

    public void setHistoriqueConge(List<Conge> historiqueConge) {
        this.historiqueConge = historiqueConge;
    }
    
/// constructor

    public CongePersonnel(Employe personnel, int soldeRestant, Conge currentConge, List<Conge> demandeConge, List<Conge> historiqueConge) {
        this.personnel = personnel;
        this.soldeRestant = soldeRestant;
        this.currentConge = currentConge;
        this.demandeConge = demandeConge;
        this.historiqueConge = historiqueConge;
    }
    
    public CongePersonnel(Employe personnel) {
        this.personnel = personnel;
    }
    
/// methods
    
    // Prendre tous les conges du personnel
    public void fetchAllConge(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Conge> congeList = new ArrayList<>();
        String query = "SELECT * FROM v_conge_with_type WHERE id_personnel = %d ORDER BY deposit_date DESC";
        query = String.format(query, getPersonnel().getIdEmploye());

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idConge = resultset.getInt("id_conge");

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

                Contrat chefContrat = new Contrat();
                Candidature chefCandidature = new Candidature();
                PersonnalInformation infoPersoChef = new PersonnalInformation();
                infoPersoChef.setName("Réponse du chef en attente !");
                infoPersoChef.setFirstName("");
                chefCandidature.setPersonnalInformation(infoPersoChef);
                chefContrat.setCandidature(chefCandidature);
                
                Employe chefHierarchique = new Employe();
                String remarqueChefHierarchique = "...";
                chefHierarchique.setContrat(chefContrat);
                if (resultset.getInt("id_chef_hierarchique") != 0) {
                    chefHierarchique = Employe.getById(resultset.getInt("id_chef_hierarchique"), connection);
                    remarqueChefHierarchique = resultset.getString("remarque_chef_hierarchique");
                }
                
                Contrat rhContrat = new Contrat();
                Candidature rhCandidature = new Candidature();
                PersonnalInformation infoPersoRh = new PersonnalInformation();
                infoPersoRh.setName("Réponse du RH en attente !");
                infoPersoRh.setFirstName("");
                rhCandidature.setPersonnalInformation(infoPersoRh);
                rhContrat.setCandidature(rhCandidature);
                
                Employe responsableRH = new Employe();
                String remarquePersonnelRH = "...";
                responsableRH.setContrat(rhContrat);
                if (resultset.getInt("id_personnel_rh") != 0) {
                    responsableRH = Employe.getById(resultset.getInt("id_personnel_rh"), connection);
                    remarquePersonnelRH = resultset.getString("remarque_personnel_rh");
                }

                int etat = resultset.getInt("etat");

                congeList.add(new Conge(idConge, getPersonnel(), explication, typeConge, dateDebutDemande, dateFinDemande, dateDebutReel, dateFinReel, chefHierarchique, responsableRH, etat, depositDate, remarqueChefHierarchique, remarquePersonnelRH));

            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            setHistoriqueConge(congeList);
            setDemandeConge(CongeFilter.filterDemandeConge(congeList));
            
            List<Conge> currentConge = CongeFilter.filterCurrentConge(congeList);
            if (currentConge.size() == 1) {
                setCurrentConge(currentConge.get(0));
            }
            
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
