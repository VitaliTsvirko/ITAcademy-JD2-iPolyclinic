<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/static/css/navbar.css'/>" rel="stylesheet">
<link href="<c:url value='/static/css/style.css'/>" rel="stylesheet">

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
                            <a class="nav-link" href="#">О нас</a>
                        </li>
                        <li class="nav-item mx-3">
                            <a class="nav-link" href="#">Отделения</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="#">Наши врачи</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="#">Записаться на прием</a>
                        </li>

                        <li class="nav-item mx-3">
                            <a class="nav-link" href="#">Новости</a>
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
                                        <c:when test="${not empty sessionScope.userRoleIsAdmin}">
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/user/manager">Пользователи</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile">Профиль</a>
                                                <a class="dropdown-item" href="#">Записаться на прием</a>
                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a>
                                            </ul>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</header>