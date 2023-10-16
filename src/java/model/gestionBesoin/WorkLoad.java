/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionBesoin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Chalman
 */
public class WorkLoad {
    private Integer idWorkLoad;
    private Besoin besoin;
    private WantedProfile wantedProfile;
    private Integer quantity;
    private Unity unity;
    
///Getters and setters
    public Integer getIdWorkLoad() {
        return idWorkLoad;
    }
    public void setIdWorkLoad(Integer idWorkLoad) {
        this.idWorkLoad = idWorkLoad;
    }

    public Besoin getBesoin() {
        return besoin;
    }
    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }
    
    public WantedProfile getWantedProfile() {
        return wantedProfile;
    }
    public void setWantedProfile(WantedProfile wantedProfile) {
        this.wantedProfile = wantedProfile;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unity getUnity() {
        return unity;
    }
    public void setUnity(Unity unity) {
        this.unity = unity;
    }
    

///Constructors
    public WorkLoad() {   
    }
    public WorkLoad(WantedProfile wantedProfile, Integer quantity, Unity unity) {
        this.wantedProfile = wantedProfile;
        this.quantity = quantity;
        this.unity = unity;
    }
    
    public WorkLoad(Integer idWorkLoad, Besoin besoin, WantedProfile wantedProfile, Integer quantity, Unity unity) {
        this.idWorkLoad = idWorkLoad;
        this.besoin = besoin;
        this.wantedProfile = wantedProfile;
        this.quantity = quantity;
        this.unity = unity;
    }

///Fonctions
    //Creer un workLoad
    public void create(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "INSERT INTO workLoad (id_workload, id_besoin, id_wanted_profile, quantity, id_unity) VALUES (DEFAULT,"+this.getBesoin().getIdBesoin()+","+this.getWantedProfile().getIdWantedProfile()+", "+this.getQuantity()+", "+this.getUnity().getIdUnity()+")";
        System.out.println("insertion : "+req);
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Avoir tous les workloads
    public static ArrayList<WorkLoad> getAll(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "select * from workLoad";
        ResultSet result = work.executeQuery(req);
        ArrayList<WorkLoad> workLoads = new ArrayList<>();
     
        while(result.next()) {
            WorkLoad workLoad = new WorkLoad(result.getInt(1), Besoin.getById(conn,result.getInt(2)), WantedProfile.getById(conn, result.getInt(3)), result.getInt(4), Unity.getById(conn, result.getInt(5)));
            workLoads.add(workLoad);
        }
        
        return workLoads;
    }
    
     //Avoir tous les workloads d'une besoin
    public static ArrayList<WorkLoad> getAllWorkloadBesoin(Connection conn, Besoin besoin)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM workLoad WHERE id_besoin = "+besoin.getIdBesoin();
        ResultSet result = work.executeQuery(req);
        ArrayList<WorkLoad> workLoads = new ArrayList<>();
     
        while(result.next()) {
            WorkLoad workLoad = new WorkLoad(result.getInt(1), Besoin.getById(conn,result.getInt(2)), WantedProfile.getById(conn, result.getInt(3)), result.getInt(4), Unity.getById(conn, result.getInt(5)));
            workLoads.add(workLoad);
        }
        
        return workLoads;
    }
    
    //Recuperer un workLoad par son id
    public static WorkLoad getById(Connection conn, Integer idWorkLoad) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from workLoad where id_workLoad = "+idWorkLoad;
        ResultSet result = work.executeQuery(req);
        WorkLoad workLoad = new WorkLoad();
        int i = 1;
        while(result.next()) {
            workLoad.setIdWorkLoad(result.getInt(1));
            workLoad.setBesoin(Besoin.getById(conn, result.getInt(2)));
            workLoad.setWantedProfile(WantedProfile.getById(conn, result.getInt(3)));
            workLoad.setQuantity(result.getInt(4));
            workLoad.setUnity(Unity.getById(conn,result.getInt(5)));
        }
        
        return workLoad;
    }
    
    //Modifier un workLoad
    public void update(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE workLoad SET besoin="+this.getBesoin().getIdBesoin()+", wanted_profile="+this.getWantedProfile().getPoste()+", quantity="+this.getQuantity()+", unity="+this.getUnity().getIdUnity()+" WHERE id_workLoad="+this.getIdWorkLoad();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Supprimer un workLoad
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from workLoad WHERE id_workLoad="+this.getIdWorkLoad();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
     //Inserer une liste des workLoad
    public static void insertListWorkLoad(Connection conn, List<WorkLoad> listWorkLoad, Besoin besoin) throws Exception {
        for(int i = 0; i < listWorkLoad.size(); i++) {
            listWorkLoad.get(i).setBesoin(besoin);
            listWorkLoad.get(i).create(conn);
        }
    }
}
