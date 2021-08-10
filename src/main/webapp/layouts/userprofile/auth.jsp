<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-5 mb-xl-10">
    <!--begin::Card header-->
    <div class="card-header">
        <div class="card-title m-0">
            <h3 class="fw-bolder m-0">Авторизация</h3>
        </div>
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9 text-start">

        <div class="row mb-7">
            <label class="col-lg-3 fw-bold fs-5 text-muted">Логин</label>
            <div class="col-lg-9">
                <span class="fw-bolder fs-5 text-dark">${requestScope.user.phoneNo}</span>
            </div>
        </div>
        <div class="row mb-7">
            <label class="col-lg-3 fw-bold fs-5 text-muted">Пароль</label>
            <div class="col-lg-9">
                <span class="fw-bolder fs-5 text-dark">*********</span>
            </div>
        </div>

    </div>
</div>