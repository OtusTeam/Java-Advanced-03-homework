package otus.moryakovdv.task13.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import otus.moryakovdv.task13.model.User;

/**Spring JPA репозиторий для работы с сущностью USER*/
@Repository
public interface UsersCrudRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByUserNameAndPassword(String userName, String password);

	
	
	
}