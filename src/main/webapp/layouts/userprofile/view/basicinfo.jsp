<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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