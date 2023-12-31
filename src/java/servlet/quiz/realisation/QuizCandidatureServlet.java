/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.quiz.realisation;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import model.candidature.Candidature;
import model.candidature.PersonnalInformation;
import model.gestionProfile.WantedProfile;
import model.quiz.Quiz;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "QuizCandidatureServlet", urlPatterns = {"/quiz-realisation"})
public class QuizCandidatureServlet extends HttpServlet {

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
        // Id du candidature ( On doit prendre depuis le candidature toutes les informations nécessaire )
        // Tous les informations ici doivent partir du classe candidature
        try {
            int idCandidature = Integer.valueOf(request.getParameter("idCandidature"));
            
            Connection connection = GConnection.getSimpleConnection();
            Candidature candidature = Candidature.getById(connection, idCandidature);
            connection.close();
            
            PersonnalInformation personalInfo = candidature.getPersonnalInformation();
            WantedProfile wantedProfile = candidature.getWantedProfile();
            Quiz quiz = wantedProfile.getQuiz();
            
            request.getSession().setAttribute("candidature", candidature);
        
            request.setAttribute("idCandidature", candidature.getIdCandidature());
            request.setAttribute("quiz", quiz);
            request.setAttribute("infoPerso", personalInfo);
            request.setAttribute("wantedProfile", wantedProfile);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./pages/quiz/quiz_candidature.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
