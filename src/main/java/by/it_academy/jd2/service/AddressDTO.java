package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vitali Tsvirko
 */
public class AddressDTO {

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street")
    private String street;

    @JsonProperty("home_no")
    private String homeNo;

    @JsonProperty("corp_no")
    private Integer corpsNo;

    @JsonProperty("flat_no")
    private Integer flatNo;

    public AddressDTO() {
    }

    public AddressDTO(Address address) {
        if (address != null){
            this.setCountryCode(address.getCountry().getCode());
            this.setCountryName(address.getCountry().getShotName());
            this.setCity(address.getCity());
            this.setStreet(address.getStreet());
            this.setHomeNo(address.getHomeNo());
            this.setCorpsNo(address.getCorpsNo());
            this.setFlatNo(address.getFlatNo());
        }
    }


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
