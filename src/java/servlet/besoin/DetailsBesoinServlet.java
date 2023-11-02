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
import java.util.List;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;
import model.gestionBesoin.WorkLoad;
import model.requis.Service;

@WebServlet(name = "DetailsBesoinServlet", urlPatterns = {"/detailsBesoin"})
public class DetailsBesoinServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
                Connection conn = GConnection.getSimpleConnection();
              
              
                List<String> css = new ArrayList<>();
                css.add("./assets/css/quiz/quiz_creation.css");

                List<String> js = new ArrayList<>();
                js.add("./assets/js/quiz/quiz-creation.js");

                req.setAttribute("title", "Detail besoin");
                req.setAttribute("contentPage", "./pages/besoin/besoin_detail.jsp");
                req.setAttribute("css", css);
                req.setAttribute("js", js);
              
               Integer idBesoin = Integer.valueOf(req.getParameter("idBesoin"));
               Besoin besoin = Besoin.getById(conn, idBesoin);
               ArrayList<Task> tasks = Task.getAllTaskBesoin(conn, besoin);
               ArrayList<WorkLoad> workloads = WorkLoad.getAllWorkloadBesoin(conn, besoin);
               req.setAttribute("besoin", besoin);
               req.setAttribute("tasks", tasks);
               req.setAttribute("workloads", workloads);
               
               conn.close();
          } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
               exe.printStackTrace();
          }
          RequestDispatcher dispat = req.getRequestDispatcher("./template.jsp");
          dispat.forward(req, res);
     }

     protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          try {
            

        } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
         
    }
}