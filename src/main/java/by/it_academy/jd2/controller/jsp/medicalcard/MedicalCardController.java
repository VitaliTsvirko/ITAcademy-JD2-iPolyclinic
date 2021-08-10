package by.it_academy.jd2.controller.jsp.medicalcard;

import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestUserMedicalId(#id)")
    public String getMedicalCardById(@PathVariable Long id, Model model){
        try{
            model.addAttribute("appointmentsList", medicalCardService.getAllAppointments(id)
                    .stream()
                    .map(a -> new AppointmentDTO(a))
                    .collect(Collectors.toList()));
            model.addAttribute("patientData", new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)));
            model.addAttribute("authUser", new UserBasicDataDTO(userService.getAuthorizedUser()));
            model.addAttribute("medicalCardId", id);
            model.addAttribute("medicalCardAllergy", medicalCardService.getMedicalCardById(id).getAllergy());

            return "medicalcard/medicalcard";
        } catch (MedicalCardNotFoundException e) {
            return "error";
        }
    }


    @GetMapping("/{id}/appointment/new")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public String createAppointment(@PathVariable Long id, Model model){
        model.addAttribute("appointmentDTO",
                            new AppointmentDTO(appointmentsService.createAppointment(userService.getAuthorizedUser(), id)));

        model.addAttribute("medicalCardId", id);
        model.addAttribute("userId", medicalCardService.getMedicalCardById(id).getUser().getId());
        model.addAttribute("medicalCardAllergy", medicalCardService.getMedicalCardById(id).getAllergy());
        model.addAttribute("patientData", new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)));
        model.addAttribute("diseasesMap", diseasesService.getAllDiseases());

        return "medicalcard/appointment";
    }

}
