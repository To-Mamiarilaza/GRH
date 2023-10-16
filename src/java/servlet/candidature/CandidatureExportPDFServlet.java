/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.candidature;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.candidature.Career;
import model.candidature.Formation;
import model.candidature.FormationPath;
import model.candidature.ProfessionalCareer;
import model.gestionBesoin.Task;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;
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
@WebServlet(name = "CandidatureExportPDFServlet", urlPatterns = {"/candidature-export"})
public class CandidatureExportPDFServlet extends HttpServlet {

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
        response.setHeader("Content-Disposition", "inline; filename=candidature.pdf");

        // Voici les champs requis pour la réalisation du pdf du CV
        String depositDate = "2023-01-23";
        String nomService = "Informatique";

        String nom = "Couturier";
        String prenom = "Jean";
        String dateNaissance = "10 Aout 1974";
        String adresse = "Avenue Apaly RAFRINGA";
        String telephone = "034 10 425 65";
        String email = "mamiarilaza.to@gmail.com";
        String sexe = "Homme";

        String presentation = "Un étudiant motivé, j'aimerai bien integrer votre societe pour pouvoir ameliorer mes compétences, je suis heureux de pouvoir être avec vous";

        ProfessionalCareer professionalCareer = new ProfessionalCareer();
        professionalCareer.setExperience(new Experience("3 ans d'experience", 1));

        List<String> tasks = new ArrayList<>();
        tasks.add("Chargée de relation avec les clients");
        tasks.add("Responsable de l'accomplissement des projets");

        List<Career> careers = new ArrayList<>();
//        careers.add(new Career(LocalDate.of(2018, 7, 1), LocalDate.of(2020, 5, 1), "Novity Madagascar", "Chef de projet Informatique", tasks));
//        careers.add(new Career(LocalDate.of(2018, 7, 1), LocalDate.of(2020, 5, 1), "Novity Madagascar", "Chef de projet Informatique", tasks));
        professionalCareer.setCareers(careers);

        FormationPath formationPath = new FormationPath();
        formationPath.setDiplome(new Diplome("Docteur en Informatique", 1));
        List<Formation> formations = new ArrayList<>();
        formations.add(new Formation(2021, "Master MBDS", "University Lis"));
        formations.add(new Formation(2019, "License en Informatique", "IT University"));
        formationPath.setFormations(formations);

        List<String> loisirs = new ArrayList<>();
        loisirs.add("Jouer au basket ball");
        loisirs.add("Lire des livres sur l' histoire");

        String pretentionSalaire = "2 000 000";

        // Réalisation du pdf
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // parametre util et globale
                int lineHeight = 16;
                int dynamicY = 800;

                // Nom et prenom
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, prenom.toUpperCase() + " " + nom.toUpperCase());
                dynamicY -= lineHeight * 2.5;

                // Entete
                contentStream.setFont(PDType1Font.HELVETICA, 11);
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "ADRESSE : " + adresse);
                dynamicY -= lineHeight * 1;
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "EMAIL : " + email);
                dynamicY -= lineHeight * 1;
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "TELEPHONE : " + telephone);
                dynamicY -= lineHeight * 1;
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "DATE DE NAISSANCE : " + dateNaissance);
                dynamicY -= lineHeight * 1;
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "SEXE : " + sexe);
                dynamicY -= lineHeight * 2;

                // Présentation du profile
                contentStream.setFont(PDType1Font.HELVETICA, 11);
                int line = PDFRealisationUtil.writeMultilineText(contentStream, 50, dynamicY, presentation, lineHeight, 70);
                dynamicY -= lineHeight * (line + 1);

                // Parcours professionel
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Parcours professionels");
                dynamicY -= (int) (lineHeight / 2);

                contentStream.setLineWidth(1);
                contentStream.setStrokingColor(0, 0, 0);
                contentStream.moveTo(50, dynamicY);
                contentStream.lineTo(500, dynamicY);
                contentStream.stroke();
                dynamicY -= lineHeight * 1.5;

                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Ayant en ce moment :    " + professionalCareer.getExperience().getExperience());
                dynamicY -= lineHeight * 1.5;

                for (Career career : professionalCareer.getCareers()) {
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
//                    String startDate = career.getStartDate().getMonth().name() + " " + career.getStartDate().getYear();
//                    String endDate = career.getEndDate().getMonth().name() + " " + career.getEndDate().getYear();
//                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, startDate + "  -  " + endDate);
                    dynamicY -= lineHeight * 1;

                    contentStream.setFont(PDType1Font.HELVETICA, 11);
                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, career.getSociety());
                    dynamicY -= lineHeight * 1;

                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, career.getPoste());
                    dynamicY -= lineHeight * 1;

                    contentStream.setFont(PDType1Font.HELVETICA, 9);
                    for (String task : career.getTasks()) {
                        PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "-  " + task);
                        dynamicY -= lineHeight * 1;
                    }
                    dynamicY -= lineHeight * 1;
                }
                dynamicY -= lineHeight * 1;

                // Formation professionel
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Formations professionels");
                dynamicY -= (int) (lineHeight / 2);

                contentStream.setLineWidth(1);
                contentStream.setStrokingColor(0, 0, 0);
                contentStream.moveTo(50, dynamicY);
                contentStream.lineTo(500, dynamicY);
                contentStream.stroke();
                dynamicY -= lineHeight * 1.5;

                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Titulaire d'un diplome:    " + formationPath.getDiplome().getDiplome());
                dynamicY -= lineHeight * 1.5;

                for (Formation formation : formationPath.getFormations()) {
                    contentStream.setFont(PDType1Font.HELVETICA, 11);
                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, String.valueOf(formation.getYear()));
                    dynamicY -= lineHeight * 1;

                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, formation.getDiplome());
                    dynamicY -= lineHeight * 1;

                    contentStream.setFont(PDType1Font.HELVETICA, 11);
                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, formation.getSchool());
                    dynamicY -= lineHeight * 2;
                }

                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 11);
                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Je me prétends être à la valeur de  :  " + pretentionSalaire + " AR");
                dynamicY -= lineHeight * 2;

                PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "Mes centres d'interêts :");
                dynamicY -= lineHeight * 1;
                contentStream.setFont(PDType1Font.HELVETICA, 11);
                for (String loisir : loisirs) {
                    PDFRealisationUtil.writeText(contentStream, 50, dynamicY, "-  " + loisir);
                    dynamicY -= lineHeight * 1;
                }
                
            }

            // Exportation en image
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImage(0);
            // 
            ImageIO.write(image, "PNG", new File(getServletContext().getRealPath("/candidatures/" + depositDate + "_" + prenom + "_" + nom + "_" + nomService + "_" + "candidature.png")));
            
            
            // Exportation en pdf
            document.save(getServletContext().getRealPath("/candidatures/" + depositDate + "_" + prenom + "_" + nom + "_" + nomService + "_" + "candidature.pdf"));
            
            // Affichage a l'écran
            document.save(response.getOutputStream());

        } catch (IOException e) {
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
