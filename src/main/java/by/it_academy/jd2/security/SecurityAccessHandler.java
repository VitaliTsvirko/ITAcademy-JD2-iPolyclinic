package by.it_academy.jd2.security;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.UserRoles;
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


    public boolean isAllowPassportConfirm(Optional<Long> userId){
        User authorizedUser = userService.getAuthorizedUser();

        boolean hasPermission = authorizedUser.getUserRole().equals(UserRoles.ADMIN) || authorizedUser.getUserRole().equals(UserRoles.DOCTOR);
        boolean isDoctor = authorizedUser.getUserRole().equals(UserRoles.DOCTOR);
        boolean personalAccount = isAuthenticationUserIdEqualsRequestId(userId);

        return hasPermission && !(isDoctor && personalAccount);
    }

    public boolean isAllowEditPassportData(Optional<Long> userId){
        User authorizedUser = userService.getAuthorizedUser();

        boolean stateActivated = authorizedUser.getState().equals(ApplicationUserState.ACTIVATED) || authorizedUser.getState().equals(ApplicationUserState.PASSPORT_DATA_VERIFIED);
        boolean hasExclusivePermission = authorizedUser.getUserRole().equals(UserRoles.ADMIN) || authorizedUser.getUserRole().equals(UserRoles.DOCTOR);

        boolean personalAccount = isAuthenticationUserIdEqualsRequestId(userId);
        boolean isDoctor = authorizedUser.getUserRole().equals(UserRoles.DOCTOR);

        return !stateActivated || (hasExclusivePermission && !(personalAccount && isDoctor));
    }
}
