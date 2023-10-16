<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Diplome "%>
<%
    List<Diplome> listeDiplome = (List<Diplome>) request.getAttribute("listeDiplome");
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
        <link rel="stylesheet" href="./assets/css/candidature/professional_career_insertion.css">
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
                                <h4>Nous allons maintenant entrer vos formations</h4>
                                <h6 class="font-weight-light little-line-height">Entrer tous les formations que vous penser pertinent Ã  votre candidature</h6>
                                <form class="pt-3 form">
                                    <div class="form-group row align-items-center">
                                        <label for="diplomeSelect" class="form-label target-poste col-sm-2">Titulaire d'un diplome</label>
                                        <div class="col-sm-5">
                                            <select name="diplomeObtenu" id="diplomeSelect" class="form-control form-select">
                                                <% for(int i = 0;i<listeDiplome.size();i++) { %>
                                                <option value="<%= listeDiplome.get(i).getDiplome() %>"><%= listeDiplome.get(i).getDiplome() %></option>
                                                <% } %>                                            
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row mt-3">
                                        <div class="col-md-6">
                                            <hr>
                                            <div class="form-group row mt-5 align-items-center justify-content-between">
                                                <label for="date" class="col-sm-4 target-poste">AnnÃ©e</label>
                                                <div class="col-sm-8 d-flex align-items-center justify-content-between">
                                                    <input type="number" value="2023" class="form-control form-control-sm" name="annee" id="annee">
                                                </div>
                                            </div>
                                            <div class="form-group align-items-center row">
                                                <label for="societe" class="target-poste col-sm-4">Diplome ou certificat</label>
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Nom du diplome ou certificat" name="diplome" id="diplome">
                                                </div>
                                            </div>
                                            <div class="form-group align-items-center row">
                                                <label for="societe" class="target-poste col-sm-4">Ecole ou Institution</label>
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Le nom de votre Ã©cole ou institution" name="ecole" id="ecole">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <button class="btn btn-gradient-primary" id="validate">Valider</button>
                                            </div>
                                            <script>
                                                document.addEventListener("DOMContentLoaded", function () {
                                                    var boutonSubmit = document.getElementById("validate");
                                                    boutonSubmit.addEventListener("click", function (event) {
                                                        event.preventDefault();
                                                        var diplomeObtenu = document.getElementById("diplomeSelect").value;
                                                        var annee = document.getElementById("annee").value;
                                                        var diplome = document.getElementById("diplome").value;
                                                        var ecole = document.getElementById("ecole").value;
                                                        var xhr = new XMLHttpRequest();
                                                        xhr.open("POST", "/RessourceHumaine/FormationPathInsertionServlet", true);
                                                        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                                                        var careerCase = document.getElementById("careerCase");
                                                        careerCase.innerHTML = "";
                                                        xhr.onload = function () {
                                                            if (xhr.readyState === 4 && xhr.status === 200) {
                                                                var responseData = JSON.parse(xhr.responseText);
                                                                var diplome = responseData.diplome;
                                                                var diplomeName = diplome.diplome;

                                                                var formations = responseData.formations;
                                                                for (var i = 0; i < formations.length; i++) {
                                                                    var formation = formations[i];
                                                                    var year = formation.year;
                                                                    var formationDiplome = formation.diplome;
                                                                    var school = formation.school;

                                                                    var carcase = document.createElement("div");
                                                                    carcase.className = "career";
                                                                    carcase.className = "my-3";

                                                                    var divFlex = document.createElement("div");
                                                                    divFlex.className = "d-flex";
                                                                    divFlex.className = "justify-content-between";

                                                                    var h5 = document.createElement("h5");
                                                                    h5.className = "career-date";

                                                                    h5.textContent = year;
//
                                                                    var icon = document.createElement("i");
                                                                    icon.classeName = "mdi", "mdi-settings", "little-to-left";

                                                                    divFlex.appendChild(h5);
                                                                    divFlex.appendChild(icon);
//
                                                                    var h5Societe = document.createElement("h5");
                                                                    h5Societe.className = "societe";
                                                                    h5Societe.textContent = formationDiplome;

                                                                    var h5Poste = document.createElement("h5");
                                                                    h5Poste.className = "poste";
                                                                    h5Poste.textContent = school;



                                                                    carcase.appendChild(divFlex);
                                                                    carcase.appendChild(h5Societe);
                                                                    carcase.appendChild(h5Poste);

                                                                    careerCase.appendChild(carcase);
                                                                }
                                                            } else {
                                                                alert("Une erreur s'est produite lors de la récupération des données.");
                                                            }
                                                        };
                                                        var formData = "diplomeobtenu=" + encodeURIComponent(diplomeObtenu) + "&annee=" + encodeURIComponent(annee) + "&diplome=" + encodeURIComponent(diplome) + "&ecole=" + encodeURIComponent(ecole);
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
                                                       href="/RessourceHumaine/FormationPathInsertionServlet?in=1">SUIVANT</a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6 px-5 py-3 career-preview" id="careerCase">
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