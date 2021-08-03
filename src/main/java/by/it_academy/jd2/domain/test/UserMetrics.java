package by.it_academy.jd2.domain.test;

import by.it_academy.jd2.domain.User;
import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name="user_metrics", schema = "test")
public class UserMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "height")
    @Min(0)
    private Integer height;

    @Column(name = "weight")
    @Min(0)
    private Integer weight;

    @Column(name = "created_time_stamp", nullable = false)
    private LocalDateTime createdTimeStamp;

    @ManyToOne
    @JoinColumn(name="created_by_id", nullable=false)
    private AppUser createdBy;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Card card;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
        this.createdTimeStamp = createdTimeStamp;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
