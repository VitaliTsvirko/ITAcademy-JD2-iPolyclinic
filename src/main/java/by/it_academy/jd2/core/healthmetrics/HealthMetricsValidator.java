package by.it_academy.jd2.core.healthmetrics;

public class HealthMetricsValidator {

    public static Double WEIGHT_MIN = 0.0;
    public static Double WEIGHT_MAX = 1000.0;

    public static Double HEIGHT_MIN = 0.0;
    public static Double HEIGHT_MAX = 300.0;

    public static Integer SYS_AD_MIN = 40;
    public static Integer SYS_AD_MAX = 300;

    public static Integer DIA_AD_MAX = 300;
    public static Integer DIA_AD_MIN = 40;

    public static Integer HEART_RATE_MAX = 300;
    public static Integer HEART_RATE_MIN = 20;

    public static Integer AGE_MIN = 0;
    public static Integer AGE_MAX = 150;

    private HealthMetricsValidator(){

    }


    /**
     * Данный метод выполняет проверку значения роста
     * @param height рост, см
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public static void validateHeightValue(Double height) throws IllegalArgumentException{
        if (height == null){
            throw new IllegalArgumentException("Height is empty or zero");
        }

        if(isNotInRange(height, HEIGHT_MIN, HEIGHT_MAX)){
            throw new IllegalArgumentException("Height is out of valid range");
        }
    }


    /**
     * Данный метод выполняет проверку значения веса
     * @param weight вес, кг
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public static void validateWeightValue(Double weight) throws IllegalArgumentException{
        if (weight == null){
            throw new IllegalArgumentException("Weight is empty or zero");
        }

        if(isNotInRange(weight, WEIGHT_MIN, WEIGHT_MAX)){
            throw new IllegalArgumentException("Weight is out of valid range");
        }
    }


    /**
     * Данный метод выполняет проверку значения артериального давления
     * @param sysAD систолическое артериальное давление, мм рт.ст
     * @param diaAD диастолическое артериальное давление, мм рт.ст
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public static void validateBloodPressureValues(Integer sysAD, Integer diaAD){
        if (sysAD == null || diaAD == null){
            throw new IllegalArgumentException("one of AD value is null");
        }

        if(isNotInRange(sysAD, SYS_AD_MIN, SYS_AD_MAX)){
            throw new IllegalArgumentException("sysAD is out of valid range");
        }

        if(isNotInRange(diaAD, DIA_AD_MIN, DIA_AD_MAX)){
            throw new IllegalArgumentException("diaAD is out of valid range");
        }
    }


    /**
     * Данный метод выполняет проверку значения пульса
     * @param heartRate пульс, уд/мин
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public static void validateHeartRate(Integer heartRate){
        if (heartRate == null){
            throw new IllegalArgumentException("HeartRate value is null");
        }

        if(isNotInRange(heartRate, HEART_RATE_MIN, HEART_RATE_MAX)){
            throw new IllegalArgumentException("HeartRate is out of valid range");
        }
    }


    /**
     * Данный метод выполняет проверку значения пульса
     * @param age возраст, лет
     * @throws IllegalArgumentException если параметры находяться вне доспустимого диапазона
     */
    public static void validateAge(Integer age){
        if (age == null){
            throw new IllegalArgumentException("Age value is null");
        }

        if(isNotInRange(age, AGE_MIN, AGE_MAX)){
            throw new IllegalArgumentException("Age is out of valid range");
        }
    }


    /**
     * Данные метод выполняет проверку нахождения числа в диапазоне
     * @param x число
     * @param lower нижний передел
     * @param upper верхний предел
     * @return true если значение находиться в диапазоне, false если значение находиться вне диапазона
     */
    public static boolean isInRange(double x, double lower, double upper) {
        return lower < x && x < upper;
    }


    /**
     * Данные метод выполняет проверку нахождения числа вне диапазона
     * @param x число
     * @param lower нижний передел
     * @param upper верхний предел
     * @return true если значение находиться вне диапазона, false если значение находиться в диапазоне
     */
    public static boolean isNotInRange(double x, double lower, double upper) {
        return !isInRange(x, lower, upper);
    }

}
