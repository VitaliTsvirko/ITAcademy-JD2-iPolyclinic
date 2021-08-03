package by.it_academy.jd2.domain.test;

import javax.persistence.*;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "app_users", schema = "test")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_card_id", foreignKey=@ForeignKey(name = "FK_patient_medical_card_id"))
    private Card card;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
