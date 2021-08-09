<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Ошибка</title>
    <%@include file="layouts/head.jsp"%>
</head>

<!--begin::Body-->
<body class="bg-light">
<!--begin::Main-->
<div class="d-flex flex-column flex-root">

    <!--begin::Navigation-->
    <%@include file="layouts/header.jsp"%>
    <!--end::Navigation-->

    <!--begin::Content-->
    <div class="d-flex flex-column flex-column-fluid text-center p-10 py-lg-20">
        <main class="container">

            <div class="text-center py-5 px-3">
                <p style="font-size: 32px">Упс!!!!</p>
                <p style="font-size: 20px">Ошибка 404 - Запрашиваемая страница не найдена</p>
                <p class="login-callout mt-3"> Перейти <a href="${pageContext.request.contextPath}/index.jsp">на главную страницу</a> </p>
            </div>

        </main>
    </div>
    <!--end::Content-->

    <!--begin::Footer-->
    <%@include file="layouts/footer.jsp"%>
    <!--end::Footer-->

</div>
<!--end::Main-->
</body>
<!--end::Body-->
</html>

