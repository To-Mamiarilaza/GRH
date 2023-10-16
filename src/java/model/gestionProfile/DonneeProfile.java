/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import java.util.List;

/**
 *
 * @author Fy Botas
 */
public class DonneeProfile {

    List<Diplome> listeDiplome;
    List<Adresse> listeAdresse;
    List<Experience> listeExperience;
    List<Sexe> listeSexe;
    List<Salaire> listeSalaire;

    public DonneeProfile(List<Diplome> listeDiplome, List<Adresse> listeAdresse, List<Experience> listeExperience, List<Sexe> listeSexe, List<Salaire> listeSalaire) {
        this.listeDiplome = listeDiplome;
        this.listeAdresse = listeAdresse;
        this.listeExperience = listeExperience;
        this.listeSexe = listeSexe;
        this.listeSalaire = listeSalaire;
    }
}
