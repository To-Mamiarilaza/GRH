<%@page import=" model.requis.Service "%>
<%@page import=" model.gestionBesoin.Besoin "%>
<%@page import=" java.util.ArrayList "%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>

            <!-- partial -->
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="page-header">
                        <h3 class="page-title">
                            <span class="page-title-icon bg-gradient-primary text-white me-2">
                                <i class="mdi mdi-home"></i>
                            </span> Besoin des services
                        </h3>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="">Listes des besoins reçue des services</h4>
                                    <% if(request.getAttribute("services") != null) {
                                        ArrayList<Service> services = (ArrayList<Service>)request.getAttribute("services");
                                    %>
                                    <form action="./listBesoins" method="POST" class="form mt-3">
                                        <div class="form-group d-flex align-items-center mb-2">
                                            <p class="mb-0"><strong>Filtre par service</strong></p>
                                            <div class="mx-4">
                                                <select name="service" id="service" class="form-select">
                                                    <% for(int i = 0; i < services.size(); i++) { %>
                                                    <option value="<%=services.get(i).getIdService() %>"><%=services.get(i).getService() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                            <div class="mx-4">
                                                <button type="submit" class="btn btn-outline-primary btn-sm">Filtrer</button>
                                            </div>
                                        </div>
                                        <div class="d-flex align-items-center">
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="1" id="nonVisite" type="radio" class="form-check-input"> Non visité </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="2" id="enAttente" type="radio" class="form-check-input"> En attente </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="5" id="refuse" type="radio" class="form-check-input"> Refusé </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="10" id="valide" type="radio" class="form-check-input"> Validé </label>
                                            </div>
                                        </div>
                                    </form>
                                    <% } 
                                    if(request.getAttribute("allBesoin") != null) {
                                        ArrayList<Besoin> allBesoins = (ArrayList<Besoin>)request.getAttribute("allBesoin");
                                    %>
                                    <div class="table-overflow">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Date</th>
                                                    <th>Service</th>
                                                    <th>Description</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <% for(int i = 0; i < allBesoins.size(); i++) { %>
                                                <tr>
                                                    <td><%=allBesoins.get(i).getCreationDate() %></td>
                                                    <td><%=allBesoins.get(i).getService().getService() %></td>
                                                    <td><%=allBesoins.get(i).getDescription() %></td>
                                                    <td><a href="detailsBesoin?idBesoin=<%=allBesoins.get(i).getIdBesoin() %>" class="btn btn-gradient-primary btn-sm">Voir plus</a></td>
                                                </tr>
                                            <% } %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
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