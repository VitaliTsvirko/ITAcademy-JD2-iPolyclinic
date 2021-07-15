package by.it_academy.jd2.config;

import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.security.UserAuthenticationSuccessHandler;
import by.it_academy.jd2.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private Environment env;

    public static final String CLIENT_PROPERTY_KEY = "spring.security.oauth2.client.registration.";
    private static List<CommonOAuth2Provider> clients = Arrays.asList(
            CommonOAuth2Provider.GITHUB
    );

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
            .and().oauth2Login()
                .loginPage("/login")
            .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
            .and().authorizeRequests()
                .antMatchers("/userprofile/**").authenticated()
                .antMatchers("/passport/**").authenticated()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/signup").permitAll();
    }


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(Environment env) {
        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c, env))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }


    private ClientRegistration getRegistration(CommonOAuth2Provider client, Environment env) {

        String clientName = client.name().toLowerCase();

        String clientId = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-id");

        if (clientId == null) {
            return null;
        }

        String clientSecret = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".client-secret");

        if (clientSecret == null) {
            return null;
        }

        String callbackUrl = env.getProperty(CLIENT_PROPERTY_KEY + clientName + ".callback-url");

        if (callbackUrl == null) {
            return null;
        }

        return client.getBuilder(clientSecret)
                .redirectUri(callbackUrl)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientId(clientId).clientSecret(clientSecret).build();
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