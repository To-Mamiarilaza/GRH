/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.tacheEmploye;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.todo_list.EmployeTask;

/**
 *
 * @author Fy Botas
 */
public class ListeTacheEmployeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEmp = Integer.valueOf(request.getParameter("idEmp"));
        HttpSession session = request.getSession();
        session.setAttribute("idEmp", idEmp);
        EmployeTask employeTask = new EmployeTask();
        try {
            List<EmployeTask> listeEmployeTask = employeTask.getTaskByIdEmploye(idEmp);
            List<EmployeTask> listeTaches = employeTask.getAll();
            request.setAttribute("ListTaskEmploye", listeEmployeTask);
            request.setAttribute("listeTaches", listeEmployeTask);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("./pages/tache/employeTache.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
