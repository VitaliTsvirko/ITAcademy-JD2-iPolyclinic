<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="../layouts/head.jsp"%>
</head>

<body class="bg-light">
<%@include file="../layouts/header.jsp"%>

<main class="container-md pt-5">

    <!--begin::card-->
    <div class="card mb-5 mb-xl-10">

        <div class="card-header border-0 cursor-pointer m-0 p-0">
            <div class="card-title m-3">
                <h3 class="fw-bolder mb-3 fs-4">Пациенты</h3>
            </div>
        </div>
        <!--begin::card body-->
        <div class="card-body border-top p-3">

            <!--begin::table container-->
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Возраст</th>
                        <th scope="col">Обращений</th>
                        <th scope="col">---</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${patientsList}" var="patient">
                        <tr>
                            <th scope="row">${patient.id}</th>
                            <td>${patient.name}</td>
                            <td>${patient.surname}</td>
                            <td>${patient.phoneNo}</td>
                            <td>${patient.age}</td>
                            <td>${appointmentsCounts[patient.id]}</td>
                            <td>
                              <a href="${pageContext.request.contextPath}/userprofile/${patient.id}" target="_blank"> <span class="badge rounded-pill bg-info text-dark">Профиль</span></a>
                              <span> </span>
                              <a href="${pageContext.request.contextPath}/medicalcard/${patient.medicalCardId}" target="_blank"> <span class="badge rounded-pill bg-success">Медкарта</span></a>
                              <span> </span>
                              <a href="${pageContext.request.contextPath}/medicalcard/${patient.medicalCardId}/appointment/new" target="_blank"> <span class="badge rounded-pill bg-primary">+Прием</span></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!--end::table container-->
        </div>
        <!--end::card body-->
    </div>
    <!--end::card-->

</main>
    <%@include file="../layouts/footer.jsp"%>
</body>
</html>
