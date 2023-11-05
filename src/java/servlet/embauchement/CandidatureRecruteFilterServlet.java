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
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author chalman
 */
@WebServlet(name = "CandidatureRecruteFilterServlet", urlPatterns = {"/candidatureRecruteFilter"})
public class CandidatureRecruteFilterServlet extends HttpServlet {

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
            out.println("<title>Servlet CandidatureRecruteFilterServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CandidatureRecruteFilterServlet at " + request.getContextPath() + "</h1>");
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
           response.setContentType("text/plain");
          PrintWriter out = response.getWriter();
        try {
            Connection conn = GConnection.getSimpleConnection();
              
            HttpSession session = request.getSession();
            if(request.getParameter("poste").equalsIgnoreCase("0") && request.getParameter("status") == null) {
                conn.close();
                RequestDispatcher dispat = request.getRequestDispatcher("listCandidatRecrute");
                dispat.forward(request, response);
            }
            else if(request.getParameter("poste").equalsIgnoreCase("0") && request.getParameter("status") != null) {
                Integer status = Integer.valueOf(request.getParameter("status"));
                String title = Candidature.getTitle(status);
                session.setAttribute("title", title);
                ArrayList<Candidature> candidatures = Candidature.getRecruteInPoste(conn, status, null);
                request.setAttribute("candidatureList", candidatures);
            }
            else {
                 Integer idPoste = Integer.valueOf(request.getParameter("poste"));
                Integer status = Integer.valueOf(request.getParameter("status"));
                String title = Candidature.getTitle(status);
                session.setAttribute("title", title);
                //Service service = Service.getById(conn, idService);s
                WantedProfile wp = WantedProfile.getById(conn, idPoste);
                ArrayList<Candidature> candidatures = Candidature.getRecruteInPoste(conn, status, wp);
                request.setAttribute("candidatureList", candidatures);
            }
           
              
            conn.close();
        } catch (Exception exe) {
            exe.printStackTrace();
            request.setAttribute("erreur", exe.getMessage());
        }
        RequestDispatcher dispat = request.getRequestDispatcher("listCandidatRecrute");
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
