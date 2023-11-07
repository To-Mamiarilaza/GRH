<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.quiz.CandidatureTest, java.util.List, model.gestionProfile.WantedProfile" %>

<%
    List<CandidatureTest> candidatureTestList = (List<CandidatureTest>) request.getAttribute("candidatureTestList");
    List<WantedProfile> profiles = (List<WantedProfile>) request.getAttribute("profiles");
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-checkbox-marked-outline
               "></i>
        </span> Résultat des tests
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
                <h4 class="card-title">Listes des candidature avec leurs notes</h4>
                <form class="form mt-3" method="POST" action="./quiz-results">
                    <div class="row align-items-end">
                        <div class="form-group col-md-5 mt-2">
                            <label for="" class="form-label">Poste</label>
                            <select name="poste" id="" class="form-select mt-2">
                                <% for(WantedProfile profile: profiles) { %>
                                <option value="<%= profile.getIdWantedProfile() %>"><%= profile.getPoste() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-group col-md-2">
                            <input type="submit" class="btn btn-gradient-primary" value="Filtrer">
                        </div>
                    </div>
                </form>
                <table class="table mt-3">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Note</th>
                            <th>Description</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(CandidatureTest candidatureTest : candidatureTestList) { %>
                            <tr>
                                <td><%= candidatureTest.getQuizDate() %></td>
                                <td><%= candidatureTest.getCandidature().getPersonnalInformation().getName() %></td>
                                <td><%= candidatureTest.getCandidature().getPersonnalInformation().getFirstName() %></td>
                                <td><%= candidatureTest.getNote() %></td>
                                <td><%= candidatureTest.getCandidature().getSelfProfile() %></td>
                                <td><a href="./quiz-result-detail?idCandidatureTest=<%= candidatureTest.getIdCandidatureTest() %>" class="btn btn-gradient-info btn-sm">Voir plus</a></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
