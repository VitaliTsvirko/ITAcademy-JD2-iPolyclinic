<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Регистрация</title>
    <%@include file="/layouts/head.jsp"%>
</head>

<!--begin::Body-->
<body class="bg-light">
<!--begin::Main-->
<div class="d-flex flex-column flex-root">

    <!--begin::Navigation-->
    <%@include file="/layouts/header.jsp"%>

    <link href="${pageContext.request.contextPath}/static/css/auth.css" rel="stylesheet">
    <!--end::Navigation-->

    <!--begin::Content-->
    <div class="d-flex flex-column flex-column-fluid text-center p-10 py-lg-20">
        <main class="container">

            <div class="w-lg-500px bg-white rounded shadow-sm p-10 p-lg-15 mx-auto">

                <form class="form w-100" method="POST" action="signup">
                    <c:if test="${not empty requestScope.error}">
                        <div class="alert alert-danger d-flex align-items-center mx-4" role="alert">
                                ${requestScope.error}
                        </div>
                    </c:if>

                    <div class="text-center mb-10">
                        <h3 class="text-dark mb-3">Зарегистрироваться</h3>
                        <div class="text-gray-400 fw-bold fs-5">
                            Есть аккаунт, <a href="${pageContext.request.contextPath}/auth/login.jsp">войти</a>
                        </div>
                    </div>

                    <!--begin::Input group-->
                    <div class="fv-row mb-7 text-start">
                        <label class="form-label fs-5 fw-bolder text-dark">Номер телефона</label>
                        <input type="tel" name="phoneNo" id="inputName" pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" class="form-control form-control-lg form-control-solid" required autofocus/>
                    </div>
                    <div class="fv-row mb-7 text-start">
                        <label class="form-label fw-bolder text-dark fs-5 mb-0">Пароль</label>
                        <input type="password" name="password" id="inputPassword" class="form-control form-control-lg form-control-solid" required/>
                    </div>
                    <div class="fv-row mb-7 text-start">
                        <label class="form-label fw-bolder text-dark fs-5 mb-0">Подтвертиде, пароль</label>
                        <input type="password" name="confirmPassword" id="inputConfirmPassword" class="form-control form-control-lg form-control-solid" required/>
                    </div>

                    <!--end::Input-->


                    <div class="text-center">
                        <button type="submit" class="btn btn-lg btn-primary w-100 mb-2">Зарегистрироваться</button>
                        <div class="text-center text-muted text-uppercase fw-bolder mb-2">или</div>
                        <button class="btn btn-lg btn-block btn-primary border-0 w-100 mb-2" style="color: #7e8299; background-color: #f5f8fa;" type="submit">
                            <a><img alt="Logo" src="${pageContext.request.contextPath}/static/image/google-icon.svg" class="h-15 me-3">Войти через Google</a>
                        </button>
                        <button onclick="window.location.href = '${pageContext.request.contextPath}/oauth2/authorization/ca9c89a8c5895c9416fb9105d9617790d88c0ec7';" class="btn btn-lg btn-block btn-primary border-0 mb-2 w-100" style="color: #7e8299; background-color: #f5f8fa;" type="submit">
                            <a><img alt="Logo" src="${pageContext.request.contextPath}/static/image/github-icon.svg" class=" h-15 me-3">Войти через GitHub</a>
                        </button>
                    </div>
                    <!--end::Actions-->
                </form>
                <!--end::Form-->
            </div>

        </main>
    </div>
    <!--end::Content-->

    <!--begin::Footer-->
    <%@include file="/layouts/footer.jsp"%>
    <!--end::Footer-->

</div>
<!--end::Main-->
</body>

<script>
    let password = document.getElementById("inputPassword");
    let confirm_password = document.getElementById("inputConfirmPassword");

    function validatePassword(){
        if(password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Пароли не совпадают");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>

<!--end::Body-->
</html>

