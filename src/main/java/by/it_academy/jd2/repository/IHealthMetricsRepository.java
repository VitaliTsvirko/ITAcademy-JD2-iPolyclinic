package by.it_academy.jd2.repository;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {

    @Query(value = "SELECT to_char(timestamp, 'YYYY-MM-DD\"T\"HH24:MI:SS'), metric_type, metric_value\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE (metric_type, timestamp) in (\n" +
            "SELECT metric_type, max(timestamp)\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE medicalcard_id = :medical_card_id\n" +
            "GROUP BY metric_type)",
            nativeQuery = true)
    List<String[]> findLastUserMetricsByMedicalCardId(@Param("medical_card_id") Long id);


    List<HealthMetrics> findHealthMetricsByTypesAndAndMedicalCardIdOrderByTimestampDesc(HealthMetricsTypes metricType, Long medicalcardId);

}
