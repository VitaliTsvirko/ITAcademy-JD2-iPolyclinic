package by.it_academy.jd2.core.healthmetrics.enumeration;

import by.it_academy.jd2.core.healthmetrics.analysers.BodyMassIndexAnalyser;
import by.it_academy.jd2.core.healthmetrics.analysers.FunctionalChangesIndexAnalyser;
import by.it_academy.jd2.core.healthmetrics.analysers.HeartRateIndexAnalyser;
import by.it_academy.jd2.core.healthmetrics.analysers.PhysicalLevelIndexAnalyser;
import by.it_academy.jd2.core.healthmetrics.analysers.IHealthMetricAnalyser;

import java.util.Optional;

public enum HealthMetricsTypes {
    HEIGHT ("Вес", "кг", null),
    WEIGHT ("Рост", "см", null),
    HEART_RATE ("Пульс", "уд/мин", new HeartRateIndexAnalyser()),
    AD_SYS ("Cистолическое артериальное давление", "мм рт.ст", null),
    AD_DIA ("Диастолическое артериальное давление", "мм рт.ст", null),
    BODY_TEMPERATURE ("Температура тела", "oC", null),

    BMI ("Индекс массы тела", "", new BodyMassIndexAnalyser()),
    PHYS_LEVEL ("Индекс физического состояния", "", new PhysicalLevelIndexAnalyser()),
    FUNC_CHANGE ("Индекс функциональных изменений", "", new FunctionalChangesIndexAnalyser());

    private final String description;
    private final String unit;
    private final IHealthMetricAnalyser healthMetricAnalyser;

    HealthMetricsTypes(String description, String unit, IHealthMetricAnalyser healthMetricAnalyser){
        this.healthMetricAnalyser = healthMetricAnalyser;
        this.description = description;
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public IHealthMetricAnalyser getHealthMetricAnalyser() {
        return healthMetricAnalyser;
    }
}
