package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/userhealth")
public class UserHealthController {

    private final IUserService userService;

    public UserHealthController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getPage(Model model){
        User user = userService.getAuthorizedUser();

        model.addAttribute("userId", user.getId());
        model.addAttribute("appointmentTotal", user.getMedicalCard().getAppointments().size());

        return "userhealth";
    }
}
