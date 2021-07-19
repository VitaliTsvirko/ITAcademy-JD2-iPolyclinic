package by.it_academy.jd2.core;

import by.it_academy.jd2.domain.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
@Component
public class AddressHolder {

    private List<Address> data = new ArrayList<>();

    public AddressHolder() {
        Address address1 = new Address();
        address1.setCity("Minsk");
        address1.setStreet("Goretskogo");
        address1.setHomeNo("1");
        address1.setFlatNo(29);

        data.add(address1);
    }

    public void addAddress(Address address){
        this.data.add(address);
    }


    public List<Address> getData() {
        return data;
    }

    public void setData(List<Address> data) {
        this.data = data;
    }
}
