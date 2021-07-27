package by.it_academy.jd2.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vitali Tsvirko
 */
public class ApiErrorDTO {
    @JsonProperty("message")
    private String message;

    public ApiErrorDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
