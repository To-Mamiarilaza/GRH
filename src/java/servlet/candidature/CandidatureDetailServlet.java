/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

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
import java.util.ArrayList;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.requis.Service;
import model.requis.User;

@WebServlet(name = "CandidatureDetailServlet", urlPatterns = {"/candidatureDetail"})
public class CandidatureDetailServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
              Connection conn = GConnection.getSimpleConnection();
              
                       
              
              conn.close();
          } catch (Exception exe) {
              exe.printStackTrace();
               req.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = req.getRequestDispatcher("./pages/candidature/candidature_detail.jsp");
          dispat.forward(req, res);
     }

     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          try {
             Connection conn = GConnection.getSimpleConnection();
            
          
            conn.close();
        } catch (Exception exe) {
            exe.printStackTrace();
            req.setAttribute("erreur", exe.getMessage());
        }
         doGet(req, res);
    }
}