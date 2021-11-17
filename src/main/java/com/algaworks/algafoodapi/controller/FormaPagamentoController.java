package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forma_pagamento")
public class FormaPagamentoController {

	private final FormaPagamentoService formaPagamentoService;

	public FormaPagamentoController(FormaPagamentoService formaPagamentoService){
		this.formaPagamentoService = formaPagamentoService;
	}

	@PostMapping
	public ResponseEntity<FormaPagamento> salvar(@RequestBody FormaPagamento formaPagamento){
		formaPagamento = formaPagamentoService.salvar(formaPagamento);
		return new ResponseEntity<>(formaPagamento, HttpStatus.OK);
	}

}
