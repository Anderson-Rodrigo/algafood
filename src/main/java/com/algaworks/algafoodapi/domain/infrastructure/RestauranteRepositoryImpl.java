package com.algaworks.algafoodapi.domain.infrastructure;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Restaurante> listar (String nome, BigDecimal taxaFreteIncial, BigDecimal taxaFreteFinal) {
		//var jpql = "from Restaurante where nome like :nome and taxaFrete between :tataxaFreteIncial and :taxaFreteFinal";

		//		var jpql = new StringBuilder();
		//		jpql.append("from Restaurante where 0 = 0 ");
		//
		//		var parametros = new HashMap<String, Object>();
		//
		//		if(StringUtils.hasLength(nome)){
		//			jpql.append(" and nome like :nome ");
		//			parametros.put("nome", "%" + nome + "%" );
		//		}
		//
		//		if(taxaFreteFinal != null){
		//			jpql.append(" and taxaFrete >= :tataxaFreteIncial ");
		//			parametros.put("tataxaFreteIncial", taxaFreteIncial );
		//		}
		//
		//		if(taxaFreteIncial != null){
		//			jpql.append(" and taxaFrete <= :tataxaFreteIncial ");
		//			parametros.put("taxaFreteFinal", taxaFreteFinal );
		//		}
		//
		//		TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
		//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		//
		//		return query.getResultList();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);

		var predicates = new ArrayList<Predicate>();

		if(StringUtils.hasLength(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}

		if(taxaFreteIncial != null){
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteIncial));
		}

		if(taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Restaurante> query = entityManager.createQuery(criteria);

		return query.getResultList();

	}

}
