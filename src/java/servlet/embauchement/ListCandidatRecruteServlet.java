/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.embauchement;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;
import model.requis.Service;

/**
 *
 * @author chalman
 */
@WebServlet(name = "ListCandidatRecruteServlet", urlPatterns = {"/listCandidatRecrute"})
public class ListCandidatRecruteServlet extends HttpServlet {

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
            out.println("<title>Servlet ListCandidatRecruteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCandidatRecruteServlet at " + request.getContextPath() + "</h1>");
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
                Connection conn = GConnection.getSimpleConnection();

                
                List<String> css = new ArrayList<>();
                css.add("./assets/css/quiz/quiz_creation.css");

                List<String> js = new ArrayList<>();
                request.setAttribute("title", "Candidat recrutes");
                request.setAttribute("contentPage", "./pages/embauchement/candidats_recrutes_list.jsp");
                request.setAttribute("css", css);
                request.setAttribute("js", js);
                
                
                WantedProfile wp = new WantedProfile();

                ArrayList<Service> services = Service.getAll(conn);
                List<WantedProfile> wps = wp.getAll(conn);
                request.setAttribute("services", services);
                request.setAttribute("wantedProfiles", wps);
                HttpSession session = request.getSession();
                
                if(request.getAttribute("candidatureList") != null) {
                    request.setAttribute("candidatures", request.getAttribute("candidatureList"));
                    request.setAttribute("title", session.getAttribute("title"));
                    session.removeAttribute("title");
                }
                else {
                    ArrayList<Candidature> candidatures = Candidature.getAllRecrutes(conn);
                    String title = Candidature.getTitle(5);
                    request.setAttribute("title", title);
                    request.setAttribute("candidatures", candidatures);
                }
              
              conn.close();
          } catch (Exception exe) {
                exe.printStackTrace();
                request.setAttribute("erreur", exe.getMessage());
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
        System.out.println("Dans Get : "+request.getAttribute("title"));
           request.setAttribute("title", request.getAttribute("title"));
        doGet(request, response);
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
