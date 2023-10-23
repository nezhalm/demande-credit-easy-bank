
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Easy Bank</title>
    <link rel="stylesheet" href="../../Css/DemandeAdministration/modal.css" />
    <link rel="stylesheet" href="../../Css/ClientAdministration/DashboardPage.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.min.js"></script>

    <!-- Font Awesome Cdn Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
</head>
<body>
<div class="container">
    <nav>
        <ul>
            <li><a href="/" class="logo">
                <img src="../../Images/admin.png">
                <span class="nav-item">EasyBank</span>
            </a></li>
            <li><a href="/credit-request">
                <i class="fas fa-database"></i>
                <span class="nav-item">Credit Request</span>
            </a></li>
            <li><a href="/displayAllDemandes">
                <i class="fas fa-comment"></i>
                <span class="nav-item">Request List</span>
            </a></li>
            <li><a href="#">
                <i class="fas fa-database"></i>
                <span class="nav-item">Report</span>
            </a></li>
            <li><a href="#">
                <i class="fas fa-chart-bar"></i>
                <span class="nav-item">Attendance</span>
            </a></li>
            <li><a href="#">
                <i class="fas fa-cog"></i>
                <span class="nav-item">Setting</span>
            </a></li>

            <li><a href="#" class="logout">
                <i class="fas fa-sign-out-alt"></i>
                <span class="nav-item">Log out</span>
            </a></li>
        </ul>
    </nav>

    <section class="main">
        <div class="main-top">
            <%-- Affichez le message de succès --%>
            <c:if test="${not empty param.successMessage}">
                <div class="alert alert-success">
                    <p>
                        ${param.successMessage}
                    </p>
                    <button class="close-notification-btn">
                        <iconify-icon icon="material-symbols:close-rounded"></iconify-icon>
                    </button>
                </div>
            </c:if>

            <c:if test="${not empty param.errorMessage}">
                <div class="alert alert-danger">
                    <p>
                        ${param.errorMessage}
                    </p>
                    <button class="close-notification-btn">
                        <iconify-icon icon="material-symbols:close-rounded"></iconify-icon>
                    </button>
                </div>
            </c:if>

            <c:if test="${not empty message && message[0] == 'success'}">
                <div class="alert alert-success">
                    <p>
                        ${message[1]}
                    </p>
                    <button class="close-notification-btn">
                        <iconify-icon icon="material-symbols:close-rounded"></iconify-icon>
                    </button>
                </div>
            </c:if>

            <c:if test="${not empty message && message[0] == 'error'}">
                <div class="alert alert-danger">
                    <p>
                        ${message[1]}
                    </p>
                    <button class="close-notification-btn">
                        <iconify-icon icon="material-symbols:close-rounded"></iconify-icon>
                    </button>
                </div>
            </c:if>
        </div>
        <section class="attendance">
            <div class="attendance-list">
                <div style="display: flex;justify-content: space-between;">
                    <div> <h1>Demandes</h1></div>
                    <div style="margin-top: 1%;
    margin-left: 67%;" class="search">
                        <form action="/searchByStatus" method="get">
                            <input type="text"
                                   placeholder=" Entrer l'etat"
                                   name="status">
                            <input type="hidden" name="action" value="/searchByStatus">

                            <button>
                                <i class="fa fa-search"
                                   style="font-size: 18px;">
                                </i>
                            </button>
                        </form>

                            <form action="/searchByDate" method="get">
                                <input type="date"
                                       placeholder="Entrer la date"
                                       name="date">
                                <input type="hidden" name="action" value="/searchByDate">

                                <button>
                                    <i class="fa fa-search"
                                       style="font-size: 18px;">
                                    </i>
                                </button>
                            </form>

                    </div>

                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>Client</th>
                        <th>Employee</th>
                        <th>Agency</th>
                        <th>Credit</th>
                        <th>Duration</th>
                        <th>Monthly Payment</th>
                        <th>Status</th>
                        <th>Modify</th>
                        <th>History</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:choose>
                        <c:when test="${param.action == '/searchByStatus'}">

                            <tr>
                            <c:forEach items="${demandes}" var="demande">
                                <tr>
                                    <td>${demande.client.prenom} ${demande.client.nom}</td>
                                    <td>${demande.employe.prenom} ${demande.employe.nom}</td>
                                    <td>${demande.agence.nom}</td>
                                    <td>${demande.price} DH</td>
                                    <td>${demande.duration} months</td>
                                    <td class="monthly-payment">${demande.monsualite} DH/month</td>
                                    <td>${demande.status}</td>

                                    <!-- Utilisez "telephone" au lieu de "heureOuverture" -->
                                    <td>
                                        <!--  <form method="GET" action="updateClient">
                                                <input type="hidden" name="action" value="updateClient">
                                                <input type="hidden" name="clientCode" value="">-->
                                        <button data-number="${demande.number}" class="updateDetails" data-status="${demande.status}" type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg></button>
                                        <!--    </form>-->
                                    </td>
                                    <td>
                                        <form method="GET" action="updateClient">
                                            <input type="hidden" name="action" value="updateClient">
                                            <input type="hidden" name="clientCode" value="">
                                            <button type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"/></svg></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tr>
                        </c:when>
                        <c:when test="${param.action == '/searchByDate'}">
                            <tr>
                            <c:forEach items="${demandes}" var="demande">
                                <tr>
                                    <td>${demande.client.prenom} ${demande.client.nom}</td>
                                    <td>${demande.employe.prenom} ${demande.employe.nom}</td>
                                    <td>${demande.agence.nom}</td>
                                    <td>${demande.price} DH</td>
                                    <td>${demande.duration} months</td>
                                    <td class="monthly-payment">${demande.monsualite} DH/month</td>
                                    <td>${demande.status}</td>

                                    <!-- Utilisez "telephone" au lieu de "heureOuverture" -->
                                    <td>
                                        <!--  <form method="GET" action="updateClient">
                                                <input type="hidden" name="action" value="updateClient">
                                                <input type="hidden" name="clientCode" value="">-->
                                        <button data-number="${demande.number}" class="updateDetails" data-status="${demande.status}" type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg></button>
                                        <!--    </form>-->
                                    </td>
                                    <td>
                                        <form method="GET" action="updateClient">
                                            <input type="hidden" name="action" value="updateClient">
                                            <input type="hidden" name="clientCode" value="">
                                            <button type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"/></svg></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tr>

                        </c:when>
                        <c:otherwise>
                             <c:forEach items="${demandes}" var="demande">
                                <tr>
                                    <td>${demande.client.prenom} ${demande.client.nom}</td>
                                    <td>${demande.employe.prenom} ${demande.employe.nom}</td>
                                    <td>${demande.agence.nom}</td>
                                    <td>${demande.price} DH</td>
                                    <td>${demande.duration} months</td>
                                    <td class="monthly-payment">${demande.monsualite} DH/month</td>
                                    <td>${demande.status}</td>

                                    <!-- Utilisez "telephone" au lieu de "heureOuverture" -->
                                    <td>
                                        <!--  <form method="GET" action="updateClient">
                                                <input type="hidden" name="action" value="updateClient">
                                                <input type="hidden" name="clientCode" value="">-->
                                        <button data-number="${demande.number}" class="updateDetails" data-status="${demande.status}" type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg></button>
                                        <!--    </form>-->
                                    </td>
                                    <td>
                                        <form method="GET" action="updateClient">
                                            <input type="hidden" name="action" value="updateClient">
                                            <input type="hidden" name="clientCode" value="">
                                            <button type="submit"><svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 576 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M288 32c-80.8 0-145.5 36.8-192.6 80.6C48.6 156 17.3 208 2.5 243.7c-3.3 7.9-3.3 16.7 0 24.6C17.3 304 48.6 356 95.4 399.4C142.5 443.2 207.2 480 288 480s145.5-36.8 192.6-80.6c46.8-43.5 78.1-95.4 93-131.1c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C433.5 68.8 368.8 32 288 32zM144 256a144 144 0 1 1 288 0 144 144 0 1 1 -288 0zm144-64c0 35.3-28.7 64-64 64c-7.1 0-13.9-1.2-20.3-3.3c-5.5-1.8-11.9 1.6-11.7 7.4c.3 6.9 1.3 13.8 3.2 20.7c13.7 51.2 66.4 81.6 117.6 67.9s81.6-66.4 67.9-117.6c-11.1-41.5-47.8-69.4-88.6-71.1c-5.8-.2-9.2 6.1-7.4 11.7c2.1 6.4 3.3 13.2 3.3 20.3z"/></svg></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </section>
    </section>
</div>





<!-- Boîte de dialogue contextuelle simple contenant un formulaire -->
<dialog id="favDialog">
    <form action="/updateStatus" method="post">
        <div style="justify-content: center; display: flex;">
            <label>Status :
                <select id="demande-status" name="status" style="margin-top: 10px; width: 49%; padding: 2px 14px;">
                    <option selected disabled>Select</option>
                    <option value="Pending">Pending</option>
                    <option value="Accepted">Accpeted</option>
                    <option value="Rejected">Rejected</option>

                </select>
            </label>
            <input id="demande-number" type="hidden" name="number">
        </div>
        <menu style="column-gap: 19px; justify-content: center; display: flex; padding: 13px;">
            <button id="annulerBtn" style="padding: 4px 14px; color: aliceblue; background-color: brown; border-radius: 10px;" type="button" value="cancel">Annuler</button>
            <button type="submit" style="padding: 4px 14px; color: aliceblue; background-color: brown; border-radius: 10px;" id="confirmBtn" value="default">Confirmer</button>
        </menu>
    </form>
</dialog>




<output></output>




</body>
<script>
    const updateButton = document.querySelectorAll(".updateDetails");
    const favDialog = document.getElementById("favDialog");
    const outputBox = document.querySelector("output");
    const selectEl = favDialog.querySelector("select");
    const confirmBtn = favDialog.querySelector("#confirmBtn");
    const demandeStatus = document.querySelector("#demande-status");
    const demandeNumber = document.querySelector("#demande-number");
    const annulerBtn = document.getElementById("annulerBtn");
    const alert = document.querySelector(".alert");

    document.addEventListener("click", (evt) => {
        if (evt.target.classList.contains("close-notification-btn")) {
            alert.style.display = "none";
        }
    });

    annulerBtn.addEventListener("click", () => {
        favDialog.close();
    });

    if (typeof favDialog.showModal !== "function") {
        favDialog.hidden = true;
        /* un script alternatif pour les navigateurs historiques.
         */
    }
    // Le bouton "Mettre à jour les détails" ouvre <dialog> en modal

    updateButton.forEach(btn => {
        btn.addEventListener("click", (event) => {
            if (typeof favDialog.showModal === "function") {

                favDialog.showModal();
                demandeStatus.value = event.target.dataset.status;
                demandeNumber.value = event.target.dataset.number;
            } else {
                outputBox.value =
                    "Désolé, l'API <dialog> n'est pas prise en charge par ce navigateur.";
            }
        });
    })
    // Le champ "Animal préféré" définit la valeur du bouton d'envoi
    selectEl.addEventListener("change", (e) => {
        confirmBtn.value = selectEl.value;
    });
</script>
</html>
