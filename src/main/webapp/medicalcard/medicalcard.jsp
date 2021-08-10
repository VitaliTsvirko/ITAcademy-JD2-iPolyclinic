<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.GenderType" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.UserRoles" %>

<html>
<head>
    <title>Медицинская карта - ${patientData.fullName}</title>
    <%@include file="../layouts/head.jsp"%>
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

            <div class="card w-100 mx-auto mb-2">
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
                <div class="card-body fs-5 text-start">
                    <%@include file="../layouts/medicalcard.jsp"%>
                </div>
            </div>

            <div class="card w-100 mx-auto">
                <div class="table table-row-dashed table-row-gray-300 gy-5 text-start">
                    <table class="table table-hover" id="patient_appointments">
                        <thead class="border-gray-200 fs-6 fw-bold bg-lighten">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Дата</th>
                            <th scope="col">Врач</th>
                            <th scope="col">Прием</th>
                            <th scope="col">Диагноз</th>
                            <th scope="col">Рекомендовано</th>
                            <th scope="col">Заключение</th>
                            <c:if test="${not authUser.userRole.equals(UserRoles.USER)}">
                                <th scope="col">Действия</th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody class="fs-6 fw-bold text-gray-700">
                        <c:forEach items="${appointmentsList}" var="app">
                            <tr>
                                <th scope="row">${app.id}</th>
                                <th>${app.date}</th>
                                <td>${app.doctorFullName}</td>
                                <td>${app.type}</td>
                                <td>${app.diagnosisName}</td>
                                <td>${app.therapy}</td>
                                <td>${app.healthStatus}</td>
                                <c:if test="${not authUser.userRole.equals(UserRoles.USER)}">
                                    <td>
                                        <a href="${pageContext.request.contextPath}/appointment/${app.id}" target="_blank"> <span class="btn btn-light-success fw-bold">Редактировать</span></a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

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
</html>
