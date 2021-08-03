package by.it_academy.jd2.service.dto;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Vitali Tsvirko
 */
public class UserBasicDataDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirth;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String eMail;

    @JsonProperty("user_state")
    private ApplicationUserState state;

    @JsonProperty("user_role")
    private UserRoles userRole;

    @JsonProperty("full_address")
    private String fullAddress;

    public UserBasicDataDTO() {
    }

    public UserBasicDataDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.phoneNo = user.getPhoneNo();
            this.eMail = user.geteMail();
            this.userRole = user.getUserRole();
            this.state = user.getState();
            if (user.getPassport() != null){
                this.fullName = user.getPassport().getSurname() + " " + user.getPassport().getName() + " " + user.getPassport().getPatronymic();
                this.dateOfBirth = user.getPassport().getDateOfBirth();
                this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();
            }

            if (user.getAddress() != null) {
                this.fullAddress = Stream.of(user.getAddress().getCountry().getShotName(),
                                                user.getAddress().getCity(),
                                                user.getAddress().getStreet(),
                                                user.getAddress().getHomeNo(),
                                                user.getAddress().getCorpsNo().toString(),
                                                user.getAddress().getFlatNo().toString())
                                            .filter(s -> s != null && !s.isEmpty())
                                            .collect(Collectors.joining(", "));
            }
        }
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
