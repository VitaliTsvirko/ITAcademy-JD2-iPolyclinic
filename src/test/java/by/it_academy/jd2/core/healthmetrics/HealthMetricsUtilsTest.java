package by.it_academy.jd2.core.healthmetrics;

import org.junit.jupiter.api.DisplayName;


@DisplayName("Testing HealthMetricsUtils")
class HealthMetricsUtilsTest {


/*

    @DisplayName("bodyMassIndex - isNullOrZeroParametersValue")
    @ParameterizedTest(name = "#{index}: weight = {0},  height = {1}")
    @MethodSource("bodyMassIndexZeroParametersProvider")
    void bodyMassIndex_isNullOrZeroParametersValue(Double weight,  Double height) {
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsAnalysisHandler().bodyMassIndex(weight, height));
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
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsAnalysisHandler().bodyMassIndex(weight, height));
    }

    static Stream<Arguments> bodyMassIndexOutORangeParametersProvider() {
        return Stream.of(arguments(HealthMetricsUtils.WEIGHT_MAX + 0.5, 2.0),
                arguments(50.0, HealthMetricsUtils.HEIGHT_MAX + 0.5),
                arguments(-15.0, 50.0),
                arguments(1.5, -50.0)
        );
    }

    @DisplayName("bodyMassIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: weight = {0},  height = {1}")
    @MethodSource("bodyMassIndexValidParametersProvider")
    void bodyMassIndex_validParametersValue(Double weight, Double height, HealthMetricAnalysisDTO expectedResult) {
        HealthMetricsAnalysisHandler calc = new HealthMetricsAnalysisHandler();

        HealthMetricAnalysisDTO result = calc.bodyMassIndex(weight, height);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> bodyMassIndexValidParametersProvider() {
        return Stream.of(arguments(46.5, 180.0, new HealthMetricAnalysisDTO(14.35, HealthMetricStatus.LLOW, "Острый дефицит массы")),
                arguments(60.0, 180.0, new HealthMetricAnalysisDTO(18.5, HealthMetricStatus.LOW, "Дефицит массы")),
                arguments(70.0, 180.0, new HealthMetricAnalysisDTO(21.6, HealthMetricStatus.OK, "Нормальный")),
                arguments(85.0, 180.0, new HealthMetricAnalysisDTO(26.23, HealthMetricStatus.HIGH, "Избыточная масса")),
                arguments(100.0, 180.0, new HealthMetricAnalysisDTO(30.86, HealthMetricStatus.HHIGH, "Ожирение организма и отдельных его частей"))
        );
    }





    @DisplayName("heartRateIndex - notValidParametersValue")
    @ParameterizedTest(name = "#{index}: value = {0}")
    @ValueSource(ints = {0, -15, 350, 5})
    @NullSource
    void heartRateIndex_isNotValidValue(Integer heartRate) {
        assertThrows(IllegalArgumentException.class, () -> new HealthMetricsAnalysisHandler().heartRateIndex(heartRate));
    }


    @DisplayName("heartRateIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: value = {0}")
    @MethodSource("heartRateIndexValidParametersProvider")
    void heartRateIndex_validValues(Integer heartRate, HealthMetricAnalysisDTO expectedResult) {
        HealthMetricsAnalysisHandler calc = new HealthMetricsAnalysisHandler();

        HealthMetricAnalysisDTO result = calc.heartRateIndex(heartRate);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> heartRateIndexValidParametersProvider() {
        return Stream.of(arguments(50, new HealthMetricAnalysisDTO(50.0, HealthMetricStatus.LOW, "Редкий")),
                arguments(70, new HealthMetricAnalysisDTO(70.0, HealthMetricStatus.OK, "Умеренный")),
                arguments(100, new HealthMetricAnalysisDTO(100.0, HealthMetricStatus.HIGH, "Частый"))
        );
    }


    @DisplayName("functionalChangesIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: heartRate = {0}, sysAD = {1}, diaAD = {2}, weight = {3}, height = {4}, age = {5}")
    @MethodSource("functionalChangesIndexValidParametersProvider")
    void functionalChangesIndex_validValues(Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age, HealthMetricAnalysisDTO expectedResult) {
        HealthMetricsAnalysisHandler calc = new HealthMetricsAnalysisHandler();

        HealthMetricAnalysisDTO result = calc.functionalChangesIndex(heartRate, sysAD, diaAD, weight, height, age);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());
    }

    static Stream<Arguments> functionalChangesIndexValidParametersProvider() {
        return Stream.of(arguments(60, 120, 80, 60.0, 174.0, 30, new HealthMetricAnalysisDTO(2.1, HealthMetricStatus.OK, "Норма"))
        );
    }

    @DisplayName("healthRateIndex - validParametersValue")
    @ParameterizedTest(name = "#{index}: heartRate = {0}, sysAD = {1}, diaAD = {2}, weight = {3}, height = {4}, age = {5}")
    @MethodSource("physicalLevelIndexParametersProvider")
    void physicalLevelIndex_validValues(Integer heartRate, Integer sysAD, Integer diaAD, Double weight,  Double height, Integer age, GenderType genderType, HealthMetricAnalysisDTO expectedResult) {
        HealthMetricsAnalysisHandler calc = new HealthMetricsAnalysisHandler();

        HealthMetricAnalysisDTO result = calc.physicalLevelIndex(heartRate, sysAD, diaAD, weight, height, age, genderType);

        assertEquals(result.getValue(), expectedResult.getValue(), 0.1);
        assertEquals(result.getStatus(), expectedResult.getStatus());
        assertEquals(result.getComment(), expectedResult.getComment());

    }

    static Stream<Arguments> physicalLevelIndexParametersProvider() {
        return Stream.of(
                arguments(77, 120, 80, 83.5, 173.0, 34, GenderType.MALE,
                        new HealthMetricAnalysisDTO(0.561, HealthMetricStatus.OK, HealthMetricsAnalysisHandler.physicalLevelIndexStatus.get(HealthMetricStatus.OK))),
                arguments(77, 120, 80, 83.5, 173.0, 34, GenderType.FEMALE,
                        new HealthMetricAnalysisDTO(0.561, HealthMetricStatus.HIGH, HealthMetricsAnalysisHandler.physicalLevelIndexStatus.get(HealthMetricStatus.HIGH)))
        );
    }
*/

}