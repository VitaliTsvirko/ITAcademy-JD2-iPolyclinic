package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNo(String phoneNo);

    List<User> findByUserRoleEquals(UserRoles userRoles);

}
