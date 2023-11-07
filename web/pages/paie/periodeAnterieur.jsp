<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.paie.rappelperiode.RappelPeriode, java.util.List, model.requis.Service" %>

<%
    List<RappelPeriode> rappels = (List<RappelPeriode>) request.getAttribute("rappels");
    
    int mois = (int) request.getAttribute("mois");
    int year = (int) request.getAttribute("year");
    
    List<Service> services = (List<Service>) request.getAttribute("services");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Periode anterieur
    </h3>
    <nav aria-label="breadcrumb">
        <ul class="breadcrumb">
            <li class="breadcrumb-item active" aria-current="page">
                <span></span>Overview <i
                    class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
            </li>
        </ul>
    </nav>
</div>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Gestion des periodes anterieurs</h4>
                <div class="row">
                    <div class="col-md-8">
                        <form action="./RappelPeriode" method="GET" class="mt-3">
                            <label for="" class="form-label">Filtrer le resultat par mois</label>
                            <div class="form-group row mt-3">
                                <div class="col-md-4">
                                    <input type="number" class="form-control form-control-sm" name="mois" value="<%= mois %>" placeholder="Mois">
                                </div>
                                <div class="col-md-4">
                                    <input type="number mx-5" class="form-control form-control-sm" name="year" value="<%= year %>" placeholder="Annee">
                                </div>
                                <div class="col-md-2">
                                    <input type="submit" class="btn btn-gradient-primary btn-md" value="Valider">
                                </div>
                            </div>
                        </form>

                        <div class="rappel-list row">
                            <% for(RappelPeriode rappel : rappels) { %>
                            <div class="rappel col-md-6 mb-4">
                                <p class="m-0 text-normal"><%= rappel.getDate() %></p>
                                <h4 class="mt-2 text-normale">Nombre de mois : <span
                                        class="mx-4 color-primary"><%= rappel.getNombreMois() %></span></h4>
                                <h5 class="mt-3 text-normal black-underline">Départements</h5>
                                <div class="row">
                                    <% for(Service service : rappel.getServices()) { %>
                                    <div class="col-md-6">
                                        <div class="form-check mt-2 form-check-flat form-check-primary">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" checked disabled>
                                                <%= service.getService() %> <i class="input-helper"></i></label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                            <% } %>
                        </div>

                    </div>
                    <div class="col-md-4 paie-element-insertion">
                        <h4 class="color-primary">Ajouter des rappels antérieur</h4>
                        <form action="./RappelPeriode" method="POST" class="form mt-3">
                            <div class="form-group">
                                <label for="" class="form-label">Nombre de mois</label>
                                <input type="number" name="nbMois" class="form-control mt-2">
                            </div>
                            <div class="form-group">
                                <label for="" class="form-label">Modification</label>
                                <input type="number" name="modification" class="form-control mt-2" step="0.5">
                            </div>
                            <div class="form-group">Départements</label>
                                <hr>
                                <div class="row">
                                    <% for(Service service : services) { %>
                                    <div class="col-md-6">
                                        <div class="form-check mt-2 form-check-flat form-check-primary">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" name="services" value="<%= service.getIdService() %>">
                                                <%= service.getService() %> <i class="input-helper"></i></label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-gradient-primary"
                                       value="Valider">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
