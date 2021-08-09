package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.HealthMetricService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/userhealth")
public class UserHealthController {

    private final IUserService userService;

    private final HealthMetricService healthMetricService;

    public UserHealthController(IUserService userService, HealthMetricService healthMetricService) {
        this.userService = userService;
        this.healthMetricService = healthMetricService;
    }


    @GetMapping
    public String getPage(Model model){
        User user = userService.getAuthorizedUser();

        model.addAttribute("userId", user.getId());
        model.addAttribute("appointmentTotal", user.getMedicalCard().getAppointments().size());
        model.addAttribute("userAge", user.getPassport() != null ? Period.between(user.getPassport().getDateOfBirth(), LocalDate.now()).getYears() : "---");

        return "userhealth";
    }


    @GetMapping("/{userId}/healthmetrics/{type}")
    public String getMetricInfoPage(Model model, @PathVariable Long userId, @PathVariable String type){
        User user = userService.getAuthorizedUser();

        model.addAttribute("userId", user.getId());
        model.addAttribute("metricType", HealthMetricsTypes.valueOf(type.toUpperCase(Locale.ROOT)));
        model.addAttribute("metricDescription", "Индекс массы тееа (англ. body mass index (BMI), ИМТ) — величина, позволяющая оценить степень соответствия массы человека и его роста и тем самым косвенно судить о том, является ли масса недостаточной, нормальной или избыточной.");

        return "metricinfo";
    }
}
