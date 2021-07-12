package by.it_academy.jd2.controller;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.IUserService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/login")
public class Login {

    private final IUserService usersService;

    public Login(IUserService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String loginPage(){
        return "login";
    }


    @PostMapping
    public String userLogin(@RequestParam String login,
                          @RequestParam String password,
                          HttpServletRequest request,
                          Model model){

        User user = usersService.authentication(login, password);

        if (user != null){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(Math.toIntExact(TimeUnit.MINUTES.toSeconds(15)));

            return "index.jsp";
        }
        else{
            return "error";
        }
    }

}
