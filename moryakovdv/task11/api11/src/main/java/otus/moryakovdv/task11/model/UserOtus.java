package otus.moryakovdv.task11.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**Сущность Пользователь*/

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
public class UserOtus {

	@Id
	private long id;
	
	private String userName;
	
	private String password;
	
	/**Сессия юзера*/
	private String sessionId;

	/**Конструктор по логину и паролю*/
	public UserOtus(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}



	
	

	

}
