package otus.moryakovdv.task17.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import otus.moryakovdv.task17.model.User;
import otus.moryakovdv.task17.repository.UsersCrudRepository;
import otus.moryakovdv.task17.service.LoginFailedException;
import otus.moryakovdv.task17.service.PasswordHasher;
import otus.moryakovdv.task17.service.WebService;

/** Рест- Контроллер для пользователей */
@RestController
@Slf4j
public class UserController {

	@Autowired
	private UsersCrudRepository usersRepo;

	@Autowired
	private PasswordHasher passwordHasher;

	@Autowired
	private WebService wService;

	/**
	 * Метод аутентификации пользователя. Хранит зарегистрированных пользователей в
	 * Spring-репозитория, БД H2 in-memory
	 * 
	 * @param userName       - login пользователя,String
	 * @param passwd         - пароль пользователя,String
	 * @param createIfAbsent - флаг создания нового пользвоателя, boolean, default =
	 *                       false
	 * @return ResponseEntity<User> - json-описание созданного или залогиненного
	 *         User
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws LoginFailedException         - если юзер не найден и флаг создания не
	 *                                      поднят
	 */

	ExecutorService processContentExecutor = Executors.newFixedThreadPool(4, new ThreadFactory() {

		AtomicInteger c = new AtomicInteger();

		@Override
		public Thread newThread(Runnable arg0) {
			Thread t = new Thread(arg0, "processing-thread-" + c.incrementAndGet());
			return t;
		}
	});

	@RequestMapping(value = "/getUserAgeRPM", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeRPM(@RequestParam int id) {
		log.info("getUserAgeRPM called");
		return findUserAgeWithRPM(id);

	}

	@RequestMapping(value = "/getUserAgeRPS", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeRPS(@RequestParam int id) {
		log.info("getUserAgeRPS called");
		return findUserAgeWithRPS(id);

	}

	@RequestMapping(value = "/getUserAgeCB", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer getUserAgeCB(@RequestParam int id) {
		log.info("getUserAgeCB called");
		return findUserAgeWithCB(id);

	}
	
	
	
	
	@RateLimiter(name = "RPM", fallbackMethod = "fallBack")
	public int findUserAgeWithRPM(int id) {
		log.info("findUserAgeWithRPM called");
		return findUserAge(id);
	}
	@RateLimiter(name = "RPS", fallbackMethod = "fallBack")
	public int findUserAgeWithRPS(int id) {
		log.info("findUserAgeWithRPS called");

		return findUserAge(id);

	}
	@CircuitBreaker(name = "CBR", fallbackMethod = "fallBack")
	public int findUserAgeWithCB(int id) {
		log.info("findUserAgeWithCB called");
		return findUserAge(id);

	}
	
	
	@RequestMapping(value = "/getUserAgeTimed", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public CompletableFuture<Void> getUserAgeTimed(@RequestParam int id) {
		log.info("getUserAgeTimed called");
		return findUserAgeWithTimed(id);

	}
	
	
	
	
	//@TimeLimiter(name = "TL", fallbackMethod = "fallBack")
	public CompletableFuture<Void> findUserAgeWithTimed(int id) {
		
		/*
		try {
			Thread.currentThread().sleep(3000);
			log.info("findUserAgeWithCB called");
			return findUserAge(id);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		*/
		
		return 	testWithFuture();

	}
	
	@TimeLimiter(name = "TL", fallbackMethod = "fallBack")
	public CompletableFuture<Void> testWithFuture() {        
	    
		return CompletableFuture.runAsync(() -> {            
	        log.info("Processing request");
	        try {
				Thread.currentThread().sleep(5000);
				log.info("Request processed with delay");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }, Executors.newSingleThreadExecutor()); 
	    
	}
	
	
	public void fallBack(Exception e) throws Exception {
		
		throw new Exception("giving up processing");
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestParam String userName, @RequestParam String passwd,
			@RequestParam(defaultValue = "false") boolean createIfAbsent)
			throws NoSuchAlgorithmException, InterruptedException, ExecutionException, IOException {

		String passHash = passwordHasher.hash(passwd);

		User user = usersRepo.findByUserNameAndPassword(userName, passHash).orElseGet(() -> {

			return createUser(userName, passwd, createIfAbsent);

		});
		String uuid = UUID.randomUUID().toString();

		user.setSessionId(uuid);
		wService.processContent(uuid);
		return user;

	}

	/** Количество пользователей в репозитории */
	@RequestMapping(value = "/userCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int countAllUsers() {

		AtomicInteger c = new AtomicInteger();

		usersRepo.findAll().forEach(u -> c.incrementAndGet());

		return c.get();

	}

	/**
	 * Сохраненение пользователя в репозитории, хеширует пароль перед записью
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	private User createUser(String userName, String password, boolean createIfAbsent) throws ResponseStatusException {
		if (createIfAbsent) {

			try {
				password = passwordHasher.hash(password);
				User newUser = new User(userName, password);
				return usersRepo.save(newUser);
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Password hash exception");
			}

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

	}

	private int findUserAge(int id) {
		
		log.info("Pure findUserAge method called");
		
		User user = usersRepo.findById(id).orElseGet(() -> {
			String uuid = UUID.randomUUID().toString();
			Random rnd = new Random();

			User newUser = createUser("mockUserName", "mockPassword", true);
			
			newUser.setSessionId(uuid);
			int mockAge = rnd.ints(5, 65).findAny().getAsInt();
			newUser.setUserAge(mockAge);

			usersRepo.save(newUser);
			
			return newUser;

		});

		return user.getUserAge();

	}


	
	
	

	@ExceptionHandler(RequestNotPermitted.class)
	public ResponseEntity<String> requestNotPermittedExceptionHandler(HttpServletRequest request,
			RequestNotPermitted e) {
		log.warn("Too many requests triggered");
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
	}

	@ExceptionHandler(CallNotPermittedException.class)
	public ResponseEntity<String> callNotPermittedExceptionHandler(HttpServletRequest request,
			CallNotPermittedException e) {
		log.warn("Call not permitted by Circuit breaker");
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Call not permitted by Circuit breaker");
	}

}
