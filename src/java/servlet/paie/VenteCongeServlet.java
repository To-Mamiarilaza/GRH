/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.paie;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.conge.CongePersonnel;
import model.conge.service.CongeManager;
import model.paie.heuresup.HeureSupplementaireService;
import model.paie.venteconge.VenteConge;
import model.paie.venteconge.VenteCongeService;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "VenteCongeServlet", urlPatterns = {"/VenteConge"})
public class VenteCongeServlet extends HttpServlet {

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
            out.println("<title>Servlet VenteCongeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VenteCongeServlet at " + request.getContextPath() + "</h1>");
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
        try {
            Connection connection = GConnection.getSimpleConnection();
            
            // Les données requis
            int idEmploye = Integer.valueOf(request.getParameter("idEmploye"));
            request.setAttribute("idEmploye", idEmploye);

            int mois = LocalDate.now().getMonthValue();
            int year = LocalDate.now().getYear();

            if (request.getParameter("mois") != null) {
                mois = Integer.valueOf(request.getParameter("mois"));
                year = Integer.valueOf(request.getParameter("year"));
            }
            
            request.setAttribute("mois", mois);
            request.setAttribute("year", year);
            
            List<VenteConge> venteConges = VenteCongeService.getVenteConge(idEmploye, mois, year, connection);
            request.setAttribute("venteConges", venteConges);
            
            CongePersonnel conge = CongeManager.getCongePersonnelInfo(idEmploye, connection);
            request.setAttribute("congeRestant", conge.getSoldeRestant());
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/paie/detail-employe.css");

            List<String> js = new ArrayList<>();

            request.setAttribute("title", "Gestion des ventes de conges");
            request.setAttribute("contentPage", "./pages/paie/venteConge.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            connection.close();
            
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
            int idEmploye = Integer.valueOf(request.getParameter("idEmploye"));
            String debut = request.getParameter("debut");
            String fin = request.getParameter("fin");
            double montant = Double.valueOf(request.getParameter("montant"));
            
            VenteCongeService.sellConge(idEmploye, LocalDate.parse(debut), LocalDate.parse(fin), montant);
            
            response.sendRedirect("./VenteConge?idEmploye=" + idEmploye);
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
