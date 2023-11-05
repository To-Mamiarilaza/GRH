/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.embauchement;

import framework.database.utilitaire.GConnection;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.candidature.Candidature;
import model.embauchement.Contrat;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import util.pdf.DateManager;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author chalman
 */
@WebServlet(name = "VoirContratServlet", urlPatterns = {"/voirContrat"})
public class VoirContratServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VoirContratServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoirContratServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try {
            Connection conn = GConnection.getSimpleConnection();

            Integer idCandidature = Integer.valueOf(request.getParameter("idCandidat"));
            Candidature candidat = Candidature.getById(conn, idCandidature);
            Contrat contrat = Contrat.getByCandidat(idCandidature);

            Integer periodeEssai = contrat.getPeriodeEssai();

            // Bien remplir ces données et tout doit aller automatiquement
            String logoPath = "/assets/images/entreprise_logo.png";
            String societyName = "HUILE DE BONGOLAVA";
            String societyAdresse = "Antananarivo";
            String title = "LETTRE A ENGAGEMENT";
            String objet = "Engagement";

            String dateBesoin = DateManager.getDateActuel();
            String serviceName = candidat.getWantedProfile().getService().getService();
            String link = "http://www.huile-de-bongolava.mg/recrutement/postule";
            String societyContact = "034 21 561 26";

            String nomRecrute = contrat.getCandidature().getPersonnalInformation().getName() + " " + contrat.getCandidature().getPersonnalInformation().getFirstName();
            String adresseRecrute = contrat.getCandidature().getPersonnalInformation().getAdresse().getAdresse();

            String nomEmployeur = "INSSA Chalman";
            String adresseEmployeur = "Bevalala";

            String startDate = contrat.getStartDate().toString();
            String poste = contrat.getCandidature().getWantedProfile().getPoste();
            String duration = contrat.getPeriodeEssai().toString();
            String salaireMois = contrat.getSalary().toString();
            Double salaireSem = contrat.getSalary() / 4;
            String salaireSemaine = salaireSem.toString();
            String villeContrat = contrat.getProvince().getName();
            String dateContrat = contrat.getContratDate().toString();

            // Listes des missions  ( à refaire )
            List<String> missions = new ArrayList<>();
            missions.add("S'assurer de la bonne fonctionnement du serveur");
            missions.add("Bien organiser le service ressource humaine");
            missions.add("Travailler sur l'objectif annuel de l'entreprise");

            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    PDFRealisationUtil outil = new PDFRealisationUtil();

                    // Header du contrat
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 200, 790, title);

                    // Description du contenu
                    int dynamicY = 730;     // pour que la hauteur s'adapte en fonction du nombres de ligne
                    int lineHeight = 20;

                    // Information personnelle de l'employeur
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    int line = outil.writeMultilineText(contentStream, 65, dynamicY, "D'une part, le société denommé :", lineHeight, 70);
                    //dynamicY -= lineHeight * (line + 1);
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 68, dynamicY, "NOM : ");
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 110, dynamicY, societyName);
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 68, dynamicY, "ADRESSE : ");
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 140, dynamicY, societyAdresse);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    // Information personnelle du recrute
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    line = outil.writeMultilineText(contentStream, 65, dynamicY, "D'autre part, l'employe denommé :", lineHeight, 70);
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 68, dynamicY, "NOM : ");
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 110, dynamicY, nomRecrute);
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 68, dynamicY, "ADRESSE : ");
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 140, dynamicY, adresseRecrute);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Objet du contrat d'esai
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 65, dynamicY, "Objet : " + objet);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeMultilineText(contentStream, 65, dynamicY, "Je soussigne Monsieur " + nomEmployeur + ", je confirme votre engagement a travailler au sein de notre entreprise" + " a compte du " + startDate + " en qualite de " + poste + ".", lineHeight, 70);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeMultilineText(contentStream, 65, dynamicY, "A cette effet, il sera effectue une periode d'essai d'une duree de " + duration + " mois pendant laquelle vous serez chargé :", lineHeight, 70);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Liste des taches
                    dynamicY -= lineHeight;
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    for (String mission : missions) {
                        outil.writeText(contentStream, 65, dynamicY, "- " + mission);
                        dynamicY -= lineHeight;
                    }
                    dynamicY -= lineHeight;

                    //Concernant le salaire du salarie
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    line = outil.writeMultilineText(contentStream, 65, dynamicY, "La renumeration est fixe a " + salaireMois + " ariary par mois soit " + salaireSemaine + " ariary par semaine.", lineHeight, 70);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Le contrat
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    line = outil.writeMultilineText(contentStream, 65, dynamicY, "Si l'essai est concluant, nous procederons a la signature d'un contrat de travail etre rompu par l'un ou l'autre partie a tout moment.", lineHeight, 70);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    line = outil.writeMultilineText(contentStream, 65, dynamicY, "Tous litiges relatifs a l'execution du present contrat seront soumis a la competence des tribunaux.", lineHeight, 70);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Date et lieu du contrat
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 200, dynamicY, " Fait a " + villeContrat + ", le " + dateContrat);
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    //Signature des deux
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 65, dynamicY, " Signature de l'employeur");
                    outil.writeText(contentStream, 400, dynamicY, " Signature de l'employe");
                    dynamicY -= lineHeight;

                }

                conn.close();

                // Affichage a l'écran
                document.save(response.getOutputStream());

                //conn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Error", e.getMessage());
            RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/start_contrat_recrute.jsp");
            dispat.forward(request, response);
        }
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
