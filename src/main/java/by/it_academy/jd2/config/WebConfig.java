package by.it_academy.jd2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali Tsvirko
 */
@Configuration
@ComponentScan(value = "by.it_academy.jd2.controller")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    /**
     * Настройка ResourceHandlers для обслуживания статических ресурсов, таких как CSS / Javascript
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/")
                .setCachePeriod(Math.toIntExact(TimeUnit.HOURS.toSeconds(12)));
    }
}
