<%-- 
    Document   : programme_entretient.jsp
    Created on : 20 oct. 2023, 18:22:24
    Author     : Fy Botas
--%>
<%@page import=" java.util.List "%>
<%@page import=" model.candidature.Candidature "%>
<%@page import=" model.requis.Service "%>
<%@page import=" model.gestionProfile.WantedProfile "%>
<%
    List<Service> listeService = (List<Service>) request.getAttribute("listeService");
    List<WantedProfile> listeWp = (List<WantedProfile>) request.getAttribute("listeWp");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>programme d'entretient</title>

        <!-- plugins:css -->
        <link rel="stylesheet" href="./assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="./assets/css/besoin/besoin-insertion.css">
        <link rel="stylesheet" href="./assets/vendors/css/vendor.bundle.base.css">
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
        <nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
                <a class="d-flex align-items-center d-navbar-brand brand-logo"
                   style="text-decoration: none; color: #da8cff;" href="./index.html">
                    <i class="mdi mdi-account-box" style="font-size: 35px;margin-right: 25px;"></i>
                    <h2 style="margin: 0;">GRH</h2>
                </a>
                <a class="navbar-brand brand-logo-mini" href="./index.html"><img
                        src="./assets/images/logo-mini.svg" alt="logo" /></a>
            </div>
            <div class="navbar-menu-wrapper d-flex align-items-stretch">
                <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                    <span class="mdi mdi-menu"></span>
                </button>
                <div class="search-field d-none d-md-block">
                    <form class="d-flex align-items-center h-100" action="#">
                        <div class="input-group">
                            <div class="input-group-prepend bg-transparent">
                                <i class="input-group-text border-0 mdi mdi-magnify"></i>
                            </div>
                            <input type="text" class="form-control bg-transparent border-0"
                                   placeholder="Search projects">
                        </div>
                    </form>
                </div>
                <ul class="navbar-nav navbar-nav-right">
                    <li class="nav-item nav-profile dropdown">
                        <a class="nav-link dropdown-toggle" id="profileDropdown" href="#" data-bs-toggle="dropdown"
                           aria-expanded="false">
                            <div class="nav-profile-img">
                                <img src="./assets/images/faces/face1.jpg" alt="image">
                                <span class="availability-status online"></span>
                            </div>
                            <div class="nav-profile-text">
                                <p class="mb-1 text-black">David Greymaax</p>
                            </div>
                        </a>
                        <div class="dropdown-menu navbar-dropdown" aria-labelledby="profileDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="mdi mdi-cached me-2 text-success"></i> Activity Log </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">
                                <i class="mdi mdi-logout me-2 text-primary"></i> Signout </a>
                        </div>
                    </li>
                    <li class="nav-item d-none d-lg-block full-screen-link">
                        <a class="nav-link">
                            <i class="mdi mdi-fullscreen" id="fullscreen-button"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link count-indicator dropdown-toggle" id="messageDropdown" href="#"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="mdi mdi-email-outline"></i>
                            <span class="count-symbol bg-warning"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
                             aria-labelledby="messageDropdown">
                            <h6 class="p-3 mb-0">Messages</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="./assets/images/faces/face4.jpg" alt="image" class="profile-pic">
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject ellipsis mb-1 font-weight-normal">Mark send you a message
                                    </h6>
                                    <p class="text-gray mb-0"> 1 Minutes ago </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="./assets/images/faces/face2.jpg" alt="image" class="profile-pic">
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject ellipsis mb-1 font-weight-normal">Cregh send you a
                                        message</h6>
                                    <p class="text-gray mb-0"> 15 Minutes ago </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <img src="./assets/images/faces/face3.jpg" alt="image" class="profile-pic">
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject ellipsis mb-1 font-weight-normal">Profile picture updated
                                    </h6>
                                    <p class="text-gray mb-0"> 18 Minutes ago </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <h6 class="p-3 mb-0 text-center">4 new messages</h6>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#"
                           data-bs-toggle="dropdown">
                            <i class="mdi mdi-bell-outline"></i>
                            <span class="count-symbol bg-danger"></span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
                             aria-labelledby="notificationDropdown">
                            <h6 class="p-3 mb-0">Notifications</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-success">
                                        <i class="mdi mdi-calendar"></i>
                                    </div>
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject font-weight-normal mb-1">Event today</h6>
                                    <p class="text-gray ellipsis mb-0"> Just a reminder that you have an event today
                                    </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-warning">
                                        <i class="mdi mdi-settings"></i>
                                    </div>
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject font-weight-normal mb-1">Settings</h6>
                                    <p class="text-gray ellipsis mb-0"> Update dashboard </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-info">
                                        <i class="mdi mdi-link-variant"></i>
                                    </div>
                                </div>
                                <div
                                    class="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 class="preview-subject font-weight-normal mb-1">Launch Admin</h6>
                                    <p class="text-gray ellipsis mb-0"> New admin wow! </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <h6 class="p-3 mb-0 text-center">See all notifications</h6>
                        </div>
                    </li>
                    <li class="nav-item nav-logout d-none d-lg-block">
                        <a class="nav-link" href="#">
                            <i class="mdi mdi-power"></i>
                        </a>
                    </li>
                    <li class="nav-item nav-settings d-none d-lg-block">
                        <a class="nav-link" href="#">
                            <i class="mdi mdi-format-line-spacing"></i>
                        </a>
                    </li>
                </ul>
                <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                        data-toggle="offcanvas">
                    <span class="mdi mdi-menu"></span>
                </button>
            </div>
        </nav>
        <!-- partial -->
        <div class="container-fluid page-body-wrapper">
            <!-- partial:./partials/_sidebar.html -->
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
                <ul class="nav">
                    <li class="nav-item nav-profile">
                        <a href="#" class="nav-link">
                            <div class="nav-profile-image">
                                <img src="./assets/images/faces/face1.jpg" alt="profile">
                                <span class="login-status online"></span>
                                <!--change to offline or busy as needed-->
                            </div>
                            <div class="nav-profile-text d-flex flex-column">
                                <span class="font-weight-bold mb-2">David Grey. H</span>
                                <span class="text-secondary text-small">Project Manager</span>
                            </div>
                            <i class="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./index.html">
                            <span class="menu-title">Dashboard</span>
                            <i class="mdi mdi-home menu-icon"></i>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="./pages/icons/mdi.html">
                            <span class="menu-title">Recrutements</span>
                            <i class="mdi mdi-contacts menu-icon"></i>
                        </a>
                    </li>
                </ul>
            </nav>

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

            <div class="main-panel">
                <!-- Candidats  -->
                <%
                    List<Candidature> listePostulate = (List<Candidature>) request.getAttribute("listePostulate");
                    if(listePostulate.size() != 0) {
                %>
                <div class="content-wrapper">
                    <div class="row" >
                        <div class="mt-4">
                            <h4 class="card-title mb-4 " style="text-align: center;">Liste des candidats qui ont postulés concernant un poste</h4>
                            <!--                            <form class="form-control" action="/GRH/FiltreEntretientServlet" method="post">
                                                            <div class='col-md-6'>
                                                                <div class='row'>
                                                                    <div class='col-md-5 p-2'>
                                                                        <input type="date" name="date" class="form-control p-1">
                                                                    </div>
                                                                    <div class='col-md-5'>
                                                                        <select class="form-control"  name="poste">
                            <% for(int i=0; i<listeWp.size();i++ ) { %>
                            <option value="<%= listeWp.get(i).getIdWantedProfile() %>"> <%= listeWp.get(i).getPoste() %> </option>
                            <% } %>
                        </select>
                    </div>
                    <input type="hidden" value="1" name="status">
                    <div class='col-md-2'>
                        <input type="submit" value="filtrer" class="btn btn-gradient-primary p-2">
                    </div>
                </div>
            </div>
        </form>-->
                            <div class="row m-3 profile-list" id="profile-list" style="overflow-y: auto; max-height: 350px;">
                                <% for(int i=0;i<listePostulate.size();i++) { %>
                                <div class="col-md-4 stretch-card grid-margin">
                                    <div class="card " style="text-align: center;">
                                        <div class="profile-card">
                                            <h5 class="profile-title"> <img src="./assets/images/faces/face1.jpg" alt="profile"></h5>

                                            <h5 class="profile-title"> <%= listePostulate.get(i).getPersonnalInformation().getName() %>  <%= listePostulate.get(i).getPersonnalInformation().getFirstName() %> </h5>
                                            <p> Sexe : <%= listePostulate.get(i).getPersonnalInformation().getSexe().getSexe() %></p>
                                            <p> Date de postulation : <%= listePostulate.get(i).getDepositDate() %></p>
                                            <p> Note du cv : <%= listePostulate.get(i).getNote() %> </p>
                                            <button data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-gradient-primary entretientButton" data-id="<%= listePostulate.get(i).getIdCandidature() %>">Mener un entretien</button>

                                        </div>
                                    </div>
                                </div>
                                <% } %>
                            </div>
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
                                var note = document.getElementById("exampleModalLabel");
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
                                        note.textContent = "Note du cv : " + donnees.note;
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
                <div class="content-wrapper">
                    <div class="row">
                        <div class="mt-4">
                            <h4 class="card-title mb-4 " style="text-align: center;">Liste des candidats qui ont fini l'entretient</h4>
                            <!--                            <form class="form-control" action="/GRH/FiltreEntretientServlet" method="post">
                                                            <div class='col-md-6'>
                                                                <div class='row'>
                                                                    <div class='col-md-5'>
                                                                        <input type="date" name="date" class="form-control p-1">
                                                                    </div>
                                                                    <div class='col-md-5'>
                                                                        <select class="form-control"  name="poste">
                            <% for(int i=0; i<listeWp.size();i++ ) { %>
                            <option value="<%= listeWp.get(i).getIdWantedProfile() %>"> <%= listeWp.get(i).getPoste() %> </option>
                            <% } %>
                        </select>
                    </div>
                    <input type="hidden" value="2" name="status">
                    <div class='col-md-2'>
                        <input type="submit" value="filtrer" class="btn btn-gradient-primary p-2">
                    </div>
                </div>
            </div>
        </form> -->
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
                <div class="content-wrapper">
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
                </div>
                <% } else { %>
                <h3> Aucun element correspondant <a href="/GRH/EntretientServlet"> Retour </a> </h3>
                <% } %>


                <footer class="footer">
                    <div class="container-fluid d-flex justify-content-between">
                        <span class="text-muted d-block text-center text-sm-start d-sm-inline-block">Copyright �
                            bootstrapdash.com 2021</span>
                        <span class="float-none float-sm-end mt-1 mt-sm-0 text-end"> Free <a
                                href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap
                                admin template</a> from Bootstrapdash.com</span>
                    </div>
                </footer>
            </div>
            <div class="row">
            </div>
            <div class="row">
            </div>

            <script src="./assets/vendors/js/vendor.bundle.base.js"></script>

            <script src="./assets/js/off-canvas.js"></script>
            <script src="./assets/js/hoverable-collapse.js"></script>
            <script src="./assets/js/misc.js"></script>
    </body>
</html>
