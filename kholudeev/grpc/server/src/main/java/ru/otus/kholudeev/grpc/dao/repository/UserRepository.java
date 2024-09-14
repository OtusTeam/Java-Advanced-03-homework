package ru.otus.kholudeev.grpc.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.kholudeev.grpc.dao.model.DaoUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DaoUser, Long> {

    @Query("""
             FROM DaoUser po
             WHERE po.id = :id
            """)
    Optional<DaoUser> findById(@Param("id") Long id);

    @Query("""
             FROM DaoUser po
             WHERE po.name = :name
            """)
    Optional<DaoUser> findByName(@Param("name") String name);

}
