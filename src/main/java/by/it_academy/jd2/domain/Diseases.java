package by.it_academy.jd2.domain;

import javax.persistence.*;

/**
 * Created by Vitali Tsvirko
 */
@Entity
@Table(name ="diseases")
public class Diseases {

    @Id
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
