package by.it_academy.jd2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vitali Tsvirko
 */
@Configuration
@ComponentScan("by.it_academy.jd2")
public class RootConfig {

/*    @Bean
    public InitStaticDBData initStaticDBData(ICountriesRepository repository){
        return new InitStaticDBData(repository, Path.of("D:\\01. Vitali\\03. Inf\\01. Java\\01. IT-Academy\\02. JD2\\03. Projects\\IPolyclinic\\src\\main\\java\\by\\it_academy\\jd2\\utils\\countries\\oksm.csv"));
    }*/

}
