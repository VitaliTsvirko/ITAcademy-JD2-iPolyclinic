<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes" %>

<div class="modal fade" id="addUserMetrics" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Добавление данных</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id='form-user-metrics' action='#' method='post'>
                    <div class="row mb-7 p-2 text-start">
                        <label class="col-lg-5 col-form-label fw-bold fs-6">Вес</label>
                        <div class="col-lg-4">
                            <input type="number" name="${HealthMetricsTypes.WEIGHT}" min="${HealthMetricsUtils.WEIGHT_MIN}" max="${HealthMetricsUtils.WEIGHT_MAX}"  placeholder="${HealthMetricsTypes.WEIGHT.unit}" class="form-control form-control-lg form-control-solid" />
                        </div>
                    </div>
                    <div class="row mb-7 p-2 text-start">
                        <label class="col-lg-5 col-form-label fw-bold fs-6">Рост</label>
                        <div class="col-lg-4">
                            <input type="number" name="${HealthMetricsTypes.HEIGHT}" min="${HealthMetricsUtils.HEIGHT_MIN}" max="${HealthMetricsUtils.HEIGHT_MAX}"  placeholder="${HealthMetricsTypes.HEIGHT.unit}" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row mb-7 p-2 text-start">
                        <label class="col-lg-5 col-form-label fw-bold fs-6">Cистолическое АД</label>
                        <div class="col-lg-4">
                            <input type="number" name="${HealthMetricsTypes.AD_SYS}" min="${HealthMetricsUtils.DIA_AD_MIN}" max="${HealthMetricsUtils.SYS_AD_MAX}"  placeholder="${HealthMetricsTypes.AD_SYS.unit}" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>
                    <div class="row mb-7 p-2 text-start">
                        <label class="col-lg-5 col-form-label fw-bold fs-6">Диастолическое АД</label>
                        <div class="col-lg-4">
                            <input type="numer" name="${HealthMetricsTypes.AD_DIA}" min="${HealthMetricsUtils.DIA_AD_MIN}" max="${HealthMetricsUtils.DIA_AD_MAX}"  placeholder="${HealthMetricsTypes.AD_DIA.unit}" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row mb-7 p-2 text-start">
                        <label class="col-lg-5 col-form-label fw-bold fs-6">Пульс</label>
                        <div class="col-lg-4">
                            <input type="text" name="${HealthMetricsTypes.HEART_RATE}" min="${HealthMetricsUtils.HEART_RATE_MIN}" max="${HealthMetricsUtils.HEART_RATE_MAX}"  placeholder="${HealthMetricsTypes.HEART_RATE.unit}" class="form-control form-control-lg form-control-solid"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onClick="addMetrics()">Добавить</button>
            </div>
        </div>
    </div>
</div>