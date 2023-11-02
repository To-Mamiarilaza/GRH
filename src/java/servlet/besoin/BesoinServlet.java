/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.besoin;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Unity;
import model.gestionProfile.AdresseNote;
import model.gestionProfile.BestCritere;
import model.gestionProfile.DiplomeNote;
import model.gestionProfile.ExperienceNote;
import model.gestionProfile.SalaireNote;
import model.gestionProfile.SexeNote;
import model.gestionProfile.WantedProfile;
import model.requis.Service;
import model.requis.User;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "BesoinServlet", urlPatterns = {"/besoin-insertion"})
public class BesoinServlet extends HttpServlet {

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

        WantedProfile wp = new WantedProfile();
        HttpSession session = request.getSession();
        session.setAttribute("wantedprofile", wp);

        // Add attribute to request
        try {
            Connection connex = GConnection.getSimpleConnection();

            Besoin besoin = new Besoin();
            session.setAttribute("besoin", besoin);
            User user = (User)session.getAttribute("user");
            
            Service service = user.getService();
            session.setAttribute("service", service);

            ArrayList<Unity> unitys = Unity.getAll(connex);
            request.setAttribute("service", service);
            request.setAttribute("unitys", unitys);

            // Récupérer les indices des profils recherchés
            List<Integer> lsIndice = wp.getIdWantedProfile(null);
            BestCritere bc = new BestCritere();
            List<BestCritere> listep = bc.getListeProfile(lsIndice, null);
            
            //pour le css
              List<String> css = new ArrayList<>();
            css.add("./assets/css/besoin/besoin-insertion.css");
            
            List<String> js = new ArrayList<>();
            js.add("./assets/js/besoin/besoin-insertion.js");
            
            request.setAttribute("title", "insertion besoin");
            request.setAttribute("contentPage", "./pages/besoin/besoin_insertion.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);

            response.getWriter().println(listep.size());
            request.setAttribute("listeProfile", listep);
            // dispatch to target servlet
            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
            
        } catch (Exception exe) {
            request.setAttribute("erreur", exe.getMessage());
            exe.printStackTrace();
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
