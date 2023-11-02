/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

/**
 *
 * @author To Mamiarilaza
 */
public class WeekEndParameter {
    /// field
    int day;
    double pourcentage;
    
    /// getter and setter

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    /// constructor

    public WeekEndParameter(int day, double pourcentage) {
        this.day = day;
        this.pourcentage = pourcentage;
    }

    public WeekEndParameter() {
    }
    
}
