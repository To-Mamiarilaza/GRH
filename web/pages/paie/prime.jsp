<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.paie.prime.PrimeEmploye, java.util.List, model.paie.prime.Prime" %>

<%
    int idEmploye = (int) request.getAttribute("idEmploye");
    List<PrimeEmploye> primes = (List<PrimeEmploye>) request.getAttribute("primes");
    
    List<Prime> typesPrime = (List<Prime>) request.getAttribute("typesPrime");
    
    int mois = (int) request.getAttribute("mois");
    int year = (int) request.getAttribute("year");
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
                        <li><a href="./Prime?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Prime</a> </li>
                        <li><a href="./HeureSupplementaire?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Heure supplementaire</a></li>
                        <li><a href="./VenteConge?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Vente conges</a></li>
                        <li><a href="./Abscence?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Abscence</a></li>
                        <li><a href="./Avance?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Demande d'avance</a></li>
                        <li><a href="./FichePaie?idEmploye=<%= idEmploye %>" class="btn btn-outline-primary btn-sm">Fiche de paie</a></li>
                    </ul>
                </div>
                <hr>
                <h4 class="card-title">GESTION DES PRIMES</h4>
                <div class="row">
                    <div class="col-md-8">
                        <form action="./Prime" method="GET" class="mt-3">
                            <label for="" class="form-label">Filtrer le resultat par mois</label>
                            <div class="form-group row mt-3">
                                <input type="hidden" name="idEmploye" value="<%= idEmploye %>"/>
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

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <td>Date</td>
                                    <td>Type de prime</td>
                                    <td>Montant</td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for(PrimeEmploye prime : primes) { %>
                                <tr>
                                    <td><%= prime.getDatePrime() %></td>
                                    <td><%= prime.getPrime().getPrime() %></td>
                                    <td><%= prime.getMontant() %> Ar</td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4 paie-element-insertion">
                        <h4 class="color-primary">Attribuer un prime</h4>
                        <form action="./Prime" method="POST" class="form mt-3">
                            <input type="hidden" name="idEmploye" value="<%= idEmploye %>"/>
                            <div class="form-group">
                                <label for="" class="form-label">Type de prime</label>
                                <select name="prime" id="" class="form-select form-control-sm  mt-2">
                                    <% for(Prime type : typesPrime) { %>
                                    <option value="<%= type.getIdPrime() %>"><%= type.getPrime() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="" class="form-label">Montant</label>
                                <input type="text" name="montant" class="form-control mt-2" placeholder="20 000 Ar">
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-gradient-primary" value="Valider">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

