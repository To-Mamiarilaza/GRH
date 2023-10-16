/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.candidature;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;
import model.requis.Service;

/**
 *
 * @author chalman
 */
@WebServlet(name = "CandidatureFilterServlet", urlPatterns = {"/candidatureFilter"})
public class CandidatureFilterServlet extends HttpServlet {

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
            out.println("<title>Servlet CandidatureFilterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CandidatureFilterServlet at " + request.getContextPath() + "</h1>");
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
              
             
              
              conn.close();
          } catch (Exception exe) {
                exe.printStackTrace();
                request.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = request.getRequestDispatcher("./pages/candidature/candidature_list.jsp");
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
        try {
            Connection conn = GConnection.getSimpleConnection();
              
            Integer idService = Integer.valueOf(request.getParameter("service"));
            Integer idPoste = Integer.valueOf(request.getParameter("poste"));
            Service service = Service.getById(conn, idService);
            WantedProfile wp = WantedProfile.getById(conn, idPoste);
            ArrayList<Candidature> candidatures = Candidature.getAllInServicePoste(conn, service, wp);
             
            request.setAttribute("candidatureList", candidatures);
              
            conn.close();
        } catch (Exception exe) {
            exe.printStackTrace();
            request.setAttribute("erreur", exe.getMessage());
        }
        RequestDispatcher dispat = request.getRequestDispatcher("listCandidature");
        dispat.forward(request, response);
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
