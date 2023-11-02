/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.entretient;

import com.google.gson.Gson;
import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.List;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;
import model.requis.Service;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "EntretientServlet", urlPatterns = {"/EntretientServlet"})

public class EntretientServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Candidature c = new Candidature();
        try {
            Connection con = GConnection.getSimpleConnection();
            List<Candidature> listePostulate = c.getCandidatState(1, con);
            List<Candidature> listeEntretenue = c.getCandidatState(2, con);
            List<Candidature> listeEmbauchedCandidat = c.getResultCandidature(con);

            List<Service> listeService = Service.getAll(con);
            List<WantedProfile> listeWp = new WantedProfile().getAll(con);

            request.setAttribute("listePostulate", listePostulate);
            request.setAttribute("listeEntretenue", listeEntretenue);
            request.setAttribute("listeEmbauchedCandidat", listeEmbauchedCandidat);
            request.setAttribute("listeService", listeService);
            request.setAttribute("listeWp", listeWp);

            RequestDispatcher req = request.getRequestDispatcher("./pages/entretient/programme_entretient.jsp");
            req.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("idCand") == null) {
            try {
                Connection con = GConnection.getSimpleConnection();
                int idCandidature = Integer.valueOf(request.getParameter("idCandidature"));
                System.out.println(idCandidature);
                Candidature can = Candidature.getById(con, idCandidature);
                request.setAttribute("candidature", can);
                response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(can);
                response.getWriter().write(jsonData);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                int idCand = Integer.valueOf(request.getParameter("idCand"));
                double note = Double.valueOf(request.getParameter("noteEntretient"));
                Candidature c = new Candidature();
                c.updateState(idCand, 2, note, null);// 2 pour dire que le candidat a deja terminer l'entretient
                response.getWriter().write("success");
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
