<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-6">
        <p class="card-text m-0 text-start">${patientData.age} лет, ${patientData.dateOfBirth}</p>
        <p class="bi bi-telephone-fill m-0">${patientData.phoneNo}</p>
        <p class="bi bi-house-fill m-0">${patientData.fullAddress}</p>
    </div>
    <div class="col-lg-4 text-start">
        <p class="card-text m-0">Рост: <span>${empty medicalCardHeight ? '---' : medicalCardHeight}</span> см</p>
        <p class="card-text m-0">Вес: <span>${empty medicalCardWeight ? '---' : medicalCardWeight}</span> кг</p>
    </div>
    <div class="col-lg-2 align-self-start justify-content-end">
        <button type="button" class="btn btn-info btn-sm bi bi-pencil-square" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
            Изменить
        </button>
    </div>
</div>
<hr>
<div class="row mt-0">
    <p class="card-text m-0 text-start">Аллергия: <span>${empty requestScope.medicalCardAllergy ? "нет" : medicalCardAllergy}</span> </p>
</div>


