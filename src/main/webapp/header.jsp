<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="icon" href="favicon.ico" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="<c:url value='/static/css/navbar.css'/>" rel="stylesheet">

<header>
    <nav class="navbar navbar-expand-md navbar-light fixed-top bg-white">
        <div class="container-xxl">
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a href="${pageContext.request.contextPath}/index.jsp">
                            <img src="<c:url value='/static/image/logo.svg'/>" alt="iPolyclinic">
                        </a>
                    </li>
                </ul>
            </div>

            <ul class="navbar-nav" style="margin-right: 30px;">
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Войти</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/signup.jsp">Регистрация</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-expanded="false">${sessionScope.user.phoneNo}</a>
                            <ul class="dropdown-menu" aria-labelledby="dropdown01" >
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Выйти</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/userprofile.jsp">Профиль</a></li>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>

</header>