package by.it_academy.jd2.core.healthmetrics.dto;

import by.it_academy.jd2.domain.enumeration.GenderType;

/**
 * Created by Vitali Tsvirko
 */
public class UserHealthMetricDTO {

    private Double height;

    private Double weight;

    private Integer heartRate;

    private Integer adSys;

    private Integer adDia;

    private Integer bodyTemperature;

    private Integer age;

    private GenderType genderType;

    public UserHealthMetricDTO(){

    }

    public UserHealthMetricDTO(Double height, Double weight, Integer heartRate, Integer adSys, Integer adDia, Integer bodyTemperature, Integer age, GenderType genderType) {
        this.height = height;
        this.weight = weight;
        this.heartRate = heartRate;
        this.adSys = adSys;
        this.adDia = adDia;
        this.bodyTemperature = bodyTemperature;
        this.age = age;
        this.genderType = genderType;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getAdSys() {
        return adSys;
    }

    public void setAdSys(Integer adSys) {
        this.adSys = adSys;
    }

    public Integer getAdDia() {
        return adDia;
    }

    public void setAdDia(Integer adDia) {
        this.adDia = adDia;
    }

    public Integer getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Integer bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderType getGenderType() {
        return genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    @Override
    public String toString() {
        return "UserHealthMetric{" +
                "height=" + height +
                ", weight=" + weight +
                ", heartRate=" + heartRate +
                ", adSys=" + adSys +
                ", adDia=" + adDia +
                ", bodyTemperature=" + bodyTemperature +
                ", age=" + age +
                ", genderType=" + genderType +
                '}';
    }
}
