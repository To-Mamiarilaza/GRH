/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.candidature;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.gestionProfile.Adresse;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
public class Career {
/// field
    Date startDate;
    Date endDate;
    String society;
    String poste;
    List<String> tasks;       // Les différentes taches sont séparés par des ";" dans la base et on va devoir les décomposer apres
    
/// getter and setter

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
    
    public String formatTask(){
        String task = "";
        for(int i=0; i<this.getTasks().size(); i++){
            task += this.getTasks().get(i).concat(";");
        }
        return task;
    }

    public void create(Connection con, int idCandidature) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into professional_career values (DEFAULT, "+ idCandidature +", '"+ this.getStartDate()+"', '"+ this.getEndDate() +"', '"+ this.getSociety() +"', '"+ this.getPoste() +"', '"+ this.formatTask() +"')";
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
    
/// contructor

    public Career(Date startDate, Date endDate, String society, String poste, List<String> tasks) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.society = society;
        this.poste = poste;
        this.tasks = tasks;
    }
    
    
/// methods
    public Career() {
    }
    
    
    //Recuperer une carriere par son id
    public static Career getById(Connection conn, Integer idCareer) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from professional_career where id_Career = "+idCareer;
        ResultSet result = work.executeQuery(req);
        Career career = new Career();
        
        while(result.next()) {
            career.setStartDate(result.getDate("start_date"));
            career.setEndDate(result.getDate("end_date"));
            career.setSociety("society");
            career.setPoste("poste");
            List<String> tasks = Career.getList(result.getString("task"));
            career.setTasks(tasks);
        }
        
        return career;
    }
    
    //Tansformer le string en une liste de string
    public static List<String> getList(String listes) throws Exception {
        List<String> lists = new ArrayList<>();
        String [] listesSplitter = listes.split(";");
        for(int i = 0; i < listesSplitter.length; i++) {
            lists.add(listesSplitter[i]);
        }
        
        return lists;
    }
    
      
    //Avoir tous les annonces
    public static ArrayList<Career> getCareerCandidat(Connection conn, Integer idCandidat)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM professional_career WHERE id_candidature="+idCandidat;
        ResultSet result = work.executeQuery(req);
        ArrayList<Career> careers = new ArrayList<>();

        while(result.next()) {
            List<String> tasks = Career.getList(result.getString("task"));
            Career career = new Career(result.getDate("start_date"), result.getDate("end_date"), result.getString("society"), result.getString("poste"), tasks);
            careers.add(career);
        }
        
        return careers;
    }

}
