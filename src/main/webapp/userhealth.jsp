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
                    <h5 class="card-title d-flex justify-content-between align-items-center">Возраст</h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.userAge}</p>
                        <p class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Вес
                        <div>
                            <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-circle"></i></button>
                            <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.WEIGHT.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                        </div>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.WEIGHT}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.WEIGHT}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.WEIGHT}_timestamp" class="fs-6 m-0 text-black-50">01/01/2021</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Рост
                        <div>
                            <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-circle"></i></button>
                            <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEIGHT.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                        </div>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.HEIGHT}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.HEIGHT}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.HEIGHT}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Давление
                        <div>
                            <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-circle"></i></button>
                            <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.AD_SYS.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                        </div>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p class="fs-2 m-0 fw-bolder d-flex justify-content-center">
                            <span id="${HealthMetricsTypes.AD_SYS}_value" data-toggle="counterUp"></span>/<span id="${HealthMetricsTypes.AD_DIA}_value" data-toggle="counterUp"></span>
                        </p>
                        <p id="${HealthMetricsTypes.AD_SYS}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        Пульс
                        <div>
                            <button type="button" class="btn btn-light" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-circle"></i></button>
                            <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEART_RATE.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                        </div>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.HEART_RATE}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.HEART_RATE}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.HEART_RATE}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>


        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">Температура</h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        ИМТ
                        <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.BMI.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.BMI}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.BMI}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.BMI}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        ${HealthMetricsTypes.PHYS_LEVEL.description}
                        <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.PHYS_LEVEL.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.PHYS_LEVEL}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.PHYS_LEVEL}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.PHYS_LEVEL}_timestamp" class="fs-6 m-0 text-black-50"></p>
                    </div>
                </div>
            </div>
        </div>


        <div class="col">
            <div class="card h-100 border-0">
                <div class="card-body">
                    <h5 class="card-title d-flex justify-content-between align-items-center">
                        ${HealthMetricsTypes.FUNC_CHANGE.description}
                        <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.FUNC_CHANGE.name().toLowerCase()}" target="_blank"><span class="btn btn-light"><i class="bi bi-info-circle-fill"></i></span></a>
                    </h5>
                    <div class="flex-center">
                        <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                        <p id="${HealthMetricsTypes.FUNC_CHANGE}_value" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                        <p id="${HealthMetricsTypes.FUNC_CHANGE}_analysis_text" class="fs-6 m-0 d-flex justify-content-center"></p>
                        <p id="${HealthMetricsTypes.FUNC_CHANGE}_timestamp" class="fs-6 m-0 text-black-50"></p>
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
    </div>

    <div class="modal fade" id="addUserMetrics" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Добавление данных</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id='form-user-metrics' action='#' method='post'>
                        <div class="row mb-7 p-2">
                            <label class="col-lg-5 col-form-label required fw-bold fs-6">Вес</label>
                            <div class="col-lg-4">
                                <input type="number" name="${HealthMetricsTypes.WEIGHT}" min="${HealthMetricsUtils.WEIGHT_MIN}" max="${HealthMetricsUtils.WEIGHT_MAX}"  placeholder="${HealthMetricsTypes.WEIGHT.unit}" class="form-control form-control-lg form-control-solid" />
                            </div>
                        </div>
                        <div class="row mb-7 p-2">
                            <label class="col-lg-5 col-form-label required fw-bold fs-6">Рост</label>
                            <div class="col-lg-4">
                                <input type="number" name="${HealthMetricsTypes.HEIGHT}" min="${HealthMetricsUtils.HEIGHT_MIN}" max="${HealthMetricsUtils.HEIGHT_MAX}"  placeholder="${HealthMetricsTypes.HEIGHT.unit}" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-7 p-2">
                            <label class="col-lg-5 col-form-label required fw-bold fs-6">Cистолическое АД</label>
                            <div class="col-lg-4">
                                <input type="number" name="${HealthMetricsTypes.AD_SYS}" min="${HealthMetricsUtils.DIA_AD_MIN}" max="${HealthMetricsUtils.SYS_AD_MAX}"  placeholder="${HealthMetricsTypes.AD_SYS.unit}" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                        <div class="row mb-7 p-2">
                            <label class="col-lg-5 col-form-label required fw-bold fs-6">Диастолическое АД</label>
                            <div class="col-lg-4">
                                <input type="numer" name="${HealthMetricsTypes.AD_DIA}" min="${HealthMetricsUtils.DIA_AD_MIN}" max="${HealthMetricsUtils.DIA_AD_MAX}"  placeholder="${HealthMetricsTypes.AD_DIA.unit}" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-7 p-2">
                            <label class="col-lg-5 col-form-label required fw-bold fs-6">Пульс</label>
                            <div class="col-lg-4">
                                <input type="text" name="${HealthMetricsTypes.HEART_RATE}" min="${HealthMetricsUtils.HEART_RATE_MIN}" max="${HealthMetricsUtils.HEART_RATE_MAX}"  placeholder="${HealthMetricsTypes.HEART_RATE.unit}" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                    <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onClick="addMetrics()">Добавить</button>
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

</body>
</html>
