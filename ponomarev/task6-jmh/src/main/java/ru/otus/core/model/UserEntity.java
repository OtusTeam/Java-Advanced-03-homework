package ru.otus.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Data
@Entity
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String objectId;
    private String userName;
    private String userData;
    private String password;
    private Integer version = 0;

    public void incrementVersion() {
        Integer ver = this.version + 1;
        setVersion(ver);
    }
}
