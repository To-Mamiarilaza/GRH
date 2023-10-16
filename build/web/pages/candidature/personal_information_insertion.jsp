<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Adresse "%>
<%@page import=" model.gestionProfile.WantedProfile "%>
<%
    List<WantedProfile> listePoste = (List<WantedProfile>) request.getAttribute("listePoste");
    List<Adresse> listeAdresse = (List<Adresse>) request.getAttribute("listeAdresse");
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
        <link rel="stylesheet" href="./assets/css/candidature/personal_information_insertion.css">
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
                        <div class="col-lg-5 mx-auto">
                            <div class="auth-form-light text-left p-5">
                                <div class="d-flex align-items-center brand-logo" style="color: #da8cff;">
                                    <i class="mdi mdi-vector-arrange-above" style="font-size: 35px;margin-right: 25px;"></i>
                                    <h3 style="margin: 0;">Huile de bongolava</h3>
                                </div>
                                <h4>Bienvenue ! Commençons maintenant </h4>
                                <h6 class="font-weight-light little-line-height">Pour postuler votre candidature, veillez remplir Ã©tape
                                    par étape tous les champs données.</h6>
                                <form class="pt-3" method="post" action="/RessourceHumaine/PersonalInformationInsertionServlet">
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Choisir le poste Ã  la quelle vous voulez postuler :</label>
                                        <select name="poste" id="" class="form-control form-select">
                                            <% for(int i = 0;i<listePoste.size();i++) { %>
                                            <option value="<%= listePoste.get(i).getIdWantedProfile() %>"><%= listePoste.get(i).getPoste() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Nom</label>
                                        <input type="text" class="form-control" id="exampleInputEmail1" name="nom">
                                    </div>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Prenom</label>
                                        <input type="text" class="form-control" id="exampleInputEmail1" name="prenom">
                                    </div>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Adresse email</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1"
                                               name="email">
                                    </div>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Numero telephone</label>
                                        <input type="text" class="form-control" id="exampleInputEmail1"
                                               name="contact">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="form-label labeled">Date de naissance</label>
                                        <input type="date" class="form-control" id="exampleInputEmail1"
                                               name="dateNaissance">
                                    </div>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Adresse</label>
                                        <select name="adresse" id="adresseSelect" class="form-control form-select">
                                            <% for(int i = 0;i<listeAdresse.size();i++) { %>
                                            <option value="<%= listeAdresse.get(i).getAdresse() %>"><%= listeAdresse.get(i).getAdresse() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="d-flex align-items-center">
                                        <div class="form-check">
                                            <label class="form-check-label text-muted">
                                                <input type="radio" class="form-check-input" name="sexe" value="1"> Homme</label>
                                        </div>
                                        <div class="form-check mx-5">
                                            <label class="form-check-label text-muted">
                                                <input type="radio" class="form-check-input" name="sexe" value="2"> Femme</label>
                                        </div>
                                    </div>
                                    <div class="mt-3">
                                        <input type="submit" value="SUIVANT" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
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