package by.it_academy.jd2.core.healthmetrics.dto;

import by.it_academy.jd2.core.healthmetrics.enumeration.UserHealthMetricsTypes;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class UserLastMetricsDTO {

    private UserHealthMetricsTypes type;
    private Double value;
    private LocalDateTime timestamp;

    public UserHealthMetricsTypes getTypes() {
        return type;
    }

    public void setTypes(UserHealthMetricsTypes type) {
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
