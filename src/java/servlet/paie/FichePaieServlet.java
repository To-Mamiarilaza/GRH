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
import java.util.ArrayList;
import java.util.List;
import model.paie.fiche.FichePaie;
import model.paie.fiche.FichePaieManager;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "FichePaieServlet", urlPatterns = {"/FichePaie"})
public class FichePaieServlet extends HttpServlet {

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
            out.println("<title>Servlet FichePaieServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FichePaieServlet at " + request.getContextPath() + "</h1>");
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
                    
            int idEmploye = Integer.valueOf(request.getParameter("idEmploye"));
            request.setAttribute("idEmploye", idEmploye);
            
            // Date par defaut
            LocalDate dateFin = LocalDate.now();
            LocalDate dateDebut = LocalDate.of(dateFin.getYear(), dateFin.getMonthValue(), 1);
            
            if (request.getParameter("dateDebut") != null) {
                dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
                dateFin = LocalDate.parse(request.getParameter("dateFin"));
            }
            
            request.setAttribute("dateDebut", dateDebut);
            request.setAttribute("dateFin", dateFin);
            
            
            FichePaie fichePaie = FichePaieManager.getFichePaie(idEmploye, dateDebut, dateFin, connection);
            request.setAttribute("fichePaie", fichePaie);
                    
            List<String> css = new ArrayList<>();
            css.add("./assets/css/paie/detail-employe.css");

            List<String> js = new ArrayList<>();

            request.setAttribute("title", "Prime d'employee");
            request.setAttribute("contentPage", "./pages/paie/fichePaieEmploye.jsp");
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
