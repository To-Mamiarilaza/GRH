<%@page import="model.quiz.CandidatureTest, model.candidature.Candidature, model.quiz.Question, model.quiz.Answer" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    CandidatureTest candidatureTest = (CandidatureTest) request.getAttribute("candidatureTest");
    Candidature candidature = candidatureTest.getCandidature();
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-checkbox-marked-outline
               "></i>
        </span> Détail d'un test
    </h3>
    <nav aria-label="breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <span></span>Overview <i
                    class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
        </ul>
    </nav>
</div>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4>Candidature N° : 5</h4>
                <h6 class="mt-3">Pour le poste d'un <%= candidatureTest.getCandidature().getWantedProfile().getPoste() %></h6>
                <h6><%= candidatureTest.getCandidature().getPersonnalInformation().getName() %> <%= candidatureTest.getCandidature().getPersonnalInformation().getFirstName() %></h6>
                <div class="row mt-4">
                    <div class="col-md-6 p-3">
                        <div class="quiz-result">
                            <div class="d-flex">
                                <h6>Resultat du test</h6>
                                <h6 class="mx-5">Note : <%= candidatureTest.getCandidature().getNote() %></h6>
                            </div>
                            <% for(Question question : candidatureTest.getAnswer().getQuestions()) { %>
                            <div class="question mt-3">
                                <p class="mb-2"><strong>1.</strong> <span  class="mx-3"><%= question.getQuestion() %></span></p>
                                <div class="answer-list">
                                    <% for(Answer answer : question.getAnswers()) { %>
                                    <% String value = answer.getValue() == 1 ? "checked" : ""; %>
                                    <div class="answer mt-2">
                                        <input type="checkbox" <%= value %> disabled>
                                        <label for="" class="mx-3"><%= answer.getAnswer() %></label>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                            <% } %>

                        </div>
                    </div>
                    <div class="col-md-6 p-3">
                        <div class="info-person">
                            <h4><%= candidatureTest.getCandidature().getPersonnalInformation().getName() %> <%= candidatureTest.getCandidature().getPersonnalInformation().getFirstName() %></h4>

                            <ul class="mt-4">
                                <li><strong>Date de naissance :</strong><span class="mx-3"><%= candidatureTest.getCandidature().getPersonnalInformation().getBirthDate() %></span></li>
                                <li><strong>Adresse :</strong><span class="mx-3"><%= candidatureTest.getCandidature().getPersonnalInformation().getAdresse().getAdresse() %></span></li>
                                <li><strong>Email :</strong><span class="mx-3"><%= candidatureTest.getCandidature().getPersonnalInformation().getEmail() %></span></li>
                                <li><strong>Telephone :</strong><span class="mx-3"><%= candidatureTest.getCandidature().getPersonnalInformation().getTelephone() %></span></li>
                                <li><strong>Genre :</strong><span class="mx-3"><%= candidatureTest.getCandidature().getPersonnalInformation().getSexe().getSexe() %></span></li>
                            </ul>

                            <div class="mt-4">
                                <p><strong>Diplome : </strong><span class="mx-3"><%= candidatureTest.getCandidature().getFormationPath().getDiplome().getDiplome() %></span></p>
                                <p><strong>Année d'experience : </strong><span class="mx-3"><%= candidatureTest.getCandidature().getProfessionalCareer().getExperience().getExperience() %></span></p>
                                <p><strong>Description : </strong></p>
                                <textarea name="description" class="form-control description" id="" cols="30" rows="10"><%= candidatureTest.getCandidature().getSelfProfile() %></textarea>
                            </div>

                            <div class="mt-4">
                                <a href="#" class="btn btn-outline-info btn-sm px-5">Voir le CV</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="action mt-3">
                    <div class="d-flex">
                        <a href="./repondre-candidature?idCandidature=<%= candidature.getIdCandidature() %>&reponse=<%= 0 %>&idCandidatureTest=<%= candidatureTest.getIdCandidatureTest() %>" class="btn btn-gradient-info px-5">Refuser</a>
                        <a href="./repondre-candidature?idCandidature=<%= candidature.getIdCandidature() %>&reponse=<%= 1 %>&idCandidatureTest=<%= candidatureTest.getIdCandidatureTest() %>" class="btn btn-gradient-info px-5 mx-5">Valider</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

