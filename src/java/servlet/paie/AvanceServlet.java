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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.paie.abscence.AbscenceService;
import model.paie.avance.Avance;
import model.paie.avance.AvanceService;
import model.paie.prime.Prime;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "AvanceServlet", urlPatterns = {"/Avance"})
public class AvanceServlet extends HttpServlet {

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
            out.println("<title>Servlet AvanceServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AvanceServlet at " + request.getContextPath() + "</h1>");
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
            
            List<Avance> avances = AvanceService.getAllAvance(idEmploye, mois, year, connection);
            request.setAttribute("avances", avances);
            
            double montantPossible = AvanceService.calculMontantPossible(idEmploye, connection);
            request.setAttribute("montantPossible", montantPossible);
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/paie/detail-employe.css");

            List<String> js = new ArrayList<>();

            request.setAttribute("title", "Prime d'employee");
            request.setAttribute("contentPage", "./pages/paie/avance.jsp");
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
            double montant = Double.valueOf(request.getParameter("montant"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            
            AvanceService.demanderAvance(idEmploye, date, montant);
            
            response.sendRedirect("./Avance?idEmploye=" + idEmploye);
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
