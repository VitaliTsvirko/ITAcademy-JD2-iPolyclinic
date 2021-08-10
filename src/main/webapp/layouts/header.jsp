<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.it_academy.jd2.domain.enumeration.UserRoles" %>

<header>
    <div class="navigation-wrap bg-white mb-2">
        <div class="container-md">
            <nav class="navbar navbar-expand-md navbar-light">

                <a href="${pageContext.request.contextPath}/index.jsp">
                    <img src="<c:url value='/static/image/logo.svg'/>" alt="iPolyclinic">
                </a>


                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ms-auto py-4 py-md-0">

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="${pageContext.request.contextPath}">О нас</a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Отделения</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Наши врачи</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Записаться на прием</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="${pageContext.request.contextPath}">Новости</a>
                        </li>

                        <c:choose>
                            <c:when test="${sessionScope.user == null}">
                                <li class="nav-item mx-3">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Личный кабинет</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item mx-3 dropdown">
                                    <a class="nav-link dropdown-toggle" id="dropdown01" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Личный кабинет</a>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.userRole eq UserRoles.ADMIN}">
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/users">Пользователи</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:when>

                                        <c:when test="${sessionScope.user.userRole eq UserRoles.MANAGER}">
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/medicalcard/${sessionScope.user.medicalCard.id}">Медкарта</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userhealth">Статистика</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:when>

                                        <c:when test="${sessionScope.user.userRole eq UserRoles.USER or sessionScope.user.userRole eq UserRoles.DOCTOR}">
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/medicalcard/${sessionScope.user.medicalCard.id}">Медкарта</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userhealth">Статистика</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:when>

                                        <c:otherwise>
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:otherwise>
                                    </c:choose>
                                </li>

                                <c:if test="${sessionScope.user.userRole eq UserRoles.DOCTOR}">
                                    <li class="nav-item mx-3">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/patients">Портал врача</a>
                                    </li>
                                </c:if>

                                <c:if test="${sessionScope.user.userRole eq UserRoles.MANAGER}">
                                    <li class="nav-item mx-3">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/management">Статистика</a>
                                    </li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>