<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.GenderType" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>

<div id="passport-tab-alert" class="mt-3"></div>

<div class="${not empty requestScope.user.passport ? 'collapse' : ''}" id="div-passport-add-btn">
    <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#passportModalForm">Добавить</button>
</div>

<c:if test="${requestScope.allowEditPassportData}">
<div class="${empty requestScope.user.passport ? 'collapse' : ''}" id="tap-passport-data">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#passportModalForm">Измениить</button>
        <button type="button" class="btn btn-danger" onclick='deletePassport($("#user_id").val())'>Удалить</button>
        <c:if test="${requestScope.allowPassportConfirm}">
            <button type="button" id="confirm-passport-btn" class="btn btn-success ${requestScope.user.state eq ApplicationUserState.PASSPORT_DATA_IS_INPUT ? '' : 'collapse'}" onclick='confirmPassportData($("#user_id").val())'>Подвердить</button>
        </c:if>
    </div>
</c:if>

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

            <div class="row mb-7 p-3">
                <label class="col-4 col-form-label required fw-bold fs-6 text-muted">Пол</label>
                <div class="col-8">
                    <span id="gender_name" class="fw-bolder fs-6 text-dark">${requestScope.user.passport.genderType.genderName}</span>
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
                                    <input name="name" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.name}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
                                <div class="col-8">
                                    <input name="surname" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.surname}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
                                <div class="col-8">
                                    <input name="patronymic" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.patronymic}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <div class="col-4">
                                    <label class="fw-bold fs-6">Пол</label>
                                </div>
                                <div class="col-8">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender_type" id="type1" value="MALE" ${requestScope.user.passport.genderType eq GenderType.MALE ? 'checked' : ''} >
                                        <label class="form-check-label" for="type1">Муж.</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender_type" id="type2" value="FEMALE" ${requestScope.user.passport.genderType eq GenderType.FEMALE ? 'checked' : ''}>
                                        <label class="form-check-label" for="type2">Жен.</label>
                                    </div>
                                </div>
                            </div>
                        </div>




                        <div class="col-lg-6">
                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
                                <div class="col-8">
                                    <input name="date_of_birth" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.dateOfBirth}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Место рождения</label>
                                <div class="col-8">
                                    <input name="place_of_birth" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.placeOfBirth}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Национальность</label>
                                <div class="col-8">
                                    <input name="nationality" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.nationality}">
                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="row pt-5">
                        <div class="col-lg-6">
                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
                                <div class="col-8">
                                    <input name="personal_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.personalNo}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
                                <div class="col-8">
                                    <input name="passport_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.passportNo}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
                                <div class="col-8">
                                    <select class="form-select" name="country_of_issue_code" aria-label="Страна" required>
                                        <option value="">Укажите страну</option>
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
                                    <input name="issue_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.issueDate}">
                                </div>
                            </div>

                            <div class="row mb-7 p-3">
                                <label class="col-4 col-form-label required fw-bold fs-6">Действителен до</label>
                                <div class="col-8">
                                    <input name="expiration_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.expirationDate}">
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" class='btn btn-primary' data-bs-dismiss="modal" onclick='savePassportData($("#user_id").val())'>Сохранить</button>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/static/js/userprofile/passport.js" type="application/javascript"></script>
