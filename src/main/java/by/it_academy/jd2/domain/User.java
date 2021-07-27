package by.it_academy.jd2.domain;

import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.service.AddressDTO;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(name = "phone_no", nullable = false, unique = true)
    private String phoneNo;

    @Email
    @Column(name = "email", unique = true)
    private String eMail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id", foreignKey=@ForeignKey(name = "FK_user_passport_id"))
    private Passport passport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey=@ForeignKey(name = "FK_user_address_id"))
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    private ApplicationUserState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    private HealthStatus healthStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_card_id", foreignKey=@ForeignKey(name = "FK_user_medical_card_id"))
    private MedicalCard medicalCard;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRoles userRole;

    @Column(name = "oauth2_github_id")
    private String githubId;

    @Column(name = "oauth2_google_id")
    private String googleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ApplicationUserState getState() {
        return state;
    }

    public void setState(ApplicationUserState state) {
        this.state = state;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }


    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(password, user.password) && Objects.equals(phoneNo, user.phoneNo) && Objects.equals(eMail, user.eMail) && Objects.equals(passport, user.passport) && Objects.equals(address, user.address) && state == user.state && healthStatus == user.healthStatus && Objects.equals(medicalCard, user.medicalCard) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, phoneNo, eMail, passport, address, state, healthStatus, medicalCard, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", eMail='" + eMail + '\'' +
                ", passport=" + passport +
                ", address=" + address +
                ", state=" + state +
                ", healthStatus=" + healthStatus +
                ", medicalCard=" + medicalCard +
                ", userRole=" + userRole +
                '}';
    }
}
