package otus.tabaev.task11.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

}
