<%-- 
    Document   : newjsp
    Created on : 12 oct. 2023, 18:56:25
    Author     : To Mamiarilaza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.quiz.Quiz, model.candidature.PersonnalInformation, model.gestionProfile.WantedProfile, model.quiz.Question, model.quiz.Answer" %>

<%
    int idCandidature = (int) request.getAttribute("idCandidature");
    WantedProfile wantedProfile = (WantedProfile) request.getAttribute("wantedProfile");
    PersonnalInformation infoPerso = (PersonnalInformation) request.getAttribute("infoPerso");
    Quiz quiz = (Quiz) request.getAttribute("quiz");

%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Insertion candidature</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="./assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="./assets/vendors/css/vendor.bundle.base.css">
        <link rel="stylesheet" href="./assets/css/quiz/quiz_candidature.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="./assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="./assets/images/favicon.ico" />
    </head>

    <body>
        <div class="container-scroller">
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="content-wrapper d-flex align-items-center auth">
                    <div class="row flex-grow">
                        <div class="col-lg-6 mx-auto">
                            <div class="auth-form-light text-left p-5">
                                <div class="d-flex align-items-center brand-logo" style="color: #da8cff;">
                                    <i class="mdi mdi-vector-arrange-above" style="font-size: 35px;margin-right: 25px;"></i>
                                    <h3 style="margin: 0;">Huile de bongolava</h3>
                                </div>
                                <h4>Felicitation <%= infoPerso.getFirstName() %> <%= infoPerso.getName() %> ! </h4>
                                <h6 class="font-weight-light little-line-height">Vous êtes choisis pour passer le test pour le poste de
                                    <strong><%= wantedProfile.getPoste() %></strong></h6>
                                <h6 class="font-weight-light little-line-height">Maintenant vous devez effectuer un test pour gagner une
                                    entretien <br> <strong>Répondez a ces questions</strong></h6>
                                <hr>
                                <form class="" action="./quiz-validation" method="POST">
                                    <input type="hidden" name="idCandidature" value="<%= idCandidature %>">
                                    <input type="hidden" name="idWantedProfile" value="<%= wantedProfile.getIdWantedProfile() %>">
                                    <input type="hidden" name="idQuiz" value="<%= quiz.getIdQuiz() %>">

                                    <div class="mt-4 quizzes-list">
                                        <% for(Question question : quiz.getQuestions()) { %>
                                        <div class="quiz col-md-12 p-3 mb-4">
                                            <div class="quiz-header d-flex justify-content-between align-items-center">
                                                <h4 class="quiz-title"><%= question.getQuestion() %><span class="mx-4">( <%= question.getScore() %> pts )</span>
                                                </h4>
                                            </div>
                                            <hr>
                                            <div class="answer-list">
                                                <% for(Answer answer : question.getAnswers()) { %>
                                                <div class="answer d-flex justify-content-between align-items-center">
                                                    <div class="form-check">
                                                        <label class="form-check-label">
                                                            <input type="checkbox" class="form-check-input" name="<%= question.getIdQuestion() %>" value="<%= answer.getIdAnswer() %>"><%= answer.getAnswer() %></label>
                                                    </div>
                                                </div>
                                                <% } %>
                                            </div>
                                        </div>
                                        <% } %>
                                    </div>
                                    <div class="mt-3">
                                        <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
                                           href="./index.html">ENVOYER LES REPONSES</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- content-wrapper ends -->
            </div>
            <!-- page-body-wrapper ends -->
        </div>
        <!-- container-scroller -->
        <!-- plugins:js -->
        <script src="./assets/vendors/js/vendor.bundle.base.js"></script>
        <!-- endinject -->
        <!-- Plugin js for this page -->
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="./assets/js/off-canvas.js"></script>
        <script src="./assets/js/hoverable-collapse.js"></script>
        <script src="./assets/js/misc.js"></script>
        <!-- endinject -->
    </body>

</html>