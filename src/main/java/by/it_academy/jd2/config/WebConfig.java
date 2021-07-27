package by.it_academy.jd2.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
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
                .setCachePeriod(0);
                //.setCachePeriod(Math.toIntExct(TimeUnit.HOURS.toSeconds(12)));
    }


    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }


    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter(ObjectMapper mapper){
        return new MappingJackson2HttpMessageConverter(mapper);
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return converter;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/medicalcard").setViewName("medicalcard");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}
