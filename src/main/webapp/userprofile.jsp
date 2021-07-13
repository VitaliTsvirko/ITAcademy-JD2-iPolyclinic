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
            </div>
            <!--end::tabs header-->

            <!--begin::card body-->
            <div class="card-body border-top p-3">

                <!--begin::notification-->
                <c:if test="${requestScope.userActivationState == null }">
                    <div class="alert alert-danger d-flex align-items-center mx-4" role="alert">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                        <div>
                            Ваш аккаунт не активирован. <br> Для доступа ко всем функциям необходимо обратиться в учреждение здравоохранения с паспортом или иным документом удостоверяющим личность.
                        </div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Закрыть"></button>
                    </div>
                </c:if>
                <!--end::notification-->

                <!--begin::tabs content-->
                <div class="tab-content mx-4" id="tabContent">

                    <!--begin::tabs overview-->
                    <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 fw-bold text-muted">ID</label>
                            <div class="col-lg-6">
                                <span class="fw-bolder fs-6 text-dark">${sessionScope.user.id}</span>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 fw-bold text-muted">ФИО</label>
                            <div class="col-lg-6">
                                <span class="fw-bolder fs-6 text-dark">${sessionScope.user.passport.name} ${sessionScope.user.passport.surname} ${sessionScope.user.passport.patronymic}</span>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 fw-bold text-muted">Дата рождения</label>
                            <div class="col-lg-6">
                                <span class="fw-bolder fs-6 text-dark">${sessionScope.user.passport.dateOfBirth}</span>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 fw-bold text-muted">Телефон</label>
                            <div class="col-lg-6">
                                <span class="fw-bolder fs-6 text-dark">${sessionScope.user.phoneNo}</span>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 fw-bold text-muted">Адресс</label>
                            <div class="col-lg-6">
                                <span class="fw-bolder fs-6 text-dark">${sessionScope.user.address.city} ${sessionScope.user.address.street} ${sessionScope.user.address.corpsNo} ${sessionScope.user.address.flatNo}</span>
                            </div>
                        </div>

                    </div>
                    <!--end::tabs overview-->

                    <!--begin::tabs auth-->
                    <div class="tab-pane fade show false" id="auth" role="tabpanel" aria-labelledby="auth-tab">
                        <div class="row mb-7">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Телефон</label>
                            <div class="col-lg-6">
                                <input type="text" name="phoneNo" class="form-control form-control-lg form-control-solid mb-3" value="${sessionScope.user.phoneNo}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Пароль</label>
                            <div class="col-lg-6">
                                <input type="password" name="password" class="form-control form-control-lg form-control-solid mb-3" value="${sessionScope.user.password}" readonly>
                            </div>
                        </div>
                    </div>
                    <!--begin::tabs auth-->


                    <div class="tab-pane fade" id="address" role="tabpanel" aria-labelledby="address-tab">
                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Страна</label>
                            <div class="col-lg-6">
                                <input type="text" name="country" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.country}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Город</label>
                            <div class="col-lg-6">
                                <input type="text" name="city" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.city}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Улица</label>
                            <div class="col-lg-6">
                                <input type="text" name="street" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.street}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Дом</label>
                            <div class="col-lg-1">
                                <input type="text" name="homeNo" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.homeNo}" readonly>
                            </div>

                            <label class="col-lg-1 col-form-label required fw-bold fs-6">Корпус</label>
                            <div class="col-lg-1">
                                <input type="text" name="corpsNo" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.corpsNo}" readonly>
                            </div>

                            <label class="col-lg-1 col-form-label required fw-bold fs-6">Квартира</label>
                            <div class="col-lg-1">
                                <input type="text" name="flatNo" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.address.flatNo}" readonly>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="passport" role="tabpanel" aria-labelledby="passport-tab">
                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Имя</label>
                            <div class="col-lg-8">
                                <input type="text" name="flatNo" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.passport.name}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Фамилия</label>
                            <div class="col-lg-8">
                                <input type="text" name="surname" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.passport.surname}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-lg-2 col-form-label required fw-bold fs-6">Отчество</label>
                            <div class="col-lg-8">
                                <input type="text" name="patronymic" class="form-control form-control-lg form-control-solid" value="${sessionScope.user.passport.patronymic}" readonly>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <a href="passport"> Добавить </a>
                        </div>


                    </div>
                </div>
                <!--end::tabs content-->
            </div>
            <!--end::card body-->
        </div>
        <!--end::tabs container-->
    </main>

    <%@include file="layouts/footer.jsp"%>
</body>
</html>
