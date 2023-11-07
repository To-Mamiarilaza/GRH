<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.paie.fiche.FichePaie, model.paie.fiche.ElementPaie, model.util.NombresEnLettres, model.paie.fiche.Retenue, java.util.List, java.time.LocalDate" %>

<%
    int idEmploye = (int) request.getAttribute("idEmploye");
    FichePaie fichePaie = (FichePaie) request.getAttribute("fichePaie");
    
    LocalDate dateDebut = (LocalDate) request.getAttribute("dateDebut");
    LocalDate dateFin = (LocalDate) request.getAttribute("dateFin");
%>


<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Fiche de paie
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
                <h2 class="big-title color-primary mb-4 underline">FICHE DE PAIE</h2>
                <div class="row">
                    <div class="col-md-8 mb-5">
                        <form action="./FichePaie" method="GET" class="form">
                            <input type="hidden" name="idEmploye" value="<%= idEmploye %>" />
                            <div class="row align-items-end">
                                <div class="col-md-4">
                                    <label class="form-label">Date debut : </label>
                                    <input type="date" class="form-control form-control-sm" name="dateDebut" value="<%= dateDebut %>">
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">Date fin : </label>
                                    <input type="date" class="form-control form-control-sm" name="dateFin" value="<%= dateFin %>">
                                </div>
                                <div class="col-md-3">
                                    <input type="submit" class="btn btn-success btn-md" value="Valider">
                                </div>
                            </div>
                        </form>
                    </div>
                                <div class="col-md-4 mb-5"></div>
                    
                    <div class="col-md-6">
                        <dl class="row">
                            <dt class="col-sm-4">Nom et prenom :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getFullName() %></dd>

                            <dt class="col-sm-4">Numero matricule :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getNumMatricule() %></dd>

                            <dt class="col-sm-4">Fonction :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getPoste() %></dd>

                            <dt class="col-sm-4">Date d'embauche :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getDateEmbauche() %></dd>

                            <dt class="col-sm-4">Ancienneté</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getAnciennete() %></dd>

                            <dt class="col-sm-4">N° CNAPS</dt>
                            <dd class="col-sm-7"></dd>
                        </dl>
                    </div>
                    <div class="col-md-6">
                        <dl class="row">
                            <dt class="col-sm-4">Classification :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getEmploye().getClasseEmploye().getClasseEmploye() %></dd>

                            <dt class="col-sm-4">Salaire de base :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getSalaireBaseString() %></dd>

                            <dt class="col-sm-4">Taux journaliers :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getTauxJournalierString() %></dd>

                            <dt class="col-sm-4">Taux horraires :</dt>
                            <dd class="col-sm-7"><%= fichePaie.getTauxHorairesString() %></dd>
                        </dl>
                    </div>
                </div>
                <div class="row">
                    <table class="table">
                        <thead>
                            <tr class="table-primary">
                                <td>Désignations</td>
                                <td>Nombre</td>
                                <td>Taux</td>
                                <td>Montant</td>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Ligne des elements de paie -->
                            <% for(ElementPaie element : fichePaie.getElementPaieList()) { %>
                            <tr>
                                <td><%= element.getDesignation() %></td>
                                <td><%= element.getNombre() %></td>
                                <td class="argent-left"><%= element.getTauxString() %></td>
                                <td class="argent-left"><%= element.getMontantString() %></td>
                            </tr>
                            <% } %>

                            <!-- Ligne montrant le total-->
                            <tr>
                                <td colspan="2"></td>
                                <td><strong>Salaire brut</strong></td>
                                <td><%= fichePaie.getSalaireBruteString() %></td>
                            </tr>

                            <tr class="table-primary">
                                <td colspan="4"></td>
                            </tr>

                            <!-- Ligne montrant Orgaisme -->
                            <% for(Retenue retenue : fichePaie.getRetenueOrganisationList()) { %>
                                <tr>
                                    <td></td>
                                    <td><%= retenue.getDesignation() %></td>
                                    <td><%= retenue.getTaux() %> %</td>
                                    <td class="argent-left"><%= retenue.getMontantString() %></td>
                                </tr>
                            <% } %>
                            <!-- Total Organisme -->
                            <tr>
                                <td colspan="2"></td>
                                <td><strong>Total Organisme</strong></td>
                                <td class="argent-left"><%= fichePaie.getTotalRetenueOrganisationString() %></td>
                            </tr>

                            <!-- Ligne montrant IRSA -->
                            <% for(Retenue retenue : fichePaie.getRetenueIRSAList()) { %>
                                <tr>
                                    <td></td>
                                    <td><%= retenue.getDesignation() %></td>
                                    <td><%= retenue.getTaux() %>%</td>
                                    <td class="argent-left"><%= retenue.getMontantString() %></td>
                                </tr>
                            <% } %>
                            
                            <!-- Total IRSA -->
                            <tr>
                                <td colspan="2"></td>
                                <td><strong>Total IRSA</strong></td>
                                <td class="argent-left"><%= fichePaie.getTotalIRSAString() %></td>
                            </tr>

                            <tr class="table-primary">
                                <td colspan="4"></td>
                            </tr>

                            <!-- Total -->
                            <tr>
                                <td colspan="2"></td>
                                <td><strong>Total des retenues</strong></td>
                                <td class="argent-left"><span class="text-danger"><%= fichePaie.getTotalRetenueString() %></span></td>
                            </tr>
                            <tr>
                                <td colspan="2"></td>
                                <td><strong>Net à payer</strong></td>
                                <td class="argent-left"><span class="text-success"><%= fichePaie.getNetAPayerString() %></span></td>
                            </tr>

                        </tbody>
                    </table>
                    <dl class="row mt-4">
                        <dt class="col-sm-2">Mode de payement :</dt>
                        <dd class="col-sm-9">Chèque ou virement</dd>

                        <dt class="col-sm-2">Somme arrête a :</dt>
                        <dd class="col-sm-9"><%= NombresEnLettres.nombreEnLettres((int)fichePaie.getNetAPayer()) + " ARIARY" %></dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>
</div>
