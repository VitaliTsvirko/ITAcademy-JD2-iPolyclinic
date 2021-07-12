package by.it_academy.jd2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/logout")
public class Logout {

    @GetMapping
    public String userLogout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        session.invalidate();

        return "index.jsp";
    }

}
