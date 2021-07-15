package by.it_academy.jd2.domain.enumeration;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Vitali Tsvirko
 */
public enum UserRoles{ //implements GrantedAuthority {
    ROOT,
    ADMIN,
    USER,
    DOCTOR,
    ANONYMOUS
}
