/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.fiche;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import model.employe.Employe;

/**
 *
 * @author To Mamiarilaza
 */
public class FichePaie {

    /// Field
    Employe employe;
    
    LocalDate dateDebut;
    LocalDate dateFin;

    double salaireBase;
    double tauxJournalier;
    double tauxHoraires;

    double salaireBrute;
    double salaireNet;
    double montantImposable;
    double totalRetenue;
    double totalIRSA;
    double totalRetenueOrganisation;
    double netAPayer;

    List<ElementPaie> elementPaieList;
    List<Retenue> retenueOrganisationList;
    List<Retenue> retenueIRSAList;
    
    /// Total field for etat de paie
    double montantHeureSupTotal;
    double montantRappelTotal;
    double cnaps1;
    double cnaps8;
    double ostie1;
    double ostie5;
    double montantImpotTotal;
    double totalAvance;
    double totalIndemnite;
    int nombreAbscence;
    double retenueAbscenceTotal;
    
    /// getter and setter
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public double getSalaireBase() {
        return salaireBase;
    }
    
    public String getSalaireBaseString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireBase());
    }

    public void setSalaireBase(double salaireBase) {
        this.salaireBase = salaireBase;
    }

    public double getTauxJournalier() {
        return tauxJournalier;
    }
    
    public String getTauxJournalierString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTauxJournalier());
    }

    public void setTauxJournalier(double tauxJournalier) {
        this.tauxJournalier = tauxJournalier;
    }

    public double getTauxHoraires() {
        return tauxHoraires;
    }
    
    public String getTauxHorairesString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTauxHoraires());
    }

    public void setTauxHoraires(double tauxHoraires) {
        this.tauxHoraires = tauxHoraires;
    }

    public double getSalaireBrute() {
        return salaireBrute;
    }
    
    public String getSalaireBruteString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireBrute());
    }

    public void setSalaireBrute(double salaireBrute) {
        this.salaireBrute = salaireBrute;
    }

    public double getMontantImposable() {
        return montantImposable;
    }
    
    public String getMontantImposableString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getMontantImposable());
    }

    public void setMontantImposable(double montantImposable) {
        this.montantImposable = montantImposable;
    }

    public double getTotalRetenue() {
        return totalRetenue;
    }

    public String getTotalRetenueString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTotalRetenue());
    }

    
    public void setTotalRetenue(double totalRetenue) {
        this.totalRetenue = totalRetenue;
    }

    public double getTotalIRSA() {
        return totalIRSA;
    }
    
    public String getTotalIRSAString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTotalIRSA());
    }

    public void setTotalIRSA(double totalIRSA) {
        this.totalIRSA = totalIRSA;
    }

    public double getNetAPayer() {
        return netAPayer;
    }
    
    public String getNetAPayerString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getNetAPayer());
    }

    public int getNombreAbscence() {
        return nombreAbscence;
    }

    public void setNombreAbscence(int nombreAbscence) {
        this.nombreAbscence = nombreAbscence;
    }

    public double getRetenueAbscenceTotal() {
        return retenueAbscenceTotal;
    }
    
    public String getRetenueAbscenceTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getRetenueAbscenceTotal());
    }

    public void setRetenueAbscenceTotal(double retenueAbscenceTotal) {
        this.retenueAbscenceTotal = retenueAbscenceTotal;
    }
    
    public void setNetAPayer(double netAPayer) {
        this.netAPayer = netAPayer;
    }

    public List<ElementPaie> getElementPaieList() {
        return elementPaieList;
    }

    public void setElementPaieList(List<ElementPaie> elementPaieList) {
        this.elementPaieList = elementPaieList;
    }

    public List<Retenue> getRetenueOrganisationList() {
        return retenueOrganisationList;
    }

    public void setRetenueOrganisationList(List<Retenue> retenueOrganisationList) {
        this.retenueOrganisationList = retenueOrganisationList;
    }

    public List<Retenue> getRetenueIRSAList() {
        return retenueIRSAList;
    }

    public void setRetenueIRSAList(List<Retenue> retenueIRSAList) {
        this.retenueIRSAList = retenueIRSAList;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public double getTotalRetenueOrganisation() {
        return totalRetenueOrganisation;
    }
    
    public String getTotalRetenueOrganisationString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTotalRetenueOrganisation());
    }

    public void setTotalRetenueOrganisation(double totalRetenueOrganisation) {
        this.totalRetenueOrganisation = totalRetenueOrganisation;
    }

    public double getSalaireNet() {
        return salaireNet;
    }
    
    public String getSalaireNetString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireNet());
    }

    public void setSalaireNet(double salaireNet) {
        this.salaireNet = salaireNet;
    }

    public double getMontantHeureSupTotal() {
        return montantHeureSupTotal;
    }
    
    public String getMontantHeureSupTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getMontantHeureSupTotal());
    }

    public void setMontantHeureSupTotal(double montantHeureSupTotal) {
        this.montantHeureSupTotal = montantHeureSupTotal;
    }

    public double getMontantRappelTotal() {
        return montantRappelTotal;
    }
    
    public String getMontantRappelTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getMontantRappelTotal());
    }

    public void setMontantRappelTotal(double montantRappelTotal) {
        this.montantRappelTotal = montantRappelTotal;
    }

    public double getCnaps1() {
        return cnaps1;
    }
    
    public String getCnaps1String() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getCnaps1());
    }

    public void setCnaps1(double cnaps1) {
        this.cnaps1 = cnaps1;
    }

    public double getCnaps8() {
        return cnaps8;
    }
    
    public String getCnaps8String() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getCnaps8());
    }

    public void setCnaps8(double cnaps8) {
        this.cnaps8 = cnaps8;
    }

    public double getOstie1() {
        return ostie1;
    }
    
    public String getOstie1String() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getOstie1());
    }

    public void setOstie1(double ostie1) {
        this.ostie1 = ostie1;
    }

    public double getOstie5() {
        return ostie5;
    }

    public void setOstie5(double ostie5) {
        this.ostie5 = ostie5;
    }
    
    public String getOstie5String() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getOstie5());
    }

    public double getMontantImpotTotal() {
        return montantImpotTotal;
    }
    
    public String getMontantImpotTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getMontantImpotTotal());
    }

    public void setMontantImpotTotal(double montantImpotTotal) {
        this.montantImpotTotal = montantImpotTotal;
    }

    public double getTotalAvance() {
        return totalAvance;
    }
    
    public String getTotalAvanceString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTotalAvance());
    }

    public void setTotalAvance(double totalAvance) {
        this.totalAvance = totalAvance;
    }

    public double getTotalIndemnite() {
        return totalIndemnite;
    }
    
    public String getTotalIndemniteString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getTotalIndemnite());
    }

    public void setTotalIndemnite(double totalIndemnite) {
        this.totalIndemnite = totalIndemnite;
    }
    
    
    
    /// Constructor
    public FichePaie() {
        setElementPaieList(new ArrayList<ElementPaie>());
        setRetenueOrganisationList(new ArrayList<Retenue>());
        setRetenueIRSAList(new ArrayList<Retenue>());
    }

    /// methods
    // Fonction pour ajouter un element de paie
    public void addElementPaie(String designation, double nombre, double taux, double montant, int type) {
        ElementPaie newElement = new ElementPaie(designation, nombre, taux, montant, type);
        getElementPaieList().add(newElement);
    }

    // Fonction pour ajouter un retenue d'organisation
    public void addOrganisationRetenue(String designation, double taux, double montant) {
        Retenue newRetenue = new Retenue(designation, taux, montant);
        getRetenueOrganisationList().add(newRetenue);
    }

    // Fonction pour ajouter un retenue d'IRSA
    public void addIRSARetenue(String designation, double taux, double montant) {
        Retenue newRetenue = new Retenue(designation, taux, montant);
        getRetenueIRSAList().add(newRetenue);
    }

    // Pour voir le detail d'un fiche de paie
    public void detail() {
        System.out.println("DETAIL DU FICHE DE PAIE");
        System.out.println("Employe : " + getEmploye().getFullName());
        System.out.println("Salaire de base : " + getEmploye().getSalaire());
        System.out.println("Taux journalier : " + getTauxJournalierString()+ " et horraire : " + getTauxHorairesString());
        System.out.println("---------------------------------------------------------------");
        System.out.println("Listes des elements de paie : ");
        for (ElementPaie elementPaie : getElementPaieList()) {
            System.out.println("- " + elementPaie.getDesignation() + " | " + elementPaie.getNombre() + " | " + elementPaie.getTaux() + " | " + elementPaie.getMontantString()+ " | " + elementPaie.getType());
        }
        System.out.println("Montant salaire brut : " + getSalaireBruteString());
        System.out.println("Listes des retenues d'organisation : ");
        for (Retenue retenue : getRetenueOrganisationList()) {
            System.out.println("- " + retenue.getDesignation() + " | " + retenue.getTaux() + " | " + retenue.getMontantString());
        }
        System.out.println("Total Retenue Organisation : "  + getTotalRetenueOrganisation());
        System.out.println("Listes des retenues IRSA : ");
        for (Retenue retenue : getRetenueIRSAList()) {
            System.out.println("- " + retenue.getDesignation() + " | " + retenue.getTaux() + " | " + retenue.getMontant());
        }
        System.out.println("Total IRSA : " + getTotalIRSAString());
        System.out.println("Total Retenue : " + getTotalRetenueString());
        System.out.println("Net a payer : " + getNetAPayerString());
    }

}
