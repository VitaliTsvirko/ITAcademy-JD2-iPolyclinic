package by.it_academy.jd2.domain;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_health_metrics_2")
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metric_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HealthMetricsTypes types;

    @Column(name = "metric_value", nullable = false)
    private Double value;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name="created_by_id", nullable=false, foreignKey=@ForeignKey(name = "FK_user_health_metrics_created_user_id"))
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="medicalcard_id", nullable=false, foreignKey=@ForeignKey(name = "FK_user_health_metrics_medical_card_id"))
    private MedicalCard medicalCard;

    public HealthMetrics(){

    }

    public HealthMetrics(HealthMetricsTypes types, Double value, LocalDateTime timestamp, User createdBy, MedicalCard medicalCard) {
        this.types = types;
        this.value = value;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.medicalCard = medicalCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HealthMetricsTypes getTypes() {
        return types;
    }

    public void setTypes(HealthMetricsTypes types) {
        this.types = types;
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
}
