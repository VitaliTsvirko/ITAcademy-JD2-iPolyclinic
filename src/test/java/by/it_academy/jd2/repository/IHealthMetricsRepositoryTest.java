package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.HealthMetrics;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
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
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IHealthMetricsRepositoryTest {

    @Autowired
    private IHealthMetricsRepository healthMetricsRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IMedicalCardRepository medicalCardRepository;



    @Test()
    void createMetrics(){
        User user = usersRepository.findByPhoneNo("user").get();
        MedicalCard medicalCard = user.getMedicalCard();

       /* for (int i = 0; i < 10; i++) {
            HealthMetrics metric = new HealthMetrics();
            metric.setCreatedBy(user);
            metric.setMedicalCard(medicalCard);

            metric.setTypes(UserHealthMetricsTypes.HEART_RATE);
            metric.setValue(70.0 + i * 10);
            metric.setTimestamp(LocalDateTime.now().minusDays(i));

            healthMetricsRepository.save(metric);
        }

        for (int i = 0; i < 10; i++) {
            HealthMetrics metric = new HealthMetrics();
            metric.setCreatedBy(user);
            metric.setMedicalCard(medicalCard);

            metric.setTypes(UserHealthMetricsTypes.WEIGHT);
            metric.setValue(70.0 + i);
            metric.setTimestamp(LocalDateTime.now().minusDays(i*2));

            healthMetricsRepository.save(metric);
        }
*/

        HealthMetrics metric = new HealthMetrics();
        metric.setCreatedBy(user);
        metric.setMedicalCard(medicalCard);

        metric.setTypes(HealthMetricsTypes.HEIGHT);
        metric.setValue(176.0);
        metric.setTimestamp(LocalDateTime.now().minusDays(30));

        healthMetricsRepository.save(metric);


        /*
        timestamp
         */


    }


    @Test()
    void getMetricByType(){
        List<HealthMetrics> result = healthMetricsRepository.findHealthMetricsByTypesAndAndMedicalCardIdOrderByTimestampDesc(HealthMetricsTypes.HEIGHT, 1L);

        List<HealthMetricEntityDTO> collect = result.stream().map(entity -> new HealthMetricEntityDTO(entity)).collect(Collectors.toList());



        System.out.printf("end");
    }





}