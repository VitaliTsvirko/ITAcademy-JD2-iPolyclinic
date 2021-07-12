package by.it_academy.jd2.controller;

import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.api.ICountryService;
import by.it_academy.jd2.service.api.IPassportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/passport")
public class PassportController {

    private final IPassportService passportService;

    private final ICountryService countryService;

    public PassportController(IPassportService passportService, ICountryService countryService) {
        this.passportService = passportService;
        this.countryService = countryService;
    }

    @GetMapping
    public String addPassportForm(Model model){
        Passport passport = new Passport();

        passport.setName("Name");
        passport.setSurname("Surname");

        model.addAttribute("userPassport", passport);
        model.addAttribute("countriesMap", countryService.getAllCountries());

        return "passport.jsp";
    }

    @PostMapping
    public String addPassport(Model model,
                              @ModelAttribute("userPassport") Passport passport,
                              @SessionAttribute("user") User user){

        passportService.addPassport(passport, user);

        System.out.println("passport post service");


        return "userprofile.jsp";
    }


}
