package by.it_academy.jd2.security;

import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.IUsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Service
public class UserSecurityService implements UserDetailsService {

    public final IUsersRepository usersRepository;

    public UserSecurityService(IUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User existingUser = usersRepository.findByPhoneNo(login).orElseThrow(
                () -> new UsernameNotFoundException("User with phone number" + login + "was not found")
        );

        List<UserRoles> userRoles = new ArrayList<>();
        userRoles.add(existingUser.getUserRole());

        return new org.springframework.security.core.userdetails.User(existingUser.getPhoneNo(),
                                                                    existingUser.getPassword(),
                                                                    mapRolesAuthorities(userRoles));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<UserRoles> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.name())).collect(Collectors.toList());
    }
}
