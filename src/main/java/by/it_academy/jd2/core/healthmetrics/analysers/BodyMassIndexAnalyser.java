package by.it_academy.jd2.core.healthmetrics.analysers;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;

import static by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils.isInRange;

/**
 * Created by Vitali Tsvirko
 */
public class BodyMassIndexAnalyser implements IHealthMetricAnalyser {

    /**
     * Данные метод выполняет анализ Индекс массы тела (ИМТ):
     *  меньше 15 – острый дефицит массы;
     *  от 15 до 20 – дефицит массы;
     *  от 20 до 24 – нормальный;
     *  от 25 до 30 – избыточная массы.
     *  свыше 30 – ожирение организма и отдельных его частей.
     *
     * @param value Индекс массы тела (ИМТ)
     * @return {@link HealthMetricAnalysisDTO} с результатом расчета и анализом
     * @throws IllegalArgumentException если параметры рост и вес находяться вне доспустимого диапазона
     */
    @Override
    public HealthMetricAnalysisDTO analysis(Double value, GenderType genderType) {
        HealthMetricAnalysisDTO result = new HealthMetricAnalysisDTO();
        result.setValue(value);
        result.setType(HealthMetricsTypes.BMI);

        if (value < 15){
            result.setStatus(HealthMetricStatus.LLOW);
            result.setComment("Острый дефицит массы");
        } else if (isInRange(value, 15, 20)) {
            result.setStatus(HealthMetricStatus.LOW);
            result.setComment("Дефицит массы");
        } else if (isInRange(value, 20, 24)){
            result.setStatus(HealthMetricStatus.OK);
            result.setComment("Нормальный");
        } else if (isInRange(value, 25, 30)){
            result.setStatus(HealthMetricStatus.HIGH);
            result.setComment("Избыточная масса");
        } else {
            result.setStatus(HealthMetricStatus.HHIGH);
            result.setComment("Ожирение организма и отдельных его частей");
        }

        return result;
    }
}
