/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.besoin;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import framework.database.utilitaire.GConnection;
import java.util.ArrayList;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;
import model.gestionBesoin.WorkLoad;
import model.requis.Service;
/**
 *
 * @author ITU
 */
@WebServlet(name = "AddBesoinServlet", urlPatterns = {"/addBesoinServlet"})
public class AddBesoinServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  { 
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        Connection connex = null;
       try {
                connex = GConnection.getSimpleConnection();
                
       }catch(Exception exe){
           exe.printStackTrace();
            req.setAttribute("erreur", exe.getMessage());
       }
       RequestDispatcher dispat = req.getRequestDispatcher("pages/create_employe.jsp");
        dispat.forward(req,res);
    }
     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  { 
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
       try {
           Connection conn = GConnection.getSimpleConnection();
           
           HttpSession session = req.getSession();
           Service service = (Service)session.getAttribute("service");
           Besoin besoin = (Besoin)session.getAttribute("besoin");
           String description = req.getParameter("descri");
           LocalDate dateActuel = LocalDate.now();
           besoin.setService(service);
           besoin.setCreationDate(dateActuel);
           besoin.setDescription(description);
           besoin.setStatus(1);
           System.out.println("Tsy tonga");
           besoin.create(conn);
           System.out.println("Tonga");
           Besoin lastBesoin = Besoin.getLastBesoin(conn);
           Task.insertListTask(conn, besoin.getTasks(), lastBesoin);
           WorkLoad.insertListWorkLoad(conn, besoin.getWorkLoad(), lastBesoin);
           
           conn.close();
           
       }catch(Exception exe){
            String message = exe.getMessage();
            exe.printStackTrace();
            System.out.println(exe.getMessage());
            req.setAttribute("erreur","erreur "+message);          
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
  
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
