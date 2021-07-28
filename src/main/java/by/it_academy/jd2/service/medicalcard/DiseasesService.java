package by.it_academy.jd2.service.medicalcard;

import by.it_academy.jd2.core.exceptions.DiseasesNotFoundException;
import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IDiseasesRepository;
import by.it_academy.jd2.service.api.IDiseasesService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiseasesService implements IDiseasesService {

    private final IDiseasesRepository diseasesRepository;

    public DiseasesService(IDiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }

    @Override
    public Map<String, String> getAllDiseases(){
        return diseasesRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Diseases::getCode, Diseases::getName));

    }

    public Diseases getDiseasesById(String code){
        return diseasesRepository.findById(code).orElseThrow(
                () -> new DiseasesNotFoundException("Diseases with code " + code + "was not found")
        );
    }

    @Override
    public List<Diseases> findDiseaseByName(String name){
        return diseasesRepository.findByNameContaining(name);
    }

}
