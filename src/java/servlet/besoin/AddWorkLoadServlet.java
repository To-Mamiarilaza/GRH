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
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Unity;
import model.gestionBesoin.WorkLoad;
import model.gestionProfile.WantedProfile;

@WebServlet(name = "AddWorkLoadServlet", urlPatterns = {"/addWorkLoadServlet"})
public class AddWorkLoadServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
                Connection conn = GConnection.getSimpleConnection();

                        String poste = req.getParameter("poste");
                String volumeHorraire = req.getParameter("vh");
                String unitySelect = req.getParameter("unity");
                System.out.println("unity : "+unitySelect);
                HttpSession session = req.getSession();
                Besoin besoin = (Besoin)session.getAttribute("besoin");
                WantedProfile wp = (WantedProfile)session.getAttribute("profileValided");
                Unity unity = Unity.getByName(conn, unitySelect);
                WorkLoad wl = new WorkLoad(wp, Integer.valueOf(volumeHorraire), unity);
                System.out.println("taille avant : "+besoin.getWorkLoad().size());
                besoin.delWorkLoad(wl);
                
                for(int i = 0; i < besoin.getWorkLoad().size(); i++) {
                    System.out.println("item delete : "+besoin.getWorkLoad().get(i).getWantedProfile().getPoste());
                }
                System.out.println("taille : "+besoin.getWorkLoad().size());
                
                conn.close();
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
                System.out.println("Tafa");
                String volumeHorraire = req.getParameter("volumeHorraire");
                String unitySelect = req.getParameter("unitySelect");
              
                HttpSession session = req.getSession();
                Besoin besoin = (Besoin)session.getAttribute("besoin");
                WantedProfile wp = (WantedProfile)session.getAttribute("profileValided");
                Unity unity = Unity.getById(conn, Integer.valueOf(unitySelect));
                WorkLoad wl = new WorkLoad(wp, Integer.valueOf(volumeHorraire), unity);
                besoin.addWorkLoad(wl);
                
                conn.close();
                
                out.println(wp.getPoste()+"&"+unity.getUnity());
          } catch (Exception exe) {
              exe.printStackTrace();
               req.setAttribute("erreur", exe.getMessage());
          }
          //RequestDispatcher dispat = req.getRequestDispatcher("pages/besoin/besoin_insertion2.jsp");
          //dispat.forward(req, res);
    }
}