<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Профиль пользователя</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
</head>

<body class="d-flex flex-column h-100">
    <%@include file="header.jsp"%>

    <link href="static/css/userprofile.css" rel="stylesheet">

    <main class="container">

        <spring:url value="/passport" var="passportActionUrl" />

        <form:form class="form-horizontal" method="POST"
                    modelAttribute="userPassport" action="${passportActionUrl}">

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Имя</label>
                <div class="col-lg-6">
                    <form:input path="name" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Фамилия</label>
                <div class="col-lg-6">
                    <form:input path="surname" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Отчество</label>
                <div class="col-lg-6">
                    <form:input path="patronymic" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Личный номер</label>
                <div class="col-lg-6">
                    <form:input path="personalNo" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Номер паспорта</label>
                <div class="col-lg-6">
                    <form:input path="passportNo" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Страна выдачи</label>
                <div class="col-lg-6">
                    <form:select class="form-select" path="countryOfIssue" aria-label="Страна выдачи">
                        <form:option value="---" label="Укажите страну"/>
                        <form:options items="${countriesMap}"/>
                    </form:select>

                </div>

            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Национальность</label>
                <div class="col-lg-6">
                    <form:input path="nationality" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Дата рождения</label>
                <div class="col-lg-6">
                    <form:input path="dateOfBirth" type="date" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Дата выдачи</label>
                <div class="col-lg-6">
                    <form:input path="issueDate" type="date" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Действителен до</label>
                <div class="col-lg-6">
                    <form:input path="expirationDate" type="date" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-lg-2 col-form-label required fw-bold fs-6">Место рождения</label>
                <div class="col-lg-6">
                    <form:input path="placeOfBirth" type="text" class="form-control form-control-lg form-control-solid"/>
                </div>
            </div>


            <button type="submit" class="btn-lg btn-primary pull-right">Добавить</button>


        </form:form>



    </main>

    <%@include file="footer.jsp"%>
</body>
</html>
