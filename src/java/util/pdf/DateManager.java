/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.pdf;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author chalman
 */
public class DateManager {
    public static String getDateActuel() throws Exception {
        Date dateActuel = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateFormated = format.format(dateActuel);
        
        return dateFormated;
    }
}
