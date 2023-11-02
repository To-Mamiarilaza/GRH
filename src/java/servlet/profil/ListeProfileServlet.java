/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.profil;

import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.gestionProfile.BestCritere;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author Fy Botas
 */
@WebServlet(name = "ListeProfileServlet", urlPatterns = {"/ListeProfileServlet"})
public class ListeProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les indices des profils recherchés
        WantedProfile wp = new WantedProfile();
        HttpSession session = request.getSession();
        session.setAttribute("wantedprofile", wp);
        try {
            List<Integer> lsIndice = wp.getIdWantedProfile(null);
            BestCritere bc = new BestCritere();
            List<BestCritere> listep = bc.getListeProfile(lsIndice, null);

            response.getWriter().println(listep.size());

            response.setContentType("application/json");
                Gson gson = new Gson();
                String jsonData = gson.toJson(listep);
                response.getWriter().write(jsonData);
                
                response.getWriter().println(jsonData);
//            RequestDispatcher req = request.getRequestDispatcher("./pages/besoin/besoin_insertion.jsp");
//            req.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ListeProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
