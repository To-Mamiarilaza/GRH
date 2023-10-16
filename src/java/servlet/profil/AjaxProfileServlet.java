package servlet.profil;

import com.google.gson.Gson;
import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gestionProfile.Adresse;
import model.gestionProfile.AdresseNote;
import model.gestionProfile.Diplome;
import model.gestionProfile.DiplomeNote;
import model.gestionProfile.Experience;
import model.gestionProfile.ExperienceNote;
import model.gestionProfile.Salaire;
import model.gestionProfile.SalaireNote;
import model.gestionProfile.Sexe;
import model.gestionProfile.SexeNote;
import model.gestionProfile.WantedProfile;
import model.requis.Service;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "AjaxProfileServlet", urlPatterns = {"/AjaxProfileServlet"})
public class AjaxProfileServlet extends HttpServlet {

    List<SexeNote> listeSexeNote = new ArrayList<>();
    List<DiplomeNote> listeDiplomeNote = new ArrayList<>();
    List<AdresseNote> listeAdresseNote = new ArrayList<>();
    List<ExperienceNote> listeExperienceNote = new ArrayList<>();
    List<SalaireNote> listeSalaireNote = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection conn = GConnection.getSimpleConnection();
            String poste = request.getParameter("poste");
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.setPoste(poste);
            wp.setService(new Service(1, 1));
            wp.createWantedProfile(null);

            //l'id du dernier wantedProfile
            int lastId = wp.getLastId(null);

            //pour le diplome
            DiplomeNote dn = new DiplomeNote();
            for (int i = 0; i < listeDiplomeNote.size(); i++) {
                dn.setNote(listeDiplomeNote.get(i).getNote());
                int idDiplome = new Diplome().getIdByName(listeDiplomeNote.get(i).getDiplome().getDiplome(), null);
                dn.createDiplomeNote(lastId, idDiplome, null);
            }
            //pour l'Adresse
            AdresseNote an = new AdresseNote();
            for (int a = 0; a < listeAdresseNote.size(); a++) {
                an.setNote(listeAdresseNote.get(a).getNote());
                int idAdresse = new Adresse().getIdByName(listeAdresseNote.get(a).getAdresse().getAdresse(), null);
                an.createAdresseNote(lastId, idAdresse, null);
            }
            // pour l' experience
            ExperienceNote en = new ExperienceNote();
            for (int e = 0; e < listeExperienceNote.size(); e++) {
                en.setNote(listeExperienceNote.get(e).getNote());
                int idExperience = new Experience().getIdByName(listeExperienceNote.get(e).getExperience().getExperience(), null);
                en.createExperienceNote(lastId, idExperience, null);
            }
            // pour le sexe
            SexeNote sen = new SexeNote();
            for (int u = 0; u < listeSexeNote.size(); u++) {
                sen.setNote(listeSexeNote.get(u).getNote());
                int idSexe = new Sexe().getIdByName(listeSexeNote.get(u).getSexe().getSexe(), null);
                sen.createSexeNote(lastId, idSexe, null);
            }
            // pour le salaire
            SalaireNote san = new SalaireNote();
            for (int s = 0; s < listeSalaireNote.size(); s++) {
                san.setNote(listeSalaireNote.get(s).getNote());
                int idSalaire = new Salaire().getIdByName(listeSalaireNote.get(s).getSalaire().getSalaire(), null);
                san.createSalaireNote(lastId, idSalaire, null);
            }

        } catch (Exception ex) {
            Logger.getLogger(AjaxProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        listeAdresseNote.clear();
        listeDiplomeNote.clear();
        listeExperienceNote.clear();
        listeSalaireNote.clear();
        listeSexeNote.clear();

        response.sendRedirect("./besoin-insertion");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WantedProfile wp = null;
        HttpSession session = request.getSession();
        if (request.getParameter("diplome") != null && request.getParameter("diplomenote") != null) {

            String diplome = request.getParameter("diplome");
            double note = Double.valueOf(request.getParameter("diplomenote"));

            Diplome d = new Diplome(diplome, 1);
            DiplomeNote dn = new DiplomeNote(d, note);

            listeDiplomeNote.add(dn);

            if (session.getAttribute("wantedprofile") == null) {
                wp = new WantedProfile();
                session.setAttribute("wantedprofile", wp);
                wp.setDiplomeNote(listeDiplomeNote);
                String dip = diplome + ":" + String.valueOf(note);

                List<String> diplomeString = new ArrayList<>();
                diplomeString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(diplomeString);
                response.getWriter().write(jsonData);
            } else {
                wp = (WantedProfile) session.getAttribute("wantedprofile");
                wp.setDiplomeNote(listeDiplomeNote);
                String dip = diplome + ":" + String.valueOf(note);

                List<String> diplomeString = new ArrayList<>();
                diplomeString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(diplomeString);
                response.getWriter().write(jsonData);
            }
        } else if (request.getParameter("adresse") != null && request.getParameter("adressenote") != null) {

            String adresse = request.getParameter("adresse");
            double note = Double.valueOf(request.getParameter("adressenote"));

            Adresse a = new Adresse(adresse, 1);
            AdresseNote an = new AdresseNote(a, note);

            listeAdresseNote.add(an);
            List<String> adresseString = new ArrayList<>();
            if (session.getAttribute("wantedprofile") == null) {
                wp = new WantedProfile();
                session.setAttribute("wantedprofile", wp);
                wp.setAdresseNote(listeAdresseNote);
                String dip = adresse + ":" + String.valueOf(note);

                adresseString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(adresseString);
                response.getWriter().write(jsonData);
            } else {
                wp = (WantedProfile) session.getAttribute("wantedprofile");
                wp.setAdresseNote(listeAdresseNote);
                String dip = adresse + ":" + String.valueOf(note);
                
                adresseString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(adresseString);
                response.getWriter().write(jsonData);
            }
        } else if (request.getParameter("experience") != null && request.getParameter("experiencenote") != null) {
            String experience = request.getParameter("experience");
            double note = Double.valueOf(request.getParameter("experiencenote"));

            Experience e = new Experience(experience, 1);
            ExperienceNote en = new ExperienceNote(e, note);

            List<String> experienceString = new ArrayList<>();
            listeExperienceNote.add(en);
            if (session.getAttribute("wantedprofile") == null) {
                wp = new WantedProfile();
                session.setAttribute("wantedprofile", wp);
                wp.setExperienceNote(listeExperienceNote);
                String dip = experience + ":" + String.valueOf(note);

                experienceString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(experienceString);
                response.getWriter().write(jsonData);
            } else {
                wp = (WantedProfile) session.getAttribute("wantedprofile");
                wp.setExperienceNote(listeExperienceNote);
                String dip = experience + ":" + String.valueOf(note);

                experienceString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(experienceString);
                response.getWriter().write(jsonData);
            }
        } else if (request.getParameter("salaire") != null && request.getParameter("salairenote") != null) {

            String salaire = request.getParameter("salaire");
            double note = Double.valueOf(request.getParameter("salairenote"));

            Salaire s = new Salaire(Double.valueOf(salaire), 1);
            SalaireNote sn = new SalaireNote(s, note);

            listeSalaireNote.add(sn);
            List<String> salaireString = new ArrayList<>();
            if (session.getAttribute("wantedprofile") == null) {
                wp = new WantedProfile();
                session.setAttribute("wantedprofile", wp);
                wp.setSalaireNote(listeSalaireNote);
                String dip = salaire + ":" + String.valueOf(note);

                salaireString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(salaireString);
                response.getWriter().write(jsonData);
            } else {
                wp = (WantedProfile) session.getAttribute("wantedprofile");
                wp.setSalaireNote(listeSalaireNote);
                String dip = salaire + ":" + String.valueOf(note);

                salaireString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(salaireString);
                response.getWriter().write(jsonData);
            }
        } else if (request.getParameter("sexe") != null && request.getParameter("sexenote") != null) {

            String sexe = request.getParameter("sexe");
            double note = Double.valueOf(request.getParameter("sexenote"));

            Sexe s = new Sexe(sexe, 1);
            SexeNote sn = new SexeNote(s, note);

            List<String> sexeString = new ArrayList<>();
            listeSexeNote.add(sn);
            if (session.getAttribute("wantedprofile") == null) {
                wp = new WantedProfile();
                session.setAttribute("wantedprofile", wp);
                wp.setSexeNote(listeSexeNote);
                String dip = sexe + ":" + String.valueOf(note);

                sexeString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(sexeString);
                response.getWriter().write(jsonData);
            } else {
                wp = (WantedProfile) session.getAttribute("wantedprofile");
                wp.setSexeNote(listeSexeNote);
                String dip = sexe + ":" + String.valueOf(note);

                sexeString.add(dip);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(sexeString);
                response.getWriter().write(jsonData);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
