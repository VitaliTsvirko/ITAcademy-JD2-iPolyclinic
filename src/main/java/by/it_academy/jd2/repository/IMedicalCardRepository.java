package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMedicalCardRepository extends JpaRepository<MedicalCard, Long> {

    Optional<MedicalCard> findByUserId(Long id);

}
