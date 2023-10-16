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
import model.gestionProfile.WantedProfile;

/**
 *
 * @author chalman
 */
public class VDiplomeWantedProfile {
    private WantedProfile wantedProfile;
    private Diplome diplome;
    private Double note;
    
///Getters et setters
    public WantedProfile getWantedProfile() {
        return wantedProfile;
    }
    public void setWantedProfile(WantedProfile wantedProfile) {
        this.wantedProfile = wantedProfile;
    }

    public Diplome getDiplome() {
        return diplome;
    }
    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public Double getNote() {
        return note;
    }
    public void setNote(Double note) {
        this.note = note;
    }
    
///Constructors
    public VDiplomeWantedProfile() {
    }

    public VDiplomeWantedProfile(WantedProfile wantedProfile, Diplome diplome, Double note) {
        this.wantedProfile = wantedProfile;
        this.diplome = diplome;
        this.note = note;
    }
    
///Fonctions de la classe
    //Recuperer les diplomes d'un wanted profile
    public static ArrayList<VDiplomeWantedProfile> getDiplomeWantedProfile(Connection conn, WantedProfile wp) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from v_diplome_wanted_profile where id_wanted_profile = "+wp.getIdWantedProfile()+" AND id_service="+wp.getService().getIdService();
        ResultSet result = work.executeQuery(req);
        ArrayList<VDiplomeWantedProfile> vdwps = new ArrayList<>();
        while(result.next()) {
            VDiplomeWantedProfile vdwp = new VDiplomeWantedProfile(WantedProfile.getById(conn, result.getInt("id_wanted_profile")), Diplome.getById(conn, result.getInt("id_diplome")), result.getDouble("note"));
            vdwps.add(vdwp);
        }
        
        return vdwps;
    }
}
