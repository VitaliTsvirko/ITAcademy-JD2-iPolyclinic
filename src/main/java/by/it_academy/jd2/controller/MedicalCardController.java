package by.it_academy.jd2.controller;

import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.dto.AppointmentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/medicalcard")
public class MedicalCardController {

    private final IMedicalCardService medicalCardService;

    public MedicalCardController(IMedicalCardService medicalCardService) {
        this.medicalCardService = medicalCardService;
    }

    @GetMapping("/{id}")
    public String getMedicalCardById(@PathVariable Long id, Model model){
        model.addAttribute("appointmentsList", medicalCardService.getAllAppointments(id)
                                                    .stream()
                                                    .map(a -> new AppointmentDTO(a))
                                                    .collect(Collectors.toList()));
        return "medicalcard";
    }

}
