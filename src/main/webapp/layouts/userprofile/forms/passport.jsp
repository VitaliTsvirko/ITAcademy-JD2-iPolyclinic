<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade" id="passportModalForm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Пасспорт</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">

            <form id='form-passport' action='#' method='post'>
            <div class="row">
                <div class="col-lg-6">
                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Имя</label>
                        <div class="col-8">
                            <input name="name" id="name" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.name}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
                        <div class="col-8">
                            <input name="surname" id="surname" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.surname}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
                        <div class="col-8">
                            <input name="patronymic" id="patronymic" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.patronymic}">
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
                        <div class="col-8">
                            <input name="date_of_birth" id="date_of_birth" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.dateOfBirth}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Место рождения</label>
                        <div class="col-8">
                            <input name="place_of_birth" id="place_of_birth" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.placeOfBirth}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Национальность</label>
                        <div class="col-8">
                            <input name="nationality" id="nationality" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.nationality}">
                        </div>
                    </div>
                </div>
            </div>



            <div class="row pt-5">
                <div class="col-lg-6">
                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
                        <div class="col-8">
                            <input name="personal_no" id="personal_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.personalNo}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
                        <div class="col-8">
                            <input name="passport_no" id="passport_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.passportNo}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
                        <div class="col-8">
                            <select class="form-select" name="country_of_issue_code" aria-label="Страна" required>
                                <c:forEach items="${requestScope.countriesMap}" var="country">
                                    <option ${country.key.equalsIgnoreCase(requestScope.user.address.country.code) ? "selected" : ""} value="${country.key}">${country.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6">
                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Дата выдачи</label>
                        <div class="col-8">
                            <input name="issue_date" id="issue_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.issueDate}">
                        </div>
                    </div>

                    <div class="row mb-7 p-3">
                        <label class="col-4 col-form-label required fw-bold fs-6">Действителен до</label>
                        <div class="col-8">
                            <input name="expiration_date" id="expiration_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.expirationDate}">
                        </div>
                    </div>
                </div>
            </div>
            </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" id='form-passport-save-btn' class='btn btn-primary' data-bs-dismiss="modal">Добавить</button>
            </div>
        </div>
    </div>
</div>