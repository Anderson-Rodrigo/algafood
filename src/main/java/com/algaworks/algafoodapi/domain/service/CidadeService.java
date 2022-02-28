package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;

	public Cidade salvar(Cidade cidade){
		Optional<Estado> estado = estadoService.buscar(cidade.getEstado().getId());
		if(estado.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum estado com id %d", cidade.getEstado().getId()));
		}
		return cidadeRepository.save(cidade);
	}

	public Cidade atualizar(Cidade cidade, Cidade cidadeBusca){

		BeanUtils.copyProperties(cidade, cidadeBusca, "id");

		return cidadeRepository.save(cidadeBusca);

	}

	public void remover(Long id){
		Optional<Cidade> cidade = cidadeRepository.findById(id);

		if(cidade.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhum cidade com o id %d", id));
		}
		cidadeRepository.delete(cidade.get());
	}

	public Optional<Cidade> buscar(Long id){
		return cidadeRepository.findById(id);
	}

	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
}
