/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.embauchement;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.embauchement.Contrat;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;
import model.gestionBesoin.WorkLoad;

/**
 *
 * @author chalman
 */
@WebServlet(name = "StartContratServlet", urlPatterns = {"/startContrat"})
public class StartContratServlet extends HttpServlet {

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
            out.println("<title>Servlet StartContratServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StartContratServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
          
          try {
                Connection conn = GConnection.getSimpleConnection();
              
              
                List<String> css = new ArrayList<>();
                css.add("./assets/css/quiz/quiz_creation.css");

                List<String> js = new ArrayList<>();
                js.add("./assets/js/quiz/quiz-creation.js");

                request.setAttribute("title", "Commencer contrat");
                request.setAttribute("contentPage", "./pages/embauchement/start_contrat_recrute.jsp");
                request.setAttribute("css", css);
                request.setAttribute("js", js);
              
               Integer idCandidat = Integer.valueOf(request.getParameter("id"));
               Candidature candidatRecrute = Candidature.getById(conn, idCandidat);
               System.out.println("idCandidat : "+candidatRecrute);
               HttpSession session = request.getSession();
               session.setAttribute("candidatRecrute", candidatRecrute);

               request.setAttribute("candidatRecrute", candidatRecrute);
               
               boolean hasContrat = Contrat.isCandidatHasContrat(conn, candidatRecrute);
               if(hasContrat==true) {
                   Contrat contratFind = Contrat.getByCandidat(candidatRecrute.getIdCandidature());
                   request.setAttribute("isContratValidate", contratFind.isContratValidate());
               }
               request.setAttribute("hasContrat", hasContrat);
               
               conn.close();
          } catch (Exception exe) {
               request.setAttribute("erreur", exe.getMessage());
               exe.printStackTrace();
          }
          RequestDispatcher dispat = request.getRequestDispatcher("./template.jsp");
          dispat.forward(request, response);
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
