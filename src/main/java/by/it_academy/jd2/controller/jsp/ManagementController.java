package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.service.api.IUsersMetricsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/management")
public class ManagementController {

    private final IUsersMetricsService usersMetricsService;

    public ManagementController(IUsersMetricsService usersMetricsService) {
        this.usersMetricsService = usersMetricsService;
    }

    @GetMapping
    public String getPage(Model model){
        model.addAttribute("usersMetrics", usersMetricsService.getUserMetrics());

        return "management";
    }
}
