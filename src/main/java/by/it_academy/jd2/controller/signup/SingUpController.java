package by.it_academy.jd2.controller.signup;

import by.it_academy.jd2.core.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping(value = "/signup")
public class SingUpController {

    private final IUserService usersService;

    public SingUpController(IUserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String userSignUp(Model model){
        return "signup";
    }


    @PostMapping
    public String registerUser(@RequestParam(name = "phoneNo") String phoneNo,
                             @RequestParam(name = "password") String password,
                             Model model){

        try{
            User user = usersService.userSignUp(phoneNo, password);
        } catch (UsernameAlreadyUsedException e){
            model.addAttribute("error", "Пользователь с таким номер телефона уже зарегестрирован");
            return "signup";
        }


        return "index";
    }





}
