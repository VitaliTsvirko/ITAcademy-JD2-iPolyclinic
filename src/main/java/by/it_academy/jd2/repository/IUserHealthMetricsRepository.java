package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.UserHealthMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserHealthMetricsRepository extends JpaRepository<UserHealthMetrics, Long> {

    List<UserHealthMetrics> findAllByCreatedById(Long id);

    UserHealthMetrics findFirstByCreatedByIdOrderByTimestampDesc(Long id);

}
