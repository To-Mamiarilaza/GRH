/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author To Mamiarilaza
 */
public class HeureSupplementaire {
    /// field
    int idHeureSupplementaire;
    int idEmploye;
    LocalDateTime debut;
    LocalDateTime fin;
    int etat;
    
    /// getter and setter

    public int getIdHeureSupplementaire() {
        return idHeureSupplementaire;
    }

    public void setIdHeureSupplementaire(int idHeureSupplementaire) {
        this.idHeureSupplementaire = idHeureSupplementaire;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public LocalDateTime getDebut() {
        return debut;
    }
    
    public String getDebutString() {
        return getDebut().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm"));
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }
    
    public String getFinString() {
        return getFin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm"));
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// constructor

    public HeureSupplementaire(int idHeureSupplementaire, int idEmploye, LocalDateTime debut, LocalDateTime fin, int etat) {
        this.idHeureSupplementaire = idHeureSupplementaire;
        this.idEmploye = idEmploye;
        this.debut = debut;
        this.fin = fin;
        this.etat = etat;
    }

    public HeureSupplementaire() {
    }
    
}
