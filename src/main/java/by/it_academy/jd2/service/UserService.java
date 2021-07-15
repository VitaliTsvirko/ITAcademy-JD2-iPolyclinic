package by.it_academy.jd2.service;

import by.it_academy.jd2.core.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final IUsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(IUsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String phoneNo, String password){
        User existingUser = usersRepository.findByPhoneNo(phoneNo);

        if (existingUser != null){
            throw new UsernameAlreadyUsedException();
        }

        User user = new User();
        user.setPhoneNo(phoneNo);
        user.setPassword(passwordEncoder.encode(password));
        user.setState(ApplicationUserState.SIGNUP);
        user.setUserRole(UserRoles.USER);

        usersRepository.save(user);

        return user;
    }

    public User getUserById(Long id){
        return usersRepository.findById(id).get();
    }
}
