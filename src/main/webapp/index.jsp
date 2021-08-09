<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Поликлиника</title>
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
                Умная поликлиника
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
