/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.quiz;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.requis.Service;

/**
 *
 * @author To Mamiarilaza
 */
public class Question {
/// field
    int idQuestion;
    String question;
    int score;
    List<Answer> answers;
    
    private int tempAnswerID;
    
/// getter and setter

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
/// constructor

    public Question(int idQuestion, String question, int score, List<Answer> answers) throws Exception {
        this.idQuestion = idQuestion;
        this.question = question;
        this.score = score;
        this.answers = answers;
        this.tempAnswerID = getMaxAnswerId();
    }
    
/// methods
    
    // Enregistrer tous les réponses du questiono
    public void saveAllAnswer(Connection connection) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            for (Answer answer : answers) {
                String query = "INSERT INTO answer (id_question, answer, value) VALUES (?, ?, ?)";
                query = String.format(query, this.getIdQuestion(), answer.getAnswer(), answer.getValue());
                statement = connection.prepareStatement(query);
                statement.setInt(1, this.getIdQuestion());
                statement.setString(2, answer.getAnswer());
                statement.setInt(3, answer.getValue());
                
                statement.executeUpdate();
            }
            
            statement.close();
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            throw e;
        }
    }
    
    // Effacer un réponse
    public void deleteAnswer(int idAnswer) {
        for (int i = 0; i < getAnswers().size(); i++) {
            if (getAnswers().get(i).getIdAnswer() == idAnswer) {
                getAnswers().remove(i);
            }
        }
    }
    
    // find the maximum possible ID
    // Prends l'ID maximum du question pour eviter les conflicts
    public int getMaxAnswerId() throws Exception {
        int maxID = 0;
        String query = "SELECT MAX(id_answer) + 1 as next_max FROM answer";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                maxID = resultset.getInt("next_max");
            }

            resultset.close();
            statement.close();
            connection.close();

            return maxID;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close(    );
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            throw e;
        }
    }
    
    // find the appropriate answer
    public Answer findAnswer(int idAnswer) throws Exception {
        for (Answer answer : answers) {
            if (answer.getIdAnswer() == idAnswer) {
                return answer;
            }
        }
        throw new Exception("Aucun réponse de ce type !");
    }
    
    // add an answer to the list of answer
    public Answer addAnswer(String answer, String value) {
        Answer newAnswer = new Answer(this.tempAnswerID, answer, Integer.valueOf(value));
        getAnswers().add(newAnswer);
        this.tempAnswerID++;
        return newAnswer;
    }
    
    // Avoir tous les questions
    public static List<Question> getAllQuestion(int idQuiz, Connection connection) throws Exception {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question WHERE id_quiz = %d";
        query = String.format(query, idQuiz);
        
        Statement statement = null;
        ResultSet resultset = null;
        
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);
            
            while(resultset.next()) {
                int idQuestion = resultset.getInt("id_question");
                String questionDeclaration = resultset.getString("question");
                int score = resultset.getInt("score");
                
                Question question = new Question(idQuestion, questionDeclaration, score, null);
                question.setAnswers(Answer.getAllAnswer(idQuestion, connection));
                
                questions.add(question);
            }
            
            resultset.close();
            statement.close();
            
            return questions;
        } catch (Exception e) {
            if (resultset != null) resultset.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            throw e;
        }
    }
}
