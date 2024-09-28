package otus.moryakovdv.task11.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import otus.moryakovdv.task11.model.UserOtus;
import reactor.core.publisher.Flux;

/**Spring JPA репозиторий для работы с сущностью USER на Reactive*/
@Repository
public interface FluxUserRepository extends R2dbcRepository<UserOtus, Integer> {

	
	@Query("SELECT * FROM USER_OTUS u WHERE u.USER_NAME=:userName AND u.PASSWORD = :password")
	Flux<UserOtus> findByUserNameAndPassword(String userName, String password);
	
	/*
	@Query(value = "SELECT u.userName FROM USERS u")
	Flux<User> getAllLogins();
	*/
	
	//@Query("{ 'userName': ?0, 'password': ?1}")
	//Mono<User> findByUserNameAndPassword(String userName, String password);
	
}
