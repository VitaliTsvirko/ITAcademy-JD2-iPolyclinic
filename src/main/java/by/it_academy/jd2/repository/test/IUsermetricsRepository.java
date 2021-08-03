package by.it_academy.jd2.repository.test;

import by.it_academy.jd2.domain.test.UserMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vitali Tsvirko
 */
@Repository
public interface IUsermetricsRepository extends JpaRepository<UserMetrics, Long> {
}
