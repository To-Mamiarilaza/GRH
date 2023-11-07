/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.entretient;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "FiltreEntretientServlet", urlPatterns = {"/FiltreEntretientServlet"})

public class FiltreEntretientServlet extends HttpServlet {

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
        Date d = Date.valueOf(request.getParameter("date"));
        int idWp = Integer.valueOf(request.getParameter("poste"));
        Candidature c = new Candidature();
        int status = Integer.valueOf(request.getParameter("status"));
        if (request.getParameter("refuser") != null) {
            try {
                List<Candidature> listeEmbauchedCandidat = c.getRefusedCandidat(idWp, d, null);
                request.setAttribute("listeEmbauchedCandidat", listeEmbauchedCandidat);
                List<Candidature> listePostulate = c.getCandidatState(3, null);
                List<Candidature> listeEntretenue = c.getCandidatState(4, null);
                List<WantedProfile> listeWp = new WantedProfile().getAll(null);

                request.setAttribute("listeWp", listeWp);

                request.setAttribute("listePostulate", listePostulate);
                request.setAttribute("listeEntretenue", listeEntretenue);

                List<String> css = new ArrayList<>();
                List<String> js = new ArrayList<>();

                request.setAttribute("title", "Programmes des entretiens");
                request.setAttribute("contentPage", "./pages/entretient/programme_entretient.jsp");
                request.setAttribute("css", css);
                request.setAttribute("js", js);

                RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
                dispatch.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            switch (status) {
                case 3:
                    try {
                    List<Candidature> listePostulate = c.filtreDatePoste(idWp, d, null);
                    request.setAttribute("listePostulate", listePostulate);
                    List<Candidature> listeEmbauchedCandidat = c.getResultCandidature(null);
                    List<Candidature> listeEntretenu = c.getCandidatState(3, null);

                    List<WantedProfile> listeWp = new WantedProfile().getAll(null);

                    request.setAttribute("listeWp", listeWp);

                    request.setAttribute("listeEntretenu", listeEntretenu);
                    request.setAttribute("listeEmbauchedCandidat", listeEmbauchedCandidat);

                    List<String> css = new ArrayList<>();
                    List<String> js = new ArrayList<>();

                    request.setAttribute("title", "Programmes des entretiens");
                    request.setAttribute("contentPage", "./pages/entretient/programme_entretient.jsp");
                    request.setAttribute("css", css);
                    request.setAttribute("js", js);

                    RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
                    dispatch.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
                case 4:
                    try {
                    List<Candidature> listeEntretenu = c.filtreDatePoste(idWp, d, null);
                    request.setAttribute("listeEntretenu", listeEntretenu);
                    List<Candidature> listeEmbauchedCandidat = c.getResultCandidature(null);
                    List<Candidature> listePostulate = c.getCandidatState(4, null);
                    List<WantedProfile> listeWp = new WantedProfile().getAll(null);

                    request.setAttribute("listeWp", listeWp);

                    request.setAttribute("listePostulate", listePostulate);
                    request.setAttribute("listeEmbauchedCandidat", listeEmbauchedCandidat);
                    request.setAttribute("title", "Programmes des entretiens");
                    request.setAttribute("contentPage", "./pages/entretient/programme_entretient.jsp");

                    List<String> css = new ArrayList<>();
                    List<String> js = new ArrayList<>();

                    request.setAttribute("css", css);
                    request.setAttribute("js", js);

                    RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
                    dispatch.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
                case 5:
                    try {
                    List<Candidature> listeEmbauchedCandidat = c.filtreDatePoste(idWp, d, null);
                    request.setAttribute("listeEmbauchedCandidat", listeEmbauchedCandidat);
                    List<Candidature> listePostulate = c.getCandidatState(3, null);
                    List<Candidature> listeEntretenue = c.getCandidatState(4, null);
                    List<WantedProfile> listeWp = new WantedProfile().getAll(null);

                    request.setAttribute("listeWp", listeWp);

                    request.setAttribute("listePostulate", listePostulate);
                    request.setAttribute("listeEntretenue", listeEntretenue);

                    List<String> css = new ArrayList<>();
                    List<String> js = new ArrayList<>();

                    request.setAttribute("title", "Programmes des entretiens");
                    request.setAttribute("contentPage", "./pages/entretient/programme_entretient.jsp");
                    request.setAttribute("css", css);
                    request.setAttribute("js", js);

                    RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
                    dispatch.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;

                default:
                    break;

            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
