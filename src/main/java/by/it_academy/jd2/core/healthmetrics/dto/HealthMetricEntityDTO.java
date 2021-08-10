package by.it_academy.jd2.core.healthmetrics.dto;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.HealthMetrics;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class HealthMetricEntityDTO {


    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private HealthMetricsTypes type;

    @JsonProperty("value")
    private Double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;


    public HealthMetricEntityDTO(){

    }

    public HealthMetricEntityDTO(String type, String value, String timestamp) {
        this.type = HealthMetricsTypes.valueOf(type);
        this.value = Double.valueOf(value);
        this.timestamp = LocalDateTime.parse(timestamp);
    }

    public HealthMetricEntityDTO(HealthMetricsTypes type, Double value, LocalDateTime timestamp) {
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public HealthMetricEntityDTO(HealthMetrics entity) {
        this.type = entity.getMetricType();
        this.value = entity.getValue();
        this.timestamp = entity.getTimestamp();
    }


    public HealthMetricsTypes getType() {
        return type;
    }

    public void setType(HealthMetricsTypes type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
