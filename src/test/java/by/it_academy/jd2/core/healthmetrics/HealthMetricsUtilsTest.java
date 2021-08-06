package by.it_academy.jd2.core.healthmetrics;

import by.it_academy.jd2.core.healthmetrics.dto.MetricDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricStatus;
import by.it_academy.jd2.domain.enumeration.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@DisplayName("Testing HealthMetricsUtils")
class HealthMetricsUtilsTest {



    @DisplayName("bodyMassIndex - isNullOrZeroParametersValue")
    @ParameterizedTest(name = "#{index}: weight = {0},  height = {1}")
    @MethodSource("bodyMassIndexZeroParametersProvider")
    void bodyMassIndex_isNullOrZeroParametersValue(Double weight,  Double height) {
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsUtils().bodyMassIndex(weight, height));
    }

    static Stream<Arguments> bodyMassIndexZeroParametersProvider() {
        return Stream.of(arguments(0.0, 0.0),
                arguments(null, null),
                arguments(0.0, null),
                arguments(null, 0.0)
        );
    }

    @DisplayName("bodyMassIndex - isOutORangeParametersValue")
    @ParameterizedTest(name = "#{index}: weight = {0},  height = {1}")
    @MethodSource("bodyMassIndexOutORangeParametersProvider")
    void bodyMassIndex_isOutORangeParametersValue(Double weight,  Double height) {
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsUtils().bodyMassIndex(weight, height));
    }

    static Stream<Arguments> bodyMassIndexOutORangeParametersProvider() {
        return Stream.of(arguments(HealthMetricsValidator.WEIGHT_MAX + 0.5, 2.0),
                arguments(50.0, HealthMetricsValidator.HEIGHT_MAX + 0.5),
                arguments(-15.0, 50.0),
                arguments(1.5, -50.0)
        );
    }

    @DisplayName("bodyMassIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: weight = {0},  height = {1}")
    @MethodSource("bodyMassIndexValidParametersProvider")
    void bodyMassIndex_validParametersValue(Double weight, Double height, MetricDTO expectedResult) {
        HealthMetricsUtils calc = new HealthMetricsUtils();

        MetricDTO result = calc.bodyMassIndex(weight, height);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> bodyMassIndexValidParametersProvider() {
        return Stream.of(arguments(46.5, 180.0, new MetricDTO(14.35, HealthMetricStatus.LLOW, "Острый дефицит массы")),
                arguments(60.0, 180.0, new MetricDTO(18.5, HealthMetricStatus.LOW, "Дефицит массы")),
                arguments(70.0, 180.0, new MetricDTO(21.6, HealthMetricStatus.OK, "Нормальный")),
                arguments(85.0, 180.0, new MetricDTO(26.23, HealthMetricStatus.HIGH, "Избыточная масса")),
                arguments(100.0, 180.0, new MetricDTO(30.86, HealthMetricStatus.HHIGH, "Ожирение организма и отдельных его частей"))
        );
    }





    @DisplayName("heartRateIndex - notValidParametersValue")
    @ParameterizedTest(name = "#{index}: value = {0}")
    @ValueSource(ints = {0, -15, 350, 5})
    @NullSource
    void heartRateIndex_isNotValidValue(Integer heartRate) {
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsUtils().heartRateIndex(heartRate));
    }


    @DisplayName("heartRateIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: value = {0}")
    @MethodSource("heartRateIndexValidParametersProvider")
    void heartRateIndex_validValues(Integer heartRate, MetricDTO expectedResult) {
        HealthMetricsUtils calc = new HealthMetricsUtils();

        MetricDTO result = calc.heartRateIndex(heartRate);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> heartRateIndexValidParametersProvider() {
        return Stream.of(arguments(50, new MetricDTO(50.0, HealthMetricStatus.LOW, "Редкий")),
                arguments(70, new MetricDTO(70.0, HealthMetricStatus.OK, "Умеренный")),
                arguments(100, new MetricDTO(100.0, HealthMetricStatus.HIGH, "Частый"))
        );
    }


    @DisplayName("functionalChangesIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: heartRate = {0}, sysAD = {1}, diaAD = {2}, weight = {3}, height = {4}, age = {5}")
    @MethodSource("functionalChangesIndexValidParametersProvider")
    void functionalChangesIndex_validValues(Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age, MetricDTO expectedResult) {
        HealthMetricsUtils calc = new HealthMetricsUtils();

        MetricDTO result = calc.functionalChangesIndex(heartRate, sysAD, diaAD, weight, height, age);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());
    }

    static Stream<Arguments> functionalChangesIndexValidParametersProvider() {
        return Stream.of(arguments(60, 120, 80, 60.0, 174.0, 30, new MetricDTO(2.1, HealthMetricStatus.OK, "Норма"))
        );
    }

    @DisplayName("healthRateIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: heartRate = {0}, sysAD = {1}, diaAD = {2}, weight = {3}, height = {4}, age = {5}")
    @MethodSource("physicalLevelIndexParametersProvider")
    void physicalLevelIndex_validValues(Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age, GenderType genderType, MetricDTO expectedResult) {
        HealthMetricsUtils calc = new HealthMetricsUtils();

        MetricDTO result = calc.physicalLevelIndex(heartRate, sysAD, diaAD, weight, height, age, genderType);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> physicalLevelIndexParametersProvider() {
        return Stream.of(
                arguments(77, 120, 80, 83.5, 173.0, 34, GenderType.MALE,
                        new MetricDTO(0.561, HealthMetricStatus.OK, HealthMetricsUtils.physicalLevelIndexStatus.get(HealthMetricStatus.OK))),
                arguments(77, 120, 80, 83.5, 173.0, 34, GenderType.FEMALE,
                        new MetricDTO(0.561, HealthMetricStatus.HIGH, HealthMetricsUtils.physicalLevelIndexStatus.get(HealthMetricStatus.HIGH)))
        );
    }

}