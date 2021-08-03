package by.it_academy.jd2.repository.test;

import by.it_academy.jd2.domain.test.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vitali Tsvirko
 */
@Repository
public interface ICardRepository extends JpaRepository<Card, Long> {
}
