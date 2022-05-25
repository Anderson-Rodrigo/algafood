package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@GetMapping
	public List<Cozinha> lista () {
		return cozinhaRepository.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
		Optional<Cozinha> cozinhaAtual = cozinhaService.buscar(id);

		if(cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

			return new ResponseEntity<>(cozinhaService.salvar(cozinhaAtual.get()), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		try {
			cozinhaService.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);

		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(EntidadeEmUsoException e) {
			return new ResponseEntity<>(e, HttpStatus.CONFLICT);
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid Cozinha cozinha){
		Cozinha coz = cozinhaService.salvar(cozinha);
		return new ResponseEntity<>(coz.getId(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Cozinha cozinha = cozinhaService.buscar(id).orElseThrow(() -> new EntidadeNaoEncontradaException("teste"));
//
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.add(HttpHeaders.LOCATION, "http://api.algafood.loca:8080/cozinhas");
//
//		return ResponseEntity
//				.status(HttpStatus.FOUND)
//				.headers(httpHeaders)
//				.build();
		//return ResponseEntity.ok().build();
		if(cozinha != null){
			return new ResponseEntity<>(cozinhaService.buscar(id).get(), HttpStatus.OK) ;
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
	}

	@GetMapping("/buscar_por_nome")
	public ResponseEntity<List<Cozinha>> buscarPorNome(@RequestParam("nome") String nome){
		List<Cozinha> cozinhas = cozinhaService.consultaPorNome(nome);
		return new ResponseEntity<>(cozinhas, HttpStatus.OK);
	}

	@GetMapping("/consulta_por_nome")
	public ResponseEntity<Cozinha> consultarPorNome(String nome, Long cozinhaId){
		Cozinha cozinhas = cozinhaService.consultaPorNome(nome, cozinhaId);
		return new ResponseEntity<>(cozinhas, HttpStatus.OK);
	}
}
