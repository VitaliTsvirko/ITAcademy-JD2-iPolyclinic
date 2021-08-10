package by.it_academy.jd2.security;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IUsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Vitali Tsvirko
 */

@Component
public class UserAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final IUsersRepository usersRepository;

    public UserAuthenticationSuccessHandler(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            usersRepository.findByPhoneNo(authentication.getName())
                    .ifPresent( loginUser ->
                        session.setAttribute("user", loginUser)
                    );

            //redirect to request page
            SavedRequest saveRequest = new HttpSessionRequestCache().getRequest(request, response);
            if (saveRequest == null){
                super.onAuthenticationSuccess(request, response, authentication);
            } else  {
                response.sendRedirect(saveRequest.getRedirectUrl());
            }
        }
    }
}
