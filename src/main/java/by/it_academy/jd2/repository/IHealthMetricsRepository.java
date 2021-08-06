package by.it_academy.jd2.repository;

import by.it_academy.jd2.core.healthmetrics.dto.UserLastMetricsDTO;
import by.it_academy.jd2.domain.HealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

public interface IHealthMetricsRepository extends JpaRepository<HealthMetrics, Long> {





    @Query(value = "SELECT timestamp, metric_type, metric_value\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE (metric_type, timestamp) in (\n" +
            "SELECT metric_type, max(timestamp)\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE medicalcard_id = :medical_card_id\n" +
            "GROUP BY metric_type)",
            nativeQuery = true)
    List<String[]> findLastUserMetrics(@Param("medical_card_id") Long id);


/*    @NamedNativeQuery(name = "findLastUserMetrics",
            query  = "SELECT timestamp, metric_type, metric_value\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE (metric_type, timestamp) in (\n" +
            "SELECT metric_type, max(timestamp)\n" +
            "FROM polyclinic.user_health_metrics_2\n" +
            "WHERE medicalcard_id = 161\n" +
            "GROUP BY metric_type)",
            resultSetMapping = "findLastUserMetricsResult")


    List<UserLastMetricsDTO> findLastUserMetricsDTO(@Param("medical_card_id") Long id);*/

}
