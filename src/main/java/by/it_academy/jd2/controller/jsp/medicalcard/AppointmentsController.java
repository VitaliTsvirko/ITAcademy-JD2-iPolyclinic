package by.it_academy.jd2.controller.jsp.medicalcard;

import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.service.api.IAppointmentsService;
import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.dto.AppointmentDTO;
import by.it_academy.jd2.service.dto.UserBasicDataDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointment")
public class AppointmentsController {

    private final IMedicalCardService medicalCardService;
    private final IAppointmentsService appointmentsService;

    public AppointmentsController(IMedicalCardService medicalCardService, IAppointmentsService appointmentsService) {
        this.medicalCardService = medicalCardService;
        this.appointmentsService = appointmentsService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public String getAppointmentPageById(@PathVariable Long id, Model model){
        model.addAttribute("appointmentDTO", new AppointmentDTO(appointmentsService.readAppointmentById(id)));

        MedicalCard medicalCard = appointmentsService.readAppointmentById(id).getMedicalCard();

        model.addAttribute("medicalCardId", medicalCard.getId());
        model.addAttribute("patientData", new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(medicalCard.getId())));
        model.addAttribute("userId", medicalCard.getUser().getId());

        return "medicalcard/appointment";
    }
}
