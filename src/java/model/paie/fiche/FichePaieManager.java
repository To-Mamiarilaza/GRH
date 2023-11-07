/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.fiche;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.List;
import model.employe.Employe;
import model.paie.abscence.AbscenceService;
import model.paie.avance.Avance;
import model.paie.avance.AvanceService;
import model.paie.heuresup.HeureSupplementaire;
import model.paie.heuresup.HeureSupplementaireService;
import model.paie.heuresup.parameter.HeureSupplementaireParameter;
import model.paie.retenue.OrganismeParameter;
import model.paie.prime.PrimeEmploye;
import model.paie.prime.PrimeEmployeService;
import model.paie.rappelperiode.RappelPeriode;
import model.paie.rappelperiode.RappelPeriodeService;
import model.paie.retenue.IRSAParameter;
import model.paie.venteconge.VenteConge;
import model.paie.venteconge.VenteCongeService;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
public class FichePaieManager {
    /// Classe de service pour le gerer le fiche de paie

    // Informations de base requis
    public static void addInitialInformation(FichePaie fichePaie, int idEmploye, LocalDate dateDebut, LocalDate dateFin, Connection connection) throws Exception {
        Employe employe = Employe.getById(idEmploye, connection);

        fichePaie.setEmploye(employe);

        fichePaie.setDateDebut(dateDebut);
        fichePaie.setDateFin(dateFin);

        fichePaie.setSalaireBase(employe.getSalaire());

        double tauxJournalier = Math.round(fichePaie.getSalaireBase() / 30);
        fichePaie.setTauxJournalier(tauxJournalier);

        double tauxHorraire = Math.round(fichePaie.getSalaireBase() / 173);
        fichePaie.setTauxHoraires(Double.valueOf(tauxHorraire));
    }

    // Ajout le salaire convenable
    public static void addReceivedSalaire(FichePaie fichePaie, Connection connection) {
        LocalDate endDay = fichePaie.getDateFin();
        if (endDay.isAfter(LocalDate.now())) {
            endDay = LocalDate.now();
        }

        LocalDate beginDay = fichePaie.getDateDebut();
        if (fichePaie.getDateDebut().isBefore(fichePaie.getEmploye().getContrat().getContratDate().toLocalDate())) {
            beginDay = fichePaie.getEmploye().getContrat().getContratDate().toLocalDate();
        }

        int jourEffectue = (int) ChronoUnit.DAYS.between(beginDay, fichePaie.getDateFin()) + 1;
        double salaire = jourEffectue * fichePaie.getTauxJournalier();

        String designation = "Salaire du " + beginDay + " au " + endDay;
        fichePaie.addElementPaie(designation, jourEffectue, fichePaie.getTauxJournalier(), salaire, 1);
    }

    // Marquer dans le fiche de paie les abscences d'une employe
    public static void addAbscenceDeductible(FichePaie fichePaie, Connection connection) throws Exception {
        int totalAbscence = AbscenceService.getTotalAbscence(fichePaie.getEmploye().getIdEmploye(), fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);

        fichePaie.addElementPaie("Abscences deductibles", totalAbscence, fichePaie.getTauxHoraires(), totalAbscence * fichePaie.getTauxHoraires(), -1);    // Argent sortant
        
        fichePaie.setNombreAbscence(totalAbscence);
        fichePaie.setRetenueAbscenceTotal(totalAbscence * fichePaie.getTauxHoraires());
    }

    // Ajouter tous les primes de l'employe
    public static void addAllPrimes(FichePaie fichePaie, Connection connection) throws Exception {
        double somme = 0;
        List<PrimeEmploye> primes = PrimeEmployeService.getEmployePrime(fichePaie.getEmploye().getIdEmploye(), fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);
        for (PrimeEmploye prime : primes) {
            fichePaie.addElementPaie(prime.getPrime().getPrime(), 1, prime.getMontant(), prime.getMontant(), 1);
            somme += prime.getMontant();
        }
        
        fichePaie.setTotalIndemnite(somme);
    }

    // Ajouter les heures supplementaires
    public static void addHeureSupplementaires(FichePaie fichePaie, Connection connection) throws Exception {
        HeureSupplementaireParameter hsParameter = HeureSupplementaireParameter.getHeureSupplementaireParameter(connection);
        List<HeureSupplementaire> heureSups = HeureSupplementaireService.getEmployeHeureSupplementaire(fichePaie.getEmploye().getIdEmploye(), fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);

        double sommeMajore30 = 0;
        double sommeMajore50 = 0;
        double sommeMajore40 = 0;
        double sommeMajore100 = 0;
        double sommeNocturne = 0;

        // Parametre par semaine
        double sommeMajore30Semaine = 0;
        int semaine = 0;

        for (HeureSupplementaire heureSup : heureSups) {
            if (semaine == 0) {
                semaine = heureSup.getDebut().get(WeekFields.ISO.weekOfWeekBasedYear());
            }

            if (semaine != heureSup.getDebut().get(WeekFields.ISO.weekOfWeekBasedYear())) {
                semaine = heureSup.getDebut().get(WeekFields.ISO.weekOfWeekBasedYear());

                sommeMajore30 += sommeMajore30Semaine;
                sommeMajore30Semaine = 0;
            }

            if (hsParameter.checkOutWorkDay(heureSup)) {
                int duree = (int) ChronoUnit.MINUTES.between(heureSup.getDebut(), heureSup.getFin());
                double hourQuantity = (double) duree / 60;
                sommeMajore100 += hourQuantity;
            } else if (hsParameter.checkWeekEndDay(heureSup)) {
                int duree = (int) ChronoUnit.MINUTES.between(heureSup.getDebut(), heureSup.getFin());
                double hourQuantity = (double) duree / 60;
                sommeMajore40 += hourQuantity;
            } else {
                int duree = (int) ChronoUnit.MINUTES.between(heureSup.getDebut(), heureSup.getFin());
                double hourQuantity = (double) duree / 60;

                double reste = hsParameter.getDurationMajore(30).getHeureFin() - sommeMajore30Semaine;
                if (reste > 0) {
                    if (hourQuantity >= reste) {
                        sommeMajore30Semaine += reste;
                        hourQuantity -= reste;
                        sommeMajore50 += hourQuantity;
                    } else {
                        sommeMajore30Semaine += hourQuantity;
                    }
                } else {
                    sommeMajore50 += hourQuantity;
                }
            }

            // Majoration pour les travails nocturne
            double nocturneDuree = hsParameter.getNightWorkDuration(heureSup);
            sommeNocturne += nocturneDuree;
        }

        // Vérsement en cas de reste
        sommeMajore30 += sommeMajore30Semaine;

        // Arrondissement des valeurs
        sommeMajore30 = Math.round(sommeMajore30);
        sommeMajore40 = Math.round(sommeMajore40);
        sommeMajore50 = Math.round(sommeMajore50);
        sommeMajore100 = Math.round(sommeMajore100);
        sommeNocturne = Math.round(sommeNocturne);

        // Ajout des elements de paie
        fichePaie.addElementPaie("Heure supplementaire majorees de 30 %", sommeMajore30, fichePaie.getTauxHoraires() * 1.3, sommeMajore30 * fichePaie.getTauxHoraires() * 1.3, 1);    // Taux avec majoration
        fichePaie.addElementPaie("Heure supplementaire majorees de 40 %", sommeMajore40, fichePaie.getTauxHoraires() * 1.4, sommeMajore40 * fichePaie.getTauxHoraires() * 1.4, 1);    // Taux avec majoration
        fichePaie.addElementPaie("Heure supplementaire majorees de 50 %", sommeMajore50, fichePaie.getTauxHoraires() * 1.5, sommeMajore50 * fichePaie.getTauxHoraires() * 1.5, 1);    // Taux avec majoration
        fichePaie.addElementPaie("Heure supplementaire majorees de 100 %", sommeMajore100, fichePaie.getTauxHoraires() * 2, sommeMajore100 * fichePaie.getTauxHoraires() * 2, 1);    // Taux avec majoration
        fichePaie.addElementPaie("Majoration pour heure de nuit", sommeNocturne, fichePaie.getTauxHoraires() * 0.3, sommeNocturne * fichePaie.getTauxHoraires() * 0.3, 1);    // Taux avec majoration

        // Placer le montant total des heures supplmentaires
        double montantTotal =  sommeMajore30 * fichePaie.getTauxHoraires() * 1.3;
        montantTotal +=  sommeMajore40 * fichePaie.getTauxHoraires() * 1.4;
        montantTotal +=  sommeMajore50 * fichePaie.getTauxHoraires() * 1.5;
        montantTotal +=  sommeMajore100 * fichePaie.getTauxHoraires() * 2;
        montantTotal +=  sommeNocturne * fichePaie.getTauxHoraires() * 0.3;
        
        fichePaie.setMontantHeureSupTotal(montantTotal);
    }

    // Ajouter les rappels sur periode anterieur
    public static void addRappelPeriode(FichePaie fichePaie, Connection connection) throws Exception {
        List<RappelPeriode> rappels = RappelPeriodeService.getAllRappelPeriode(fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);

        String designation = "Rappels sur periode anterieur";
        double nombre = 0;

        for (RappelPeriode rappel : rappels) {
            for (Service service : rappel.getServices()) {
                if (service.getIdService().equals(fichePaie.getEmploye().getService().getIdService())) {
                    nombre += rappel.getNombreMois();
                }
            }
        }

        fichePaie.addElementPaie(designation, nombre, fichePaie.getSalaireBase(), nombre * fichePaie.getSalaireBase(), 1);
        
        fichePaie.setMontantRappelTotal(nombre * fichePaie.getSalaireBase());
    }

    // Ajouter les ventes de conges
    public static void addVenteConge(FichePaie fichePaie, Connection connection) throws Exception {
        List<VenteConge> venteConges = VenteCongeService.getVenteConge(fichePaie.getEmploye().getIdEmploye(), fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);

        String designation = "Droits de congés";
        int nombre = 0;

        for (VenteConge venteConge : venteConges) {
            int duree = (int) ChronoUnit.DAYS.between(venteConge.getDebut(), venteConge.getFin()) + 1;
            nombre += duree;
        }

        fichePaie.addElementPaie(designation, nombre, fichePaie.getTauxJournalier(), nombre * fichePaie.getTauxJournalier(), 1);
    }

    // Ajouts des demandes d'avance dans le fiche de paie
    public static void addAvance(FichePaie fichePaie, Connection connection) throws Exception {
        List<Avance> avanceList = AvanceService.getAllAvance(fichePaie.getEmploye().getIdEmploye(), fichePaie.getDateDebut(), fichePaie.getDateFin(), connection);

        double somme = 0;
        int nombre = 0;
        for (Avance avance : avanceList) {
            somme += avance.getMontant();
            nombre++;
        }

        double sommeMoyenne = somme / nombre;
        if (somme == 0) {
            sommeMoyenne = 0;  // Pour eviter le NAN
        }

        String designation = "Avance de salaire ( en moyenne )";
        fichePaie.addElementPaie(designation, nombre, sommeMoyenne, somme, -1);
        fichePaie.setTotalAvance(somme);
    }

    // Marquer les droits de préavis et licenciement
    public static void addPreavisAndLicenciement(FichePaie fichePaie, Connection connection) {
        fichePaie.addElementPaie("Droit de préavis", 0, fichePaie.getTauxJournalier(), 0, 1);
        fichePaie.addElementPaie("Indemnites de licenciement", 0, fichePaie.getTauxJournalier(), 0, 1);
    }

    // Fonction pour calculer le salaire brute
    public static void calculSalaireBrut(FichePaie fichePaie) {
        double somme = 0;
        for (ElementPaie elementPaie : fichePaie.getElementPaieList()) {
            somme += elementPaie.getMontant() * elementPaie.getType();
        }
        fichePaie.setSalaireBrute(somme);
    }

    // Fonction pour marquer tous les retenues d'organisation
    public static void addAllRetenue(FichePaie fichePaie, Connection connection) throws Exception {
        double smigValue = OrganismeParameter.getSMIGValue(connection);

        if (fichePaie.getEmploye().getContrat().getIsCnaps() == 1) {
            OrganismeParameter cnapsParameter = OrganismeParameter.getOrganismeParameter("CNAPS", connection);
            double montantRetenable = fichePaie.getSalaireBrute();
            if (smigValue * cnapsParameter.getPlafond() < montantRetenable) {
                montantRetenable = smigValue * cnapsParameter.getPlafond();
            }
            fichePaie.addOrganisationRetenue("Retenue " + cnapsParameter.getOrganisme().getNomOrganisme(), cnapsParameter.getPourcentage(), montantRetenable * (cnapsParameter.getPourcentage() / 100));
            fichePaie.setCnaps1(montantRetenable * 0.1);
            fichePaie.setCnaps8(montantRetenable * 0.8);
        }

        if (fichePaie.getEmploye().getContrat().getIsSanitaire() == 1) {
            OrganismeParameter santeParameter = OrganismeParameter.getOrganismeParameter("OSTIE", connection);
            double montantRetenable = fichePaie.getSalaireBrute();
            fichePaie.addOrganisationRetenue("Retenue " + santeParameter.getOrganisme().getNomOrganisme(), santeParameter.getPourcentage(), montantRetenable * (santeParameter.getPourcentage() / 100));
            fichePaie.setOstie1(montantRetenable * 0.1);
            fichePaie.setOstie5(montantRetenable * 0.5);
        }

    }

    // Calculer le total des retenues d'organisation
    public static void calculRetenueOrganisation(FichePaie fichePaie) {
        double somme = 0;

        for (Retenue retenue : fichePaie.getRetenueOrganisationList()) {
            somme += retenue.getMontant();
        }

        fichePaie.setTotalRetenueOrganisation(somme);
        fichePaie.setMontantImposable(fichePaie.getSalaireBrute() - fichePaie.getTotalRetenueOrganisation());
    }

    // Calculer le total des retenues IRSA
    public static void calculRetenueIRSA(FichePaie fichePaie) {
        double somme = 0;

        for (Retenue retenue : fichePaie.getRetenueIRSAList()) {
            somme += retenue.getMontant();
        }

        // Placons tous les donnees nécessaires ici
        fichePaie.setTotalIRSA(somme);
        fichePaie.setMontantImpotTotal(somme);
        fichePaie.setTotalRetenue(fichePaie.getTotalRetenueOrganisation() + fichePaie.getTotalIRSA());
        fichePaie.setNetAPayer(fichePaie.getSalaireBrute() - fichePaie.getTotalRetenue());
        fichePaie.setSalaireNet(fichePaie.getSalaireBrute() - fichePaie.getTotalRetenue() + fichePaie.getTotalAvance());

    }

    // Marquer les retenues IRSA
    public static void addAllRetenueIRSA(FichePaie fichePaie, Connection connection) throws Exception {
        List<IRSAParameter> parameters = IRSAParameter.getAllIRSAParameter(connection);
        for (IRSAParameter parameter : parameters) {
            if (parameter.getFinMontant() != 0 && parameter.getFinMontant() < fichePaie.getMontantImposable()) {
                String designation = "Tranche IRSA de " + parameter.getDebutMontant() + " a " + parameter.getFinMontant();
                double montant = Math.round((parameter.getFinMontant() - parameter.getDebutMontant()) * (parameter.getPourcentage() / 100));
                fichePaie.addIRSARetenue(designation, parameter.getPourcentage(), montant);
            } else if (parameter.getDebutMontant() >= fichePaie.getMontantImposable()) {
                String designation = "Tranche IRSA de " + parameter.getDebutMontant() + " a " + parameter.getFinMontant();
                double montant = 0;
                fichePaie.addIRSARetenue(designation, parameter.getPourcentage(), montant);
            } else {
                String designation = "Tranche IRSA de " + parameter.getDebutMontant() + " a " + parameter.getFinMontant();
                double montant = Math.round((fichePaie.getMontantImposable() - parameter.getDebutMontant()) * (parameter.getPourcentage() / 100));
                fichePaie.addIRSARetenue(designation, parameter.getPourcentage(), montant);
            }
        }
    }

    // Pour avoir le fiche de paie d'un employe dans un intervalle donnée
    public static FichePaie getFichePaie(int idEmploye, LocalDate dateDebut, LocalDate dateFin, Connection connection) throws Exception {
        FichePaie fichePaie = new FichePaie();

        // Ajoute les informations initiale requis
        addInitialInformation(fichePaie, idEmploye, dateDebut, dateFin, connection);
        // Ajout du ligne du salaire
        addReceivedSalaire(fichePaie, connection);
        // Marquage des abscences
        addAbscenceDeductible(fichePaie, connection);
        // Marquage des primes
        addAllPrimes(fichePaie, connection);
        // Marquer les heures supplementaires
        addHeureSupplementaires(fichePaie, connection);
        // Marquer les rappels sur periode anterieur
        addRappelPeriode(fichePaie, connection);
        // Marquer les ventes de conges
        addVenteConge(fichePaie, connection);
        // Marquer les demandes d'avance
        addAvance(fichePaie, connection);
        // Marquer les préavis et licenciement
        addPreavisAndLicenciement(fichePaie, connection);

        calculSalaireBrut(fichePaie);

        // Marquer les retenues des organisations
        addAllRetenue(fichePaie, connection);
        calculRetenueOrganisation(fichePaie);

        // Marquer les retenues IRSA
        addAllRetenueIRSA(fichePaie, connection);
        calculRetenueIRSA(fichePaie);

        return fichePaie;
    }

    public static void main(String[] args) throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        getFichePaie(1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 6), connection).detail();
        connection.close();
    }
}
