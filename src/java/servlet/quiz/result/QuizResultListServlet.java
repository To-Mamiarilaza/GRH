/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.quiz.result;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.gestionProfile.WantedProfile;
import model.quiz.CandidatureTest;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "QuizResultListServlet", urlPatterns = {"/quiz-results"})
public class QuizResultListServlet extends HttpServlet {

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
        
        try {
            List<CandidatureTest> candidatureTestList = CandidatureTest.getAllNewCandidatureTest();
            request.setAttribute("candidatureTestList", candidatureTestList);
            
            Connection connection  = GConnection.getSimpleConnection();
            WantedProfile emptyProfile = new WantedProfile();
            List<WantedProfile> wantedProfileList = emptyProfile.getAll(connection);
            request.setAttribute("profiles", wantedProfileList);
            connection.close();

            List<String> css = new ArrayList<>();
            List<String> js = new ArrayList<>();
            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/quiz/quiz_result_list.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
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
        try {
            List<CandidatureTest> candidatureTestList = CandidatureTest.getAllNewCandidatureTest();
            request.setAttribute("candidatureTestList", candidatureTestList);
            
            Connection connection  = GConnection.getSimpleConnection();
            WantedProfile emptyProfile = new WantedProfile();
            List<WantedProfile> wantedProfileList = emptyProfile.getAll(connection);
            request.setAttribute("profiles", wantedProfileList);
            connection.close();

            List<String> css = new ArrayList<>();
            List<String> js = new ArrayList<>();
            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/quiz/quiz_result_list.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            int idWantedProfile = Integer.valueOf(request.getParameter("poste"));
            List<CandidatureTest> candidatureTestList = CandidatureTest.getFilterCandidatureTest(idWantedProfile);
            request.setAttribute("candidatureTestList", candidatureTestList);
            
            Connection connection  = GConnection.getSimpleConnection();
            WantedProfile emptyProfile = new WantedProfile();
            List<WantedProfile> wantedProfileList = emptyProfile.getAll(connection);
            request.setAttribute("profiles", wantedProfileList);
            connection.close();

            List<String> css = new ArrayList<>();
            List<String> js = new ArrayList<>();
            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/quiz/quiz_result_list.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
