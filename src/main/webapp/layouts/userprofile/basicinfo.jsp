<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-5 mb-xl-10">
    <!--begin::Card header-->
    <div class="card-header">
        <div class="card-title m-0">
            <h4 class="fw-bolder m-0">Общие сведения</h4>
        </div>
    </div>
    <!--end::Card header-->

    <!--begin::Card body-->
    <div class="card-body p-9 text-start">

        <div class="row mb-7">
            <label class="col-lg-3 fw-bold text-muted">ID</label>
            <div class="col-lg-9">
                <span class="fw-bolder fs-5 text-dark">${requestScope.user.id}</span>
            </div>
        </div>
        <div class="row mb-7">
            <label class="col-lg-3 fw-bold text-muted">ФИО</label>
            <div class="col-lg-9">
                <span id="info-full_name" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.name} ${requestScope.user.passport.surname} ${requestScope.user.passport.patronymic}</span>
            </div>
        </div>
        <div class="row mb-7">
            <label class="col-lg-3 fw-bold text-muted">Дата рождения</label>
            <div class="col-lg-9">
                <span id="info-date_of_birth" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.dateOfBirth}</span>
            </div>
        </div>
        <div class="row mb-7">
            <label class="col-lg-3 fw-bold text-muted">Телефон</label>
            <div class="col-lg-9">
                <span id="info-phone_no" class="fw-bolder fs-5 text-dark">${requestScope.user.phoneNo}</span>
            </div>
        </div>
        <div class="row mb-7">
            <label class="col-lg-3 fw-bold text-muted">Адресс</label>
            <div class="col-lg-9">
                <span id="info-address" class="fw-bolder fs-5 text-dark">${requestScope.user.address.city} ${requestScope.user.address.street} ${requestScope.user.address.corpsNo} ${requestScope.user.address.flatNo}</span>
            </div>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/userprofile/basicinfo.js" type="application/javascript"></script>