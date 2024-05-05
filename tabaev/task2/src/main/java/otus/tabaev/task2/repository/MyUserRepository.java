package otus.tabaev.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import otus.tabaev.task2.model.MyUser;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByLogin(String login);

}
