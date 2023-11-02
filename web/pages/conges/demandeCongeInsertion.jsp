<%@page import="java.util.List, model.conge.TypeConge" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    List<TypeConge> typeConges = (List<TypeConge>) request.getAttribute("typeConges");
%>
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Demande de conges
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

<div class="row flex-grow">
    <div class="col-lg-6 grid-margin mx-auto stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Demande de congés</h4>
                <p class="card-description">
                    Votre demande de congés sera pris en charge par votre chef hiérarchique ensuite par le responsable RH.
                    Nous vous notifions si une réponse est arrivée.
                </p>
                <form action="./DemandeConges" class="form" method="POST">
                    <div class="form-group">
                        <label for="" class="form-label">Explications</label>
                        <input type="text" name="explication" class="form-control form-control-sm">
                    </div>
                    <div class="form-group">
                        <label for="" class="form-label">Type de conges</label>
                        <select name="idTypeConge" id="" class="form-select form-control-sm">
                            <% for(TypeConge typeConge : typeConges) { %>
                            <option value="<%= typeConge.getIdTypeConge() %>"><%= typeConge.getTypeCongeName() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="" class="form-label">Date début</label>
                        <input type="date" name="dateDebut" class="form-control form-control-sm">
                    </div>
                    <div class="form-group">
                        <label for="" class="form-label">Date fin</label>
                        <input type="date" name="dateFin" class="form-control form-control-sm">
                    </div>
                    <% if(request.getAttribute("error") != null) { %>
                    <p class="text-danger mt-3 text-small"><%= ((Exception) request.getAttribute("error")).getMessage() %></p>
                    <% } %>
                    <div class="form-group">
                        <input type="submit" value="Envoyer une demande de conges" class="btn btn-gradient-primary">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
