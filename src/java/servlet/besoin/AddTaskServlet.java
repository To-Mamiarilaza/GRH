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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;

@WebServlet(name = "AddTaskServlet", urlPatterns = {"/addTaskServlet"})
public class AddTaskServlet extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
          res.setContentType("text/plain");
          PrintWriter out = res.getWriter();
          
          try {
                String itemToRemove = req.getParameter("itemToRemove");
                Task task = new Task(itemToRemove, 1);
                HttpSession session = req.getSession();
                Besoin besoin = (Besoin)session.getAttribute("besoin");
                besoin.delTask(task);
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
              String taskValue = req.getParameter("task");
              HttpSession session = req.getSession();
              Besoin besoin = (Besoin)session.getAttribute("besoin");
              Task task = new Task(taskValue, 1);
              besoin.addTask(task);
               
              out.println(taskValue);
          } catch (Exception exe) {
               req.setAttribute("erreur", exe.getMessage());
          }
          //RequestDispatcher dispat = req.getRequestDispatcher("pages/besoin/besoin_insertion2.jsp");
          //dispat.forward(req, res);
    }
}