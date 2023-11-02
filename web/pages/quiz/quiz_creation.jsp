
<div class="page-header">
    <h3 class="page-title">
        <span class="page-title-icon bg-gradient-primary text-white me-2">
            <i class="mdi mdi-home"></i>
        </span> Création des questionnaires
    </h3>
</div>
<div class="row">
    <div class="col-lg-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="">Ces questionnaires sont utilisés pour évaluer les candidats pour une
                    poste</h4>
                <div class="form ">
                    <div class="form-group little-width mt-3">
                        <label for="" class="form-label">Nom du questionnaire</label>
                        <input type="text" id="quizName" class="form-control mt-2" placeholder="" required>
                    </div>

                    <div class="form-group little-width mt-3">
                        <label for="" class="form-label">Ajouter une question</label>
                        <div class="mt-2 row">
                            <div class="col-md-6">
                                <input type="text" id="questionInput"
                                       class="form-control form-control-sm"
                                       placeholder="Nouvelle Questionnaire ?"
                                       value="Comment t'appelle tu ?">
                            </div>
                            <div class="col-md-2">
                                <input type="number" id="noteInput"
                                       class="form-control form-control-sm" placeholder="Note"
                                       value="5">
                            </div>
                            <div class="col-md-4">
                                <button class="btn btn-gradient-primary form-control-sm"
                                        onclick="addNewQuestion()">Ajouter</button>
                            </div>
                        </div>
                        <p class="text-danger error-text" id="addQuestionErrorField"></p>
                    </div>

                    <div class="mt-5 quizzes-list d-flex" id="quizList">

                    </div>
                    <button class="btn btn-gradient-primary mt-4" onclick="saveNewQuiz()">Enregistrer questionnaire</button>
                </div>

            </div>
        </div>
    </div>
</div>
