package by.it_academy.jd2.domain;

import by.it_academy.jd2.domain.enumeration.AppointmentType;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumn(name = "doctor_id", foreignKey=@ForeignKey(name = "FK_appointment_doctor_id"))
    private User doctor;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AppointmentType type;

    @Column(name = "complaints")
    private String complaints;

    @Column(name = "temperature")
    @DecimalMin(value = "35")
    @DecimalMax(value = "41")
    private Float temperature;

    @Column(name = "systolic_blood_pressure")
    @Min(value = 100)
    @Max(value = 300)
    private Integer systolicBloodPressure;

    @Column(name = "diastolic_blood_pressure")
    @Min(value = 100)
    @Max(value = 300)
    private Integer diastolicBloodPressure;

    @OneToOne
    @JoinColumn(name = "disease_code", foreignKey=@ForeignKey(name = "FK_appointment_doctor_id"))
    private Diseases diagnosis;

    @Column(name = "therapy")
    private String therapy;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private HealthStatus healthStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
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

    public Diseases getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diseases diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTherapy() {
        return therapy;
    }

    public void setTherapy(String therapy) {
        this.therapy = therapy;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }
}
