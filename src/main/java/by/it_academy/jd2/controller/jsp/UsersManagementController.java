package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.service.api.ICountryService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/admin/users")
public class UsersManagementController {

    private final IUserService userService;

    private final ICountryService countryService;

    public UsersManagementController(IUserService userService, ICountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getUserManagementPage(Model model){
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("countriesMap", countryService.getAllCountriesOrderByShotName());

        return "users/usermanagement";
    }
}
