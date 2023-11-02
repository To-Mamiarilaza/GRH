/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.venteconge;

import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class VenteConge {
    /// field
    int idVenteConge;
    int idEmploye;
    LocalDate debut;
    LocalDate fin;
    double montant;
    int etat;
    
    /// getter and setter

    public int getIdVenteConge() {
        return idVenteConge;
    }

    public void setIdVenteConge(int idVenteConge) {
        this.idVenteConge = idVenteConge;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// constructor

    public VenteConge(int idVenteConge, int idEmploye, LocalDate debut, LocalDate fin, double montant, int etat) {
        this.idVenteConge = idVenteConge;
        this.idEmploye = idEmploye;
        this.debut = debut;
        this.fin = fin;
        this.montant = montant;
        this.etat = etat;
    }

    public VenteConge() {
    }
    
}
