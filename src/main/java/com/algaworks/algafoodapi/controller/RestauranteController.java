package com.algaworks.algafoodapi.controller;

import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.infrastructure.RestauranteRepositoryImpl;
import com.algaworks.algafoodapi.domain.infrastructure.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafoodapi.domain.infrastructure.spec.RestauranteComNomeSemelhanteSpec;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteService restauranteService;

	@GetMapping
	public ResponseEntity<?> listar(){
		List<Restaurante> restaurante = restauranteService.lista();

		if(restaurante.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(restaurante, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		Optional<Restaurante> restaurante = restauranteService.buscar(id);

		if(restaurante.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(restaurante.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante){
		try {
			restaurante = restauranteService.salvar(restaurante);
			return new ResponseEntity<>(restaurante, HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		try {
			restaurante = restauranteService.atualizar(id, restaurante);
			return new ResponseEntity<>(restaurante, HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarAlgumasInfo(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		try {
			Restaurante restaurante =restauranteService.atualizarAlgumasInfo(id, campos);
			return new ResponseEntity<>(restaurante, HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			restauranteService.remover(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EntidadeNaoEncontradaException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/buscar-por-nome")
	public ResponseEntity<List<?>> buscarPorNomeETaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		List<Restaurante> restaurante = restauranteService.buscarPorNome(nome, taxaFreteInicial, taxaFreteFinal);

		if(restaurante.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(restaurante, HttpStatus.OK);
	}

	@GetMapping("/com-frete-gratis")
	public ResponseEntity<List<?>> buscarFreteGratis(String nome){
		var freteGratis = new RestauranteComFreteGratisSpec();
		var nomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		var result = restauranteService.buscarFreteGratis(freteGratis, nomeSemelhante);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}