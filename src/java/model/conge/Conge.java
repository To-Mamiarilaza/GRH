/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class Conge {
/// field
    int idConge;
    Personnel personnel;
    String explication;
    TypeConge typeConge;
    LocalDate dateDebutDemande;
    LocalDate dateFinDemande;
    LocalDate dateDebutReel;
    LocalDate dateFinReel;
    Personnel chefHierarchique;
    String remarqueChef;
    Personnel responsableRH;
    String remarqueRH;
    int etat;
    LocalDate depositDate;
    
/// getter and setter

    public int getIdConge() {
        return idConge;
    }

    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public LocalDate getDateDebutDemande() {
        return dateDebutDemande;
    }

    public void setDateDebutDemande(LocalDate dateDebutDemande) {
        this.dateDebutDemande = dateDebutDemande;
    }

    public LocalDate getDateFinDemande() {
        return dateFinDemande;
    }

    public void setDateFinDemande(LocalDate dateFinDemande) {
        this.dateFinDemande = dateFinDemande;
    }

    public LocalDate getDateDebutReel() {
        return dateDebutReel;
    }

    public void setDateDebutReel(LocalDate dateDebutReel) {
        this.dateDebutReel = dateDebutReel;
    }

    public LocalDate getDateFinReel() {
        return dateFinReel;
    }

    public void setDateFinReel(LocalDate dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public Personnel getChefHierarchique() {
        return chefHierarchique;
    }

    public void setChefHierarchique(Personnel chefHierarchique) {
        this.chefHierarchique = chefHierarchique;
    }

    public Personnel getResponsableRH() {
        return responsableRH;
    }

    public void setResponsableRH(Personnel responsableRH) {
        this.responsableRH = responsableRH;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public LocalDate getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
    }

    public String getRemarqueChef() {
        return remarqueChef;
    }

    public void setRemarqueChef(String remarqueChef) {
        this.remarqueChef = remarqueChef;
    }

    public String getRemarqueRH() {
        return remarqueRH;
    }

    public void setRemarqueRH(String remarqueRH) {
        this.remarqueRH = remarqueRH;
    }
    
    
/// constructor
    public Conge(int idConge, Personnel personnel, String explication, TypeConge typeConge, LocalDate dateDebutDemande, LocalDate dateFinDemande, LocalDate dateDebutReel, LocalDate dateFinReel, Personnel chefHierarchique, Personnel responsableRH, int etat, LocalDate depositDate, String remarqueChef, String remarqueRH) {
        this.idConge = idConge;
        this.personnel = personnel;
        this.explication = explication;
        this.typeConge = typeConge;
        this.dateDebutDemande = dateDebutDemande;
        this.dateFinDemande = dateFinDemande;
        this.dateDebutReel = dateDebutReel;
        this.dateFinReel = dateFinReel;
        this.chefHierarchique = chefHierarchique;
        this.responsableRH = responsableRH;
        this.etat = etat;
        this.depositDate = depositDate;
        this.remarqueChef = remarqueChef;
        this.remarqueRH = remarqueRH;
    }
    
/// methods
    
    // Pour avoir le remarque convenable
    public String getConvenientRemarque() {
        if (getEtat() == 3 || getEtat() == 1 && getResponsableRH().getIdPersonnel() == 0) {
            return getRemarqueChef();
        } else if (getEtat() == 4 || getEtat() == 1 && getResponsableRH().getIdPersonnel() != 0){
            return getRemarqueRH();
        }
        return null;
    }
    
    // Pour avoir le nom et prenom
    public String getFullName() {
        return getPersonnel().getNom() + " " + getPersonnel().getPrenom();
    }
    
    // Pour avoir la date debut convenable
    public LocalDate getDateDebut() {
        if (getDateDebutReel() == null) {
            return getDateDebutDemande();
        }
        return getDateDebutReel();
    }
    
    // Pour avoir la date debut convenable
    public LocalDate getDateFin() {
        if (getDateFinReel()== null) {
            return getDateFinDemande();
        }
        return getDateFinReel();
    }
    
    // Pour avoir l'état d'un conge
    public String getCongeEtat() {
        final int annuleState = 0;
        final int refuseState = 1;
        final int attenteState = 2;
        final int valideChef = 3;
        final int valide = 4;
        
        switch (getEtat()) {
            case annuleState:
                return "Annulé";
            case refuseState:
                return "Refusé";
            case attenteState:
                return "En Attente";
            case valideChef:
                return "Validation en cours";
            case valide:
                return "Validé";
        }
        return "Etat inconnue";
    }

}
