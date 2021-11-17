package com.algaworks.algafoodapi.domain.infrastructure;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl /*implements CozinhaRepository*/ {

//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public List<Cozinha> listar(){
//
//		TypedQuery<Cozinha> query = entityManager.createQuery("from Cozinha", Cozinha.class );
//		return query.getResultList();
//	}
//
//	@Transactional
//	@Override
//	public Cozinha salvar(Cozinha cozinha){
//		return entityManager.merge(cozinha);
//	}
//
//	@Override
//	public List<Cozinha> buscarCozinha (String nome) {
//		return entityManager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
//				.setParameter("nome", "%" +  nome + "%")
//				.getResultList();
//	}
//
//	@Override
//	public Cozinha buscar(Long id){
//		return entityManager.find(Cozinha.class, id);
//	}
//
//	@Transactional
//	@Override
//	public void remover(Long id){
//		Cozinha cozinha = buscar(id);
//		if( cozinha == null ){
//			throw new EmptyResultDataAccessException(1);
//		}
//		entityManager.remove(cozinha);
//	}
}
