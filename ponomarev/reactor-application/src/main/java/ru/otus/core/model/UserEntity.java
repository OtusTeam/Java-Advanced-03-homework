package ru.otus.core.model;

import jakarta.persistence.Column;
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
    @Column(name = "object_id")
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String objectId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_data")
    private String userData;
    @Column(name = "password")
    private String password;
}
