<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-6">
        <p class="card-text m-0 text-start">${patientData.age} лет, ${patientData.dateOfBirth}</p>
        <p class="bi bi-telephone-fill m-0">${patientData.phoneNo}</p>
        <p class="bi bi-house-fill m-0">${patientData.fullAddress}</p>
    </div>
</div>
<hr>
<div class="row mt-0">
    <p class="card-text m-0 text-start">Аллергия: <span>${empty requestScope.medicalCardAllergy ? "нет" : medicalCardAllergy}</span> </p>
</div>


