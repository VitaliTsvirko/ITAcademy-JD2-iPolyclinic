package by.it_academy.jd2.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public String getLoginPage(){
        return "login";
    }

}
