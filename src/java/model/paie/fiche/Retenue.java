/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.fiche;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author To Mamiarilaza
 */
public class Retenue {
    /// Field
    String designation;
    double taux;
    double montant;
    
    /// getter and setter

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getMontant() {
        return montant;
    }
    
    public String getMontantString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getMontant());
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    /// constructor

    public Retenue(String designation, double taux, double montant) {
        this.designation = designation;
        this.taux = taux;
        this.montant = montant;
    }
    
}
