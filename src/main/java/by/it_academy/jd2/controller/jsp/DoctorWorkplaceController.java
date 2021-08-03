package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.api.ICountryService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/work")
public class DoctorWorkplaceController {

    private final IUserService userService;

    public DoctorWorkplaceController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public String getPatientManagementPage(Model model){
        List<User> allPatients = userService.getAllPatients();

        Map<Long, Integer> appointmentsCounts = allPatients.stream()
                                                    .collect(Collectors.
                                                            toMap(usr -> usr.getId(), usr -> usr.getMedicalCard().getAppointments().size()));
        model.addAttribute("usersList", allPatients);
        model.addAttribute("appointmentsCounts", appointmentsCounts);

        return "users/userslist";
    }
}
