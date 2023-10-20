<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-calendar"></i>
        </span> Gestion des congés
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
                <h4 class="card-title">Mes conges</h4>
                <div class="current-conges mt-3">
                    <label for="" class="form-label">Conge en cours</label>
                    <div class="row mt-3">
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Date debut</label>
                            <input type="text" value="10/12/23" class="form-control form-control-sm" readonly>
                        </div>
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Date fin</label>
                            <input type="text" value="13/12/23" class="form-control form-control-sm" readonly>
                        </div>
                        <div class="col-md-3">
                            <label for="" class="form-label mb-3">Motif</label>
                            <input type="text" value="Repos médicale" class="form-control form-control-sm" readonly>
                        </div>
                    </div>
                </div>
                <div class="reste-solde mt-4 mb-3">
                    <p>SOLDE RESTANT : <span class="nb-jour">30 jour</span></p>
                </div>
                <hr>
                <div class="mt-4">
                    <div class="action-conges row">
                        <div class="col-md-4">
                            <a href="" class="btn btn-gradient-primary"> Envoyer demande de
                                conges</a>
                        </div>
                        <div class="col-md-4">
                            <a href="" class="btn btn-gradient-primary"> Mes demandes de conges</a>
                        </div>
                        <div class="col-md-4">
                            <a href="" class="btn btn-gradient-primary"> Historiques de conges</a>
                        </div>
                    </div>
                    <div class="list-conges mt-4">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Depot congés</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Motif</th>
                                    <th>Etat</th>
                                    <th>Info</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>10/12/23</td>
                                    <td>25/12/23</td>
                                    <td>30/12/23</td>
                                    <td>Vacance de noel</td>
                                    <td>En attente</td>
                                    <td><a href="" data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                class="mdi mdi-information-variant little-big-font"></i></a></td>
                                    <td><a href="" class="text-danger little-big-font"><i
                                                class="mdi mdi-minus-circle-outline"></i></a></td>
                                </tr>
                                <tr>
                                    <td>10/12/23</td>
                                    <td>25/12/23</td>
                                    <td>30/12/23</td>
                                    <td>Vacance de noel</td>
                                    <td>En attente</td>
                                    <td><a href="" data-bs-toggle="modal" data-bs-target="#exampleModal"><i
                                                class="mdi mdi-information-variant little-big-font"></i></a></td>
                                    <td><a href="" class="text-danger little-big-font"><i
                                                class="mdi mdi-minus-circle-outline"></i></a></td>
                                </tr>
                            </tbody>
                        </table>

                        <!-- Modal to show validateur informations -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                            aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Note des validateurs
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <p class="validateur text-primary">RAKOTOARISOLO maela</p>
                                        <p class="content">
                                            Mila tafaverina aloha ihany ianao fa mila anao izahay
                                        </p>
                                        <hr>
                                        <p class="validateur text-primary">ZOKY Seanona</p>
                                        <p class="content">
                                            Manaova fety sambatra ary e !
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>