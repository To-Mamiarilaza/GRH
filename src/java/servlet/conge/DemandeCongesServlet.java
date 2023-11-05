/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.conge;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import model.conge.service.CongeManager;
import model.conge.TypeConge;
import model.requis.User;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "DemandeCongesServlet", urlPatterns = {"/DemandeConges"})
public class DemandeCongesServlet extends HttpServlet {

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
            List<TypeConge> typeConges = TypeConge.getAllTypeConge(null);
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/conges/conges-personnel.css");
            
            List<String> js = new ArrayList<>();
            
            request.setAttribute("title", "Demande De Conge");
            request.setAttribute("contentPage", "./pages/conges/demandeCongeInsertion.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            request.setAttribute("typeConges", typeConges);
            
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
            User user = (User) request.getSession().getAttribute("user");
            
            String explication = request.getParameter("explication");
            int idTypeConge = Integer.valueOf(request.getParameter("idTypeConge"));
            LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
            
            CongeManager.demanderConge(user.getIdEmploye(), explication, idTypeConge, dateDebut, dateFin, null);
            
            response.sendRedirect("./CongesPersonnel");
        } catch (Exception e) {
            try {
                List<TypeConge> typeConges = TypeConge.getAllTypeConge(null);

                List<String> css = new ArrayList<>();
                css.add("./assets/css/conges/conges-personnel.css");

                List<String> js = new ArrayList<>();

                request.setAttribute("title", "Demande de conge");
                request.setAttribute("contentPage", "./pages/conges/demandeCongeInsertion.jsp");
                request.setAttribute("css", css);
                request.setAttribute("js", js);

                request.setAttribute("typeConges", typeConges);
                request.setAttribute("error", e);

                RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
                dispatch.forward(request, response);
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
