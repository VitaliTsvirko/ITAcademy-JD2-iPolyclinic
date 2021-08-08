package by.it_academy.jd2.core.healthmetrics.dto;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class HealthMetricAnalysisDTO {


    @Enumerated(EnumType.STRING)
    private HealthMetricsTypes type;

    private Double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private HealthMetricStatus status;

    private String comment;

    public HealthMetricAnalysisDTO() {

    }

    public HealthMetricAnalysisDTO(HealthMetricsTypes type, Double value, LocalDateTime timestamp, HealthMetricStatus status, String comment) {
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HealthMetricsTypes getType() {
        return type;
    }

    public void setType(HealthMetricsTypes type) {
        this.type = type;
    }
}
