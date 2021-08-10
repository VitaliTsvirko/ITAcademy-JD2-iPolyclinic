<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.AppointmentType" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.HealthStatus" %>

<html>
<head>
    <title>Прием - ${patientData.fullName}</title>
    <%@include file="../layouts/head.jsp"%>

    <input type="hidden" id="user_id" value="${requestScope.userId}">
</head>

<!--begin::Body-->
<body class="bg-light">
<!--begin::Main-->
<div class="d-flex flex-column flex-root">

    <!--begin::Navigation-->
    <%@include file="../layouts/header.jsp"%>
    <!--end::Navigation-->

    <!--begin::Content-->
    <div class="d-flex flex-column flex-column-fluid text-center p-10 py-lg-20">
        <main class="container">

            <div class="card mb-2">
                <div class="card-header" id="patient_full_name">
                    <div class="card-title">
                        <c:choose>
                            <c:when test="${patientData.genderType eq GenderType.MALE}">
                                <i class="bi bi-gender-male"></i> ${patientData.fullName}
                            </c:when>
                            <c:otherwise>
                                <i class="bi bi-gender-female"></i> ${patientData.fullName}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="row g-4">
                <div class="col-3">
                    <div class = "card h-100 text-start">
                        <div class="card-header">
                            <div class="card-title">
                                Метрики
                                <div class="d-flex align-items-center">
                                    <a class="btn btn-icon btn-sm btn-color-info btn-active-light-primary align-self-center" data-bs-toggle="modal" data-bs-target="#addUserMetrics"><i class="bi bi-plus-square fs-4 me-2"></i></a>
                                </div>
                            </div>
                        </div>

                        <div class="card-body">
                            <div class="row my-3">
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-5 text-muted">Вес:</label>
                                </div>
                                <div class="col-lg-4">
                                    <p id="${HealthMetricsTypes.WEIGHT}_value" class="fw-bold fs-5"></p>
                                </div>
                                <div class="col-lg-1">
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.WEIGHT.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-6"></i></span></a>
                                </div>
                            </div>

                            <div class="row my-3">
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-5 text-muted">Рост:</label>
                                </div>
                                <div class="col-lg-4">
                                    <p id="${HealthMetricsTypes.HEIGHT}_value" class="fw-bold fs-5"></p>
                                </div>
                                <div class="col-lg-1">
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEIGHT.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-6"></i></span></a>
                                </div>
                            </div>
                            <hr>
                            <div class="row my-3">
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-5 text-muted">Давление:</label>
                                </div>
                                <div class="col-lg-4">
                                    <p><span id="${HealthMetricsTypes.AD_SYS}_value" class="fw-bold fs-5"></span> / <span id="${HealthMetricsTypes.AD_DIA}_value" class="fw-bold fs-5"></span></p>
                                </div>
                                <div class="col-lg-1">
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.AD_SYS.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-6"></i></span></a>
                                </div>
                            </div>

                            <div class="row my-3">
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-5 text-muted">Пульс:</label>
                                </div>
                                <div class="col-lg-4">
                                    <p id="${HealthMetricsTypes.HEART_RATE}_value" class="fw-bold fs-5"></p>
                                </div>
                                <div class="col-lg-1">
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.HEART_RATE.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-6"></i></span></a>
                                </div>
                            </div>
                            <hr>
                            <div class="row my-3">
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-5 text-muted">Температура:</label>
                                </div>
                                <div class="col-lg-4">
                                    <p id="${HealthMetricsTypes.BODY_TEMPERATURE}_value" class="fw-bold fs-6"></p>
                                </div>
                                <div class="col-lg-1">
                                    <a href="${pageContext.request.contextPath}/userhealth/${requestScope.userId}/healthmetrics/${HealthMetricsTypes.BODY_TEMPERATURE.name().toLowerCase()}" target="_blank" style="text-decoration : none"><span class="btn btn-icon btn-sm btn-color-info btn-active-light-primary"><i class="bi bi-info-square fs-6"></i></span></a>
                                </div>
                            </div>
                    </div>
                </div>
                </div>


                <div class="col-9">
                    <div class="card h-100 text-start">
                        <div class="card-header">
                            <div class="card-title">
                                Текущий прием
                            </div>
                        </div>
                        <div class="card-body text-start">

                            <div class="row my-3">
                                <div class="col-lg-3">
                                    <label class="fw-bold fs-6">Дата приема:</label>
                                </div>
                                <div class="col-lg-3">
                                    <label class="fw-bold fs-6">${appointmentDTO.date}</label>
                                </div>
                            </div>

                            <div class="row my-3">
                                <div class="col-lg-3">
                                    <label class="fw-bold fs-6">Время приема:</label>
                                </div>
                                <div class="col-lg-3">
                                    <label class="fw-bold fs-6">${appointmentDTO.time}</label>
                                </div>
                            </div>

                            <div class="row my-3">
                                <div class="col-lg-3">
                                    <label class="fw-bold fs-6">Врач:</label>
                                </div>
                                <div class="col-lg-5">
                                    <label class="fw-bold fs-6">${appointmentDTO.doctorFullName}</label>
                                </div>
                            </div>
                            <form id='form-appointment' action='#' method='post'>
                                <input type="hidden" id="id" value="${appointmentDTO.id}">
                                <input type="hidden" id="medical_card_id" value="${medicalCardId}">

                                <div class="row my-3">
                                    <div class="col-lg-3">
                                        <label class="fw-bold fs-6">Прием</label>
                                    </div>
                                    <div class="col-lg-9">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="appointmentType" id="type1" value="PRIMARY" ${appointmentDTO.type eq AppointmentType.PRIMARY ? 'checked' : ''} >
                                            <label class="form-check-label" for="type1">Первичный</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="appointmentType" id="type2" value="SECOND" ${appointmentDTO.type eq AppointmentType.SECOND ? 'checked' : ''}>
                                            <label class="form-check-label" for="type2">Повторный</label>
                                        </div>
                                    </div>
                                </div>

                                <div class="row my-3">
                                    <div class="col-lg-3">
                                        <label class="fw-bold fs-6">Жалобы:</label>
                                    </div>
                                    <div class="col-lg-9">
                                        <textarea class="form-control" name="complaints" id="complaints">${appointmentDTO.complaints}</textarea>
                                    </div>
                                </div>

                                <div class="row my-3">
                                    <div class="col-lg-3">
                                        <label class="fw-bold fs-6">Диагноз:</label>
                                        <input type="hidden" id="current_diagnosis_code" value="${appointmentDTO.diagnosisCode}">
                                        <input type="hidden" id="current_diagnosis_name" value="${appointmentDTO.diagnosisName}">
                                    </div>
                                    <div class="col-lg-9">
                                        <select name="diagnosis_code" class="js-diseases-search form-select-lg" style="width: 100%"></select>
                                    </div>
                                </div>

                                <div class="row my-3">
                                    <div class="col-lg-3">
                                        <label class="fw-bold fs-6">Рекомендовано:</label>
                                    </div>
                                    <div class="col-lg-9">
                                        <textarea name="therapy" id="therapy" class="form-control"> ${appointmentDTO.therapy} </textarea>
                                    </div>
                                </div>

                                <div class="row my-3">
                                    <div class="col-lg-3">
                                        <label class="fw-bold fs-6">Результат приема:</label>
                                    </div>
                                    <div class="col-lg-9">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="health_status" id="health_status1" value="HEALTHY" ${appointmentDTO.healthStatus eq HealthStatus.HEALTHY ? 'checked' : ''}>
                                            <label class="form-check-label" for="health_status1">Здоров</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="health_status" id="health_status2" value="SICK" ${appointmentDTO.healthStatus eq HealthStatus.SICK ? 'checked' : ''}>
                                            <label class="form-check-label" for="health_status2">Нуждается в лечении</label>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <div class="row m-3">
                                <div class="col-lg-12">
                                    <div id="address-buttons" class="d-grid gap-2 d-md-flex justify-content-md-end">
                                        <button type="button" class="btn btn-secondary" onClick="#">Отмена</button>
                                        <button type="button" class="btn btn-info" onClick="saveAppointmentData()">Сохранить</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="../layouts/modal/addhealthmetrics.jsp"%>

        </main>
    </div>
    <!--end::Content-->

    <!--begin::Footer-->
    <%@include file="../layouts/footer.jsp"%>
    <!--end::Footer-->

</div>
<!--end::Main-->
</body>
<!--end::Body-->

<script src="${pageContext.request.contextPath}/static/js/appointment.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/select2/css/select2.min.css">
<script src="${pageContext.request.contextPath}/static/select2/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/select2/js/i18n/ru.js"></script>

<script>
    $('.js-diseases-search').select2({
        placeholder: "Укажите диагноз",
        allowClear: true,
        language: "ru",
        minimumInputLength: 3,

        ajax: {
            url: BASE_URL + '/api/diseases/search',
            type: 'GET',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    param: params.term,
                };
            },
            processResults: function (data) {
                return {
                    results: data
                };
            },
        }
    });
</script>

<script>
    jQuery(function($){
        $(document).ready(function(){
            if ($('#current_diagnosis_code').val() !== ''){
                $('.js-diseases-search').select2("trigger", "select", {data : {id: $('#current_diagnosis_code').val(),
                        text: $('#current_diagnosis_name').val()}});
            }
        })
    });
</script>

</html>
