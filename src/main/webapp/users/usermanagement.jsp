<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>

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
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Паспорт</th>
                        <th scope="col">Роль</th>
                        <th scope="col">---</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${usersList}" var="usr">
                        <tr>
                            <th scope="row">${usr.id}</th>
                            <td>${usr.passport.name}</td>
                            <td>${usr.passport.surname}</td>
                            <td>${usr.phoneNo}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${usr.state eq ApplicationUserState.SIGNUP}">
                                        <i>---</i>
                                    </c:when>

                                    <c:when test="${usr.state eq ApplicationUserState.PASSPORT_DATA_VERIFIED or usr.state eq ApplicationUserState.ACTIVATED}">
                                        <i class="bi bi-check-circle-fill" style="color: #2ea44f" data-bs-toggle="tooltip" title="Паспортные данные подверждены"></i>
                                    </c:when>

                                    <c:otherwise>
                                        <i class="bi bi-exclamation-circle-fill" style="color: #842029" data-bs-toggle="tooltip" title="Паспортные данные не подверждены"></i>
                                    </c:otherwise>
                                </c:choose>
                                ${usr.passport.passportNo}
                            </td>
                            <td>${usr.userRole}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/userprofile/${usr.id}" target="_blank"> <span class="btn btn-info">Профиль</span></a>
                                <a href="${pageContext.request.contextPath}/medicalcard/${usr.medicalCard.id}" target="_blank"> <span class="btn btn-info">Медкарта</span></a>
                                <button type="button" class="btn btn-warning">Заблокировать</button>
                                <button type="button" class="btn btn-danger">Удалить</button>
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
