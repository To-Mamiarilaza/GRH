<%@page import="java.util.List, java.time.LocalDate, model.conge.Conge, model.conge.CongePersonnel" %>
<%
    List<Conge> congeList = (List<Conge>) request.getAttribute("congeList");
    
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Gestion des conges
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
                <h4 class="card-title">Listes des demandes de conges</h4>
                <div class="mt-4">
                    <div class="action-conges d-flex">
                        <div class="">
                            <a href="" class="btn btn-gradient-primary btn-sm"><i class="mdi mdi-calendar"></i></a>
                        </div>
                        <div class="mx-4">
                            <a href="./CongesSubordonneDemandeList?demande=1" class="btn btn-gradient-primary btn-sm same-width"> Demande de conges </a>
                        </div>
                        <div class="mx-4">
                            <a href="./CongesSubordonneDemandeList?refuse=1" class="btn btn-gradient-primary btn-sm same-width"> Conges refuse </a>
                        </div>
                        <div class="mx-4">
                            <a href="./CongesSubordonneDemandeList?valide=1" class="btn btn-gradient-primary btn-sm same-width"> Conges valide</a>
                        </div>
                        <div class="mx-4">
                            <a href="./CongesSubordonneDemandeList?current=1" class="btn btn-gradient-primary btn-sm same-width"> Conges en cours</a>
                        </div>
                    </div>
                    <h4 class="text-info mt-4"><%= (String) request.getAttribute("sectionTitle") %></h4>
                    <div class="list-conges mt-3">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Depot conges</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Personnel</th>
                                    <th>Poste</th>
                                    <th>Motif</th>
                                    <th>Etat</th>
                                    <th>Voir plus</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(Conge conge: congeList) { %>
                                    <tr>
                                        <td><%= conge.getDepositDate() %></td>
                                        <td><%= conge.getDateDebut() %></td>
                                        <td><%= conge.getDateFin() %></td>
                                        <td><%= conge.getFullName() %></td>
                                        <td><%= conge.getPersonnel().getPoste() %></td>
                                        <td><%= conge.getTypeConge().getTypeCongeName() %></td>
                                        <td><%= conge.getCongeEtat() %></td>
                                        <td><a href="./CongesChefDemandeDetail?idConge=<%= conge.getIdConge() %>"><i
                                                    class="mdi mdi-information-variant little-big-font"></i></a></td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>