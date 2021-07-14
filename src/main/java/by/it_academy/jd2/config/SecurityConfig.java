package by.it_academy.jd2.config;

import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.security.UserAuthenticationSuccessHandler;
import by.it_academy.jd2.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private IUsersRepository usersRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic()
            .and().formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler(userAuthenticationSuccessHandler(usersRepository))
            .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
            .and().authorizeRequests()
                .antMatchers("/userprofile/**").authenticated()
                .antMatchers("/passport/**").authenticated();
    }



    @Bean
    UserAuthenticationSuccessHandler userAuthenticationSuccessHandler(IUsersRepository usersRepository){
        return new UserAuthenticationSuccessHandler(usersRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        authenticationProvider.setUserDetailsService(userSecurityService);

        return authenticationProvider;
    }

}