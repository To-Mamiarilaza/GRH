/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

/**
 *
 * @author To Mamiarilaza
 */
public class PDFRealisationUtil {
    // Les fonctions pouvant être utile à la réalisation d'un pdf sont stockés ici
    // Fonction pour diviser une long texte en liste pour les afficher par lignes
    public static List<String> toMultilinesText(String text, int limit) {
        String[] multiwords = text.split(" ");      // Séparation par espaces

        List<String> stringListes = new ArrayList<>();

        int carac = 0;
        String tempResult = "";

        for (String multiword : multiwords) {
            carac += multiword.length();
            if (carac > limit) {
                carac = 0;
                stringListes.add(tempResult);
                tempResult = "";
            } else {
                tempResult += " " + multiword;
            }
        }
        stringListes.add(tempResult);

        return stringListes;
    }
    
    // Pour afficher de long texte en auto à la ligne, limit c'est le nombre de caractère max pour une ligne
    public static int writeMultilineText(PDPageContentStream contentStream, int x, int y, String text, int lineHeight, int limit) throws IOException {
        List<String> multilines = toMultilinesText(text, limit);

        for (int i = 0; i < multilines.size(); i++) {
            writeText(contentStream, x, y - (lineHeight * i), multilines.get(i));
        }

        return multilines.size();
    }

    public static void writeText(PDPageContentStream contentStream, int x, int y, String text) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }
    
}
