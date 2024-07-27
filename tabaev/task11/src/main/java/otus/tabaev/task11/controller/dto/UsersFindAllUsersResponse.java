package otus.tabaev.task11.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersFindAllUsersResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "login")
    private String login;
}
