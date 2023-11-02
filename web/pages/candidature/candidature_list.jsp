<%@page import=" java.util.ArrayList "%>
<%@page import=" java.util.List "%>
<%@page import=" model.candidature.Candidature "%>
<%@page import=" model.requis.Service "%>
<%@page import=" model.gestionProfile.WantedProfile "%>

                    <div class="page-header">
                        <h3 class="page-title">
                            <span class="page-title-icon bg-gradient-primary text-white me-2">
                                <i class="mdi mdi-home"></i>
                            </span> Listes des candidatures
                        </h3>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 grid-margin stretch-card">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="">Listes des candidatures reçue</h4>
                                    <% if(request.getAttribute("services") != null && request.getAttribute("wantedProfiles") != null) { 
                                        ArrayList<Service> services = (ArrayList<Service>)request.getAttribute("services");
                                        List<WantedProfile> wps = (List<WantedProfile>)request.getAttribute("wantedProfiles");
                                    %>
                                    <form action="candidatureFilter" method="post" class="form mt-3 row">
                                        <div class="col-md-3 form-group mb-2 px-4">
                                            <label for="" class="form-label">Poste</label>
                                            <select name="poste" id="" class="form-select form-control same-height">
                                                <% for(int i = 0; i < wps.size(); i++) { %>
                                                <option value="<%= wps.get(i).getIdWantedProfile() %>"><%=wps.get(i).getPoste() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-md-3 form-group mb-2 px-4 d-flex align-items-end">
                                            <button type="submit" class="btn btn-gradient-primary same-height">Filtrer</button>
                                        </div>
                                    </form>
                                    <% } %>
                                    <div class="row mt-3">
                                        <% if(request.getAttribute("candidatures") != null) { 
                                            ArrayList<Candidature> candidatures = (ArrayList<Candidature>)request.getAttribute("candidatures");
                                            for(int i = 0; i < candidatures.size(); i++) {
                                        %>
                                        <div class="col-md-3 p-3">
                                            <a href="candidatureDetail?idCandidature=<%=candidatures.get(i).getIdCandidature() %>">
                                                <div class="relative-position">
                                                    <div class="besoin-information">
                                                        <p class="text-black"> <span class="mx-1">Date : <strong> <%=candidatures.get(i).getDepositDate() %> </strong></span><span class="mx-1">Service : <strong> <%=candidatures.get(i).getWantedProfile().getService().getService() %> </strong></span></p>
                                                    </div>
                                                    <div class="pdf-container">
                                                        <img src="./candidatures/2023-01-23_Jean_Couturier_Informatique_candidature.png" class="pdf-image-view" alt="">
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                                      <% } } %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>