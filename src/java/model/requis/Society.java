/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.requis;

import framework.database.annotation.Champs;
import model.*;


/**
 *
 * @author Chalman
 */
public class Society extends Model {
    @Champs
    private String name;
    @Champs  
    private String  adresse;
    @Champs
    private String nif;
    @Champs
    private String contact;
    @Champs
    private String secteur;
    @Champs
    private String legal_status;
    
///Getters and setters
    public String getName (){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSecteur() {
        return secteur;
    }
    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getLegal_status() {
        return legal_status;
    }
    public void setLegal_status(String legal_status) {   
        this.legal_status = legal_status;
    }

///Constructors
    public Society() {    
    }

    public Society(String name, String adresse, String nif, String contact, String secteur, String legal_status) {
        this.name = name;
        this.adresse = adresse;
        this.nif = nif;
        this.contact = contact;
        this.secteur = secteur;
        this.legal_status = legal_status;
    }
    
///Fonctions de la classe
    public static Society getSociety() throws Exception {
        return null;
    }
}
