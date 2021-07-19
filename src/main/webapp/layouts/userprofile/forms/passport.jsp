<div class="row">
    <div class="col-lg-6">
        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Имя</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.name}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Фамилия</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.surname}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Отчество</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.patronymic}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Дата рождения</label>
            <div class="col-8">
                <input type="date" value="${sessionScope.user.passport.dateOfBirth}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Место рождения</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.placeOfBirth}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Национальность</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.nationality}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>
    </div>
</div>

<div class="row pt-5">
    <div class="col-lg-6">
        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Личный номер</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.personalNo}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Номер паспорта</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.passportNo}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Страна выдачи</label>
            <div class="col-8">
                <input type="text" value="${sessionScope.user.passport.countryOfIssue.shotName}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Дата выдачи</label>
            <div class="col-8">
                <input type="date" value="${sessionScope.user.passport.issueDate}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>

        <div class="row mb-7 p-3">
            <label class="col-4 col-form-label required fw-bold fs-6">Действителен до</label>
            <div class="col-8">
                <input type="date" value="${sessionScope.user.passport.expirationDate}" readonly class="form-control form-control-lg form-control-solid"/>
            </div>
        </div>
    </div>
</div>