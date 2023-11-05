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
import java.sql.Date;
import java.util.List;
import model.candidature.Candidature;
import model.embauchement.Contrat;
import model.embauchement.Province;
import model.embauchement.WorkLocation;
import model.employe.Employe;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author chalman
 */
@WebServlet(name = "ContratTravailServlet", urlPatterns = {"/contratTravail"})
public class ContratTravailServlet extends HttpServlet {

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
            out.println("<title>Servlet ContratTravailServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContratTravailServlet at " + request.getContextPath() + "</h1>");
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
               
          } catch (Exception exe) {
               request.setAttribute("erreur", exe.getMessage());
               exe.printStackTrace();
          }
          RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/contrat_travail_insertion.jsp");
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
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
          
        try {
            String dateEnd = request.getParameter("end_date");
            String dateStart = request.getParameter("start_date") ;
            System.out.println("Date : "+dateEnd.trim().equalsIgnoreCase(""));
            if(dateStart != null && request.getParameter("salary") != null && request.getParameter("work_location") != null && request.getParameter("id_province") != null) {
                Date startDate = Date.valueOf(request.getParameter("start_date"));
                Date endDate = null;
                if(!dateEnd.trim().equalsIgnoreCase("")) {
                    endDate = Date.valueOf(request.getParameter("end_date"));
                }
                
                
                Double salary = Double.valueOf(request.getParameter("salary"));
                Integer idWorkLocation = Integer.valueOf(request.getParameter("work_location"));
                Integer idProvince = Integer.valueOf(request.getParameter("id_province"));
                Integer idWantedProfile = Integer.valueOf(request.getParameter("id_wanted_profile"));
                HttpSession session = request.getSession();
                Candidature candidat = (Candidature)session.getAttribute("candidatRecrute");
                List<Employe> employes = Employe.getAll();
                request.setAttribute("employes", employes);
                
                Contrat contrat = new Contrat(WorkLocation.getById(idWorkLocation), candidat, Province.getById(idProvince), salary, startDate, endDate, 1, WantedProfile.getById(null, idWantedProfile));         
                session.setAttribute("contrat", contrat);
            }
            else {
                throw new Exception("Verifier votre donnee");
            }
            
               
        } catch (Exception exe) {
            request.setAttribute("erreur", exe.getMessage());
            exe.printStackTrace();
            RequestDispatcher dispat = request.getRequestDispatcher("contratEssai");
            dispat.forward(request, response);
        }
        RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/contrat_travail_insertion.jsp");
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
