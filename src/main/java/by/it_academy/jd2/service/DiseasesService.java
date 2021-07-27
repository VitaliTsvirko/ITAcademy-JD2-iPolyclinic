package by.it_academy.jd2.service;

import by.it_academy.jd2.core.exceptions.DiseasesNotFoundException;
import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IDiseasesRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiseasesService {

    private final IDiseasesRepository diseasesRepository;

    public DiseasesService(IDiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }

    public List<Diseases> getAllDiseases(){
        return diseasesRepository.findAll();
    }

    public Diseases getDiseasesById(String code){
        return diseasesRepository.findById(code).orElseThrow(
                () -> new DiseasesNotFoundException("Diseases with code " + code + "was not found")
        );
    }
}
