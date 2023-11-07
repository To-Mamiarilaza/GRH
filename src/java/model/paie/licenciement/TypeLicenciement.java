/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.licenciement;

/**
 *
 * @author To Mamiarilaza
 */
public class TypeLicenciement {
    /// field
    int idTypeLicenciement;
    String typeLicenciement;
    
    /// getter and setter

    public int getIdTypeLicenciement() {
        return idTypeLicenciement;
    }

    public void setIdTypeLicenciement(int idTypeLicenciement) {
        this.idTypeLicenciement = idTypeLicenciement;
    }

    public String getTypeLicenciement() {
        return typeLicenciement;
    }

    public void setTypeLicenciement(String typeLicenciement) {
        this.typeLicenciement = typeLicenciement;
    }
    
    /// constructor

    public TypeLicenciement(int idTypeLicenciement, String typeLicenciement) {
        this.idTypeLicenciement = idTypeLicenciement;
        this.typeLicenciement = typeLicenciement;
    }
    
}
