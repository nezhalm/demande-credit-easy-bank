<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Easy Bank</title>
    <link rel="stylesheet" href="../../Css/DemandeAdministration/FormeDemande.css"/>
    <link rel="stylesheet" href="../../Css/DemandeAdministration/PetiteCard.css"/>
    <link rel="stylesheet" href="../../Css/DemandeAdministration/style.css"/>
    <script src="../../Js/Script.js" defer></script>
    <script src="../../Js/FormeScript.js" defer></script>

    <!-- Font Awesome Cdn Link -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
</head>
<body>
<div class="container">
    <nav>
        <ul>

            <li><a href="/" class="logo">
                <img src="../../../Images/admin.png">
                <span class="nav-item">Admin</span>
            </a></li>
            <li><a href="/list">
                <i class="fas fa-menorah"></i>
                <span class="nav-item">Dashboard</span>
            </a></li>
            <li><a href="/displayFormDemande">
                <i class="fas fa-database"></i>
                <span class="nav-item">Credit Request</span>
            </a></li>

            <li><a href="#">
                <i class="fas fa-comment"></i>
                <span class="nav-item">Message</span>
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

    <section>
        <form action="#" class="form">
            <h1 class="text-center">Registration Form</h1>
            <!-- Progress bar -->
            <div class="progressbar">
                <div class="progress" id="progress"></div>

                <div
                        class="progress-step progress-step-active"
                        data-title="sumuler mon credit"
                ></div>
                <div class="progress-step" data-title="cordonnes"></div>
                <div class="progress-step" data-title="info professionnel"></div>
            </div>

            <!-- Steps -->
            <div class="form-step form-step-active">
                <div class="input-group">
                    <div class="user-input-box">
                        <label for="matricule">mon projet</label>
                        <select style="width: 95%; padding: 11px; border-radius: 6px; height: 40px;" name="matricule">
                            <option value="">proejet1</option>
                            <option value="">proejet2</option>
                            <option value="">proejet3</option>
                            <option value="">proejet4</option>
                            <option value="">proejet5</option>
                        </select>

                    </div>
                </div>
                <div class="input-group">
                    <div class="user-input-box">
                        <label for="matricule">je suis</label>
                        <select style="width: 95%; padding: 11px; border-radius: 6px; height: 40px;" id="matricule"
                                name="matricule">
                            <option value="">proejet1</option>
                            <option value="">proejet2</option>
                            <option value="">proejet3</option>
                            <option value="">proejet4</option>
                            <option value="">proejet5</option>
                        </select>

                    </div>
                </div>
                <h3 style="margin-bottom: 24px;
              ">choisir le montant</h3>
                <div class="range">
                    <div class="sliderValue">
                        <span>5000</span>
                    </div>
                    <div class="field">
                        <div class="value left">5000</div>
                        <input type="range" id="range1" min="5000" max="600000" value="5000" step="1000">
                        <div class="value right">600000</div>
                    </div>
                </div>
                <h3 style="margin-bottom: 24px;  margin-top: 10px;
                ">choisir la duree</h3>

                <div class="range">
                    <div class="sliderValue2">
                        <span>12</span>
                    </div>
                    <div class="field">
                        <div class="value left">12</div>
                        <input type="range" id="range2" min="12" max="120" value="12" step="6">
                        <div class="value right">120</div>
                    </div>
                </div>
                <div class="">
                    <a style="margin-top: 20px;" href="#" class="btn btn-next width-50 ml-auto">Next</a>
                </div>
            </div>


            <div class="form-step">
                <div class="input-group">
                    <form action="/searchClient" method="get">
                        <label for="phone">Entrer le code de l'utilisateur</label>
                        <input style="border-radius: 0.5rem;margin-bottom: 26px; padding: 8px 4px;" type="text"
                               name="code" id="phone"/>
                        <div class="btns-group">
                            <a href="#" class="btn btn-prev">Previous</a>
                            <button type="submit" class="btn btn-next">Next</button>
                        </div>
                    </form>
                </div>
            </div>


            <div class="form-step">
                <div class="input-group">
                    <label for="dob">Date of Birth</label>
                    <input type="date" name="dob" id="dob"/>
                </div>
                <div class="input-group">
                    <label for="ID">National ID</label>
                    <input type="number" name="ID" id="ID"/>
                </div>
                <div class="btns-group">
                    <a href="#" class="btn btn-prev">Previous</a>
                    <a href="#" class="btn btn-next">Next</a>
                </div>
            </div>
            <div class="form-step">
                <div class="input-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password"/>
                </div>
                <div class="input-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input
                            type="password"
                            name="confirmPassword"
                            id="confirmPassword"
                    />
                </div>
                <div class="btns-group">
                    <a href="#" class="btn btn-prev">Previous</a>
                    <input type="submit" value="Submit" class="btn"/>
                </div>
            </div>
        </form>


    </section>
    <section>
        <div style="
  margin-left: 54px;
  border-radius: 15px;
  margin-left: 61px;
    width: 316px;padding: 2px 25px;
    background-color: rgb(200, 200, 200);" class="table basic">
            <div class="package-name"></div>
            <ul class="features">
                <li>
                    <span class="list-name">Vous êtes:</span>
                    <span class="list-price">fontionnaire</span>
                </li>
                <li>
                    <span class="list-name">Montant:</span>
                    <span class="list-price" id="mon"></span>
                </li>
                <li>
                    <span class="list-name">Durée:</span>
                    <span class="list-price" id="dureet"></span>
                </li>
                <li>
                    <span class="list-name">Mensualité:</span>
                    <span class="list-price" id="output"></span>
                </li>
                <li>
                    <span class="list-name">Frais de dossier:</span>
                    <span class="list-price">212355 $</span>
                </li>
            </ul>
        </div>

    </section>

</div>

</body>


</html>
