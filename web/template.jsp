<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.List, model.requis.User, model.employe.Employe" %>
<% 
    User user = (User) session.getAttribute("user");
    Employe employe = Employe.getById(user.getIdEmploye());

    List<String> css = (List<String>) request.getAttribute("css");
    List<String> js = (List<String>) request.getAttribute("js");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>${requestScope.title}</title>

        <!-- plugins:css -->
        <link rel="stylesheet" href="./assets/vendors/mdi/css/materialdesignicons.min.css">
        <link rel="stylesheet" href="./assets/css/besoin/besoin-insertion.css">
        <link rel="stylesheet" href="./assets/vendors/css/vendor.bundle.base.css">

        <% for(String cssElement : css) { %>
        <link rel="stylesheet" href="<%= cssElement %>">
        <% } %>

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
                                    <img src="./photo_identity/<%= employe.getPhoto() %>" alt="image">
                                    <span class="availability-status online"></span>
                                </div>
                                <div class="nav-profile-text">
                                    <p class="mb-1 text-black"><%= user.getUsername() %></p>
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

                        <li class="nav-item nav-logout d-none d-lg-block">
                            <a class="nav-link" href="./login">
                                <i class="mdi mdi-power"></i>
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
                                    <img src="./photo_identity/<%= employe.getPhoto() %>" alt="profile">
                                    <span class="login-status online"></span>
                                    <!--change to offline or busy as needed-->
                                </div>
                                <div class="nav-profile-text d-flex flex-column">
                                    <span class="font-weight-bold mb-2"><%= user.getUsername() %></span>
                                    <span class="text-secondary text-small"><%= employe.getPoste() %></span>
                                </div>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./index.html">
                                <span class="menu-title">Mon Profil</span>
                                <i class="mdi mdi-account menu-icon"></i>
                            </a>
                        </li>

                        <% if(user.isAdmin()) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="./besoin-insertion">
                                <span class="menu-title">Envoie des besoins</span>
                                <i class="mdi mdi-account-multiple menu-icon"></i>
                            </a>
                        </li>
                        <% } %>

                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="collapse" href="#ui-basic-1" aria-expanded="false"
                               aria-controls="ui-basic">
                                <span class="menu-title">Gestion congés</span>
                                <i class="mdi mdi-calendar menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic-1">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="./CongesPersonnel">Mes congés</a></li>
                                        <% if(employe.getSubordonnesNb(null) != 0) { %>
                                    <li class="nav-item"> <a class="nav-link" href="./CongesSubordonneDemandeList">Demandes des subordonnes</a></li>
                                        <% } %>
                                        <% if(user.getService().getService().equals("Ressources humaines")) { %>
                                    <li class="nav-item"> <a class="nav-link" href="./CongesRHDemandeList">En attente de validation</a></li>
                                        <% } %>
                                </ul>
                            </div>
                        </li>

                        <% if(user.getService().getService().equals("Ressources humaines")) { %>
                        <li class="nav-item">
                            <a class="nav-link" data-bs-toggle="collapse" href="#ui-basic" aria-expanded="false"
                               aria-controls="ui-basic">
                                <span class="menu-title">Recrutements</span>
                                <i class="mdi mdi-contacts menu-icon"></i>
                            </a>
                            <div class="collapse" id="ui-basic">
                                <ul class="nav flex-column sub-menu">
                                    <li class="nav-item"> <a class="nav-link" href="listBesoins">Besoins
                                            des services</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="annonce">Annonce</a></li>
                                    <li class="nav-item"> <a class="nav-link" href="./PersonalInformationInsertionServlet">Insertion d'un candidature</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="./listCandidature">Réceptions des CV</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="./ReadyForQuizList">Prêts pour le tests</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="./quiz-results">Résultats des tests</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="./EntretientServlet">Programme d'entretien</a></li>
                                    <li class="nav-item"> <a class="nav-link"
                                                             href="./listCandidatRecrute">Embauchement</a></li>
                                </ul>
                            </div>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="./listPersonnel">
                                <span class="menu-title">Listes des employées</span>
                                <i class="mdi mdi-account-multiple menu-icon"></i>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="./RappelPeriode">
                                <span class="menu-title">Période de rappel</span>
                                <i class="mdi mdi-arrow-left-bold-circle-outline menu-icon"></i>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="./EtatPaie">
                                <span class="menu-title">Etat de paie</span>
                                <i class="mdi mdi-book-open menu-icon"></i>
                            </a>
                        </li>
                        <% } %>
                    </ul>
                </nav>

                <!-- partial -->
                <div class="main-panel">
                    <div class="content-wrapper">
                        <jsp:include page="${contentPage}" />
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
                <!-- main-panel ends -->
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
        <!-- Custom js for this page -->
        <% for(String jsElement : js) { %>
        <script src="<%= jsElement %>"></script>
        <% } %>
        <!-- End custom js for this page -->
    </body>

</html>

