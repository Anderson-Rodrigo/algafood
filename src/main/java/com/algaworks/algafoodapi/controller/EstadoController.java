package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;

	@GetMapping
	public ResponseEntity<?> listar(){
		List<Estado> estados = estadoService.listar();
		if(estados.isEmpty()){
			return new ResponseEntity<>( HttpStatus.FOUND);

		}
		return new ResponseEntity<>(estados, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id){
		Optional<Estado> estadoDaBusca = estadoService.buscar(id);
		if(estadoDaBusca.isEmpty()){
			return new ResponseEntity<>(HttpStatus.FOUND);
		}

		return new ResponseEntity<>(estadoDaBusca.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Estado estado){
		try {
			estado = estadoService.salvar(estado);
			return new ResponseEntity<>(estado, HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@PutMapping("{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado){
		try {
			estado = estadoService.atualizar(id, estado);
			return new ResponseEntity<>(estado, HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try{
			estadoService.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

}
