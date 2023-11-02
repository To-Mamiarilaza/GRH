<%@page import=" java.util.ArrayList "%>
<%@page import=" model.annonce.Annonce "%>
<%@page import=" model.requis.Service "%>

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
                                            
                                        <div class="d-flex align-items-center">
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="0" id="supprime" type="radio" class="form-check-input"> Annonce efface </label>
                                            </div>
                                        </div>
                                    </form>
                                    

                                    <div class="row mt-3">
                                    <% if(request.getAttribute("annonces") != null) { 
                                        ArrayList<Annonce> annonces = (ArrayList<Annonce>)request.getAttribute("annonces");
                                        for(int i = 0; i < annonces.size(); i++) {
                                    %>
                                        <div class="col-md-3 p-3">
                                            <div class="relative-position">
                                                <a href="removeAnnonce?idAnnonce=<%=annonces.get(i).getIdAnnonce() %>">
                                                    <button class="btn btn-danger bouton-suppr">X</button>
                                                </a>
                                                <div class="besoin-information">
                                                    <p class="text-black"> <span class="mx-1">Date : <strong> <%=annonces.get(i).getDateAnnonce() %> </strong></span><span class="mx-1">Service : <strong> <%=annonces.get(i).getService().getService() %> </strong></span></p>
                                                </div>
                                                <a href="annonce-export?idBesoin=<%=annonces.get(i).getBesoin().getIdBesoin() %>&idHelp=1">
                                                    <div class="pdf-container">
                                                        <img src="./annonces/<%=annonces.get(i).getNomAnnonce() %>" width="100px" height="100px" class="pdf-image-view" alt="">
                                                    </div>
                                                </a>                   
                                            </div>
                                        </div>
                                    <% } } %>
                                    </div>
                 
                                </div>
                            </div>
                        </div>
                    </div>