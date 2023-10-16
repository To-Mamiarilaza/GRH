/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
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
import model.candidature.Candidature;
import model.candidature.Formation;
import model.candidature.FormationPath;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "FormationPathInsertionServlet", urlPatterns = {"/FormationPathInsertionServlet"})
public class FormationPathInsertionServlet extends HttpServlet {

    List<Formation> listForm = new ArrayList<>();
    FormationPath fo = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormationPathInsertionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormationPathInsertionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getParameter("in") == null) {
                Diplome d = new Diplome();
                List<Diplome> listeDiplome = d.getAllDiplome(null);

                request.setAttribute("listeDiplome", listeDiplome);
                RequestDispatcher req = request.getRequestDispatcher("/pages/candidature/formation_path_insertion.jsp");
                req.forward(request, response);

                listForm.clear();
            } else {
                HttpSession session = request.getSession();
                Candidature can = (Candidature) session.getAttribute("candidature");
                can.setFormationPath(fo);
                response.sendRedirect("pages/candidature/other_information_insertion.jsp");

            }
        } catch (Exception ex) {
            Logger.getLogger(FormationPathInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String diplomeObtenu = request.getParameter("diplomeobtenu");
        String annee = request.getParameter("annee");
        String diplome = request.getParameter("diplome");
        String ecole = request.getParameter("ecole");

        Formation f = new Formation(Integer.valueOf(annee), diplome, ecole);

        listForm.add(f);

        fo = new FormationPath(new Diplome(diplomeObtenu, 1), listForm);
        response.setContentType("application/json");
        Gson gson = new Gson();
        String jsonData = gson.toJson(fo);
        response.getWriter().write(jsonData);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
