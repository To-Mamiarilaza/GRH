/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.quiz.realisation;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;
import model.quiz.CandidatureTest;
import model.quiz.Question;
import model.quiz.Quiz;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "QuizValidationServlet", urlPatterns = {"/quiz-validation"})
public class QuizValidationServlet extends HttpServlet {

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
        // reception des données requiz
        try {
            String idCandidature = request.getParameter("idCandidature");
            String idWantedProfile = request.getParameter("idWantedProfile");
            String idQuiz = request.getParameter("idQuiz");
            
            Quiz quiz = Quiz.getQuizById(Integer.valueOf(idQuiz));
            Candidature candidature = new Candidature();
            candidature.setIdCandidature(0);
            
            WantedProfile wantedProfile = new WantedProfile();
            
            // Prise en main des réponses de l'utilisateur
            HashMap<String, String[]> answers = new HashMap<>();
            for (Question question : quiz.getQuestions()) {
                answers.put(String.valueOf(question.getIdQuestion()), request.getParameterValues(String.valueOf(question.getIdQuestion())));
            }
            
            CandidatureTest.saveCandidatureTest(candidature, wantedProfile, quiz, answers);
            
            response.sendRedirect("./quiz-finish");
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
