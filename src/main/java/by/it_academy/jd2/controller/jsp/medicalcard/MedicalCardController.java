package by.it_academy.jd2.controller.jsp.medicalcard;

import by.it_academy.jd2.domain.Diseases;
import by.it_academy.jd2.service.api.IAppointmentsService;
import by.it_academy.jd2.service.api.IDiseasesService;
import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.AppointmentDTO;
import by.it_academy.jd2.service.dto.UserBasicDataDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/medicalcard")
public class MedicalCardController {

    private final IMedicalCardService medicalCardService;
    private final IAppointmentsService appointmentsService;
    private final IUserService userService;
    private final IDiseasesService diseasesService;

    public MedicalCardController(IMedicalCardService medicalCardService, IAppointmentsService appointmentsService, IUserService userService, IDiseasesService diseasesService) {
        this.medicalCardService = medicalCardService;
        this.appointmentsService = appointmentsService;
        this.userService = userService;
        this.diseasesService = diseasesService;
    }

    @GetMapping("/{id}")
    public String getMedicalCardById(@PathVariable Long id, Model model){
        model.addAttribute("appointmentsList", medicalCardService.getAllAppointments(id)
                                                    .stream()
                                                    .map(a -> new AppointmentDTO(a))
                                                    .collect(Collectors.toList()));
        model.addAttribute("patientData", new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)));

        model.addAttribute("medicalCardId", id);
        model.addAttribute("medicalCardHeight", medicalCardService.getMedicalCardById(id).getHeight());
        model.addAttribute("medicalCardWeight", medicalCardService.getMedicalCardById(id).getWeight());
        model.addAttribute("medicalCardAllergy", medicalCardService.getMedicalCardById(id).getAllergy());

        return "medicalcard";
    }


    @GetMapping("/{id}/appointment/new")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public String createAppointment(@PathVariable Long id, Model model){
        model.addAttribute("appointmentDTO",
                            new AppointmentDTO(appointmentsService.createAppointment(userService.getAuthorizedUser(), id)));

        model.addAttribute("medicalCardId", id);

        model.addAttribute("patientData", new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)));

        model.addAttribute("diseasesMap", diseasesService.getAllDiseases());

        return "appointment";
    }

}
