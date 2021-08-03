package by.it_academy.jd2.domain;

import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "medical_cards")
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "medicalCard")
    private User user;

    @OneToMany(mappedBy = "medicalCard", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

    @Column(name = "height")
    @Min(0)
    private Integer height;

    @Column(name = "weight")
    @Min(0)
    private Integer weight;

    @Column(name = "allergy")
    private String allergy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment){
        this.appointments.add(appointment);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }
}
