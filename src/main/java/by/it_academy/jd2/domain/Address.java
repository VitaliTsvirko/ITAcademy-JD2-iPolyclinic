package by.it_academy.jd2.domain;

import jakarta.validation.constraints.Min;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

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
    @JoinColumn(name = "country_id", foreignKey=@ForeignKey(name = "FK_address_country_id"))
    private Countries country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street")
    private String street;

    @Min(value = 0)
    @Column(name ="home_no")
    private Integer homeNo;

    @Min(value = 0)
    @Column(name = "corps_no")
    private Integer corpsNo;

    @Min(value = 0)
    @Column(name = "flat_no")
    private Integer flatNo;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private Collection<User> tenants;


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

    public Integer getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(Integer homeNo) {
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

    public Collection<User> getTenants() {
        return tenants;
    }

    public void setTenants(Collection<User> tenants) {
        this.tenants = tenants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(homeNo, address.homeNo) && Objects.equals(corpsNo, address.corpsNo) && Objects.equals(flatNo, address.flatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, homeNo, corpsNo, flatNo);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country=" + country +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNo=" + homeNo +
                ", corpsNo=" + corpsNo +
                ", flatNo=" + flatNo +
                ", tenants=" + tenants +
                '}';
    }
}
