package by.it_academy.jd2.service;

import by.it_academy.jd2.core.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.repository.IUsersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class UserService implements IUserService{

    private final IUsersRepository usersRepository;

    public UserService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User userSignUp(String phoneNo, String password){
        User existingUser = usersRepository.findByPhoneNo(phoneNo);

        if (existingUser != null){
            throw new UsernameAlreadyUsedException();
        }

        User user = new User();
        user.setPhoneNo(phoneNo);
        user.setPassword(password);
        user.setState(ApplicationUserState.SIGNUP);

        usersRepository.save(user);

        return user;
    }

    @Override
    public User authentication(String login, String password) {
        return usersRepository.findByPhoneNo(login);
    }
}
