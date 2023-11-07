/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.entretient;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.candidature.Candidature;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "DecisionEntretientServlet", urlPatterns = {"/DecisionEntretientServlet"})

public class DecisionEntretientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("idCandidature") != null) {
            try {
                int idCand = Integer.valueOf(request.getParameter("idCandidature"));
                Candidature can = new Candidature();
                can.updateStateEntretient(idCand, 6, null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                int idCand = Integer.valueOf(request.getParameter("idCandi"));
                Candidature can = new Candidature();
                can.updateStateEntretient(idCand, 0, null);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
