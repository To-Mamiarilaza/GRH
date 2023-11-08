<%@page import=" java.util.ArrayList "%>
<%@page import=" java.util.List "%>
<%@page import=" model.candidature.Candidature "%>
<%@page import=" model.employe.Employe "%>
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
                                    <h4 class="">Listes des personnels</h4>
                                    
                                    <form class="pt-3" method="post" action="traitListePersonnal">
                                        <div class="form-group">
                                            <label for="adresseSelect" class="form-label labeled">Nom ou matricule de l'employe :</label>
                                            <div class="col-md-5 mb-3">
                                                <input type="text" class="form-control same-height form-control-sm" id="exampleInputEmail1" name="search_emp">
                                            </div>
                                            <input type="submit" value="Rechercher" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
                                            <input type="hidden" value="1" name="id_help" />
                                        </div>
                                    </form>
                                    
                                    
                                    <% if(request.getAttribute("services") != null && request.getAttribute("postes") != null) { 
                                        ArrayList<Service> services = (ArrayList<Service>)request.getAttribute("services");
                                        List<WantedProfile> wps = (List<WantedProfile>)request.getAttribute("postes");
                                    %>
                                    <form action="traitListePersonnal" method="post" class="form mt-3 row">
                                        <div class="col-md-3 form-group mb-2 px-4">
                                            <label for="" class="form-label">Service</label>
                                            <select name="service" id="" class="form-select form-control same-height">
                                                <% for(int i = 0; i < services.size(); i++) { %>
                                                <option value="<%=services.get(i).getIdService() %>"><%=services.get(i).getService() %></option>
                                                <% } %>
                                            </select>
                                        </div>
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
                                        <% if(request.getAttribute("employes") != null) { 
                                        List<Employe> employes = (List<Employe>)request.getAttribute("employes");
                                        %>
                                        <div class="table-overflow">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Num matricule</th>
                                                        <th>Nom et prenom</th>
                                                        <th>Date naissance</th>
                                                        <th>Date d'embauche</th>
                                                        <th>Genre</th>
                                                        <th>direction</th>
                                                        <th>Fonction</th>
                                                        <th></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for(int i = 0; i < employes.size(); i++) { %>
                                                    <tr>
                                                        <td><%=employes.get(i).getNumMatricule() %></td>
                                                        <td><%=employes.get(i).getContrat().getCandidature().getPersonnalInformation().getName() +" "+employes.get(i).getContrat().getCandidature().getPersonnalInformation().getFirstName() %></td>
                                                        <td><%=employes.get(i).getContrat().getCandidature().getPersonnalInformation().getBirthDate() %></td>
                                                        <td><%=employes.get(i).getDateEmbauche() %></td>
                                                        <td><%=employes.get(i).getContrat().getCandidature().getPersonnalInformation().getSexe().getSexe() %></td>
                                                        <td><%=employes.get(i).getContrat().getCandidature().getWantedProfile().getService().getService() %></td>
                                                        <td><%=employes.get(i).getContrat().getPoste().getPoste() %></td>
                                                        <td><a href="./DetailEmploye?idEmploye=<%=employes.get(i).getIdEmploye() %>" class="btn btn-gradient-primary btn-sm">Voir plus</a></td>
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