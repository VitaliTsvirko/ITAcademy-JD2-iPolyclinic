package by.it_academy.jd2.controller;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Vitali Tsvirko
 */
@Controller
@RequestMapping("/userprofile")
public class UserProfileInfo {

    private final IUserService userService;

    public UserProfileInfo(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getUserProfile(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        ApplicationUserState state = user.getState();

        if (user.getState() != ApplicationUserState.ACTIVATED) {
            model.addAttribute("userActivationState", false);
        }

        return "userprofile.jsp";
    }

}
