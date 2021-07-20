<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="${empty requestScope.user.passport ? "collapse" : ""}" id="div-passport-add-btn">
    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#passportModalForm">Добавить</button>
</div>

<div class="${not empty requestScope.user.passport ? "collapse" : ""}" id="tap-passport-data">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#passportModalForm">Измениить</button>
        <button type="button" class="btn btn-danger" id='form-passport-delete-btn'>Удалить</button>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Имя</label>
                <div class="col-8">
                    <span id="name" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.name}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Фамилия</label>
                <div class="col-8">
                    <span id="surname" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.surname}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Отчество</label>
                <div class="col-8">
                    <span id="patronymic" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.patronymic}</span>
                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Дата рождения</label>
                <div class="col-8">
                    <span id="date_of_birth" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.dateOfBirth}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Место рождения</label>
                <div class="col-8">
                    <span id="place_of_birth" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.placeOfBirth}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Национальность</label>
                <div class="col-8">
                    <span id="nationality" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.nationality}</span>
                </div>
            </div>
        </div>
    </div>

    <div class="row pt-5">
        <div class="col-lg-6">
            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Личный номер</label>
                <div class="col-8">
                    <span id="personal_no" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.personalNo}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Номер паспорта</label>
                <div class="col-8">
                    <span id="passport_no" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.passportNo}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Страна выдачи</label>
                <div class="col-8">
                    <span id="country_of_issue" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.countryOfIssue.shotName}</span>
                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Дата выдачи</label>
                <div class="col-8">
                    <span id="issue_date" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.issueDate}</span>
                </div>
            </div>

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Действителен до</label>
                <div class="col-8">
                    <span id="expiration_date" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.expirationDate}</span>
                </div>
            </div>
        </div>
    </div>
</div>