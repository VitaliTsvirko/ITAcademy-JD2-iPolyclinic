package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.Diseases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDiseasesRepository extends JpaRepository<Diseases, String > {
    List<Diseases> findByNameContaining(String name);
}
