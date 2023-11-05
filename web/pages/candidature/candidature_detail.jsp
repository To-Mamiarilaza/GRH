<%@page import=" model.candidature.Candidature "%>

<% 
    Candidature candidat = (Candidature) request.getAttribute("candidat");
%>

<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Detail du candidature
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
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-md-6 p-5">
                <img src="./candidatures/<%=candidat.getCandidaturePNG() %>"
                     alt="" class="candidature-image">
            </div>  
            <div class="col-md-6 p-5">
                <h6 class="card-title">Les piéces jointes avec le candidature</h6>
                <form action="" class="form mt-4">
                    <div class="form-group mt-4">
                        <label class="form-label">Photo d'identite</label>
                        <div class="">
                            <a href="" class="btn btn-gradient-primary mt-3">Télécharger la photo</a>
                        </div>
                    </div>
                    <div class="form-group mt-4">
                        <label>Dossier des justificatives</label>
                        <div class="">
                            <a href="" class="btn btn-gradient-primary mt-3">Télécharger les dossiers</a>
                        </div>
                    </div>
                </form>
                <hr>
                <div class="">
                    <p>Apres avoir analyser la candidature, prenez votre décision maintenant. <br> Nous envoyons le lien d'access au test si vous validez sa candidature</p>
                </div>
                <div class="d-flex mt-4">
                    <a href="TraitStatusCandidature?status=0&idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-danger">Refuser</a>
                    <a href="TraitStatusCandidature?status=1&idCandidat=<%=candidat.getIdCandidature() %>" class="btn btn-gradient-success mx-5">Accepter</a>
                </div>
            </div>
        </div>
    </div>
</div>