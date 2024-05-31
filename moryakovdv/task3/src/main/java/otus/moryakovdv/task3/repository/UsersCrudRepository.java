package otus.moryakovdv.task3.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import otus.moryakovdv.task3.model.User;

@Repository
public interface UsersCrudRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByUserNameAndPassword(String userName, String password);

	
	
	
}