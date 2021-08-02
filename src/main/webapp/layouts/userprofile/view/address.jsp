<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="${not empty requestScope.user.address ? 'collapse' : ''}" id="div-address-add-btn">
    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addressModalForm">Добавить</button>
</div>

<div class="${empty requestScope.user.address ? 'collapse' : ''}" id="tap-address-data">
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