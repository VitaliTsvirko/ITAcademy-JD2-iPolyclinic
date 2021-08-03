package by.it_academy.jd2.domain.test;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "cards", schema = "test")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "card")
    private AppUser patient;

    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER)
    private Collection<UserMetrics> metrics;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public Collection<UserMetrics> getMetrics() {
        return metrics;
    }

    public void setMetrics(Collection<UserMetrics> metrics) {
        this.metrics = metrics;
    }
}
