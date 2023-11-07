/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.paie.heuresup.parameter;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.paie.heuresup.HeureSupplementaire;

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

    private HashMap<DayOfWeek, Integer> dayValue = new HashMap<>();

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

        // Valeurs des jours par semaine
        dayValue.put(DayOfWeek.MONDAY, 1);
        dayValue.put(DayOfWeek.TUESDAY, 2);
        dayValue.put(DayOfWeek.WEDNESDAY, 3);
        dayValue.put(DayOfWeek.THURSDAY, 4);
        dayValue.put(DayOfWeek.FRIDAY, 5);
        dayValue.put(DayOfWeek.SATURDAY, 6);
        dayValue.put(DayOfWeek.SUNDAY, 7);
    }

    public HeureSupplementaireParameter() {
        this.durationParameters = new ArrayList<>();
        this.weekEndParameters = new ArrayList<>();
        this.outWorkParameters = new ArrayList<>();
        this.nightWorkParameters = new ArrayList<>();

        // Valeurs des jours par semaine
        dayValue.put(DayOfWeek.MONDAY, 1);
        dayValue.put(DayOfWeek.TUESDAY, 2);
        dayValue.put(DayOfWeek.WEDNESDAY, 3);
        dayValue.put(DayOfWeek.THURSDAY, 4);
        dayValue.put(DayOfWeek.FRIDAY, 5);
        dayValue.put(DayOfWeek.SATURDAY, 6);
        dayValue.put(DayOfWeek.SUNDAY, 7);
    }

    /// methods
    // Vérifie si une heure supplementaire est dans un jour ferrier
    public boolean checkOutWorkDay(HeureSupplementaire heureSup) {
        for (OutWorkParameter outWorkParameter : getOutWorkParameters()) {
            if (heureSup.getDebut().getDayOfMonth() == outWorkParameter.getDay() && heureSup.getDebut().getMonthValue() == outWorkParameter.getMonth()) {
                return true;
            }
        }
        return false;
    }

    // Vérifie si une heure supplementaire est dans un week end
    public boolean checkWeekEndDay(HeureSupplementaire heureSup) {
        for (WeekEndParameter weekEndParameter : getWeekEndParameters()) {
            if (dayValue.get(heureSup.getDebut().getDayOfWeek()) == weekEndParameter.getDay()) {
                return true;
            }
        }
        return false;
    }

    // Vérifie si une heure supplementaire a une partie nocturne et renvoyer sa durée
    public double getNightWorkDuration(HeureSupplementaire heureSup) {
        LocalTime debutHS = LocalTime.of(heureSup.getDebut().getHour(), heureSup.getDebut().getMinute());
        LocalTime finHS = LocalTime.of(heureSup.getFin().getHour(), heureSup.getFin().getMinute());

        for (NightWorkParameter nightWorkParameter : getNightWorkParameters()) {
            boolean includeAll = (debutHS.isAfter(nightWorkParameter.getDebut()) || debutHS.equals(nightWorkParameter.getDebut())) && (finHS.isBefore(nightWorkParameter.getFin()) || finHS.equals(nightWorkParameter.getFin()));
            boolean firstPartInclude = (debutHS.isAfter(nightWorkParameter.getDebut()) || debutHS.equals(nightWorkParameter.getDebut())) && debutHS.isBefore(nightWorkParameter.getFin()) && finHS.isAfter(nightWorkParameter.getFin());
            boolean secondPartInclude = debutHS.isBefore(nightWorkParameter.getDebut()) && (finHS.isBefore(nightWorkParameter.getFin()) || finHS.equals(nightWorkParameter.getFin())) && finHS.isAfter(nightWorkParameter.getDebut());

            if (includeAll) {
                int duree = (int) ChronoUnit.MINUTES.between(debutHS, finHS);
                return (double) duree / 60;
            } else if (firstPartInclude) {
                int duree = (int) ChronoUnit.MINUTES.between(debutHS, nightWorkParameter.getFin());
                return (double) duree / 60;
            } else if (secondPartInclude) {
                int duree = (int) ChronoUnit.MINUTES.between(nightWorkParameter.getDebut(), finHS);
                return (double) duree / 60;
            }
        }

        return 0;
    }

    // Pour avoir le parametre de majoration 
    public DurationParameter getDurationMajore(double pourcentage) {
        for (DurationParameter durationParameter : getDurationParameters()) {
            if (durationParameter.getPourcentage() == pourcentage) {
                return durationParameter;
            }
        }
        return null;
    }

    // Prendre les parametres de duration
    public static List<DurationParameter> getDurationParameter(Connection connection) throws Exception {
        List<DurationParameter> paramList = new ArrayList<>();

        String query = "SELECT * FROM heure_sup_duration_parameter";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            int heureDebut = resultset.getInt("heure_debut");
            int heureFin = resultset.getInt("heure_fin");
            double pourcentage = resultset.getDouble("pourcentage");

            paramList.add(new DurationParameter(heureDebut, heureFin, pourcentage));
        }

        statement.close();
        resultset.close();

        return paramList;
    }

    // Prendre les parametres de travail nocturne
    public static List<NightWorkParameter> getNightWorkParameter(Connection connection) throws Exception {
        List<NightWorkParameter> paramList = new ArrayList<>();

        String query = "SELECT * from night_work_parameter";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            LocalTime debutHeure = resultset.getTime("debut_heure").toLocalTime();
            LocalTime finHeure = resultset.getTime("fin_heure").toLocalTime();
            double pourcentage = resultset.getDouble("pourcentage");

            paramList.add(new NightWorkParameter(debutHeure, finHeure, pourcentage));

        }

        statement.close();
        resultset.close();

        return paramList;
    }

    // Prendre les parametres de travail week end
    public static List<WeekEndParameter> getWeekEndParameter(Connection connection) throws Exception {
        List<WeekEndParameter> paramList = new ArrayList<>();

        String query = "SELECT * from week_end_parameter";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            int day = resultset.getInt("day");
            double pourcentage = resultset.getDouble("pourcentage");

            paramList.add(new WeekEndParameter(day, pourcentage));

        }

        statement.close();
        resultset.close();

        return paramList;
    }

    // Prendre les parametres en jour férier
    public static List<OutWorkParameter> getOutWorkParameter(Connection connection) throws Exception {
        List<OutWorkParameter> paramList = new ArrayList<>();

        String query = "SELECT * from out_work_parameter";
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery(query);

        while (resultset.next()) {
            int day = resultset.getInt("day");
            int month = resultset.getInt("month");
            double pourcentage = resultset.getDouble("pourcentage");

            paramList.add(new OutWorkParameter(day, month, pourcentage));

        }

        statement.close();
        resultset.close();

        return paramList;
    }

    // Fonction pour avoir toutes les parametres
    public static HeureSupplementaireParameter getHeureSupplementaireParameter(Connection connection) throws SQLException, Exception {
        HeureSupplementaireParameter heureSupParam = new HeureSupplementaireParameter();

        heureSupParam.setDurationParameters(getDurationParameter(connection));
        heureSupParam.setNightWorkParameters(getNightWorkParameter(connection));
        heureSupParam.setWeekEndParameters(getWeekEndParameter(connection));
        heureSupParam.setOutWorkParameters(getOutWorkParameter(connection));

        return heureSupParam;
    }

    // Avoir en detail les parametres
    public void detail() {
        System.out.println("Les parametres heures supplementaires :");

        System.out.println("DURATION PARAMETER :");
        for (DurationParameter durationParameter : getDurationParameters()) {
            System.out.println("- " + durationParameter.getHeureDebut() + "  " + durationParameter.getHeureFin() + "  " + durationParameter.getPourcentage());
        }

        System.out.println("NIGHT WORK PARAMETER :");
        for (NightWorkParameter nightParameter : getNightWorkParameters()) {
            System.out.println("- " + nightParameter.getDebut() + "  " + nightParameter.getFin() + "  " + nightParameter.getPourcentage());
        }

        System.out.println("WEEK END PARAMETER :");
        for (WeekEndParameter weekEndParameter : getWeekEndParameters()) {
            System.out.println("- " + weekEndParameter.getDay() + "  " + weekEndParameter.getPourcentage());
        }

        System.out.println("OUT WORK PARAMETER :");
        for (OutWorkParameter outWorkParameter : getOutWorkParameters()) {
            System.out.println("- " + outWorkParameter.getDay() + "  " + outWorkParameter.getMonth() + "  " + outWorkParameter.getPourcentage());
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        HeureSupplementaireParameter parameter = getHeureSupplementaireParameter(connection);
        connection.close();

        HeureSupplementaire test = new HeureSupplementaire(0, 0, LocalDateTime.of(2023, 11, 22, 0, 0), LocalDateTime.of(2023, 11, 5, 23, 59), 0);

    }
}
