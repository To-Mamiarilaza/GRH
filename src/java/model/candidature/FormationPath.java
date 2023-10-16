/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.candidature;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import model.gestionProfile.Diplome;

/**
 *
 * @author To Mamiarilaza
 */
public class FormationPath {
/// field
    Diplome diplome;
    List<Formation> formations;
    
/// getter and setter

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
    
/// constructor

    public FormationPath(Diplome diplome, List<Formation> formations) {
        this.diplome = diplome;
        this.formations = formations;
    }

    public FormationPath() {
    }
    
}
