/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionProfile;

import framework.database.annotation.Champs;
import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Model;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;
import model.requis.Service;

/**
 *
 * @author Chalman
 */
public class WantedProfile extends Model {

    private int idWantedProfile;
    private String poste;
    private Service service;
    private int status;
    private List<DiplomeNote> diplomeNote;
    private List<ExperienceNote> experienceNote;
    private List<SalaireNote> salaireNote;
    private List<SexeNote> sexeNote;
    private List<AdresseNote> adresseNote;

///Getters and setters
    public int getIdWantedProfile() {
        return idWantedProfile;
    }
    public void setIdWantedProfile(int idWantedProfile) {
        this.idWantedProfile = idWantedProfile;
    }

    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    
    
    public String getPoste() {
        return poste;
    }
    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public List<DiplomeNote> getDiplomeNote() {
        return diplomeNote;
    }
    public void setDiplomeNote(List<DiplomeNote> diplomeNote) {
        this.diplomeNote = diplomeNote;
    }

    public List<ExperienceNote> getExperienceNote() {
        return experienceNote;
    }
    public void setExperienceNote(List<ExperienceNote> experienceNote) {
        this.experienceNote = experienceNote;
    }
    
    public List<SalaireNote> getSalaireNote() {
        return salaireNote;
    }
    public void setSalaireNote(List<SalaireNote> salaireNote) {
        this.salaireNote = salaireNote;
    }

    public List<SexeNote> getSexeNote() {
        return sexeNote;
    }
    public void setSexeNote(List<SexeNote> sexeNote) {
        this.sexeNote = sexeNote;
    }

    public List<AdresseNote> getAdresseNote() {
        return adresseNote;
    }
    public void setAdresseNote(List<AdresseNote> adresseNote) {
        this.adresseNote = adresseNote;
    }

    //Constructors
    public WantedProfile() {
    }

    public WantedProfile(String poste, int status) {
        this.poste = poste;
        this.status = status;
    }

    public WantedProfile(String poste, Service idService) {
        this.poste = poste;
        this.service = service;
    }

    public WantedProfile(int idWaantedProfile, String poste, Service idService) {
        this.idWantedProfile = idWaantedProfile;
        this.poste = poste;
        this.service = idService;
    }

    public WantedProfile(int idWaantedProfile, String poste, Service idService, List<DiplomeNote> diplomeNote, List<ExperienceNote> experienceNote, List<SalaireNote> salaireNote, List<SexeNote> sexeNote, List<AdresseNote> adresseNote) {
        this.idWantedProfile = idWaantedProfile;
        this.poste = poste;
        this.service = idService;
        this.diplomeNote = diplomeNote;
        this.experienceNote = experienceNote;
        this.salaireNote = salaireNote;
        this.sexeNote = sexeNote;
        this.adresseNote = adresseNote;
    }

///Fonctions
    // delete wanted_profile
    public void deleteWantedProfile(int indice, Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "update wanted_profile set status = 2 where id_wanted_profile = " + indice;
            System.out.println(requete);
            Statement s = con.createStatement();
            s.executeUpdate(requete);
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    // avoir la liste des postes
    public List<WantedProfile> getAll(Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<WantedProfile> listePoste = new ArrayList<>();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * from wanted_profile where status = 1");
            while (rs.next()) {
                listePoste.add(new WantedProfile(rs.getInt(1), rs.getString(2), new Service(rs.getInt(3), 1)));
            }

            return listePoste;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    //find poste par l'id
    public List<String> getPostById(Connection con) throws Exception {
        boolean b = true;
        try {
            List<Integer> lsIndice = this.getIdWantedProfile(null);
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<String> listePoste = new ArrayList<>();
            Statement s = con.createStatement();
            for (int i = 0; i < lsIndice.size(); i++) {
                ResultSet rs = s.executeQuery("SELECT poste from wanted_profile where id_wanted_profile = " + lsIndice.get(i));
                while (rs.next()) {
                    listePoste.add(rs.getString(1));
                }
            }
            return listePoste;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    //avoir l'id du dernier wantedprofile 
    public int getLastId(Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            int lastId = 0;
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT max(id_wanted_profile) as max from wanted_profile where status = 1");
            while (rs.next()) {
                lastId = rs.getInt(1);
            }
            return lastId;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    //avoir toutes les indices des profiles recherchés
    public List<Integer> getIdWantedProfile(Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            List<Integer> lsIndice = new ArrayList<>();
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT id_Wanted_Profile from wanted_profile where status = 1");
            while (rs.next()) {
                lsIndice.add(rs.getInt(1));
            }
            return lsIndice;
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }

    //Creer un profil voulu dans la base
    public void createWantedProfile(Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into wanted_profile values (DEFAULT,' " + this.getPoste() + " '," + this.getService().getIdService() + ", 1)";
            System.out.println(requete);
            Statement s = con.createStatement();
            s.executeUpdate(requete);
        } catch (Exception exe) {
            throw exe;
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
    }
    
    //Recuperer un wantedProfil par son id
    public static WantedProfile getById(Connection conn, Integer idWantedProfile) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from wanted_profile where id_wanted_profile = "+idWantedProfile;
        ResultSet result = work.executeQuery(req);
        WantedProfile wp = new WantedProfile();
        while(result.next()) {
            wp.setIdWantedProfile(result.getInt(1));
            wp.setPoste(result.getString(2));
            wp.setService(Service.getById(conn, result.getInt(3)));
            wp.setStatus(result.getInt(4));
        }
        
        return wp;
    }
    
   
}
