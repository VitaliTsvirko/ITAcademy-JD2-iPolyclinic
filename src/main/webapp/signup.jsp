<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
     <title>Регистрация</title>
    <%@include file="layouts/head.jsp"%>
</head>

<body class="bg-light">
<%@include file="layouts/header.jsp"%>
<link href="static/css/auth.css" rel="stylesheet">

<main class="container-md pt-5">
    <div class="vh-80">
        <div class="container py-5 h-80">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card shadow p-3 mb-5 bg-body rounded border-0" style="border-radius: .5rem!important;">
                        <div class="card-body p-4 text-center">

                            <div class="fs-3 m-2">
                                Зарегистрироваться
                            </div>
                            <div class="fs-7 mb-2 pb-3">
                                Есть аккаунт, <a href="${pageContext.request.contextPath}/login.jsp">войти</a>
                            </div>

                            <c:if test="${not empty requestScope.error}">
                                <div class="alert alert-danger d-flex align-items-center mx-4" role="alert">
                                        ${requestScope.error}
                                </div>
                            </c:if>

                            <div>
                                <form action="signup" method="POST" class="auth-form">
                                    <div class="form-outline mb-4">
                                        <label for="inputName">Номер телефона</label>
                                        <input type="tel" name="phoneNo" id="inputName" pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" class="form-control input-block" placeholder="+375(__)___-__-__" required autofocus >
                                    </div>
                                    <div class="form-outline mb-4">
                                        <label for="inputPassword">Пароль</label>
                                        <input type="password" name="password" id="inputPassword" class="form-control input-block" required>
                                    </div>
                                    <div class="form-outline mb-4">
                                        <label for="inputPassword">Подтвертиде, пароль</label>
                                        <input type="password" name="confirmPassword" id="inputConfirmPassword" class="form-control input-block" required>
                                    </div>
                                    <button class="btn btn-primary btn-lg btn-block" type="submit">Зарегистрироваться</button>
                                </form>
                            </div>

                            <div class="text-center text-muted text-uppercase fw-bolder my-2">или</div>

                            <button class="btn btn-lg btn-block btn-primary border-0" style="color: #7e8299; background-color: #f5f8fa;" type="submit"><a><img alt="Logo" src="static/image/google-icon.svg" class="h-15 me-3">Войти через Google</a></button>
                            <button class="btn btn-lg btn-block btn-primary border-0 mb-2" style="color: #7e8299; background-color: #f5f8fa;" type="submit"><a><img alt="Logo" src="static/image/github-icon.svg" class=" h-15 me-3">Войти через GitHub</a></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

<%@include file="layouts/footer.jsp"%>
</body>

<script>
    var password = document.getElementById("inputPassword")
        , confirm_password = document.getElementById("inputConfirmPassword");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Пароли не совпадают");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</html>


