package otus.moryakovdv.task13.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Кастомное исключение - не авторизован*/
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Login failed")
public class LoginFailedException extends RuntimeException {

	
	
	private static final long serialVersionUID = 1937936107282823445L;



  

}
