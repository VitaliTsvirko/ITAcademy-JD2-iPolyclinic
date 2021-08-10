package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    Long countByUserRoleEquals(UserRoles userRoles);

    @Query(value = "SELECT AVG(EXTRACT(year FROM AGE(polyclinic.passports.date_of_birth)))\n" +
            "   FROM polyclinic.passports, polyclinic.users \n" +
            "   WHERE polyclinic.users.passport_id = polyclinic.passports.id and polyclinic.users.user_role = :user_role",
            nativeQuery = true)
    Double avgAgeByUserRole(@Param ("user_role") String userRole);






}
