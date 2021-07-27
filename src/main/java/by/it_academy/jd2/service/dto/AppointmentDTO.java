package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.AppointmentType;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Vitali Tsvirko
 */
public class AppointmentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "hh:mm")
    private LocalTime time;

    @JsonProperty("doctor_id")
    private Long doctorId;

    @JsonProperty("doctor_full_name")
    private String doctorFullName;

    @JsonProperty("type")
    private AppointmentType type;

    @JsonProperty("complaints")
    private String complaints;

    @JsonProperty("temperature")
    private Float temperature;

    @JsonProperty("systolic_blood_pressure")
    private Integer systolicBloodPressure;

    @JsonProperty("diastolic_blood_pressure")
    private Integer diastolicBloodPressure;

    @JsonProperty("diagnosis")
    private Diseases diagnosis;

    @JsonProperty("therapy")
    private String therapy;

    @JsonProperty("health_status")
    private HealthStatus healthStatus;

    @JsonProperty("medical_card_id")
    private Long medicalCardId;


    public AppointmentDTO() {

    }

    public AppointmentDTO(Appointment appointment) {
        if (appointment != null){
            this.id = appointment.getId();
            this.date = appointment.getDateTime().toLocalDate();
            this.time = appointment.getDateTime().toLocalTime();
            this.doctorId = appointment.getDoctor().getId();
            if (appointment.getDoctor().getPassport() != null){
                this.doctorFullName = appointment.getDoctor().getPassport().getSurname()
                        + " " + appointment.getDoctor().getPassport().getName()
                        + " " + appointment.getDoctor().getPassport().getPatronymic();
            }
            this.type = appointment.getType();
            this.complaints = appointment.getComplaints();
            this.temperature = appointment.getTemperature();
            this.diastolicBloodPressure = appointment.getDiastolicBloodPressure();
            this.systolicBloodPressure = appointment.getSystolicBloodPressure();
            this.diagnosis = appointment.getDiagnosis();
            this.therapy = appointment.getTherapy();
            this.healthStatus = appointment.getHealthStatus();
            this.medicalCardId = appointment.getMedicalCard().getId();
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
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

    public Long getMedicalCardId() {
        return medicalCardId;
    }

    public void setMedicalCardId(Long medicalCardId) {
        this.medicalCardId = medicalCardId;
    }
}
