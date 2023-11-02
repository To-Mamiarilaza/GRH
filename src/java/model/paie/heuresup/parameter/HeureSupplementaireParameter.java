/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class HeureSupplementaireParameter {
    /// field
    List<DurationParameter> durationParameters;
    List<WeekEndParameter> weekEndParameters;
    List<OutWorkParameter> outWorkParameters;
    List<NightWorkParameter> nightWorkParameters;
    
    /// getter and setter

    public List<DurationParameter> getDurationParameters() {
        return durationParameters;
    }

    public void setDurationParameters(List<DurationParameter> durationParameters) {
        this.durationParameters = durationParameters;
    }

    public List<WeekEndParameter> getWeekEndParameters() {
        return weekEndParameters;
    }

    public void setWeekEndParameters(List<WeekEndParameter> weekEndParameters) {
        this.weekEndParameters = weekEndParameters;
    }

    public List<OutWorkParameter> getOutWorkParameters() {
        return outWorkParameters;
    }

    public void setOutWorkParameters(List<OutWorkParameter> outWorkParameters) {
        this.outWorkParameters = outWorkParameters;
    }

    public List<NightWorkParameter> getNightWorkParameters() {
        return nightWorkParameters;
    }

    public void setNightWorkParameters(List<NightWorkParameter> nightWorkParameters) {
        this.nightWorkParameters = nightWorkParameters;
    }

    /// constructor
    
    public HeureSupplementaireParameter(List<DurationParameter> durationParameters, List<WeekEndParameter> weekEndParameters, List<OutWorkParameter> outWorkParameters, List<NightWorkParameter> nightWorkParameters) {
        this.durationParameters = durationParameters;
        this.weekEndParameters = weekEndParameters;
        this.outWorkParameters = outWorkParameters;
        this.nightWorkParameters = nightWorkParameters;
    }
    
}
