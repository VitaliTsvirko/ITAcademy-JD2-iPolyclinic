package by.it_academy.jd2.repository.test;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.test.AppUser;
import by.it_academy.jd2.domain.test.Card;
import by.it_academy.jd2.domain.test.UserMetrics;
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

import java.time.LocalDateTime;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IUsermetricsRepositoryTest {

    @Autowired
    private IUsermetricsRepository usermetricsRepository;

    @Autowired
    private IAppUserRepository appUserRepository;

    @Autowired
    private ICardRepository cardRepository;


    @Test
    void initData(){
        AppUser doctor = new AppUser();

        appUserRepository.save(doctor);

        AppUser patient = new AppUser();
        Card card = new Card();
        card.setPatient(patient);
        cardRepository.save(card);

        patient.setCard(card);
        appUserRepository.save(patient);
    }


    @Test
    void addMetrics(){
        UserMetrics metrics = new UserMetrics();

        metrics.setCard(cardRepository.findById(1L).get());
        metrics.setCreatedBy(appUserRepository.findById(2L).get());
        metrics.setCreatedTimeStamp(LocalDateTime.now());
        metrics.setWeight(101);
        metrics.setHeight(51);

        usermetricsRepository.save(metrics);
    }

    @Test
    void getMetrics(){
        Card card = cardRepository.findById(1L).get();

        Collection<UserMetrics> metrics = card.getMetrics();

        System.out.println(metrics.size());

    }

}