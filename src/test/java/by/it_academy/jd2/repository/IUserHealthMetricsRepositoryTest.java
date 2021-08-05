package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.UserHealthMetrics;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IUserHealthMetricsRepositoryTest {

    @Autowired
    private IUserHealthMetricsRepository userHealthMetricsRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Test()
    void test(){
        List<UserHealthMetrics> all = userHealthMetricsRepository.findAll();

        System.out.println(all.size());

    }


    @Test()
    void addData(){
        UserHealthMetrics metrics = new UserHealthMetrics();

        metrics.setCreatedBy(usersRepository.findById(38L).get());
        metrics.setMedicalCard(usersRepository.findById(38L).get().getMedicalCard());
        metrics.setTimestamp(LocalDateTime.now());
        metrics.setWeight(88);
        metrics.setHeight(180);
        metrics.setDiastolicBloodPressure(100);
        metrics.setSystolicBloodPressure(140);
        metrics.setHeartRate(100);

        userHealthMetricsRepository.save(metrics);
    }


    @Test()
    void getLastByUserId(){
        UserHealthMetrics result = userHealthMetricsRepository.findFirstByCreatedByIdOrderByTimestampDesc(38L);

        System.out.println(result.getTimestamp());
    }


}