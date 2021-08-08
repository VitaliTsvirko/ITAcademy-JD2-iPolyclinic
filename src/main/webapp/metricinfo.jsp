<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.HealthStatus" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils" %>

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
<input type="hidden" id="user_id" value="${requestScope.userId}">
<main class="container-md pt-5">



    <div class="row row-cols-2 row-cols-lg-5 g-4">
        <div class="col-3">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Обращений</h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">162</p>
                        <p class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-9">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">ИМТ</h5>
                    <div class="flex-center">
                        <p class="fs-6 m-0 text-black-50">sdkjfhkldjshflkjhsdalkhflskadhf dh fskjdlhf kjlsadh fjkasdh fjkahsd fjksdh kjhasd jhasdl khflksdajh flksadjh flkjashd flkjashd flkjasdh fklsadjhfsjdhf lsd</p>
                    </div>
                </div>
            </div>
        </div>



        <div class="col-12">
            <div class="card border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center"> Давление и пульс </h5>
                    <canvas id="line-chart" width="800" height="300"></canvas>
                </div>
            </div>
        </div>
    </div>


        <script>
            jQuery(function ($) {
                $(document).ready(function() {

                    let id = $("#user_id").val();

                    $.getJSON(BASE_URL + '/api/user/' + id + '/healthmetrics/weights', function (result, textStatus, jqXHR) {
                        if (!$.isEmptyObject(result)) {
                            //store data to page
                            new Chart(document.getElementById("line-chart"), {
                                type: 'line',
                                data: {
                                    labels: result.date,
                                    datasets: [{
                                        data: result.values,
                                        label: "Вес",
                                        borderColor: "#03A9F4",
                                        fill: false,
                                        cubicInterpolationMode: 'monotone',
                                        tension: 0.4
                                    }
                                    ]
                                },
                                options: {
                                    title: { display: false}
                                },
                                interaction: {
                                    intersect: false,
                                },
                            });
                        }
                    }).fail(function(jqxhr, textStatus, error) {
                        alert( "Ошибка при получении данных.\nError detail:\nHTTP status " + jqxhr.status + "\n error: " + error);
                    })
                });
            });
        </script>



</main>
<%@include file="/layouts/footer.jsp"%>

<script src="${pageContext.request.contextPath}/static/js/userhealth.js"></script>
</body>
</html>
