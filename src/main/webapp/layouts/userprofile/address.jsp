<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-5 mb-xl-10">
    <!--begin::Card header-->
    <div class="card-header cursor-pointer">
        <div class="card-title m-0">
            <h3 class="fw-bolder m-0">Адрес</h3>
        </div>
        <div class="d-flex align-items-center">
            <a id="address-add-btn" class="btn btn-light-info align-self-center ${not empty requestScope.user.address ? 'collapse' : ''}" data-bs-toggle="modal" data-bs-target="#addressModalForm">Добавить</a>
            <a id="address-edit-btn" class="btn btn-light-info align-self-center me-2 ${empty requestScope.user.address ? 'collapse' : ''}" data-bs-toggle="modal" data-bs-target="#addressModalForm">Измениить</a>
            <a id="address-delete-btn" class="btn btn-light-danger align-self-center me-2 ${empty requestScope.user.address ? 'collapse' : ''}" onclick='deleteAddress($("#user_id").val())'>Удалить</a>
        </div>
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9 text-start">

        <div id="address-tab-alert" class="mt-3"></div>
        <div class="text-start ${empty requestScope.user.address ? 'collapse' : ''}" id="tap-address-data">
            <div class="row mb-7">
                <label class="col-lg-2 fw-bold fs-5 text-muted">Страна</label>
                <div class="col-lg-10">
                    <span id="country_name" class="fw-bolder fs-5 text-dark">${requestScope.user.address.country.shotName}</span>
                </div>
            </div>
            <div class="row mb-7">
                <label class="col-lg-2 fw-bold fs-5 text-muted">Город</label>
                <div class="col-lg-10">
                    <span id="city" class="fw-bolder fs-5 text-dark">${requestScope.user.address.city}</span>
                </div>
            </div>

            <div class="row mb-7">
                <label class="col-lg-2 fw-bold fs-5 text-muted">Улица</label>
                <div class="col-lg-10">
                    <span id="street" class="fw-bolder fs-5 text-dark">${requestScope.user.address.street}</span>
                </div>
            </div>

            <div class="row mb-7">
                <label class="col-lg-2 fw-bold fs-5 text-muted">Дом</label>
                <div class="col-lg-1">
                    <span id="home_no" class="fw-bolder fs-5 text-dark">${requestScope.user.address.homeNo}</span>
                </div>

                <label class="col-lg-1 fw-bold fs-5 text-muted">Корпус</label>
                <div class="col-lg-1">
                    <span id="corp_no" class="fw-bolder fs-5 text-dark">${requestScope.user.address.corpsNo}</span>
                </div>

                <label class="col-lg-1 fw-bold fs-5 text-muted">Квартира</label>
                <div class="col-lg-6">
                    <span id="flat_no" class="fw-bolder fs-5 text-dark">${requestScope.user.address.flatNo}</span>
                </div>
            </div>
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
                    <div class="row mb-7">
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

                    <div class="row mb-7">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Город</label>
                        <div class="col-lg-10">
                            <input type="text" name="city" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.city}" >
                        </div>
                    </div>

                    <div class="row mb-7">
                        <label class="col-lg-2 col-form-label fw-bold fs-6">Улица</label>
                        <div class="col-lg-10">
                            <input type="text" name="street" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.street}" >
                        </div>
                    </div>

                    <div class="row mb-7">
                        <label class="col-lg-2 col-form-label fw-bold fs-6">Дом</label>
                        <div class="col-lg-2">
                            <input type="text" name="home_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.homeNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label fw-bold fs-6">Корпус</label>
                        <div class="col-lg-2">
                            <input type="text" name="corp_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.corpsNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label fw-bold fs-6">Квартира</label>
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



