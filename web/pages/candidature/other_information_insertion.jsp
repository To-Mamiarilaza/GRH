<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Insertion candidature</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="../../assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="../../assets/vendors/css/vendor.bundle.base.css">
        <link rel="stylesheet" href="../../assets/css/candidature/professional_career_insertion.css">
        <!-- endinject -->
        <!-- Plugin css for this page -->
        <!-- End plugin css for this page -->
        <!-- inject:css -->
        <!-- endinject -->
        <!-- Layout styles -->
        <link rel="stylesheet" href="../../assets/css/style.css">
        <!-- End layout styles -->
        <link rel="shortcut icon" href="../../assets/images/favicon.ico" />
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
                                <h4>Autre information pouvant être utile</h4>
                                <h6 class="font-weight-light little-line-height">Nous aimerons vous connaître un peu plus, alors présentez vous</h6>
                                <form class="pt-3 form" action="/RessourceHumaine/OtherInformationInsertionServlet">
                                    <div class="row mt-3">
                                        <div class="col-md-6">
                                            <hr>
                                            <div class="form-group mt-5 align-items-center row">
                                                <div class="col-sm-8">
                                                    <input type="text" class="form-control" placeholder="Vos centres d'int�r�ts" id="interetsInput">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button class="btn btn-gradient-primary" id="ajouterInteretButton">Ajouter</button>
                                                </div>
                                            </div>
                                            <div class="form-group tasks-list">
                                                <ul>
                                                </ul>
                                            </div>
                                        </div>
                                        <script>
                                            document.addEventListener("DOMContentLoaded", function () {
                                                var interetsInput = document.getElementById("interetsInput");
                                                var ajouterInteretButton = document.getElementById("ajouterInteretButton");
                                                var tasksList = document.querySelector(".tasks-list ul");

                                                ajouterInteretButton.addEventListener("click", function (event) {
                                                    event.preventDefault();

                                                    var interet = interetsInput.value.trim();
                                                    if (interet !== "") {
                                                        var listItem = document.createElement("li");
                                                        listItem.className = "list-remove d-flex justify-content-between align-items-center";
                                                        var st = '<span class="interet"> <i class="mdi mdi-share text-primary"></i>';
                                                        var ts = '</span> <button class="little-button btn btn-gradient-danger">X</button>';
                                                        listItem.innerHTML = st + interet + ts;


                                                        tasksList.appendChild(listItem);

                                                        interetsInput.value = "";

                                                        var interetString = "";

                                                        var interetElements = document.querySelectorAll(".interet");

                                                        interetElements.forEach(function (element, index) {
                                                            interetString += element.textContent;

                                                            if (index < interetElements.length - 1) {
                                                                interetString += " - ";
                                                            }
                                                        });
                                                        window.interetString = interetString;
                                                    }
                                                });
                                            });
                                        </script>


                                        <div class="form-group align-items-center row">
                                            <label for="salaire" class="target-poste col-sm-4">Prétention salariale</label>
                                            <div class="col-sm-8">
                                                <input type="number" id="salaire"  class="form-control" placeholder="Le salaire à la quelle vous penser atteindre vos valeurs" name="slaire">
                                            </div>
                                        </div>

                                        <div class="col-md-6 px-5 py-5 career-preview form-group">
                                            <label for="ambitions" class="target-poste form-label mb-4">Présenter vous et quelles sont vos ambitions</label>
                                            <textarea name="ambitions" id="ambitions" cols="30" rows="10" class="form-control"></textarea>
                                        </div>
                                    </div>
                                    <div class="mt-5 d-flex justify-content-between half-width align-items-center">
                                        <div class="">
                                            <a class="btn btn-block btn-gradient-primary font-weight-medium auth-form-btn"
                                               href="">RETOUR</a>
                                        </div>
                                        <div class="">
                                            <button class="btn btn-block btn-gradient-primary font-weight-medium auth-form-btn"
                                                    id="next">SUIVANT</button>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("next");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var interetString = window.interetString;
                                                var salaire = document.getElementById("salaire").value;
                                                var ambitions = document.getElementById("ambitions").value;
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/RessourceHumaine/OtherInformationInsertionServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.readyState === 4 && xhr.status === 200) {
                                                        window.location.href = "/RessourceHumaine/PreviewCandidatureServlet";
                                                    }
                                                };
                                                var formData = "salaire=" + encodeURIComponent(salaire) + "&ambitions=" + encodeURIComponent(ambitions) + "&interet=" + encodeURIComponent(interetString);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>
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
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../assets/js/off-canvas.js"></script>
    <script src="../../assets/js/hoverable-collapse.js"></script>
    <script src="../../assets/js/misc.js"></script>
    <!-- endinject -->
</body>

</html>