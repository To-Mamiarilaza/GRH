/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.gestionBesoin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author chalman
 */
public class VExperienceWantedProfile {
    private WantedProfile wantedProfile;
    private Experience experience;
    private Double note;
    
///Getter et setters
    public WantedProfile getWantedProfile() {
        return wantedProfile;
    }
    public void setWantedProfile(WantedProfile wantedProfile) {
        this.wantedProfile = wantedProfile;
    }

    public Experience getExperience() {
        return experience;
    }
    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public Double getNote() {
        return note;
    }
    public void setNote(Double note) {
        this.note = note;
    }
    
///Contructors
    public VExperienceWantedProfile() {
    }

    public VExperienceWantedProfile(WantedProfile wantedProfile, Experience experience, Double note) {
        this.wantedProfile = wantedProfile;
        this.experience = experience;
        this.note = note;
    }
    
///Fonctions de classe
    //Recuperer les diplomes d'un wanted profile
    public static ArrayList<VExperienceWantedProfile> getExperienceWantedProfile(Connection conn, WantedProfile wp) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from v_experience_wanted_profile where id_wanted_profile = "+wp.getIdWantedProfile()+" AND id_service="+wp.getService().getIdService();
        ResultSet result = work.executeQuery(req);
        ArrayList<VExperienceWantedProfile> vewps = new ArrayList<>();
        while(result.next()) {
            VExperienceWantedProfile vewp = new VExperienceWantedProfile(WantedProfile.getById(conn, result.getInt("id_wanted_profile")), Experience.getById(conn, result.getInt("id_experience")), result.getDouble("note"));
            vewps.add(vewp);
        }
        
        return vewps;
    }
}
