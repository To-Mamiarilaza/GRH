
function getQuestionTemplate(question, point) {
    return `
    <div class="quiz col-md-5 p-3">
        <div
            class="quiz-header d-flex justify-content-between align-items-center">
            <h4 class="quiz-title">` + question + `<span
                    class="mx-4">( ` + point + ` pts )</span> </h4>
            <button onclick="deleteQuestion(this)"><i class="mdi mdi-bookmark-remove"></i></button>
        </div>
        <hr>
        <div class="answer-list">
            <div class="list">
                
            </div>
            <div
                class="answer answer-input d-flex justify-content-between align-items-center">
                <div class="form-check">
                    <label class="form-check-label">
                        <input type="checkbox" class="form-check-input checkbox">
                    </label>
                    <input type="text" class="answer-insertion">
                </div>
                <div class="remove">
                    <button class="remove-button btn btn-outline-primary" onclick="addNewAnswer(this)">
                        <i class="mdi mdi-check"></i>
                    </button>
                </div>
            </div>
        </div>
    </div> 
    `
}

function getAnswerTemplate(answer, checkState) {
    return `
    <div class="answer d-flex justify-content-between align-items-center">
        <div class="form-check">
            <label class="form-check-label">
            <input type="checkbox" onchange="changeAnswerValue(this)" class="form-check-input" ` + checkState + `> ` + answer + ` </label>
        </div>
        <div class="remove">
            <button class="remove-button btn btn-outline-primary" onclick="deleteThisAnswer(this)">X</button>
        </div>
    </div>`;
}

function renderJsStyle() {

    (function ($) {
        'use strict';
        $(function () {

            //checkbox and radios
            $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');

            //fullscreen
            $("#fullscreen-button").on("click", function toggleFullScreen() {
                if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
                    if (document.documentElement.requestFullScreen) {
                        document.documentElement.requestFullScreen();
                    } else if (document.documentElement.mozRequestFullScreen) {
                        document.documentElement.mozRequestFullScreen();
                    } else if (document.documentElement.webkitRequestFullScreen) {
                        document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
                    } else if (document.documentElement.msRequestFullscreen) {
                        document.documentElement.msRequestFullscreen();
                    }
                } else {
                    if (document.cancelFullScreen) {
                        document.cancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                }
            })
        });
    })(jQuery);
}

// Fonction pour valider la création d'un quiz
function saveNewQuiz() {
    var quizNameInput = $("#quizName");
    var quizName = quizNameInput.val();

    if (quizName.trim() == "") {
        quizNameInput.attr("placeholder", "Ce champ ne doit pas être null !");
        return;
    } else {
        // changer le valeur dans le session
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/GRH/save-quiz',
            data: {
                quizName: quizName
            },
            dataType: 'json',
            success: function (response) {
                if (response.error != null) {
                    console.log(response);
                } else {
                    window.location.href = "http://localhost:8081/GRH/quiz-create";
                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    }
}

// Fonction pour changer l'état d'une réponse
function changeAnswerValue(checkbox) {
    // requisition des données nécéssaire
    var newValue = $(checkbox).is(":checked") ? 1 : 0;
    var idAnswer = $(checkbox).closest(".answer").attr("id").split("-")[1];
    var idQuestion = $(checkbox).closest(".quiz").attr("id").split("-")[1];

    // changer le valeur dans le session
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8081/GRH/change-answer-value',
        data: {
            idAnswer: idAnswer,
            idQuestion: idQuestion,
            newValue: newValue
        },
        dataType: 'json',
        success: function (response) {

        },
        error: function (response) {

        }
    });

}

// Fonctino pour supprimer une réponse
function deleteThisAnswer(bouton) {
    // requisition des données nécéssaire
    var idAnswer = $(bouton).closest(".answer").attr("id").split("-")[1];
    var idQuestion = $(bouton).closest(".quiz").attr("id").split("-")[1];
    var answer = $(bouton).closest(".answer");

    // Ensuite supprimé du session
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/GRH/delete-answer',
        data: {
            idAnswer: idAnswer,
            idQuestion: idQuestion
        },
        success: function (reponse) {
            answer.remove();
        },
        error: function () {
            alert("Une erreur est survenue lors du suppression !");
        }
    })

}

// Envoie de l'AJAX Permettant d'enregistrer le nouveau answer
function saveNewAnswer(answerValue, checkedValue, idQuestion, answerTemplate) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8081/GRH/add-answer',
        data: {
            answer: answerValue,
            value: checkedValue,
            question: idQuestion
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                answerTemplate.remove();
            } else {
                var idAnswer = response.idAnswer;
                answerTemplate.attr('id', 'answer-' + idAnswer);        // Redonner l'ID aux nouvelles éléments
            }
        },
        error: function () {
            alert("Une erreur est survenue lors de l\'envoie");
        }
    });
}

// fonction pour ajouter une nouvelle réponse
function addNewAnswer(bouton) {
    var answerInput = $(bouton).closest('.answer').find(".answer-insertion");
    var answerCheckValue = $(bouton).closest('.answer').find(".checkbox").is(":checked");

    var newCheckState = answerCheckValue ? "checked" : "";
    var answer = answerInput.val();

    if (answer.trim() == "") {
        answerInput.css({ "border-bottom": "solid 1px red" });
        return;
    }

    // Ajout du nouveau template de réponse dans la liste
    answerInput.css({ "border-bottom": "solid 1px #b66dff" });
    var answerList = $(bouton).closest('.answer-list').find(".list");
    var answerTemplate = $(getAnswerTemplate(answer, newCheckState));
    answerList.append(answerTemplate);
    answerInput.val("");

    // Envoyer une nouvelle requete Ajax
    var checkedValue = answerCheckValue ? 1 : 0;
    var idQuestion = $(bouton).closest('.quiz').attr("id").split("-")[1];
    saveNewAnswer(answer, checkedValue, idQuestion, answerTemplate);

    renderJsStyle();
}

// fonction pour ajouter une nouvelle question
function addNewQuestion() {
    var questionValue = $('#questionInput').val();
    var noteValue = $('#noteInput').val();

    // add template to the quiz container
    var quizList = $('#quizList');
    var quizTemplate = $(getQuestionTemplate(questionValue, noteValue));
    quizList.append(quizTemplate);

    // envoyer une ajax vers le controller
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8081/GRH/add-new-question',
        data: {
            question: questionValue,
            note: noteValue
        },
        dataType: 'json',
        success: function (response) {
            if (response.error != null) {
                quizTemplate.remove();
                $('#addQuestionErrorField').text(response.error);
            } else {
                $('#addQuestionErrorField').text("");
                var idQuestion = response.idQuestion;
                quizTemplate.attr('id', 'question-' + idQuestion);        // Redonner l'ID aux nouvelles éléments
            }
        },
        error: function (response) {
            console.log(response);
        }
    });

    renderJsStyle();
}

// fonction pour supprimé une question
function deleteQuestion(bouton) {
    var quiz = bouton.closest('.quiz');
    var id = quiz.id.split("-")[1];

    // Ensuite supprimé du session
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8081/GRH/delete-question',
        data: {
            idQuestion: id
        },
        success: function (reponse) {
            quiz.remove();
        },
        error: function () {
            alert("Une erreur est survenue lors du suppression !");
        }
    })
}