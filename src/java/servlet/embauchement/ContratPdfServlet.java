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
import model.candidature.PersonnalInformation;
import model.embauchement.Contrat;
import model.employe.Employe;
import model.requis.User;
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
@WebServlet(name = "ContratPdfServlet", urlPatterns = {"/contratPdf"})
public class ContratPdfServlet extends HttpServlet {

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
            out.println("<title>Servlet ContratPdfServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContratPdfServlet at " + request.getContextPath() + "</h1>");
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
        try {
            if (request.getParameter("superieur") != null && request.getParameter("periode_essai") != null && request.getParameter("is_cnaps") != null && request.getParameter("is_sanitaire") != null) {
                Integer periodeEssai = Integer.valueOf(request.getParameter("periode_essai"));
                Integer isCnaps = Integer.valueOf(request.getParameter("is_cnaps"));
                Integer isSanitaire = Integer.valueOf(request.getParameter("is_sanitaire"));
                HttpSession session = request.getSession();
                Contrat contrat = (Contrat) session.getAttribute("contrat");
                System.out.println("id contrat : "+contrat.getIdContrat());
                contrat.setIsCnaps(isCnaps);
                contrat.setIsSanitaire(isSanitaire);
                contrat.setPeriodeEssai(periodeEssai);
                Employe superieur = Employe.getById(Integer.valueOf(request.getParameter("superieur")));
                contrat.setSuperieur(superieur);
                System.out.println("Contrat : "+contrat);
                // Les missions du personne
                Candidature candidature = contrat.getCandidature();
                
                // Bien remplir ces données et tout doit aller automatiquement
                String logoPath = "/assets/images/entreprise_logo.png";
                String societyName = "HUILE DE BONGOLAVA";
                String societyAdresse = "Antananarivo";
                String title = "LETTRE D' ENGAGEMENT";
                String objet = "Engagement";

                String dateBesoin = DateManager.getDateActuel();
                String serviceName = "Resources humaines";
                String link = "http://www.huile-de-bongolava.mg/recrutement/postule";
                String societyContact = "034 21 561 26";
                String nomAnnonce = dateBesoin + "_" + serviceName + "_" + "annonce.png";

                String nomRecrute = contrat.getCandidature().getPersonnalInformation().getName() + " " + contrat.getCandidature().getPersonnalInformation().getFirstName();
                String adresseRecrute = contrat.getCandidature().getPersonnalInformation().getAdresse().getAdresse();

                // Pour avoir l'identite du responsable
                User user = (User) request.getSession().getAttribute("user");
                Employe employeRH = Employe.getById(user.getIdEmploye());
                PersonnalInformation infoPerson = employeRH.getContrat().getCandidature().getPersonnalInformation();
                String nomEmployeur = infoPerson.getFirstName() + " " + infoPerson.getName();
                String adresseEmployeur = infoPerson.getAdresse().getAdresse();

                String startDate = contrat.getStartDate().toString();
                String poste = contrat.getCandidature().getWantedProfile().getPoste();
                String duration = contrat.getPeriodeEssai().toString();
                String salaireMois = contrat.getSalary().toString();
                Double salaireSem = contrat.getSalary() / 4;
                String salaireSemaine = salaireSem.toString();
                String villeContrat = contrat.getProvince().getName();
                String dateContrat = DateManager.getDateActuel();
                String monSuperieur = superieur.getContrat().getCandidature().getPersonnalInformation().getName() + " " + superieur.getContrat().getCandidature().getPersonnalInformation().getFirstName();

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

                    Connection conn = GConnection.getSimpleConnection();
                    int lastContrat = Contrat.getLastContratId(conn);
                    int idNewContrat = 0;
                    if (lastContrat == 0) {
                        idNewContrat = 1;
                    } else {
                        idNewContrat = lastContrat + 1;
                    }
                    conn.close();
                    
                    // Pour le persistance de données
                    String persistanceDirectory = getServletContext().getRealPath("").replace("build\\web\\", "web\\");
                    
                    // Exportation en image
                    PDFRenderer pdfRenderer = new PDFRenderer(document);
                    BufferedImage image = pdfRenderer.renderImage(0);
                    ImageIO.write(image, "PNG", new File(getServletContext().getRealPath("/contratEssais/CONT" + dateContrat + "00" + idNewContrat + ".png")));
                    ImageIO.write(image, "PNG", new File(persistanceDirectory + "\\contratEssais\\CONT" + dateContrat + "00" + idNewContrat + ".png"));

                    // Exportation en pdf
                    document.save(getServletContext().getRealPath("/contratEssais/CONT" + dateContrat + "00" + idNewContrat + ".pdf"));
                    document.save(persistanceDirectory + "\\contratEssais\\CONT" + dateContrat + "00" + idNewContrat + ".pdf");
                    
                    //Suppression des sessions
                    session.removeAttribute("employe");
                    session.removeAttribute("contrat");
                    session.removeAttribute("candidat");
                    
                    //Insertion dans base de donnee
                    contrat.setPath("CONT" + dateContrat + "00" + idNewContrat);
                    contrat.save(null);

                    // Affichage a l'écran
                    document.save(response.getOutputStream());

                    //conn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                throw new Exception("Verifier votre donnee");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Error", e.getMessage());
            RequestDispatcher dispat = request.getRequestDispatcher("./pages/embauchement/contrat_travail_insertion.jsp");
            dispat.forward(request, response);
        }
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
