<%-- 
    Document   : programme_entretient.jsp
    Created on : 20 oct. 2023, 18:22:24
    Author     : Fy Botas
--%>
<%@page import=" java.util.List "%>
<%@page import=" model.candidature.Candidature "%>
<%@page import=" model.requis.Service "%>
<%@page import=" model.gestionProfile.WantedProfile "%>
<%@page import=" framework.database.utilitaire.GConnection " %>
<%
    List<Service> listeService = (List<Service>) request.getAttribute("listeService");
    List<WantedProfile> listeWp = (List<WantedProfile>) request.getAttribute("listeWp");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Modal pour faire un entretient -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog  modal-xl modal-dialog-centered">
        <div class="modal-content card-body">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">  </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                <hr>
            </div>
            <div class="modal-body" style="margin-left: 300px;">
                <div class="row">
                    <div class="col-md-6">
                        <p style="float: right;"><img src="./assets/images/faces/face1.jpg" alt="profile"></p>
                        <h2 class="card-title" id='nomPrenom'> </h2>
                        <p id='dtn'>   </p>
                        <p id='adresse'>  </p>
                        <p id='email'>  </p>
                        <p id='tel'> </p>
                        <p id='sexe'>  </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <h3> Parcours professionels </h3>
                        <hr>
                        <p id='exp'>  </p>
                        <p id='parcours'> </p>
                        <p id='poste'>  </p>
                        <h6> Vos taches : </h6>
                        <p style="margin-left: 50px;" id='tache'>  </p>

                        <h3> Formations </h3>
                        <hr>
                        <p id='diplome'></p>
                        <p style="margin-left: 50px;" id='formation'>  </p>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="noteEntretient" class="col-sm-2 col-form-label"> Note d'entretient </label>
                    <div class="col-sm-3">
                        <div class='row'>
                            <p><input type="text" class="form-control" id="noteEntretient" name="note">
                                <input type="hidden" class="form-control" id="idCand" name="idCandidature">
                                <button type="button" class="btn btn-gradient-primary m-1" id='validerBouton'>valider</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-gradient-primary">Terminer</button>
            </div>
        </div>
    </div>
</div>

<!-- Candidats  -->
<%
    List<Candidature> listePostulate = (List<Candidature>) request.getAttribute("listePostulate");
    if(listePostulate.size() != 0) {
%>
<div class="row" >
    <div class="mt-4">
        <h4 class="card-title mb-4 " style="text-align: center;">Liste des candidats qui ont postulés concernant un poste</h4>
        <div class="row m-3 profile-list" id="profile-list" style="overflow-y: auto; max-height: 350px;">
            <% for(int i=0;i<listePostulate.size();i++) { %>
            <div class="col-md-4 stretch-card grid-margin">
                <div class="card " style="text-align: center;">
                    <div class="profile-card">
                        <h5 class="profile-title"> <img src="./assets/images/faces/face1.jpg" alt="profile"></h5>

                        <h5 class="profile-title"> <%= listePostulate.get(i).getPersonnalInformation().getName() %>  <%= listePostulate.get(i).getPersonnalInformation().getFirstName() %> </h5>
                        <p> Sexe : <%= listePostulate.get(i).getPersonnalInformation().getSexe().getSexe() %></p>
                        <p> Date de postulation : <%= listePostulate.get(i).getDepositDate() %></p>
                        <button data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-gradient-primary entretientButton" data-id="<%= listePostulate.get(i).getIdCandidature() %>">Mener un entretien</button>

                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
<% } %>
<script>
    var validerButton = document.getElementById("validerBouton");

    validerButton.addEventListener("click", function () {
        var idCandValue = document.getElementById("idCand").value;
        var noteEntretientValue = document.getElementById("noteEntretient").value;

        var xhr = new XMLHttpRequest();

        xhr.open("POST", "/GRH/EntretientServlet", true);

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onload = function () {
            if (xhr.status === 200) {
                window.location.reload();
            } else {
                alert("Erreur lors de la requête : " + xhr.status);
            }
        };

        var data = "idCand=" + encodeURIComponent(idCandValue) + "&noteEntretient=" + encodeURIComponent(noteEntretientValue);

        xhr.send(data);
    });

</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var boutonsEntretien = document.querySelectorAll(".entretientButton");
        boutonsEntretien.forEach(function (bouton) {
            bouton.addEventListener("click", function (event) {
                event.preventDefault();
                var idCand = bouton.getAttribute("data-id");
                var nomPrenom = document.getElementById("nomPrenom");
                var dtn = document.getElementById("dtn");
                var email = document.getElementById("email");
                var tel = document.getElementById("tel");
                var sexe = document.getElementById("sexe");
                var exp = document.getElementById("exp");
                var parcours = document.getElementById("parcours");
                var poste = document.getElementById("poste");
                var tache = document.getElementById("tache");
                var diplome = document.getElementById("diplome");
                var formation = document.getElementById("formation");
                var xhr = new XMLHttpRequest();
                var idC = document.getElementById("idCand");
                xhr.open("POST", "/GRH/EntretientServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onload = function () {
                    if (xhr.status === 200) {
                        var donnees = JSON.parse(xhr.responseText);
                        idC.value = donnees.idCandidature;
                        nomPrenom.textContent = donnees.PersonnalInformation.name + " " + donnees.PersonnalInformation.firstName;
                        dtn.textContent = "Date de naissance : " + donnees.PersonnalInformation.birthDate;
                        email.textContent = "Email : " + donnees.PersonnalInformation.email;
                        tel.textContent = "Telephone : " + donnees.PersonnalInformation.telephone;
                        sexe.textContent = donnees.PersonnalInformation.sexe.sexe;
                        exp.textContent = "Année d'experience : " + donnees.ProfessionalCareer.experience.experience;
                        for (var i = 0; i < donnees.ProfessionalCareer.careers.length; i++) {
                            parcours.textContent = donnees.ProfessionalCareer.careers[i].startDate + " - " + donnees.ProfessionalCareer.careers[i].endDate;
                            poste.textContent = donnees.ProfessionalCareer.careers[i].society + " en tant que " + donnees.ProfessionalCareer.careers[i].poste;
                            for (var a = 0; a < donnees.ProfessionalCareer.careers[i].tasks.length; a++) {
                                tache.textContent = "- " + donnees.ProfessionalCareer.careers[i].tasks[a];
                            }
                        }
                        diplome.textContent = "Titulaires d'un diplome de : " + donnees.FormationPath.diplome.diplome;
                        for (var e = 0; e < donnees.FormationPath.formations.length; e++) {
                            formation.textContent = donnees.FormationPath.formations[e].year + " : " + donnees.FormationPath.formations[e].diplome + " " + donnees.FormationPath.formations[e].school;
                        }

                    } else {
                        alert("Erreur lors de la requête : " + xhr.status);
                    }
                };
                // Envoyez les données au Servlet
                var formData = "idCandidature=" + encodeURIComponent(idCand);
                xhr.send(formData);
                return false;
            });
        });
    });
</script>
<!-- Candidats qui ont fini l'entretient  -->
<%
    List<Candidature> listeEntretenue = (List<Candidature>) request.getAttribute("listeEntretenue");
    if(listeEntretenue.size() != 0) {
%>
<div class="row">
    <div class="mt-4">
        <h4 class="card-title mb-4 " style="text-align: center;">Liste des candidats qui ont fini l'entretient</h4>
        <div class="row profile-list" id="profile-list" style="overflow-y: auto; max-height: 350px;">
            <% for(int i=0;i<listeEntretenue.size();i++) { %>
            <div class="col-md-4 stretch-card grid-margin">
                <div class="card " style="text-align: center;">
                    <div class="profile-card">
                        <h5 class="profile-title"> <img src="./assets/images/faces/face1.jpg"<%= listeEntretenue.get(i).getPhoto() %> alt="profile"></h5>

                        <h5 class="profile-title"> <%= listeEntretenue.get(i).getPersonnalInformation().getName() %>  <%= listeEntretenue.get(i).getPersonnalInformation().getFirstName() %> </h5>
                        <p> Sexe : <%= listeEntretenue.get(i).getPersonnalInformation().getSexe().getSexe() %></p>
                        <p> Date de postulation : <%= listeEntretenue.get(i).getDepositDate() %></p>
                        <p> Note de l'entretient : <%= listeEntretenue.get(i).getNote() %> </p>

                        <div class="row">
                            <div class = "col-md-6">
                                <button data-bs-toggle="modal"
                                        class="btn btn-gradient-success validateEntretient" data-id="<%= listeEntretenue.get(i).getIdCandidature() %>">Valider
                                </button>
                            </div>
                            <div class = "col-md-6">
                                <button data-bs-toggle="modal"
                                        class="btn btn-gradient-danger declineEntretient" data-id="<%= listeEntretenue.get(i).getIdCandidature() %>">Refuser
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
<% } %>
<!-- SCript pour valider un entretient -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var boutonsEntretien = document.querySelectorAll(".validateEntretient");
        boutonsEntretien.forEach(function (bouton) {
            bouton.addEventListener("click", function (event) {
                event.preventDefault();
                var idCand = bouton.getAttribute("data-id");
                var xhr = new XMLHttpRequest();

                xhr.open("POST", "/GRH/DecisionEntretientServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.onload = function () {
                    if (xhr.status === 200) {
                        window.location.reload();
                    } else {
                        alert("Erreur lors de la requête : " + xhr.status);
                    }
                };

                var formData = "idCandidature=" + encodeURIComponent(idCand);
                xhr.send(formData);
                return false;
            });
        });
    });
</script>

<!-- refuser un entretient -->

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var boutonsEntretien = document.querySelectorAll(".declineEntretient");
        boutonsEntretien.forEach(function (bouton) {
            bouton.addEventListener("click", function (event) {
                event.preventDefault();
                var idCand = bouton.getAttribute("data-id");
                var xhr = new XMLHttpRequest();

                xhr.open("POST", "/GRH/DecisionEntretientServlet", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xhr.onload = function () {
                    if (xhr.status === 200) {
                        window.location.reload();

                    } else {
                        alert("Erreur lors de la requête : " + xhr.status);
                    }
                };

                var formData = "idCandi=" + encodeURIComponent(idCand);
                xhr.send(formData);
                return false;
            });
        });
    });
</script>

<!-- Les resultats des entretients -->
<%
    List<Candidature> listeEmbauched = (List<Candidature>) request.getAttribute("listeEmbauchedCandidat");
    if(listeEmbauched.size() != 0) {
%>
<div class="row">
    <div class="mt-4">
        <h4 class="card-title mb-4 " style="text-align: center;">Le resultat des entretients</h4>
        <form class="form-control" action="/GRH/FiltreEntretientServlet" method="post">
            <div class='col-md-8'>
                <div class='row'>
                    <div class='col-md-4'>
                        <input type="date" name="date" class="form-control p-1">
                    </div>
                    <div class='col-md-4'>
                        <select class="form-control p-1"  name="poste">
                            <% for(int i=0; i<listeWp.size();i++ ) { %>
                            <option value="<%= listeWp.get(i).getIdWantedProfile() %>"> <%= listeWp.get(i).getPoste() %> </option>
                            <% } %>
                        </select>
                        <input type="hidden" value="5" name="status">
                    </div>
                    <div class="col-md-2">
                        <input type="checkbox" name="refuser" value="refuser"> refuser
                    </div>
                    <div class='col-md-2'>
                        <input type="submit" value="filtrer" class="btn btn-gradient-primary p-2">
                    </div>
                </div>
            </div>
        </form>
        <div class="row profile-list" id="profile-list" style="overflow-y: auto; max-height: 350px;">
            <% for(int i=0;i<listeEmbauched.size();i++) { %>
            <div class="col-md-3 stretch-card grid-margin">
                <div class="card " style="text-align: center;">
                    <div class="profile-card">
                        <h5 class="profile-title"> <img src="./assets/images/faces/face1.jpg"<%= listeEmbauched.get(i).getPhoto() %> alt="profile"></h5>

                        <h5 class="profile-title"> <%= listeEmbauched.get(i).getPersonnalInformation().getName() %>  <%= listeEmbauched.get(i).getPersonnalInformation().getFirstName() %> </h5>
                        <p> Sexe : <%= listeEmbauched.get(i).getPersonnalInformation().getSexe().getSexe() %></p>
                        <p> Entretient : <%= listeEmbauched.get(i).getNote() %> </p>
                        <p> Resultat de l'entretient : <% String res = new Candidature().checkResult(listeEmbauched.get(i).getStatus()); out.print(res); %></p>

                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</div>
<% } else { %>
<h3> Aucun element correspondant <a href="/GRH/EntretientServlet"> Retour </a> </h3>
<% } %>


