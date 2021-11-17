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

	public Cidade atualizar(Long id, Cidade cidade){
		Optional<Cidade> cidadeBusca = cidadeRepository.findById(id);
		if(cidadeBusca.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhum cidade com o id %d", id));
		}

		BeanUtils.copyProperties(cidade, cidadeBusca.get(), "id");

		return cidadeRepository.save(cidadeBusca.get());

	}

	public void remover(Long id){
		Cidade cidade = cidadeRepository.getById(id);
		if(cidade == null){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrada nenhum cidade com o id %d", id));
		}
		cidadeRepository.delete(cidade);
	}

	public Optional<Cidade> buscar(Long id){
		return cidadeRepository.findById(id);
	}

	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
}
