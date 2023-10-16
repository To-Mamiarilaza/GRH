<!-- <%@ page contentType="text/html; charset=UTF-8" %> -->
<%@page import="java.util.ArrayList"%>
<%@page import="model.requis.Service"%>
<%@page import="model.gestionBesoin.Unity"%>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Diplome "%>
<%@page import=" model.gestionProfile.Adresse "%>
<%@page import=" model.gestionProfile.Salaire "%>
<%@page import=" model.gestionProfile.Sexe "%>
<%@page import=" model.gestionProfile.Experience "%>
<%
    List<Diplome> listeDiplome = (List<Diplome>) request.getAttribute("listeDiplome");
    List<Adresse> listeAdresse = (List<Adresse>) request.getAttribute("listeAdresse");
    List<Experience> listeExperience = (List<Experience>) request.getAttribute("listeExperience");
    List<Salaire> listeSalaire = (List<Salaire>) request.getAttribute("listeSalaire");
    List<Sexe> listeSexe = (List<Sexe>) request.getAttribute("listeSexe");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Insertion des besoins</title>

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
        <div class="container-scroller">
            <!-- partial:./partials/_navbar.html -->
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

                <!-- Modal pour l'insertion d'un nouveau profil -->
                <div class="modal fade" id="newProfil" tabindex="-1" aria-labelledby="newProfilLabel" aria-hidden="true">
                    <div class="modal-dialog  modal-xl modal-dialog-centered">
                        <div class="modal-content card-body">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="newProfilLabel">Insertion nouveau profile</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                <hr>
                            </div>

                            <form class="forms-sample" method="get" action="/RessourceHumaine/AjaxProfileServlet">
                                <div class="form-group row">
                                    <label for="profileName" class="col-sm-2 col-form-label"> Poste </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="profileName" name="poste">
                                    </div>
                                </div>

                                <hr>

                                <div class="row">
                                    <div class="side-by-side col-md-6">
                                        <!-- Section diplome -->
                                        <div class="diplome">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="diplomeChoice"
                                                       class="col-sm-2 col-form-label">Diplome</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="diplome" id="diplomeChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="diplomeNote" id="diplomeNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="diplomeCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("diplomeChoice").value;
                                                var diplomeNote = document.getElementById("diplomeNote").value;
                                                var diplomeCase = document.getElementById("diplomeCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?idDiplome=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "diplome=" + encodeURIComponent(diplome) + "&diplomenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="experience">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="experienceChoice"
                                                       class="col-sm-2 col-form-label">Experience</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="experience" id="experienceChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="experienceNote" id="experienceNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitExperienceButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="experienceCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitExperienceButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("experienceChoice").value;
                                                var diplomeNote = document.getElementById("experienceNote").value;
                                                var diplomeCase = document.getElementById("experienceCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?idExperience=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "experience=" + encodeURIComponent(diplome) + "&experiencenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="salaire mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="salaireChoice"
                                                       class="col-sm-2 col-form-label">Salaire</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="salaire" id="salaireChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="salaireNote" id="salaireNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitSalaireButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="salaireCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitSalaireButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("salaireChoice").value;
                                                var diplomeNote = document.getElementById("salaireNote").value;
                                                var diplomeCase = document.getElementById("salaireCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?idSalaire=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "salaire=" + encodeURIComponent(diplome) + "&salairenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="sexe mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="sexeChoice"
                                                       class="col-sm-2 col-form-label">Sexe</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="sexe" id="sexeChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="sexeNote" id="sexeNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitSexeButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="sexeCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitSexeButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("sexeChoice").value;
                                                var diplomeNote = document.getElementById("sexeNote").value;
                                                var diplomeCase = document.getElementById("sexeCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?idSexe=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "sexe=" + encodeURIComponent(diplome) + "&sexenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="adresse mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="adresseChoice"
                                                       class="col-sm-2 col-form-label">Adresse</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="adresse" id="adresseChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="adresseNote" id="adresseNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitAdresseButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="adresseCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitAdresseButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("adresseChoice").value;
                                                var diplomeNote = document.getElementById("adresseNote").value;
                                                var diplomeCase = document.getElementById("adresseCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?idAdresse=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "adresse=" + encodeURIComponent(diplome) + "&adressenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <input type="submit" class="btn btn-gradient-primary" value="Valider">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal pour le choix d'un profil -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog  modal-xl modal-dialog-centered">
                        <div class="modal-content card-body">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel"> Poste </h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                <hr>
                            </div>
                            <div class="modal-body">
                                <button type="submit" class="btn btn-gradient-primary me-2" data-bs-toggle="modal" id="nouveauProfile"
                                        data-bs-target="#newProfil">Crée un nouveau profile</button>

                                <div class="mt-4">
                                    <h4 class="card-title mb-4">Liste des anciens profiles existants</h4>
                                    <div class="row profile-list" id="profile-list">
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button onclick="profileValided()" type="button" class="btn btn-gradient-primary">Valider</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%  if(request.getAttribute("service") != null) {
                    Service service = (Service)request.getAttribute("service");
                    ArrayList<Unity> unitys = (ArrayList<Unity>)request.getAttribute("unitys");
                %>

                <!-- partial -->
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Service : <%=service.getService() %></h4>
                                        <p class="card-description"> Veuillez bien remplir les formulaires et bien décrire
                                            vos demandes </p>
                                        <form id="formTask" class="forms-sample" method="post" accept-charset="UTF-8">
                                            <div class="form-group">
                                                <label for="besoinDescription">Description du besoin</label>
                                                <textarea name="description" id="description" class="form-control" cols="30"
                                                          rows="10"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Liste des taches</label>
                                                <div class="row d-flex align-items-center">
                                                    <div class="col-md-6">
                                                        <input type="text" id="task" name="tasks" class="form-control"
                                                               placeholder="Nouvelle tache">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <button  
                                                            class="btn btn-gradient-primary me-2">Ajouter</button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group task-list">
                                                <div class="list-wrapper">
                                                    <ul id="valueEnter" class="d-flex flex-column-reverse todo-list todo-list-custom">

                                                    </ul>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="">Charge de travail et profil recherché</h4>
                                        <button data-bs-toggle="modal" data-bs-target="#exampleModal"
                                                class="btn btn-gradient-primary me-1" id="profileModal">Choisir le
                                            profil</button>
                                        <form id="formWorkLoad" class="forms-sample mt-3" action="addBesoinServlet" method="post"> 
                                            <div class="row d-flex align-items-start">
                                                <div class="form-group col-md-4">
                                                    <label for="volumeHorraire">Volume horraire</label>
                                                    <input type="number" class="form-control" id="volumeHorraire"
                                                           placeholder="40">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="unitySelect">Unité</label>
                                                    <select name="" id="unitySelect" class="form-control-sm form-select">
                                                        <% for(int i = 0; i < unitys.size(); i++) { %>
                                                        <option value=<%=unitys.get(i).getIdUnity() %>><%=unitys.get(i).getUnity() %></option>
                                                        <% } %>   
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="unitySelect">Unité</label>
                                                    <button
                                                        class="btn btn-gradient-primary me-2">Ajouter</button>
                                                </div>
                                            </div>
                                            <div class="form-group task-list mt-3" style="height: 250px;">
                                                <div class="list-wrapper">
                                                    <table id="tableWorkLoad" class="table">

                                                    </table>
                                                </div>
                                            </div>
                                        </form>
                                        <button onclick="sendBesoin()" class="btn btn-gradient-primary me-2">Envoye du
                                            besoin</button>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                    <!-- content-wrapper ends -->
                    <!-- partial:/partials/_footer.html -->
                    <footer class="footer">
                        <div class="container-fluid d-flex justify-content-between">
                            <span class="text-muted d-block text-center text-sm-start d-sm-inline-block">Copyright �
                                bootstrapdash.com 2021</span>
                            <span class="float-none float-sm-end mt-1 mt-sm-0 text-end"> Free <a
                                    href="https://www.bootstrapdash.com/bootstrap-admin-template/" target="_blank">Bootstrap
                                    admin template</a> from Bootstrapdash.com</span>
                        </div>
                    </footer>
                    <!-- partial -->
                </div>
                <% } %>
                <!-- main-panel ends -->
            </div>
            <!-- partial -->

            <!-- container-scroller -->
            <!-- plugins:js -->
            <script src="./assets/vendors/js/vendor.bundle.base.js"></script>
            <!-- endinject -->
            <!-- Plugin js for this page -->
            <!-- End plugin js for this page -->
            <!-- inject:js -->
            <script>
                                            document.addEventListener("DOMContentLoaded", function () {
                                                var boutonOuvrirModal = document.getElementById("profileModal");
                                                var profilListe = document.getElementById("profile-list");
                                                var stopIndice = 0;
                                                boutonOuvrirModal.addEventListener("click", function () {
                                                    var xhr = new XMLHttpRequest();
                                                    xhr.open("GET", "/RessourceHumaine/ListeProfileServlet", true);
                                                    xhr.setRequestHeader("Content-Type", "application/json");
                                                    xhr.onload = function () {
                                                        if (xhr.readyState === 4 && xhr.status === 200) {

                                                            var jsonResponse = JSON.parse(xhr.responseText);
                                                            var ListeIdWp = jsonResponse.listeIdWp;
                                                            var ListePoste = jsonResponse.listePost;
                                                            var BestDiplomeNote = jsonResponse.listeDiplomeNote;
                                                            var BestAdresseNote = jsonResponse.listeAdresseNote;
                                                            var BestSexeNote = jsonResponse.listeSexeNote;
                                                            var BestExperienceNote = jsonResponse.listeExperienceNote;
                                                            var BestSalaireNote = jsonResponse.listeSalaireNote;
                                                                if (stopIndice === 0) {
                                                                    console.log(BestDiplomeNote.length);
                                                                for (var i = 0; i < BestDiplomeNote.length; i++) {
                                                                    var profileCard = document.createElement("div");
                                                                    var id = ListeIdWp[i];
                                                                    console.log("id : "+id);
                                                                    profileCard.setAttribute("onclick", "clicked("+id+")");
                                                                    profileCard.classList.add("profile-card");
                                                                    var removeFloating = document.createElement("div");
                                                                    removeFloating.classList.add("remove-floating");
                                                                    var removeIcon = document.createElement("i");
                                                                    removeIcon.classList.add("remove", "mdi", "mdi-close-circle-outline");
                                                                    (function (ListeIdWp) {
                                                                        removeIcon.onclick = function () {
                                                                            var xhr = new XMLHttpRequest();
                                                                            xhr.open("GET", "/RessourceHumaine/DeleteProfileServlet?indice=" + ListeIdWp, true);
                                                                            xhr.onreadystatechange = function () {
                                                                                if (xhr.readyState === 4) {
                                                                                    if (xhr.status === 200) {
                                                                                        window.location.reload();
                                                                                    } else {
                                                                                        alert("Erreur !");
                                                                                    }
                                                                                }
                                                                            };
                                                                            xhr.send();
                                                                        };
                                                                    })(ListeIdWp[i]); // i est capturé comme indice ici

                                                                    var profileTitle = document.createElement("h5");
                                                                    profileTitle.classList.add("profile-title");
                                                                    profileTitle.textContent = ListePoste[i];
                                                                    var ul = document.createElement("ul");
                                                                    var profileDiplome = document.createElement("li");
                                                                    profileDiplome.classList.add("profile-diplome");
                                                                    profileDiplome.textContent = BestDiplomeNote[i].diplome.diplome;
                                                                    ul.appendChild(profileDiplome);
                                                                    var profileAdresse = document.createElement("li");
                                                                    profileAdresse.classList.add("profile-adress");
                                                                    profileAdresse.textContent = BestAdresseNote[i].adresse.adresse;
                                                                    ul.appendChild(profileAdresse);
                                                                    var profileSexe = document.createElement("li");
                                                                    profileSexe.classList.add("profile-sexe");
                                                                    profileSexe.textContent = BestSexeNote[i].sexe.sexe;
                                                                    ul.appendChild(profileSexe);
                                                                    var profileExperience = document.createElement("li");
                                                                    profileExperience.classList.add("profile-experience");
                                                                    profileExperience.textContent = BestExperienceNote[i].experience.experience;
                                                                    ul.appendChild(profileExperience);
                                                                    var profileSalary = document.createElement("li");
                                                                    profileSalary.classList.add("profile-salary");
                                                                    profileSalary.textContent = BestSalaireNote[i].salaire.salaire.toLocaleString('fr-FR') + " Ar";
                                                                    ul.appendChild(profileSalary);
                                                                    removeFloating.appendChild(removeIcon);
                                                                    profileCard.appendChild(removeFloating);
                                                                    profileCard.appendChild(profileTitle);
                                                                    profileCard.appendChild(ul);
                                                                    var donneeCard = document.createElement("div");
                                                                    donneeCard.classList.add("card");
                                                                    donneeCard.appendChild(profileCard);
                                                                    var profileLayout = document.createElement("div");
                                                                    profileLayout.classList.add("col-md-3");
                                                                    profileLayout.classList.add("stretch-card");
                                                                    profileLayout.classList.add("grid-margin");
                                                                    profileLayout.appendChild(profileCard);
                                                                    profilListe.appendChild(profileLayout);
                                                                    stopIndice = stopIndice + 1;
                                                                }
                                                            }

                                                        } else {
                                                            alert("Une erreur s'est produite lors de la récupération des données.");
                                                        }
                                                    };
                                                    // Envoyez la requête AJAX
                                                    xhr.send();
                                                });
                                            });
            </script>

            <!-- script pour ajouter les données du diplomes -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/RessourceHumaine/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeDiplome = responseData.listeDiplome;
                            var selectDiplome = document.getElementById("diplomeChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeDiplome.length; i++) {
                                var diplome = listeDiplome[i].diplome;
                                var option = document.createElement("option");
                                option.value = diplome;
                                option.textContent = diplome;
                                selectDiplome.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <!-- script pour ajouter les données du adresse -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/RessourceHumaine/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeAdresse = responseData.listeAdresse;
                            var selectAdresse = document.getElementById("adresseChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeAdresse.length; i++) {
                                var adresse = listeAdresse[i].adresse;
                                var option = document.createElement("option");
                                option.value = adresse;
                                option.textContent = adresse;
                                selectAdresse.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>


            <!-- script pour ajouter les données du experience -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/RessourceHumaine/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeExperience = responseData.listeExperience;
                            var selectExperience = document.getElementById("experienceChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeExperience.length; i++) {
                                var experience = listeExperience[i].experience;
                                var option = document.createElement("option");
                                option.value = experience;
                                option.textContent = experience;
                                selectExperience.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <!-- script pour ajouter les données du salaire -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/RessourceHumaine/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeSalaire = responseData.listeSalaire;
                            var selectSalaire = document.getElementById("salaireChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeSalaire.length; i++) {
                                var salaire = listeSalaire[i].salaire;
                                var option = document.createElement("option");
                                option.value = salaire;
                                option.textContent = salaire;
                                selectSalaire.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <!-- script pour ajouter les données du sexe -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/RessourceHumaine/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeSexe = responseData.listeSexe;
                            var selectSexe = document.getElementById("sexeChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeSexe.length; i++) {
                                var sexe = listeSexe[i].sexe;
                                var option = document.createElement("option");
                                option.value = sexe;
                                option.textContent = sexe;
                                selectSexe.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <script>
                var idClicked = -1;
                var tableWorkLoad = document.getElementById("tableWorkLoad");

                function remove(logo) {
                    // Récupérer l'élément parent (li) du bouton cliqué et le supprimer
                    var parentElement = logo.parentNode;
                    parentElement.parentNode.removeChild(parentElement);
                    var itemValue = parentElement.childNodes[0].textContent;

                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', './addTaskServlet?itemToRemove=' + itemValue, true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    xhr.send();
                }
                ;

                function clicked(id) {
                    idClicked = id;
                }
                ;

                function profileValided() {
                    var idValue = idClicked;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './profileValidedServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec la valeur à envoyer
                    var formData = 'idValue=' + encodeURIComponent(idValue);

                    // Envoyez la requête
                    xhr.send(formData);
                }
                ;

                function createListItem(item) {

                    var valueEnter = document.getElementById('valueEnter');
                    var listItem = document.createElement('li');
                    var divItem = document.createElement("div");
                    divItem.classList.add("form-check");
                    var labelItem = document.createElement("label");
                    labelItem.classList.add("form-check-label");
                    labelItem.textContent = item;

                    var logoItem = document.createElement("i");
                    logoItem.classList.add("remove");
                    logoItem.classList.add("mdi");
                    logoItem.classList.add("mdi-close-circle-outline");
                    logoItem.setAttribute("onclick", "remove(this)");

                    divItem.appendChild(labelItem);
                    listItem.appendChild(divItem);
                    listItem.appendChild(logoItem);
                    valueEnter.appendChild(listItem);

                }
                ;
                function createTableworkLoad(table, poste, horaire, unity) {
                    var tr = document.createElement("tr");

                    var td1 = document.createElement("td");
                    td1.textContent = poste;

                    var td2 = document.createElement("td");
                    td2.textContent = horaire + " " + unity;
                    td2.classList.add("to-right");

                    var td3 = document.createElement("td");
                    var logoQuestion = document.createElement("i");
                    logoQuestion.classList.add("list-action");
                    logoQuestion.classList.add("primary");
                    logoQuestion.classList.add("mdi");
                    logoQuestion.classList.add("mdi-comment-question-outline");
                    td3.appendChild(logoQuestion);

                    var td4 = document.createElement("td");
                    var logoDelete = document.createElement("i");
                    logoDelete.setAttribute("onclick", "removeWorkLoad(this)");
                    logoDelete.classList.add("list-action");
                    logoDelete.classList.add("danger");
                    logoDelete.classList.add("mdi");
                    logoDelete.classList.add("mdi-close-circle-outline");
                    td4.appendChild(logoDelete);

                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    table.appendChild(tr);
                }
                ;

                function removeWorkLoad(logo) {
                    var parentElement = logo.parentNode;
                    var parent = parentElement.parentNode;
                    parent.parentNode.removeChild(parent);
                    var poste = parent.cells[0].textContent;
                    var hu = parent.cells[1].textContent.split(' ');
                    var volumeHorraire = hu[0];
                    var unity = hu[1];


                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', './addWorkLoadServlet?poste=' + poste + '&vh=' + volumeHorraire + '&unity=' + unity, true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    xhr.send();

                }

                // Fonction pour ajouter une tâche de travail
                function addWorkLoad() {
                    var volumeHorraire = document.getElementById("volumeHorraire").value;
                    var unitySelect = document.getElementById("unitySelect").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addWorkLoadServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                const res = xhr.responseText.split('&');

                                createTableworkLoad(tableWorkLoad, res[0], volumeHorraire, res[1]);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'volumeHorraire=' + encodeURIComponent(volumeHorraire) + '&unitySelect=' + encodeURIComponent(unitySelect);

                    // Envoyez la requête
                    xhr.send(formData);
                }

                // Ajoutez un gestionnaire d'événements au formulaire
                document.getElementById('formWorkLoad').addEventListener('submit', function (event) {
                    event.preventDefault();  // Empêche le rechargement de la page
                    addWorkLoad();
                });

                function sendBesoin() {
                    var descri = document.getElementById("description").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addBesoinServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {

                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'descri=' + encodeURIComponent(descri);

                    // Envoyez la requête
                    xhr.send(formData);

                }


                //Ajouter une tache
                document.getElementById('formTask').addEventListener('submit', function (event) {
                    event.preventDefault();  // Empêche le rechargement de la page

                    var task = document.getElementById('task').value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addTaskServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                createListItem(xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'task=' + encodeURIComponent(task);

                    // Envoyez la requête
                    xhr.send(formData);
                });
            </script>


            <script src="./assets/js/off-canvas.js"></script>
            <script src="./assets/js/hoverable-collapse.js"></script>
            <script src="./assets/js/misc.js"></script>
            <!-- endinject -->
            <!-- Custom js for this page -->
            <!-- End custom js for this page -->
    </body>

</html>