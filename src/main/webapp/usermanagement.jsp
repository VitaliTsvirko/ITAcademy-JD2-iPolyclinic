<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="layouts/head.jsp"%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/core/core.js" type="application/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/userprofile/user.js" type="application/javascript"></script>

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
                            <td>
                                <button type="button" class="btn btn-info" data-bs-toggle="modal" id="${usr.id}" data-bs-target="#userProfileModal" onClick="loadUserProfileData(this.id)">Профиль</button>
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



<div class="modal fade" id="userProfileModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Профиль пользователя</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">


                    <div id="userProfileModalTabsHeader" class="card-header border-0 cursor-pointer m-0 p-0">
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
                                        <span id="info-id" class="fw-bolder fs-6 text-dark">${requestScope.user.id}</span>
                                    </div>
                                </div>

                                <div class="row mb-7 p-3">
                                    <label class="col-lg-2 fw-bold text-muted">ФИО</label>
                                    <div class="col-lg-6">
                                        <span id="info-full_name" class="fw-bolder fs-6 text-dark"> </span>
                                    </div>
                                </div>

                                <div class="row mb-7 p-3">
                                    <label class="col-lg-2 fw-bold text-muted">Дата рождения</label>
                                    <div class="col-lg-6">
                                        <span id="info-date_of_birth" class="fw-bolder fs-6 text-dark"> </span>
                                    </div>
                                </div>

                                <div class="row mb-7 p-3">
                                    <label class="col-lg-2 fw-bold text-muted">Телефон</label>
                                    <div class="col-lg-6">
                                        <span id="info-phone_no" class="fw-bolder fs-6 text-dark"> </span>
                                    </div>
                                </div>

                                <div class="row mb-7 p-3">
                                    <label class="col-lg-2 fw-bold text-muted">Статус</label>
                                    <div class="col-lg-6">
                                        <span id="info-user_state" class="fw-bolder fs-6 text-dark"> </span>
                                    </div>
                                </div>

                                <div class="row mb-7 p-3">
                                    <label class="col-lg-2 fw-bold text-muted">Роль</label>
                                    <div class="col-lg-6">
                                        <span id="info-user_role" class="fw-bolder fs-6 text-dark"> </span>
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
                                <div id="address-tab-alert"></div>
                                <form id='form-address' action='#' method='post'>
                                    <div class="row mb-7 p-3">
                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Страна</label>
                                        <div class="col-lg-10">
                                            <select class="form-select" name="country_code" aria-label="Страна" required>
                                                <option value="">Укажите страну</option>
                                                <c:forEach items="${requestScope.countriesMap}" var="country">
                                                    <option ${country.key.equalsIgnoreCase(requestScope.user.address.country.code) ? "selected" : ""} value="${country.key}">${country.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-7 p-3">
                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Город</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="city" id="city" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.city}" >
                                        </div>
                                    </div>

                                    <div class="row mb-7 p-3">
                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Улица</label>
                                        <div class="col-lg-10">
                                            <input type="text" name="street" id="street" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.street}" >
                                        </div>
                                    </div>

                                    <div class="row mb-7 p-3">
                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Дом</label>
                                        <div class="col-lg-2">
                                            <input type="text" name="home_no" id="home_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.homeNo}" >
                                        </div>

                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Корпус</label>
                                        <div class="col-lg-2">
                                            <input type="text" name="corp_no" id="corp_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.corpsNo}" >
                                        </div>

                                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Квартира</label>
                                        <div class="col-lg-2">
                                            <input type="text" name="flat_no" id="flat_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.flatNo}" >
                                        </div>
                                    </div>
                                </form>

                                <div id="address-buttons" class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
                                    <button type="button" class="btn btn-danger" onClick="deleteAddress(this.id)">Удалить</button>
                                    <button type="button" class="btn btn-info" onClick="saveAddressData(this.id)">Сохранить</button>
                                </div>
                            </div>
                            <!--end::tabs address-->

                            <!--begin::tabs passport-->
                            <div class="tab-pane fade" id="passport" role="tabpanel" aria-labelledby="passport-tab">
                                <div id="passport-tab-alert"></div>
                                <form id='form-passport' action='#' method='post'>
                                    <div class="row">
                                        <div class="col-lg-6">
                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Имя</label>
                                                <div class="col-8">
                                                    <input name="name" id="name" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.name}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
                                                <div class="col-8">
                                                    <input name="surname" id="surname" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.surname}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
                                                <div class="col-8">
                                                    <input name="patronymic" id="patronymic" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.patronymic}">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
                                                <div class="col-8">
                                                    <input name="date_of_birth" id="date_of_birth" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.dateOfBirth}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Место рождения</label>
                                                <div class="col-8">
                                                    <input name="place_of_birth" id="place_of_birth" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.placeOfBirth}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Национальность</label>
                                                <div class="col-8">
                                                    <input name="nationality" id="nationality" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.nationality}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row pt-5">
                                        <div class="col-lg-6">
                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
                                                <div class="col-8">
                                                    <input name="personal_no" id="personal_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.personalNo}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
                                                <div class="col-8">
                                                    <input name="passport_no" id="passport_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.passportNo}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
                                                <div class="col-8">
                                                    <select class="form-select" name="country_of_issue_code" aria-label="Страна" required>
                                                        <c:forEach items="${requestScope.countriesMap}" var="country">
                                                            <option ${country.key.equalsIgnoreCase(requestScope.user.address.country.code) ? "selected" : ""} value="${country.key}">${country.value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Дата выдачи</label>
                                                <div class="col-8">
                                                    <input name="issue_date" id="issue_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.issueDate}">
                                                </div>
                                            </div>

                                            <div class="row mb-7 p-3">
                                                <label class="col-4 col-form-label required fw-bold fs-6">Действителен до</label>
                                                <div class="col-8">
                                                    <input name="expiration_date" id="expiration_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.expirationDate}">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <div id="passport-buttons" class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="button" class="btn btn-danger" onClick="deletePassport(this.id)">Удалить</button>
                                    <button type="button" class="btn btn-info" onClick="savePassportData(this.id)">Сохранить</button>
                                    <button type="button" class="btn btn-info" onClick="confirmPassportData(this.id)">Подвердить данные</button>
                                </div>
                            </div>
                            <!--end::tabs passport-->
                        </div>
                        <!--end::tabs content-->
                    </div>
                    <!--end::card body-->
                </div>
                <!--end::tabs container-->
            </div>
        </div>
    </div>




</main>

    <%@include file="layouts/footer.jsp"%>

</body>
</html>
