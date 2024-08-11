package otus.tabaev.task12.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import otus.tabaev.task12.model.MyUser;
import reactor.core.publisher.Mono;

@Repository
public interface MyUserRepository extends R2dbcRepository<MyUser, Long> {

    Mono<MyUser> findByLogin(String login);

    Mono<MyUser> deleteByLogin(String login);

}
