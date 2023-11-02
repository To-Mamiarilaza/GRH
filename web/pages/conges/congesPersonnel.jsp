<%@page import="java.util.List, java.time.LocalDate, model.conge.Conge, model.conge.CongePersonnel, model.conge.Personnel" %>
<%
    List<Conge> congeList = (List<Conge>) request.getAttribute("congeList");
    CongePersonnel congePersonnelInfo = (CongePersonnel) request.getAttribute("congePersonnelInfo");
    Conge currentConge = congePersonnelInfo.getCurrentConge();
    Personnel personnel = congePersonnelInfo.getPersonnel();
    
    LocalDate currentCongeDebut = null;
    LocalDate currentCongeFin = null;
    String motif = null;
    
    if(currentConge != null) {
        currentCongeDebut = currentConge.getDateDebutReel() != null ? currentConge.getDateDebutReel() : currentConge.getDateDebutDemande();
        currentCongeFin = currentConge.getDateFinReel() != null ? currentConge.getDateFinReel() : currentConge.getDateFinDemande();
        motif = currentConge.getTypeConge().getTypeCongeName();
    }
    
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Gestion des cong�s
    </h3>
    <nav aria-label="breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <span></span>Overview <i class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
        </ul>
    </nav>
</div>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Mes conges</h4>
                <div class="current-conges mt-3">
                    <label for="" class="form-label">Conge en cours</label>
                    <div class="row mt-3">
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Date debut</label>
                            <input type="text" value="<% if(currentCongeDebut == null) { out.print("..."); } else { out.print(currentCongeDebut); } %>" class="form-control form-control-sm" readonly>
                        </div>
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Date fin</label>
                            <input type="text" value="<% if(currentCongeFin == null) { out.print("..."); } else { out.print(currentCongeFin); } %>" class="form-control form-control-sm" readonly>
                        </div>
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Motif</label>
                            <input type="text" value="<% if(motif == null) { out.print("..."); } else { out.print(motif); } %>" class="form-control form-control-sm" readonly>
                        </div>
                    </div>
                </div>
                <div class="reste-solde mt-4 mb-3">
                    <p>SOLDE RESTANT : <span class="nb-jour"><%= congePersonnelInfo.getSoldeRestant() %> jour</span></p>
                </div>
                <hr>
                <div class="mt-4">
                    <div class="action-conges row">
                        <div class="col-md-4">
                            <a href="./DemandeConges" class="btn btn-gradient-primary"> Envoyer demande de
                                conges</a>
                        </div>
                        <div class="col-md-4">
                            <a href="./CongesPersonnel?demande=1" class="btn btn-gradient-primary"> Mes demandes de conges</a>
                        </div>
                        <div class="col-md-4">
                            <a href="./CongesPersonnel" class="btn btn-gradient-primary"> Historiques de conges</a>
                        </div>
                    </div>
                    <div class="list-conges mt-4">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Depot cong�s</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Motif</th>
                                    <th>Etat</th>
                                    <th>Info</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Conge conge: congeList) { %>
                                <% 
                                    String chefName = conge.getChefHierarchique().getNom() + " " + conge.getChefHierarchique().getPrenom();
                                    String rhName = conge.getResponsableRH().getNom() + " " + conge.getResponsableRH().getPrenom();
                                %>
                                <tr>
                                    <td><%= conge.getDepositDate() %></td>
                                    <td><%= conge.getDateDebut() %></td>
                                    <td><%= conge.getDateFin() %></td>
                                    <td><%= conge.getTypeConge().getTypeCongeName() %></td>
                                    <td><%= conge.getCongeEtat() %></td>
                                    <td><a href="#" onclick="updateInfoModal('<%= chefName %>', '<%= conge.getRemarqueChef() %>', '<%= rhName %>', '<%= conge.getRemarqueRH() %>')" data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                class="mdi mdi-information-variant little-big-font"></i></a></td>
                                    <% if(conge.getEtat() == 2) { %>
                                    <td><a  href="./AnnuleDemande?idConge=<%= conge.getIdConge() %>" class="text-danger little-big-font"><i
                                                class="mdi mdi-minus-circle-outline"></i></a></td>
                                    <% } else { %>
                                    <td><a href="#" class="text-black little-big-font disabled"><i
                                                class="mdi mdi-minus-circle-outline"></i></a></td>
                                    <% } %>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>

                        <!-- Modal to show validateur informations -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Remarque des validateurs
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p class="validateur text-primary" id="chefName">RAKOTOARISOLO maela</p>
                                        <p class="content" id="chefRemarque">
                                            Mila tafaverina aloha ihany ianao fa mila anao izahay
                                        </p>
                                        <hr>
                                        <p class="validateur text-primary" id="rhName">ZOKY Seanona</p>
                                        <p class="content" id="rhRemarque">
                                            Manaova fety sambatra ary e !
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>