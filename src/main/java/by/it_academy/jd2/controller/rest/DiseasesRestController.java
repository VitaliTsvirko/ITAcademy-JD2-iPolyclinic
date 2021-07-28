package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.service.api.IDiseasesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
public class DiseasesRestController {

    private IDiseasesService diseasesService;

    public DiseasesRestController(IDiseasesService diseasesService) {
        this.diseasesService = diseasesService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Diseases>> findDiseaseByName(@RequestParam String param){
        return new ResponseEntity<>(diseasesService.findDiseaseByName(param), HttpStatus.OK);
    }

}
