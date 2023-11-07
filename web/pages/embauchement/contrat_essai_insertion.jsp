<%@page import=" java.util.ArrayList "%>
<%@page import=" java.util.List "%>
<%@page import=" model.embauchement.WorkLocation "%>
<%@page import=" model.embauchement.Province "%>
<%@page import=" model.gestionProfile.WantedProfile "%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Contrat travail</title>
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
                                <h4>Bienvenue ! Commençons un contrat</h4>
                                <h6 class="font-weight-light little-line-height">Pour lier un contrat avec nous, vous allez devoir remplir
                                les formulaires ci-desous correctement</h6>
                                <form class="pt-3" method="post" action="contratTravail">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="form-label labeled">Date commencement</label>
                                        <input type="date" class="form-control" id="exampleInputEmail1"
                                               name="start_date">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="form-label labeled">Date fin</label>
                                        <input type="date" class="form-control" id="exampleInputEmail1"
                                               name="end_date">
                                    </div>
                                    <hr>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Montant salaire</label>
                                        <input type="number" class="form-control" id="exampleInputEmail1" name="salary">
                                    </div>
                                   
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Lieu de travail</label>
                                        <% if(request.getAttribute("workLocations") != null) { 
                                            ArrayList<WorkLocation> workLocations = (ArrayList<WorkLocation>)request.getAttribute("workLocations");
                                        %>
                                        <select name="work_location" id="location" class="form-control form-select">
                                            <% for(int i = 0; i < workLocations.size(); i++) { %>
                                            <option value="<%=workLocations.get(i).getIdWorkLocation() %>"><%=workLocations.get(i).getName() %></option>
                                            <% } %>
                                        </select>
                                        <% } %>
                                    </div>       
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Fait a : </label>
                                        <% if(request.getAttribute("provinces") != null) { 
                                            ArrayList<Province> provinces = (ArrayList<Province>)request.getAttribute("provinces");
                                        %>
                                        <select name="id_province" id="location" class="form-control form-select">
                                            <% for(int i = 0; i < provinces.size(); i++) { %>
                                            <option value="<%=provinces.get(i).getIdProvince() %>"><%=provinces.get(i).getName() %></option>
                                            <% } %>
                                        </select>
                                        <% } %>
                                    </div> 
                                    <% if(request.getAttribute("wantedProfiles") != null) { 
                                        List<WantedProfile> wps = (List<WantedProfile>)request.getAttribute("wantedProfiles");
                                    %>
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Poste: </label>
                                        <select name="id_wanted_profile" id="location" class="form-control form-select">
                                            <% for(int i = 0; i < wps.size(); i++) { %>
                                            <option value="<%=wps.get(i).getIdWantedProfile() %>"><%=wps.get(i).getPoste() %></option>
                                            <% } %>
                                        </select>
                                    </div> 
                                    <% } %>
                                    <div class="mt-3">
                                        <input type="submit" value="Suivant" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
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