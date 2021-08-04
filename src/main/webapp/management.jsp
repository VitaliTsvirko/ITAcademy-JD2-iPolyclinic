<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="/layouts/head.jsp"%>

    <!-- Counts JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
    <!-- Charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.0/chart.min.js" integrity="sha512-asxKqQghC1oBShyhiBwA+YgotaSYKxGP1rcSYTDrB0U6DxwlJjU59B67U8+5/++uFjcuVM8Hh5cokLjZlhm3Vg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>


</head>

<body class="bg-light">
<%@include file="/layouts/header.jsp"%>
<input type="hidden" id="user_id" value="${requestScope.user.id}">
<main class="container-md pt-5">

    <div class="row row-cols-4 row-cols-lg-5 g-4">

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Пользователей</h5>
                    <div class="flex-center">
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.usersMetrics.usersTotal}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Пациентов</h5>
                    <div class="flex-center">
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.usersMetrics.patientsTotal}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Врачей</h5>
                    <div class="flex-center">
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.usersMetrics.doctorsTotal}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Средний возраст пациента</h5>
                    <div class="flex-center">
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.usersMetrics.patientsAvgAgeYear}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Средний возраст врача</h5>
                    <div class="flex-center">
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.usersMetrics.doctorsAvgAgeYear}</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-12">
            <div class="card border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        График обращений
                    </h5>
                    <canvas id="bar-chart" width="800" height="200"></canvas>
                </div>
            </div>
        </div>
    </div>



</main>
<%@include file="/layouts/footer.jsp"%>

<script type="text/javascript">
    // jQuery counterUp
    $('[data-toggle="counterUp"]').counterUp({
        delay: 15,
        time: 1500
    });
</script>

<script>
    jQuery(function ($) {
        $(document).ready(function() {
            $.getJSON(BASE_URL + '/api/management', function (result, textStatus, jqXHR) {
                if (!$.isEmptyObject(result)) {
                    //store data to page
                    new Chart(document.getElementById("bar-chart"), {
                        type: 'bar',
                        legend: { display: false },
                        title: { display: false },
                        data: {
                            labels: result.date,
                            datasets: [
                                {
                                    backgroundColor: ["#50cd89"],
                                    data: result.count
                                }
                            ]
                        },
                        options: {
                            responsive: true,
                            legend: { display: false },
                            title: { display: false }
                        }
                    });
                }

            }).fail(function(jqxhr, textStatus, error) {
                alert( "Ошибка при получении данных.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
            })
        });
    });
</script>


</body>
</html>
