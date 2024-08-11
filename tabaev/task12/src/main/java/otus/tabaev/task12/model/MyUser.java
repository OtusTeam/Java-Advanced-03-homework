package otus.tabaev.task12.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "my_user")
public class MyUser {

    @Id
    @Column(value = "id")
    private Long id;

    @Column(value = "login")
    private String login;

    @Column(value = "password")
    private String password;

    public MyUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
