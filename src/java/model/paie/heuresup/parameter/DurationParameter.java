/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

/**
 *
 * @author To Mamiarilaza
 */
public class DurationParameter {
    /// field
    int heureDebut;
    int heureFin;
    double pourcentage;
    
    /// getter and setter

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    /// constructor

    public DurationParameter(int heureDebut, int heureFin, double pourcentage) {
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.pourcentage = pourcentage;
    }

    public DurationParameter() {
    }
    
}
