<!-- <%@ page contentType="text/html; charset=UTF-8" %> -->
<%@page import=" java.util.ArrayList "%>
<%@page import=" model.gestionBesoin.Task "%>
<%@page import=" model.gestionBesoin.WorkLoad "%>
<%@page import=" model.gestionBesoin.Besoin "%>
<%@include file="../header.jsp" %>

            <!-- partial -->
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="page-header">
                        <h3 class="page-title">
                            <span class="page-title-icon bg-gradient-primary text-white me-2">
                                <i class="mdi mdi-home"></i>
                            </span> Détail du service
                        </h3>
                    </div>
                    <% if(request.getAttribute("besoin") != null) { 
                        Besoin besoin = (Besoin)request.getAttribute("besoin");
                        ArrayList<Task> tasks = (ArrayList<Task>)request.getAttribute("tasks");
                        ArrayList<WorkLoad> workloads = (ArrayList<WorkLoad>)request.getAttribute("workloads");
                    %>
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Détail du Besoin</strong></h4>
                                    <p>Numero : 1</p>
                                    <p>Service : <%=besoin.getService().getService() %></p>
                                    <p>Date : <%=besoin.getCreationDate() %></p>

                                    <div class="little-part description mt-4">
                                        <h5 class="desc-title">Description du besoin</h5>
                                        <hr>
                                        <p><%=besoin.getDescription() %></p>
                                    </div>

                                    <div class="little-part row mt-4">
                                        <div class="col-md-6 grid-margin">
                                            <h5 class="desc-title">Listes des taches</h5>
                                            <hr>
                                            <ul class="mt-2">
                                                <% for(int i = 0; i < tasks.size(); i++) { %>
                                                <li><%=tasks.get(i).getTask() %></li>
                                                <% } %>
                                            </ul>
                                        </div>

                                        <div class="col-md-6 grid-margin">
                                            <h5 class="desc-title">Profil recherché</h5>
                                            <hr>
                                            <ul class="mt-2">
                                                <% for(int i = 0; i < workloads.size(); i++) { %>
                                                <li>
                                                    <div class="d-flex justify-content-between">
                                                        <label><%=workloads.get(i).getWantedProfile().getPoste() %></label>
                                                        <label><strong><%=workloads.get(i).getQuantity()+" "+ workloads.get(i).getUnity().getUnity() %></strong></label>
                                                    </div>
                                                </li>
                                                <% } %>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="little-part d-flex align-items-center">
                                        <a href="refusedBesoin?idBesoin=<%=besoin.getIdBesoin() %>" class="btn btn-gradient-danger">Refuser</a>
                                        <a href="annonce-export?idBesoin=<%=besoin.getIdBesoin() %>" class="btn btn-gradient-primary mx-5">Valider et générer annonce</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% } %>
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
    <!-- End custom js for this page -->
</body>

</html>