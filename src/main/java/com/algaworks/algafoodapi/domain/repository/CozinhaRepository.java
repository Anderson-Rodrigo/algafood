package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

//	@Query(value = "select * from tab_cozinha where tab_cozinha.nom_cozinha like(?) ", nativeQuery = true)
//	public List<Cozinha> findByNome(String nome);

	public List<Cozinha> findByNomeContaining(String nome);

	//pega o primeiro
	public List<Cozinha> findFirstByNomeContaining(String nome);

	//pega os dois primeiros
	public List<Cozinha> findTop2ByNomeContaining(String nome);

	//se for tru retorna o resultado
	//boolean existByNome(String nome);

	//@Query("from Cozinha where nome like %:nome% and id = :id")
	public Cozinha consultarPorNome(String nome, @Param("id") Long cozinhaId);
}
