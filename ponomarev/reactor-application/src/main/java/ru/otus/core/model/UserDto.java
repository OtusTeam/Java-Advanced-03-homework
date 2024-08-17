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
    private Long objectId;
    private String userName;
    private String userData;
    private String password;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto(Long objectId, String userName, String userData, String password) {
        this.objectId = objectId;
        this.userName = userName;
        this.userData = userData;
        this.password = password;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto() {
    }
}
