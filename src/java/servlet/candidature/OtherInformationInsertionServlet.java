/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.candidature.Candidature;
import model.gestionProfile.AdresseNote;
import model.gestionProfile.DiplomeNote;
import model.gestionProfile.ExperienceNote;
import model.gestionProfile.SalaireNote;
import model.gestionProfile.SexeNote;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "OtherInformationInsertionServlet", urlPatterns = {"/OtherInformationInsertionServlet"})
public class OtherInformationInsertionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OtherInformationInsertionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OtherInformationInsertionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/candidature/other_information_insertion.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double salaire = Double.valueOf(request.getParameter("salaire"));
            String ambitions = request.getParameter("ambitions");
            String interet = request.getParameter("interet");
            
            HttpSession session = request.getSession();
            Candidature can = (Candidature) session.getAttribute("candidature");
            can.setSalaryExpectation(salaire);
            can.setInterestCareer(interet);
            can.setSelfProfile(ambitions);
            
            
            int idWantedProfile = (Integer) session.getAttribute("wp");
            System.out.println(idWantedProfile);

            double diplomeNote = new DiplomeNote().getDiplomeNote(null, idWantedProfile, can.getFormationPath().getDiplome().getDiplome());
            double adresseNote = new AdresseNote().getAdresseNote(null, idWantedProfile, can.getPersonnalInformation().getAdresse().getAdresse());
            double sexeNote = new SexeNote().getSexeNote(null, idWantedProfile, can.getPersonnalInformation().getSexe().getSexeString(Integer.valueOf(can.getPersonnalInformation().getSexe().getSexe())));
            double salaireNote = new SalaireNote().getSalaireNote(null, idWantedProfile, can.getSalaryExpectation());
            double experienceNote = new ExperienceNote().getExperienceNote(null, idWantedProfile, can.getProfessionalCareer().getExperience().getExperience());
            
            double totalNote = diplomeNote + adresseNote + sexeNote + salaireNote + experienceNote;
            
            can.setNote(totalNote);
            response.sendRedirect("/RessourceHumaine/PreviewCandidatureServlet");
        } catch (Exception ex) {
            Logger.getLogger(OtherInformationInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
