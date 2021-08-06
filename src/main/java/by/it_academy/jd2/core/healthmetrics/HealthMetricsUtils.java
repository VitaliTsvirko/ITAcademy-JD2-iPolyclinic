package by.it_academy.jd2.core.healthmetrics;

import by.it_academy.jd2.core.healthmetrics.dto.MetricDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.domain.enumeration.GenderType;

import static by.it_academy.jd2.core.healthmetrics.HealthMetricsValidator.isInRange;

import java.util.Map;


/**
 * https://kpfu.ru/staff_files/F1073379016/fiziologicheskie_osnovy_diagnostiki_funkcionalnoj_sostoyaniya_organizma.pdf
 *
 * https://dyn.ru/publications/AssessmentOfTheLevelOfHealth.pdf
 *
 */

public class HealthMetricsUtils {

    /**
     * Тектовые значения оценки физического состояния
     */
    public static Map<HealthMetricStatus, String> physicalLevelIndexStatus = Map.of(
            HealthMetricStatus.LLOW, "Ниже среднего",
            HealthMetricStatus.LOW, "Низкий",
            HealthMetricStatus.OK, "Средний",
            HealthMetricStatus.HIGH, "Высокий",
            HealthMetricStatus.HHIGH, "Выше среднего");


    /**
     * Данные метод расчитывает Индекс массы тела (ИМТ), а так же анализ рузельтата:
     *  меньше 15 – острый дефицит массы;
     *  от 15 до 20 – дефицит массы;
     *  от 20 до 24 – нормальный;
     *  от 25 до 30 – избыточная массы.
     *  свыше 30 – ожирение организма и отдельных его частей.
     *
     * @param weight масса тела, кг
     * @param height - рост, cм
     * @return {@link MetricDTO} с результатом расчета и анализом
     * @throws IllegalArgumentException если параметры рост и вес находяться вне доспустимого диапазона
     */
    public MetricDTO bodyMassIndex (Double weight,  Double height) throws IllegalArgumentException{
        HealthMetricsValidator.validateHeightValue(height);
        HealthMetricsValidator.validateWeightValue(weight);

        height /= 100.0; //covert height to meter

        double value = weight / (height * height);

        MetricDTO result = new MetricDTO();
        result.setValue(value);

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


    /**
     * Данный метод возвращает {@link MetricDTO} пульса
     * @param heartRate пульс, ударов в минту
     * @return {@link MetricDTO} пульса
     * @throws IllegalArgumentException если параметры пульса находяться вне доспустимого диапазона
     * @see HealthMetricsValidator
     */
    public MetricDTO heartRateIndex(Integer heartRate) throws IllegalArgumentException{
        HealthMetricsValidator.validateHeartRate(heartRate);

        MetricDTO result = new MetricDTO();
        result.setValue(Double.valueOf(heartRate));

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

    /**
     * Данный метод расчитывает и возвращает {@link MetricDTO} оценку физического состояния
     * расчитанную по методике Д. Н. Давиденко.
     *
     * @param heartRate пульс, уд/мин
     * @param sysAD систолическое артериальное давление, мм рт.ст
     * @param diaAD диастолическое артериальное давление, мм рт.ст
     * @param weight вес, кг
     * @param height рост, см
     * @param age возраст, лет
     * @param genderType пол
     * @return {@link MetricDTO} с индексом физического состояния
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public MetricDTO physicalLevelIndex(Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age, GenderType genderType) throws IllegalArgumentException{
        HealthMetricsValidator.validateHeightValue(height);
        HealthMetricsValidator.validateWeightValue(weight);
        HealthMetricsValidator.validateBloodPressureValues(sysAD, diaAD);
        HealthMetricsValidator.validateHeartRate(heartRate);
        HealthMetricsValidator.validateAge(age);

        double avgAD = diaAD + (sysAD - diaAD) / 3.0;

        double value = (700.0 - 3*heartRate - 2.5*avgAD -2.7*age + 0.28*weight) / (350.0 - 2.6*age + 0.21*height);

        MetricDTO result = new MetricDTO();
        result.setValue(value);

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


    /**
     * Данный метод расчитывает индекс функциональных изменений
     * Индекс расчиствается по методике А.П.Берсеневой и используется для оценки уровня функционирования
     * системы кровообращения и определения ее адаптационного потенциала
     *
     * @param heartRate пульс, уд/мин
     * @param sysAD систолическое артериальное давление, мм рт.ст
     * @param diaAD диастолическое артериальное давление, мм рт.ст
     * @param weight вес, кг
     * @param height рост, см
     * @param age возраст, лет
     * @return {@link MetricDTO} с индексом функциональных изменений
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public MetricDTO functionalChangesIndex (Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age) throws IllegalArgumentException{
        HealthMetricsValidator.validateHeightValue(height);
        HealthMetricsValidator.validateWeightValue(weight);
        HealthMetricsValidator.validateBloodPressureValues(sysAD, diaAD);
        HealthMetricsValidator.validateHeartRate(heartRate);
        HealthMetricsValidator.validateAge(age);

        double value = 0.01*heartRate + 0.014*sysAD + 0.008*diaAD + 0.014*age + 0.009*weight - 0.009*height - 0.27;

        MetricDTO result = new MetricDTO();
        result.setValue(value);

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
