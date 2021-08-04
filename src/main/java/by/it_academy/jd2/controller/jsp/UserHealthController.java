package by.it_academy.jd2.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/userhealth")
public class UserHealthController {


    @GetMapping
    public String getPage(){

        return "userhealth";
    }
}
