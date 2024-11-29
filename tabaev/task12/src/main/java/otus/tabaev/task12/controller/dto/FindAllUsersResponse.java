package otus.tabaev.task12.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindAllUsersResponse {

    @JsonProperty(value = "users")
    private List<UsersFindAllUsersResponse> users;
}
