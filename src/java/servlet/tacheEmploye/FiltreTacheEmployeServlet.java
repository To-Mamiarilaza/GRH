/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.tacheEmploye;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.todo_list.EmployeTask;

/**
 *
 * @author Fy Botas
 */
public class FiltreTacheEmployeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idEmp = (int) session.getAttribute("idEmp");
        if (request.getParameter("delete") == null) {
            try {
                int idUnderTask = Integer.valueOf(request.getParameter("underTask"));
                int etat = Integer.valueOf(request.getParameter("etat"));

                EmployeTask empTask = new EmployeTask();
                List<EmployeTask> listeTaches = empTask.getTaskByIdEmploye(idEmp);
                List<EmployeTask> listeEmployeTask = empTask.FilterTask(idUnderTask, etat);

                request.setAttribute("ListTaskEmploye", listeEmployeTask);
                request.setAttribute("listeTaches", listeTaches);

                RequestDispatcher dispatch = request.getRequestDispatcher("./pages/tache/employeTache.jsp");
                dispatch.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else{
            try {
                Connection conn = GConnection.getSimpleConnection();
                EmployeTask empTask = new EmployeTask();
                int idUnderTask = Integer.valueOf(request.getParameter("delete"));
                empTask.deleteUnderTask(idUnderTask, conn);
                
                response.sendRedirect("./ListeEmployeTache?idEmp=" + idEmp);
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = GConnection.getSimpleConnection();
            HttpSession session = request.getSession();
            int idEmp = (int) session.getAttribute("idEmp");
            int etat = Integer.valueOf(request.getParameter("etat"));
            int idUnderTask = Integer.valueOf(request.getParameter("idUnderTask"));
            EmployeTask empTask = new EmployeTask();
            empTask.updateUnderTaskState(idUnderTask, etat, con);

            response.sendRedirect("./ListeEmployeTache?idEmp=" + idEmp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
