package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha){
		return cozinhaRepository.save(cozinha);
	}

	public void remover(Long id){
		try {
			cozinhaRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com o código %d", id));
		} catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
		}
	}

	public Optional<Cozinha> buscar(Long id){
		return cozinhaRepository.findById(id);
	}

	public List<Cozinha> consultaPorNome(String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}

	public Cozinha consultaPorNome(String nome, Long cozinhaId){
		return cozinhaRepository.consultarPorNome(nome, cozinhaId);
	}
}
