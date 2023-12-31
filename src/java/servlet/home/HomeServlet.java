/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.home;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.employe.Employe;
import model.gestionBesoin.Besoin;
import model.paie.fiche.EtatPaie;
import model.requis.User;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    final String RH_DEPARTEMENT = "Ressources humaines";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            RequestDispatcher dispatch = request.getRequestDispatcher("./pages/home/login.jsp");
            dispatch.forward(request, response);
        } else {

            request.setAttribute("RH_DEPARTEMENT", RH_DEPARTEMENT);
            
            LocalDate dateFin = LocalDate.now();
            LocalDate dateDebut = LocalDate.of(dateFin.getYear(), dateFin.getMonthValue(), 1);
            
            double totalSalaire = 0;
            int nombreEmploye = 0;
            int nombreBesoin = 0;
            try {
                Connection connection = GConnection.getSimpleConnection();
                totalSalaire = EtatPaie.getEtatPaie(dateDebut, dateFin, connection).getNetAPayerTotal();
                nombreEmploye = Employe.countEmploye(connection);
                nombreBesoin = Besoin.getNewBesoinsNumber(connection);
                connection.close();
            } catch (Exception e) {
                System.out.println("ERREUR : " + e.getMessage());
                e.printStackTrace();
            }
            
            request.setAttribute("totalSalaire", totalSalaire);
            request.setAttribute("nombreEmploye", nombreEmploye);
            request.setAttribute("nombreBesoin", nombreBesoin);
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/quiz/quiz_creation.css");

            List<String> js = new ArrayList<>();
            js.add("./assets/js/quiz/quiz-creation.js");

            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/home/welcome.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            

            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
            
            System.out.println("OK j'arrive ici");

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
