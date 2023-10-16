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
public class Quiz {
/// field

    int idQuiz;
    Service service;
    String quizName;
    QuizType type;                  // 1 question et 2 réponse
    List<Question> questions;

    private int tempQuestionID;     // ID temporaire ajouter au nouveau question

/// getter and setter
    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public QuizType getType() {
        return type;
    }

    public void setType(QuizType type) {
        this.type = type;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

/// constructor
    public Quiz() throws Exception {
        setQuestions(new ArrayList<Question>());
        this.tempQuestionID = getMaxQuestionId(null) + 1;
    }

    public Quiz(int idQuiz, Service service, String quizName, QuizType type, List<Question> questions) {
        this.idQuiz = idQuiz;
        this.service = service;
        this.quizName = quizName;
        this.type = type;
        this.questions = questions;
    }

/// fonctions
    // Pour avoir l'id du quiz inséré
    public int getLastQuizId(Connection connection) throws Exception {
        int maxID = 0;
        String query = "SELECT MAX(id_quiz) as last_id FROM quiz";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                maxID = resultset.getInt("last_id");
            }

            resultset.close();
            statement.close();

            return maxID;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
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

    // Enregistre le quiz dans la base de données
    public void save(int type, Connection connection) throws Exception {
        String query = "INSERT INTO quiz (id_service, quiz_name, id_quiz_type, status) VALUES (?, ?, ?, ?)";

        boolean closeable = false;
        if (connection == null) {
            closeable = true;
            connection = GConnection.getSimpleConnection();
            connection.setAutoCommit(false);
        }

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, getService().getIdService());
            statement.setString(2, getQuizName());
            statement.setInt(3, type);
            statement.setInt(4, 1);

            statement.executeUpdate();

            int lastQuizId = getLastQuizId(connection);

            // Insertion des question
            for (Question question : getQuestions()) {
                query = "INSERT INTO question (id_quiz, question, score) VALUES (?, ?, ?)";
                query = String.format(query, lastQuizId, question.getQuestion(), question.getScore());
                statement = connection.prepareStatement(query);
                statement.setInt(1, lastQuizId);
                statement.setString(2, question.getQuestion());
                statement.setInt(3, question.getScore());

                statement.executeUpdate();

                int lastQuestionId = getMaxQuestionId(connection);
                question.setIdQuestion(lastQuestionId);

                question.saveAllAnswer(connection);
            }

            statement.close();

            if (closeable) {
                connection.commit();
                connection.close();
            }

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

    // Prends l'ID maximum du question pour eviter les conflicts
    public int getMaxQuestionId(Connection connection) throws Exception {
        boolean needClose = false;
        if (connection == null) {
            needClose = true;
            connection = GConnection.getSimpleConnection();
        }

        int maxID = 0;
        String query = "SELECT MAX(id_question) as next_max FROM question";

        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                maxID = resultset.getInt("next_max");
            }

            resultset.close();
            statement.close();

            if (needClose) {
                connection.close();
            }

            return maxID;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
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

    // delete answer
    public void deleteAnswer(String idQuestion, String idAnswer) throws Exception {
        checkChangeAnswerValidity(idQuestion, idAnswer, "0");

        Question question = findQuestion(Integer.valueOf(idQuestion));
        question.deleteAnswer(Integer.valueOf(idAnswer));
    }

    // Vérifie la validité des données entrer 
    public void checkChangeAnswerValidity(String idQuestion, String idAnswer, String newValue) throws Exception {
        if (idQuestion.trim().equals("") || idQuestion == null) {
            throw new Exception("L' ID du question cible ne doit pas être vide !");
        }

        if (idAnswer.trim().equals("") || idAnswer == null) {
            throw new Exception("L' ID du réponse cible ne doit pas être vide !");
        }

        if (newValue.trim().equals("") || newValue == null) {
            throw new Exception("La nouvelle valeur du réponse ne doit pas être vide !");
        }

        try {
            Integer.valueOf(idQuestion);
        } catch (Exception e) {
            throw new Exception("L'ID du question doit être un nombre");
        }

        try {
            Integer.valueOf(idAnswer);
        } catch (Exception e) {
            throw new Exception("L'ID du réponse doit être un nombre");
        }

        try {
            Integer.valueOf(newValue);
        } catch (Exception e) {
            throw new Exception("La nouvelle valeur du réponse doit être un nombre");
        }

    }

    // Changer la valeur d'une réponse
    public void changeAnswerValue(String idQuestion, String idAnswer, String newValue) throws Exception {
        checkChangeAnswerValidity(idQuestion, idAnswer, newValue);
        Question question = findQuestion(Integer.valueOf(idQuestion));
        Answer answer = question.findAnswer(Integer.valueOf(idAnswer));
        answer.changeValue(Integer.valueOf(newValue));
    }

    // Trouver le question 
    public Question findQuestion(int idQuestion) throws Exception {
        for (Question question : getQuestions()) {
            if (question.getIdQuestion() == idQuestion) {
                return question;
            }
        }
        throw new Exception("La question demandée n'éxiste pas !");
    }

    // Vérifie la validité des données entrer
    public void checkAddAnswerValidity(String answer, String value, String idQuestion) throws Exception {
        if (answer.trim().equals("")) {
            throw new Exception("La réponse entrer ne doit pas être vide");
        }

        if (value == null) {
            throw new Exception("La valeur état d'un réponse ne doit pas être null");
        }

        if (idQuestion.trim().equals("") || idQuestion == null) {
            throw new Exception("L' ID du question ne doit pas être vide");
        }

        try {
            Integer.valueOf(value);
        } catch (Exception e) {
            throw new Exception("La valeur d'une réponse doit être un nombre");
        }

        try {
            Integer.valueOf(idQuestion);
        } catch (Exception e) {
            throw new Exception("L' ID du question doit être un nombre");
        }

    }

    // Ajouter une réponse a un question
    public Answer addAnswer(String answerValue, String value, String idQuestion) throws Exception {
        checkAddAnswerValidity(answerValue, value, idQuestion);
        Question question = findQuestion(Integer.valueOf(idQuestion));
        Answer answer = question.addAnswer(answerValue, value);
        return answer;
    }

    // supprimer une question
    public void deleteQuestion(String idQuestion) throws Exception {
        if (idQuestion.trim().equals("")) {
            throw new Exception("L' ID du question ne doit pas être vide ou null !");
        }

        int idQuestionValue;
        try {
            idQuestionValue = Integer.valueOf(idQuestion);
        } catch (Exception e) {
            throw new Exception("La valeur de l'ID question doit être un nombre !");
        }

        for (int i = 0; i < getQuestions().size(); i++) {
            if (getQuestions().get(i).getIdQuestion() == idQuestionValue) {
                getQuestions().remove(i);
            }
        }
    }

    // ajouter une question
    public Question addQuestion(String question, String score) throws Exception {
        if (question.trim().equals("")) {
            throw new Exception("La nouvelle question ne doit pas être vide !");
        }
        if (score.trim().equals("")) {
            throw new Exception("Le score ne doit pas être vide !");
        }

        int scoreValue;
        try {
            scoreValue = Integer.valueOf(score);
        } catch (Exception e) {
            throw new Exception("La valeur du score doit être un nombre !");
        }

        Question newQuestion = new Question(tempQuestionID, question, scoreValue, new ArrayList<Answer>());

        getQuestions().add(newQuestion);
        this.tempQuestionID++;

        return newQuestion;
    }

    // Pour avoir les informations a propos d'une quiz
    public void getInformation() {
        System.out.println("Detail du quiz : " + getIdQuiz());
        System.out.println("Nom : " + getQuizName());
        System.out.println("Service : " + getService().getService());
        System.out.println("Quiz Type : " + getType().getQuizType());
        System.out.println("Les questions :");
        for (Question question : getQuestions()) {
            System.out.println("--> " + question.getIdQuestion() + " . " + question.getQuestion() + " " + question.getScore() + " points");
            for (Answer answer : question.getAnswers()) {
                System.out.println("    " + answer.getIdAnswer() + ". " + answer.getAnswer() + "   " + answer.getValue());
            }
        }
    }

    // Pour avoir une quiz par son ID
    public static Quiz getQuizById(int id) throws Exception {
        Quiz quiz = null;
        String query = "SELECT * FROM v_quiz_full_info WHERE id_quiz = %d";
        query = String.format(query, id);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                int idQuiz = resultset.getInt("id_quiz");
                String quizName = resultset.getString("quiz_name");
                int status = resultset.getInt("status");

                int id_quiz_type = resultset.getInt("id_quiz_type");
                String quiz_type = resultset.getString("quiz_type");
                QuizType quizType = new QuizType(id_quiz_type, quiz_type);

                int id_service = resultset.getInt("id_service");
                String serviceName = resultset.getString("service");
                String fonction = resultset.getString("fonction");
                int serviceStatus = resultset.getInt("service_status");
                Service service = new Service(id_service, serviceName, fonction, null, serviceStatus);

                quiz = new Quiz(idQuiz, service, quizName, quizType, null);

                quiz.setQuestions(Question.getAllQuestion(idQuiz, connection));
            }

            resultset.close();
            statement.close();
            connection.close();

            return quiz;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
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

    public static List<Quiz> getAllQuiz() throws Exception {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM v_quiz_full_info WHERE id_quiz_type = 1";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idQuiz = resultset.getInt("id_quiz");
                String quizName = resultset.getString("quiz_name");
                int status = resultset.getInt("status");

                int id_quiz_type = resultset.getInt("id_quiz_type");
                String quiz_type = resultset.getString("quiz_type");
                QuizType quizType = new QuizType(id_quiz_type, quiz_type);

                int idService = resultset.getInt("id_service");
                String serviceName = resultset.getString("service");
                String fonction = resultset.getString("fonction");
                int serviceStatus = resultset.getInt("service_status");
                Service service = new Service(idService, serviceName, fonction, null, serviceStatus);

                Quiz quiz = new Quiz(idQuiz, service, quizName, quizType, null);

                quiz.setQuestions(Question.getAllQuestion(idQuiz, connection));

                quizzes.add(quiz);
            }

            resultset.close();
            statement.close();
            connection.close();

            return quizzes;
        } catch (Exception e) {
            if (resultset != null) {
                resultset.close();
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

    public static void main(String[] args) throws Exception {
        Quiz quiz = new Quiz();
        Service service = new Service();
        service.setIdService(2);
        quiz.setService(service);
        quiz.setQuizName("Quiz de test");

        quiz.addQuestion("Q1", "1");
        quiz.addAnswer("A1", "0", "1");
        quiz.addAnswer("A11", "1", "1");

        quiz.addQuestion("Q2", "2");
        quiz.addAnswer("A2", "0", "2");
        quiz.addAnswer("A21", "0", "2");
        quiz.addAnswer("A23", "1", "2");

        quiz.changeAnswerValue("2", "2", "5");
        quiz.changeAnswerValue("2", "3", "50");

        quiz.getInformation();


    }
}
