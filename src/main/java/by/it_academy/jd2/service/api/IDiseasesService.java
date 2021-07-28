package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Diseases;

import java.util.List;
import java.util.Map;

public interface IDiseasesService {
    Map<String, String> getAllDiseases();
    List<Diseases> findDiseaseByName(String name);
}
