package by.it_academy.jd2.utils.countries;

import by.it_academy.jd2.config.RootConfig;
import by.it_academy.jd2.domain.Countries;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Vitali Tsvirko
 */
public class InitStaticDBDataMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);

        InitStaticDBData initStaticDBData = context.getBean("initStaticDBData", InitStaticDBData.class);

        List<Countries> countries = initStaticDBData.readCountriesDataFromFile();

        initStaticDBData.writeCountriesDataToDb(countries);

    }
}
