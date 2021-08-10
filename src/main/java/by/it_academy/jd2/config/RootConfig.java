package by.it_academy.jd2.config;

import by.it_academy.jd2.core.healthmetrics.HealthMetricsCalculator;
import by.it_academy.jd2.core.healthmetrics.HealthMetricsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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


/*    @Bean
    public InitStaticDBData initStaticDBData(ICountriesRepository repository, IDiseasesRepository diseasesRepository){
        return new InitStaticDBData(repository, diseasesRepository,
                Path.of("d:\\01. Vitali\\03. Inf\\01. Java\\01. IT-Academy\\02. JD2\\03. Projects\\IPolyclinic\\src\\main\\java\\by\\it_academy\\jd2\\utils\\countries\\oksm.csv"),
                Path.of("d:\\01. Vitali\\03. Inf\\01. Java\\01. IT-Academy\\02. JD2\\03. Projects\\IPolyclinic\\src\\main\\java\\by\\it_academy\\jd2\\utils\\mkb10.csv"));
    }*/


    @Bean
    public HealthMetricsHandler healthMetricsHandler(){
        return new HealthMetricsHandler(new HealthMetricsCalculator());
    }


}
