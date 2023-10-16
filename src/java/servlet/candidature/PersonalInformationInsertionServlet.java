/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.candidature.Candidature;
import model.candidature.PersonnalInformation;
import model.gestionProfile.Adresse;
import model.gestionProfile.Sexe;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "PersonalInformationInsertionServlet", urlPatterns = {"/PersonalInformationInsertionServlet"})
public class PersonalInformationInsertionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PersonalInformationInsertionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PersonalInformationInsertionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Candidature can = new Candidature();
            HttpSession session = request.getSession();
            session.setAttribute("candidature", can);

            WantedProfile wp = new WantedProfile();
            Adresse a = new Adresse();
            List<WantedProfile> listePoste = wp.getAll(null);
            List<Adresse> listeAdresse = a.getAllAdresse(null);

            request.setAttribute("listePoste", listePoste);
            request.setAttribute("listeAdresse", listeAdresse);

            RequestDispatcher req = request.getRequestDispatcher("/pages/candidature/personal_information_insertion.jsp");
            req.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PersonalInformationInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Candidature can = (Candidature) session.getAttribute("candidature");
            
            int idWantedProfile = Integer.valueOf(request.getParameter("poste"));
            System.out.println(idWantedProfile);
            session.setAttribute("wp", idWantedProfile);
            
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String contact = request.getParameter("contact");
            String dateString = request.getParameter("dateNaissance");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            java.util.Date date = dateFormat.parse(dateString);
            Date dateNaissance = new Date(date.getTime());
            
            String adresse = request.getParameter("adresse");
            Adresse ad = new Adresse(adresse, 1);
            
            String sexe = request.getParameter("sexe");
            Sexe se = new Sexe(sexe, 1);
            
            PersonnalInformation per = new PersonnalInformation(nom, prenom, dateNaissance, ad, email, contact, se);
            
            can.setPersonnalInformation(per);

            response.sendRedirect("/RessourceHumaine/ProfessionalCareerInsertionServlet");
        } catch (ParseException ex) {
            Logger.getLogger(PersonalInformationInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
