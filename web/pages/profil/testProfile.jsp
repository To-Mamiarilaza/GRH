<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Diplome "%>
<%@page import=" model.gestionProfile.Adresse "%>
<%@page import=" model.gestionProfile.Salaire "%>
<%@page import=" model.gestionProfile.Sexe "%>
<%@page import=" model.gestionProfile.Experience "%>
<%
    List<Diplome> listeDiplome = (List<Diplome>) request.getAttribute("listeDiplome");
    List<Adresse> listeAdresse = (List<Adresse>) request.getAttribute("listeAdresse");
    List<Experience> listeExperience = (List<Experience>) request.getAttribute("listeExperience");
    List<Salaire> listeSalaire = (List<Salaire>) request.getAttribute("listeSalaire");
    List<Sexe> listeSexe = (List<Sexe>) request.getAttribute("listeSexe");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> profil </title>
        <script src="./../../assets/js/Ajax/DiplomeScript.js"></script>
    </head>
    <body>
        <h1> Creation d'un nouveau profil </h1>
        <form method="post" id="diplomeFormulaire">
            <p> Diplome : <select name='diplome' id="diplome">
                    <% for(int i=0;i<listeDiplome.size();i++) { %>
                    <option value = "<%= listeDiplome.get(i).getDiplome() %>"><%= listeDiplome.get(i).getDiplome() %></option>
                    <% } %>
                </select> 
                <input type="number" name="note" placeholder="note" id="note">
                <input type="submit" value="valider">
            </p>
        </form>
        <div id="listeDiplomes"></div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var formulaireDiplome = document.getElementById("diplomeFormulaire");
                formulaireDiplome.addEventListener("submit", function (event) {
                    event.preventDefault();
                    var diplome = document.getElementById("diplome").value;
                    var diplomeNote = document.getElementById("note").value;
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
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
        </script>

        <form method="post" id="adresseFormulaire">
            <p> Adresse : <select name='adresse' id="adresseSelect">
                    <% for(int i=0;i<listeAdresse.size();i++) { %>
                    <option value="<%= listeAdresse.get(i).getAdresse() %>"><%= listeAdresse.get(i).getAdresse() %></option>
                    <% } %>
                </select> 
                <input type="number" name="note" placeholder="note" id="AdresseNote">
                <input type="submit" value="valider">
            </p>
        </form>
        <div id="listeAdresse"></div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var formulaireAdresse = document.getElementById("adresseFormulaire");

                formulaireAdresse.addEventListener("submit", function (event) {
                    event.preventDefault();

                    var adresse = document.getElementById("adresseSelect").value;
                    var adresseNote = document.getElementById("AdresseNote").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            var donnees = JSON.parse(xhr.responseText);
                            var listeDonneesDiv = document.getElementById("listeAdresse");

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
                    var formData = "adresse=" + encodeURIComponent(adresse) + "&adressenote=" + encodeURIComponent(adresseNote);
                    xhr.send(formData);
                });
            });
        </script>


        <form method="post" id="experienceFormulaire">
            <p> Experience : <select name='experience' id="experienceSelect">
                    <% for(int i=0;i<listeExperience.size();i++) { %>
                    <option value="<%= listeExperience.get(i).getExperience() %>"><%= listeExperience.get(i).getExperience() %></option>
                    <% } %>
                </select> 
                <input type="number" name="note" placeholder="note" id="ExperienceNote">
                <input type="submit" value="valider">
            </p>
        </form>
        <div id="listeExperience"></div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var formulaireExperience = document.getElementById("experienceFormulaire");

                formulaireExperience.addEventListener("submit", function (event) {
                    event.preventDefault();

                    var experience = document.getElementById("experienceSelect").value;
                    var experienceNote = document.getElementById("ExperienceNote").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            var donnees = JSON.parse(xhr.responseText);
                            var listeDonneesDiv = document.getElementById("listeExperience");

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
                    var formData = "experience=" + encodeURIComponent(experience) + "&experiencenote=" + encodeURIComponent(experienceNote);
                    xhr.send(formData);
                });
            });
        </script>


        <form method="post" id="salaireFormulaire">
            <p> Salaire : <select name='salaire' id="salaireSelect">
                    <% for(int i=0;i<listeSalaire.size();i++) { %>
                    <option value="<%= listeSalaire.get(i).getSalaire() %>"><%= listeSalaire.get(i).getSalaire() %></option>
                    <% } %>
                </select> 
                <input type="number" name="note" placeholder="note" id="SalaireNote">
                <input type="submit" value="valider">
            </p>
        </form>
        <div id="listeSalaire"></div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var formulaireSalaire = document.getElementById("salaireFormulaire");

                formulaireSalaire.addEventListener("submit", function (event) {
                    event.preventDefault();

                    var salaire = document.getElementById("salaireSelect").value;
                    var salaireNote = document.getElementById("SalaireNote").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            var donnees = JSON.parse(xhr.responseText);
                            var listeDonneesDiv = document.getElementById("listeSalaire");

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
                    var formData = "salaire=" + encodeURIComponent(salaire) + "&salairenote=" + encodeURIComponent(salaireNote);
                    xhr.send(formData);
                });
            });
        </script>

        <form method="post" id="sexeFormulaire">
            <p> Sexe : <select name='sexe' id="sexeSelect">
                    <% for(int i=0;i<listeSexe.size();i++) { %>
                    <option value="<%= listeSexe.get(i).getSexe() %>"><%= listeSexe.get(i).getSexe() %></option>
                    <% } %>
                </select> 
                <input type="number" name="note" placeholder="note" id="SexeNote">
                <input type="submit" value="valider">
            </p>
        </form>
        <div id="listeSexe"></div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var formulaireSexe = document.getElementById("sexeFormulaire");

                formulaireSexe.addEventListener("submit", function (event) {
                    event.preventDefault();

                    var sexe = document.getElementById("sexeSelect").value;
                    var sexeNote = document.getElementById("SexeNote").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "/RessourceHumaine/AjaxProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            var donnees = JSON.parse(xhr.responseText);
                            var listeDonneesDiv = document.getElementById("listeSexe");

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
                    var formData = "sexe=" + encodeURIComponent(sexe) + "&sexenote=" + encodeURIComponent(sexeNote);
                    xhr.send(formData);
                });
            });
        </script>
        <button type="submit"> valider </button>
    </body>
</html>
