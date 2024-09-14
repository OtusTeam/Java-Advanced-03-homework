package ru.otus.kholudeev.grpc.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.kholudeev.grpc.dao.model.DaoProduct;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<DaoProduct, Long> {

    @Query("""
             FROM DaoProduct po
             WHERE po.id = :id
            """)
    Optional<DaoProduct> findById(@Param("id") Long id);
}
