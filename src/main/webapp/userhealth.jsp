<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.HealthStatus" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils" %>

<html>
<head>
    <title>Поликлиника</title>
    <%@include file="layouts/head.jsp"%>

    <!-- Counts JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
    <!-- Charts -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.0/chart.min.js" integrity="sha512-asxKqQghC1oBShyhiBwA+YgotaSYKxGP1rcSYTDrB0U6DxwlJjU59B67U8+5/++uFjcuVM8Hh5cokLjZlhm3Vg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <input type="hidden" id="user_id" value="${requestScope.userId}">
</head>

<!--begin::Body-->
<body class="bg-light">
<!--begin::Main-->
<div class="d-flex flex-column flex-root">

    <!--begin::Navigation-->
    <%@include file="layouts/header.jsp"%>
    <!--end::Navigation-->

    <!--begin::Content-->
    <div class="d-flex flex-column flex-column-fluid text-center p-10 py-lg-20">
        <main class="container">

            <div class="row row-cols-4 row-cols-lg-5 g-4">
                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">Обращений</h5>
                            <div class="flex-center">
                                <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                                <p id="appointment_total" class="fs-2 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp">${requestScope.appointmentTotal}</p>
                                <p id="appointment_last_date" class="fs-6 m-0 text-black-50 text-start">01/01/2021</p>
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
                                    <button type="button" class="btn btn-icon btn-sm btn-color-info btn-active-light-primary" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-square fs-4 me-2"></i></button>
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.WEIGHT.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                                </div>
                            </h5>
                            <div class="flex-center">

                            </div><p id="${HealthMetricsTypes.WEIGHT}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                            <p id="${HealthMetricsTypes.WEIGHT}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                            <p id="${HealthMetricsTypes.WEIGHT}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">
                                Рост
                                <div>
                                    <button type="button" class="btn btn-icon btn-sm btn-color-info btn-active-light-primary" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-square fs-4 me-2"></i></button>
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEIGHT.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                                </div>
                            </h5>
                            <div class="flex-center">
                                <p id="${HealthMetricsTypes.HEIGHT}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.HEIGHT}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.HEIGHT}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
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
                                    <button type="button" class="btn btn-icon btn-sm btn-color-info btn-active-light-primary" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-square fs-4 me-2"></i></button>
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.AD_SYS.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                                </div>
                            </h5>
                            <div class="flex-center">
                                <p class="fs-1 m-0 fw-bolder d-flex justify-content-center">
                                    <span id="${HealthMetricsTypes.AD_SYS}_value" data-toggle="counterUp"></span>/<span id="${HealthMetricsTypes.AD_DIA}_value" data-toggle="counterUp"></span>
                                </p>
                                <p id="${HealthMetricsTypes.AD_SYS}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
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
                                    <button type="button" class="btn btn-icon btn-sm btn-color-info btn-active-light-primary" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-square fs-4 me-2"></i></button>
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEART_RATE.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                                </div>
                            </h5>
                            <div class="flex-center">
                                <p id="${HealthMetricsTypes.HEART_RATE}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.HEART_RATE}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.HEART_RATE}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">Температура</h5>
                            <div class="flex-center">
                                <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">
                                ИМТ
                                <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.BMI.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                            </h5>
                            <div class="flex-center">
                                <p id="${HealthMetricsTypes.BMI}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.BMI}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.BMI}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">
                                ${HealthMetricsTypes.PHYS_LEVEL.description}
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.PHYS_LEVEL.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                            </h5>
                            <div class="flex-center">
                                <p id="${HealthMetricsTypes.PHYS_LEVEL}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.PHYS_LEVEL}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.PHYS_LEVEL}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col">
                    <div class="card h-100 border-0">
                        <div class="card-body">
                            <h5 class="card-title d-flex justify-content-between align-items-center">
                                ${HealthMetricsTypes.FUNC_CHANGE.description}
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.FUNC_CHANGE.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-4 me-2"></i></span></a>
                            </h5>
                            <div class="flex-center">
                                <span class="svg-icon svg-icon-3 svg-icon-success me-2"></span>
                                <p id="${HealthMetricsTypes.FUNC_CHANGE}_value" class="fs-1 m-0 fw-bolder d-flex justify-content-center" data-toggle="counterUp"></p>
                                <p id="${HealthMetricsTypes.FUNC_CHANGE}_analysis_text" class="fs-5 mb-1 d-flex justify-content-center text-success"></p>
                                <p id="${HealthMetricsTypes.FUNC_CHANGE}_timestamp" class="fs-6 m-0 text-black-50 text-start"></p>
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

            <%@include file="layouts/modal/addhealthmetrics.jsp"%>




        </main>
    </div>
    <!--end::Content-->

    <!--begin::Footer-->
    <%@include file="layouts/footer.jsp"%>
    <!--end::Footer-->

</div>
<!--end::Main-->
</body>
<!--end::Body-->

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

</html>