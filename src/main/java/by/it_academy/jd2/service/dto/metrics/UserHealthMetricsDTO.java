package by.it_academy.jd2.service.dto.metrics;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.UserHealthMetrics;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.time.LocalDateTime;

public class UserHealthMetricsDTO {

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("temperature")
    private Float temperature;

    @JsonProperty("systolic_blood_pressure")
    private Integer systolicBloodPressure;

    @JsonProperty("diastolic_blood_pressure")
    private Integer diastolicBloodPressure;

    @JsonProperty("heart_rate")
    private Integer heartRate;

    @JsonProperty("body_mass_index")
    private Float bodyMassIndex;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime timestamp;

    @JsonProperty("created_by_id")
    private Long createdById;

    @JsonProperty("medicalcard_id")
    private Long medicalCardId;

    public UserHealthMetricsDTO() {
    }

    public UserHealthMetricsDTO(UserHealthMetrics userHealthMetrics) {
        if (userHealthMetrics != null){
            this.temperature= userHealthMetrics.getTemperature();
            this.systolicBloodPressure = userHealthMetrics.getSystolicBloodPressure();
            this.diastolicBloodPressure = userHealthMetrics.getDiastolicBloodPressure();
            this.heartRate = userHealthMetrics.getHeartRate();
            this.height = userHealthMetrics.getHeight();
            this.weight = userHealthMetrics.getWeight();
            this.bodyMassIndex = userHealthMetrics.getBodyMassIndex();
            this.medicalCardId = userHealthMetrics.getMedicalCard().getId();
            this.createdById = userHealthMetrics.getCreatedBy().getId();
        }
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(Long medicalCardId) {
        this.medicalCardId = medicalCardId;
    }

    public Integer getSystolicBloodPressure() {
        return systolicBloodPressure;
    }

    public void setSystolicBloodPressure(Integer systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public Integer getDiastolicBloodPressure() {
        return diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(Integer diastolicBloodPressure) {
        this.diastolicBloodPressure = diastolicBloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Float getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(Float bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }
}
