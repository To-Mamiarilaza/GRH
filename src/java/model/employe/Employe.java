/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.employe;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.conge.CongeReport;
import model.embauchement.Contrat;
import model.requis.Service;

/**
 *
 * @author chalman
 */
public class Employe {

    private Integer idEmploye;
    private Contrat contrat;
    private String numMatricule;
    private Date dateEmbauche;
    private Integer status;
    private ClasseEmploye classeEmploye;

///Getters et setters
    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public String getNumMatricule() {
        return numMatricule;
    }

    public void setNumMatricule(String numMatricule) {
        this.numMatricule = numMatricule;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ClasseEmploye getClasseEmploye() {
        return classeEmploye;
    }

    public void setClasseEmploye(ClasseEmploye calasseEmploye) {
        this.classeEmploye = calasseEmploye;
    }

    // Fonction  pour avoir le nom facilement
    public String getAnciennete() {
        Period period = Period.between(getContrat().getContratDate().toLocalDate(), LocalDate.now());
        
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        
        String result = "";
        
        if (years != 0) {
            result += years + " ans ";
        }
        
        if (months != 0) {
            result += months + " mois ";
        }
        
        if (days != 0) {
            result += days + " jour ";
        }
        
        return result;
    }
    
    public String getPhoto() {
        return getContrat().getCandidature().getPhoto();
    }
    
    public String getNom() {
        return getContrat().getCandidature().getPersonnalInformation().getName();
    }

    public String getPrenom() {
        return getContrat().getCandidature().getPersonnalInformation().getFirstName();
    }
    
    public String getPoste() {
        return getContrat().getPoste().getPoste();
    }
    
    public String getTelephone()  {
        return getContrat().getCandidature().getPersonnalInformation().getTelephone();
    }
    
    public String getDateNaissance() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(getContrat().getCandidature().getPersonnalInformation().getBirthDate());
    }
    
    public String getFullName() {
        return getNom() + " " + getPrenom();
    }
    
    public String getAdresse() {
        return getContrat().getCandidature().getPersonnalInformation().getAdresse().getAdresse();
    }
    
    public Employe getSuperieur() {
        return getContrat().getSuperieur();
    }
    
    public double getSalaire() {
        return getContrat().getSalary();
    }
    
    public Service getService() {
        return getContrat().getPoste().getService();
    }
    
    public String getNumCNAPS() {
        return "000.000.000.000";
    }

///Constructors
    public Employe() {
    }

    public Employe(Contrat contrat, Date dateEmbauche, Integer status, ClasseEmploye classeEmploye) {
        this.contrat = contrat;
        this.numMatricule = numMatricule;
        this.dateEmbauche = dateEmbauche;
        this.status = status;
        this.classeEmploye = classeEmploye;
    }

    public Employe(Integer idEmploye, Contrat contrat, String numMatricule, Date dateEmbauche, Integer status, ClasseEmploye classeEmploye) {
        this.idEmploye = idEmploye;
        this.contrat = contrat;
        this.numMatricule = numMatricule;
        this.dateEmbauche = dateEmbauche;
        this.status = status;
        this.classeEmploye = classeEmploye;
    }

///Fonctions de classe
    // Fonction pour avoir seulement le nombre de subordonnées d'un employe
    public int getSubordonnesNb(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        String query = "SELECT count(id_employe) as nombre FROM employe e JOIN contrat c ON e.id_contrat = c.id_contrat WHERE id_superieur = %d";
        query = String.format(query, getIdEmploye());

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            int nombre = 0;

            if (resultset.next()) {
                nombre = resultset.getInt("nombre");

            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return nombre;
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

    // Fonction pour avoir tous les subordonnées d'un employe dans un contrat
    public static int countEmploye(Connection connection) throws Exception {
        int nombre = 0;
        String query = "SELECT count(*) as nombre FROM employe";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                nombre = resultset.getInt("nombre");
            }

            resultset.close();
            statement.close();

            return nombre;
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
    
    public List<Employe> getSubordonnes(Connection connection) throws Exception {
        // Etat de fermeture
        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
        }

        // Pour avoir l'id du quiz inséré
        List<Employe> employeList = new ArrayList<>();
        String query = "SELECT id_employe FROM employe e JOIN contrat c ON e.id_contrat = c.id_contrat WHERE id_superieur = %d";
        query = String.format(query, getIdEmploye());

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idEmploye = resultset.getInt("id_employe");

                employeList.add(Employe.getById(idEmploye, connection));
            }

            resultset.close();
            statement.close();
            if (closeable) {
                connection.close();
            }

            return employeList;
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

    // Avoir l'id de l'employe inséré
    // Pour avoir l'id du quiz inséré
    public int getLastQuizId(Connection connection) throws Exception {
        int maxID = 0;
        String query = "SELECT MAX(id_employe) as last_id FROM employe";

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

    // Enregistre l'employe dans la base de données
    public void save(Connection connection) throws Exception {
        String query = "INSERT INTO employe ( id_contrat, num_matricule, date_embauche, status, id_classe_employe) VALUES (?, ?, ?, ?, ?)";

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
            statement.setInt(1, getContrat().getIdContrat());
            statement.setString(2, getNumMatricule());
            statement.setDate(3, getDateEmbauche());
            statement.setInt(4, getStatus());
            statement.setInt(5, getClasseEmploye().getIdClasseEmploye());
            statement.executeUpdate();

            // Insertion d'un conge reporting
            CongeReport.addCongeReport(getLastEmpId(connection), LocalDate.now().getYear(), 0, connection);

            statement.close();

            if (closeable) {
                connection.commit();
                connection.close();
            }

        } catch (Exception e) {
            /*if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
                connection.close();
            }*/
            e.printStackTrace();
            throw e;
        }
    }

    // Pour avoir un employe par son ID
    public static Employe getById(int id) throws Exception {
        Employe employe = new Employe();
        String query = "SELECT * FROM employe WHERE id_employe= %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                employe.setIdEmploye(resultset.getInt("id_employe"));
                Contrat contrat = Contrat.getById(resultset.getInt("id_contrat"));
                employe.setContrat(Contrat.getByCandidat(contrat.getCandidature().getIdCandidature()));
                employe.setNumMatricule(resultset.getString("num_matricule"));
                employe.setDateEmbauche(resultset.getDate("date_embauche"));
                employe.setStatus(resultset.getInt("status"));
                employe.setClasseEmploye(ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
            }

            resultset.close();
            statement.close();
            connection.close();

            return employe;
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

    // Pour avoir un employe par son ID
    public static Employe getById(int id, Connection connection) throws Exception {
        Employe employe = new Employe();
        String query = "SELECT * FROM employe WHERE id_employe= %d";
        query = String.format(query, id);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                employe.setIdEmploye(resultset.getInt("id_employe"));
                Contrat contrat = Contrat.getById(resultset.getInt("id_contrat"));
                employe.setContrat(Contrat.getByCandidat(contrat.getCandidature().getIdCandidature()));
                employe.setNumMatricule(resultset.getString("num_matricule"));
                employe.setDateEmbauche(resultset.getDate("date_embauche"));
                employe.setStatus(resultset.getInt("status"));
                employe.setClasseEmploye(ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
            }

            resultset.close();
            statement.close();

            return employe;
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
    public static List<Employe> getAll() throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe WHERE status != 0 ORDER BY id_employe DESC";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Employe employe = new Employe(resultset.getInt("id_employe"), Contrat.getById(resultset.getInt("id_contrat"), connection), resultset.getString("num_matricule"), resultset.getDate("date_embauche"), resultset.getInt("status"), ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
                employes.add(employe);
            }

            resultset.close();
            statement.close();
            connection.close();

            return employes;
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
    
    public static List<Employe> getAllId(Connection connection) throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe WHERE status != 0";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Employe employe = new Employe();
                employe.setIdEmploye(resultset.getInt("id_employe"));
                employes.add(employe);
            }

            resultset.close();
            statement.close();

            return employes;
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

    public static List<Employe> getAll(Connection connection) throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe WHERE status != 0 ORDER BY id_employe DESC";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Employe employe = new Employe(resultset.getInt("id_employe"), Contrat.getById(resultset.getInt("id_contrat"), connection), resultset.getString("num_matricule"), resultset.getDate("date_embauche"), resultset.getInt("status"), ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
                employes.add(employe);
            }

            resultset.close();
            statement.close();

            return employes;
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

    //Rechercher un employer
    public static List<Employe> searchEmployeByText(String text) throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe WHERE status != 0 AND num_matricule LIKE '%s'";
        query = String.format(query, "%" + text + "%");
        System.out.println("Req : " + query);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Employe employe = new Employe(resultset.getInt("id_employe"), Contrat.getById(resultset.getInt("id_contrat"), connection), resultset.getString("num_matricule"), resultset.getDate("date_embauche"), resultset.getInt("status"), ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
                employes.add(employe);
            }

            resultset.close();
            statement.close();
            connection.close();

            return employes;
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

    public static List<Employe> searchEmployeByText(String text, Connection connection) throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe WHERE status != 0 AND num_matricule LIKE '%s'";
        query = String.format(query, "%" + text + "%");
        System.out.println("Req : " + query);

        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            while (resultset.next()) {
                Employe employe = new Employe(resultset.getInt("id_employe"), Contrat.getById(resultset.getInt("id_contrat"), connection), resultset.getString("num_matricule"), resultset.getDate("date_embauche"), resultset.getInt("status"), ClasseEmploye.getClasseEmployeById(resultset.getInt("id_classe_employe"), connection));
                employes.add(employe);
            }

            resultset.close();
            statement.close();

            return employes;
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

    //Filtrer un employe
    public static List<Employe> getFilter(Integer idService, Integer idWantedProfile) throws Exception {
        List<Employe> employesFinded = new ArrayList<>();

        try {
            List<Employe> allEmploye = Employe.getAll();

            for (int i = 0; i < allEmploye.size(); i++) {
                if (allEmploye.get(i).getContrat().getCandidature().getWantedProfile().getService().getIdService() == idService || allEmploye.get(i).getContrat().getCandidature().getWantedProfile().getIdWantedProfile() == idWantedProfile) {
                    employesFinded.add(allEmploye.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employesFinded;
    }

    //Modifie le contrat d'essai
    public void update(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "UPDATE employe SET id_contrat=" + this.getContrat().getIdContrat() + ", num_matricule='" + this.getNumMatricule() + "', date_embauche='" + this.getDateEmbauche() + "', status=" + this.getStatus() + ", id_classe_employe=" + this.getClasseEmploye().getIdClasseEmploye() + " WHERE id_employe=" + this.getIdEmploye();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    //Supprimer un contrat d'essai
    public void delete(Connection conn) throws Exception {
        Statement work = conn.createStatement();
        String req = "DELETE from employe WHERE id_employe=" + this.getIdEmploye();
        work.execute(req);
        conn.setAutoCommit(true);
    }

    //Avoir le dernier id employes inseres
    public static int getLastEmpId(Connection connection) throws Exception {
        int maxID = 0;
        String query = "SELECT MAX(id_employe) as last_id FROM employe";

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

    //generer un numero matricule pour un employe
    public String generateNumMatricule(Connection conn) throws Exception {
        Integer lastIdEmp = Employe.getLastEmpId(conn);

        //Creer un objet calendar a partir de la date_embauche
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateEmbauche);

        //Obtenir l'annee actuelle
        int annee = calendar.get(calendar.YEAR);

        String anneeString = String.valueOf(annee);
        char[] seq = anneeString.toCharArray();
        int idNewEmp = 0;
        if (lastIdEmp == null) {
            idNewEmp = 1;
        } else {
            idNewEmp = lastIdEmp + 1;
        }
        String numMatricule = "EMP00" + (idNewEmp) + "" + seq[2] + "" + seq[3];

        return numMatricule;
    }

    //Recuperer toutes mees subordonnees grace a mon superieur
    public static List<Employe> getMySubByMySup(Employe superieur) {
        return null;
    }

    //Recuperer toutes mes subordonnees
    public static List<Employe> getMySub(Employe employe) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        List<Employe> employe = Employe.getAll(connection);
        for (Employe employe1 : employe) {
            System.out.println("ID : " + employe1.getIdEmploye());
        }
    }

}
