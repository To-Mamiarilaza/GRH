<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import=" model.employe.Employe "%>
<%@page import=" java.util.ArrayList "%>

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
                                <h4>Vous etes a deux doigts de creer un contrat avec nous </h4>
                                <h6 class="font-weight-light little-line-height">Veuillez remplir correctement les formulaires ci-dessous
                                par des vrais informations</h6>
                                <form class="pt-3" method="post" action="contratPdf">
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Periode d'essai en mois :</label>
                                        <input type="number" class="form-control" id="exampleInputEmail1" name="periode_essai">
                                    </div>
                                    <div class="d-flex align-items-center">                             
                                        <label for="exampleInputEmail1" class="form-label labeled">Est affilie a CNAPS :</label>
                                        <div class="form-check form-check-flat form-check-primary mx-3">                                            
                                            <label class="form-check-label">
                                            <input name="is_cnaps" value="1" id="nonVisite" type="radio" class="form-check-input"> oui </label>
                                        </div>
                                        <div class="form-check form-check-flat form-check-primary mx-3">
                                            <label class="form-check-label">
                                            <input name="isCnaps" value="0" id="is_cnaps" type="radio" class="form-check-input"> Non </label>
                                        </div>                
                                        </div>   
                                    <div class="mt-3">
                                    <div class="d-flex align-items-center">
                                        <label for="exampleInputEmail1" class="form-label labeled">Est affilie a un organisme sanitaire :</label>
                                        <div class="form-check form-check-flat form-check-primary mx-3">                       
                                            <label class="form-check-label">
                                            <input name="is_sanitaire" value="1" id="nonVisite" type="radio" class="form-check-input"> oui </label>
                                        </div>
                                        <div class="form-check form-check-flat form-check-primary mx-3">
                                            <label class="form-check-label">
                                            <input name="is_sanitaire" value="0" id="enAttente" type="radio" class="form-check-input"> Non </label>
                                        </div>                
                                    </div>     
                                    <div class="form-group">
                                        <label for="adresseSelect" class="form-label labeled">Superieur :</label>
                                        <% if(request.getAttribute("employes") != null) { 
                                            ArrayList<Employe> employes = (ArrayList<Employe>)request.getAttribute("employes");
                                        %>
                                        <select name="superieur" id="location" class="form-control form-select">
                                            <% for(int i = 0; i < employes.size(); i++) { %>
                                            <option value="<%=employes.get(i).getIdEmploye() %>"><%=employes.get(i).getContrat().getCandidature().getPersonnalInformation().getName() +" "+employes.get(i).getContrat().getCandidature().getPersonnalInformation().getFirstName() %> - <%=employes.get(i).getContrat().getPoste().getPoste() %></option>
                                            <% } %>
                                        </select>
                                        <% } %>
                                    </div>     
                                    <div class="mt-3">
                                        <input type="submit" value="Generer un contrat" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
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