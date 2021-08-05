package by.it_academy.jd2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_health_metrics")
public class UserHealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height")
    @Min(0)
    private Integer height;

    @Column(name = "weight")
    @Min(0)
    private Integer weight;

    @Column(name = "systolic_blood_pressure")
    @Min(value = 100)
    @Max(value = 300)
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    @Min(value = 100)
    @Max(value = 300)
    private Integer diastolicBloodPressure;

    @Column(name = "heart_rate")
    @Min(40)
    @Max(400)
    private Integer heartRate;

    @Column(name = "temperature")
    @DecimalMin(value = "35")
    @DecimalMax(value = "41")
    private Float temperature;

    @Column(name = "body_mass_index")
    private Float bodyMassIndex;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name="created_by_id", nullable=false, foreignKey=@ForeignKey(name = "FK_user_health_metrics_created_user_id"))
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="medicalcard_id", nullable=false, foreignKey=@ForeignKey(name = "FK_user_health_metrics_medical_card_id"))
    private MedicalCard medicalCard;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
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

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(Float bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }
}
