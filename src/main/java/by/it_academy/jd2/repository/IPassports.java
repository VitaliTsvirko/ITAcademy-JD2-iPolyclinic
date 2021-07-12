package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vitali Tsvirko
 */
@Repository
public interface IPassports extends JpaRepository<Passport, Long> {
}
