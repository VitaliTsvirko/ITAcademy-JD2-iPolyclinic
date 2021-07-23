package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.enumeration.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Created by Vitali Tsvirko
 */
public class PassportDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic ;

    @JsonProperty("personal_no")
    private String personalNo;

    @JsonProperty("country_of_issue_code")
    private String countryOfIssueCode;

    @JsonProperty("country_of_issue")
    private String countryOfIssue;

    @JsonProperty("passport_no")
    private String passportNo;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("sex")
    private Sex sex;

    @JsonProperty("issue_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @JsonProperty("expiration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    public PassportDTO() {
    }

    public PassportDTO(Passport passport){
        if (passport != null) {
            this.setName(passport.getName());
            this.setSurname(passport.getSurname());
            this.setPatronymic(passport.getPatronymic());
            this.setDateOfBirth(passport.getDateOfBirth());
            this.setPlaceOfBirth(passport.getPlaceOfBirth());
            this.setNationality(passport.getNationality());
            this.setPersonalNo(passport.getPersonalNo());
            this.setPassportNo(passport.getPassportNo());
            this.setCountryOfIssue(passport.getCountryOfIssue().getShotName());
            this.setIssueDate(passport.getIssueDate());
            this.setExpirationDate(passport.getExpirationDate());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }

    public String getCountryOfIssueCode() {
        return countryOfIssueCode;
    }

    public void setCountryOfIssueCode(String countryOfIssueCode) {
        this.countryOfIssueCode = countryOfIssueCode;
    }

    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
}
