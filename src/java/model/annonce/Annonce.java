/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.annonce;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.gestionBesoin.Besoin;
import model.requis.Service;

/**
 *
 * @author chalman
 */
public class Annonce {
    private Integer idAnnonce;
    private Besoin besoin;
    private Service service;
    private String nomAnnonce;
    private Date dateAnnonce;
    private Integer status;
    
///Getters et setters 
    public Integer getIdAnnonce() {
        return idAnnonce;
    }
    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Besoin getBesoin() {
        return besoin;
    }
    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }
    
    public Date getDateAnnonce() {
        return dateAnnonce;
    }
    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
///Contructors

    public Annonce() {
    }

    public Annonce(Integer idAnnonce, Besoin besoin, Service service, String nomAnnonce, Date dateAnnonce, Integer status) {
        this.idAnnonce = idAnnonce;
        this.besoin = besoin;
        this.service = service;
        this.nomAnnonce = nomAnnonce;
        this.dateAnnonce = dateAnnonce;
        this.status = status;
    }
    
    public Annonce(Besoin besoin, Service service, String nomAnnonce, Date dateAnnonce, Integer status) {
        this.besoin = besoin;
        this.service = service;
        this.nomAnnonce = nomAnnonce;
        this.dateAnnonce = dateAnnonce;
        this.status = status;
    }
///Fonctions
     //Creer une annonce
    public void create(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "INSERT INTO annonce(id_annonce, id_besoin, id_service, nom_annonce, date_annonce, status) VALUES (default, "+this.getBesoin().getIdBesoin()+", "+this.getService().getIdService()+", '"+this.getNomAnnonce()+"', '"+this.getDateAnnonce()+"', 1)";
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Avoir tous les annonces
    public static ArrayList<Annonce> getAll(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "SELECT * FROM annonce WHERE status!=0 ORDER BY id_annonce DESC";
        ResultSet result = work.executeQuery(req);
        ArrayList<Annonce> annonces = new ArrayList<>();

        while(result.next()) {
            Annonce annonce = new Annonce(result.getInt("id_annonce"), Besoin.getById(conn, result.getInt("id_besoin")), Service.getById(conn,result.getInt("id_service")), result.getString("nom_annonce"), result.getDate("date_annonce"), result.getInt("status"));
            annonces.add(annonce);
        }
        
        return annonces;
    }
    
    //Avoir tous les annonces selon le status 
    public static ArrayList<Annonce> getAnnonceByStatus(Connection conn, String status, Service service)  throws Exception { 
        if(status == null) {
            status = "1";
        }
        Statement work = conn.createStatement();
        String req = "SELECT * FROM annonce WHERE status = "+status+ " AND id_service="+service.getIdService();
        ResultSet result = work.executeQuery(req);
        ArrayList<Annonce> annonces = new ArrayList<>();

        while(result.next()) {
            Annonce annonce = new Annonce(result.getInt("id_annonce"), Besoin.getById(conn, result.getInt("id_besoin")), Service.getById(conn,result.getInt("id_service")), result.getString("nom_annonce"), result.getDate("date_annonce"),result.getInt("status"));
            annonces.add(annonce);
        }
        
        return annonces;
    }
    
    //Recuperer une annonce par son id
    public static Annonce getById(Connection conn, Integer idAnnonce) throws Exception {
        Statement work = conn.createStatement();
        String req = "select * from annonce where id_annonce = "+idAnnonce;
        ResultSet result = work.executeQuery(req);
        Annonce annonce = new Annonce();
        int i = 1;
        while(result.next()) {
            annonce.setIdAnnonce(result.getInt("id_annonce"));
            annonce.setBesoin(Besoin.getById(conn, result.getInt("id_besoin")));
            annonce.setService(Service.getById(conn, result.getInt("id_service")));
            annonce.setNomAnnonce(result.getString("nom_annonce"));
            annonce.setDateAnnonce(result.getDate("date_annonce"));
            annonce.setStatus(result.getInt("status"));
        }
        
        return annonce;
    }
    
    //Modifier un besoin
    public void update(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "UPDATE annonce SET id_besoin="+this.getBesoin().getIdBesoin()+", id_service="+this.getService().getIdService()+", nom_annonce='"+this.getNomAnnonce()+"', date_annonce='"+this.getDateAnnonce()+"', status="+this.getStatus()+" WHERE id_annonce="+this.getIdAnnonce();
        work.execute(req);
        conn.setAutoCommit(true);
    }
    
    //Supprimer une annonce
    public void delete(Connection conn)  throws Exception { 
        Statement work = conn.createStatement();
        String req = "DELETE from annonce WHERE id_annonce="+this.getIdAnnonce();
        work.execute(req);
        conn.setAutoCommit(true);
    }
}
