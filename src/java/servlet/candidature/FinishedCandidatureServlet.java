/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

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
import model.candidature.Career;
import model.candidature.Formation;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "FinishedCandidatureServlet", urlPatterns = {"/FinishedCandidatureServlet"})
public class FinishedCandidatureServlet extends HttpServlet {

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
            out.println("<title>Servlet FinishedCandidatureServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinishedCandidatureServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Candidature can = (Candidature) session.getAttribute("candidature");
            int wp = (Integer) session.getAttribute("wp");
            can.create(null, wp, can.getPersonnalInformation().getAdresse().getAdresse(), Integer.valueOf(can.getPersonnalInformation().getSexe().getSexe()), can.getProfessionalCareer().getExperience().getExperience(), can.getFormationPath().getDiplome().getDiplome());
            int id = new Candidature().getLastId(null);
            System.out.println(id);
            Career c = can.getProfessionalCareer().getCareers().get(0);
            c.create(null, id);
            Formation f = can.getFormationPath().getFormations().get(0);
            f.create(null, id);
            
            
            response.sendRedirect("pages/candidature/finished_candidature.jsp");
        } catch (Exception ex) {
            Logger.getLogger(FinishedCandidatureServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
