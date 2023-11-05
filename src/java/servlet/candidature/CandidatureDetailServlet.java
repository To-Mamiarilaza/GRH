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
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;

@WebServlet(name = "CandidatureDetailServlet", urlPatterns = {"/candidatureDetail"})
public class CandidatureDetailServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
                Connection conn = GConnection.getSimpleConnection();
              
                Integer idCandidature = Integer.valueOf(req.getParameter("idCandidature"));
                Candidature candidat = Candidature.getById(conn, idCandidature);
                req.setAttribute("candidat", candidat);
                         
                List<String> css = new ArrayList<>();
                css.add("./assets/css/annonce/annonce-list.css");

                List<String> js = new ArrayList<>();

                req.setAttribute("title", "list candidature");
                req.setAttribute("contentPage", "./pages/candidature/candidature_detail.jsp");
                req.setAttribute("css", css);
                req.setAttribute("js", js);
              
              conn.close();
          } catch (Exception exe) {
              exe.printStackTrace();
               req.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = req.getRequestDispatcher("./template.jsp");
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