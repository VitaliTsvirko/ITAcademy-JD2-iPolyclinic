package by.it_academy.jd2.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name = "countries")
public class Countries implements Serializable {

    @Id
    @Column(name="code", length=3, columnDefinition="bpchar")
    private String code;

    @Column(name = "short_name")
    private String shotName;

    @Column(name = "full_name")
    private String fullName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShotName() {
        return shotName;
    }

    public void setShotName(String shotName) {
        this.shotName = shotName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
