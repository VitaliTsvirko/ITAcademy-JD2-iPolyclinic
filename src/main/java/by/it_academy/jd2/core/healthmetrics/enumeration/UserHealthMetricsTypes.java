package by.it_academy.jd2.core.healthmetrics.enumeration;

public enum UserHealthMetricsTypes {
    HEIGHT ("Вес", "кг"),
    WEIGHT ("Рост", "см"),
    HEART_RATE ("Пульс", "уд/мин"),
    AD_SYS ("Cистолическое артериальное давление", "мм рт.ст"),
    AD_DIA ("Диастолическое артериальное давление", "мм рт.ст"),
    BODY_TEMPERATURE ("Температура тела", "oC"),

    BMI ("Индекс массы тела", ""),
    PHYS_LEVEL ("Индекс физического состояния", ""),
    FUNC_CHANGE ("Индекс функциональных изменений", "");

    private final String description;
    private final String unit;

    UserHealthMetricsTypes(String description, String unit){
        this.description = description;
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }
}
