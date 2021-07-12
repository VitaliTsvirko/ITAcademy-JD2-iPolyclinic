package by.it_academy.jd2.domain;

import javax.persistence.*;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "medical_cards")
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
