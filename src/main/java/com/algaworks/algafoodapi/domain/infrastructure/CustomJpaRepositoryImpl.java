package com.algaworks.algafoodapi.domain.infrastructure;

import com.algaworks.algafoodapi.domain.repository.CustomJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	@Autowired
	private EntityManager entityManager;

	public CustomJpaRepositoryImpl (JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);

		this.entityManager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro () {
		var jpql = "from " + getDomainClass().getName();

		T entity = entityManager.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();

		return Optional.ofNullable(entity);
	}
}
