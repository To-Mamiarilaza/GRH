/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.conge;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.conge.ChefDemandeConge;
import model.conge.Conge;
import model.conge.service.CongeManager;
import model.requis.User;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "CongesSubordonneDemandeListServlet", urlPatterns = {"/CongesSubordonneDemandeList"})
public class CongesSubordonneDemandeListServlet extends HttpServlet {

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
        try {

            User user = (User) request.getSession().getAttribute("user");
            
            ChefDemandeConge chefDemande = CongeManager.getCongeChefDemandeConge(user.getIdEmploye(), null);

            List<Conge> congeList = chefDemande.getDemandes();
            String sectionTitle = "Les demandes de conges";
            
            if (request.getParameter("demande") != null) {
                congeList = chefDemande.getDemandes();
            } else if (request.getParameter("current") != null) {
                congeList = chefDemande.getCurrents();
                sectionTitle = "Les conges en cours";
            } else if (request.getParameter("valide") != null) {
                congeList = chefDemande.getValides();
                sectionTitle = "Les conges validés";
            } else if (request.getParameter("refuse") != null) {
                sectionTitle = "Les conges refusés";
                congeList = chefDemande.getRefuses();
            }
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/conges/conges-personnel.css");

            List<String> js = new ArrayList<>();
            
            request.setAttribute("sectionTitle", sectionTitle);
            request.setAttribute("congeList", congeList);

            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/conges/congesChefDemandeList.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
