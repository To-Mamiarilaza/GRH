/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.employe;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.employe.Employe;

/**
 *
 * @author chalman
 */
@WebServlet(name = "TraitListePersonnalServlet", urlPatterns = {"/traitListePersonnal"})
public class TraitListePersonnalServlet extends HttpServlet {

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
            out.println("<title>Servlet TraitListePersonnalServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TraitListePersonnalServlet at " + request.getContextPath() + "</h1>");
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
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispat = request.getRequestDispatcher("listPersonnel");
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
        try {
            String idHelp = "";
            if(request.getParameter("id_help") != null) {
                idHelp = request.getParameter("id_help");
            }
            
            if(idHelp.equals("1")) {   //Rechercher
               String wordToSearch = request.getParameter("search_emp");
                List<Employe> employes = Employe.searchEmployeByText(wordToSearch);
                request.setAttribute("employes", employes);
            }
            else {  //Filtrer
                Integer idService = Integer.valueOf(request.getParameter("service"));
                Integer idWantedProfile = Integer.valueOf(request.getParameter("poste"));
                
                List<Employe> employes = Employe.getFilter(idService, idWantedProfile);
                request.setAttribute("employes", employes);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher dispat = request.getRequestDispatcher("listPersonnel");
        dispat.forward(request, response);
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
