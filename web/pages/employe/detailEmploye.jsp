<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.employe.Employe, java.util.List" %>

<%
    Employe employe = (Employe) request.getAttribute("employe");
    List<Employe> subordonnes = (List<Employe>) request.getAttribute("subordonnes");
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Detail de l'employee
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
                <div class="inside-link">
                    <ul class="horizontal-list no-style-list">
                        <li><a href="./Prime?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Prime</a> </li>
                        <li><a href="./HeureSupplementaire?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Heure supplementaire</a></li>
                        <li><a href="./VenteConge?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Vente conges</a></li>
                        <li><a href="./Abscence?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Abscence</a></li>
                        <li><a href="./Avance?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Demande d'avance</a></li>
                        <li><a href="./FichePaie?idEmploye=<%= employe.getIdEmploye() %>" class="btn btn-outline-primary btn-sm">Fiche de paie</a></li>
                    </ul>
                </div>
                <hr>
                <h4 class="card-title"><%= employe.getFullName() %></h4>
                <div class="row">

                    <div class="col-md-6">
                        <div class="row info-person mt-3">
                            <div class="col-md-5">
                                <h3 class="text-small color-primary">Photo de profile</h3>
                                <img class="profile-pic mt-2" src="./photo_identity/<%= employe.getPhoto() %>"
                                     alt="Profile picture">
                            </div>
                            <div class="col-md-7">
                                <h4 class="text-md">Information personnel</h4>
                                <div class="form-group">
                                    <label class="form-label">Nom et prenom</label>
                                    <input type="text" class="form-control form-control-sm" value="<%= employe.getFullName() %>"  readonly>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">Date de naissance</label>
                                            <input type="text" class="form-control form-control-sm" value="<%= employe.getDateNaissance() %>" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form-label">Telephone</label>
                                            <input type="text" class="form-control form-control-sm" value="<%= employe.getTelephone() %>" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="form-label">Adresse</label>
                                    <input type="text" class="form-control form-control-sm" value="<%= employe.getAdresse() %>" readonly>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <h4 class="color-primary text-small">Poste de</h4>
                                <h4 class=""><%= employe.getPoste() %></h4>
                            </div>
                            <div class="col-md-12 mt-3">
                                <h4 class="color-primary underline">Missions et attributions</h4>
                                <ul>
                                    <li>Accomplir toutes les taches dans l'entreprise</li>
                                    <li>Respecter toutes les lois regissant a l'interieur du societe</li>
                                    <li>Assurer la mission specifique de chaque departement</li>
                                </ul>
                                <h4 class="color-primary underline">Competences et aptitudes requises</h4>
                                <ul class="no-style-list">
                                    <li><i class="mdi mdi-check list-style-i color-primary"></i>Avoir une belle capacite de communication</li>
                                    <li><i class="mdi mdi-check list-style-i color-primary"></i>A l'ecoute de ces collaborateurs et savoir travailler en equipe</li>
                                    <li><i class="mdi mdi-check list-style-i color-primary"></i>Les competences necessaires prerequis dans la demande du departement</li>

                                </ul>
                                <h4 class="color-primary underline">Son superieur</h4>
                                <div class="employe-list">
                                    <% if(employe.getSuperieur().getContrat() != null) { %>
                                    <div class="employe">
                                        <div class="img">
                                            <img src="./photo_identity/<%= employe.getSuperieur().getPhoto() %>" alt="">
                                        </div>
                                        <div class="name">
                                            <%= employe.getSuperieur().getFullName() %>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                                <h4 class="color-primary underline">Ces subordonnees</h4>
                                <div class="employe-list">
                                    <% for(Employe sub : subordonnes) { %>
                                    <div class="employe">
                                        <div class="img">
                                            <img src="./photo_identity/<%= sub.getPhoto() %>" alt="">
                                        </div>
                                        <div class="name">
                                            <%= sub.getFullName() %>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                            <div class="col-md-12 d-flex align-items-center">
                                <div>
                                    <a href="" class="btn btn-gradient-primary">Licencier</a>
                                </div>
                                
                                <% if(false) { %>
                                <div class="div">
                                    <p class="my-0 text-md color-primary licenciement-information">
                                        <i class="mx-3 mdi mdi-information-outline"></i> Sera
                                        licencie dans 2 mois</p>
                                </div>
                                <% } %>
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 contrat">
                        <h4 class="card-title">CONTRAT DE TRAVAIL</h4>
                        <hr>
                        <img style="width: 100%" src="./contratEssais/<%= employe.getContrat().getPath() %>.png" alt="Image du contrat"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

