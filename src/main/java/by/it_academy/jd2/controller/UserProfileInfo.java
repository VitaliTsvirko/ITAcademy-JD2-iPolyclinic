package by.it_academy.jd2.controller;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.service.api.ICountryService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/userprofile")
public class UserProfileInfo {

    private final IUserService userService;
    private final ICountryService countryService;

    public UserProfileInfo(IUserService userService, ICountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }


    @GetMapping()
    public String getUserProfile(Model model,
                                 @SessionAttribute("user") User user){

        User authUser = userService.getAuthorizedUser();

        ApplicationUserState state = authUser.getState();

        if (authUser.getState() != ApplicationUserState.ACTIVATED) {
            model.addAttribute("userActivationState", false);
        }

        model.addAttribute("countriesMap", countryService.getAllCountriesOrderByShotName());
        model.addAttribute("user", authUser);

        return "userprofile";
    }

}
