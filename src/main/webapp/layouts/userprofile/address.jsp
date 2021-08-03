<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="address-tab-alert" class="mt-3"></div>

<div class="${not empty requestScope.user.address ? 'collapse' : ''}" id="div-address-add-btn">
    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addressModalForm">Добавить</button>
</div>

<div class="${empty requestScope.user.address ? 'collapse' : ''}" id="tap-address-data">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#addressModalForm">Измениить</button>
        <button type="button" class="btn btn-danger" onclick='deleteAddress($("#user_id").val())'>Удалить</button>
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



<div class="modal fade" id="addressModalForm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Адрес</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

                <form id='form-address' action='#' method='post'>
                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Страна</label>
                        <div class="col-lg-10">
                            <select class="form-select" name="country_code" aria-label="Страна" required>
                                <c:forEach items="${requestScope.countriesMap}" var="country">
                                    <option ${country.key.equalsIgnoreCase(requestScope.user.address.country.code) ? "selected" : ""} value="${country.key}">${country.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Город</label>
                        <div class="col-lg-10">
                            <input type="text" name="city" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.city}" >
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Улица</label>
                        <div class="col-lg-10">
                            <input type="text" name="street" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.street}" >
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Дом</label>
                        <div class="col-lg-2">
                            <input type="text" name="home_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.homeNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Корпус</label>
                        <div class="col-lg-2">
                            <input type="text" name="corp_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.corpsNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Квартира</label>
                        <div class="col-lg-2">
                            <input type="text" name="flat_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.flatNo}" >
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" class='btn btn-primary' data-bs-dismiss="modal" onclick='saveAddressData($("#user_id").val())'>Сохранить</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/userprofile/address.js" type="application/javascript"></script>



