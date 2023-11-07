<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.paie.fiche.EtatPaie, model.paie.fiche.FichePaie, java.util.List, java.time.LocalDate" %>

<%
    EtatPaie etatPaie = (EtatPaie) request.getAttribute("etatPaie");
    
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
                <h4 class="card-title">ETAT DE PAIE</h4>
                <div class="row">
                    <div class="col-md-10">
                        <form action="./EtatPaie" method="GET" class="mt-3">
                            <label class="form-label">Periode données</label>
                            <div class="form-group row mt-3">
                                <div class="col-md-4">
                                    <input type="date" class="form-control form-control-sm" value="<%= dateDebut %>" name="dateDebut">
                                </div>
                                <div class="col-md-4">
                                    <input type="date" class="form-control form-control-sm" value="<%= dateFin %>" name="dateFin">
                                </div>
                                <div class="col-md-2">
                                    <input type="submit" class="btn btn-gradient-primary btn-md"
                                           value="Valider">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-12">
                        <div class="etat-paie-display" >
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <td>Debut</td>
                                        <td>Fin</td>
                                        <td>N° Matricule</td>
                                        <td>N° CNAPS</td>
                                        <td>Nom et prenoms</td>
                                        <td>Date d'embauche</td>
                                        <td>Abscence</td>
                                        <td>Categorie</td>
                                        <td>Fonction</td>
                                        <td>Salaire de Base</td>
                                        <td>Retenue sur abscence</td>
                                        <td>Indemnites</td>
                                        <td>Rappel</td>
                                        <td>Autres</td>
                                        <td>Heure Supplementaire</td>
                                        <td>Salaire brute</td>
                                        <td>CNAPS 1%</td>
                                        <td>CNAPS 8%</td>
                                        <td>OSTIE 1%</td>
                                        <td>OSTIE 5%</td>
                                        <td>Revenue imposable</td>
                                        <td>Impot</td>
                                        <td>IGR NET</td>
                                        <td>Total retenue</td>
                                        <td>Salaire NET</td>
                                        <td>Avance</td>
                                        <td>Net a payer</td>
                                        <td>Net du mois</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(FichePaie fiche : etatPaie.getFiches()) { %>
                                        <tr>
                                            <td><%= dateDebut %></td>
                                            <td><%= dateFin %></td>
                                            <td> <a class="link-no-style" href="./FichePaie?idEmploye=<%= fiche.getEmploye().getIdEmploye() %>&dateDebut=<%= dateDebut %>&dateFin=<%= dateFin %>"><%= fiche.getEmploye().getNumMatricule() %></a></td>
                                            <td><%= fiche.getEmploye().getNumCNAPS() %></td>
                                            <td><%= fiche.getEmploye().getFullName() %></td>
                                            <td><%= fiche.getEmploye().getDateEmbauche() %></td>
                                            <td><%= fiche.getNombreAbscence() %></td>
                                            <td><%= fiche.getEmploye().getClasseEmploye().getClasseEmploye() %></td>
                                            <td><%= fiche.getEmploye().getPoste() %></td>
                                            <td class="argent"><%= fiche.getSalaireBaseString() %></td>
                                            <td class="argent"><%= fiche.getRetenueAbscenceTotalString() %></td>
                                            <td class="argent"><%= fiche.getTotalIndemniteString() %></td>
                                            <td class="argent"><%= fiche.getMontantRappelTotalString() %></td>
                                            <td></td>
                                            <td class="argent"><%= fiche.getMontantHeureSupTotalString() %></td>
                                            <td class="argent"><%= fiche.getSalaireBruteString() %></td>
                                            <td class="argent"><%= fiche.getCnaps1String() %></td>
                                            <td class="argent"><%= fiche.getCnaps8String() %></td>
                                            <td class="argent"><%= fiche.getOstie1String() %></td>
                                            <td class="argent"><%= fiche.getOstie5String() %></td>
                                            <td class="argent"><%= fiche.getMontantImposableString() %></td>
                                            <td class="argent"><%= fiche.getMontantImpotTotalString() %></td>
                                            <td></td>
                                            <td class="argent"><%= fiche.getTotalRetenueString() %></td>
                                            <td class="argent"><%= fiche.getSalaireNetString() %></td>
                                            <td class="argent"><%= fiche.getTotalAvanceString() %></td>
                                            <td class="argent"><%= fiche.getNetAPayerString() %></td>
                                            <td class="argent"><%= fiche.getNetAPayerString() %></td>
                                        </tr>
                                    <% } %>

                                    <!-- Ligne pour afficher les total -->
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="argent"><%= etatPaie.getSalaireBaseTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getRetenueAbscenceTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getTotalIndemniteString() %></td>
                                        <td class="argent"><%= etatPaie.getRappelTotalString() %></td>
                                        <td></td>
                                        <td class="argent"><%= etatPaie.getHeureSupplementaireTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getSalaireBruteTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getCnaps1TotalString() %></td>
                                        <td class="argent"><%= etatPaie.getCnaps8TotalString() %></td>
                                        <td class="argent"><%= etatPaie.getOstie1TotalString() %></td>
                                        <td class="argent"><%= etatPaie.getOstie5TotalString() %></td>
                                        <td class="argent"><%= etatPaie.getRevenueImposableTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getImpotTotalString() %></td>
                                        <td></td>
                                        <td class="argent"><%= etatPaie.getRetenueTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getSalaireNETTotalString() %></td>
                                        <td class="argent"><%= etatPaie.getAvanceTotalString() %></td>
                                        <td class="text-danger argent"><%= etatPaie.getNetAPayerTotalString() %></td>
                                        <td class="text-danger argent"><%= etatPaie.getNetAPayerTotalString() %></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
