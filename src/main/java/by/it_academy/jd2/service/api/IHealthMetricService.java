package by.it_academy.jd2.service.api;


import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;

import java.util.List;

public interface IHealthMetricService {

    List<HealthMetricAnalysisDTO> getUserAnalysisHealthMetricsByUserId(Long userId);
    List<HealthMetricEntityDTO> getUserHealthMetricsByUserId(Long userId);
    List<HealthMetricAnalysisDTO> addMetricsByUserId(List<HealthMetricEntityDTO> metricEntityDTOS, Long userId);
    List<HealthMetricEntityDTO> getMetricDataByUserIdAndMetricType(Long userId, HealthMetricsTypes metricType);
}
