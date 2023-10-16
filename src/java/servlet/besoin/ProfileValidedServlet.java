/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.besoin;

import framework.database.utilitaire.GConnection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import model.gestionProfile.WantedProfile;

@WebServlet(name = "ProfileValidedServlet", urlPatterns = {"/profileValidedServlet"})
public class ProfileValidedServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
            
          } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = req.getRequestDispatcher("pages/besoin/besoin_insertion.jsp");
          dispat.forward(req, res);
     }

     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          try {
                Connection conn = GConnection.getSimpleConnection();
                
                String idValue = req.getParameter("idValue");
                WantedProfile wp = WantedProfile.getById(conn, Integer.valueOf(idValue));
                HttpSession session = req.getSession();
                session.setAttribute("profileValided", wp);
                
                conn.close();
          } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
          //RequestDispatcher dispat = req.getRequestDispatcher("pages/besoin/besoin_insertion2.jsp");
          //dispat.forward(req, res);
    }
}