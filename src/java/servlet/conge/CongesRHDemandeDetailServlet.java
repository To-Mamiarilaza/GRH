    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.conge;

import com.mysql.cj.xdevapi.DbDoc;
import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import model.conge.Conge;
import model.conge.service.CongeManager;
import model.conge.CongePersonnel;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "CongesDemandeDetailServlet", urlPatterns = {"/CongesRHDemandeDetail"})
public class CongesRHDemandeDetailServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Connection connection = GConnection.getSimpleConnection();
            int idConge = Integer.valueOf(request.getParameter("idConge"));
            Conge conge = CongeManager.getCongeById(idConge, connection);
            CongePersonnel congePersonnel = CongeManager.getCongePersonnelInfo(conge.getPersonnel().getIdPersonnel(), connection);
            
            conge.getRemarqueChef();
            
            request.setAttribute("conge", conge);
            request.setAttribute("congePersonnel", congePersonnel);
            
            List<String> css = new ArrayList<>();
            css.add("./assets/css/conges/conges-personnel.css");
            
            List<String> js = new ArrayList<>();
            
            request.setAttribute("title", "Gestion Ressource Humaine");
            request.setAttribute("contentPage", "./pages/conges/congesRHDemandeDetail.jsp");
            request.setAttribute("css", css);
            request.setAttribute("js", js);
            
            RequestDispatcher dispatch = request.getRequestDispatcher("./template.jsp");
            dispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
