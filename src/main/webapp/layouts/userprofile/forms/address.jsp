<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                            <input type="text" name="city" id="city" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.city}" >
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Улица</label>
                        <div class="col-lg-10">
                            <input type="text" name="street" id="street" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.street}" >
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Дом</label>
                        <div class="col-lg-2">
                            <input type="text" name="home_no" id="home_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.homeNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Корпус</label>
                        <div class="col-lg-2">
                            <input type="text" name="corp_no" id="corp_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.corpsNo}" >
                        </div>

                        <label class="col-lg-2 col-form-label required fw-bold fs-6">Квартира</label>
                        <div class="col-lg-2">
                            <input type="text" name="flat_no" id="flat_no" class="form-control form-control-lg form-control-solid" value="${requestScope.user.address.flatNo}" >
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" id='form-address-save-btn' class='btn btn-primary' data-bs-dismiss="modal">Добавить</button>
            </div>
        </div>
    </div>
</div>




