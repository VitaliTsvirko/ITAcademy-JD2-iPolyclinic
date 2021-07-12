package by.it_academy.jd2.service;

import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IPassportsRepository;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.service.api.IPassportService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class PassportService implements IPassportService {

    private final IPassportsRepository passportsRepository;

    private final IUsersRepository usersRepository;

    public PassportService(IPassportsRepository passportsRepository, IUsersRepository usersRepository) {
        this.passportsRepository = passportsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Passport addPassport(Passport passport, User user) {

        User repositoryUser = usersRepository.findById(user.getId()).get();

        repositoryUser.setPassport(passport);
        passportsRepository.save(passport);
        usersRepository.save(user);

        return passport;
    }
}
