<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Медицинская карта - ${patientData.fullName}</title>
    <%@include file="../layouts/head.jsp"%>

</head>

<body class="bg-light">
<%@include file="../layouts/header.jsp"%>

<main class="container-md pt-5">

    <div class="card w-75 mx-auto mb-2">
        <h5 class="card-header" id="patient_full_name"><i class="bi bi-gender-male">${patientData.fullName}</i></h5>
        <div class="card-body">
            <%@include file="../layouts/medicalcard.jsp"%>
        </div>
    </div>

    <div class="card w-75 mx-auto">
        <div class="table-responsive">
            <table class="table table-hover" id="patient_appointments">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Врач</th>
                    <th scope="col">Прием</th>
                    <th scope="col">Диагноз</th>
                    <th scope="col">Рекомендовано</th>
                    <th scope="col">Заключение</th>
                    <th scope="col">Действия</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${appointmentsList}" var="app">
                        <tr>
                            <th scope="row">${app.id}</th>
                            <th>${app.date}</th>
                            <td>${app.doctorFullName}</td>
                            <td>${app.type}</td>
                            <td>${app.diagnosisName}</td>
                            <td>${app.therapy}</td>
                            <td>${app.healthStatus}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/appointment/${app.id}" target="_blank"> <span class="badge rounded-pill bg-primary">Редактировать</span></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</main>
    <%@include file="../layouts/footer.jsp"%>
</body>
</html>
