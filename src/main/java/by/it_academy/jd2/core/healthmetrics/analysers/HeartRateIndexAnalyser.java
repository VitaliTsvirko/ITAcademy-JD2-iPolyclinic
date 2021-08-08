package by.it_academy.jd2.core.healthmetrics.analysers;

import by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;

import static by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils.isInRange;

/**
 * Created by Vitali Tsvirko
 */
public class HeartRateIndexAnalyser implements IHealthMetricAnalyser {

    @Override
    public HealthMetricAnalysisDTO analysis(Double value, GenderType genderType) {
        return heartRateIndex(value.intValue());
    }


    /**
     * Данный метод возвращает {@link HealthMetricAnalysisDTO} пульса
     * @param heartRate пульс, ударов в минту
     * @return {@link HealthMetricAnalysisDTO} пульса
     * @throws IllegalArgumentException если параметры пульса находяться вне доспустимого диапазона
     * @see HealthMetricsUtils
     */
    public HealthMetricAnalysisDTO heartRateIndex(Integer heartRate) throws IllegalArgumentException{
        HealthMetricsUtils.validateHeartRate(heartRate);

        HealthMetricAnalysisDTO result = new HealthMetricAnalysisDTO();
        result.setValue(Double.valueOf(heartRate));
        result.setType(HealthMetricsTypes.HEART_RATE);

        if (heartRate < 60) {
            result.setStatus(HealthMetricStatus.LOW);
            result.setComment("Редкий");
        } else if (isInRange(heartRate, 60.0,90.0)){
            result.setStatus(HealthMetricStatus.OK);
            result.setComment("Умеренный");
        } else {
            result.setStatus(HealthMetricStatus.HIGH);
            result.setComment("Частый");
        }

        return result;
    }
}
