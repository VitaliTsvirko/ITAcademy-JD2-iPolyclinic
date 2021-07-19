package by.it_academy.jd2.service;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.config.RootConfig;
import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IAddressRepository;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.service.api.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class UserServiceTest {

    @Autowired
    private IUsersRepository userRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Test
    void createAddress() {
    }

    @Test
    void deleteAddress() {
        User user = userRepository.getById(1L);

        Address address = user.getAddress();
/*        user.setAddress(null);
        userRepository.saveAndFlush(user);*/

        addressRepository.delete(address);



    }

    @Test
    void updateAddress() {
    }
}