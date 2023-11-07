/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.conge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.conge.Conge;

/**
 *
 * @author To Mamiarilaza
 */
public class CongeFilter {
    public static final int ETAT_ANNULE = 0;
    public static final int ETAT_REFUSE = 1;
    public static final int ETAT_ATTENTE = 2;
    public static final int ETAT_VALIDE_CHEF = 3;
    public static final int ETAT_VALIDE_RH = 4;
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterDemandeConge(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getEtat() == ETAT_ATTENTE && conge.getDateDebut().isAfter(LocalDate.now())) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterRHDemandeConge(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getEtat() == ETAT_VALIDE_CHEF && conge.getDateDebut().isAfter(LocalDate.now())) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterCurrentConge(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getDateDebutReel() != null && conge.getEtat() == ETAT_VALIDE_RH && (conge.getDateDebutReel().isBefore(LocalDate.now()) || conge.getDateDebutReel().isEqual(LocalDate.now())) && conge.getDateFinReel() == null) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterValidateCongeByChef(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getEtat() == ETAT_VALIDE_CHEF) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterValidateCongeByRH(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getEtat() == ETAT_VALIDE_RH) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    /// Classe de service pour filtrer 
    public static List<Conge> filterRefusedConge(List<Conge> initialList)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getEtat() == ETAT_REFUSE) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
    
    // Prendre le conge dans une année
    public static List<Conge> filterYearConge(List<Conge> initialList, int year)  {
        List<Conge> resultat = new ArrayList<>();
        
        for (Conge conge : initialList) {
            if (conge.getDateDebutDemande().getYear() == year) {
                resultat.add(conge);
            }
        }
        
        return resultat;
    }
}
