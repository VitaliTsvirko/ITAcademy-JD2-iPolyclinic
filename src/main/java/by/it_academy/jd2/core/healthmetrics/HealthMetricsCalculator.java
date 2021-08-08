package by.it_academy.jd2.core.healthmetrics;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.domain.enumeration.GenderType;

import java.util.Optional;

/**
 * https://kpfu.ru/staff_files/F1073379016/fiziologicheskie_osnovy_diagnostiki_funkcionalnoj_sostoyaniya_organizma.pdf
 *
 * https://dyn.ru/publications/AssessmentOfTheLevelOfHealth.pdf
 *
 */

public class HealthMetricsCalculator {

    /**
     * Данные метод расчитывает Индекс массы тела (ИМТ)
     *
     * @param weight масса тела, кг
     * @param height - рост, cм
     * @return {@link HealthMetricAnalysisDTO} с результатом расчета и анализом
     * @throws IllegalArgumentException если параметры рост и вес находяться вне доспустимого диапазона
     */
    public Optional<Double> bodyMassIndex (Double weight, Double height){
        try{
            HealthMetricsUtils.validateHeightValue(height);
            HealthMetricsUtils.validateWeightValue(weight);
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }

        height /= 100.0; //covert height to meter

        return Optional.of( weight / (height * height) );
    }


     /**
     * Данный метод расчитывает оценку физического состояния по методике Д. Н. Давиденко.
     *
     * @param heartRate пульс, уд/мин
     * @param sysAD систолическое артериальное давление, мм рт.ст
     * @param diaAD диастолическое артериальное давление, мм рт.ст
     * @param weight вес, кг
     * @param height рост, см
     * @param age возраст, лет
     * @param genderType пол
     * @return {@link HealthMetricAnalysisDTO} с индексом физического состояния
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public Optional<Double> physicalLevelIndex(Integer heartRate, Integer sysAD, Integer diaAD, Double weight, Double height, Integer age, GenderType genderType) throws IllegalArgumentException{
        try {
            HealthMetricsUtils.validateHeightValue(height);
            HealthMetricsUtils.validateWeightValue(weight);
            HealthMetricsUtils.validateBloodPressureValues(sysAD, diaAD);
            HealthMetricsUtils.validateHeartRate(heartRate);
            HealthMetricsUtils.validateAge(age);
            if (genderType == null ){
                throw new IllegalArgumentException("Gender type is null");
            }
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }

        double avgAD = diaAD + (sysAD - diaAD) / 3.0;

        return Optional.of( (700.0 - 3*heartRate - 2.5*avgAD -2.7*age + 0.28*weight) / (350.0 - 2.6*age + 0.21*height) );
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
     * @return {@link HealthMetricAnalysisDTO} с индексом функциональных изменений
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public Optional<Double> functionalChangesIndex (Integer heartRate, Integer sysAD, Integer diaAD, Double weight, Double height, Integer age){
        try {
            HealthMetricsUtils.validateHeightValue(height);
            HealthMetricsUtils.validateWeightValue(weight);
            HealthMetricsUtils.validateBloodPressureValues(sysAD, diaAD);
            HealthMetricsUtils.validateHeartRate(heartRate);
            HealthMetricsUtils.validateAge(age);
        } catch (IllegalArgumentException e){
            return Optional.empty();
        }

        return Optional.of( 0.01*heartRate + 0.014*sysAD + 0.008*diaAD + 0.014*age + 0.009*weight - 0.009*height - 0.27);
    }

}
