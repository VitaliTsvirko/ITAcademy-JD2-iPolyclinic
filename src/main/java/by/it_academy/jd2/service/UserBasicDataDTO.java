package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Vitali Tsvirko
 */
public class UserBasicDataDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String eMail;

    @JsonProperty("user_state")
    private ApplicationUserState state;

    @JsonProperty("user_role")
    private UserRoles userRole;

    public UserBasicDataDTO() {
    }

    public UserBasicDataDTO(User user) {
        this.id = user.getId();
        this.phoneNo = user.getPhoneNo();
        this.eMail = user.geteMail();
        this.userRole = user.getUserRole();
        this.state = user.getState();
        this.fullName = user.getPassport().getSurname() + " " + user.getPassport().getName() + " " + user.getPassport().getPatronymic();
        this.dateOfBirth = user.getPassport().getDateOfBirth();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public ApplicationUserState getState() {
        return state;
    }

    public void setState(ApplicationUserState state) {
        this.state = state;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}