/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.embauchement;

import framework.database.utilitaire.GConnection;
import static framework.database.utilitaire.GConnection.connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.employe.Employe;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author chalman
 */
public class Contrat {

    private Integer idContrat;
    private WorkLocation workLocation;
    private Candidature candidature;
    private Province province;
    private Integer periodeEssai;
    private Double salary;
    private Date startDate;
    private Date endDate;
    private Date contratDate;
    private Integer isCnaps;
    private Integer isSanitaire;
    private Integer status;
    private String path;
    private Employe superieur;
    private WantedProfile poste;
    private int idEmploye;

///Getters et setters
    public Integer getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public WorkLocation getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(WorkLocation workLocation) {
        this.workLocation = workLocation;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Integer getPeriodeEssai() {
        return periodeEssai;
    }

    public void setPeriodeEssai(Integer periodeEssai) {
        this.periodeEssai = periodeEssai;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getContratDate() {
        return contratDate;
    }

    public void setContratDate(Date contratDate) {
        this.contratDate = contratDate;
    }

    public Integer getIsCnaps() {
        return isCnaps;
    }

    public void setIsCnaps(Integer isCnaps) {
        this.isCnaps = isCnaps;
    }

    public Integer getIsSanitaire() {
        return isSanitaire;
    }

    public void setIsSanitaire(Integer isSanitaire) {
        this.isSanitaire = isSanitaire;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Employe getSuperieur() {
        return superieur;
    }

    public void setSuperieur(Employe superieur) {
        this.superieur = superieur;
    }

    public WantedProfile getPoste() {
        return poste;
    }

    public void setPoste(WantedProfile poste) {
        this.poste = poste;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

///Contructors
    public Contrat() {
    }

    public Contrat(WorkLocation workLocation, Candidature candidature, Province province, Double salary, Date startDate, Date endDate, Integer status, WantedProfile wp) {
        this.workLocation = workLocation;
        this.candidature = candidature;
        this.province = province;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.poste = wp;
    }

    public Contrat(Integer idContrat, WorkLocation workLocation, Candidature candidature, Province province, Integer periodeEssai, Double salary, Date startDate, Date endDate, Date contratDate, Integer isCnaps, Integer isSanitaire, Integer status, String path, Employe superieur, WantedProfile wp) {
        this.idContrat = idContrat;
        this.workLocation = workLocation;
        this.candidature = candidature;
        this.province = province;
        this.periodeEssai = periodeEssai;
        this.salary = salary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contratDate = contratDate;
        this.isCnaps = isCnaps;
        this.isSanitaire = isSanitaire;
        this.status = status;
        this.path = path;
        this.superieur = superieur;
        this.poste = wp;
    }

///Focntions de classe
    // Enregistre le contratdans la base de données
    public void save(Connection connection) throws Exception {
        String query = "INSERT INTO contrat ( id_work_location, id_candidature, id_province, salary, periode_essai, start_date, end_date, contrat_date, is_cnaps, is_sanitaire, status, path, id_superieur, poste) VALUES (?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?)";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, getWorkLocation().getIdWorkLocation());
            statement.setInt(2, getCandidature().getIdCandidature());
            statement.setInt(3, getProvince().getIdProvince());
            statement.setDouble(4, getSalary());
            statement.setInt(5, getPeriodeEssai());
            statement.setDate(6, getStartDate());
            statement.setDate(7, getEndDate());
            statement.setInt(8, getIsCnaps());
            statement.setInt(9, getIsSanitaire());
            statement.setInt(10, getStatus());
            statement.setString(11, getPath());
            statement.setInt(12, getSuperieur().getIdEmploye());
            statement.setInt(13, getPoste().getIdWantedProfile());

            statement.executeUpdate();

            statement.close();

            if (closeable) {
                connection.commit();
                connection.close();
            }

        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }

    // Pour avoir un contratpar son ID
    public static Contrat getById(int id) throws Exception {
        Contrat contrat = new Contrat();
        String query = "SELECT * FROM contrat_employe WHERE id_contrat= %d";
        query = String.format(query, id);
        System.out.println("Query : " + query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                contrat.setIdContrat(resultset.getInt("id_contrat"));
                contrat.setWorkLocation(WorkLocation.getById(resultset.getInt("id_work_location")));
                contrat.setCandidature(Candidature.getById(connection, resultset.getInt("id_candidature")));
                contrat.setProvince(Province.getById(resultset.getInt("id_province")));
                contrat.setSalary(resultset.getDouble("salary"));
                contrat.setPeriodeEssai(resultset.getInt("periode_essai"));
                contrat.setStartDate(resultset.getDate("start_date"));
                contrat.setEndDate(resultset.getDate("end_date"));
                contrat.setContratDate(resultset.getDate("contrat_date"));
                contrat.setIsCnaps(resultset.getInt("is_cnaps"));
                contrat.setIsSanitaire(resultset.getInt("is_sanitaire"));
                contrat.setStatus(resultset.getInt("status"));
                contrat.setPath(resultset.getString("path"));
                contrat.setSuperieur(Employe.getById(resultset.getInt("id_superieur")));
                contrat.setIdEmploye(resultset.getInt("id_employe"));
                contrat.setPoste(WantedProfile.getById(connection, resultset.getInt("poste")));
            }

            resultset.close();
            statement.close();
            connection.close();

            return contrat;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    // Pour avoir un contratpar son ID
    public static Contrat getById(int id, Connection connection) throws Exception {
        Contrat contrat = new Contrat();
        String query = "SELECT * FROM contrat_employe WHERE id_contrat= %d";
        query = String.format(query, id);
        System.out.println("Query : " + query);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                contrat.setIdContrat(resultset.getInt("id_contrat"));
                contrat.setWorkLocation(WorkLocation.getById(resultset.getInt("id_work_location"), connection));
                contrat.setCandidature(Candidature.getById(connection, resultset.getInt("id_candidature")));
                contrat.setProvince(Province.getById(resultset.getInt("id_province"), connection));
                contrat.setSalary(resultset.getDouble("salary"));
                contrat.setPeriodeEssai(resultset.getInt("periode_essai"));
                contrat.setStartDate(resultset.getDate("start_date"));
                contrat.setEndDate(resultset.getDate("end_date"));
                contrat.setIsCnaps(resultset.getInt("is_cnaps"));
                contrat.setIsSanitaire(resultset.getInt("is_sanitaire"));
                contrat.setContratDate(resultset.getDate("contrat_date"));
                contrat.setStatus(resultset.getInt("status"));
                contrat.setPath(resultset.getString("path"));
                contrat.setIdEmploye(resultset.getInt("id_employe"));
                contrat.setSuperieur(Employe.getById(resultset.getInt("id_superieur"), connection));
                contrat.setPoste(WantedProfile.getById(connection, resultset.getInt("poste")));
            }

            resultset.close();
            statement.close();

            return contrat;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    // Pour avoir le contrat d'un candidat
    public static Contrat getByCandidat(int id) throws Exception {
        Contrat contrat = new Contrat();
        String query = "SELECT * FROM contrat WHERE id_candidature= %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                contrat.setIdContrat(resultset.getInt("id_contrat"));
                contrat.setWorkLocation(WorkLocation.getById(resultset.getInt("id_work_location")));
                contrat.setCandidature(Candidature.getById(connection, resultset.getInt("id_candidature")));
                contrat.setProvince(Province.getById(resultset.getInt("id_province")));
                contrat.setSalary(resultset.getDouble("salary"));
                contrat.setPeriodeEssai(resultset.getInt("periode_essai"));
                contrat.setStartDate(resultset.getDate("start_date"));
                contrat.setEndDate(resultset.getDate("end_date"));
                contrat.setIsCnaps(resultset.getInt("is_cnaps"));
                contrat.setIsSanitaire(resultset.getInt("is_sanitaire"));
                contrat.setStatus(resultset.getInt("status"));
                contrat.setPath(resultset.getString("path"));
                contrat.setContratDate(resultset.getDate("contrat_date"));
                contrat.setSuperieur(Employe.getById(resultset.getInt("id_superieur")));
                contrat.setPoste(WantedProfile.getById(connection, resultset.getInt("poste")));
            }

            resultset.close();
            statement.close();
            connection.close();

            return contrat;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    //Avoir tous les contrats d'essai
    public static List<Contrat> getAll() throws Exception {
        List<Contrat> contrats = new ArrayList<>();
        String query = "SELECT * FROM contrat WHERE status = 1";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                WantedProfile wp = WantedProfile.getById(connection, resultset.getInt("poste"));
                Contrat contrat = new Contrat(resultset.getInt("id_contrat"), WorkLocation.getById(resultset.getInt("id_work_location")), Candidature.getById(connection, resultset.getInt("id_candidature")), Province.getById(resultset.getInt("id_province")), resultset.getInt("periode_essai"), resultset.getDouble("salary"), resultset.getDate("start_date"), resultset.getDate("end_date"), resultset.getDate("contrat_date"), resultset.getInt("is_cnaps"), resultset.getInt("is_sanitaire"), resultset.getInt("status"), resultset.getString("path"), Employe.getById(resultset.getInt("id_superieur")), wp);
                contrats.add(contrat);
            }

            resultset.close();
            statement.close();
            connection.close();

            return contrats;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    //Modifie le contrat d'essai
    public void update(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "UPDATE contrat SET work_location=" + this.getWorkLocation().getIdWorkLocation() + " id_candidature=" + this.getCandidature().getIdCandidature() + ", id_province=" + this.getProvince().getIdProvince() + "salary=" + this.getSalary() + ", periode_essai=" + this.getPeriodeEssai() + ", start_date='" + this.getStartDate() + "', '" + this.getEndDate() + "' is_cnaps=" + this.getIsCnaps() + ", is_sanitaire=" + this.getIsSanitaire() + ", status=" + this.getStatus() + ", path='" + this.getPath() + "', is_superieur=" + this.getSuperieur().getIdEmploye() + ", poste=" + this.getPoste().getIdWantedProfile() + " WHERE id_contrat=" + this.getIdContrat();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    //Supprimer un contrat d'essai
    public void delete(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "DELETE from contrat WHERE id_contrat=" + this.getIdContrat();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    //Avoir le dernier id contrat inseres
    public static int getLastContratId(Connection connection) throws Exception {
        int maxID = 0;
        String query = "SELECT MAX(id_contrat) as last_id FROM contrat";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                maxID = resultset.getInt("last_id");
            }

            resultset.close();
            statement.close();

            return maxID;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    public static boolean isCandidatHasContrat(Connection conn, Candidature candidat) throws Exception {
        Contrat contrat = new Contrat();
        String query = "SELECT * FROM contrat WHERE id_candidature= %d";
        query = String.format(query, candidat.getIdCandidature());

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                resultset.close();
                statement.close();
                connection.close();

                return true;
            }

            resultset.close();
            statement.close();
            connection.close();

            return false;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }

    //Refuser le contrat
    public void refusedContrat(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "UPDATE contrat SET status = 0 WHERE id_contrat=" + this.getIdContrat();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    //valider le contrat
    public void validateContrat(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "UPDATE contrat SET status = 3 WHERE id_contrat=" + this.getIdContrat();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    public boolean isContratValidate() {
        if (this.getStatus() == 3) {
            return true;
        }

        return false;
    }
}
