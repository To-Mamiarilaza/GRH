/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

/**
 *
 * @author To Mamiarilaza
 */
public class OutWorkParameter {
    /// field
    int day;
    int month;
    double pourcentage;
    
    /// getter and setter

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    /// constructor

    public OutWorkParameter(int day, int month, double pourcentage) {
        this.day = day;
        this.month = month;
        this.pourcentage = pourcentage;
    }

    public OutWorkParameter() {
    }
    
}
