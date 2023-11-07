<%@page import="java.util.List, model.requis.User, java.time.LocalDate, model.conge.Conge, model.conge.CongePersonnel, model.employe.Employe" %>
<%
    Conge conge = (Conge) request.getAttribute("conge");
    CongePersonnel congePerso = (CongePersonnel) request.getAttribute("congePersonnel");
    Employe personnel = congePerso.getPersonnel();
    User user = (User) request.getSession().getAttribute("user");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Detail d'un conges
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
                <div class="row">
                    <div class="col-md-6 p-3">
                        <div class="bordered px-4 py-3" style="height: 100%;">
                            <h4 class="my-0">Fiche de poste</h4>
                            <div class="section mt-3">
                                <p class="text-primary mb-2">Information personnelle</p>
                                <ul class="list-espaced">
                                    <li><strong>Nom :</strong> <span class="mx-2"><%= personnel.getNom() %></span></li>
                                    <li><strong>Prenom :</strong><span class="mx-2"><%= personnel.getPrenom() %></span></li>
                                    <li><strong>Date Naissance :</strong><span class="mx-2"><%= personnel.getDateNaissance() %></span></li>
                                    <li><strong>Telephone :</strong><span class="mx-2"><%= personnel.getTelephone() %></span></li>
                                </ul>
                            </div>
                            <div class="section mt-3">
                                <p class="text-primary mb-2">POSTE DE : <strong class="text-black mx-3"><%= personnel.getPoste() %></strong></p>
                            </div>
                            <div class="section mt-3">
                                <p class="text-primary mb-2">Missions et attributions</p>
                                <ul class="list-espaced">
                                    <li>Accomplir toutes les taches dans l'entreprise</li>
                                    <li>Respecter toutes les lois regissant a l'interieur du societe</li>
                                    <li>Assurer la mission spécifique de chaque département</li>
                                </ul>
                            </div>
                            <div class="section mt-3">
                                <p class="text-primary mb-2">Compétences et aptitudes requises</p>
                                <ul class="list-espaced">
                                    <li>Avoir une belle capacité de communication</li>
                                    <li>A l'ecoute de ces collaborateurs et savoir travailler en equipe</li>
                                    <li>Les compétences nécessaires prérequis dans la demande du département</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 p-3">
                        <div class="bordered detail-conges px-4 py-2">
                            <div class="mt-3">
                                <h4 class="">Demande de congés</h4>
                                <p class="mb-2">Jour restant : <span class="mx-3 text-black"><%= congePerso.getSoldeRestant() %> jour</span></p>
                                <p class="mb-2">Type de congés : <span class="mx-3 text-black"><%= conge.getTypeConge().getTypeCongeName() %></span></p>
                                <p class="mb-2">Date debut : <span class="mx-3 text-black"><%= conge.getDateDebut() %></span></p>
                                <p class="mb-2">Date fin : <span class="mx-3 text-black"><%= conge.getDateFin() %></span></p>
                                <div class="explication mt-3">
                                    <p class="mb-2 text-primary">Explications :</p>
                                    <p><%= conge.getExplication() %></p>
                                </div>
                            </div>
                        </div>
                        <div class="bordered detail-conges px-4 py-2 mt-3">
                            <form class="form mt-3" action="./ValidationChefConge" method="POST">
                                <input type="hidden" name="idResponsable" value="<%= user.getIdEmploye() %>">
                                <input type="hidden" name="idConge" value="<%= conge.getIdConge() %>">
                                <h4 class="">Validation du demande</h4>
                                <div class="form-group mt-3">
                                    <label for="" class="form-label">Laisser votre remarque</label>
                                    
                                    <% if(conge.getEtat() != 2) { %>
                                    <textarea name="remarque" id="" cols="30" rows="5"
                                              class="form-control mt-2" readonly> <%= conge.getConvenientRemarque() %></textarea>
                                    <% } else { %>
                                        <textarea name="remarque" id="" cols="30" rows="5"
                                        class="form-control mt-2"> </textarea>
                                    <% } %>
                                </div>
                                
                                <% if(conge.getEtat() == 2) { %>
                                <div class="d-flex mb-3">
                                    <div class="">
                                        <button type="submit" name="refuser"
                                            class="btn btn-gradient-primary">Refuser</button>
                                    </div>
                                    <div class="mx-5">
                                        <button type="submit" name="valider"
                                            class="btn btn-gradient-primary">Valider</button>
                                    </div>
                                </div>
                                <% } else { %>
                                <div class="d-flex mb-3">
                                    <div class="">
                                        <button type="submit" name="refuser"
                                                class="px-5 btn btn-gradient-primary disabled" disabled><%= conge.getCongeEtat() %></button>
                                    </div>
                                </div>
                                <% } %>
                                
                                <hr>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>