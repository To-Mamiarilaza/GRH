/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.profil;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "DeleteProfileServlet", urlPatterns = {"/DeleteProfileServlet"})
public class DeleteProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateSession</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSession at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("indice") != null) {
            int indice = Integer.valueOf(request.getParameter("indice"));
            WantedProfile wp = new WantedProfile();
            try {
                wp.deleteWantedProfile(indice, null);
                response.sendRedirect("pages/besoin/besoin_insertion.jsp");
            } catch (Exception ex) {
                Logger.getLogger(DeleteProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("idAdresse") != null) {
            int idAdresse = Integer.valueOf(request.getParameter("idAdresse"));
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.getAdresseNote().remove(idAdresse);
        } else if (request.getParameter("idDiplome") != null) {
            int idDiplome = Integer.valueOf(request.getParameter("idDiplome"));
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.getDiplomeNote().remove(idDiplome);
        } else if (request.getParameter("idSexe") != null) {
            int idSexe = Integer.valueOf(request.getParameter("idSexe"));
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.getSexeNote().remove(idSexe);
        } else if (request.getParameter("idSalaire") != null) {
            int idSalaire = Integer.valueOf(request.getParameter("idSalaire"));
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.getSalaireNote().remove(idSalaire);
        } else if (request.getParameter("idExperience") != null) {
            int idExperience = Integer.valueOf(request.getParameter("idExperience"));
            HttpSession session = request.getSession();
            WantedProfile wp = (WantedProfile) session.getAttribute("wantedprofile");
            wp.getExperienceNote().remove(idExperience);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
