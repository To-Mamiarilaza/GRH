/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.util;

/**
 *
 * @author To Mamiarilaza
 */
public class NombresEnLettres {

    private static final String[] unite = {"", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
        "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
    private static final String[] dizaine = {"", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante", "quatre-vingt", "quatre-vingt"};

    public static String nombreEnLettres(int nombre) {
        if (nombre == 0) {
            return "zéro";
        }
        if (nombre < 0) {
            return "moins " + nombreEnLettres(-nombre);
        }

        String resultat = "";
        if (nombre >= 1000000) {
            resultat += nombreEnLettres(nombre / 1000000) + " million";
            if (nombre % 1000000 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 1000000;
        }

        if (nombre >= 1000) {
            resultat += nombreEnLettres(nombre / 1000) + " mille";
            if (nombre % 1000 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 1000;
        }

        if (nombre >= 100) {
            resultat += unite[nombre / 100] + " cent";
            if (nombre % 100 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 100;
        }

        if (nombre >= 20) {
            resultat += dizaine[nombre / 10];
            if (nombre % 10 != 0) {
                resultat += "-" + unite[nombre % 10];
            }
        } else {
            resultat += unite[nombre];
        }

        return resultat;
    }

    public static void main(String[] args) {
        int nombre = 12300522;
        System.out.println(nombreEnLettres(nombre));
    }
}
