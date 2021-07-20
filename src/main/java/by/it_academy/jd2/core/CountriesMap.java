package by.it_academy.jd2.core;

import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.repository.ICountriesRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Component
public class CountriesMap {

    private final ICountriesRepository countriesRepository;

    public CountriesMap(ICountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public Map<String, String> getCountriesMap(){
        return countriesRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Countries::getCode, Countries::getShotName));
    }
}