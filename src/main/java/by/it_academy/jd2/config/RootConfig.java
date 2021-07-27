package by.it_academy.jd2.config;

import by.it_academy.jd2.repository.ICountriesRepository;
import by.it_academy.jd2.repository.IDiseasesRepository;
import by.it_academy.jd2.utils.countries.InitStaticDBData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

/**
 * Created by Vitali Tsvirko
 */
@Configuration
@ComponentScan("by.it_academy.jd2")
public class RootConfig {

/*    @Bean
    public InitStaticDBData initStaticDBData(ICountriesRepository repository, IDiseasesRepository diseasesRepository){
        return new InitStaticDBData(repository, diseasesRepository,
                Path.of("C:\\Users\\admin\\IdeaProjects\\ITAcademy-JD2-iPolyclinic\\src\\main\\java\\by\\it_academy\\jd2\\utils\\countries\\oksm.csv"),
                Path.of("C:\\Users\\admin\\IdeaProjects\\ITAcademy-JD2-iPolyclinic\\src\\main\\java\\by\\it_academy\\jd2\\utils\\mkb10.csv"));
    }*/

}
