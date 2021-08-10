package by.it_academy.jd2.core;

import by.it_academy.jd2.core.healthmetrics.HealthMetricsCalculator;
import by.it_academy.jd2.core.healthmetrics.HealthMetricsHandler;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.dto.UserHealthMetricDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.enumeration.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class HealthMetricsHandlerTest {


    private HealthMetricsHandler handler = new HealthMetricsHandler(new HealthMetricsCalculator());


    @DisplayName("analysisMetrics - null source")
    @ParameterizedTest
    @NullSource
    void analysisMetrics_nullSource(UserHealthMetricDTO data) {
        assertThrows(IllegalArgumentException.class, () -> handler.calculateMetrics(data));
    }

/*

    @DisplayName("analysisMetrics - null source")
    @ParameterizedTest
    @NullSource
    void analysisMetrics_ValidValue(List<HealthMetricEntityDTO> data, List<HealthMetricAnalysisDTO> expectedResult) {
        List<HealthMetricAnalysisDTO> healthMetricAnalysisDTOS = handler.analysisMetrics(data);


    }

    static Stream<Arguments> analysisMetricsParametersProvider() {


        UserHealthMetric metric1 = new UserHealthMetric();
        metric1.setHeartRate(70);
        metric1.setWeight(83.0);
        metric1.setHeight(176.0);
        metric1.setAdSys(120);
        metric1.setAdDia(80);
        metric1.setAge(35);
        metric1.setGenderType(GenderType.MALE);
        List<HealthMetricsTypes> result1 = List.of(HealthMetricsTypes.BMI, HealthMetricsTypes.FUNC_CHANGE, HealthMetricsTypes.PHYS_LEVEL);

        UserHealthMetric metric2 = new UserHealthMetric();
        metric2.setHeartRate(70);
        metric2.setWeight(83.0);
        metric2.setHeight(176.0);
        metric2.setAdSys(120);
        metric2.setAdDia(80);
        metric2.setAge(35);
        List<HealthMetricsTypes> result2 = List.of(HealthMetricsTypes.BMI, HealthMetricsTypes.FUNC_CHANGE);

        UserHealthMetric metric3 = new UserHealthMetric();
        metric3.setWeight(83.0);
        metric3.setHeight(176.0);
        List<HealthMetricsTypes> result3 = List.of(HealthMetricsTypes.BMI);

        return Stream.of(arguments(metric1, result1),
                arguments(metric2, result2),
                arguments(metric3, result3)
        );
    }


*/






    @DisplayName("calculateMetrics - null source")
    @ParameterizedTest
    @NullSource
    void calculateMetrics_nullSource(List<HealthMetricEntityDTO> data) {
        assertThrows(IllegalArgumentException.class, () -> handler.analysisMetrics(data, GenderType.MALE));
    }

    @DisplayName("calculateMetrics - valid value")
    @ParameterizedTest(name = "#{index}: UserHealthMetric = {0}")
    @MethodSource("calculateMetricsParametersProvider")
    void calculateMetrics_ValidValue(UserHealthMetricDTO data, List<HealthMetricsTypes> expectedResult) {
        List<HealthMetricEntityDTO> result = handler.calculateMetrics(data);

        List<HealthMetricsTypes> resultTypes = result.stream().map(item -> item.getType()).collect(Collectors.toList());

        assertEquals(resultTypes, expectedResult);
    }


    static Stream<Arguments> calculateMetricsParametersProvider() {
        UserHealthMetricDTO metric1 = new UserHealthMetricDTO();
        metric1.setHeartRate(70);
        metric1.setWeight(83.0);
        metric1.setHeight(176.0);
        metric1.setAdSys(120);
        metric1.setAdDia(80);
        metric1.setAge(35);
        metric1.setGenderType(GenderType.MALE);
        List<HealthMetricsTypes> result1 = List.of(HealthMetricsTypes.BMI, HealthMetricsTypes.FUNC_CHANGE, HealthMetricsTypes.PHYS_LEVEL);

        UserHealthMetricDTO metric2 = new UserHealthMetricDTO();
        metric2.setHeartRate(70);
        metric2.setWeight(83.0);
        metric2.setHeight(176.0);
        metric2.setAdSys(120);
        metric2.setAdDia(80);
        metric2.setAge(35);
        List<HealthMetricsTypes> result2 = List.of(HealthMetricsTypes.BMI, HealthMetricsTypes.FUNC_CHANGE);

        UserHealthMetricDTO metric3 = new UserHealthMetricDTO();
        metric3.setWeight(83.0);
        metric3.setHeight(176.0);
        List<HealthMetricsTypes> result3 = List.of(HealthMetricsTypes.BMI);

        return Stream.of(arguments(metric1, result1),
                        arguments(metric2, result2),
                        arguments(metric3, result3)
        );
    }

}