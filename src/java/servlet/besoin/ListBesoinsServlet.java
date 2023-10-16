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
import java.sql.Connection;
import java.util.ArrayList;
import model.gestionBesoin.Besoin;
import model.requis.Service;

@WebServlet(name = "ListBesoinsServlet", urlPatterns = {"/listBesoins"})
public class ListBesoinsServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
                Connection connex = GConnection.getSimpleConnection();
                ArrayList<Service> services = Service.getAll(connex);             
                req.setAttribute("services", services);
                if(req.getAttribute("allBesoin") != null) {
                    req.setAttribute("allBesoin", req.getAttribute("allBesoin"));
                }
                else {
                    //HttpSession session = req.getSession();
                    //Service service = (Service)session.getAttribute("service");
                    Service service = Service.getById(connex, 1);
                    ArrayList<Besoin> allBesoins = Besoin.getBesoinsService(connex, service);
                    req.setAttribute("allBesoin", allBesoins);
                }
              
                connex.close();
          } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
          RequestDispatcher dispat = req.getRequestDispatcher("pages/besoin/besoin_list.jsp");
          dispat.forward(req, res);
     }

     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          try {
            Connection conn = GConnection.getSimpleConnection();
            
            String status = req.getParameter("status");
            Integer idService = Integer.valueOf(req.getParameter("service"));
            Service service = Service.getById(conn, idService);
            ArrayList<Besoin> besoinFilter = Besoin.getBesoinByStatus(conn, status, service);
            req.setAttribute("allBesoin", besoinFilter);
            
            conn.close();

        } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
            doGet(req,res);
    }
}