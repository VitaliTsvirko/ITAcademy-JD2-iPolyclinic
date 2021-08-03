<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
 <head>
   <title>Авторизация</title>
   <%@include file="../layouts/head.jsp"%>
 </head>

 <body class="bg-light">
    <%@include file="../layouts/header.jsp"%>

    <link href="${pageContext.request.contextPath}/static/css/auth.css" rel="stylesheet">

    <main class="container-md pt-5">

        <div class="vh-80">
            <div class="container py-5 h-80">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow p-3 mb-5 bg-body rounded border-0" style="border-radius: .5rem!important;">
                            <div class="card-body p-4 text-center">

                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger d-flex align-items-center mx-4" role="alert">
                                           Ошибка авторизации. Проверьте правильность введенных данных!
                                    </div>
                                </c:if>

                                <div class="fs-3 m-2">
                                    Войти
                                </div>
                                <div class="fs-7 mb-2 pb-3">
                                    Нет аккаунта, <a href="${pageContext.request.contextPath}/signup">зарегистрируйтесь</a>
                                </div>


                                <div>
                                    <form action="login" method="POST" class="auth-form">

                                        <div class="form-outline mb-4">
                                            <label for="inputName">Номер телефона</label>
                                            <input type="text" name="login" id="inputName" class="form-control input-block" required autofocus >
                                        </div>

                                        <div class="form-outline mb-4">
                                            <label for="inputPassword">Пароль</label>
                                            <input type="password" name="password" id="inputPassword" class="form-control input-block" required>
                                        </div>

                                        <button class="btn btn-primary btn-lg btn-block" type="submit">Войти</button>

                                    </form>
                                </div>

                                <div class="text-center text-muted text-uppercase fw-bolder my-2">или</div>

                                <button class="btn btn-lg btn-block btn-primary border-0" style="color: #7e8299; background-color: #f5f8fa;" type="submit">
                                    <a><img alt="Logo" src="${pageContext.request.contextPath}/static/image/google-icon.svg" class="h-15 me-3">Войти через Google</a>
                                </button>
                                <button onclick="window.location.href = '${pageContext.request.contextPath}/oauth2/authorization/ca9c89a8c5895c9416fb9105d9617790d88c0ec7';" class="btn btn-lg btn-block btn-primary border-0 mb-2" style="color: #7e8299; background-color: #f5f8fa;" type="submit">
                                    <a><img alt="Logo" src="${pageContext.request.contextPath}/static/image/github-icon.svg" class=" h-15 me-3">Войти через GitHub</a>
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </main>

<%@include file="../layouts/footer.jsp"%>
 </body>

</html>


