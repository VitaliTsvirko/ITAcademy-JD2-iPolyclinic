package by.it_academy.jd2.core.healthmetrics.dto;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;

public class MetricDTO {

    private Double value;

    private HealthMetricStatus status;

    private String comment;

    public MetricDTO() {

    }

    public MetricDTO(Double value, HealthMetricStatus status, String comment) {
        this.value = value;
        this.status = status;
        this.comment = comment;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public HealthMetricStatus getStatus() {
        return status;
    }

    public void setStatus(HealthMetricStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
