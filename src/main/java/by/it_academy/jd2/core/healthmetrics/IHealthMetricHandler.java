package by.it_academy.jd2.core.healthmetrics;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.dto.UserHealthMetricDTO;
import by.it_academy.jd2.domain.enumeration.GenderType;

import java.util.List;

public interface IHealthMetricHandler {

    List<HealthMetricEntityDTO> calculateMetrics(UserHealthMetricDTO metrics);

    List<HealthMetricAnalysisDTO> analysisMetrics(List<HealthMetricEntityDTO> metrics, GenderType genderType);

}
