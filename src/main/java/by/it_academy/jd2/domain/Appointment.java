package by.it_academy.jd2.domain;

import by.it_academy.jd2.domain.enumeration.AppointmentType;
import by.it_academy.jd2.domain.enumeration.HealthStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "appointments")
public class Appointment {

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

    @OneToOne
    @JoinColumn(name ="user_health_metrics_id", foreignKey=@ForeignKey(name = "FK_appointment_user_health_metrics_id"))
    private HealthMetrics userHealthMetrics;

    @OneToOne
    @JoinColumn(name = "disease_code", foreignKey=@ForeignKey(name = "FK_disease_code_id"))
    private Diseases diagnosis;

    @Column(name = "therapy")
    private String therapy;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private HealthStatus healthStatus;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name="medical_card_id")
    private MedicalCard medicalCard;

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

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public HealthMetrics getHealthMetrics() {
        return userHealthMetrics;
    }

    public void setUserHealthMetrics(HealthMetrics userHealthMetrics) {
        this.userHealthMetrics = userHealthMetrics;
    }
}
