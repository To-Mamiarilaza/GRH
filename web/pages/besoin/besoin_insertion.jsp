<%@page import="java.util.ArrayList"%>
<%@page import="model.requis.Service"%>
<%@page import="model.gestionBesoin.Unity"%>
<%@page import=" java.util.List "%>
<%@page import=" model.gestionProfile.Diplome "%>
<%@page import=" model.gestionProfile.Adresse "%>
<%@page import=" model.gestionProfile.Salaire "%>
<%@page import=" model.gestionProfile.Sexe "%>
<%@page import=" model.gestionProfile.Experience "%>
<%@page import=" model.gestionProfile.BestCritere "%>
<%
    List<Diplome> listeDiplome = (List<Diplome>) request.getAttribute("listeDiplome");
    List<Adresse> listeAdresse = (List<Adresse>) request.getAttribute("listeAdresse");
    List<Experience> listeExperience = (List<Experience>) request.getAttribute("listeExperience");
    List<Salaire> listeSalaire = (List<Salaire>) request.getAttribute("listeSalaire");
    List<Sexe> listeSexe = (List<Sexe>) request.getAttribute("listeSexe");
    List<BestCritere> listeProfile = (List<BestCritere>) request.getAttribute("listeProfile");
%>

                <!-- Modal pour l'insertion d'un nouveau profil -->
                <div class="modal fade" id="newProfil" tabindex="-1" aria-labelledby="newProfilLabel" aria-hidden="true">
                    <div class="modal-dialog  modal-xl modal-dialog-centered">
                        <div class="modal-content card-body">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="newProfilLabel">Insertion nouveau profile</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                <hr>
                            </div>

                            <form class="forms-sample" method="get" action="/GRH/AjaxProfileServlet">
                                <div class="form-group row">
                                    <label for="profileName" class="col-sm-2 col-form-label"> Poste </label>
                                    <div class="col-sm-5">
                                        <input type="text" class="form-control" id="profileName" name="poste">
                                    </div>
                                </div>

                                <hr>

                                <div class="row">
                                    <div class="side-by-side col-md-6">
                                        <!-- Section diplome -->
                                        <div class="diplome">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="diplomeChoice"
                                                       class="col-sm-2 col-form-label">Diplome</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="diplome" id="diplomeChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="diplomeNote" id="diplomeNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="diplomeCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("diplomeChoice").value;
                                                var diplomeNote = document.getElementById("diplomeNote").value;
                                                var diplomeCase = document.getElementById("diplomeCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/GRH/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/GRH/DeleteProfileServlet?idDiplome=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "diplome=" + encodeURIComponent(diplome) + "&diplomenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="experience">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="experienceChoice"
                                                       class="col-sm-2 col-form-label">Experience</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="experience" id="experienceChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="experienceNote" id="experienceNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitExperienceButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="experienceCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitExperienceButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("experienceChoice").value;
                                                var diplomeNote = document.getElementById("experienceNote").value;
                                                var diplomeCase = document.getElementById("experienceCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/GRH/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/GRH/DeleteProfileServlet?idExperience=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "experience=" + encodeURIComponent(diplome) + "&experiencenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="salaire mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="salaireChoice"
                                                       class="col-sm-2 col-form-label">Salaire</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="salaire" id="salaireChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="salaireNote" id="salaireNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitSalaireButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="salaireCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitSalaireButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("salaireChoice").value;
                                                var diplomeNote = document.getElementById("salaireNote").value;
                                                var diplomeCase = document.getElementById("salaireCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/GRH/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/GRH/DeleteProfileServlet?idSalaire=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "salaire=" + encodeURIComponent(diplome) + "&salairenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="sexe mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="sexeChoice"
                                                       class="col-sm-2 col-form-label">Sexe</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="sexe" id="sexeChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="sexeNote" id="sexeNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitSexeButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="sexeCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitSexeButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("sexeChoice").value;
                                                var diplomeNote = document.getElementById("sexeNote").value;
                                                var diplomeCase = document.getElementById("sexeCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/GRH/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/GRH/DeleteProfileServlet?idSexe=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "sexe=" + encodeURIComponent(diplome) + "&sexenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                    <div class="side-by-side col-md-6">
                                        <div class="adresse mt-3">
                                            <div
                                                class="form-group row d-flex align-items-center justify-content-center">
                                                <label for="adresseChoice"
                                                       class="col-sm-2 col-form-label">Adresse</label>
                                                <div class="col-sm-5 reduce-padding">
                                                    <select name="adresse" id="adresseChoice"
                                                            class="form-control form-control-sm form-select">


                                                    </select>
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <input type="number" class="form-control form-control-sm" name="adresseNote" id="adresseNote"
                                                           placeholder="Note">
                                                </div>
                                                <div class="col-sm-2 reduce-padding">
                                                    <button type="submit"
                                                            class="btn btn-gradient-primary btn-sm" id="submitAdresseButton"> Ajouter </button>
                                                </div>
                                            </div>
                                            <div class="profil-critere-container">
                                                <div class="row d-flex align-items-center border-bottom" id="adresseCase">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", function () {
                                            var boutonSubmit = document.getElementById("submitAdresseButton");
                                            boutonSubmit.addEventListener("click", function (event) {
                                                event.preventDefault();
                                                var diplome = document.getElementById("adresseChoice").value;
                                                var diplomeNote = document.getElementById("adresseNote").value;
                                                var diplomeCase = document.getElementById("adresseCase");
                                                var xhr = new XMLHttpRequest();
                                                xhr.open("POST", "/GRH/AjaxProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                xhr.onload = function () {
                                                    if (xhr.status === 200) {
                                                        var donnees = JSON.parse(xhr.responseText);
                                                        for (var i = 0; i < donnees.length; i++) {
                                                            var donnee = donnees[i];
                                                            var donneeSpliter = donnee.split(":");
                                                            var paragraphe1 = document.createElement("div");
                                                            paragraphe1.className = "col-sm-7";
                                                            paragraphe1.textContent = donneeSpliter[0];
                                                            var paragraphe2 = document.createElement("div");
                                                            paragraphe2.className = "col-sm-2";
                                                            paragraphe2.textContent = donneeSpliter[1];
                                                            var paragraphe3 = document.createElement("div");
                                                            paragraphe3.className = "col-sm-2";
                                                            var dele = document.createElement("i");
                                                            dele.className = "remove mdi mdi-close-circle-outline";
                                                            (function (ListeIdWp) {
                                                                dele.onclick = function () {
                                                                    var xhr = new XMLHttpRequest();
                                                                    xhr.open("GET", "/GRH/DeleteProfileServlet?idAdresse=" + ListeIdWp, true);
                                                                    xhr.onreadystatechange = function () {
                                                                        if (xhr.readyState === 4) {
                                                                            if (xhr.status === 200) {
                                                                                diplomeCase.textContent = "";
                                                                            } else {
                                                                                alert("Erreur !");
                                                                            }
                                                                        }
                                                                    };
                                                                    xhr.send();
                                                                };
                                                            })(i);
                                                            paragraphe3.appendChild(dele);
                                                            diplomeCase.appendChild(paragraphe1);
                                                            diplomeCase.appendChild(paragraphe2);
                                                            diplomeCase.appendChild(paragraphe3);
                                                        }
                                                    } else {
                                                        alert("Erreur lors de la requête : " + xhr.status);
                                                    }
                                                };
                                                // Envoyez les données au Servlet
                                                var formData = "adresse=" + encodeURIComponent(diplome) + "&adressenote=" + encodeURIComponent(diplomeNote);
                                                xhr.send(formData);
                                                return false;
                                            });
                                        });
                                    </script>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <input type="submit" class="btn btn-gradient-primary" value="Valider">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Modal pour le choix d'un profil -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                     aria-hidden="true">
                    <div class="modal-dialog  modal-xl modal-dialog-centered">
                        <div class="modal-content card-body">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel"> Poste </h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                <hr>
                            </div>
                            <div class="modal-body">
                                <button type="submit" class="btn btn-gradient-primary me-2" data-bs-toggle="modal" id="nouveauProfile"
                                        data-bs-target="#newProfil">Crée un nouveau profile</button>

                                <div class="mt-4">
                                    <h4 class="card-title mb-4">Liste des anciens profiles existants</h4>
                                    <div class="row profile-list" id="profile-list">
                                        <% for(int i = 0; i<listeProfile.size(); i++) { %>
                                        <div class="col-md-3 stretch-card grid-margin">
                                            <div class="card ">
                                                <div class="profile-card" onclick="clicked(<%= listeProfile.get(i).getListeWantedProfile().get(i).getIdWantedProfile() %>, this)">
                                                    <div class="remove-floating">
                                                        <a href="/GRH/DeleteProfileServlet?indice=<%= listeProfile.get(i).getListeWantedProfile().get(i).getIdWantedProfile() %>"><i class="remove mdi mdi-close-circle-outline"></i></a>
                                                    </div>
                                                    <h5 class="profile-title"> <%= listeProfile.get(i).getListeWantedProfile().get(i).getPoste() %></h5>
                                                    <ul>
                                                        <li class="profile-diplome"> <%= listeProfile.get(i).getListeDiplomeNote().get(i).getDiplome().getDiplome() %> </li>
                                                        <li class="profile-experience"> <%= listeProfile.get(i).getListeExperienceNote().get(i).getExperience().getExperience() %> </li>
                                                        <li class="profile-salary"><%= listeProfile.get(i).getListeSalaireNote().get(i).getSalaire().getSalaire() %> Ar</li>
                                                        <li class="profile-sexe"> <%= listeProfile.get(i).getListeSexeNote().get(i).getSexe().getSexe() %> </li>
                                                        <li class="profile-adress"> <%= listeProfile.get(i).getListeAdresseNote().get(i).getAdresse().getAdresse() %> </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <% } %>
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button onclick="profileValided()" type="button" class="btn btn-gradient-primary" data-bs-dismiss="modal">Valider</button>
                            </div>
                        </div>
                    </div>
                </div>

                <%  if(request.getAttribute("service") != null) {
                    Service service = (Service)request.getAttribute("service");
                    ArrayList<Unity> unitys = (ArrayList<Unity>)request.getAttribute("unitys");
                %>

                        <div class="row">
                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Service : <%=service.getService() %></h4>
                                        <p class="card-description"> Veuillez bien remplir les formulaires et bien décrire
                                            vos demandes </p>
                                        <form id="formTask" class="forms-sample" method="post" accept-charset="UTF-8">
                                            <div class="form-group">
                                                <label for="besoinDescription">Description du besoin</label>
                                                <textarea name="description" id="description" class="form-control" cols="30"
                                                          rows="10"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Liste des taches</label>
                                                <div class="row d-flex align-items-center">
                                                    <div class="col-md-6">
                                                        <input type="text" id="task" name="tasks" class="form-control"
                                                               placeholder="Nouvelle tache">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <button  
                                                            class="btn btn-gradient-primary me-2">Ajouter</button>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group task-list">
                                                <div class="list-wrapper">
                                                    <ul id="valueEnter" class="d-flex flex-column-reverse todo-list todo-list-custom">

                                                    </ul>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 grid-margin stretch-card">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="">Charge de travail et profil recherché</h4>
                                        <a data-bs-toggle="modal" data-bs-target="#exampleModal"
                                           class="btn btn-gradient-primary me-1" href="ListeProfileServlet">Choisir le
                                            profil</a>
                                        <form id="formWorkLoad" class="forms-sample mt-3" action="addBesoinServlet" method="post"> 
                                            <div class="row d-flex align-items-start">
                                                <div class="form-group col-md-4">
                                                    <label for="volumeHorraire">Volume horraire</label>
                                                    <input type="number" class="form-control" id="volumeHorraire"
                                                           placeholder="40">
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="unitySelect">Unité</label>
                                                    <select name="" id="unitySelect" class="form-control-sm form-select">
                                                        <% for(int i = 0; i < unitys.size(); i++) { %>
                                                        <option value=<%=unitys.get(i).getIdUnity() %>><%=unitys.get(i).getUnity() %></option>
                                                        <% } %>   
                                                    </select>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <label for="unitySelect">Unité</label>
                                                    <button
                                                        class="btn btn-gradient-primary me-2">Ajouter</button>
                                                </div>
                                            </div>
                                            <div class="form-group task-list mt-3" style="height: 250px;">
                                                <div class="list-wrapper">
                                                    <table id="tableWorkLoad" class="table">

                                                    </table>
                                                </div>
                                            </div>
                                        </form>
                                        <button onclick="sendBesoin()" class="btn btn-gradient-primary me-2">Envoye du
                                            besoin</button>
                                    </div>
                                </div>
                            </div>

                        </div>

                <% } %>
           
            <script>
                                            document.addEventListener("DOMContentLoaded", function () {
                                                var xhr = new XMLHttpRequest();
                                                // Configurez la requête AJAX
                                                xhr.open("GET", "/GRH/ProfileServlet", true);
                                                xhr.setRequestHeader("Content-Type", "application/json");
                                                xhr.onload = function () {
                                                    if (xhr.readyState === 4 && xhr.status === 200) {
                                                        var responseData = JSON.parse(xhr.responseText);
                                                        var listeDiplome = responseData.listeDiplome;
                                                        var selectDiplome = document.getElementById("diplomeChoice");
                                                        // Parcourez la liste des diplômes et ajoutez-les au select
                                                        for (var i = 0; i < listeDiplome.length; i++) {
                                                            var diplome = listeDiplome[i].diplome;
                                                            var option = document.createElement("option");
                                                            option.value = diplome;
                                                            option.textContent = diplome;
                                                            selectDiplome.appendChild(option);
                                                        }
                                                    } else {
                                                        alert("Une erreur s'est produite lors de la récupération des données.");
                                                    }
                                                };
                                                // Envoyez la requête AJAX
                                                xhr.send();
                                            });
            </script>

            <!-- script pour ajouter les données du adresse -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/GRH/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeAdresse = responseData.listeAdresse;
                            var selectAdresse = document.getElementById("adresseChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeAdresse.length; i++) {
                                var adresse = listeAdresse[i].adresse;
                                var option = document.createElement("option");
                                option.value = adresse;
                                option.textContent = adresse;
                                selectAdresse.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>


            <!-- script pour ajouter les données du experience -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/GRH/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeExperience = responseData.listeExperience;
                            var selectExperience = document.getElementById("experienceChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeExperience.length; i++) {
                                var experience = listeExperience[i].experience;
                                var option = document.createElement("option");
                                option.value = experience;
                                option.textContent = experience;
                                selectExperience.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <!-- script pour ajouter les données du salaire -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/GRH/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeSalaire = responseData.listeSalaire;
                            var selectSalaire = document.getElementById("salaireChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeSalaire.length; i++) {
                                var salaire = listeSalaire[i].salaire;
                                var option = document.createElement("option");
                                option.value = salaire;
                                option.textContent = salaire;
                                selectSalaire.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <!-- script pour ajouter les données du sexe -->
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var xhr = new XMLHttpRequest();
                    // Configurez la requête AJAX
                    xhr.open("GET", "/GRH/ProfileServlet", true);
                    xhr.setRequestHeader("Content-Type", "application/json");
                    xhr.onload = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            var responseData = JSON.parse(xhr.responseText);
                            var listeSexe = responseData.listeSexe;
                            var selectSexe = document.getElementById("sexeChoice");
                            // Parcourez la liste des diplômes et ajoutez-les au select
                            for (var i = 0; i < listeSexe.length; i++) {
                                var sexe = listeSexe[i].sexe;
                                var option = document.createElement("option");
                                option.value = sexe;
                                option.textContent = sexe;
                                selectSexe.appendChild(option);
                            }
                        } else {
                            alert("Une erreur s'est produite lors de la récupération des données.");
                        }
                    };
                    // Envoyez la requête AJAX
                    xhr.send();
                });
            </script>

            <script>
                var idClicked = -1;
                var tableWorkLoad = document.getElementById("tableWorkLoad");

                function remove(logo) {
                    // Récupérer l'élément parent (li) du bouton cliqué et le supprimer
                    var parentElement = logo.parentNode;
                    parentElement.parentNode.removeChild(parentElement);
                    var itemValue = parentElement.childNodes[0].textContent;

                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', './addTaskServlet?itemToRemove=' + itemValue, true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    xhr.send();
                }
                ;

                function clicked(id, item) {
                    idClicked = id;
                    var profileDiv = document.getElementsByClassName("profile-card");
                    for(var i = 0; i < profileDiv.length; i++) {
                        profileDiv[i].style.backgroundColor = "white";
                    }
                    item.style.backgroundColor = "rgba(153, 85, 255, 0.5764705882)";
                }
                ;

                function profileValided() {
                    var idValue = idClicked;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './profileValidedServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec la valeur à envoyer
                    var formData = 'idValue=' + encodeURIComponent(idValue);

                    // Envoyez la requête
                    xhr.send(formData);
                }
                ;

                function createListItem(item) {

                    var valueEnter = document.getElementById('valueEnter');
                    var listItem = document.createElement('li');
                    var divItem = document.createElement("div");
                    divItem.classList.add("form-check");
                    var labelItem = document.createElement("label");
                    labelItem.classList.add("form-check-label");
                    labelItem.textContent = item;

                    var logoItem = document.createElement("i");
                    logoItem.classList.add("remove");
                    logoItem.classList.add("mdi");
                    logoItem.classList.add("mdi-close-circle-outline");
                    logoItem.setAttribute("onclick", "remove(this)");

                    divItem.appendChild(labelItem);
                    listItem.appendChild(divItem);
                    listItem.appendChild(logoItem);
                    valueEnter.appendChild(listItem);

                }
                ;
                function createTableworkLoad(table, poste, horaire, unity) {
                    var tr = document.createElement("tr");

                    var td1 = document.createElement("td");
                    td1.textContent = poste;

                    var td2 = document.createElement("td");
                    td2.textContent = horaire + " " + unity;
                    td2.classList.add("to-right");

                    var td3 = document.createElement("td");
                    var logoQuestion = document.createElement("i");
                    logoQuestion.classList.add("list-action");
                    logoQuestion.classList.add("primary");
                    logoQuestion.classList.add("mdi");
                    logoQuestion.classList.add("mdi-comment-question-outline");
                    td3.appendChild(logoQuestion);

                    var td4 = document.createElement("td");
                    var logoDelete = document.createElement("i");
                    logoDelete.setAttribute("onclick", "removeWorkLoad(this)");
                    logoDelete.classList.add("list-action");
                    logoDelete.classList.add("danger");
                    logoDelete.classList.add("mdi");
                    logoDelete.classList.add("mdi-close-circle-outline");
                    td4.appendChild(logoDelete);

                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    table.appendChild(tr);
                }
                ;

                function removeWorkLoad(logo) {
                    var parentElement = logo.parentNode;
                    var parent = parentElement.parentNode;
                    parent.parentNode.removeChild(parent);
                    var poste = parent.cells[0].textContent;
                    var hu = parent.cells[1].textContent.split(' ');
                    var volumeHorraire = hu[0];
                    var unity = hu[1];


                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', './addWorkLoadServlet?poste=' + poste + '&vh=' + volumeHorraire + '&unity=' + unity, true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Traitement de la réponse si nécessaire
                                console.log('Réponse du serveur : ' + xhr.responseText);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    xhr.send();

                }

                // Fonction pour ajouter une tâche de travail
                function addWorkLoad() {
                    var volumeHorraire = document.getElementById("volumeHorraire").value;
                    var unitySelect = document.getElementById("unitySelect").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addWorkLoadServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');

                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                const res = xhr.responseText.split('&');

                                createTableworkLoad(tableWorkLoad, res[0], volumeHorraire, res[1]);
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'volumeHorraire=' + encodeURIComponent(volumeHorraire) + '&unitySelect=' + encodeURIComponent(unitySelect);

                    // Envoyez la requête
                    xhr.send(formData);
                }

                // Ajoutez un gestionnaire d'événements au formulaire
                document.getElementById('formWorkLoad').addEventListener('submit', function (event) {
                    event.preventDefault();  // Empêche le rechargement de la page
                    addWorkLoad();
                });

                function sendBesoin() {
                    var descri = document.getElementById("description").value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addBesoinServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                window.location.href = "listBesoins";
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'descri=' + encodeURIComponent(descri);

                    // Envoyez la requête
                    xhr.send(formData);

                }


                //Ajouter une tache
                document.getElementById('formTask').addEventListener('submit', function (event) {
                    event.preventDefault();  // Empêche le rechargement de la page

                    var task = document.getElementById('task').value;

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', './addTaskServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                createListItem(xhr.responseText);
                                document.getElementById('task').value = "";
                            } else {
                                console.error('Une erreur s\'est produite : ' + xhr.status);
                            }
                        }
                    };

                    // Créez une chaîne de requête avec les données du formulaire
                    var formData = 'task=' + encodeURIComponent(task);

                    // Envoyez la requête
                    xhr.send(formData);
                });
            </script>

