package by.it_academy.jd2.core.healthmetrics;

import by.it_academy.jd2.core.healthmetrics.analysers.IHealthMetricAnalyser;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.dto.UserHealthMetricDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class HealthMetricsHandler implements IHealthMetricHandler{

    private final HealthMetricsCalculator healthMetricsCalculator;

    public HealthMetricsHandler(HealthMetricsCalculator healthMetricsHandler) {
        this.healthMetricsCalculator = healthMetricsHandler;
    }

    /**
     * Данные метод вычисляет метрики здоровья:
     * - bodyMassIndex
     * - functionalChangesIndex
     * - physicalLevelIndex
     *
     * @param metrics
     * @return
     */
    @Override
    public List<HealthMetricEntityDTO> calculateMetrics(UserHealthMetricDTO metrics) {
        List<HealthMetricEntityDTO> result = new ArrayList<>();

        if(metrics == null){
            throw new IllegalArgumentException("Empty metrics");
        }

        healthMetricsCalculator.bodyMassIndex(metrics.getWeight(),
                metrics.getHeight()
        ).ifPresent(val ->
                result.add(new HealthMetricEntityDTO(HealthMetricsTypes.BMI, val, LocalDateTime.now()))
        );

        healthMetricsCalculator.functionalChangesIndex(metrics.getHeartRate(),
                metrics.getAdSys(),
                metrics.getAdDia(),
                metrics.getWeight(),
                metrics.getHeight(),
                metrics.getAge()
        ).ifPresent(val ->
                result.add(new HealthMetricEntityDTO(HealthMetricsTypes.FUNC_CHANGE, val, LocalDateTime.now()))
        );

        healthMetricsCalculator.physicalLevelIndex(metrics.getHeartRate(),
                metrics.getAdSys(),
                metrics.getAdDia(),
                metrics.getWeight(),
                metrics.getHeight(),
                metrics.getAge(),
                metrics.getGenderType()
        ).ifPresent(val ->
                result.add(new HealthMetricEntityDTO(HealthMetricsTypes.PHYS_LEVEL, val, LocalDateTime.now()))
        );

        return result;
    }


    /**
     * Данные метод анализирует метрики здоровья
     *
     * @param metrics List<HealthMetricEntityDTO>
     * @return List<HealthMetricAnalysisDTO>
     */
    @Override
    public List<HealthMetricAnalysisDTO> analysisMetrics(List<HealthMetricEntityDTO> metrics, GenderType genderType){
        if (metrics == null){
            throw new IllegalArgumentException("Data is empty");
        }

        List<HealthMetricAnalysisDTO> result = new ArrayList<>();

        metrics.forEach(metric ->
            {
                IHealthMetricAnalyser healthMetricAnalyser = metric.getType().getHealthMetricAnalyser();
                if (healthMetricAnalyser == null){
                    result.add(new HealthMetricAnalysisDTO(metric.getType(), metric.getValue(), metric.getTimestamp(), HealthMetricStatus.NOT_APPLICABLE, ""));
                } else {
                    HealthMetricAnalysisDTO analysis = healthMetricAnalyser.analysis(metric.getValue(), genderType);
                    analysis.setTimestamp(metric.getTimestamp());

                    result.add( analysis );
                }
        }
        );

        return result;
    }



/*

    public UserHealthMetric getCurrentMetricsValue(List<HealthMetricEntityDTO> rawData){
        UserHealthMetric userHealthMetric = new UserHealthMetric();

        Map<HealthMetricsTypes, HealthMetricEntityDTO> metricsMap = rawData.stream()
                .collect(Collectors.toMap(item -> item.getType(), item -> item));

        userHealthMetric.setHeight(metricsMap.get(HealthMetricsTypes.HEIGHT).getValue());
        userHealthMetric.setWeight(metricsMap.get(HealthMetricsTypes.WEIGHT).getValue());

        userHealthMetric.setHeartRate(metricsMap.get(HealthMetricsTypes.HEART_RATE).getValue().intValue());

        userHealthMetric.setAdDia(metricsMap.get(HealthMetricsTypes.AD_DIA).getValue().intValue());
        userHealthMetric.setAdSys(metricsMap.get(HealthMetricsTypes.AD_SYS).getValue().intValue());

        userHealthMetric.setBodyTemperature(metricsMap.get(HealthMetricsTypes.BODY_TEMPERATURE).getValue().intValue());

        return userHealthMetric;
    }



    public List<HealthMetricAnalysisDTO> metricsHandler(List<HealthMetricEntityDTO> rawData){
        UserHealthMetric metricsValues = getCurrentMetricsValue(rawData);

        healthMetricsStatusHandler.bodyMassIndex(metricsValues.getWeight(), metricsValues.getHeight());








*/
/*        rawData.forEach(item -> {
            switch (item.getType()){

                case HEART_RATE:
            }

        });


        switch (rawData)*//*




        return null;
    }
*/





}
