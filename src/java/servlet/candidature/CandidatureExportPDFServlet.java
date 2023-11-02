/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet.candidature;

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
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.candidature.Candidature;
import model.candidature.Career;
import model.candidature.Formation;
import model.candidature.FormationPath;
import model.candidature.ProfessionalCareer;
import model.gestionProfile.Diplome;
import model.gestionProfile.Experience;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import util.pdf.PDFRealisationUtil;

/**
 *
 * @author To Mamiarilaza
 */
@WebServlet(name = "CandidatureExportPDFServlet", urlPatterns = {"/CandidatureExportPDFServlet"})
public class CandidatureExportPDFServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=candidature.pdf");

        Connection con = GConnection.getSimpleConnection();
        int lastId = new Candidature().getLastId(con);
        System.out.println(lastId);
        Candidature can = new Candidature().getById(con, lastId);

        // Voici les champs requis pour la réalisation du pdf du CV

        String nom = can.getPersonnalInformation().getName();
        String prenom = can.getPersonnalInformation().getFirstName();
        Date dateNaissance = (Date) can.getPersonnalInformation().getBirthDate();
        String adresse = can.getPersonnalInformation().getAdresse().getAdresse();
        String telephone = can.getPersonnalInformation().getTelephone();
        String email = can.getPersonnalInformation().getEmail();
        String sexe = can.getPersonnalInformation().getSexe().getSexe();

        String presentation = can.getSelfProfile();

        ProfessionalCareer professionalCareer = new ProfessionalCareer();
        professionalCareer.setExperience(new Experience(can.getProfessionalCareer().getExperience().getExperience(), 1));

        for (int i = 0; i < can.getProfessionalCareer().getCareers().size(); i++) {
            List<String> tasks = can.getProfessionalCareer().getCareers().get(i).getTasks();

            List<Career> careers = new ArrayList<>();
            for (int j = 0; j < can.getProfessionalCareer().getCareers().size(); j++) {
                careers.add(new Career(can.getProfessionalCareer().getCareers().get(0).getStartDate(), can.getProfessionalCareer().getCareers().get(0).getEndDate(), can.getProfessionalCareer().getCareers().get(0).getSociety(), can.getProfessionalCareer().getCareers().get(0).getPoste(), tasks));
                professionalCareer.setCareers(can.getProfessionalCareer().getCareers());
            }
        }

        FormationPath formationPath = new FormationPath();
        formationPath.setDiplome(new Diplome(can.getFormationPath().getDiplome().getDiplome(), 1));
        List<Formation> formations = new ArrayList<>();
        for (int z = 0; z < can.getFormationPath().getFormations().size(); z++) {
            formations.add(new Formation(can.getFormationPath().getFormations().get(z).getYear(), can.getFormationPath().getFormations().get(z).getDiplome(), can.getFormationPath().getFormations().get(z).getSchool()));
            formationPath.setFormations(formations);
        }

        String[] loisirs = can.getInterestCenter().split("-");

        String pretentionSalaire = String.valueOf(can.getSalaryExpectation());

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
            ImageIO.write(image, "PNG", new File(getServletContext().getRealPath("./candidatures/_00" + can.getIdCandidature() + "_" + nom + "_" + prenom + " candidature.png")));

            // Exportation en pdf
            document.save(getServletContext().getRealPath("./candidatures/_00" + can.getIdCandidature() + "_" + nom + "_" + prenom + "_" + " candidature.pdf"));

            // Affichage a l'écran
            document.save(response.getOutputStream());

            response.sendRedirect("pages/candidature/finished_candidature.jsp");

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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CandidatureExportPDFServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CandidatureExportPDFServlet.class.getName()).log(Level.SEVERE, null, ex);
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
