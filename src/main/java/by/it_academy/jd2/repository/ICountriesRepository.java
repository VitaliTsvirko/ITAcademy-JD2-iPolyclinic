package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.Countries;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICountriesRepository extends JpaRepository<Countries, String> {


}
