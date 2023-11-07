/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.retenue;

/**
 *
 * @author To Mamiarilaza
 */
public class Organisme {
    /// Field
    int idOrganisme;
    String nomOrganisme;
    String description;
    int etat;
    
    /// Getter and setter

    public int getIdOrganisme() {
        return idOrganisme;
    }

    public void setIdOrganisme(int idOrganisme) {
        this.idOrganisme = idOrganisme;
    }

    public String getNomOrganisme() {
        return nomOrganisme;
    }

    public void setNomOrganisme(String nomOrganisme) {
        this.nomOrganisme = nomOrganisme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    /// Constructor

    public Organisme(int idOrganisme, String nomOrganisme, String description, int etat) {
        this.idOrganisme = idOrganisme;
        this.nomOrganisme = nomOrganisme;
        this.description = description;
        this.etat = etat;
    }
    
    /// methods
}
