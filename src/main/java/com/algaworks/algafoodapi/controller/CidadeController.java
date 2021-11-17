package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
		try {
			cidade = cidadeService.salvar(cidade);
		} catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		try{
			cidade = cidadeService.atualizar(id, cidade);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cidadeService.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id){
		Optional<Cidade> cidade = cidadeService.buscar(id);
		if(cidade.isPresent()){
			return new ResponseEntity<>(cidade.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<?> listar(){
		List<Cidade> cidades = cidadeService.listar();
		return new ResponseEntity<>(cidades, HttpStatus.OK);
	}

}
