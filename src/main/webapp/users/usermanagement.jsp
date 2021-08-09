<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>

<html>
<head>
    <title>Пользователи</title>
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

            <div class="card-header border-0 cursor-pointer m-0 p-0">
                <div class="card-title m-3">
                    <h3 class="fw-bolder mb-3 fs-4">Пользователи</h3>
                </div>
            </div>

            <div class="card w-100 mx-auto">
                <div class="table table-row-dashed table-row-gray-300 gy-5 text-start">
                    <table class="table table-hover" id="patient_appointments">
                        <thead class="border-gray-200 fs-6 fw-bold bg-lighten">
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
                        <tbody class="fs-6 fw-bold text-gray-700">
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
                                    <a href="${pageContext.request.contextPath}/userprofile/${usr.id}" target="_blank"> <span class="btn btn-light-info fw-bold me-2">Профиль</span></a>
                                    <a href="${pageContext.request.contextPath}/medicalcard/${usr.medicalCard.id}" target="_blank"> <span class="btn btn-light-primary fw-bold me-2">Медкарта</span></a>
                                </td>
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
