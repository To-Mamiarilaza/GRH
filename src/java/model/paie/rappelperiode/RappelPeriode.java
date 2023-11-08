/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.rappelperiode;

import java.time.LocalDate;
import java.util.List;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
public class RappelPeriode {
    /// field
    int idRappelPeriode;
    LocalDate date;
    double modification;
    int nombreMois;
    List<Service> services;     // service concernés
    
    /// getter and setter

    public int getIdRappelPeriode() {
        return idRappelPeriode;
    }

    public void setIdRappelPeriode(int idRappelPeriode) {
        this.idRappelPeriode = idRappelPeriode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getModification() {
        return modification;
    }

    public void setModification(double modification) {
        this.modification = modification;
    }

    public int getNombreMois() {
        return nombreMois;
    }

    public void setNombreMois(int nombreMois) {
        this.nombreMois = nombreMois;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    /// constructor

    public RappelPeriode(int idRappelPeriode, LocalDate date, double modification, int nombreMois) {
        this.idRappelPeriode = idRappelPeriode;
        this.date = date;
        this.modification = modification;
        this.nombreMois = nombreMois;
    }
    
}
