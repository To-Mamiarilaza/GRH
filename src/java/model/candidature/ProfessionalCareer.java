/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.candidature;

import java.util.List;
import model.gestionProfile.Experience;

/**
 *
 * @author To Mamiarilaza
 */
public class ProfessionalCareer {
/// field
    Experience experience;
    List<Career> careers;
    
/// getter and setter

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }
    
/// constructor

    public ProfessionalCareer(Experience experience, List<Career> careers) {
        this.experience = experience;
        this.careers = careers;
    }

    public ProfessionalCareer() {
    }
    
}
