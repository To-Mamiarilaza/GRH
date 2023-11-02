/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.abscence;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author To Mamiarilaza
 */
public class Abscence {
    /// field
    int idAbscence;
    int idEmploye;
    LocalDate date;
    LocalTime debut;
    LocalTime fin;
    int etat;
    
    /// getter and setter

    public int getIdAbscence() {
        return idAbscence;
    }

    public void setIdAbscence(int idAbscence) {
        this.idAbscence = idAbscence;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// constructor

    public Abscence(int idAbscence, int idEmploye, LocalDate date, LocalTime debut, LocalTime fin, int etat) {
        this.idAbscence = idAbscence;
        this.idEmploye = idEmploye;
        this.date = date;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
    }
    
}
