package by.it_academy.jd2.domain;

import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name= "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey=@ForeignKey(name = "FK_address_country_id"), nullable = false)
    private Countries country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street")
    private String street;


    @Column(name ="home_no")
    private String homeNo;

    @Min(value = 0)
    @Column(name = "corps_no")
    private Integer corpsNo;

    @Min(value = 0)
    @Column(name = "flat_no")
    private Integer flatNo;

    public Long getId() {
        return id;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public Integer getCorpsNo() {
        return corpsNo;
    }

    public void setCorpsNo(Integer corpsNo) {
        this.corpsNo = corpsNo;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }


}
