package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join r.cozinha join fetch r.formasPagamento")
    List<Restaurante> findAll();

}
