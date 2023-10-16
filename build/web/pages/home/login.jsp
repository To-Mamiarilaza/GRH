<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Mon entreprise</title>
        <!-- plugins:css -->
        <link rel="stylesheet" href="./assets/vendors/mdi/css/materialdesignicons.min.css">
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
                        <div class="col-lg-4 mx-auto">
                            <div class="auth-form-light text-left p-5">
                                <div class="d-flex align-items-center brand-logo" style="color: #da8cff;">
                                    <i class="mdi mdi-vector-arrange-above" style="font-size: 35px;margin-right: 25px;"></i>
                                    <h2 style="margin: 0;">Mon Entreprise</h2>
                                </div>
                                <h4>Bonjour! commençons maintenant </h4>
                                <h6 class="font-weight-light">S'authentifier pour continuer</h6>
                                <form class="pt-3" action="./login" method="POST">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username" name="username">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password" name="password">
                                    </div>

                                    <% 
                                      String loginError = (String) request.getAttribute("loginError");
                                      if (loginError != null) { %>
                                    <div class="mt-3">
                                        <p class="text-danger text-small"><%= loginError %></p>
                                    </div>
                                    <% } %>

                                    <div class="mt-3">
                                        <button class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" type="submit">Se connecter</button>
                                    </div>
                                    <div class="my-2 d-flex justify-content-between align-items-center">
                                        <div class="form-check">
                                            <label class="form-check-label text-muted">
                                                <input type="checkbox" class="form-check-input"> S'authentifier en tant qu'administrateur </label>
                                        </div>
                                    </div>
                                </form>
                            </div>
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