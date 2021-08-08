package by.it_academy.jd2.core.healthmetrics.analysers;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.domain.enumeration.GenderType;

public interface IHealthMetricAnalyser {

    HealthMetricAnalysisDTO analysis(Double value, GenderType genderType);

}
