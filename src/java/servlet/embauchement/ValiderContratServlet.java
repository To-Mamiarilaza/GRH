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
import model.employe.ClasseEmploye;
import model.employe.Employe;
import util.pdf.DateManager;

/**
 *
 * @author chalman
 */
@WebServlet(name = "ValiderContratServlet", urlPatterns = {"/validerContrat"})
public class ValiderContratServlet extends HttpServlet {

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
            out.println("<title>Servlet ValiderContratServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValiderContratServlet at " + request.getContextPath() + "</h1>");
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
            
            HttpSession session = request.getSession();
            Employe emp = Employe.getById(Integer.valueOf(request.getParameter("idEmploye")));
            session.setAttribute("employe", emp);
            List<ClasseEmploye> classeEmployes = ClasseEmploye.getAllClasseEmploye(connection);
            request.setAttribute("classeEmployes", classeEmployes);  
            connection.close();
                 
            RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/valider_contrat.jsp");
            dispat.forward(request, response);
        } catch(Exception e) {
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
            Connection connection = GConnection.getSimpleConnection();
            
                HttpSession session = request.getSession();
                if(session.getAttribute("employe") != null) {
                    Employe emp = (Employe)session.getAttribute("employe");
                    Contrat contratValider = emp.getContrat();
                    session.removeAttribute("employe");
                    Candidature candidatRecrute = emp.getContrat().getCandidature();
                    contratValider.validateContrat(connection);
                    candidatRecrute.candidatEmbauched(connection);
                    Date dateEmbauched = Date.valueOf(DateManager.getDateActuel());
                    Integer idClasseEmploye = Integer.valueOf(request.getParameter("id_classe_employe"));
                    ClasseEmploye classeEmploye = ClasseEmploye.getClasseEmployeById(idClasseEmploye, connection);

                    emp.setContrat(contratValider);
                    emp.setDateEmbauche(dateEmbauched);
                    emp.setClasseEmploye(classeEmploye);
                    emp.update(connection);
                }
                else {
                    Contrat contratValider = (Contrat)session.getAttribute("contratValider");
                    session.removeAttribute("contratValider");
                    Candidature candidatRecrute = (Candidature)session.getAttribute("candidatRecrute");
                    contratValider.validateContrat(connection);
                    candidatRecrute.candidatEmbauched(connection);
                    Date dateEmbauched = Date.valueOf(DateManager.getDateActuel());
                    Integer idClasseEmploye = Integer.valueOf(request.getParameter("id_classe_employe"));
                    ClasseEmploye classeEmploye = ClasseEmploye.getClasseEmployeById(idClasseEmploye, connection);

                    Employe employe = new Employe(contratValider, dateEmbauched, 1, classeEmploye);
                    String numMatricule = employe.generateNumMatricule(connection);
                    employe.setNumMatricule(numMatricule);
                    employe.save(connection);
                }
               
                connection.close();
                 
                RequestDispatcher dispat = request.getRequestDispatcher("listPersonnel");
                dispat.forward(request, response);
                    
            
            connection.close();
        } catch(Exception e) {
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
