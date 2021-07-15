package by.it_academy.jd2.controller.login.ouath2.github.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vitali Tsvirko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUserInfoDTO {

    @JsonProperty("login")
    private String login;

    @JsonProperty("id")
    private String id;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("name")
    private String name;

}
