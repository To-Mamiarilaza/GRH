/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.candidature;

import java.util.Date;
import model.gestionProfile.Adresse;
import model.gestionProfile.Sexe;

/**
 *
 * @author Fy Botas
 */
public class PersonnalInformation {
    String name;
    String firstName;
    Date birthDate;
    Adresse adresse;
    String email;
    String telephone;
    Sexe sexe;

    public PersonnalInformation(String name, String firstName, Date birthDate, Adresse adresse, String email, String telephone, Sexe sexe) {
        this.name = name;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.sexe = sexe;
    }

    public PersonnalInformation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    
    
}
