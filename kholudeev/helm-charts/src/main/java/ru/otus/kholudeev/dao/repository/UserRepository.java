package ru.otus.kholudeev.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.kholudeev.dao.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
             FROM User po
             WHERE po.id = :id
            """)
    Optional<User> findById(@Param("id") Long id);

    @Query("""
             FROM User po
             WHERE po.name = :name
            """)
    Optional<User> findByName(@Param("name") String name);

    @Query("""
             FROM User po
             WHERE po.login = :login
            """)
    Optional<User> findByLogin(@Param("login") String login);

    @Query("""
             DELETE
             FROM User po
             WHERE po.login = :login
            """)
    void deleteByLogin(@Param("login") String login);
}
