package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.UserHealthMetrics;
import by.it_academy.jd2.repository.IUserHealthMetricsRepository;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.service.api.IUserHealthMetricsService;
import by.it_academy.jd2.service.dto.metrics.UserHealthMetricsDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHealthMetricsService implements IUserHealthMetricsService {

    private final IUserHealthMetricsRepository userHealthMetricsRepository;
    private final IUsersRepository usersRepository;

    public UserHealthMetricsService(IUserHealthMetricsRepository userHealthMetricsRepository, IUsersRepository usersRepository) {
        this.userHealthMetricsRepository = userHealthMetricsRepository;
        this.usersRepository = usersRepository;
    }


    @Override
    public List<UserHealthMetricsDTO> getAllHealthMetricByUserId(Long userId){
        return userHealthMetricsRepository.findAllByCreatedById(userId).stream()
                                            .map(metric -> new UserHealthMetricsDTO(metric)).collect(Collectors.toList());
    }


    @Override
    public UserHealthMetricsDTO getLastHealthMetricByUserId(Long userId){
        return new UserHealthMetricsDTO(userHealthMetricsRepository.findFirstByCreatedByIdOrderByTimestampDesc(userId));
    }


    @Override
    public UserHealthMetricsDTO addHealthMetric(UserHealthMetricsDTO metricsDTO, Long userId){
        return usersRepository.findById(userId)
                .map(user -> {
                    UserHealthMetrics metrics = new UserHealthMetrics();

                    metrics.setMedicalCard(user.getMedicalCard());

                    metrics.setTemperature(metricsDTO.getTemperature());
                    metrics.setSystolicBloodPressure(metricsDTO.getSystolicBloodPressure());
                    metrics.setDiastolicBloodPressure(metricsDTO.getDiastolicBloodPressure());
                    metrics.setHeartRate(metricsDTO.getHeartRate());
                    metrics.setHeight(metricsDTO.getHeight());
                    metrics.setWeight(metricsDTO.getWeight());

                    //metrics.setBodyMassIndex(??????);

                    metrics.setTimestamp(LocalDateTime.now());
                    metrics.setCreatedBy(user);

                    return new UserHealthMetricsDTO(userHealthMetricsRepository.save(metrics));
                })
            .orElseThrow(() -> new UsernameNotFoundException("User with id " + userId + "not found"));
        }

}
