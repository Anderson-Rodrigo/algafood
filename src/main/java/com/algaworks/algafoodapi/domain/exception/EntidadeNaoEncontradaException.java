package com.algaworks.algafoodapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String mensagem){
		super(mensagem);
	}

//	public class EntidadeNaoEncontradaException extends ResponseStatusException {
//
//		private static final long serialVersionUID = 1L;
//
//		public EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
//			super(status, mensagem);
//		}
//
//		public EntidadeNaoEncontradaException(String mensagem){
//			this(HttpStatus.NOT_FOUND, mensagem);
//		}
}
