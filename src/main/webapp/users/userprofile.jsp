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
<link href="../static/css/userprofile.css" rel="stylesheet">

<input type="hidden" id="user_id" value="${requestScope.user.id}">
<main class="container-md pt-5">

<!--begin::tabs container-->
<div class="card mb-5 mb-xl-10">

    <div id="userProfileModalTabsHeader" class="card-header border-0 cursor-pointer m-0 p-0">
        <div class="card-title m-3">
            <h3 class="fw-bolder mb-3 fs-4">Профиль пользователя</h3>
        </div>

        <!--begin::notification-->
        <c:if test="${user.state != ApplicationUserState.ACTIVATED }">
            <div id="userprofile-alert" class="alert alert-danger d-flex align-items-center mx-4" role="alert">
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
                <a class="nav-link" id="passport-tab" data-bs-toggle="tab" href="#passport" role="tab" aria-controls="passport" aria-selected="false">
                    Паспортные данные
                    <i id="passport-tab-badge" class="bi bi-exclamation-circle-fill" style="color: #842029" data-bs-toggle="tooltip" title="Паспортные данные не подверждены"></i>
                </a>
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
                <%@include file="../layouts/userprofile/basicinfo.jsp"%>
            </div>
            <!--end::tabs overview-->

            <!--begin::tabs auth-->
            <div class="tab-pane fade" id="auth" role="tabpanel" aria-labelledby="auth-tab">
                <%@include file="../layouts/userprofile/auth.jsp"%>
            </div>
            <!--begin::tabs auth-->

            <!--begin::tabs address-->
            <div class="tab-pane fade" id="address" role="tabpanel" aria-labelledby="address-tab">
                <%@include file="../layouts/userprofile/address.jsp"%>
            </div>
            <!--end::tabs address-->

            <!--begin::tabs passport-->
            <div class="tab-pane fade" id="passport" role="tabpanel" aria-labelledby="passport-tab">
                <%@include file="../layouts/userprofile/passport.jsp"%>
            </div>
            <!--end::tabs passport-->

        </div>
        <!--end::tabs content-->
    </div>
    <!--end::card body-->
</div>
<!--end::tabs container-->

</main>
    <%@include file="../layouts/footer.jsp"%>

<script>
    jQuery(function($){
        $(document).ready(function(){
            // Listen with the jQuery to the tabs click:
            $('#userProfileModalTabsHeader a').click(function (link) {
                //remove all alerts
                $('#address-tab-alert').empty();
                $('#passport-tab-alert').empty();
            })
        })
    });
</script>
</body>
</html>
