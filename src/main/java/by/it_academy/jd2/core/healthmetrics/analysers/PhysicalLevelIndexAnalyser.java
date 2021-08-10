package by.it_academy.jd2.core.healthmetrics.analysers;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;

import java.util.Map;

import static by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils.isInRange;

/**
 * Created by Vitali Tsvirko
 */
public class PhysicalLevelIndexAnalyser implements IHealthMetricAnalyser {

    /**
     * Тектовые значения оценки физического состояния
     */
    public static final Map<HealthMetricStatus, String> physicalLevelIndexStatus = Map.of(
            HealthMetricStatus.LLOW, "Ниже среднего",
            HealthMetricStatus.LOW, "Низкий",
            HealthMetricStatus.OK, "Средний",
            HealthMetricStatus.HIGH, "Высокий",
            HealthMetricStatus.HHIGH, "Выше среднего");

    /*


    /**
     * Данный метод возвращает {@link HealthMetricAnalysisDTO} оценку физического состояния
     * расчитанную по методике Д. Н. Давиденко.
     *
     * @param value оценка физического состояния
     * @param genderType пол
     * @return {@link HealthMetricAnalysisDTO} с индексом физического состояния
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    @Override
    public HealthMetricAnalysisDTO analysis(Double value, GenderType genderType) throws IllegalArgumentException{
        HealthMetricAnalysisDTO result = new HealthMetricAnalysisDTO();
        result.setValue(value);
        result.setType(HealthMetricsTypes.PHYS_LEVEL);

        if (genderType.equals(GenderType.MALE)){
            if (isInRange(value, 0.225, 0.375)) {
                result.setStatus(HealthMetricStatus.LOW);
            } else if (isInRange(value, 0.376, 0.525)){
                result.setStatus(HealthMetricStatus.LLOW);
            } else if (isInRange(value, 0.526, 0.675)){
                result.setStatus(HealthMetricStatus.OK);
            } else if (isInRange(value, 0.676, 0.825)) {
                result.setStatus(HealthMetricStatus.HIGH);
            } else if (value > 0.826) {
                result.setStatus(HealthMetricStatus.HHIGH);
            } else {
                result.setStatus(HealthMetricStatus.UNDEFINED);
            }
        }

        if (genderType.equals(GenderType.FEMALE)){
            if (isInRange(value, 0.157, 0.26)) {
                result.setStatus(HealthMetricStatus.LOW);
            } else if (isInRange(value, 0.261, 0.365)){
                result.setStatus(HealthMetricStatus.LLOW);
            } else if (isInRange(value, 0.366, 0.475)){
                result.setStatus(HealthMetricStatus.OK);
            } else if (isInRange(value, 0.476, 0.575)) {
                result.setStatus(HealthMetricStatus.HIGH);
            } else if (value > 0.576) {
                result.setStatus(HealthMetricStatus.HHIGH);
            } else {
                result.setStatus(HealthMetricStatus.UNDEFINED);
            }
        }

        result.setComment(physicalLevelIndexStatus.get(result.getStatus()));

        return result;
    }

}
