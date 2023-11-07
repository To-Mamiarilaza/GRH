/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.prime;

import java.time.LocalDate;

/**
 *
 * @author To Mamiarilaza
 */
public class PrimeEmploye {
    /// Field
    int idPrimeEmploye;
    Prime prime;
    int idEmploye;
    double montant;
    LocalDate datePrime;
    int etat;
    
    /// getter and setter

    public int getIdPrimeEmploye() {
        return idPrimeEmploye;
    }

    public void setIdPrimeEmploye(int idPrimeEmploye) {
        this.idPrimeEmploye = idPrimeEmploye;
    }

    public Prime getPrime() {
        return prime;
    }

    public void setPrime(Prime prime) {
        this.prime = prime;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
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

    public LocalDate getDatePrime() {
        return datePrime;
    }

    public void setDatePrime(LocalDate datePrime) {
        this.datePrime = datePrime;
    }
    
    /// constructor

    public PrimeEmploye(int idPrimeEmploye, Prime prime, int idEmploye, double montant, LocalDate primeDate, int etat) {
        this.idPrimeEmploye = idPrimeEmploye;
        this.prime = prime;
        this.idEmploye = idEmploye;
        this.montant = montant;
        this.datePrime = primeDate;
        this.etat = etat;
    }
    
    
}
