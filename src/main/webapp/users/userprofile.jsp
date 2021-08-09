<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.ApplicationUserState" %>

<html>
<head>
    <title>Профиль пользователя</title>
    <%@include file="../layouts/head.jsp"%>
</head>

<!--begin::Body-->
<body class="bg-light">
<!--begin::Main-->
<main class="d-flex flex-column flex-root">
    <!--begin::Navigation-->
    <%@include file="../layouts/header.jsp"%>
    <!--end::Navigation-->

    <!--begin::Content-->
    <div class="d-flex flex-column flex-column-fluid text-center p-10 py-lg-20">
        <input type="hidden" id="user_id" value="${requestScope.user.id}">
        <main class="container">

            <div class="card mb-5 mb-xl-10">
                <div class="card-body pt-9 pb-0">
                    <!--begin::Details-->
                    <div class="d-flex flex-wrap flex-sm-nowrap mb-3">
                        <!--begin::Info-->
                        <div class="flex-grow-1">
                            <div class="d-flex justify-content-between align-items-start flex-wrap mb-2">
                                <div class="d-flex flex-column">
                                    <div class="d-flex align-items-center mb-0">
                                        <span id="head-full_name" class="text-gray-800 text-hover-primary fs-3 fw-bolder me-1">${requestScope.user.passport.name} ${requestScope.user.passport.surname} ${requestScope.user.passport.patronymic}</span>
                                        <c:choose>
                                            <c:when test="${requestScope.user.state eq ApplicationUserState.ACTIVATED}">
                                                <span id="user-status-badge" class="badge badge-success fw-bolder ms-2 fs-8 py-1 px-3">Подвержден</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span id="user-status-badge" class="badge badge-warning fw-bolder ms-2 fs-8 py-1 px-3">Не подвержден</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="d-flex flex-wrap fw-bold fs-5">
										<span class="d-flex align-items-center text-gray-400 text-hover-primary fs-5">
											<i class="bi bi-file-person fs-4 me-2"></i> ${requestScope.user.userRole.roleName}
										</span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!--end::Info-->
                    </div>
                    <!--end::Details-->

                    <!--begin::Navs-->
                    <div id="userProfileModalTabsHeader" class="d-flex overflow-auto h-55px">
                        <ul class="nav nav-stretch nav-line-tabs nav-line-tabs-2x border-transparent fs-5 fw-bolder flex-nowrap">
                            <!--begin::Nav item-->
                            <li class="nav-item">
                                <a class="nav-link text-active-primary me-6 active" data-bs-toggle="tab" href="#overview">Обзор</a>
                            </li>
                            <!--end::Nav item-->
                            <!--begin::Nav item-->
                            <li class="nav-item">
                                <a class="nav-link text-active-primary me-6" data-bs-toggle="tab" href="#auth">Авторизация</a>
                            </li>
                            <!--end::Nav item-->
                            <!--begin::Nav item-->
                            <li class="nav-item">
                                <a class="nav-link text-active-primary me-6" data-bs-toggle="tab" href="#address">Адрес</a>
                            </li>
                            <!--end::Nav item-->
                            <!--begin::Nav item-->
                            <li class="nav-item">
                                <a class="nav-link text-active-primary me-6" data-bs-toggle="tab" href="#passport">Паспортные данные</a>
                            </li>
                            <!--end::Nav item-->
                        </ul>
                    </div>
                    <!--begin::Navs-->

                </div>
            </div>

            <div class="tab-content" id="tabContent" >
                <!--begin::tabs overview-->
                <div class="tab-pane fade show active" id="overview" role="tabpanel">
                    <%@include file="../layouts/userprofile/basicinfo.jsp"%>
                </div>
                <!--end::tabs overview-->

                <!--begin::tabs auth-->
                <div class="tab-pane fade" id="auth" role="tabpanel">
                    <%@include file="../layouts/userprofile/auth.jsp"%>
                </div>
                <!--end::tabs auth-->

                <!--begin::tabs address-->
                <div class="tab-pane fade" id="address" role="tabpanel">
                    <%@include file="../layouts/userprofile/address.jsp"%>
                </div>
                <!--end::tabs address-->

                <!--begin::tabs passport-->
                <div class="tab-pane fade" id="passport" role="tabpanel">
                    <%@include file="../layouts/userprofile/passport.jsp"%>
                </div>
                <!--end::tabs passport-->
            </div>
        </main>
        <!--end::Content-->
    </div>

    <!--begin::Footer-->
    <%@include file="../layouts/footer.jsp"%>
    <!--end::Footer-->
</main>
<!--end::Main-->
</body>
<script>
    jQuery(function($){
        $(document).ready(function(){
            // Listen with the jQuery to the tabs click:
            $('#userProfileModalTabsHeader a').click(function (link) {
                //remove all alerts
                $('#address-tab-alert').empty();
                $('#passport-tab-alert').empty();
            })
        })
    });
</script>
<!--end::Body-->
</html>
