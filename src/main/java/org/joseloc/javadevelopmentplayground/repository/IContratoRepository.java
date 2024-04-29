package org.joseloc.javadevelopmentplayground.repository;

import org.joseloc.javadevelopmentplayground.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IContratoRepository extends JpaRepository<Contract, Integer> {
    @Query(value = "SELECT * FROM contract WHERE tipo = :tipo", nativeQuery = true)
    Contract findContratoByTypeContract(@Param("tipo") String tipo);
}
