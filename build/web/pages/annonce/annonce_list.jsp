<!-- <%@ page contentType="text/html; charset=UTF-8" %> -->
<%@page import=" java.util.ArrayList "%>
<%@page import=" model.annonce.Annonce "%>
<%@page import=" model.requis.Service "%>
<%@include file="../header.jsp" %>
            <!-- partial -->
            <div class="main-panel">
                <div class="content-wrapper">
                    <div class="page-header">
                        <h3 class="page-title">
                            <span class="page-title-icon bg-gradient-primary text-white me-2">
                                <i class="mdi mdi-home"></i>
                            </span> Les annonces en cours
                        </h3>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="">Listes des annoces en cours</h4>
                                    <form action="annonce" method="POST" class="form mt-3">
                                        <div class="form-group d-flex align-items-center mb-2">
                                            <p class="mb-0"><strong>Filtre par service</strong></p>
                                            <% if(request.getAttribute("services") != null) { 
                                                ArrayList<Service> services = (ArrayList<Service>)request.getAttribute("services");
                                            %>
                                            <div class="mx-4">
                                                <select name="service" id="" class="form-select">
                                                <% for(int i = 0; i < services.size(); i++) { %>
                                                    <option value="<%=services.get(i).getIdService() %>"><%=services.get(i).getService() %></option>
                                                <% } %>
                                                </select>
                                            </div>
                                            <% } %>
                                            <div class="mx-4">
                                                <button class="btn btn-outline-primary btn-sm">Filtrer</button>
                                            </div>
                                        </div>
                                    </form>
                                    
                                    <% if(request.getAttribute("annonces") != null) { 
                                        ArrayList<Annonce> annonces = (ArrayList<Annonce>)request.getAttribute("annonces");
                                        for(int i = 0; i < annonces.size(); i++) {
                                    %>
                                    <div class="row mt-3">
                                        <div class="col-md-3 p-3">
                                            <div class="relative-position">
                                                <button class="btn btn-danger bouton-suppr">X</button>
                                                <div class="besoin-information">
                                                    <p class="text-black"> <span class="mx-1">Date : <strong> <%=annonces.get(i).getDateAnnonce() %> </strong></span><span class="mx-1">Service : <strong> <%=annonces.get(i).getService().getService() %> </strong></span></p>
                                                </div>
                                                <div class="pdf-container">
                                                    <img src="./build/web/annonces/<%=annonces.get(i).getNomAnnonce() %>" width="100px" height="100px" class="pdf-image-view" alt="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <% } } %>
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
    <script src="../../assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../assets/js/off-canvas.js"></script>
    <script src="../../assets/js/hoverable-collapse.js"></script>
    <script src="../../assets/js/misc.js"></script>
    <!-- endinject -->
    <!-- Custom js for this page -->
    <!-- End custom js for this page -->
</body>

</html>