/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.licenciement;

import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class Licenciement {
    /// field
    int idLicenciement;
    int idEmploye;
    LocalDate preavisDate;
    LocalDate licenciementDate;
    TypeLicenciement typeLicenciement;
    double preavisDroit;
    int etat;
    
    /// getter and setter

    public int getIdLicenciement() {
        return idLicenciement;
    }

    public void setIdLicenciement(int idLicenciement) {
        this.idLicenciement = idLicenciement;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDate getPreavisDate() {
        return preavisDate;
    }

    public void setPreavisDate(LocalDate preavisDate) {
        this.preavisDate = preavisDate;
    }

    public LocalDate getLicenciementDate() {
        return licenciementDate;
    }

    public void setLicenciementDate(LocalDate licenciementDate) {
        this.licenciementDate = licenciementDate;
    }

    public TypeLicenciement getTypeLicenciement() {
        return typeLicenciement;
    }

    public void setTypeLicenciement(TypeLicenciement typeLicenciement) {
        this.typeLicenciement = typeLicenciement;
    }

    public double getPreavisDroit() {
        return preavisDroit;
    }

    public void setPreavisDroit(double preavisDroit) {
        this.preavisDroit = preavisDroit;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// constructor

    public Licenciement(int idLicenciement, int idEmploye, LocalDate preavisDate, LocalDate licenciementDate, TypeLicenciement typeLicenciement, double preavisDroit, int etat) {
        this.idLicenciement = idLicenciement;
        this.idEmploye = idEmploye;
        this.preavisDate = preavisDate;
        this.licenciementDate = licenciementDate;
        this.typeLicenciement = typeLicenciement;
        this.preavisDroit = preavisDroit;
        this.etat = etat;
    }
    
    
}
