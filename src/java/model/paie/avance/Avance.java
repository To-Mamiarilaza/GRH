/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.avance;

import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class Avance {
    /// field
    int idAvance;
    int idEmploye;
    LocalDate date;
    double montant;
    int etat;
    
    /// getter and setter

    public int getIdAvance() {
        return idAvance;
    }

    public void setIdAvance(int idAvance) {
        this.idAvance = idAvance;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public Avance(int idAvance, int idEmploye, LocalDate date, double montant, int etat) {
        this.idAvance = idAvance;
        this.idEmploye = idEmploye;
        this.date = date;
        this.montant = montant;
        this.etat = etat;
    }
    
}
