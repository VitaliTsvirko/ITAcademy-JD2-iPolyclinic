<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ошибка</title>
    <%@include file="layouts/head.jsp"%>
</head>
<body class="bg-light">
<%@include file="layouts/header.jsp"%>

<main class="container-md pt-5">

    <div class="text-center py-5 px-3">
        <p style="font-size: 32px">Упс!!!!</p>
        <p style="font-size: 20px">Ошибка 404 - Запрашиваемая страница не найдена</p>
        <p class="login-callout mt-3"> Перейти <a href="${pageContext.request.contextPath}/index.jsp">на главную страницу</a> </p>
    </div>

</main>

<%@include file="layouts/footer.jsp"%>
</body>
</html>
