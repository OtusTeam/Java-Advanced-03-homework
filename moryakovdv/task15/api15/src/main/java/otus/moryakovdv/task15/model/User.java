package otus.moryakovdv.task15.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Сущность Пользователь*/
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@Schema(description = "Тестироваание аннотации Swagger для Entity")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	/**Сессия юзера*/
	private String sessionId;

	/**Конструктор по логину и паролю*/
	public User(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}



	
	

	

}
