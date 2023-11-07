<%@page import=" java.util.ArrayList "%>
<%@page import=" java.util.List "%>
<%@page import=" model.employe.ClasseEmploye "%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Validation contrat</title>
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
                                <h4>Validation contrat</h4>
                                <form class="pt-3" method="post" action="validerContrat">
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Classification nouveau employe :</label>
                                        <% if(request.getAttribute("classeEmployes") != null) { 
                                            ArrayList<ClasseEmploye> ce = (ArrayList<ClasseEmploye>)request.getAttribute("classeEmployes");
                                        %>
                                        <select name="id_classe_employe" id="location" class="form-control form-select">
                                            <% for(int i = 0; i < ce.size(); i++) { %>
                                            <option value="<%=ce.get(i).getIdClasseEmploye() %>"><%=ce.get(i).getClasseEmploye() %></option>
                                            <% } %>
                                        </select>
                                        <% } %>
                                    </div>       
                                    <div class="mt-3">
                                        <input type="submit" value="Valider" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
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