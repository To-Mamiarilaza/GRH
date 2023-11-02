/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeReport {
    /// field
    int idPersonnel;
    int annee;
    int resteConge;
    
    /// getter and setter

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getResteConge() {
        return resteConge;
    }

    public void setResteConge(int resteConge) {
        this.resteConge = resteConge;
    }
    
    /// constructor

    public CongeReport(int idPersonnel, int annee, int resteConge) {
        this.idPersonnel = idPersonnel;
        this.annee = annee;
        this.resteConge = resteConge;
    }
    
}
