/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.besoin;

import framework.database.utilitaire.GConnection;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.annonce.Annonce;
import model.gestionBesoin.Besoin;
import model.gestionBesoin.Task;
import model.gestionBesoin.VDiplomeWantedProfile;
import model.gestionBesoin.VExperienceWantedProfile;
import model.gestionBesoin.WorkLoad;
import model.gestionProfile.WantedProfile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "AnnonceExportPDFServlet", urlPatterns = {"/annonce-export"})
public class AnnonceExportPDFServlet extends HttpServlet {

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
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=annonce.pdf");
       
        try {
            Connection conn = GConnection.getSimpleConnection();
            
            Integer idBesoin = Integer.valueOf(request.getParameter("idBesoin"));
            Besoin besoin = Besoin.getById(conn, idBesoin);
            besoin.validate(conn);
            
            
            // Bien remplir ces données et tout doit aller automatiquement
            String logoPath = "/assets/images/entreprise_logo.png";
            String societyName = "HUILE DE BONGOLAVA";
            
            List<Task> tasks = Task.getAllTaskBesoin(conn, besoin);
       
            List<WorkLoad> workLoads = WorkLoad.getAllWorkloadBesoin(conn, besoin);
        
            String dateBesoin = String.valueOf(besoin.getCreationDate());
            String serviceName = besoin.getService().getService();
            String link = "http://www.huile-de-bongolava.mg/recrutement/postule";
            String societyContact = "034 21 561 26";
            String nomAnnonce = dateBesoin + "_" + serviceName + "_" + "annonce.png";
            
            Annonce annonce = new Annonce(besoin, besoin.getService(), nomAnnonce, Date.valueOf(dateBesoin), 1);
            annonce.create(conn);
            
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    PDFRealisationUtil outil = new PDFRealisationUtil();

                    // Ajout du logo dans la page
                    PDImageXObject logo = PDImageXObject.createFromFile(getServletContext().getRealPath("/") + logoPath, document);
                    contentStream.drawImage(logo, 50, 720, 80, 80);

                    // Annonce de recrutement
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    outil.writeText(contentStream, 65, 690, societyName + " recrute !");

                    // Description du besoin
                    int dynamicY = 660;     // pour que la hauteur s'adapte en fonction du nombres de ligne
                    int lineHeight = 20;

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    int line = outil.writeMultilineText(contentStream, 65, dynamicY, besoin.getDescription(), lineHeight, 70);
                    dynamicY -= lineHeight * (line + 1);

                    contentStream.setLineWidth(1);
                    contentStream.setStrokingColor(0, 0, 0);
                    contentStream.moveTo(65, dynamicY);
                    contentStream.lineTo(500, dynamicY);
                    contentStream.stroke();
                    dynamicY -= lineHeight;
                    dynamicY -= lineHeight;

                    // Liste des taches a faire
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 65, dynamicY, "Vos missions : ");
                    dynamicY -= lineHeight;

                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    for (Task task : tasks) {
                        outil.writeText(contentStream, 65, dynamicY, "-  " + task.getTask());
                        dynamicY -= lineHeight;
                    }

                    // Liste des profil cherché
                    dynamicY -= lineHeight;
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    outil.writeText(contentStream, 65, dynamicY, "Intégrer notre équipe en étant :");
                    dynamicY -= lineHeight;     // Saut à la ligne

                    for (WorkLoad workLoad : workLoads) {
                        contentStream.setFont(PDType1Font.HELVETICA, 12);
                        outil.writeText(contentStream, 65, dynamicY, "-  " + workLoad.getWantedProfile().getPoste() + " : ");
                        dynamicY -= lineHeight;

                        contentStream.setFont(PDType1Font.HELVETICA, 10);
                        // Info a propos du diplome
                        ArrayList<VDiplomeWantedProfile> vdwp = VDiplomeWantedProfile.getDiplomeWantedProfile(conn, workLoad.getWantedProfile());
                        for(int i = 0; i < vdwp.size(); i++) {
                            outil.writeText(contentStream, 75, dynamicY, "-  Titulaire d'un " + vdwp.get(i).getDiplome().getDiplome());
                            dynamicY -= lineHeight;
                        }

                        // Info a propos de l'éxpérience
                        ArrayList<VExperienceWantedProfile> vewp = VExperienceWantedProfile.getExperienceWantedProfile(conn, workLoad.getWantedProfile());
                        for(int i = 0; i < vewp.size(); i++) {
                            outil.writeText(contentStream, 75, dynamicY, "-  Ayant plus de " + vewp.get(i).getExperience().getExperience());
                            dynamicY -= lineHeight;
                            dynamicY -= lineHeight;
                        }     
                    }

                // Conclusion text
                dynamicY -= lineHeight;
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                String conclusion = "Veuillez postuler depuis notre site web, tout en envoyant les dossier justificative et un photo de vous en pièce jointe, voilà le lien";
                line = outil.writeMultilineText(contentStream, 65, dynamicY, conclusion, lineHeight, 70);
                dynamicY -= (line * lineHeight);

                // Link text
                dynamicY -= lineHeight;
                outil.writeText(contentStream, 65, dynamicY, link);
                dynamicY -= lineHeight;

                // Renseignements
                dynamicY -= lineHeight;
                String renseignement = "Pour tout renseignement, conntactez nous : " + societyContact;
                outil.writeText(contentStream, 65, dynamicY, renseignement);

                
            }
            
            // Exportation en image
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImage(0);
            ImageIO.write(image, "PNG", new File(getServletContext().getRealPath("/annonces/" + dateBesoin + "_" + serviceName + "_" + "annonce.png")));

            // Exportation en pdf
            document.save(getServletContext().getRealPath("/annonces/" + dateBesoin + "_" + serviceName + "_" + "annonce.pdf"));
            
            // Affichage a l'écran
            document.save(response.getOutputStream());
            
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }   
            
        } catch(Exception e) {
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
