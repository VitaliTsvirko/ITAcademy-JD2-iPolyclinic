package by.it_academy.jd2.core.healthmetrics.analysers;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;

import static by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils.isInRange;

/**
 * Created by Vitali Tsvirko
 */
public class FunctionalChangesIndexAnalyser implements IHealthMetricAnalyser {

    /**
     * Данный метод анализирует индекс функциональных изменений
     *
     * @param value индекс функциональных изменений
     * @return {@link HealthMetricAnalysisDTO} с индексом функциональных изменений
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    @Override
    public HealthMetricAnalysisDTO analysis(Double value, GenderType genderType){
        HealthMetricAnalysisDTO result = new HealthMetricAnalysisDTO();
        result.setValue(value);
        result.setType(HealthMetricsTypes.FUNC_CHANGE);

        if (value < 2.59){
            result.setStatus(HealthMetricStatus.OK);
            result.setComment("Норма");
        } else if (isInRange(value, 2.6, 3.09)){
            result.setStatus(HealthMetricStatus.HIGH);
            result.setComment("Напряжение механизмов адаптации");
        } else {
            result.setStatus(HealthMetricStatus.HHIGH);
            result.setComment("Неудовлетворительная адаптация");
        }

        return result;

    }

}
