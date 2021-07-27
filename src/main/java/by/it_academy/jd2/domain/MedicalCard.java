package by.it_academy.jd2.domain;

import javax.persistence.*;
import java.util.Collection;

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

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private Collection<Appointments> appointments;

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

    public Collection<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(Collection<Appointments> appointments) {
        this.appointments = appointments;
    }
}
