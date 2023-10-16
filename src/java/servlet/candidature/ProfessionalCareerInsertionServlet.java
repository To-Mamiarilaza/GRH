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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.candidature.Candidature;
import model.candidature.Career;
import model.candidature.ProfessionalCareer;
import model.gestionProfile.Experience;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "ProfessionalCareerInsertionServlet", urlPatterns = {"/ProfessionalCareerInsertionServlet"})
public class ProfessionalCareerInsertionServlet extends HttpServlet {

    List<Career> lc = new ArrayList<>();
    List<String> listeTask = new ArrayList<>();
    ProfessionalCareer pc = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfessionalCareerInsertionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfessionalCareerInsertionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("finish") == null) {
            try {
                Experience e = new Experience();
                List<Experience> listeExperience = e.getAllExperience(null);

                listeTask.clear();
                lc.clear();

                request.setAttribute("listeExperience", listeExperience);
                RequestDispatcher req = request.getRequestDispatcher("/pages/candidature/professional_career_insertion.jsp");
                req.forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ProfessionalCareerInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            HttpSession session = request.getSession();
            Candidature can = (Candidature) session.getAttribute("candidature");
            can.setProfessionalCareer(pc);

            response.sendRedirect("/RessourceHumaine/FormationPathInsertionServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            if (request.getParameter("experience") == null || request.getParameter("startDate") == null || request.getParameter("endDate") == null) {

                System.out.println("task zao no inserena");
                String task = request.getParameter("tache");

                listeTask.add(task);

                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(listeTask);
                response.getWriter().write(jsonData);
            } else {
                System.out.println("tsy task zao no inserena");
                String experience = request.getParameter("experience");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");

                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");

                java.util.Date date1 = dateFormat1.parse(startDate);
                Date start = new Date(date1.getTime());

                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

                java.util.Date date2 = dateFormat2.parse(endDate);
                Date end = new Date(date2.getTime());

                String societe = request.getParameter("societe");
                String ancienPoste = request.getParameter("poste");

                Career c = new Career(start, end, societe, ancienPoste, listeTask);

                lc.add(c);

                pc = new ProfessionalCareer(new Experience(experience, 1), lc);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(lc);
                response.getWriter().write(jsonData);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ProfessionalCareerInsertionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
