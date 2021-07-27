<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Медицинская карта</title>
    <%@include file="layouts/head.jsp"%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/core/core.js" type="application/javascript"></script>

</head>

<body class="bg-light">
<%@include file="layouts/header.jsp"%>
<link href="static/css/userprofile.css" rel="stylesheet">

<main class="container-md pt-5">

    <div class="card mb-2">
        <h5 class="card-header" id="patient_full_name"></h5>
        <div class="card-body">
            <span class="card-text m-0" id="patient_age_date_of_birth"></span>
            <span class="card-text m-0" id="patient_address"></span>
        </div>
    </div>

    <div class="card">
        <div class="table-responsive">
            <table class="table table-hover" id="patient_appointments">
                <thead>
                <tr>
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
                            <th scope="row">${app.date}</th>
                            <td>${app.doctorFullName}</td>
                            <td>${app.type}</td>
                            <td>${app.diagnosis.name}</td>
                            <td>${app.therapy}</td>
                            <td>${app.healthStatus}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

    </div>



</main>

    <%@include file="layouts/footer.jsp"%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/medicalcard.js" type="application/javascript"></script>

</body>
</html>
