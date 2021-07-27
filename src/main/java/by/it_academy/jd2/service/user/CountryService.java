package by.it_academy.jd2.service.user;

import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.repository.ICountriesRepository;
import by.it_academy.jd2.service.api.ICountryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Service
public class CountryService implements ICountryService {

    private final ICountriesRepository countriesRepository;

    public CountryService(ICountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Map<String, String> getAllCountriesOrderByShotName() {
        return countriesRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Countries::getCode, Countries::getShotName));
    }
}
