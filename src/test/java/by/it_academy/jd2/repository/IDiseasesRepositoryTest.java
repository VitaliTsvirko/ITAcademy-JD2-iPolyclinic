package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.Diseases;
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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IDiseasesRepositoryTest {

    @Autowired
    private IDiseasesRepository diseasesRepository;


    @Test
    void searchDiseases(){
        List<Diseases> result = diseasesRepository.findByNameContaining("Кише");

        System.out.println(result.size());

    }

}