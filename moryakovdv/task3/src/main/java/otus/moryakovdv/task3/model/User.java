package otus.moryakovdv.task3.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**Сущность Пользователь*/
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Transient
	private String sessionId;

	/**Сессия юзера*/
	public String getSessionId() {
		return sessionId;
	}
	
	/**Сессия юзера*/
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**Дефолтный конструктор, ибо JPA*/
	public User() {}
	
	/**Конструктор по логину и паролю*/
	public User(String userName, String password) {
		this.userName=userName;
		this.password=password;
	}
	
	/**Первичный ключ, автоинкремент*/
	public long getId() {
		return id;
	}
	/**Первичный ключ, автоинкремент*/
	public void setId(long id) {
		this.id = id;
	}

	/**login*/
	public String getUserName() {
		return userName;
	}

	/**login*/
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**password*/
	public String getPassword() {
		return password;
	}
	
	/**password*/
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**Hashcode+equals на поле id*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id;
	}

	

}
