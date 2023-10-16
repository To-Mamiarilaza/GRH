document.addEventListener("DOMContentLoaded", function () {
    var formulaireDiplome = document.getElementById("diplomeFormulaire");
    formulaireDiplome.addEventListener("submit", function (event) {
        event.preventDefault();
        var diplome = document.getElementById("diplome").value;
        var diplomeNote = document.getElementById("note").value;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/RessourceHumaine/DiplomeServlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onload = function () {
            if (xhr.status === 200) {
                var donnees = JSON.parse(xhr.responseText);
                var listeDonneesDiv = document.getElementById("listeDiplomes");
                for (var i = 0; i < donnees.length; i++) {
                    var donnee = donnees[i];
                    var paragraphe = document.createElement("p");
                    paragraphe.textContent = donnee;
                    listeDonneesDiv.appendChild(paragraphe);
                }
            } else {
                alert("Erreur lors de la requête : " + xhr.status);
            }
        };

        // Envoyez les données au Servlet
        var formData = "diplome=" + encodeURIComponent(diplome) + "&diplomenote=" + encodeURIComponent(diplomeNote);
        xhr.send(formData);
    });
});