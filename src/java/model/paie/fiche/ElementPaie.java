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
public class ElementPaie {
    /// Field
    String designation;
    double nombre;
    double taux;
    double montant;
    int type;   // 1 si positif et -1 si négative
    
    /// Getter and setter

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
    }

    public double getTaux() {
        return taux;
    }
    
    public String getTauxString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTaux());
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    /// constructor
    public ElementPaie(String designation, double nombre, double taux, double montant, int type) {
        this.designation = designation;
        this.nombre = nombre;
        this.taux = taux;
        this.montant = montant;
        this.type = type;
    }
    
    
    
}
