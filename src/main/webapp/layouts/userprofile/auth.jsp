<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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