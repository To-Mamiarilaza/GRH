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
                                    <% if(request.getAttribute("title") != null) { %>
                                    <h4 class=""><%=request.getAttribute("title") %></h4>
                                    <% } %>
                                    <% if(request.getAttribute("services") != null && request.getAttribute("wantedProfiles") != null) { 
                                        ArrayList<Service> services = (ArrayList<Service>)request.getAttribute("services");
                                        List<WantedProfile> wps = (List<WantedProfile>)request.getAttribute("wantedProfiles");
                                    %>
                                    <form action="candidatureRecruteFilter" method="post" class="form mt-3 row">
                                        <div class="col-md-3 form-group mb-2 px-4">
                                            <label for="" class="form-label">Poste</label>
                                            <select name="poste" id="" class="form-select form-control same-height">
                                                <option value="0">All</option>
                                                <% for(int i = 0; i < wps.size(); i++) { %>
                                                <option value="<%= wps.get(i).getIdWantedProfile() %>"><%=wps.get(i).getPoste() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                        <div class="col-md-3 form-group mb-2 px-4 d-flex align-items-end">
                                            <button type="submit" class="btn btn-gradient-primary same-height">Filtrer</button>
                                        </div>
                                            
                                        <div class="d-flex align-items-center">
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input checked name="status" value="6" id="recrute" type="radio" class="form-check-input"> Recrute </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="7" id="refusRecrutement" type="radio" class="form-check-input"> Refus recrutement </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="10" id="refusContrat" type="radio" class="form-check-input"> Refus contrat </label>
                                            </div>
                                            <div class="form-check form-check-flat form-check-primary mx-3">
                                                <label class="form-check-label">
                                                    <input name="status" value="20" id="valideContrat" type="radio" class="form-check-input"> Valide contrat </label>
                                            </div>
                                        </div>    
                                            
                                            
                                    </form>
                                    <% } %>
                                    <div class="row mt-3">
                                        <% if(request.getAttribute("candidatures") != null) { 
                                            ArrayList<Candidature> candidatures = (ArrayList<Candidature>)request.getAttribute("candidatures");
                                        %>
                                         <div class="table-overflow">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>Date recrutement</th>
                                                    <th>Nom et prenom</th>
                                                    <th>Poste demande</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <%  for(int i = 0; i < candidatures.size(); i++) { %>
                                                <tr>
                                                    <td><%=candidatures.get(i).getDepositDate() %></td>
                                                    <td><%=candidatures.get(i).getPersonnalInformation().getName() + " " + candidatures.get(i).getPersonnalInformation().getFirstName() %></td>
                                                    <td><%=candidatures.get(i).getWantedProfile().getPoste() %></td>
                                                    <% if(candidatures.get(i).getStatus() != 7) { %>
                                                    <td><a href="startContrat?id=<%=candidatures.get(i).getIdCandidature() %>" class="btn btn-gradient-primary btn-sm">contrat</a></td>
                                                    <% } %>
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