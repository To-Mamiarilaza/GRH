/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author To Mamiarilaza
 */
public class Answer {
/// field
    int idAnswer;
    String answer;
    int value;
    
/// getter and setter

    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
/// constructor

    public Answer(int idAnswer, String answer, int value) {
        this.idAnswer = idAnswer;
        this.answer = answer;
        this.value = value;
    }
    
/// methods
    
    // Changer la valeur d'une réponse
    public void changeValue(int newValue) {
        setValue(newValue);
    }
    
    // Avoir les réponses a une question
    public static List<Answer> getAllAnswer(int idQuestion, Connection connection) throws Exception {
        List<Answer> answers = new ArrayList<>();
        String query = "SELECT * FROM answer WHERE id_question = %d";
        query = String.format(query, idQuestion);
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            
            while(resultset.next()) {
                int idAnswer = resultset.getInt("id_answer");
                String answerDeclaration = resultset.getString("answer");
                int value = resultset.getInt("value");
                
                Answer answer = new Answer(idAnswer, answerDeclaration, value);
                
                answers.add(answer);
            }
            
            resultset.close();
            statement.close();
            
            return answers;
        } catch (Exception e) {
            if (resultset != null) resultset.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            throw e;
        }
    }
}
