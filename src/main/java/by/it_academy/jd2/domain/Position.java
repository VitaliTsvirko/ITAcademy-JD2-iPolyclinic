package by.it_academy.jd2.domain;

import javax.persistence.*;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_name")
    private String position;

}
