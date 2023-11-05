<%@page import=" java.util.ArrayList "%>
<%@page import=" model.candidature.Candidature "%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Commencer un contrat
    </h3>
</div>

<% if(request.getAttribute("candidatRecrute") != null) { 
    Candidature candidat = (Candidature)request.getAttribute("candidatRecrute");
%>

<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
            <% if(request.getAttribute("hasContrat") != null) { 
                boolean hasContrat = (boolean)request.getAttribute("hasContrat");
                boolean isContratValidate = false;
                if(request.getAttribute("isContratValidate") != null) {
                    isContratValidate = (boolean)request.getAttribute("isContratValidate");
                }
                if(hasContrat == false) { 
            %>
            <div class="little-part d-flex align-items-center">
                <a href="traitEmbauchement?idCandidat=<%=candidat.getIdCandidature() %>&idHelp=1" class="btn btn-gradient-danger">Refuser recrutement</a>
                <a href="contratEssai?idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-primary mx-5">Commencer contrat</a>
            </div>
            <% } 
                                    else if(hasContrat == true && isContratValidate==false){ %>
            <div class="little-part d-flex align-items-center">
                <a href="voirContrat?idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-primary">voir contrat</a>
                <a href="traitEmbauchement?idCandidat=<%=candidat.getIdCandidature() %>&idHelp=2" class="btn btn-gradient-danger mx-5">Refuser</a>
                <a href="traitEmbauchement?idCandidat=<%=candidat.getIdCandidature() %>&idHelp=3" class="btn btn-gradient-primary">valider contrat</a>
            </div>
            <%  } 
                                else if(hasContrat == true && isContratValidate == true) {  %>
            <div class="little-part d-flex align-items-center">
                <a href="voirContrat?idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-primary">voir contrat</a>
            </div>
            <% }
                                else { %>
            <p style="color: red">Erreur</p>
            <% }
                                } %>
                <h5 class="desc-title mt-5">A propos du candidat recrute</h5>
                <p>Nom : <%=candidat.getPersonnalInformation().getName() %></p>
                <p>prenom : <%=candidat.getPersonnalInformation().getFirstName() %></p>
                <p>Email : <%=candidat.getPersonnalInformation().getEmail() %></p>
                <p>Contact : <%=candidat.getPersonnalInformation().getTelephone() %></p>

                <div class="little-part description mt-4">
                    <h5 class="desc-title">Poste demande</h5>
                    <hr>
                    <p>Poste : <%=candidat.getWantedProfile().getPoste() %></p>
                </div>

                <div class="little-part description mt-4">
                    <h5 class="desc-title">Plus de details</h5>
                    <hr>
                    <a href="detailsCv?idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-primary">Voir cv</a>
                </div>
            </div>
        </div>
    </div>
</div>
<% } %>