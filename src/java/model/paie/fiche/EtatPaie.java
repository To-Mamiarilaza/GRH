/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.fiche;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
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
public class EtatPaie {
    // Field
    List<FichePaie> fiches;
    
    double salaireBaseTotal;
    double retenueAbscenceTotal;
    double totalIndemnite;
    double rappelTotal;
    double autresTotal;
    double heureSupplementaireTotal;
    double salaireBruteTotal;
    double cnaps1Total;
    double cnaps8Total;
    double ostie1Total;
    double ostie5Total;
    double revenueImposableTotal;
    double impotTotal;
    double IGRNetTotal;
    double retenueTotal;
    double salaireNETTotal;
    double netAPayerTotal;
    double avanceTotal;
    double netMoisTotal;
    
    // Getter and setter

    public List<FichePaie> getFiches() {
        return fiches;
    }

    public void setFiches(List<FichePaie> fiches) {
        this.fiches = fiches;
    }
    
    public double getSalaireBaseTotal() {
        return salaireBaseTotal;
    }
    
    public String getSalaireBaseTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireBaseTotal());
    }

    public void setSalaireBaseTotal(double salaireBaseTotal) {
        this.salaireBaseTotal = salaireBaseTotal;
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

    public double getRappelTotal() {
        return rappelTotal;
    }
    
    public String getRappelTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getRappelTotal());
    }

    public void setRappelTotal(double rappelTotal) {
        this.rappelTotal = rappelTotal;
    }

    public double getAutresTotal() {
        return autresTotal;
    }

    public void setAutresTotal(double autresTotal) {
        this.autresTotal = autresTotal;
    }

    public double getHeureSupplementaireTotal() {
        return heureSupplementaireTotal;
    }
    
    public String getHeureSupplementaireTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getHeureSupplementaireTotal());
    }

    public void setHeureSupplementaireTotal(double heureSupplementaireTotal) {
        this.heureSupplementaireTotal = heureSupplementaireTotal;
    }

    public double getSalaireBruteTotal() {
        return salaireBruteTotal;
    }
    
    public String getSalaireBruteTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireBruteTotal());
    }

    public void setSalaireBruteTotal(double salaireBruteTotal) {
        this.salaireBruteTotal = salaireBruteTotal;
    }

    public double getCnaps1Total() {
        return cnaps1Total;
    }
    
    public String getCnaps1TotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getCnaps1Total());
    }

    public void setCnaps1Total(double cnaps1Total) {
        this.cnaps1Total = cnaps1Total;
    }

    public double getCnaps8Total() {
        return cnaps8Total;
    }
    
    public String getCnaps8TotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getCnaps8Total());
    }

    public void setCnaps8Total(double cnaps8Total) {
        this.cnaps8Total = cnaps8Total;
    }

    public double getOstie1Total() {
        return ostie1Total;
    }
    
    public String getOstie1TotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getOstie1Total());
    }

    public void setOstie1Total(double ostie1Total) {
        this.ostie1Total = ostie1Total;
    }

    public double getOstie5Total() {
        return ostie5Total;
    }
    
    public String getOstie5TotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getOstie5Total());
    }

    public void setOstie5Total(double ostie5Total) {
        this.ostie5Total = ostie5Total;
    }

    public double getRevenueImposableTotal() {
        return revenueImposableTotal;
    }
    
    public String getRevenueImposableTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getRevenueImposableTotal());
    }

    public void setRevenueImposableTotal(double revenueImposableTotal) {
        this.revenueImposableTotal = revenueImposableTotal;
    }

    public double getImpotTotal() {
        return impotTotal;
    }
    
    public String getImpotTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getImpotTotal());
    }

    public void setImpotTotal(double impotTotal) {
        this.impotTotal = impotTotal;
    }

    public double getIGRNetTotal() {
        return IGRNetTotal;
    }

    public void setIGRNetTotal(double IGRNetTotal) {
        this.IGRNetTotal = IGRNetTotal;
    }

    public double getRetenueTotal() {
        return retenueTotal;
    }
    
    public String getRetenueTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getRetenueTotal());
    }

    public void setRetenueTotal(double retenueTotal) {
        this.retenueTotal = retenueTotal;
    }

    public double getSalaireNETTotal() {
        return salaireNETTotal;
    }
    
    public String getSalaireNETTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getSalaireNETTotal());
    }

    public void setSalaireNETTotal(double salaireNETTotal) {
        this.salaireNETTotal = salaireNETTotal;
    }

    public double getNetAPayerTotal() {
        return netAPayerTotal;
    }
    
    public String getNetAPayerTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getNetAPayerTotal());
    }

    public void setNetAPayerTotal(double netAPayerTotal) {
        this.netAPayerTotal = netAPayerTotal;
    }

    public double getAvanceTotal() {
        return avanceTotal;
    }
    
    public String getAvanceTotalString() {
        Locale locale = new Locale("fr", "MG");
        NumberFormat formatArgent = NumberFormat.getCurrencyInstance(locale);
        
        return formatArgent.format(getAvanceTotal());
    }

    public void setAvanceTotal(double avanceTotal) {
        this.avanceTotal = avanceTotal;
    }

    public double getNetMoisTotal() {
        return netMoisTotal;
    }

    public void setNetMoisTotal(double netMoisTotal) {
        this.netMoisTotal = netMoisTotal;
    }
    
    // Methods
    
    // Pour calculer les champs des totals 
    public void calculTotalValue() {
        for (FichePaie fiche : getFiches()) {
            setSalaireBaseTotal(getSalaireBaseTotal() + fiche.getSalaireBase());
            setRetenueAbscenceTotal(getRetenueAbscenceTotal() + fiche.getRetenueAbscenceTotal());
            setTotalIndemnite(getTotalIndemnite() + fiche.getTotalIndemnite());
            setRappelTotal(getRappelTotal() + fiche.getMontantRappelTotal());
            setAutresTotal(0);
            setHeureSupplementaireTotal(getHeureSupplementaireTotal() + fiche.getMontantHeureSupTotal());
            setSalaireBruteTotal(getSalaireBruteTotal() + fiche.getSalaireBrute());
            setCnaps1Total(getCnaps1Total() + fiche.getCnaps1());
            setCnaps8Total(getCnaps8Total() + fiche.getCnaps8());
            setOstie1Total(getOstie1Total()+ fiche.getOstie1());
            setOstie5Total(getOstie5Total()+ fiche.getOstie5());
            setRevenueImposableTotal(getRevenueImposableTotal() + fiche.getMontantImposable());
            setImpotTotal(getIGRNetTotal() + fiche.getTotalIRSA());
            setIGRNetTotal(getImpotTotal());
            setRetenueTotal(getRetenueTotal() + fiche.getTotalRetenue());
            setSalaireNETTotal(getSalaireNETTotal() + fiche.getSalaireNet());
            setNetAPayerTotal(getNetAPayerTotal() + fiche.getNetAPayer());
            setAvanceTotal(getAvanceTotal() + fiche.getTotalAvance());
            setNetMoisTotal(getNetAPayerTotal());
        }
    }
    
    // Fonction pour avoir l'etat de paie dans un periode données
    public static EtatPaie getEtatPaie(LocalDate debut, LocalDate fin) throws Exception {
        EtatPaie etatPaie = new EtatPaie();
        Connection connection = GConnection.getSimpleConnection();
        
        List<Employe> employes = Employe.getAllId(connection);
        List<FichePaie> fichePaieList = new ArrayList<>();
        
        for (Employe employe : employes) {
            fichePaieList.add(FichePaieManager.getFichePaie(employe.getIdEmploye(), debut, fin, connection));
        }
        
        etatPaie.setFiches(fichePaieList);
        etatPaie.calculTotalValue();
        
        return etatPaie;
    }
    
    public static EtatPaie getEtatPaie(LocalDate debut, LocalDate fin, Connection connection) throws Exception {
        EtatPaie etatPaie = new EtatPaie();
        
        List<Employe> employes = Employe.getAllId(connection);
        List<FichePaie> fichePaieList = new ArrayList<>();
        
        for (Employe employe : employes) {
            fichePaieList.add(FichePaieManager.getFichePaie(employe.getIdEmploye(), debut, fin, connection));
        }
        
        etatPaie.setFiches(fichePaieList);
        etatPaie.calculTotalValue();
        
        return etatPaie;
    }
    
    public static void main(String[] args) throws Exception {
        getEtatPaie(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 10));
    }
    
}
