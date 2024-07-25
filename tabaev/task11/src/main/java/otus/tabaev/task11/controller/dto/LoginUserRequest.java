package otus.tabaev.task11.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserRequest {

    @JsonProperty("login")
    String login;

    @JsonProperty("password")
    String password;
}
