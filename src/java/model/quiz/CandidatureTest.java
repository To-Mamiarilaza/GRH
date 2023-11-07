/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.quiz;

import framework.database.utilitaire.GConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.candidature.Candidature;
import model.gestionProfile.WantedProfile;

/**
 *
 * @author To Mamiarilaza
 */
public class CandidatureTest {

    /// field
    int idCandidatureTest;
    Candidature candidature;
    WantedProfile wantedProfile;
    Quiz question;
    Quiz answer;
    int note;
    LocalDate quizDate;

    // getter and setter
    public int getIdCandidatureTest() {
        return idCandidatureTest;
    }

    public void setIdCandidatureTest(int idCandidatureTest) {
        this.idCandidatureTest = idCandidatureTest;
    }

    public Candidature getCandidature() {
        return candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public WantedProfile getWantedProfile() {
        return wantedProfile;
    }

    public void setWantedProfile(WantedProfile wantedProfile) {
        this.wantedProfile = wantedProfile;
    }

    public Quiz getQuestion() {
        return question;
    }

    public void setQuestion(Quiz question) {
        this.question = question;
    }

    public Quiz getAnswer() {
        return answer;
    }

    public void setAnswer(Quiz answer) {
        this.answer = answer;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public LocalDate getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(LocalDate quizDate) {
        this.quizDate = quizDate;
    }

    // constructor
    public CandidatureTest(int idCandidatureTest, Candidature candidature, WantedProfile wantedProfile, Quiz question, Quiz answer) {
        this.idCandidatureTest = idCandidatureTest;
        this.candidature = candidature;
        this.wantedProfile = wantedProfile;
        this.question = question;
        this.answer = answer;
    }

    public CandidatureTest(int idCandidatureTest, Candidature candidature, WantedProfile wantedProfile, Quiz question, Quiz answer, int note, LocalDate quizDate) {
        this.idCandidatureTest = idCandidatureTest;
        this.candidature = candidature;
        this.wantedProfile = wantedProfile;
        this.question = question;
        this.answer = answer;
        this.note = note;
        this.quizDate = quizDate;
    }

    /// methods
    // Pour avoir tous les candidatures test non traité  avec leurs detail
    public static List<CandidatureTest> getAllNewCandidatureTest() throws Exception {
        List<CandidatureTest> candidatureTestList = new ArrayList<>();
        String query = "SELECT * FROM candidature_test WHERE status = 1 ORDER BY note DESC";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            while (resultset.next()) {
                int idCandidatureTest = resultset.getInt("id_candidature_test");
                Candidature candidature = Candidature.getById(connection, resultset.getInt("id_candidature"));
                int note = resultset.getInt("note");
                Quiz answerQuiz = Quiz.getQuizById(resultset.getInt("id_quiz"));
                LocalDate quizDate = resultset.getDate("quiz_date").toLocalDate();

                CandidatureTest candidatureTest = new CandidatureTest(idCandidatureTest, candidature, candidature.getWantedProfile(), null, answerQuiz, note, quizDate);

                candidatureTestList.add(candidatureTest);
            }

            resultset.close();
            statement.close();
            connection.close();

            return candidatureTestList;
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

    // Pour avoir tous les candidatures test  avec leurs detail
    public static CandidatureTest getCandidatureTestById(int idCandidatureTest) throws Exception {
        CandidatureTest candidatureTest = null;
        String query = "SELECT * FROM candidature_test WHERE id_candidature_test = %d";
        query = String.format(query, idCandidatureTest);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            connection = GConnection.getSimpleConnection();
            statement = connection.createStatement();
            resultset = statement.executeQuery(query);

            if (resultset.next()) {
                Candidature candidature = Candidature.getById(connection, resultset.getInt("id_candidature"));
                int note = resultset.getInt("note");
                Quiz answerQuiz = Quiz.getQuizById(resultset.getInt("id_quiz"));
                LocalDate quizDate = resultset.getDate("quiz_date").toLocalDate();

                candidatureTest = new CandidatureTest(idCandidatureTest, candidature, candidature.getWantedProfile(), null, answerQuiz, note, quizDate);
            }

            resultset.close();
            statement.close();
            connection.close();

            return candidatureTest;
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

    public void saveTest(Connection connection, int idQuiz) throws Exception {
        // Save 
        // Status 1 : valide , 2 : traité , 0 : supprimé
        String query = "INSERT INTO candidature_test (id_candidature, note, id_quiz, quiz_date, status) VALUES (?, ?, ?, NOW(), 1)";

        PreparedStatement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, getCandidature().getIdCandidature());
            statement.setInt(2, getNote());
            statement.setInt(3, idQuiz);

            statement.executeUpdate();

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

    // sauvegarder la candidature test dans la base de données
    public void save() throws Exception {
        Connection connection = GConnection.getSimpleConnection();
        connection.setAutoCommit(false);

        getAnswer().save(2, connection);
        int idQuiz = getAnswer().getLastQuizId(connection);
        saveTest(connection, idQuiz);

        connection.commit();
        connection.close();
    }

    // Vérifie si les réponses sont les même
    public boolean hasSameAnswer(List<Answer> quizAnswer, List<Answer> candidatAnswer) {
        for (int i = 0; i < quizAnswer.size(); i++) {
            if (quizAnswer.get(i).getValue() != candidatAnswer.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }

    // calcul le note d'un test du candidature
    public void setCandidatureTestNote() {
        int note = 0;
        for (int i = 0; i < getQuestion().getQuestions().size(); i++) {
            if (hasSameAnswer(getQuestion().getQuestions().get(i).getAnswers(), getAnswer().getQuestions().get(i).getAnswers())) {
                note += getQuestion().getQuestions().get(i).getScore();
            }
        }
        setNote(note);
    }

    // Pour vérifier si une réponse est dans la réponse d'un candidat
    public static boolean isInAnswer(int idAnswer, String[] questionAnswers) {
        if (questionAnswers == null) {
            return false;
        }

        for (String questionAnswer : questionAnswers) {
            if (Integer.valueOf(questionAnswer) == idAnswer) {
                return true;
            }
        }
        return false;
    }

    // Changer le hashmap du resultat en Quiz
    public static Quiz resultToQuizAnswer(int idQuiz, HashMap<String, String[]> answerValue) throws Exception {
        Quiz answerResult = Quiz.getQuizById(idQuiz);

        System.out.println("Service : " + answerResult.getService().getIdService());

        for (Question question : answerResult.getQuestions()) {
            String[] questionAnswers = answerValue.get(String.valueOf(question.getIdQuestion()));
            for (Answer answer : question.getAnswers()) {
                if (isInAnswer(answer.getIdAnswer(), questionAnswers)) {
                    answer.setValue(1);
                } else {
                    answer.setValue(0);
                }
            }
        }

        return answerResult;
    }

    // Sauvegarder le test d'un candidat
    public static void saveCandidatureTest(Candidature candidature, WantedProfile wantedProfile, Quiz question, HashMap<String, String[]> answerResult) throws Exception {
        Quiz answer = resultToQuizAnswer(question.getIdQuiz(), answerResult);
        CandidatureTest test = new CandidatureTest(0, candidature, wantedProfile, question, answer);
        test.setCandidatureTestNote();
        test.save();
    }
    
    public static void main(String[] args) throws Exception {
    }

}
