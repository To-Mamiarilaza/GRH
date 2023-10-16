<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Experience "%>
<%
    List<Experience> listeExperience = (List<Experience>) request.getAttribute("listeExperience");
    String poste = request.getParameter("poste");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Insertion candidatures</title>

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
            <div class="container-fluid page-body-wrapper full-page-wrapper">
                <div class="content-wrapper d-flex align-items-center auth">
                    <div class="row flex-grow">
                        <div class="col-lg-10 mx-auto">
                            <div class="auth-form-light text-left p-5">
                                <div class="d-flex align-items-center brand-logo" style="color: #da8cff;">
                                    <i class="mdi mdi-vector-arrange-above" style="font-size: 35px;margin-right: 25px;"></i>
                                    <h3 style="margin: 0;">Huile de bongolava</h3>
                                </div>
                                <h4>Nous allons maintenant entrer votre carriÃ¨re professionel</h4>
                                <h6 class="font-weight-light little-line-height">Ajouter petit Ã  petit les carriÃ¨res professionels que
                                    vous avez faits ainsi que vos qualifications</h6>
                                <form class="pt-3 form" method="post" action="/RessourceHumaine/ProfessionalCareerInsertionServlet">
                                    <div class="form-group row align-items-center">
                                        <label for="experience" class="form-label target-poste col-sm-2">AnnÃ©e d'Ã©xperience</label>
                                        <div class="col-sm-5">
                                            <select name="experience" id="experience" class="form-control form-select">
                                                <% for(int i = 0;i<listeExperience.size();i++) { %>
                                                <option value="<%= listeExperience.get(i).getExperience() %>"><%= listeExperience.get(i).getExperience() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-6">
                                            <hr>
                                            <div class="form-group row mt-5 align-items-center justify-content-between">
                                                <label for="date" class="col-sm-4 target-poste">Date du parcours</label>
                                                <div class="col-sm-8 d-flex align-items-center justify-content-between">
                                                    <input type="date" class="form-control form-control-sm" name="startDate" id="startDate">
                                                    <label for="" class="target-poste mx-3"> Ã  </label>
                                                    <input type="date" class="form-control form-control-sm" name="endDate" id="endDate">
                                                </div>
                                            </div>
                                            <div class="form-group align-items-center row">
                                                <label for="societe" class="target-poste col-sm-4">SociÃ©tÃ©</label>
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Nom du societe" name="societe" id="societe">
                                                </div>
                                            </div>
                                            <div class="form-group align-items-center row">
                                                <label for="societe" class="target-poste col-sm-4">Poste</label>
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Votre ancien poste" name="ancienPoste" id="ancienPoste">
                                                </div>
                                            </div>
                                            <div class="form-group align-items-center row">
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Tache ou accomplissement importante" name="tache" id="tache">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button class="btn btn-gradient-primary" id="ajoutBouton">Ajouter</button>
                                                </div>
                                                <script>
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        var boutonSubmit = document.getElementById("ajoutBouton");
                                                        boutonSubmit.addEventListener("click", function (event) {
                                                            event.preventDefault();
                                                            var task = document.getElementById("tache").value;
                                                            var xhr = new XMLHttpRequest();
                                                            xhr.open("POST", "/RessourceHumaine/ProfessionalCareerInsertionServlet", true);
                                                            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                                                            var taskCase = document.getElementById("taskCase");
                                                            xhr.onload = function () {
                                                                if (xhr.readyState === 4 && xhr.status === 200) {
                                                                    var responseData = JSON.parse(xhr.responseText);
                                                                    var ul = document.createElement("ul");
                                                                    for (var i = 0; i < responseData.length; i++) {
                                                                        var donnee = responseData[i];
                                                                        var donneeSpliter = donnee.split(",");

                                                                        var ls = document.createElement("li");
                                                                        ls.className = "list-remove";
                                                                        ls.className = "d-flex";
                                                                        ls.className = "justify-content-between";
                                                                        ls.className = "align-items-center";

                                                                        var span = document.createElement("span");
                                                                        var icon = document.createElement("i");
                                                                        icon.className = "mdi";
                                                                        icon.className = "mdi-share";
                                                                        icon.className = "text-primary";
                                                                        span.appendChild(icon);
                                                                        span.textContent = donneeSpliter;

                                                                        ls.appendChild(span);

                                                                        var button = document.createElement("button");
                                                                        button.className = "little-button";
                                                                        button.className = "btn";
                                                                        button.className = "btn-gradient-danger";
                                                                        button.textContent = "X";

                                                                        ls.appendChild(button);
                                                                    }
                                                                    ul.appendChild(ls);
                                                                    taskCase.appendChild(ul);
                                                                } else {
                                                                    alert("Une erreur s'est produite lors de la récupération des données.");
                                                                }
                                                            };
                                                            var formData = "tache=" + encodeURIComponent(task);
                                                            xhr.send(formData);
                                                            return false;
                                                        });
                                                    });
                                                </script>
                                            </div>
                                            <div class="form-group tasks-list" id="taskCase">
                                            </div>
                                            <div class="form-group">
                                                <button class="btn btn-gradient-primary" id="validate">Valider</button>
                                            </div>
                                            <script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                    var boutonSubmit = document.getElementById("validate");
                                                    boutonSubmit.addEventListener("click", function (event) {
                                                        event.preventDefault();
                                                        var startDate = document.getElementById("startDate").value;
                                                        var endDate = document.getElementById("endDate").value;
                                                        var societe = document.getElementById("societe").value;
                                                        var experience = document.getElementById("experience").value;
                                                        var poste = document.getElementById("ancienPoste").value;
                                                        var xhr = new XMLHttpRequest();
                                                        xhr.open("POST", "/RessourceHumaine/ProfessionalCareerInsertionServlet", true);
                                                        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                                                        var careerCase = document.getElementById("careerCase");
                                                        xhr.onload = function () {
                                                            if (xhr.readyState === 4 && xhr.status === 200) {
                                                                var responseData = JSON.parse(xhr.responseText);

                                                                for (var i = 0; i < responseData.length; i++) {
                                                                    var startDate = responseData[i].startDate;
                                                                    var endDate = responseData[i].endDate;
                                                                    var societe = responseData[i].society;
                                                                    var poste = responseData[i].poste;
                                                                    var tasks = responseData[i].tasks;
                                                                    var carcase = document.createElement("div");
                                                                    carcase.className = "career";
                                                                    carcase.className = "my-3";

                                                                    var divFlex = document.createElement("div");
                                                                    divFlex.className = "d-flex";
                                                                    divFlex.className = "justify-content-between";

                                                                    var h5 = document.createElement("h5");
                                                                    h5.className = "career-date";

                                                                    h5.textContent = startDate + "-" + endDate;

                                                                    var icon = document.createElement("i");
                                                                    icon.classeName = "mdi", "mdi-settings", "little-to-left";

                                                                    divFlex.appendChild(h5);
                                                                    divFlex.appendChild(icon);

                                                                    var h5Societe = document.createElement("h5");
                                                                    h5Societe.className = "societe";
                                                                    h5Societe.textContent = societe;

                                                                    var h5Poste = document.createElement("h5");
                                                                    h5Poste.className = "poste";
                                                                    h5Poste.textContent = poste;

                                                                    var ul = document.createElement("ul");

                                                                    for (var e = 0; e < tasks.length; e++) {
                                                                        var li = document.createElement("li");
                                                                        li.textContent = tasks[e];
                                                                        ul.appendChild(li);
                                                                    }
                                                                    carcase.appendChild(divFlex);
                                                                    carcase.appendChild(h5Societe);
                                                                    carcase.appendChild(h5Poste);
                                                                    carcase.appendChild(ul);

                                                                    careerCase.appendChild(carcase);
                                                                }
                                                            } else {
                                                                alert("Une erreur s'est produite lors de la récupération des données.");
                                                            }
                                                        };
                                                        var formData = "startDate=" + encodeURIComponent(startDate) + "&poste=" + encodeURIComponent(poste) + "&endDate=" + encodeURIComponent(endDate) + "&societe=" + encodeURIComponent(societe) + "&poste=" + encodeURIComponent(poste) + "&experience=" + encodeURIComponent(experience);
                                                        xhr.send(formData);
                                                        return false;
                                                    });
                                                });
                                            </script>
                                            <div class="mt-5 d-flex justify-content-between half-width align-items-center">
                                                <div class="">
                                                    <a class="btn btn-block btn-gradient-primary font-weight-medium auth-form-btn"
                                                       href="">RETOUR</a>
                                                </div>
                                                <div class="">
                                                    <a class="btn btn-block btn-gradient-primary font-weight-medium auth-form-btn"
                                                       href="/RessourceHumaine/ProfessionalCareerInsertionServlet?finish">SUIVANT</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 px-5 py-3 career-preview"  id="careerCase">
                                        </div>

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