/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.quiz;

/**
 *
 * @author To Mamiarilaza
 */
public class QuizType {
/// field
    int idQuizType;
    String quizType;
    
/// getter and setter

    public int getIdQuizType() {
        return idQuizType;
    }

    public void setIdQuizType(int idQuizType) {
        this.idQuizType = idQuizType;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }
    
/// constructor

    public QuizType(int idQuizType, String quizType) {
        this.idQuizType = idQuizType;
        this.quizType = quizType;
    }
    
}
