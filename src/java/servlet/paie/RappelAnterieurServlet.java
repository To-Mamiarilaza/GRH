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
import model.paie.rappelperiode.RappelPeriode;
import model.paie.rappelperiode.RappelPeriodeService;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "RappelAnterieurServlet", urlPatterns = {"/RappelPeriode"})
public class RappelAnterieurServlet extends HttpServlet {

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
            out.println("<title>Servlet RappelAnterieurServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RappelAnterieurServlet at " + request.getContextPath() + "</h1>");
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

            int mois = LocalDate.now().getMonthValue();
            int year = LocalDate.now().getYear();

            if (request.getParameter("mois") != null) {
                mois = Integer.valueOf(request.getParameter("mois"));
                year = Integer.valueOf(request.getParameter("year"));
            }

            request.setAttribute("mois", mois);
            request.setAttribute("year", year);

            List<RappelPeriode> rappels = RappelPeriodeService.getAllRappelPeriode(mois, year, connection);
            request.setAttribute("rappels", rappels);
            
            List<Service> services = Service.getAll(connection);
            request.setAttribute("services", services);

            List<String> css = new ArrayList<>();
            css.add("./assets/css/paie/detail-employe.css");

            List<String> js = new ArrayList<>();

            request.setAttribute("title", "Periode de rappel");
            request.setAttribute("contentPage", "./pages/paie/periodeAnterieur.jsp");
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
            int nbMois = Integer.valueOf(request.getParameter("nbMois"));
            double modification = Double.valueOf(request.getParameter("modification"));

            List<Service> serviceList = new ArrayList<>();
            String[] idServices = request.getParameterValues("services");
            for (String idService : idServices) {
                serviceList.add(new Service(Integer.valueOf(idService), 1));
            }
            
            RappelPeriodeService.addNewRappelle(nbMois, modification, serviceList);
            
            response.sendRedirect("./RappelPeriode");
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
