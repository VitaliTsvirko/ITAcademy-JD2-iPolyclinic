package by.it_academy.jd2.controller.login.ouath2.github;

import by.it_academy.jd2.config.SecurityConfig;
import by.it_academy.jd2.controller.login.ouath2.github.dto.AccessTokenDTO;
import by.it_academy.jd2.controller.login.ouath2.github.dto.GitHubUserInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * Created by Vitali Tsvirko
 * https://www.baeldung.com/spring-security-5-oauth2-login
 */

@Controller
@RequestMapping("/login/oauth2/github/callback")
public class GitHubLoginController {

    private final Environment env;

    private final ObjectMapper objectMapper;

    private static final String USER_INFO_ENDPOINT_URL = "https://api.github.com/user";

    public GitHubLoginController(Environment env, ObjectMapper objectMapper) {
        this.env = env;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String clientId = env.getProperty(SecurityConfig.CLIENT_PROPERTY_KEY + "github.client-id");
        String clientSecret = env.getProperty(SecurityConfig.CLIENT_PROPERTY_KEY + "github.client-secret");
        String url = String.format("https://github.com/login/oauth/access_token?client_id=%s&client_secret=%s&code=%s",
                                    clientId, clientSecret, code);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

        try {
            AccessTokenDTO accessToken = objectMapper.readValue(response.getBody(), AccessTokenDTO.class);

            GitHubUserInfoDTO userInfo = getUserInfo(accessToken);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:/userprofile";
    }



    private GitHubUserInfoDTO getUserInfo(AccessTokenDTO accessToken) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.AUTHORIZATION,
                accessToken.getTokenType() + " " + accessToken.getAccessToken());
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

        ResponseEntity<String> response = restTemplate.exchange(USER_INFO_ENDPOINT_URL, HttpMethod.GET, httpEntity, String.class);

        return objectMapper.readValue(response.getBody(), GitHubUserInfoDTO.class);
    }



}
