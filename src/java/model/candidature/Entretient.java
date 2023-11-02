package model.candidature;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fy Botas
 */
public class Entretient {

    int idEntretient;
    Candidature candidature;
    Date dateEntretient;
    double noteEntretient;
    int etat;

    public Entretient(int idEntretient, Candidature candidature, Date dateEntretient, double noteEntretient, int etat) {
        this.idEntretient = idEntretient;
        this.candidature = candidature;
        this.dateEntretient = dateEntretient;
        this.noteEntretient = noteEntretient;
        this.etat = etat;
    }

    public Entretient() {
    }

    //inserer un entretient
    public void create(Connection con) throws Exception {
        boolean b = true;
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "insert into entretient values (DEFAULT, " + this.getCandidature().getIdCandidature() + ", '" + this.getDateEntretient() + "', " + this.getNoteEntretient() + ", " + this.getEtat() + ")";
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

    //avoir les candidats qui ont fini l'entetient
    public List<Entretient> getFinishedEntretient(int status, Connection con) throws SQLException { //status 0 refuser, 1 en attente, 5 accepter
        boolean b = true;
        List<Entretient> listeEntretient = new ArrayList<>();
        try {
            if (con == null) {
                con = GConnection.getSimpleConnection();
                b = false;
            }
            String requete = "select * from v_candidature_entretient where entretient_status = " + status;
            System.out.println(requete);
            Statement s = con.createStatement();
            s.executeQuery(requete);
            ResultSet rs = s.executeQuery(requete);
            while (rs.next()) {
                PersonnalInformation pi = new PersonnalInformation();
                pi.setName(rs.getString("name"));
                pi.setFirstName(rs.getString("first_name"));
                Candidature can = new Candidature();
                can.setPersonnalInformation(pi);
                can.setIdCandidature(rs.getInt("id_candidature"));
                listeEntretient.add(new Entretient(rs.getInt("id_entretient"), can, rs.getDate("date_entretient"), rs.getDouble("note_entretient"), rs.getInt("entretient_status")));
            }
        } catch (Exception exe) {
            exe.printStackTrace();
        } finally {
            if (con != null && !b) {
                con.close();
            }
        }
        return listeEntretient;
    }

    public int getIdEntretient() {
        return idEntretient;
    }

    public void setIdEntretient(int idEntretient) {
        this.idEntretient = idEntretient;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public Date getDateEntretient() {
        return dateEntretient;
    }

    public void setDateEntretient(Date dateEntretient) {
        this.dateEntretient = dateEntretient;
    }

    public double getNoteEntretient() {
        return noteEntretient;
    }

    public void setNoteEntretient(double noteEntretient) {
        this.noteEntretient = noteEntretient;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

}
