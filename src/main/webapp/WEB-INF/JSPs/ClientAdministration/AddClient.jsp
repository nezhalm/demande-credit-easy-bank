<%@ page import="Entities.Client" %>
<%@ page import="Entities.Employe" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Attendance Dashboard | By Code Info</title>
    <link rel="stylesheet"  href="../../../Css/AddPage.css" />
    <link rel="stylesheet"  href="../../../Css/DashboardPage.css" />

    <!-- Font Awesome Cdn Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
</head>
<body>
<div class="container">
    <nav>
        <ul>
            <li><a href="#" class="logo">
                <img src="../../../Images/admin.png">
                <span class="nav-item">Admin</span>
            </a></li>
            <li><a href="#">
                <i class="fas fa-menorah"></i>
                <span class="nav-item">Dashboard</span>
            </a></li>
            <li><a href="#">
                <i class="fas fa-comment"></i>
                <span class="nav-item">Message</span>
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
            <h1>Ajouter client</h1>
            <i class="fas fa-user-cog"></i>
        </div>

        <div class="container2">
            <h1 class="form-title">entrer vos informations</h1>
            <form action="/saveClient" method="POST">
                <input type="hidden" name="action" value="addClient">
                <div class="main-user-info">
                    <div class="user-input-box">
                        <label for="fullName">nom</label>
                        <input type="text"
                               id="fullName"
                               name="fullName"
                               placeholder="Enter Full Name"/>
                    </div>
                    <div class="user-input-box">
                        <label for="username">prenom</label>
                        <input type="text"
                               id="username"
                               name="username"
                               placeholder="Enter Username"/>
                    </div>
                    <div class="user-input-box">
                        <label for="email">adresse</label>
                        <input type="email"
                               id="email"
                               name="email"
                               placeholder="Enter Email"/>
                    </div>
                    <div class="user-input-box">
                        <label for="phoneNumber">telephone </label>
                        <input type="text"
                               id="phoneNumber"
                               name="phoneNumber"
                               placeholder="Enter Phone Number"/>
                    </div>
                    <div class="user-input-box">
                        <label for="dateNaissance">Date de naissance</label>
                        <input type="date"
                               id="dateNaissance"
                               name="dateNaissance"
                               placeholder="Sélectionnez votre date de naissance"/>
                    </div>
                    <div class="user-input-box">
                        <label for="matricule">Selectionner la matricule d'employe</label>
                        <select style="width: 95%; padding: 11px; border-radius: 6px; height: 40px;" id="matricule" name="matricule">
                            <c:forEach var="employe" items="${employes}">
                                <option value="${employe.matricule}">${employe.matricule}</option>
                            </c:forEach>
                            <!-- Ajoutez d'autres options au besoin -->
                        </select>

                    </div>


                </div>

                <div class="form-submit-btn">
                    <input type="submit" value="Register">
                </div>
            </form>
        </div>



    </section>
</div>

</body>
</html>