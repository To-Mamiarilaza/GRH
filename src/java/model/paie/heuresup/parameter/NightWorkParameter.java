/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

import java.time.LocalTime;

/**
 *
 * @author To Mamiarilaza
 */
public class NightWorkParameter {
    /// field
    LocalTime debut;
    LocalTime fin;
    double pourcentage;
    
    /// getter and setter 

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    /// constructor

    public NightWorkParameter() {
    }

    public NightWorkParameter(LocalTime debut, LocalTime fin, double pourcentage) {
        this.debut = debut;
        this.fin = fin;
        this.pourcentage = pourcentage;
    }
    
}
