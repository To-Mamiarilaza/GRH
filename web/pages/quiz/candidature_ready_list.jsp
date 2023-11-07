<%@page import=" java.util.ArrayList "%>
<%@page import=" java.util.List "%>
<%@page import=" model.candidature.Candidature "%>
<%@page import=" model.requis.Service "%>
<%@page import=" model.gestionProfile.WantedProfile "%>

<%
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Listes des candidatures
    </h3>
</div>
<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="">Les candidats prêts pour le test</h4>
                
                <div class="row mt-3">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID Candidature</th>
                                <th>Poste</th>
                                <th>Nom</th>
                                <th>Prenom</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Candidature candidature: candidatures) { %>
                            <tr>
                                <td><%= candidature.getIdCandidature() %></td>
                                <td><%= candidature.getWantedProfile().getPoste() %></td>
                                <td><%= candidature.getPersonnalInformation().getName() %></td>
                                <td><%= candidature.getPersonnalInformation().getFirstName() %></td>
                                <td><a href="./quiz-realisation?idCandidature=<%= candidature.getIdCandidature() %>" class="btn btn-gradient-info btn-sm">Passer le test</a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>