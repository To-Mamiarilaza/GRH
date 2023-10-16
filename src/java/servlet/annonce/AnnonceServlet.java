/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.annonce;

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

@WebServlet(name = "AnnonceServlet", urlPatterns = {"/annonce"})
public class AnnonceServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
              Connection conn = GConnection.getSimpleConnection();
              
              HttpSession session = req.getSession();
              User userConnected = (User)session.getAttribute("user");
              ArrayList<Service> services = Service.getAll(conn);
              req.setAttribute("services", services);

                if(req.getAttribute("annonces") != null) {
                    req.setAttribute("annonces", req.getAttribute("annonces"));
                }
                else {
                    ArrayList<Annonce> annonces = Annonce.getAnnonceByStatus(conn, "1", userConnected.getService());
                    req.setAttribute("annonces", annonces);
                }             
              
              conn.close();
          } catch (Exception exe) {
              exe.printStackTrace();
               req.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = req.getRequestDispatcher("./pages/annonce/annonce_list.jsp");
          dispat.forward(req, res);
     }

     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          try {
             Connection conn = GConnection.getSimpleConnection();
            
            Integer idService = Integer.valueOf(req.getParameter("service"));
            Service service = Service.getById(conn, idService);
            ArrayList<Annonce> annonceFilter = Annonce.getAnnonceByStatus(conn, "1", service);
            req.setAttribute("annonces", annonceFilter);
            
            conn.close();
        } catch (Exception exe) {
            exe.printStackTrace();
            req.setAttribute("erreur", exe.getMessage());
        }
         doGet(req, res);
    }
}