<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.GenderType" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>


    <div class="card mb-5 mb-xl-10">
        <!--begin::Card header-->
        <div class="card-header">
            <div class="card-title m-0">
                <h3 class="fw-bolder m-0">Паспортные данные</h3>
            </div>

            <div class="d-flex align-items-center">
            <c:if test="${requestScope.allowEditPassportData}">
                <a id="passport-add-btn" class="btn btn-light-info ${not empty requestScope.user.passport ? 'collapse' : ''}" data-bs-toggle="modal" data-bs-target="#passportModalForm">Добавить</a>
                <a id="passport-edit-btn" class="btn btn-light-info align-self-center me-2 ${empty requestScope.user.passport ? 'collapse' : ''}" data-bs-toggle="modal" data-bs-target="#passportModalForm">Измениить</a>
                <a id="passport-delete-btn" class="btn btn-light-danger align-self-center me-2 ${empty requestScope.user.passport ? 'collapse' : ''}" onclick='deletePassport($("#user_id").val())'>Удалить</a>
                <c:if test="${requestScope.allowPassportConfirm}">
                    <a id="confirm-passport-btn" class="btn btn-success  align-self-center me-2 ${requestScope.user.state eq ApplicationUserState.PASSPORT_DATA_IS_INPUT ? '' : 'collapse'}" onclick='confirmPassportData($("#user_id").val())'>Подвердить</a>
                </c:if>
            </c:if>
            </div>
        </div>

        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9 text-start">
            <div id="passport-tab-alert" class="mt-3"></div>
            <div class="text-start ${empty requestScope.user.passport ? 'collapse' : ''}" id="tap-passport-data">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Имя</label>
                            <div class="col-8">
                                <span id="name" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.name}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Фамилия</label>
                            <div class="col-8">
                                <span id="surname" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.surname}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Отчество</label>
                            <div class="col-8">
                                <span id="patronymic" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.patronymic}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Пол</label>
                            <div class="col-8">
                                <span id="gender_name" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.genderType.genderName}</span>
                            </div>
                        </div>
                    </div>


                    <div class="col-lg-6">
                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Дата рождения</label>
                            <div class="col-8">
                                <span id="date_of_birth" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.dateOfBirth}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Место рождения</label>
                            <div class="col-8">
                                <span id="place_of_birth" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.placeOfBirth}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Национальность</label>
                            <div class="col-8">
                                <span id="nationality" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.nationality}</span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row pt-5">
                    <div class="col-lg-6">
                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Личный номер</label>
                            <div class="col-8">
                                <span id="personal_no" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.personalNo}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Номер паспорта</label>
                            <div class="col-8">
                                <span id="passport_no" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.passportNo}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Страна выдачи</label>
                            <div class="col-8">
                                <span id="country_of_issue" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.countryOfIssue.shotName}</span>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6">
                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Дата выдачи</label>
                            <div class="col-8">
                                <span id="issue_date" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.issueDate}</span>
                            </div>
                        </div>

                        <div class="row mb-7">
                            <label class="col-4 col-form-label fw-bold fs-5 text-muted">Действителен до</label>
                            <div class="col-8">
                                <span id="expiration_date" class="fw-bolder fs-5 text-dark">${requestScope.user.passport.expirationDate}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--begin::Card body-->



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
                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Имя</label>
                                <div class="col-8">
                                    <input name="name" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.name}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
                                <div class="col-8">
                                    <input name="surname" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.surname}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
                                <div class="col-8">
                                    <input name="patronymic" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.patronymic}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <div class="col-4">
                                    <label class="fw-bold required fs-6">Пол</label>
                                </div>
                                <div class="col-8">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender_type" id="type1" value="MALE" ${requestScope.user.passport.genderType eq GenderType.MALE ? 'checked' : ''} >
                                        <label class="form-check-label" for="type1">Мужcкой</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender_type" id="type2" value="FEMALE" ${requestScope.user.passport.genderType eq GenderType.FEMALE ? 'checked' : ''}>
                                        <label class="form-check-label" for="type2">Женский</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
                                <div class="col-8">
                                    <input name="date_of_birth" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.dateOfBirth}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label fw-bold fs-6">Место рождения</label>
                                <div class="col-8">
                                    <input name="place_of_birth" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.placeOfBirth}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label fw-bold fs-6">Национальность</label>
                                <div class="col-8">
                                    <input name="nationality" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.nationality}">
                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="row pt-5">
                        <div class="col-lg-6">
                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
                                <div class="col-8">
                                    <input name="personal_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.personalNo}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
                                <div class="col-8">
                                    <input name="passport_no" type="text" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.passportNo}">
                                </div>
                            </div>

                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
                                <div class="col-8">
                                    <select class="form-select" name="country_of_issue_code" aria-label="Страна" required>
                                        <option value="">Укажите страну</option>
                                        <c:forEach items="${requestScope.countriesMap}" var="country">
                                            <option ${country.key.equalsIgnoreCase(requestScope.user.passport.countryOfIssue.code) ? "selected" : ""} value="${country.key}">${country.value}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="row mb-7">
                                <label class="col-4 col-form-label required fw-bold fs-6">Дата выдачи</label>
                                <div class="col-8">
                                    <input name="issue_date" type="date" class="form-control form-control-lg form-control-solid" value="${requestScope.user.passport.issueDate}">
                                </div>
                            </div>

                            <div class="row mb-7">
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
