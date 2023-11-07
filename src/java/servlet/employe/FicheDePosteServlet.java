/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.employe;

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
import java.util.ArrayList;
import java.util.List;
import model.gestionBesoin.Besoin;
import model.requis.Service;

/**
 *
 * @author chalman
 */
@WebServlet(name = "FicheDePosteServlet", urlPatterns = {"/ficheDePoste"})
public class FicheDePosteServlet extends HttpServlet {

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
            out.println("<title>Servlet FicheDePosteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FicheDePosteServlet at " + request.getContextPath() + "</h1>");
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
         response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
          
          try {
               Connection connex = GConnection.getSimpleConnection();
                
                List<String> css = new ArrayList<>();
                css.add("./assets/css/quiz/quiz_creation.css");

                List<String> js = new ArrayList<>();
                js.add("./assets/js/quiz/quiz-creation.js");

                request.setAttribute("title", "Fiche de poste");
                request.setAttribute("contentPage", "./pages/employe/fiche_de_poste.jsp");
                request.setAttribute("css", css);
                request.setAttribute("js", js);
                
                
                ArrayList<Service> services = Service.getAll(connex);             
                request.setAttribute("services", services);
                if(request.getAttribute("allBesoin") != null) {
                    request.setAttribute("allBesoin", request.getAttribute("allBesoin"));
                }
                else {
                    //HttpSession session = req.getSession();
                    //Service service = (Service)session.getAttribute("service");
                    Service service = Service.getById(connex, 1);
                    ArrayList<Besoin> allBesoins = Besoin.getBesoinsService(connex, service);
                    request.setAttribute("allBesoin", allBesoins);
                }
              
                connex.close();
          } catch (Exception exe) {
               request.setAttribute("erreur", exe.getMessage());
               exe.printStackTrace();
          }
          RequestDispatcher dispat = request.getRequestDispatcher("./template.jsp");
          dispat.forward(request, response);
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
