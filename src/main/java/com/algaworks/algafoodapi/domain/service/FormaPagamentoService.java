package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {

	private final FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamentoService (FormaPagamentoRepository formaPagamentoRepository) {
		this.formaPagamentoRepository = formaPagamentoRepository;
	}

	public FormaPagamento salvar (FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

}
