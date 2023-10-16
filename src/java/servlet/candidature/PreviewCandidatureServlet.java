/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.candidature;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import model.candidature.Candidature;

/**
 *
 * @author Fy Botas
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "PreviewCandidatureServlet", urlPatterns = {"/PreviewCandidatureServlet"})
public class PreviewCandidatureServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PreviewCandidatureServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PreviewCandidatureServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Candidature can = (Candidature) session.getAttribute("candidature");
        request.setAttribute("candidature", can);
        RequestDispatcher req = request.getRequestDispatcher("/pages/candidature/candidature_preview.jsp");
        req.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePartDossier = request.getPart("dossier");
        Part filePartPhoto = request.getPart("image");

        String dossierName = filePartDossier.getSubmittedFileName();
        String photoName = filePartPhoto.getSubmittedFileName();

        HttpSession session = request.getSession();
        Candidature can = (Candidature) session.getAttribute("candidature");
        can.setPhoto(photoName);
        can.setDossier(dossierName);

        String baseUploadDirectory = "D:\\ITU\\L3\\Gestion_d'entreprise(MrTovo)\\RessourcesHumaines\\web\\uploads\\";

        String uniqueDossierUploadDirectory = baseUploadDirectory + "dossier" + File.separator + dossierName + File.separator;
        String uniquePhotoUploadDirectory = baseUploadDirectory + "photo" + File.separator + photoName + File.separator;

        filePartDossier.write(uniqueDossierUploadDirectory);
        filePartPhoto.write(uniquePhotoUploadDirectory);

        response.sendRedirect(request.getContextPath() + "/PreviewCandidatureServlet");
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
