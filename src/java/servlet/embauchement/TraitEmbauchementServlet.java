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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.embauchement.Contrat;
import model.embauchement.Province;
import model.embauchement.WorkLocation;
import model.employe.Employe;
import util.pdf.DateManager;

/**
 *
 * @author chalman
 */
@WebServlet(name = "TraitEmbauchementServlet", urlPatterns = {"/traitEmbauchement"})
public class TraitEmbauchementServlet extends HttpServlet {

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
            out.println("<title>Servlet TraitEmbauchementServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraitEmbauchementServlet at " + request.getContextPath() + "</h1>");
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
                
                HttpSession session = request.getSession();
                Candidature candidatRecrute = (Candidature)session.getAttribute("candidatRecrute");
              
                if(request.getParameter("idHelp").equalsIgnoreCase("1")) {
                    candidatRecrute.changeStatus(conn, 7);  //refuser recrutement
                     conn.close();
                    RequestDispatcher dispat = request.getRequestDispatcher("listCandidatRecrute");
                    dispat.forward(request, response);
                }
                else if(request.getParameter("idHelp").equalsIgnoreCase("2")) {
                    Contrat contrat = Contrat.getByCandidat(candidatRecrute.getIdCandidature());
                    candidatRecrute.changeStatus(conn, 10); //Refuser contrat
                    contrat.refusedContrat(conn);
                    conn.close();
                    RequestDispatcher dispat = request.getRequestDispatcher("listCandidatRecrute");
                    dispat.forward(request, response);
                }
                else {
                    Contrat contrat = Contrat.getByCandidat(candidatRecrute.getIdCandidature());
                    session.setAttribute("contratValider", contrat);
                    conn.close();
                    RequestDispatcher dispat = request.getRequestDispatcher("validerContrat?id_help=1");
                    dispat.forward(request, response);
                }
               
                conn.close();
          } catch (Exception exe) {
               request.setAttribute("erreur", exe.getMessage());
               exe.printStackTrace();
          }
          RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/contrat_essai_insertion.jsp");
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
