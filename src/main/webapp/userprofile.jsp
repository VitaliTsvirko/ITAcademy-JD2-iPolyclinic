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

<!--begin::tabs container-->
<div class="card mb-5 mb-xl-10">

    <div class="card-header border-0 cursor-pointer m-0 p-0">
        <div class="card-title m-3">
            <h3 class="fw-bolder mb-3 fs-4">Профиль пользователя</h3>
        </div>

        <!--begin::notification-->
        <c:if test="${requestScope.userActivationState == false }">
            <div class="alert alert-danger d-flex align-items-center mx-4" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                <div>
                    Ваш аккаунт не активирован. <br> Для доступа ко всем функциям необходимо обратиться в учреждение здравоохранения с паспортом или иным документом удостоверяющим личность.
                </div>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Закрыть"></button>
            </div>
        </c:if>
        <!--end::notification-->


        <!--begin::tabs header-->
        <ul class="nav nav-tabs m-0" id="tab" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="overview-tab" data-bs-toggle="tab" href="#overview" role="tab" aria-controls="overview" aria-selected="true">Основные</a>
            </li>

            <li class="nav-item" role="presentation">
                <a class="nav-link" id="auth-tab" data-bs-toggle="tab" href="#auth" role="tab" aria-controls="auth" aria-selected="false">Авторизация</a>
            </li>

            <li class="nav-item" role="presentation">
                <a class="nav-link" id="address-tab" data-bs-toggle="tab" href="#address" role="tab" aria-controls="address" aria-selected="false">Адрес</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="passport-tab" data-bs-toggle="tab" href="#passport" role="tab" aria-controls="passport" aria-selected="false">Паспортные данные</a>
            </li>
        </ul>
        <!--end::tabs header-->
    </div>
    <!--end::tabs header-->

    <!--begin::card body-->
    <div class="card-body border-top p-3">
        <!--begin::tabs content-->
        <div class="tab-content mx-4" id="tabContent">

            <!--begin::tabs overview-->
            <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                <div class="row mb-7 p-3">
                    <label class="col-lg-2 fw-bold text-muted">ID</label>
                    <div class="col-lg-6">
                        <span class="fw-bolder fs-6 text-dark">${requestScope.user.id}</span>
                    </div>
                </div>

                <div class="row mb-7 p-3">
                    <label class="col-lg-2 fw-bold text-muted">ФИО</label>
                    <div class="col-lg-6">
                        <span class="fw-bolder fs-6 text-dark">${requestScope.user.passport.name} ${requestScope.user.passport.surname} ${requestScope.user.passport.patronymic}</span>
                    </div>
                </div>

                <div class="row mb-7 p-3">
                    <label class="col-lg-2 fw-bold text-muted">Дата рождения</label>
                    <div class="col-lg-6">
                        <span class="fw-bolder fs-6 text-dark">${requestScope.user.passport.dateOfBirth}</span>
                    </div>
                </div>

                <div class="row mb-7 p-3">
                    <label class="col-lg-2 fw-bold text-muted">Телефон</label>
                    <div class="col-lg-6">
                        <span class="fw-bolder fs-6 text-dark">${requestScope.user.phoneNo}</span>
                    </div>
                </div>

                <div class="row mb-7 p-3">
                    <label class="col-lg-2 fw-bold text-muted">Адресс</label>
                    <div class="col-lg-6">
                        <span class="fw-bolder fs-6 text-dark">${requestScope.user.address.city} ${requestScope.user.address.street} ${requestScope.user.address.corpsNo} ${requestScope.user.address.flatNo}</span>
                    </div>
                </div>

            </div>
            <!--end::tabs overview-->

            <!--begin::tabs auth-->
            <div class="tab-pane fade" id="auth" role="tabpanel" aria-labelledby="auth-tab">
                <div class="row mb-7">
                    <label class="col-lg-2 col-form-label required fw-bold fs-6">Телефон</label>
                    <div class="col-lg-6">
                        <input type="text" name="phoneNo" class="form-control form-control-lg form-control-solid mb-3" value="${requestScope.user.phoneNo}" readonly>
                    </div>
                </div>

                <div class="row mb-7">
                    <label class="col-lg-2 col-form-label required fw-bold fs-6">Пароль</label>
                    <div class="col-lg-6">
                        <input type="password" name="password" class="form-control form-control-lg form-control-solid mb-3" value="password" readonly>
                    </div>
                </div>
            </div>
            <!--begin::tabs auth-->


            <!--begin::tabs address-->
            <div class="tab-pane fade" id="address" role="tabpanel" aria-labelledby="address-tab">

                <div class="${empty requestScope.user.address ? "collapse" : ""}" id="div-address-add-btn">
                    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addressModalForm">Добавить</button>
                </div>

                <div class="${not empty requestScope.user.address ? "collapse" : ""}" id="tap-address-data">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addressModalForm">Измениить</button>
                        <button type="button" class="btn btn-danger" id='form-address-delete-btn'>Удалить</button>
                    </div>
                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6 text-muted">Страна</label>

                        <div class="col-lg-6">
                            <span id="country_name" class="fw-bolder fs-6 text-dark">${requestScope.user.address.country.shotName}</span>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6 text-muted">Город</label>
                        <div class="col-lg-6">
                            <span id="city" class="fw-bolder fs-6 text-dark">${requestScope.user.address.city}</span>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6 text-muted">Улица</label>
                        <div class="col-lg-6">
                            <span id="street" class="fw-bolder fs-6 text-dark">${requestScope.user.address.street}</span>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6 text-muted">Дом</label>
                        <div class="col-lg-1">
                            <span id="home_no" class="fw-bolder fs-6 text-dark">${requestScope.user.address.homeNo}</span>
                        </div>

                        <label class="col-lg-1 col-form-label required fw-bold fs-6 text-muted">Корпус</label>
                        <div class="col-lg-1">
                            <span id="corp_no" class="fw-bolder fs-6 text-dark">${requestScope.user.address.corpsNo}</span>
                        </div>

                        <label class="col-lg-1 col-form-label required fw-bold fs-6 text-muted">Квартира</label>
                        <div class="col-lg-1">
                            <span id="flat_no" class="fw-bolder fs-6 text-dark">${requestScope.user.address.flatNo}</span>
                        </div>
                    </div>
                </div>
            </div>
            <!--end::tabs address-->

            <!--begin::tabs passport-->
            <div class="tab-pane fade" id="passport" role="tabpanel" aria-labelledby="passport-tab">
                <%@include file="layouts/userprofile/view/passport.jsp"%>
            </div>
            <!--end::tabs passport-->
        </div>
        <!--end::tabs content-->
    </div>
    <!--end::card body-->
</div>
<!--end::tabs container-->

<%@include file="layouts/userprofile/forms/address.jsp"%>
<%@include file="layouts/userprofile/forms/passport.jsp"%>
</main>

    <%@include file="layouts/footer.jsp"%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/core/core.js" type="application/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/userprofile/address.js" type="application/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/userprofile/passport.js" type="application/javascript"></script>
</body>
</html>
