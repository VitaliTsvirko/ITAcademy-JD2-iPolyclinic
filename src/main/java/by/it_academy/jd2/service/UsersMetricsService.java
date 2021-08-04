package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.service.api.IUsersMetricsService;
import by.it_academy.jd2.service.dto.metrics.UsersMetricsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vitali Tsvirko
 */
@Service
public class UsersMetricsService implements IUsersMetricsService {

    private final IUsersRepository usersRepository;

    public UsersMetricsService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Transactional(readOnly = true)
    public UsersMetricsDTO getUserMetrics(){
        UsersMetricsDTO metrics = new UsersMetricsDTO();

        metrics.setUsersTotal(usersRepository.count());
        metrics.setDoctorsTotal(usersRepository.countByUserRoleEquals(UserRoles.DOCTOR));
        metrics.setPatientsTotal(usersRepository.countByUserRoleEquals(UserRoles.USER));

        metrics.setPatientsAvgAgeYear(usersRepository.avgAgeByUserRole(UserRoles.USER.toString()));
        metrics.setDoctorsAvgAgeYear(usersRepository.avgAgeByUserRole(UserRoles.DOCTOR.toString()));

        return metrics;
    }

}
