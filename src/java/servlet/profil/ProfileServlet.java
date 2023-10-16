/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.profil;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gestionProfile.Adresse;
import model.gestionProfile.Diplome;
import model.gestionProfile.DonneeProfile;
import model.gestionProfile.Experience;
import model.gestionProfile.Salaire;
import model.gestionProfile.Sexe;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
public class ProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Diplome d = new Diplome();
        Adresse a = new Adresse();
        Experience e = new Experience();
        Salaire s = new Salaire();
        Sexe se = new Sexe();
        try {
            List<Diplome> listeDiplome = d.getAllDiplome(null);
            request.setAttribute("listeDiplome", listeDiplome);
            List<Adresse> listeAdresse = a.getAllAdresse(null);
            request.setAttribute("listeAdresse", listeAdresse);
            List<Experience> listeExperience = e.getAllExperience(null);
            request.setAttribute("listeExperience", listeExperience);
            List<Salaire> listeSalaire = s.getAllSalaire(null);
            request.setAttribute("listeSalaire", listeSalaire);
            List<Sexe> listeSexe = se.getAllSexe(null);
            request.setAttribute("listeSexe", listeSexe);

            DonneeProfile dp = new DonneeProfile(listeDiplome, listeAdresse, listeExperience, listeSexe, listeSalaire);

            Gson gson = new Gson();
            String jsonLists = gson.toJson(dp);
            
            response.getWriter().print(jsonLists);

// Ajoutez cet objet JSON comme attribut à la requête
            request.setAttribute("jsonLists", jsonLists);
        } catch (Exception ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
