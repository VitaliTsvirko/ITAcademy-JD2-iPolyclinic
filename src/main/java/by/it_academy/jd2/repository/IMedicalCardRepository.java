package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalCardRepository extends JpaRepository<MedicalCard, Long> {
}
