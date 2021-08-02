package by.it_academy.jd2.controller.jsp;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.service.api.ICountryService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

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

    /**
     * Данный метод возвращет страницу с данными пользователя.
     * Если id пользователя не передан, беруться данные текущего зарегестрированного пользователя
     *
     * @param id id пользователя.
     * @param model
     * @return страница с данными пользователя
     */
    @GetMapping({"", "/{id}"})
    public String getUserProfilePage(@PathVariable(required = false) Optional<Long> id, Model model){

        User user = id.map(userId -> userService.getUserById(userId))
                        .orElse(userService.getAuthorizedUser());

        if (user.getState() != ApplicationUserState.PASSPORT_DATA_VERIFIED) {
            model.addAttribute("userActivationState", false);
        }

        model.addAttribute("countriesMap", countryService.getAllCountriesOrderByShotName());
        model.addAttribute("user", user);

        return "userprofile";
    }

}
