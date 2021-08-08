package by.it_academy.jd2.service.api;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.service.dto.metrics.UserHealthMetricsDTO;

import java.util.List;

public interface IUserHealthMetricsService {

    List<UserHealthMetricsDTO> getAllHealthMetricByUserId(Long userId);

    UserHealthMetricsDTO addHealthMetric(UserHealthMetricsDTO metricsDTO, Long userId);

    UserHealthMetricsDTO getLastHealthMetricByUserId(Long userId);

    //List<UserHealthMetricsDTO> getMetricDataByUserIdAndMetricType(Long userId, HealthMetricsTypes metricType);
}
