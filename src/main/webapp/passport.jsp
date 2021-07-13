<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="layouts/head.jsp"%>
</head>

<body class="bg-light">
    <%@include file="layouts/header.jsp"%>

<main class="container-md pt-5">

    <div class="card border-0">
        <div class="card-body">
            <div class="card-title m-0">
                <h3 class="fw-bolder m-0">Паспортные данные</h3>
            </div>
        </div>

        <div class="card-body">
            <form:form class="form-horizontal" method="POST"
                  modelAttribute="userPassport" action="${passportActionUrl}">


                <div class="row">
                    <div class="col-lg-6">
                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Имя</label>
                            <div class="col-8">
                                <form:input path="name" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
                            <div class="col-8">
                                <form:input path="surname" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
                            <div class="col-8">
                                <form:input path="patronymic" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
                            <div class="col-8">
                                <form:input path="dateOfBirth" type="date" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Место рождения</label>
                            <div class="col-8">
                                <form:input path="placeOfBirth" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Национальность</label>
                            <div class="col-8">
                                <form:input path="nationality" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                    </div>
                </div>



                <div class="row pt-5">
                    <div class="col-lg-6">
                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
                            <div class="col-8">
                                <form:input path="personalNo" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
                            <div class="col-8">
                                <form:input path="passportNo" type="text" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
                            <div class="col-8">
                                <form:select class="form-select" path="countryOfIssue" aria-label="Страна выдачи">
                                    <form:option value="---" label="Укажите страну"/>
                                    <form:options items="${countriesMap}"/>
                                </form:select>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Дата выдачи</label>
                            <div class="col-8">
                                <form:input path="issueDate" type="date" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>

                        <div class="row mb-7 p-3">
                            <label class="col-4 col-form-label required fw-bold fs-6">Действителен до</label>
                            <div class="col-8">
                                <form:input path="expirationDate" type="date" class="form-control form-control-lg form-control-solid"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>

        <div class="card-body d-flex justify-content-end py-6 px-9">
            <button class="btn btn-lg btn-light me-2"><a href="index.jsp">Отмена</a></button>
            <button type="submit" class="btn btn-lg btn-primary">Добавить</button>
        </div>
    </div>
</main>

    <%@include file="layouts/footer.jsp"%>
</body>
</html>
