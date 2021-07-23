package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.core.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IUsersRepositoryTest {

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ICountriesRepository countriesRepository;

    @Test
    void getExistUserByLogin(){
        String existPhone = "+375295148825";

        Optional<User> repositoryById = usersRepository.findById(1L);

        repositoryById.ifPresent(user -> {
            user.seteMail("123@mail.com");
            System.out.println(user);
        });

    }

    @Test
    void deleteUserAddress(){

        User byId = usersRepository.getById(1L);

        Address address = byId.getAddress();

        address.setCorpsNo(123);

        byId.setAddress(null);
        addressRepository.delete(address);

    }


    @Test
    void addUserAddress(){
        Address address = new Address();

        Optional<Countries> blr = countriesRepository.findById("BLR");

        address.setCountry(blr.get());
        address.setCity("MInsk");

        addressRepository.save(address);


        User user = usersRepository.findById(1L).get();

        user.setAddress(address);


    }


    @Test
    void createUser(){
        String phoneNo = "+375295148825";





/*        usersRepository.findByPhoneNo(phoneNo).ifPresent(
                user -> { throw new UsernameAlreadyUsedException("User phone number " + user.getPhoneNo() + " already registered");
                }
        );*/


    }




}