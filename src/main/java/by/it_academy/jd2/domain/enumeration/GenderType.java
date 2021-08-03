package by.it_academy.jd2.domain.enumeration;

public enum GenderType {
    MALE ("Мужской"),
    FEMALE ("Женский");

    String genderName;

    GenderType(String genderName){
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }
}
