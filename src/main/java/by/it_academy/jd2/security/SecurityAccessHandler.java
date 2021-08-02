package by.it_academy.jd2.security;

import by.it_academy.jd2.service.api.IUserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("securityAccessHandler")
public class SecurityAccessHandler {

    private final IUserService userService;

    public SecurityAccessHandler(IUserService userService) {
        this.userService = userService;
    }


    public boolean isAuthenticationUserIdEqualsRequestId(Optional<Long> userId){
        return userId.map(id -> {
            Long requestUserId = userService.getUserById(id).getId();
            Long authorizedUserId = userService.getAuthorizedUser().getId();

            return requestUserId.equals(authorizedUserId);
            })
            .orElse(true);
    }
}
