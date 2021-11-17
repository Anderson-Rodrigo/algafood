package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

	private final EstadoRepository estadoRepository;

	public EstadoService(EstadoRepository estadoRepository){
		this.estadoRepository = estadoRepository;
	}

	public Estado salvar(Estado estado){
		return estadoRepository.save(estado);
	}

	public List<Estado> listar(){
		return estadoRepository.findAll();
	}

	public Optional<Estado> buscar(Long id){
		return estadoRepository.findById(id);
	}

	public void remover(Long id){
		Optional<Estado> estado = estadoRepository.findById(id);
		if(estado.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não foi encontrado nenhum estádo com o id %d", id));
		}
		estadoRepository.delete(estado.get());
	}

	public Estado atualizar (Long id, Estado estado) {
		Optional<Estado> estadoBusca = estadoRepository.findById(id);
		if(estadoBusca.isEmpty()){
			throw new EntidadeNaoEncontradaException(String.format("Não existe o estado com id %d", id));
		}
		BeanUtils.copyProperties(estado, estadoBusca.get(), "id");

		return estadoRepository.save(estadoBusca.get());
	}
}
