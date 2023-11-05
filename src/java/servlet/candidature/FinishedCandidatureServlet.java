/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

import framework.database.utilitaire.GConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
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
            
            Connection connection = GConnection.getSimpleConnection();
            
            can.create(connection, wp, can.getPersonnalInformation().getAdresse().getAdresse(), Integer.valueOf(can.getPersonnalInformation().getSexe().getSexe()), can.getProfessionalCareer().getExperience().getExperience(), can.getFormationPath().getDiplome().getDiplome());
            int id = new Candidature().getLastId(connection);
            
            String candidaturePictureName = "_00" + id + "_" + can.getPersonnalInformation().getName() + "_" + can.getPersonnalInformation().getFirstName() + "_" + "candidature";
            can.setPictureName(id, candidaturePictureName, connection);
            
            Career c = can.getProfessionalCareer().getCareers().get(0);
            c.create(connection, id);
            Formation f = can.getFormationPath().getFormations().get(0);
            f.create(connection, id);
            
            
            response.sendRedirect("/GRH/CandidatureExportPDFServlet");
        } catch (Exception ex) {
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
