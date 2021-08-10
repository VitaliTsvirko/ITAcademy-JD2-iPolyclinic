package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.HealthMetricService;
import by.it_academy.jd2.service.api.IHealthMetricService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final IHealthMetricService healthMetricService;

    public UserHealthController(IUserService userService, IHealthMetricService healthMetricService) {
        this.userService = userService;
        this.healthMetricService = healthMetricService;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestId(#id)")
    public String getPage(Model model){
        User user = userService.getAuthorizedUser();

        model.addAttribute("userId", user.getId());
        model.addAttribute("appointmentTotal", user.getMedicalCard().getAppointments().size());
        model.addAttribute("userAge", user.getPassport() != null ? Period.between(user.getPassport().getDateOfBirth(), LocalDate.now()).getYears() : "---");

        return "userhealth";
    }


    @GetMapping("/{userId}/healthmetrics/{type}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestId(#userId)")
    public String getMetricInfoPage(Model model, @PathVariable Long userId, @PathVariable String type){
        User user = userService.getUserById(userId);

        model.addAttribute("userId", user.getId());
        model.addAttribute("metricType", HealthMetricsTypes.valueOf(type.toUpperCase(Locale.ROOT)));
        model.addAttribute("metricDescription", getMetricDescription(HealthMetricsTypes.valueOf(type.toUpperCase(Locale.ROOT))));

        return "metricinfo";
    }


    private String getMetricDescription(HealthMetricsTypes type){
        if (type.equals(HealthMetricsTypes.BMI)){
            return "Индекс массы тела (англ. body mass index (BMI), ИМТ) — величина, позволяющая оценить степень соответствия массы человека и его роста и тем самым косвенно судить о том, является ли масса недостаточной, нормальной или избыточной.";
        }

        if (type.equals(HealthMetricsTypes.HEART_RATE)){
            return "Пульс (от лат. pulsus — удар, толчок) — толчкообразные колебания стенок артерий, связанные с сердечными циклами. В более широком смысле под пульсом понимают любые изменения в сосудистой системе, связанные с деятельностью сердца, поэтому в клинике различают артериальный, венозный и капиллярный пульс. Является одним из основных и старейших биомаркеров.";
        }

        if (type.equals(HealthMetricsTypes.BODY_TEMPERATURE)) {
            return "Температура тела — комплексный показатель теплового состояния организма животных, включая человека. Является одним из основных и старейших биомаркеров.";
        }

        if (type.equals(HealthMetricsTypes.PHYS_LEVEL)){
            return "Индекс физического состояния";
        }

        return "Описание будет добавлено позже";
    }


}
