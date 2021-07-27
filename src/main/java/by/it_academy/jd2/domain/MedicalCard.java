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


}
