<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="layouts/head.jsp"%>
</head>

<body class="bg-light">
<%@include file="layouts/header.jsp"%>
<link href="static/css/userprofile.css" rel="stylesheet">

<main class="container-md pt-5">

    <!--begin::card-->
    <div class="card mb-5 mb-xl-10">

        <div class="card-header border-0 cursor-pointer m-0 p-0">
            <div class="card-title m-3">
                <h3 class="fw-bolder mb-3 fs-4">Пользователи</h3>
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
                        <th scope="col">Логин</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Обращений</th>
                        <th scope="col">---</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${usersList}" var="usr">
                        <tr>
                            <th scope="row">${usr.id}</th>
                            <td>${usr.phoneNo}</td>
                            <td>${usr.passport.name}</td>
                            <td>${usr.passport.surname}</td>
                            <td>${appointmentsCounts[usr.id]}</td>
                            <td>
                              <a href="${pageContext.request.contextPath}/userprofile/${usr.id}" target="_blank"> <span class="badge rounded-pill bg-info text-dark">Профиль</span></a>
                              <span> </span>
                              <a href="${pageContext.request.contextPath}/medicalcard/${usr.medicalCard.id}" target="_blank"> <span class="badge rounded-pill bg-success">Медкарта</span></a>
                              <span> </span>
                              <a href="${pageContext.request.contextPath}/medicalcard/${usr.medicalCard.id}/appointment/new" target="_blank"> <span class="badge rounded-pill bg-primary">+Прием</span></a>
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
    <%@include file="layouts/footer.jsp"%>
</body>
</html>
