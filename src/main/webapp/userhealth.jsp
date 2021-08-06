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
<input type="hidden" id="user_id" value="${requestScope.userId}">
<main class="container-md pt-5">
    <div class="row row-cols-4 row-cols-lg-5 g-4">
        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Обращений</h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="appointment_total" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.appointmentTotal}</p>
                        <p id="appointment_last_date" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">ИМТ</h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="body_mass_index" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">65</p>
                        <p id="body_mass_index_ts" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Вес
                        <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-circle"></i></button>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="weight" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">65</p>
                        <p id="weight_ts" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Давление
                        <button type="button" class="btn btn-light"><i class="bi bi-plus-circle"></i></button>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center">
                            <span id="systolic_blood_pressure" data-toggle="counterUp">65</span>/<span id="diastolic_blood_pressure" data-toggle="counterUp">65</span>
                        </p>
                        <p id="blood_pressure_ts" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Пульс
                        <button type="button" class="btn btn-light"><i class="bi bi-plus-circle"></i></button>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="heart_rate" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">65</p>
                        <p id="heart_rate_ts" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-12">
            <div class="card border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        График обращений
                        <div>
                            <button type="button" class="btn btn-light"><i class="bi bi-table"></i></button>
                            <button type="button" class="btn btn-light"><i class="bi bi-bar-chart-fill"></i></button>
                        </div>
                    </h5>
                    <canvas id="bar-chart" width="800" height="200"></canvas>


                </div>
            </div>
        </div>


        <div class="col-12">
            <div class="card border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Давление и пульс
                        <div>
                            <button type="button" class="btn btn-light"><i class="bi bi-table"></i></button>
                            <button type="button" class="btn btn-light"><i class="bi bi-bar-chart-fill"></i></button>
                        </div>
                    </h5>
                    <canvas id="line-chart" width="800" height="200"></canvas>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="addUserMetrics" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">


                    <div class="row pt-5">
                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Вес</label>
                            <div class="col-lg-10">
                                <input type="text" name="city" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Рост</label>
                            <div class="col-lg-10">
                                <input type="text" name="city" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Давление</label>
                        <div class="col-lg-10">
                            <input type="text" name="city" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Давление</label>
                        <div class="col-lg-10">
                            <input type="text" name="city" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Пульс</label>
                        <div class="col-lg-10">
                            <input type="text" name="city" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>




                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Understood</button>
                </div>
            </div>
        </div>
    </div>



</main>
<%@include file="/layouts/footer.jsp"%>

<script src="${pageContext.request.contextPath}/static/js/userhealth.js"></script>

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


<script>
    new Chart(document.getElementById("line-chart"), {
        type: 'line',
        data: {
            labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15],
            datasets: [{
                data: [120,126,140,120,132,133,133,140,150,120,133,133,140,150,120],
                label: "Давление",
                borderColor: "#03A9F4",
                fill: false,
                cubicInterpolationMode: 'monotone',
                tension: 0.4
            }, {
                data: [80,90,100,80,80,90,86,100,76,82,83,84,85,78,90],
                label: "Давление",
                borderColor: "#5C6BC0",
                fill: false,
                cubicInterpolationMode: 'monotone',
                tension: 0.4
            }, {
                data: [77,77,89,87,93,106,105,103,112,140,122,87,90,77,65],
                label: "Пульс",
                borderColor: "#FF5722",
                backgroundColor: "#FFEBEE",
                fill: true,
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
</script>


</body>
</html>
