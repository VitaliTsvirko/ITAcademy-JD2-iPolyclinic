<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
     <title>SingUp</title>
     <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <meta name="description" content="">
</head>

<body>
<%@include file="header.jsp"%>

    <link href="static/css/auth.css" rel="stylesheet">

    <main class="container">

      <div class="auth-form px-3">
        <div class="auth-form-header p-0">
            <h1>Регистрация нового пользователя</h1>
            <c:if test="${requestScope.signupResult != null}">
                <p class="text-danger">${requestScope.signupResult}</p>
            </c:if>
        </div>

        <div class="auth-form-body mt-3">
            <form action="signup" method="POST" class="form-group required">

                <label for="inputName" class="sr-only">Номер телефона</label>
                <input type="tel" name="phoneNo" id="inputName" pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" class="form-control input-block" placeholder="+375(__)___-__-__" required autofocus >

                <label class="sr-only">Пароль</label>
                <input type="password" id="inputPassword" name="password" placeholder="Пароль" class="form-control input-block" required>

                <label class="sr-only">Пожалуйста, подтвердите пароль</label>
                <input type="password" id="inputConfirmPassword" name="confirmPassword" placeholder="Пароль" class="form-control input-block" required>

                <button class="btn btn-primary btn-block" type="submit">Зарегистрироваться</button>
            </form>
        </div>

    </div>

    </main>

    <%@include file="footer.jsp"%>
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


