package com.algaworks.algafoodapi.domain.infrastructure;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Restaurante> listar(String nome, BigDecimal taxaFreteIncial, BigDecimal taxaFreteFinal){
		var jpql = "from Restaurante where nome like :nome and taxaFrete between :tataxaFreteIncial and :taxaFreteFinal";

		return entityManager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%")
				.setParameter("tataxaFreteIncial", taxaFreteIncial)
				.setParameter("taxaFreteFinal", taxaFreteFinal)
				.getResultList();
	}
 }
