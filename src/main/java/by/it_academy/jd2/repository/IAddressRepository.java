package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Vitali Tsvirko
 */
public interface IAddressRepository extends JpaRepository<Address, Long> {
}
