package ru.otus.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Data
@Getter
@Setter
public class UserDto {
    private String userName;
    private String userData;
    private String password;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto(String userName, String userData) {
        this.userName = userName;
        this.userData = userData;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto() {
    }
}
