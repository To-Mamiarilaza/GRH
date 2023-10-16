document.addEventListener("DOMContentLoaded", function () {
    var boutonOuvrirModal = document.getElementById("profileModal");
    var donneeCard = document.getElementById("donneesSauvegarde");
    var profilListe = document.getElementById("profile-list");

    boutonOuvrirModal.addEventListener("click", function () {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", "/RessourceHumaine/ListeProfileServlet", true);
        xhr.setRequestHeader("Content-Type", "application/json");


        xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {

                var jsonResponse = JSON.parse(xhr.responseText);

                var ListePoste = jsonResponse.listePost;
                var BestDiplomeNote = jsonResponse.listeDiplomeNote;
                var BestAdresseNote = jsonResponse.listeAdresseNote;
                var BestSexeNote = jsonResponse.listeSexeNote;
                var BestExperienceNote = jsonResponse.listeExperienceNote;
                var BestSalaireNote = jsonResponse.listeSalaireNote;

                for (var i = 0; i < BestDiplomeNote.length; i++) {
                    var profileCard = document.createElement("div");

                    profileCard.classList.add("profile-card");

                    var removeFloating = document.createElement("div");
                    removeFloating.classList.add("remove-floating");

                    var removeIcon = document.createElement("i");
                    removeIcon.classList.add("remove", "mdi", "mdi-close-circle-outline");

                    var profileTitle = document.createElement("h5");
                    profileTitle.classList.add("profile-title");
                    profileTitle.textContent = ListePoste[i];

                    var ul = document.createElement("ul");

                    var profileDiplome = document.createElement("li");
                    profileDiplome.classList.add("profile-diplome");
                    profileDiplome.textContent = BestDiplomeNote[i].diplome.diplome;
                    ul.appendChild(profileDiplome);

                    var profileAdresse = document.createElement("li");
                    profileAdresse.classList.add("profile-adress");
                    profileAdresse.textContent = BestAdresseNote[i].adresse.adresse;
                    ul.appendChild(profileAdresse);

                    var profileSexe = document.createElement("li");
                    profileSexe.classList.add("profile-sexe");
                    profileSexe.textContent = BestSexeNote[i].sexe.sexe;
                    ul.appendChild(profileSexe);

                    var profileExperience = document.createElement("li");
                    profileExperience.classList.add("profile-experience");
                    profileExperience.textContent = BestExperienceNote[i].experience.experience;
                    ul.appendChild(profileExperience);

                    var profileSalary = document.createElement("li");
                    profileSalary.classList.add("profile-salary");
                    profileSalary.textContent = BestSalaireNote[i].salaire.salaire;
                    ul.appendChild(profileSalary);

                    removeFloating.appendChild(removeIcon);

                    profileCard.appendChild(removeFloating);
                    profileCard.appendChild(profileTitle);
                    profileCard.appendChild(ul);
                    donneeCard.appendChild(profileCard);
                }

            } else {
                alert("Une erreur s'est produite lors de la récupération des données.");
            }
        };

        // Envoyez la requête AJAX
        xhr.send();
    });
});
