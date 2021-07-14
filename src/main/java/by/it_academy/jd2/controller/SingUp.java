package by.it_academy.jd2.controller;

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
public class SingUp {

    private final IUserService usersService;

    public SingUp(IUserService usersService) {
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
            return "error";
        }


        return "index";
    }





}
